package Class_DBHelder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;


public class DBHelder_SQL {

    public static Connection getDbConnection() {
        Connection con = null;
        String connectionUrl = "jdbc:sqlserver://localhost:1433;"
                + "databaseName=QL_TIEMVANG1;"
                + "user=sa;" + "password=123;"
                + "encrypt=true;trustServerCertificate=true;";
        try {
            //Ket noi sql server
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connection success");
        } catch (SQLException ex) {
            System.out.println("Ket noi loi");
           // System.out.println(ex);
            ex.printStackTrace();
        }
        return con;
    }
    public static PreparedStatement getStmt(String sql, Object... args) throws SQLException {
        Connection conn = getDbConnection();
        PreparedStatement stmt;
        if (sql.trim().startsWith("{")) {
            stmt = conn.prepareCall(sql);
        } else {
            stmt = conn.prepareCall(sql);
        }
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i + 1, args[i]);
        }
        return stmt;
    }

    public static ResultSet query(String sql, Object... args) throws SQLException {
        PreparedStatement stmt = DBHelder_SQL.getStmt(sql, args);
        return stmt.executeQuery();
    }

    public static Object value(String sql, Object... args) {
        try {
            ResultSet rs = DBHelder_SQL.query(sql, args);
            if (rs.next()) {
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int update(String sql, Object... args) {
        try {
            PreparedStatement stmt = DBHelder_SQL.getStmt(sql, args);

            try {
                return stmt.executeUpdate();
            } finally {
                stmt.getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
