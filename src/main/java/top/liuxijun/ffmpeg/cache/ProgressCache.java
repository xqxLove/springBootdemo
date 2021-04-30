package top.liuxijun.ffmpeg.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 保存视频处理进度 按照输出文件名做KEY
 *
 * @author zhangkun
 * @date 2021/4/28 14:33
 */
@Component
public class ProgressCache {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // 初始大小1 超过十分钟key没有读写操作就自动过期
    private final Cache<String, Integer> cache = CacheBuilder.newBuilder().initialCapacity(1).expireAfterAccess(10, TimeUnit.MINUTES).build();

    /**
     * 更新进度
     *
     * @param key      输出文件名
     * @param progress 进度
     */
    public void put(String key, Integer progress) {
        if (StringUtils.isBlank(key)) {
            return;
        }
        cache.put(key, progress);
    }

    /**
     * 读取进度
     *
     * @param key 输出文件名
     * @return 进度
     */
    public Integer get(String key) {
        return cache.getIfPresent(key);
    }

}
