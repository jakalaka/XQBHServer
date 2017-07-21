package Server.Table.Model;

public class MJYBWWithBLOBs extends MJYBW {
    private byte[] CRBW_U;

    private byte[] DSFSND;

    private byte[] DSFRCV;

    private byte[] CCBW_U;

    public byte[] getCRBW_U() {
        return CRBW_U;
    }

    public void setCRBW_U(byte[] CRBW_U) {
        this.CRBW_U = CRBW_U;
    }

    public byte[] getDSFSND() {
        return DSFSND;
    }

    public void setDSFSND(byte[] DSFSND) {
        this.DSFSND = DSFSND;
    }

    public byte[] getDSFRCV() {
        return DSFRCV;
    }

    public void setDSFRCV(byte[] DSFRCV) {
        this.DSFRCV = DSFRCV;
    }

    public byte[] getCCBW_U() {
        return CCBW_U;
    }

    public void setCCBW_U(byte[] CCBW_U) {
        this.CCBW_U = CCBW_U;
    }
}