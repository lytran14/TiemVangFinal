package Class_DAO;

import Class_DBHelder.DBHelder_SQL;
import Class_Model.NhanVien_Model;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhanVien_DAO extends EduSysDAO<NhanVien_Model, String> {

    String INSERT_SQL = "INSERT INTO NhanVien(MaNV,tennv,sodt,email,diachi,"
            + "vitricongviec,gioitinh,ngaysinh,matkhau)VALUES (?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE NhanVien SET tennv=?,sodt=?,email=?,diachi=?,"
            + "vitricongviec=?,gioitinh=?,ngaysinh=?,matkhau=? WHERE MaNV=?";
    String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNV = ?";
    String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE MaNV = ?";

    @Override
    public void insert(NhanVien_Model entity) {
        DBHelder_SQL.update(INSERT_SQL,
                entity.getMANV(),
                entity.getTENNV(),
                entity.getSODT(),
                entity.getEMAIL(),
                entity.getDIACHI(),
                entity.isVITRICONGVIEC(),
                entity.isGIOITINH(),
                entity.getNGAYSINH(),
                entity.getMATKHAU());
    }

    @Override
    public void update(NhanVien_Model entity) {
        DBHelder_SQL.update(UPDATE_SQL,
                entity.getTENNV(),
                entity.getSODT(),
                entity.getEMAIL(),
                entity.getDIACHI(),
                entity.isVITRICONGVIEC(),
                entity.isGIOITINH(),
                entity.getNGAYSINH(),
                entity.getMATKHAU(),
                entity.getMANV());
    }

    @Override
    public void delete(String id) {
        DBHelder_SQL.update(DELETE_SQL, id);

    }

    @Override
    public NhanVien_Model selectById(String id) {
        List<NhanVien_Model> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NhanVien_Model> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<NhanVien_Model> selectBySql(String sql, Object... args) {
        List<NhanVien_Model> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelder_SQL.query(sql, args);
            while (rs.next()) {
                NhanVien_Model entity = new NhanVien_Model();
                entity.setMANV(rs.getString("MaNV"));
                entity.setTENNV(rs.getString("tennv"));
                entity.setSODT(rs.getString("sodt"));
                entity.setEMAIL(rs.getString("email"));
                entity.setDIACHI(rs.getString("diachi"));
                entity.setVITRICONGVIEC(rs.getBoolean("vitricongviec"));
                entity.setGIOITINH(rs.getBoolean("gioitinh"));
                entity.setNGAYSINH(rs.getDate("ngaysinh"));
                 entity.setMATKHAU(rs.getString("matkhau"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
