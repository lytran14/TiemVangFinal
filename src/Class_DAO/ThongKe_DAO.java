package Class_DAO;

import Class_DBHelder.DBHelder_SQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThongKe_DAO {
    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = DBHelder_SQL.query(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Object[]> getDThu(Integer year) {
        String sql = "EXEC sp_ThongKeNam ?";
        String[] cols = {"skh", "soBR", "soMV", "TongThu", "TongChi", "DoanhThu"};
        return getListOfArray(sql, cols, year);
    }
    
    public List<Object[]> getDThuFromDateToDate(Date stDate, Date enDate) {
        String sql = "EXEC sp_ThongKeNgay ?,?";
        String[] cols = {"skh", "soBR", "soMV", "TongThu", "TongChi", "DoanhThu"};
        return getListOfArray(sql, cols, stDate,enDate);
    }
    
    
     public List<Object[]> getDThuBanRa(Integer year) {
        String sql = "EXEC sp_ThongKeNamBanRa ?";
        String[] cols = {"NGAYLAP", "KH", "SOLUONG", "TONGDOANHTHU"};
        return getListOfArray(sql, cols, year);
    }
    
    public List<Object[]> getDThuFromDateToDateBanRa(Date stDate, Date enDate) {
        String sql = "EXEC sp_ThongKeNgayBanRa ?,?";
        String[] cols = {"NGAYLAP", "KH", "SOLUONG", "TONGDOANHTHU"};
        return getListOfArray(sql, cols, stDate,enDate);
    }
    
  
     public List<Object[]> getDThuMuaVao(Integer year) {
        String sql = "EXEC sp_ThongKeNamMuaVao ?";
        String[] cols = {"NGAYLAP", "KH", "SOLUONG", "TONGDOANHTHU"};
        return getListOfArray(sql, cols, year);
    }
    
    public List<Object[]> getDThuFromDateToDateMuaVao(Date stDate, Date enDate) {
        String sql = "EXEC sp_ThongKeNgayMuaVao ?,?";
        String[] cols = {"NGAYLAP", "KH", "SOLUONG", "TONGDOANHTHU"};
        return getListOfArray(sql, cols, stDate,enDate);
    }
    
    
    public List<Object[]> getDThuCamDo(Integer year) {
        String sql = "EXEC sp_ThongKeNamCamDo ?";
        String[] cols = {"NGAYLAP", "KH", "SOLUONG", "TONGDOANHTHU"};
        return getListOfArray(sql, cols, year);
    }
    
    public List<Object[]> getDThuFromDateToDateCamDo(Date stDate, Date enDate) {
        String sql = "EXEC sp_ThongKeNgayCamDo ?,?";
        String[] cols = {"NGAYLAP", "KH", "SOLUONG", "TONGDOANHTHU"};
        return getListOfArray(sql, cols, stDate,enDate);
    }
    
}
