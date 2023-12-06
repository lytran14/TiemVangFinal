package Class_Model;

public class SanPham_Model {

    String MASP;
    String TENSP;
    String MOTA;
    double DONGIANHAP;
    String LOAI;
    String MALOAISP;
    double DONGIABAN;
    String DONVITINH;
    String MADVTINH;
    String HINH;

    public SanPham_Model() {
    }

    public SanPham_Model(String MASP, String TENSP, String MOTA, double DONGIANHAP, String LOAI, String MALOAISP, double DONGIABAN, String DONVITINH, String MADVTINH, String HINH) {
        this.MASP = MASP;
        this.TENSP = TENSP;
        this.MOTA = MOTA;
        this.DONGIANHAP = DONGIANHAP;
        this.LOAI = LOAI;
        this.MALOAISP = MALOAISP;
        this.DONGIABAN = DONGIABAN;
        this.DONVITINH = DONVITINH;
        this.MADVTINH = MADVTINH;
        this.HINH = HINH;
    }

    public String getHINH() {
        return HINH;
    }

    public void setHINH(String HINH) {
        this.HINH = HINH;
    }

    public String getMADVTINH() {
        return MADVTINH;
    }

    public void setMADVTINH(String MADVTINH) {
        this.MADVTINH = MADVTINH;
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

    public String getMOTA() {
        return MOTA;
    }

    public void setMOTA(String MOTA) {
        this.MOTA = MOTA;
    }

    public double getDONGIANHAP() {
        return DONGIANHAP;
    }

    public void setDONGIANHAP(double DONGIANHAP) {
        this.DONGIANHAP = DONGIANHAP;
    }

    public String getLOAI() {
        return LOAI;
    }

    public void setLOAI(String LOAI) {
        this.LOAI = LOAI;
    }

    public String getMALOAISP() {
        return MALOAISP;
    }

    public void setMALOAISP(String MALOAISP) {
        this.MALOAISP = MALOAISP;
    }

    public double getDONGIABAN() {
        return DONGIABAN;
    }

    public void setDONGIABAN(double DONGIABAN) {
        this.DONGIABAN = DONGIABAN;
    }

    public String getDONVITINH() {
        return DONVITINH;
    }

    public void setDONVITINH(String DONVITINH) {
        this.DONVITINH = DONVITINH;
    }

    @Override
    public String toString() {
        return this.LOAI;
    }

}
