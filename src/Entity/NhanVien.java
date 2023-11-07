package Entity;

/**
 *
 * @author Z18KAI
 */
public class NhanVien {
    String maNhanVien;
    String hoTenNhanVien;
    String sdtNhanVien;
    String emailNhanVien;
    int vaiTroNhanVien;
    String matKhauNhanVien;
    String TrangThai;

    @Override
    public String toString() {
        return this.maNhanVien + " - " + this.hoTenNhanVien;
    }

    
    
    public NhanVien() {
    }

    public NhanVien(String maNhanVien, String hoTenNhanVien, String sdtNhanVien, String emailNhanVien, int vaiTroNhanVien, String matKhauNhanVien, String TrangThai) {
        this.maNhanVien = maNhanVien;
        this.hoTenNhanVien = hoTenNhanVien;
        this.sdtNhanVien = sdtNhanVien;
        this.emailNhanVien = emailNhanVien;
        this.vaiTroNhanVien = vaiTroNhanVien;
        this.matKhauNhanVien = matKhauNhanVien;
        this.TrangThai = TrangThai;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHoTenNhanVien() {
        return hoTenNhanVien;
    }

    public void setHoTenNhanVien(String hoTenNhanVien) {
        this.hoTenNhanVien = hoTenNhanVien;
    }

    public String getSdtNhanVien() {
        return sdtNhanVien;
    }

    public void setSdtNhanVien(String sdtNhanVien) {
        this.sdtNhanVien = sdtNhanVien;
    }

    public String getEmailNhanVien() {
        return emailNhanVien;
    }

    public void setEmailNhanVien(String emailNhanVien) {
        this.emailNhanVien = emailNhanVien;
    }

    public int getVaiTroNhanVien() {
        return vaiTroNhanVien;
    }

    public void setVaiTroNhanVien(int vaiTroNhanVien) {
        this.vaiTroNhanVien = vaiTroNhanVien;
    }

    public String getMatKhauNhanVien() {
        return matKhauNhanVien;
    }

    public void setMatKhauNhanVien(String matKhauNhanVien) {
        this.matKhauNhanVien = matKhauNhanVien;
    }
    
    
}
