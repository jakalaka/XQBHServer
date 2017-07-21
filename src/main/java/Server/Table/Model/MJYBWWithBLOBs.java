package Server.Table.Model;

public class MJYBWWithBLOBs extends MJYBW {
    private byte[] 传入报文;

    private byte[] 请求支付宝报文;

    private byte[] 支付宝返回报文;

    private byte[] 传出报文;

    public byte[] get传入报文() {
        return 传入报文;
    }

    public void set传入报文(byte[] 传入报文) {
        this.传入报文 = 传入报文;
    }

    public byte[] get请求支付宝报文() {
        return 请求支付宝报文;
    }

    public void set请求支付宝报文(byte[] 请求支付宝报文) {
        this.请求支付宝报文 = 请求支付宝报文;
    }

    public byte[] get支付宝返回报文() {
        return 支付宝返回报文;
    }

    public void set支付宝返回报文(byte[] 支付宝返回报文) {
        this.支付宝返回报文 = 支付宝返回报文;
    }

    public byte[] get传出报文() {
        return 传出报文;
    }

    public void set传出报文(byte[] 传出报文) {
        this.传出报文 = 传出报文;
    }
}