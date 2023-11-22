
package Class_Model;

import java.util.Date;


public class MuaVao_Model  {
    String tenkh ;
    String maMV ;
    String maNV ;
    String maKH ;
    Date ngayLap ;
    Double tongGiaTri ;

    public MuaVao_Model() {
    }

    public MuaVao_Model(String tenkh, String maMV, String maNV, String maKH, Date ngayLap, Double tongGiaTri) {
        this.tenkh = tenkh;
        this.maMV = maMV;
        this.maNV = maNV;
        this.maKH = maKH;
        this.ngayLap = ngayLap;
        this.tongGiaTri = tongGiaTri;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    

    public String getMaMV() {
        return maMV;
    }

    public void setMaMV(String maMV) {
        this.maMV = maMV;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public Double getTongGiaTri() {
        return tongGiaTri;
    }

    public void setTongGiaTri(Double tongGiaTri) {
        this.tongGiaTri = tongGiaTri;
    }
    
    
}
