package Class_Model;

public class BanRaChiTiet_Model {

    String MACTBR;
    String MABR;
    String MASP;
    String TENSP;
    String LOAISP;
    double KHOILUONG;
    double DONGIABAN;
    double THANHTIEN;

    @Override
    public String toString() {
        return this.TENSP;
    }

    public BanRaChiTiet_Model() {
    }

    public BanRaChiTiet_Model(String MACTBR, String MABR, String MASP, String TENSP, String LOAISP, double KHOILUONG, double DONGIABAN, double THANHTIEN) {
        this.MACTBR = MACTBR;
        this.MABR = MABR;
        this.MASP = MASP;
        this.TENSP = TENSP;
        this.LOAISP = LOAISP;
        this.KHOILUONG = KHOILUONG;
        this.DONGIABAN = DONGIABAN;
        this.THANHTIEN = THANHTIEN;
    }

    public String getMACTBR() {
        return MACTBR;
    }

    public void setMACTBR(String MACTBR) {
        this.MACTBR = MACTBR;
    }

    public String getMABR() {
        return MABR;
    }

    public void setMABR(String MABR) {
        this.MABR = MABR;
    }

    public String getMASP() {
        return MASP;
    }

    public void setMASP(String MASP) {
        this.MASP = MASP;
    }

    public String getTENSP() {
        return TENSP;
    }

    public void setTENSP(String TENSP) {
        this.TENSP = TENSP;
    }

    public String getLOAISP() {
        return LOAISP;
    }

    public void setLOAISP(String LOAISP) {
        this.LOAISP = LOAISP;
    }

    public double getKHOILUONG() {
        return KHOILUONG;
    }

    public void setKHOILUONG(double KHOILUONG) {
        this.KHOILUONG = KHOILUONG;
    }

    public double getDONGIABAN() {
        return DONGIABAN;
    }

    public void setDONGIABAN(double DONGIABAN) {
        this.DONGIABAN = DONGIABAN;
    }

    public double getTHANHTIEN() {
        return getKHOILUONG() * getDONGIABAN();
    }

    public void setTHANHTIEN(double THANHTIEN) {
        this.THANHTIEN = THANHTIEN;
    }

}
