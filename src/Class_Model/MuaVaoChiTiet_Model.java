package Class_Model;

public class MuaVaoChiTiet_Model {

    String MACTMV;
    String MAMV;
    String MASP;
    String LOAISP ;
    double KHOILUONG;
    double DONGIAMUA;
    double THANHTIEN;

    public MuaVaoChiTiet_Model() {
    }

    public MuaVaoChiTiet_Model(String MACTMV, String MAMV, String MASP, String LOAISP, double KHOILUONG, double DONGIAMUA, double THANHTIEN) {
        this.MACTMV = MACTMV;
        this.MAMV = MAMV;
        this.MASP = MASP;
        this.LOAISP = LOAISP;
        this.KHOILUONG = KHOILUONG;
        this.DONGIAMUA = DONGIAMUA;
        this.THANHTIEN = THANHTIEN;
    }

   
    public String getMACTMV() {
        return MACTMV;
    }

    public void setMACTMV(String MACTMV) {
        this.MACTMV = MACTMV;
    }

    public String getMAMV() {
        return MAMV;
    }

    public void setMAMV(String MAMV) {
        this.MAMV = MAMV;
    }

   

    
    public String getMASP() {
        return MASP;
    }

    public void setMASP(String MASP) {
        this.MASP = MASP;
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

    public double getDONGIAMUA() {
        return DONGIAMUA;
    }

    public void setDONGIAMUA(double DONGIAMUA) {
        this.DONGIAMUA = DONGIAMUA;
    }

    

    public double getTHANHTIEN() {
        return THANHTIEN;
    }

    public void setTHANHTIEN(double THANHTIEN) {
        this.THANHTIEN = THANHTIEN;
    }
    
    
}
