package Class_DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import Class_DBHelder.DBHelder_SQL;
import Class_Model.BanRaChiTiet_Model;
import java.util.List;

public class BanRaChiTiet_DAO extends EduSysDAO<BanRaChiTiet_Model, String> {

    String INSERT_SQL = "insert into CHITIETBR(MABR,MASP,KHOILUONG,DONGIABAN,THANHTIEN) values (?,?,?,?,?)";
    String UPDATE_SQL = "update CHITIETBR set MABR=?, MASP = ?, KHOILUONG = ?, DONGIABAN = ?, THANHTIEN = ? where MACTBR = ?";
    String DELETE_SQL = "delete from CHITIETBR where masp = ?";
    String SELECT_ALL_SQL = "select * from CHITIETBR";
    String SELECT_BY_ID_SQL = "select * from CHITIETBR where MACTBR = ?";
    //String sql = "SELECT COUNT(*) FROM CHITIETBR WHERE MABR = ?";

    @Override
    public void insert(BanRaChiTiet_Model entity) {
        DBHelder_SQL.update(INSERT_SQL,
                entity.getMABR(),
                entity.getMASP(),
                entity.getKHOILUONG(),
                entity.getDONGIABAN(),
                entity.getTHANHTIEN());
    }

    @Override
    public void update(BanRaChiTiet_Model entity) {
        DBHelder_SQL.update(UPDATE_SQL,
                entity.getMABR(),
                entity.getMASP(),
                entity.getKHOILUONG(),
                entity.getDONGIABAN(),
                entity.getTHANHTIEN(),
                entity.getMACTBR());
    }

    @Override
    public void delete(String id) {
        DBHelder_SQL.update(DELETE_SQL, id);
    }

    @Override
    public BanRaChiTiet_Model selectById(String id) {
        List<BanRaChiTiet_Model> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<BanRaChiTiet_Model> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<BanRaChiTiet_Model> selectBySql(String sql, Object... args) {
        List<BanRaChiTiet_Model> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelder_SQL.query(sql, args);
            while (rs.next()) {
                BanRaChiTiet_Model entity = new BanRaChiTiet_Model();
                
                entity.setMASP(rs.getString("MASP"));
                entity.setTENSP(rs.getString("TENSP"));
                entity.setLOAISP(rs.getString("LOAI"));
                entity.setKHOILUONG(rs.getDouble("KHOILUONG"));
                entity.setDONGIABAN(rs.getDouble("DONGIABAN"));
                entity.setTHANHTIEN(rs.getDouble("THANHTIEN"));
                entity.setMACTBR(rs.getString("MACTBR"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<BanRaChiTiet_Model> selectsp_br(String maBR) {
        String SELECT_HOADON_SANPHAM = "SELECT SP.MASP, SP.TENSP, SP.LOAI, CTBR.KHOILUONG, CTBR.DONGIABAN, CTBR.THANHTIEN,CTBR.MACTBR FROM \n"
                + "SANPHAM SP\n"
                + "INNER JOIN CHITIETBR CTBR ON SP.MASP = CTBR.MASP\n"
                + "INNER JOIN BANRA BR ON BR.MABR = CTBR.MABR\n"
                + "WHERE BR.MABR = ?";
        return selectBySql(SELECT_HOADON_SANPHAM, maBR);
    }

   public List<BanRaChiTiet_Model> hasHoaDonChiTiet(String maHoaDon) {
   // boolean exists = false;
    String sql = "SELECT COUNT(*) FROM CHITIETBR WHERE MABR = ?";
    return selectBySql(sql, maHoaDon);
}
}
