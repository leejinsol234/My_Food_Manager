package org.myfm.team.commons.exports;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.MessageFormat;

public class MakeOracleSql {
    public static void main(String[] args) {

        File file = new File("data2.sql");
        StringBuffer sb = new StringBuffer(100000000);
        String pattern = "INSERT INTO \"public_db\" (food_nm, ca, fe, mg, va, vb1, vb2, vc, zn, nu_no) " +
                " VALUES ({0}, {1},{2}, {3}, {4}, {5}, {6}, {7}, {8}, \"public_db_seq\".nextval);";
        MessageFormat mf = new MessageFormat(pattern);

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("data.xlsx"));
             OPCPackage opcPackage = OPCPackage.open(bis);
             BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
            int seq = 1;
            for (int i = 1; i <= 8; i++) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                for (int j = 1; j <= sheet.getLastRowNum(); j++) { // 행별 데이터 추출
                    XSSFRow row = sheet.getRow(j);
                    XSSFCell[] cells = new XSSFCell[9];

                    cells[0] = row.getCell(1); // 음식명
                    cells[1] = row.getCell(2); // 칼슘(ca)
                    cells[2] = row.getCell(3); // 철(fe)
                    cells[3] = row.getCell(4); // 마그네슘(mg)
                    cells[4] = row.getCell(5); // 아연(zn)
                    cells[5] = row.getCell(6); // 레티놀(va)
                    cells[6] = row.getCell(7); // 비타민 B1(vb1)
                    cells[7] = row.getCell(8); // 비타민 B2(vb2)
                    cells[8] = row.getCell(9); // 비타민 C(vc)
                    String sql = mf.format(pattern,
                            getData(cells[0]),
                            getData(cells[1]),
                            getData(cells[2]),
                            getData(cells[3]),
                            getData(cells[4]),
                            getData(cells[5]),
                            getData(cells[6]),
                            getData(cells[7]),
                            getData(cells[8]));
                    bw.write(sql + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getData(XSSFCell cell) {
        if (cell == null) return "";
        boolean isNumber = cell.getCellType() == CellType.NUMERIC;
        cell.setCellType(CellType.STRING);

        String value = cell.getStringCellValue();
        if (isNumber) {
            value = value.substring(0, value.lastIndexOf(".") + 2);
        }
        if (value.equals("-")) value = "0";
        return "'" + value + "'";
    }
}
