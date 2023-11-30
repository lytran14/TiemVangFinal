
package Class_DAO;

import Class_DBHelder.DBHelder_SQL;
import Class_Model.ChiTietPhieuCam_Model;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ChiTietPhieuCam_DAO extends EduSysDAO<ChiTietPhieuCam_Model, String>{
    
    String INSERT_SQL = "INSERT INTO PHIEUCAMDO(MACAM,SOTIENCAM,KHOILUONG,DONGIA,MASP,LAIXUAT)VALUES (?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE PHIEUCAMDO SET MACAM = ?, SOTIENCAM = ?, KHOILUONG = ?, DONGIA =?, MASP =?, LAIXUAT =? WHERE MAPHIEU=?";
    String DELETE_SQL = "DELETE FROM PHIEUCAMDO WHERE PHIEUMACAM = ?";
    String SELECT_ALL_SQL = "SELECT * FROM PHIEUCAMDO";
    String SELECT_BY_ID_SQL = "SELECT * FROM PHIEUCAMDO WHERE PHIEUMACAM = ?";

    @Override
    public void insert(ChiTietPhieuCam_Model entity) {
        DBHelder_SQL.update(INSERT_SQL,  
                entity.getMaCam(), 
                entity.getSoTienCam(), 
                entity.getKhoiLuong(),
                entity.getDonGia(),
                entity.getMaSP(),
                entity.getLaiXuat());             
    }

    @Override
    public void update(ChiTietPhieuCam_Model entity) {
        DBHelder_SQL.update(UPDATE_SQL, 
               entity.getMaCam(), 
                entity.getSoTienCam(), 
                entity.getKhoiLuong(),
                entity.getDonGia(),
                entity.getMaSP(),
                entity.getLaiXuat(),          
                entity.getMaPhieu());
    }

    @Override
    public void delete(String id) {
        DBHelder_SQL.update(DELETE_SQL, id);
    }

    @Override
    public ChiTietPhieuCam_Model selectById(String id) {
        List<ChiTietPhieuCam_Model> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ChiTietPhieuCam_Model> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<ChiTietPhieuCam_Model> selectBySql(String sql, Object... args) {
        List<ChiTietPhieuCam_Model> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelder_SQL.query(sql, args);
            while (rs.next()) {
                ChiTietPhieuCam_Model entity = new ChiTietPhieuCam_Model();
                entity.setMaPhieu(rs.getString("MAPHIEU"));
                entity.setMaCam(rs.getString("MACAM"));
                entity.setSoTienCam(rs.getFloat("SOTIENCAM"));
                entity.setKhoiLuong(rs.getFloat("KHOILUONG"));
                entity.setDonGia(rs.getFloat("DONGIA"));
                entity.setMaSP(rs.getString("MASP"));
                entity.setLaiXuat(rs.getFloat("LAIXUAT"));

                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
