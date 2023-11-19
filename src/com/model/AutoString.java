package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Class_DBHelder.DBHelder_SQL;

public class AutoString {

    public static String autoEmail(String username) {
        String[] split = username.split(" ");
        int lenght = split.length;
        String name = split[lenght - 1];
        String spf = "";
        for (int i = 0; i < split.length - 1; i++) {
            spf += split[i].substring(0, 1);
        }
        return name + spf + "@gmail.com";
    }

public static String autoID(String type, String idTable, String table) {
    int id = 0;
    Connection conn = DBHelder_SQL.getDbConnection();
    try {
        PreparedStatement pst = conn.prepareStatement("SELECT Max(" + idTable + ") as LastID FROM " + table);
        ResultSet rs = pst.executeQuery();
        String makh = "";
        while (rs.next()) {
            makh += rs.getString(1);
        }

        String[] split = makh.split("000");
        for (int i = 1; i < split.length; i++) {
            try {
                int parsedId = Integer.parseInt(split[i]);
                if (parsedId > id) {
                    id = parsedId;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        pst.close();
        rs.close();
        
        // Kiểm tra xem mã khách hàng đã tồn tại trong cơ sở dữ liệu chưa
        String nextId = type + String.format("%04d", id + 1);
        if (checkDuplicateId(nextId, idTable, table)) {
            // Nếu mã khách hàng đã tồn tại, tăng id cho đến khi tìm được mã duy nhất
            while (checkDuplicateId(nextId, idTable, table)) {
                id++;
                nextId = type + String.format("%04d", id + 1);
            }
        }
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return type + String.format("%04d", id + 1);
}

public static boolean checkDuplicateId(String id, String idTable, String table) {
    Connection conn = DBHelder_SQL.getDbConnection();
    try {
        PreparedStatement pst = conn.prepareStatement("SELECT COUNT(*) FROM " + table + " WHERE " + idTable + " = ?");
        pst.setString(1, id);
        ResultSet rs = pst.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        pst.close();
        rs.close();
        conn.close();
        return count > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println(e);
    }
    return false;
}
}
