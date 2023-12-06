package Class_Model;

public class DonViTinh_Model {

    String maDVT;
    String tenDVT;

    @Override
    public String toString() {
        return this.tenDVT;
    }

    public DonViTinh_Model() {
    }

    public DonViTinh_Model(String maDVT, String tenDVT) {
        this.maDVT = maDVT;
        this.tenDVT = tenDVT;
    }

    public String getMaDVT() {
        return maDVT;
    }

    public void setMaDVT(String maDVT) {
        this.maDVT = maDVT;
    }

    public String getTenDVT() {
        return tenDVT;
    }

    public void setTenDVT(String tenDVT) {
        this.tenDVT = tenDVT;
    }

}
