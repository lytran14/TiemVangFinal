
package Class_Model;


public class KhachHang_Model {
    
    private String maKH;
    private String tenKH;
    private String diaChi;
    private String soDTKH;
    private String email;
    private String soCCCD;
    private String ghiChu;

    public KhachHang_Model() {
    }

    public KhachHang_Model(String maKH, String tenKH, String diaChi, String soDTKH, String email, String soCCCD, String ghiChu) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.soDTKH = soDTKH;
        this.email = email;
        this.soCCCD = soCCCD;
        this.ghiChu = ghiChu;
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDTKH() {
        return soDTKH;
    }

    public void setSoDTKH(String soDTKH) {
        this.soDTKH = soDTKH;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoCCCD() {
        return soCCCD;
    }

    public void setSoCCCD(String soCCCD) {
        this.soCCCD = soCCCD;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    
}
