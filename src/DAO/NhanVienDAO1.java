package DAO;

import Entity.NhanVien;
import Helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Z18KAI
 */
public class NhanVienDAO1 {

    public void insert(NhanVien model) {
        String sql = "INSERT INTO NhanVien (MaNV, HoTen, SDT, Email, VaiTro, MatKhau, trangthai) VALUES (UPPER(?), ?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaNhanVien(),
                model.getHoTenNhanVien(),
                model.getSdtNhanVien(),
                model.getEmailNhanVien(),
                model.getVaiTroNhanVien(),
                model.getMatKhauNhanVien(),
                "Đi Làm"
        );
    }

    public void update(NhanVien model) {
        String sql = "UPDATE NhanVien SET HoTen=?, SDT=?, Email=?, VaiTro=? WHERE MaNV=?";
        JdbcHelper.executeUpdate(sql,
                model.getHoTenNhanVien(),
                model.getSdtNhanVien(),
                model.getEmailNhanVien(),
                model.getVaiTroNhanVien(),
//                model.getMatKhauNhanVien(),
                model.getMaNhanVien());
    }

    public void updatemk(String Mk, String manv) {
        String sql = "UPDATE NhanVien SET MatKhau = ? WHERE MaNV = ?";
        JdbcHelper.executeUpdate(sql, Mk, manv);
    }

    public void update(String MaNV) {
        String sql = "UPDATE NhanVien SET trangthai = N'Đã Nghỉ' WHERE MaNV = ?";
        JdbcHelper.executeUpdate(sql, MaNV);
    }

    public void delete(String MaKH) {
        String sql = "DELETE FROM NhanVien WHERE MaNV=?";
        JdbcHelper.executeUpdate(sql, MaKH);
    }

    public List<NhanVien> select() {
        String sql = "SELECT * FROM NhanVien \n"
                + "ORDER BY trangthai desc";
        return select(sql);
    }

    public NhanVien findById(String mamv) {
        String sql = "SELECT * FROM NhanVien WHERE MaNV=?";
        List<NhanVien> list = select(sql, mamv);
        return list.size() > 0 ? list.get(0) : null;
    }

    public NhanVien findByTT(String tt) {
        String sql = "SELECT * FROM NhanVien WHERE TrangThai=?";
        List<NhanVien> list = select(sql, tt);
        return list.size() > 0 ? list.get(0) : null;
    }

    public NhanVien findByIDanhMK(String makh, String mk) {
        String sql = "SELECT * FROM NhanVien WHERE MaNV=? and MatKhau = ?";
        List<NhanVien> list = select(sql, makh, mk);
        return list.size() > 0 ? list.get(0) : null;
    }

    public NhanVien findByName(String namekh) {
        String sql = "SELECT * FROM NhanVien WHERE HoTen like N'%'+?+'%'";
        List<NhanVien> list = select(sql, namekh);
        return list.size() > 0 ? list.get(0) : null;
    }

    public NhanVien findByEmail(String email) {
        String sql = "SELECT * FROM NhanVien WHERE email = ?";
        List<NhanVien> list = select(sql, email);
        return list.size() > 0 ? list.get(0) : null;
    }

    public void update_khoiPhuc(String maNV) {
        String sql = "update NhanVien set TrangThai = N'Đi Làm' where MaNV = ?";
        JdbcHelper.executeUpdate(sql, maNV
        );
    }

    private List<NhanVien> select(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    NhanVien model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private NhanVien readFromResultSet(ResultSet rs) throws SQLException {
        NhanVien model = new NhanVien();
        model.setMaNhanVien(rs.getString("MaNV"));
        model.setHoTenNhanVien(rs.getString("HoTen"));
        model.setSdtNhanVien(rs.getString("SDT"));
        model.setEmailNhanVien(rs.getString("Email"));
        model.setVaiTroNhanVien(rs.getInt("VaiTro"));
        model.setMatKhauNhanVien(rs.getString("MatKhau"));
        model.setTrangThai(rs.getString("trangthai"));
        return model;
    }
    public String getcol(String sql,String cols , Object... args){
          try {
             String a = "0";
             ResultSet rs = JdbcHelper.executeQuery(sql, args);
             while(rs.next()){
                 a = rs.getString(cols);
                 if(a== null){
                     a = "0";
                 }
             }
             return a;
          } catch (Exception e) {
              e.printStackTrace();
          }
          return null;
      }
        public String check_nv(String manv) {
        String sql = "{call check_nv(?)}";
        String cols = "manv";
        return this.getcol(sql, cols, manv);
    }
}
