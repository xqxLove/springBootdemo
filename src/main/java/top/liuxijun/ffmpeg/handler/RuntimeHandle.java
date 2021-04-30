package top.liuxijun.ffmpeg.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.liuxijun.ffmpeg.cache.ProgressCache;
import top.liuxijun.ffmpeg.model.ClipParams;
import top.liuxijun.ffmpeg.support.CommandSupport;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 调用本地命令
 *
 * @author zhangkun
 * @date 2021/4/27 15:34
 */
@Component
public class RuntimeHandle {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final int HANDING_OVERTIME = 60;  // 处理超时时间 单位秒

    private int corePoolSize = Runtime.getRuntime().availableProcessors();
    private int maxPoolSize = Runtime.getRuntime().availableProcessors() * 2;
    private ThreadPoolExecutor startExecutor;    // 调用命线程
    private ThreadPoolExecutor streamExecutor;   // 输出流线程

    @Autowired
    private ProgressCache progressCache;

    /**
     * 初始化线程
     */
    @PostConstruct
    public void init() {
        // 核心线程池带大小/最大线程池大小/线程最大空闲时间/时间单位/线程等待队列/拒绝策略
        startExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 10, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(), new ThreadPoolExecutor.CallerRunsPolicy());
        streamExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 10, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 调用命令
     *
     * @param command  命令，把命名组装成集合，按空格区分
     * @param duration 输出视频实际时长，一般是原时长，如果视频有剪切，需要使用剪切后的时长，主要是方便统计进度
     * @param key      任务KEY
     */
    public void start(final List<String> command, Integer duration, String key) {
        init();
        startExecutor.submit(new Runnable() {
            @Override
            public void run() {
                if (command == null || command.size() == 0) {
                    logger.error("命令为空，执行失败");
                    return;
                }
                try {
                    ProcessBuilder builder = new ProcessBuilder();
                    builder.redirectErrorStream(true);  // 合并到保准输出流
                    Process process = builder.redirectErrorStream(true).command(command).start(); // 执行命令
                    outStream(process, duration, key);  // 标准输出流另起线程 否则可能出现阻塞情况
                    boolean flag = process.waitFor(HANDING_OVERTIME, TimeUnit.SECONDS); // 时间内未处理成功 根据返回状态强行关闭进程
                    if (flag) {
                        logger.info("处理结束,操作成功!");
                    } else {
                        progressCache.put(key, -1);
                        logger.info("处理超时,强制关闭!");
                        process.destroyForcibly();     // 强行关闭
                    }
                } catch (Exception e) {
                    logger.error("命令执行失败", e);
                }
            }
        });
    }

    public void outStream(final Process process, final Integer duration, final String key) {
        streamExecutor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //progressCache.put(key, "00.00");
                    InputStream ins = process.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(ins));
                    while (process.isAlive()) {
                        while (br.ready()) {
                            String readLog = br.readLine();
                            //System.out.println(readLog);
                            // 分析进度 从控制台输出信息中找到时间信息
                            if (readLog.contains("frame=") && readLog.contains("fps=") && readLog.contains("time=")
                                    && readLog.contains("size=") && readLog.contains("bitrate=") && readLog.contains("speed")) {
                                int index = readLog.indexOf("time=") + 5;
                                String timeStr = readLog.substring(index, index + 8);
                                Integer progress = percentage(timeStr, duration);
                                progressCache.put(key, progress);   // 处理进度添加到缓存中
                                logger.info("处理进度：{}%", progress);
                            }
                        }
                    }
                } catch (IOException e) {
                    progressCache.put(key, -1);
                    logger.error("流处理错", e);
                }
            }
        });
    }

    public Integer percentage(String timeStr, Integer duration) {
        Integer second = Integer.valueOf(timeStr.substring(6, 8));
        Integer minute = Integer.valueOf(timeStr.substring(3, 5));
        Integer hour = Integer.valueOf(timeStr.substring(0, 2));
        if (minute > 0) {
            second += minute * 60;
        }
        if (hour > 0) {
            second += hour * 60 * 60;
        }
        DecimalFormat df = new DecimalFormat("0");
        String progress = df.format((float) second / duration * 100);
        return Integer.valueOf(progress);
    }

    public static void main(String[] args) {
        ClipParams clipParams = new ClipParams();
        clipParams.setSourcePath("/video/demo001.mp4");
        clipParams.setOutPath("/video/demo001_out.mp4");
        clipParams.setResolvingPower("320*240");

        clipParams.setStartClipTime("00:00:05");
        clipParams.setClipDuration("120");

        clipParams.setxAxis("30");
        clipParams.setyAxis("30");
        clipParams.setHeight("200");
        clipParams.setWidht("200");

        clipParams.setImgPath("/video/logo.png");
        clipParams.setImgXAxis("20");
        clipParams.setImgYAxis("20");

        clipParams.setFontcolor("white");
        clipParams.setFontfile("/video/cly.ttf");
        clipParams.setFontsize("30");
        clipParams.setText("我很快乐");
        clipParams.setTextXAxis("50");
        clipParams.setTextYAxis("50");

        CommandSupport commandSupport = new CommandSupport();
        List<String> command = commandSupport.buildClip(clipParams);
        RuntimeHandle run = new RuntimeHandle();
        run.start(command, 120, "123456789");
    }

}
