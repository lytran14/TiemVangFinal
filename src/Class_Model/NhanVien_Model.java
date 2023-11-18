package Class_Model;

import java.util.Date;

public class NhanVien_Model {

    String MANV;
    String TENNV;
    String SODT;
    String EMAIL;
    String DIACHI;
    boolean VITRICONGVIEC;
    boolean GIOITINH;
    Date NGAYSINH;
    String MATKHAU;

    public NhanVien_Model() {
    }

    public NhanVien_Model(String MANV, String TENNV, String SODT, String EMAIL, String DIACHI, boolean VITRICONGVIEC, boolean GIOITINH, Date NGAYSINH, String MATKHAU) {
        this.MANV = MANV;
        this.TENNV = TENNV;
        this.SODT = SODT;
        this.EMAIL = EMAIL;
        this.DIACHI = DIACHI;
        this.VITRICONGVIEC = VITRICONGVIEC;
        this.GIOITINH = GIOITINH;
        this.NGAYSINH = NGAYSINH;
        this.MATKHAU = MATKHAU;
    }

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public String getTENNV() {
        return TENNV;
    }

    public void setTENNV(String TENNV) {
        this.TENNV = TENNV;
    }

    public String getSODT() {
        return SODT;
    }

    public void setSODT(String SODT) {
        this.SODT = SODT;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }

    public boolean isVITRICONGVIEC() {
        return VITRICONGVIEC;
    }

    public void setVITRICONGVIEC(boolean VITRICONGVIEC) {
        this.VITRICONGVIEC = VITRICONGVIEC;
    }

    public boolean getVITRICONGVIEC() {
        return VITRICONGVIEC;
    }

    public boolean isGIOITINH() {
        return GIOITINH;
    }

    public void setGIOITINH(boolean GIOITINH) {
        this.GIOITINH = GIOITINH;
    }

    public boolean getGIOITINH() {
        return GIOITINH;
    }

    public Date getNGAYSINH() {
        return NGAYSINH;
    }

    public void setNGAYSINH(Date NGAYSINH) {
        this.NGAYSINH = NGAYSINH;
    }

    public String getMATKHAU() {
        return MATKHAU;
    }

    public void setMATKHAU(String MATKHAU) {
        this.MATKHAU = MATKHAU;
    }

}
