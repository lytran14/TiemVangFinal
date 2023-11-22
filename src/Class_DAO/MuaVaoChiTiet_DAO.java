
package Class_DAO;
import java.sql.ResultSet;
import java.util.ArrayList;
import Class_DBHelder.DBHelder_SQL;
import Class_Model.MuaVaoChiTiet_Model;
import java.util.List;
public class MuaVaoChiTiet_DAO extends EduSysDAO<MuaVaoChiTiet_Model, String>{
    String INSERT_SQL = "insert into CHITIETMUAVAO(MAMV,MASP,KHOILUONG,DONGIAMUA,THANHTIEN) values (?,?,?,?,?)";
    String UPDATE_SQL = "update CHITIETMUAVAO set MAMV=?, MASP = ?, KHOILUONG = ?, DONGIAMUA = ?, THANHTIEN = ? where MACTMV = ?";
    String DELETE_SQL = "delete from CHITIETMUAVAO where masp = ?";
    String SELECT_ALL_SQL = "select * from CHITIETMUAVAO";
    String SELECT_BY_ID_SQL = "select * from CHITIETMUAVAO where MACTMV = ?";
    
     @Override
    public void insert(MuaVaoChiTiet_Model entity) {
        DBHelder_SQL.update(INSERT_SQL,
                entity.getMAMV(),
                entity.getMASP(),
                entity.getKHOILUONG(),
                entity.getDONGIAMUA(),
                entity.getTHANHTIEN());
    }

    @Override
    public void update(MuaVaoChiTiet_Model entity) {
        DBHelder_SQL.update(UPDATE_SQL,
                entity.getMAMV(),
                entity.getMASP(),
                entity.getKHOILUONG(),
                entity.getDONGIAMUA(),
                entity.getTHANHTIEN(),
                entity.getMACTMV());
    }

    @Override
    public void delete(String id) {
        DBHelder_SQL.update(DELETE_SQL, id);
    }

    @Override
    public MuaVaoChiTiet_Model selectById(String id) {
        List<MuaVaoChiTiet_Model> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<MuaVaoChiTiet_Model> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<MuaVaoChiTiet_Model> selectBySql(String sql, Object... args) {
        List<MuaVaoChiTiet_Model> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelder_SQL.query(sql, args);
            while (rs.next()) {
                MuaVaoChiTiet_Model entity = new MuaVaoChiTiet_Model();
                
                entity.setMASP(rs.getString("MASP"));
               // entity.setMASP(rs.getString("TENSP"));
                entity.setLOAISP(rs.getString("LOAI"));
                entity.setKHOILUONG(rs.getDouble("KHOILUONG"));
                entity.setDONGIAMUA(rs.getDouble("DONGIAMUA"));
                entity.setTHANHTIEN(rs.getDouble("THANHTIEN"));
                entity.setMACTMV(rs.getString("MACTMV"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<MuaVaoChiTiet_Model> selectsp_br(String maMV) {
        String SELECT_HOADON_SANPHAM = "SELECT SP.MASP, SP.TENSP, SP.LOAI, CTMV.KHOILUONG, CTMV.DONGIAMUA, CTMV.THANHTIEN,CTMV.MACTMV FROM \n"
                + "SANPHAM SP\n"
                + "INNER JOIN CHITIETMUAVAO CTMV ON SP.MASP = CTMV.MASP\n"
                + "INNER JOIN MUAVAO MV ON MV.MAMV = CTMV.MAMV\n"
                + "WHERE MV.MAMV = ?";
        return selectBySql(SELECT_HOADON_SANPHAM, maMV);
    }

   public List<MuaVaoChiTiet_Model> hasHoaDonChiTiet(String maHoaDon) {
   // boolean exists = false;
    String sql = "SELECT COUNT(*) FROM CHITIETMUAVAO WHERE MAMV = ?";
    return selectBySql(sql, maHoaDon);
}
}
