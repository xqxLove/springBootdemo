package top.liuxijun.ffmpeg.model.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 常用分辨率枚举
 *
 * @author zhangkun
 * @date 2021/4/27 11:07
 */
public enum ResolvingPowerEnum {
    Unknow("unknow", "未知"),
    Mobile("手机", "320x240"),
    Television("电视", "640x480"),
    DVD("DVD", "720x576"),
    P240("240p", "426*240"),
    P360("360p", "640x360"),
    P480("480p", "854x480"),
    P720HD("HD 720P", "1280x720"),
    P1080HD("HD 1080P", "1920x1080");


    private String code;
    private String desc;

    private ResolvingPowerEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 根据code获得枚举对象
     */
    public static ResolvingPowerEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return ResolvingPowerEnum.Unknow;
        }
        for (ResolvingPowerEnum e : ResolvingPowerEnum.values()) {
            if (code.equals(e.getCode())) {
                return e;
            }
        }
        return ResolvingPowerEnum.Unknow;
    }

    public static Map<String, String> getEnumsMap() {
        Map<String, String> map = new HashMap<String, String>();
        for (ResolvingPowerEnum e : ResolvingPowerEnum.values()) {
            if (!StringUtils.equals(ResolvingPowerEnum.Unknow.getCode(), e.getCode())) {
                map.put(e.getCode(), e.getDesc());
            }
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println(ResolvingPowerEnum.getEnumsMap());
    }
}
