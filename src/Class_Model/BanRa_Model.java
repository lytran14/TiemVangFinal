package Class_Model;

import java.util.Date;

public class BanRa_Model {

    String MABR;
    String MAKH;
    String MANV;
    Date NGAYLAP;
    double TONGGIATRI ;

    public BanRa_Model() {
    }

    public BanRa_Model(String MABR, String MAKH, String MANV, Date NGAYLAP, double TONGGIATRI) {
        this.MABR = MABR;
        this.MAKH = MAKH;
        this.MANV = MANV;
        this.NGAYLAP = NGAYLAP;
        this.TONGGIATRI = TONGGIATRI;
    }

    public String getMABR() {
        return MABR;
    }

    public void setMABR(String MABR) {
        this.MABR = MABR;
    }

    public String getMAKH() {
        return MAKH;
    }

    public void setMAKH(String MAKH) {
        this.MAKH = MAKH;
    }

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public Date getNGAYLAP() {
        return NGAYLAP;
    }

    public void setNGAYLAP(Date NGAYLAP) {
        this.NGAYLAP = NGAYLAP;
    }

    public double getTONGGIATRI() {
        return TONGGIATRI;
    }

    public void setTONGGIATRI(double TONGGIATRI) {
        this.TONGGIATRI = TONGGIATRI;
    }
    
    
}
