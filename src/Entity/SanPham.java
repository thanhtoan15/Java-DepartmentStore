package Entity;

/**
 *
 * @author Z18KAI
 */
public class SanPham {
    String maSanPham;
    String maLoaiHang;
    String tenSanPham;
    float giaBan;
    String hinhSanPham;
    String maNhaCungCap;
    int soLuong;
    String donVi;

    public SanPham() {
    }

    public SanPham(String maSanPham, String maLoaiHang, String tenSanPham, float giaBan, String hinhSanPham, String maNhaCungCap, int soLuong, String donVi) {
        this.maSanPham = maSanPham;
        this.maLoaiHang = maLoaiHang;
        this.tenSanPham = tenSanPham;
        this.giaBan = giaBan;
        this.hinhSanPham = hinhSanPham;
        this.maNhaCungCap = maNhaCungCap;
        this.soLuong = soLuong;
        this.donVi = donVi;
    }

    @Override
    public String toString() {
        return this.maSanPham + " - " + this.tenSanPham;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getMaLoaiHang() {
        return maLoaiHang;
    }

    public void setMaLoaiHang(String maLoaiHang) {
        this.maLoaiHang = maLoaiHang;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

    public String getHinhSanPham() {
        return hinhSanPham;
    }

    public void setHinhSanPham(String hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
    }

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }
    
    
}
