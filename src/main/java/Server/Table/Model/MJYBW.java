package Server.Table.Model;

import java.util.Date;

public class MJYBW extends MJYBWKey {
    private String 前台交易码;

    private Date 后台日期;

    private String 后台流水;

    private String 后台交易码;

    private String 交易状态;

    private String 终端号;

    private String IP地址;

    private Date 时间戳;

    private String 记录状态;

    public String get前台交易码() {
        return 前台交易码;
    }

    public void set前台交易码(String 前台交易码) {
        this.前台交易码 = 前台交易码;
    }

    public Date get后台日期() {
        return 后台日期;
    }

    public void set后台日期(Date 后台日期) {
        this.后台日期 = 后台日期;
    }

    public String get后台流水() {
        return 后台流水;
    }

    public void set后台流水(String 后台流水) {
        this.后台流水 = 后台流水;
    }

    public String get后台交易码() {
        return 后台交易码;
    }

    public void set后台交易码(String 后台交易码) {
        this.后台交易码 = 后台交易码;
    }

    public String get交易状态() {
        return 交易状态;
    }

    public void set交易状态(String 交易状态) {
        this.交易状态 = 交易状态;
    }

    public String get终端号() {
        return 终端号;
    }

    public void set终端号(String 终端号) {
        this.终端号 = 终端号;
    }

    public String getIP地址() {
        return IP地址;
    }

    public void setIP地址(String IP地址) {
        this.IP地址 = IP地址;
    }

    public Date get时间戳() {
        return 时间戳;
    }

    public void set时间戳(Date 时间戳) {
        this.时间戳 = 时间戳;
    }

    public String get记录状态() {
        return 记录状态;
    }

    public void set记录状态(String 记录状态) {
        this.记录状态 = 记录状态;
    }
}