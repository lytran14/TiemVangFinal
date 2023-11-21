
package Class_DAO;

import Class_DBHelder.DBHelder_SQL;
import Class_Model.SanPham_Model;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SanPham_DAO extends EduSysDAO<SanPham_Model, String> {

    String INSERT_SQL = "insert into SANPHAM(MASP,TENSP,MOTA,DONGIANHAP,LOAI,MALOAISP,DONGIABAN,DONVITINH) values (?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "update SANPHAM set TENSP = ?, MOTA = ?, DONGIANHAP = ?, LOAI = ?, MALOAISP = ?, DONGIABAN = ? ,DONVITINH=? where MASP = ?";
    String DELETE_SQL = "delete from SANPHAM where MASP = ?";
    String SELECT_ALL_SQL = "select * from SANPHAM";
    String SELECT_BY_ID_SQL = "select * from SANPHAM where MASP = ?";

    @Override
    public void insert(SanPham_Model entity) {
        DBHelder_SQL.update(INSERT_SQL,
                entity.getMASP(),
                entity.getTENSP(),
                entity.getMOTA(),
                entity.getDONGIANHAP(),
                entity.getLOAI(),
                entity.getMALOAISP(),
                entity.getDONGIABAN(),
                entity.getDONVITINH());
    }

    @Override
    public void update(SanPham_Model entity) {
        DBHelder_SQL.update(UPDATE_SQL,
                entity.getTENSP(),
                entity.getMOTA(),
                entity.getDONGIANHAP(),
                entity.getLOAI(),
                entity.getMALOAISP(),
                entity.getDONGIABAN(),
                entity.getDONVITINH(),
                entity.getMASP()
        );
    }

    @Override
    public void delete(String id) {
        DBHelder_SQL.update(DELETE_SQL, id);
    }

    @Override
    public List<SanPham_Model> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public SanPham_Model selectById(String id) {
        List<SanPham_Model> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<SanPham_Model> selectBySql(String sql, Object... args) {
        List<SanPham_Model> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelder_SQL.query(sql, args);
            while (rs.next()) {
                SanPham_Model entity = new SanPham_Model();
                entity.setMASP(rs.getString("MASP"));
                entity.setTENSP(rs.getString("TENSP"));
                entity.setMOTA(rs.getString("MOTA"));
                entity.setDONGIANHAP(rs.getDouble("DONGIANHAP"));
                entity.setLOAI(rs.getString("LOAI"));
                entity.setMALOAISP(rs.getString("MALOAISP"));
                entity.setDONGIABAN(rs.getDouble("DONGIABAN"));
                entity.setDONVITINH(rs.getString("DONVITINH"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
