package Entity;

/**
 *
 * @author Z18KAI
 */
public class KhachHang {
    String maKhachHang;
    String hoTenKhachHang;
    String sdtKhachHang;
    String emailKhachHang;

    public KhachHang() {
    }

    @Override
    public String toString() {
        return this.maKhachHang + " - " + this.hoTenKhachHang;
    }

    
    public KhachHang(String maKhachHang, String hoTenKhachHang, String sdtKhachHang, String emailKhachHang) {
        this.maKhachHang = maKhachHang;
        this.hoTenKhachHang = hoTenKhachHang;
        this.sdtKhachHang = sdtKhachHang;
        this.emailKhachHang = emailKhachHang;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getHoTenKhachHang() {
        return hoTenKhachHang;
    }

    public void setHoTenKhachHang(String hoTenKhachHang) {
        this.hoTenKhachHang = hoTenKhachHang;
    }

    public String getSdtKhachHang() {
        return sdtKhachHang;
    }

    public void setSdtKhachHang(String sdtKhachHang) {
        this.sdtKhachHang = sdtKhachHang;
    }

    public String getEmailKhachHang() {
        return emailKhachHang;
    }

    public void setEmailKhachHang(String emailKhachHang) {
        this.emailKhachHang = emailKhachHang;
    }
    
    
}
