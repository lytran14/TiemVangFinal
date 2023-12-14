package com.model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

    public static void outExcel(DefaultTableModel model) throws FileNotFoundException, IOException {
        FileOutputStream excelFOU = null;
        BufferedOutputStream excelBOU = null;
        Workbook workbook = null;
        JFileChooser excelFileChooser = new JFileChooser("D:\\Excel");
        excelFileChooser.setDialogTitle("Save As");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showSaveDialog(null);

        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("JTABLE sheet");
                CreationHelper createHelper = workbook.getCreationHelper();
                Row row = sheet.createRow(0);
                CellStyle headerStyle = workbook.createCellStyle();
                headerStyle.setWrapText(true);  // Tự động xuống dòng cho tiêu đề dài
                headerStyle.setAlignment(HorizontalAlignment.CENTER);

                for (int i = 0; i < model.getColumnCount(); i++) {
                    row.createCell(i).setCellValue(model.getColumnName(i));
                    row.getCell(i).setCellStyle(headerStyle);
                }

                CellStyle dateCellStyle = workbook.createCellStyle();
                dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

                for (int i = 0; i < model.getRowCount(); i++) {
                    row = sheet.createRow(i + 1);
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        if (model.getValueAt(i, j) != null) {
                            if (model.getValueAt(i, j) instanceof Date) {
                                row.createCell(j).setCellValue((Date) model.getValueAt(i, j));
                                row.getCell(j).setCellStyle(dateCellStyle);
                            } else {
                                row.createCell(j).setCellValue(model.getValueAt(i, j).toString());
                            }
                        }
                    }
                }

                excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile() + ".xlsx");
                excelBOU = new BufferedOutputStream(excelFOU);
                workbook.write(excelBOU);
                JOptionPane.showMessageDialog(null, "Xuất file thành công");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (excelBOU != null) {
                        excelBOU.close();
                    }
                    if (excelFOU != null) {
                        excelFOU.close();
                    }

                    if (workbook != null) {
                        workbook.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Xuất file thất bại");
        }
    }
}
