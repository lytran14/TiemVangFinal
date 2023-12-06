
package Class_Model;

import java.util.Date;


public class CamDo_Model {
    
    private String maCam;
    private Date ngayLap;
    private float soTienCam;
    private String maKH;
    private String tenKH;
    private String cccd;
    private String sdtKH;
    private String maNV;
    private float soNgay;
    private Date ngayCam;
    private Date ngayHetHan;
    private boolean trangThai;

    public CamDo_Model() {
    }

    public CamDo_Model(String maCam, Date ngayLap, float soTienCam, String maKH, String tenKH, String cccd, String sdtKH, String maNV, float soNgay, Date ngayCam, Date ngayHetHan, boolean trangThai) {
        this.maCam = maCam;
        this.ngayLap = ngayLap;
        this.soTienCam = soTienCam;
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.cccd = cccd;
        this.sdtKH = sdtKH;
        this.maNV = maNV;
        this.soNgay = soNgay;
        this.ngayCam = ngayCam;
        this.ngayHetHan = ngayHetHan;
        this.trangThai = trangThai;
    }

    public String getMaCam() {
        return maCam;
    }

    public void setMaCam(String maCam) {
        this.maCam = maCam;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public float getSoTienCam() {
        return soTienCam;
    }

    public void setSoTienCam(float soTienCam) {
        this.soTienCam = soTienCam;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public float getSoNgay() {
        return soNgay;
    }

    public void setSoNgay(float soNgay) {
        this.soNgay = soNgay;
    }

    public Date getNgayCam() {
        return ngayCam;
    }

    public void setNgayCam(Date ngayCam) {
        this.ngayCam = ngayCam;
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

   
}
