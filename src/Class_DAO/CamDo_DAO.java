
package Class_DAO;

import Class_DBHelder.DBHelder_SQL;
import Class_Model.CamDo_Model;
import Class_Model.KhachHang_Model;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class CamDo_DAO extends EduSysDAO<CamDo_Model, String>{
    
    String INSERT_SQL = "INSERT INTO CAMDO(MACAM,NGAYLAP,SOTIENCAM,MAKH,MANV,SONGAY,NGAYCAM,NGAYHETHAN,TRANGTHAI)VALUES (?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE CAMDO SET NGAYLAP = ?, SOTIENCAM = ?, MAKH = ?, MANV =?, SONGAY =?, NGAYCAM =?, NGAYHETHAN =?, TRANGTHAI =? WHERE MACAM=?";
    String DELETE_SQL = "DELETE FROM CAMDO WHERE MACAM = ?";
    String SELECT_ALL_SQL = "SELECT * FROM CAMDO";
    String SELECT_BY_ID_SQL = "SELECT * FROM CAMDO WHERE MACAM = ?";

    @Override
    public void insert(CamDo_Model entity) {
        DBHelder_SQL.update(INSERT_SQL, 
                entity.getMaCam(), 
                entity.getNgayLap(), 
                entity.getSoTienCam(), 
                entity.getMaKH(),
                entity.getMaNV(),
                entity.getSoNgay(),
                entity.getNgayCam(),
                entity.getNgayHetHan(),
                entity.isTrangThai());

    }

    @Override
    public void update(CamDo_Model entity) {
        DBHelder_SQL.update(UPDATE_SQL, 
               entity.getNgayLap(), 
                entity.getSoTienCam(), 
                entity.getMaKH(),
                entity.getMaNV(),
                entity.getSoNgay(),
                entity.getNgayCam(),
                entity.getNgayHetHan(),
                entity.isTrangThai(), 
                entity.getMaCam());
    }

    @Override
    public void delete(String id) {
        DBHelder_SQL.update(DELETE_SQL, id);
    }

    @Override
    public CamDo_Model selectById(String id) {
        List<CamDo_Model> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<CamDo_Model> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<CamDo_Model> selectBySql(String sql, Object... args) {
        List<CamDo_Model> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelder_SQL.query(sql, args);
            while (rs.next()) {
                CamDo_Model entity = new CamDo_Model();
                entity.setMaCam(rs.getString("MACAM"));
                entity.setNgayLap(rs.getDate("NGAYLAP"));
                entity.setSoTienCam(rs.getFloat("SOTIENCAM"));
                entity.setMaKH(rs.getString("MAKH"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setSoNgay(rs.getFloat("SONGAY"));
                entity.setNgayCam(rs.getDate("NGAYCAM"));
                entity.setNgayHetHan(rs.getDate("NGAYHETHAN"));
                entity.setTrangThai(rs.getBoolean("TRANGTHAI"));

                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
     public List<CamDo_Model> selectByKyword(String kyword) {
        String sql = "select * from CAMDO where MACAM like ?";
        return this.selectBySql(sql, "%" + kyword + "%");
    }
}
