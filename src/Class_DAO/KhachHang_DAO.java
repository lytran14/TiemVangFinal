package Class_DAO;

import Class_DBHelder.DBHelder_SQL;
import Class_Model.KhachHang_Model;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KhachHang_DAO extends EduSysDAO<KhachHang_Model, String> {

    String INSERT_SQL = "insert into KHACHHANG(MAKH,TENKH,DIACHI,SODTKH,"
            + "EMAIL,SOCCCD,GHICHU) values (?,?,?,?,?,?,?)";
    String UPDATE_SQL = "update KHACHHANG set TENKH = ?, DIACHI = ?, SODTKH = ?, "
            + "EMAIL = ?, SOCCCD = ?, GHICHU = ? where MAKH = ?";
    String DELETE_SQL = "delete from KHACHHANG where MAKH = ?";
    String SELECT_ALL_SQL = "select * from KHACHHANG";
    String SELECT_BY_ID_SQL = "select * from KHACHHANG where MAKH = ?";
    String SELECT_BY_SDT_ID_SQL = "SELECT * FROM Khachhang WHERE makh LIKE ? OR SODTKH LIKE ?";

    @Override
    public void insert(KhachHang_Model entity) {
        DBHelder_SQL.update(INSERT_SQL, 
                entity.getMaKH(), 
                entity.getTenKH(), 
                entity.getDiaChi(), 
                entity.getSoDTKH(), 
                entity.getEmail(), 
                entity.getSoCCCD(), 
                entity.getGhiChu());
    }

    @Override
    public void update(KhachHang_Model entity) {
        DBHelder_SQL.update(UPDATE_SQL, 
                entity.getTenKH(), 
                entity.getDiaChi(), 
                entity.getSoDTKH(), 
                entity.getEmail(), 
                entity.getSoCCCD(), 
                entity.getGhiChu(), 
                entity.getMaKH());
    }

    @Override
    public void delete(String id) {
        DBHelder_SQL.update(DELETE_SQL, id);
    }
    
    @Override
    public List<KhachHang_Model> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public KhachHang_Model selectById(String id) {
        List<KhachHang_Model> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public KhachHang_Model selectBysdt(String id) {
        List<KhachHang_Model> list = selectBySql(SELECT_BY_SDT_ID_SQL, 
                "%" + id + "%", "%" + id + "%");
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhachHang_Model> selectBySql(String sql, Object... args) {
        List<KhachHang_Model> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelder_SQL.query(sql, args);
            while (rs.next()) {
                KhachHang_Model entity = new KhachHang_Model();
                entity.setMaKH(rs.getString("MAKH"));
                entity.setTenKH(rs.getString("TENKH"));
                entity.setDiaChi(rs.getString("DIACHI"));
                entity.setSoDTKH(rs.getString("SODTKH"));
                entity.setEmail(rs.getString("EMAIL"));
                entity.setSoCCCD(rs.getString("SOCCCD"));
                entity.setGhiChu(rs.getString("GHICHU"));

                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
     public List<KhachHang_Model> selectByKyword(String kyword) {
        String sql = "SELECT * FROM KHACHHANG WHERE MAKH like ? OR TENKH like ? or SDTKH like ?";
        return this.selectBySql(sql, "%" + kyword + "%", "%" + kyword + "%");
    }

}
