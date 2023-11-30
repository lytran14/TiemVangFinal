
package Class_Model;


public class ChiTietPhieuCam_Model {
    
    private String maPhieu;
    private String maCam;
    private float  soTienCam;
    private float  khoiLuong;
    private float  donGia;
    private String maSP;
    private String tenSP;
    private float  laiXuat;

    public ChiTietPhieuCam_Model() {
    }

    public ChiTietPhieuCam_Model(String maPhieu, String maCam, float soTienCam, float khoiLuong, float donGia, String maSP, String tenSP, float laiXuat) {
        this.maPhieu = maPhieu;
        this.maCam = maCam;
        this.soTienCam = soTienCam;
        this.khoiLuong = khoiLuong;
        this.donGia = donGia;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.laiXuat = laiXuat;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public String getMaCam() {
        return maCam;
    }

    public void setMaCam(String maCam) {
        this.maCam = maCam;
    }

    public float getSoTienCam() {
        return soTienCam;
    }

    public void setSoTienCam(float soTienCam) {
        this.soTienCam = soTienCam;
    }

    public float getKhoiLuong() {
        return khoiLuong;
    }

    public void setKhoiLuong(float khoiLuong) {
        this.khoiLuong = khoiLuong;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public float getLaiXuat() {
        return laiXuat;
    }

    public void setLaiXuat(float laiXuat) {
        this.laiXuat = laiXuat;
    }

   
}
