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
        String[] cols = {"soKH", "soBR", "soMV", "TongThu", "TongChi", "DoanhThu"};
        return getListOfArray(sql, cols, year);
    }
    public List<Object[]> getDThuFromDateToDate(Date stDate, Date enDate) {
        String sql = "EXEC sp_ThongKeNgay ?,?";
        String[] cols = {"soKH", "soBR", "soMV", "TongThu", "TongChi", "DoanhThu"};
        return getListOfArray(sql, cols, stDate,enDate);
    }
}
