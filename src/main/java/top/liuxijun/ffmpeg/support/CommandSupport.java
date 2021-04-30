package top.liuxijun.ffmpeg.support;

import org.apache.commons.lang3.StringUtils;
import top.liuxijun.ffmpeg.model.ClipParams;
import top.liuxijun.ffmpeg.model.VideoParams;

import java.util.ArrayList;
import java.util.List;

/**
 * 命令构建
 * 多个不同的操作不能同时处理，例如添加文本、添加文字，这两个步骤需要分别执行
 * 这样会存在问题，在处理不同的操作的时候，系统应该处理两次命令，第一次命令使用源文件，第二次命令使用第一次命令生成的文件
 *
 * @author zhangkun
 * @date 2021/4/25 10:18
 */
public class CommandSupport {

    private final static String COMMAND_FFMPEG = "ffmpeg";
    private final static String COMMAND_YES = "-y";
    private final static String COMMAND_READ_FILE = "-i";           // 读取文件（地址）命令
    private final static String COMMAND_VIDEO_FILTER = "-vf";       // 视频过滤器命令
    private final static String COMMAND_RESOLVING_POWER = "-s";     // 分辨率命令
    private final static String COMMAND_CLIP_START_TIME = "-ss";    // 剪切开始时间命令
    private final static String COMMAND_CLIP_DURATION = "-t";       // 剪切持续时间命令

    private final static String COMMAND_FRAME_RATE = "-r";          // 压缩帧率命令
    private final static String COMMAND_VIDEO_BIT_RATE = "-b:v";    // 压缩视频码率命令
    private final static String COMMAND_AUDIO_BIT_RATE = "-b:a";    // 压缩音频吗了命令

    private final static String COLON = ":";      // 冒号
    private final static String COMMA = ",";      //逗号

    public static void main(String[] args) {
        ClipParams clipParams = new ClipParams();

        clipParams.setSourcePath("d:/video/demo001.mp4");
        clipParams.setOutPath("d:/video/demo001_out.mp4");
        clipParams.setResolvingPower("320*240");

        clipParams.setStartClipTime("00:00:05");
        clipParams.setClipDuration("30");

        clipParams.setxAxis("30");
        clipParams.setyAxis("30");
        clipParams.setHeight("200");
        clipParams.setWidht("200");

        clipParams.setImgPath("d:/video/logo.png");
        clipParams.setImgXAxis("20");
        clipParams.setImgYAxis("20");


        clipParams.setFontcolor("white");
        clipParams.setFontfile("d:/video/cly.ttf");
        clipParams.setFontsize("30");
        clipParams.setText("我很快乐");
        clipParams.setTextXAxis("50");
        clipParams.setTextYAxis("50");

        CommandSupport commandSupport = new CommandSupport();
        List<String> list = commandSupport.buildClip(clipParams);
        StringBuilder command = new StringBuilder();
        for (String temp : list) {
            command.append(temp).append(" ");
        }
        System.out.println(command.toString());
    }

    /**
     * 构建剪辑命令
     */
    public static List<String> buildClip(ClipParams clipParams) {
        List<String> command = new ArrayList<>();
        if (StringUtils.isBlank(clipParams.getSourcePath()) || StringUtils.isBlank(clipParams.getOutPath())) {
            return command;
        }
        CommandSupport handle = new CommandSupport();
        command.add(COMMAND_FFMPEG);
        command.add(COMMAND_YES);
        command.add(COMMAND_READ_FILE);
        command.add(clipParams.getSourcePath());
        // 剪辑操作
        command.addAll(handle.getCutParams(clipParams));        // 裁剪
        command.addAll(handle.getResolvingPower(clipParams));   // 分辨率
        // 过滤器
        StringBuilder filter = new StringBuilder();
        filter.append(handle.getImgFilter(clipParams));         // 添加图片
        filter.append(handle.getTextFilter(clipParams));        // 添加文本
        filter.append(handle.getCropParamsFilter(clipParams));  // 视频剪裁
        if (filter.length() > 1) {
            command.add(COMMAND_VIDEO_FILTER);
            command.add(filter.substring(0, filter.length() - 1));
            //System.out.println("===:"+filter.toString());
        }
        // 输出
        command.add(clipParams.getOutPath());
        return command;
    }


