package Class_DAO;

import Class_DBHelder.DBHelder_SQL;
import Class_Model.DonViTinh_Model;
import Class_Model.LoaiSanPham_Model;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DonViTinh_DAO extends EduSysDAO<DonViTinh_Model, String> {

    String INSERT_SQL = "INSERT INTO DONVITINH(MADVTINH,TENDVT)VALUES (?,?)";
    String UPDATE_SQL = "UPDATE DONVITINH SET TENLOAISP=?,MOTA=? WHERE MADVTINH=?";
    String DELETE_SQL = "DELETE FROM DONVITINH WHERE MADVTINH = ?";
    String SELECT_ALL_SQL = "SELECT * FROM DONVITINH";
    String SELECT_BY_ID_SQL = "SELECT * FROM DONVITINH WHERE MADVTINH = ?";

    @Override
    public void insert(DonViTinh_Model entity) {
        DBHelder_SQL.update(INSERT_SQL,
                entity.getMaDVT(),
                entity.getTenDVT());

    }

    @Override
    public void update(DonViTinh_Model entity) {
        DBHelder_SQL.update(UPDATE_SQL,
                entity.getMaDVT(),
                entity.getTenDVT());
    }

    @Override
    public void delete(String id) {
        DBHelder_SQL.update(DELETE_SQL, id);

    }

    @Override
    public DonViTinh_Model selectById(String id) {
        List<DonViTinh_Model> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<DonViTinh_Model> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<DonViTinh_Model> selectBySql(String sql, Object... args) {
        List<DonViTinh_Model> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelder_SQL.query(sql, args);
            while (rs.next()) {
                DonViTinh_Model entity = new DonViTinh_Model();

                entity.setMaDVT(rs.getString("MADVTINH"));
                entity.setTenDVT(rs.getString("TENDVT"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
