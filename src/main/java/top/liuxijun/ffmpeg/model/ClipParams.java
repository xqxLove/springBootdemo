package top.liuxijun.ffmpeg.model;

/**
 * 剪辑参数
 *
 * @author zhangkun
 * @date 2021/4/27 10:07
 */
public class ClipParams {

    private String sourcePath;      // 原文件地址
    private String outPath;         // 输出地址
    private String resolvingPower;  // 设置分辨率

    /*** 视频剪切参数 ***/
    private String startClipTime;   // -ss 剪切开始时间 秒
    private String clipDuration;    // -t 剪切时长 秒

    /*** 画面截取参数 ***/
    private String xAxis;           // 左上角X轴坐标
    private String yAxis;           // 左上角Y轴坐标
    private String height;          // 剪切高度
    private String widht;           // 剪切宽度

    /*** 添加图片参数 ***/
    private String imgXAxis;        // 图片左上角X轴
    private String imgYAxis;        // 图片左上角Y轴
    private String imgPath;         // 图片路径

    /*** 添加文本参数 ***/
    private String fontcolor;       // 文字颜色
    private String fontfile;        // 字体（字体文件路径）
    private String fontsize;        // 字体大小
    private String text;            // 文本内容
    private String textXAxis;       // 文本左上角X轴
    private String textYAxis;       // 文本左上角Y轴

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

    public String getResolvingPower() {
        return resolvingPower;
    }

    public void setResolvingPower(String resolvingPower) {
        this.resolvingPower = resolvingPower;
    }

    public String getStartClipTime() {
        return startClipTime;
    }

    public void setStartClipTime(String startClipTime) {
        this.startClipTime = startClipTime;
    }

    public String getClipDuration() {
        return clipDuration;
    }

    public void setClipDuration(String clipDuration) {
        this.clipDuration = clipDuration;
    }

    public String getxAxis() {
        return xAxis;
    }

    public void setxAxis(String xAxis) {
        this.xAxis = xAxis;
    }

    public String getyAxis() {
        return yAxis;
    }

    public void setyAxis(String yAxis) {
        this.yAxis = yAxis;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidht() {
        return widht;
    }

    public void setWidht(String widht) {
        this.widht = widht;
    }

    public String getImgXAxis() {
        return imgXAxis;
    }

    public void setImgXAxis(String imgXAxis) {
        this.imgXAxis = imgXAxis;
    }

    public String getImgYAxis() {
        return imgYAxis;
    }

    public void setImgYAxis(String imgYAxis) {
        this.imgYAxis = imgYAxis;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getFontcolor() {
        return fontcolor;
    }

    public void setFontcolor(String fontcolor) {
        this.fontcolor = fontcolor;
    }

    public String getFontfile() {
        return fontfile;
    }

    public void setFontfile(String fontfile) {
        this.fontfile = fontfile;
    }

    public String getFontsize() {
        return fontsize;
    }

    public void setFontsize(String fontsize) {
        this.fontsize = fontsize;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextXAxis() {
        return textXAxis;
    }

    public void setTextXAxis(String textXAxis) {
        this.textXAxis = textXAxis;
    }

    public String getTextYAxis() {
        return textYAxis;
    }

    public void setTextYAxis(String textYAxis) {
        this.textYAxis = textYAxis;
    }

}
