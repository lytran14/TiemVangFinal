package Class_DAO;

import Class_DBHelder.DBHelder_SQL;
import Class_Model.BanRa_Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BanRa_DAO extends EduSysDAO<BanRa_Model, String> {

    String INSERT_SQL = "insert into BANRA(MABR,MAKH,MANV,NGAYLAP,TONGGIATRI) "
            + "values (?,?,?,?,?)";
    String UPDATE_SQL = "update BANRA set MAKH = ?, MANV = ?, NGAYLAP = ?, "
            + "TONGGIATRI = ? where MABR = ?";
    String DELETE_SQL = "delete from BANRA where MABR = ?";
    String SELECT_ALL_SQL = "select * from BANRA";
    String SELECT_BY_ID_SQL = "select * from BANRA where MABR = ?";

    @Override
    public void insert(BanRa_Model entity) {
        DBHelder_SQL.update(INSERT_SQL,
                entity.getMABR(),
                entity.getMAKH(),
                entity.getMANV(),
                entity.getNGAYLAP(),
                entity.getTONGGIATRI());
    }

    @Override
    public void update(BanRa_Model entity) {
        DBHelder_SQL.update(UPDATE_SQL,
                entity.getMAKH(),
                entity.getMANV(),
                entity.getNGAYLAP(),
                entity.getTONGGIATRI(),
                entity.getMABR());
    }

    @Override
    public void delete(String id) {
        DBHelder_SQL.update(DELETE_SQL, id);
    }

    @Override
    public BanRa_Model selectById(String id) {
        List<BanRa_Model> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);

    }

    @Override
    public List<BanRa_Model> selectAll() {
        return selectBySql(SELECT_ALL_SQL);

    }

    @Override
    protected List<BanRa_Model> selectBySql(String sql, Object... args) {
        List<BanRa_Model> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelder_SQL.query(sql, args);
            while (rs.next()) {
                BanRa_Model entity = new BanRa_Model();
                entity.setMABR(rs.getString("MABR"));
                entity.setMAKH(rs.getString("MAKH"));
                entity.setMANV(rs.getString("MANV"));
                entity.setNGAYLAP(rs.getDate("NGAYLAP"));
                entity.setTONGGIATRI(rs.getDouble("TONGGIATRI"));

                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    protected List<BanRa_Model> selectBySqlHoaDon(String sql, Object... args) {
        List<BanRa_Model> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelder_SQL.query(sql, args);
            while (rs.next()) {
                BanRa_Model entity = new BanRa_Model();
                entity.setMABR(rs.getString("MABR"));
                entity.setTenKH(rs.getString("TENKH"));
                entity.setMANV(rs.getString("MANV"));
                entity.setNGAYLAP(rs.getDate("NGAYLAP"));
                entity.setTONGGIATRI(rs.getDouble("TONGGIATRI"));

                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<BanRa_Model> selectByKyword(String kyword) {
        String sql = "select br.MABR, kh.TENKH, br.MANV, br.NGAYLAP, "
                + "br.TONGGIATRI from BANRA br inner join KHACHHANG kh\n"
                + "on kh.MAKH = br.MAKH WHERE br.mabr like ? or kh.TENKH like ?";
        return this.selectBySqlHoaDon(sql, "%" + kyword + "%", "%" + kyword + "%");
    }

    public List<BanRa_Model> getOrdersByDateRange(Date startDate, Date endDate) {
        String sql = "select br.MABR, kh.TENKH, br.MANV, br.NGAYLAP, "
                + "br.TONGGIATRI from BANRA br inner join KHACHHANG kh\n"
                + "on kh.MAKH = br.MAKH WHERE NGAYLAP >=? AND NGAYLAP <=? ORDER BY NGAYLAP ASC";
        return selectBySqlHoaDon(sql, startDate, endDate);
    }

    public List<Integer> selectYear() {
        String sql = "select distinct year(Ngaylap) Year from BANRA order by Year asc";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelder_SQL.query(sql);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
