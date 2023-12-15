package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Class_DBHelder.DBHelder_SQL;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

public class AutoString {
    
    
//----------------------------/AUTO GMAIL/----------------------------/
    private static List<Integer> generatedNumbers = new ArrayList<>();

    public static String autoEmail(String username) {
        String[] split = username.split(" ");
        int length = split.length;
        String name = split[length - 1];
        StringBuilder spf = new StringBuilder();
        int randomNumber = ThreadLocalRandom.current().nextInt(100, 10000);
        for (int i = 0; i < split.length - 1; i++) {
            spf.append(split[i].substring(0, 1).toLowerCase());
        }
        String generatedEmail = removeAccents(name).toLowerCase() + spf.toString() + "nv" + randomNumber + "@gmail.com";
        // Kiểm tra sự tồn tại của số ngẫu nhiên
        while (isNumberExists(randomNumber)) {
            randomNumber = ThreadLocalRandom.current().nextInt(100, 10000);
            generatedEmail = removeAccents(name).toLowerCase() + spf.toString() + "nv" + randomNumber + "@gmail.com";
        }
        // Thêm số đã tạo vào danh sách
        generatedNumbers.add(randomNumber);
        return generatedEmail;
    }

    private static boolean isNumberExists(int number) {
        return generatedNumbers.contains(number);
    }

    
//----------------------------/AUTO MÃ NHÂN VIÊN/----------------------------/ 
    private static int counter = 0;

    public static String generateAutoID(String name) {
        String[] split = name.split(" ");
        int length = split.length;
        String namee = split[length - 1];
        StringBuilder spf = new StringBuilder();
        int autoNumber = counter;
        for (int i = 0; i < length - 1; i++) {
            spf.append(removeAccents(split[i].substring(0, 1).toLowerCase()));
        }
        String generatedID = removeAccents(namee).toLowerCase() + spf.toString() + autoNumber;
        // Kiểm tra sự tồn tại của mã
        while (isIDExists(generatedID)) {
            counter++;
//            if (counter > 10000) {
//                counter = 1;
//            }
            autoNumber = counter;
            generatedID = removeAccents(namee).toLowerCase() + spf.toString() + autoNumber;
        }
        return generatedID;
    }

    public static String removeAccents(String input) {
        String temp = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public static boolean isIDExists(String id) {
        Connection conn = DBHelder_SQL.getDbConnection();
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT COUNT(*) FROM nhanvien WHERE manv = ?");
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

    
//----------------------------/AUTO MÃ/----------------------------/ 
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
//----------------------------/AUTO MÃ/----------------------------/ 
}
