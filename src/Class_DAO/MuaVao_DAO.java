package Class_DAO;

import Class_DBHelder.DBHelder_SQL;
import Class_Model.MuaVao_Model;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MuaVao_DAO extends EduSysDAO<MuaVao_Model, String> {

    String INSERT_SQL = "INSERT INTO MUAVAO(MaMV,MAKH,MANV,NGAYLAP,TONGGIATRI)VALUES (?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE MUAVAO SET MAKH = ?, MANV = ?, NGAYLAP = ?, TONGGIATRI =?, WHERE MaMV=?";
    String DELETE_SQL = "DELETE FROM MUAVAO WHERE MaMV = ?";
    String SELECT_ALL_SQL = "SELECT * FROM MUAVAO";
    String SELECT_BY_ID_SQL = "SELECT * FROM MUAVAO WHERE MaMV = ?";

    @Override
    public void insert(MuaVao_Model entity) {
        DBHelder_SQL.update(INSERT_SQL,
                entity.getMaMV(),
                entity.getMaKH(),
                entity.getMaNV(),
                entity.getNgayLap(),
                entity.getTongGiaTri());

    }

    @Override
    public void update(MuaVao_Model entity) {
        DBHelder_SQL.update(UPDATE_SQL,
                entity.getMaKH(),
                entity.getMaNV(),
                entity.getNgayLap(),
                entity.getTongGiaTri(),
                entity.getMaMV());
    }

    @Override
    public void delete(String id) {
        DBHelder_SQL.update(DELETE_SQL, id);
    }

    @Override
    public MuaVao_Model selectById(String id) {
        List<MuaVao_Model> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<MuaVao_Model> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<MuaVao_Model> selectBySql(String sql, Object... args) {
        List<MuaVao_Model> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelder_SQL.query(sql, args);
            while (rs.next()) {
                MuaVao_Model entity = new MuaVao_Model();
                entity.setMaMV(rs.getString("MaMV"));
                entity.setMaKH(rs.getString("MaKH"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgayLap(rs.getDate("NgayLap"));
                entity.setTongGiaTri(rs.getDouble("TongGiaTri"));

                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    protected List<MuaVao_Model> selectBySqlHoaDon(String sql, Object... args) {
        List<MuaVao_Model> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelder_SQL.query(sql, args);
            while (rs.next()) {
                MuaVao_Model entity = new MuaVao_Model();
                entity.setMaMV(rs.getString("MAMV"));
                entity.setTenkh(rs.getString("TENKH"));
                entity.setMaNV(rs.getString("MANV"));
                entity.setNgayLap(rs.getDate("NGAYLAP"));
                entity.setTongGiaTri(rs.getDouble("TONGGIATRI"));

                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<MuaVao_Model> selectByKyword(String kyword) {
        String sql = "select br.MAMV, kh.TENKH, br.MANV, br.NGAYLAP, br.TONGGIATRI from MUAVAO br inner join KHACHHANG kh\n"
                + "on kh.MAKH = br.MAKH WHERE br.MAMV =? or kh.TENKH like ?";
        return this.selectBySqlHoaDon(sql, "%" + kyword + "%", "%" + kyword + "%");
    }

    public List<MuaVao_Model> getOrdersByDateRange(Date startDate, Date endDate) {
        String sql = "select br.MAMV, kh.TENKH, br.MANV, br.NGAYLAP, br.TONGGIATRI from MUAVAO br inner join KHACHHANG kh\n"
                + "on kh.MAKH = br.MAKH WHERE NGAYLAP >=? AND NGAYLAP <=? ORDER BY NGAYLAP ASC";
        return selectBySqlHoaDon(sql, startDate, endDate);
    }
}
