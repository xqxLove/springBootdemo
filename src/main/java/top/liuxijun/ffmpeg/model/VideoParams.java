package top.liuxijun.ffmpeg.model;

/**
 * 视频参数解析
 *
 * @author zhangkun
 * @date 2021/4/25 10:21
 */
public class VideoParams {

    private Long duration;              // 时长 秒
    private String resolvingPower;      // 分辨率
    private String videoCode;           // 视频编码
    private String audioCode;           // 音频编码
    private String frameRate;           // 帧率（FPS）
    private String videoBitRate;          // 视频码率 kbps
    private String audioBitRate;          // 音频码率 kbps

    private String sourcePath;      // 输入地址
    private String outPath;         // 输出地址

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getResolvingPower() {
        return resolvingPower;
    }

    public void setResolvingPower(String resolvingPower) {
        this.resolvingPower = resolvingPower;
    }

    public String getVideoCode() {
        return videoCode;
    }

    public void setVideoCode(String videoCode) {
        this.videoCode = videoCode;
    }

    public String getAudioCode() {
        return audioCode;
    }

    public void setAudioCode(String audioCode) {
        this.audioCode = audioCode;
    }

    public String getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(String frameRate) {
        this.frameRate = frameRate;
    }

    public String getVideoBitRate() {
        return videoBitRate;
    }

    public void setVideoBitRate(String videoBitRate) {
        this.videoBitRate = videoBitRate;
    }

    public String getAudioBitRate() {
        return audioBitRate;
    }

    public void setAudioBitRate(String audioBitRate) {
        this.audioBitRate = audioBitRate;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getOutPath() {
        return outPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }
}