    /**
     * 构建压缩命令
     */
    public static List<String> buildCompress(VideoParams videoParams) {
        List<String> command = new ArrayList<>();
        // 视频源
        command.add(COMMAND_READ_FILE);
        command.add(videoParams.getSourcePath());
        // 压缩参数
        command.add(COMMAND_FRAME_RATE);
        command.add(videoParams.getFrameRate());
        command.add(COMMAND_RESOLVING_POWER);
        command.add(videoParams.getResolvingPower());
        command.add(COMMAND_VIDEO_BIT_RATE);
        command.add(videoParams.getVideoBitRate());
        command.add(COMMAND_AUDIO_BIT_RATE);
        command.add(videoParams.getAudioBitRate());
        // 输出
        command.add(videoParams.getOutPath());
        return command;
    }


    /**
     * 设置视频裁剪参数
     */
    public String getCropParamsFilter(ClipParams clipParams) {
        if (clipParams == null || StringUtils.isBlank(clipParams.getHeight()) || StringUtils.isBlank(clipParams.getWidht())
                || StringUtils.isBlank(clipParams.getxAxis()) || StringUtils.isBlank(clipParams.getyAxis())) {
            return "";
        }
        StringBuilder cropParams = new StringBuilder("crop="); // 剪裁参数 height:width:x:y
        cropParams.append(clipParams.getHeight()).append(COLON);
        cropParams.append(clipParams.getWidht()).append(COLON);
        cropParams.append(clipParams.getxAxis()).append(COLON);
        cropParams.append(clipParams.getyAxis()).append(COMMA);
        return cropParams.toString();
    }

    /**
     * 设置视频分辨率参数
     */
    public List<String> getResolvingPower(ClipParams clipParams) {
        List<String> command = new ArrayList<>();
        if (clipParams == null || StringUtils.isBlank(clipParams.getResolvingPower())) {
            return command;
        }
        command.add(COMMAND_RESOLVING_POWER);
        command.add(clipParams.getResolvingPower());
        return command;
    }

    /**
     * 设置添加图片参数
     */
    public String getImgFilter(ClipParams clipParams) {
        List<String> command = new ArrayList<>();
        if (clipParams == null || StringUtils.isBlank(clipParams.getImgPath()) || StringUtils.isBlank(clipParams.getImgXAxis())
                || StringUtils.isBlank(clipParams.getImgYAxis())) {
            return "";
        }
        StringBuilder commandStr = new StringBuilder();
        commandStr.append("\"");
        commandStr.append("movie=").append(clipParams.getImgPath());
        commandStr.append("[watermark]").append(";").append("[in][watermark]");
        commandStr.append("overlay=");
        commandStr.append(clipParams.getImgXAxis()).append(COLON).append(clipParams.getImgYAxis());
        commandStr.append("\"").append(COMMA);
        return commandStr.toString();
    }

    /**
     * 设置添加文本参数
     */
    public String getTextFilter(ClipParams clipParams) {
        //List<String> command = new ArrayList<>();
        if (clipParams == null || StringUtils.isBlank(clipParams.getText()) || StringUtils.isBlank(clipParams.getTextXAxis())
                || StringUtils.isBlank(clipParams.getTextYAxis()) || StringUtils.isBlank(clipParams.getFontcolor())
                || StringUtils.isBlank(clipParams.getFontfile()) || StringUtils.isBlank(clipParams.getFontsize())) {
            return "";
        }
        StringBuilder textParams = new StringBuilder("drawtext=");
        textParams.append("fontcolor=").append(clipParams.getFontcolor()).append(COLON);
        textParams.append("fontfile=").append(clipParams.getFontfile()).append(COLON);
        textParams.append("fontsize=").append(clipParams.getFontsize()).append(COLON);
        textParams.append("text=").append(clipParams.getText()).append(COLON);
        textParams.append("x=").append(clipParams.getTextXAxis()).append(COLON);
        textParams.append("y=").append(clipParams.getTextYAxis()).append(COMMA);
        return textParams.toString();
    }

    /**
     * 设置视频剪切参数
     */
    public List<String> getCutParams(ClipParams clipParams) {
        List<String> command = new ArrayList<>();
        if (clipParams == null || StringUtils.isBlank(clipParams.getStartClipTime()) || StringUtils.isBlank(clipParams.getClipDuration())) {
            return command;
        }
        command.add(COMMAND_CLIP_START_TIME);
        command.add(clipParams.getStartClipTime());
        command.add(COMMAND_CLIP_DURATION);
        command.add(clipParams.getClipDuration());
        return command;
    }

}
