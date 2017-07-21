package Server.Table.Model;

import java.util.Date;

public class MJYBWKey {
    private String 法人代码;

    private Date 前台日期;

    private String 前台流水;

    public String get法人代码() {
        return 法人代码;
    }

    public void set法人代码(String 法人代码) {
        this.法人代码 = 法人代码;
    }

    public Date get前台日期() {
        return 前台日期;
    }

    public void set前台日期(Date 前台日期) {
        this.前台日期 = 前台日期;
    }

    public String get前台流水() {
        return 前台流水;
    }

    public void set前台流水(String 前台流水) {
        this.前台流水 = 前台流水;
    }
}