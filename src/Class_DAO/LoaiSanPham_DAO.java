
package Class_DAO;

import Class_DBHelder.DBHelder_SQL;
import Class_Model.LoaiSanPham_Model;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoaiSanPham_DAO extends EduSysDAO<LoaiSanPham_Model, String>{
    
    String INSERT_SQL = "INSERT INTO LOAISANPHAM(MALOAISP,TENLOAISP,MOTA)VALUES (?,?,?)";
    String UPDATE_SQL = "UPDATE LOAISANPHAM SET TENLOAISP=?,MOTA=? WHERE MALOAISP=?";
    String DELETE_SQL = "DELETE FROM LOAISANPHAM WHERE MALOAISP = ?";
    String SELECT_ALL_SQL = "SELECT * FROM LOAISANPHAM";
    String SELECT_BY_ID_SQL = "SELECT * FROM LOAISANPHAM WHERE MALOAISP = ?";

    @Override
    public void insert(LoaiSanPham_Model entity) {
        DBHelder_SQL.update(INSERT_SQL,
                entity.getMaLSP(),
                entity.getTenLSP(),
                entity.getMoTa());
        
    }

    @Override
    public void update(LoaiSanPham_Model entity) {
        DBHelder_SQL.update(UPDATE_SQL,
                entity.getTenLSP(),
                entity.getMoTa(),
                entity.getMaLSP());
    }

    @Override
    public void delete(String id) {
        DBHelder_SQL.update(DELETE_SQL, id);

    }

    @Override
    public LoaiSanPham_Model selectById(String id) {
        List<LoaiSanPham_Model> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<LoaiSanPham_Model> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<LoaiSanPham_Model> selectBySql(String sql, Object... args) {
        List<LoaiSanPham_Model> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelder_SQL.query(sql, args);
            while (rs.next()) {
                LoaiSanPham_Model entity = new LoaiSanPham_Model();
                
                entity.setMaLSP(rs.getString("MALOAISP"));
                entity.setTenLSP(rs.getString("TENLOAISP"));
                entity.setMoTa(rs.getString("MOTA"));
               
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
