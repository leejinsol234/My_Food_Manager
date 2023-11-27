package org.myfm.team.commons.exports;

import lombok.RequiredArgsConstructor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.myfm.team.entities.PublicDB;
import org.myfm.team.repositories.PublicDBRepository;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InsertData {
    private final PublicDBRepository repository;

    public void process() {



        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("data.xlsx"));
             OPCPackage opcPackage = OPCPackage.open(bis)) {
            XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
            List<PublicDB> items = new ArrayList<>();
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

                    PublicDB item = PublicDB.builder()
                            .foodNm(getData(cells[0]))
                            .ca(Double.parseDouble(getData(cells[1])))
                            .fe(Double.parseDouble(getData(cells[2])))
                            .mg(Double.parseDouble(getData(cells[3])))
                            .zn(Double.parseDouble(getData(cells[4])))
                            .va(Double.parseDouble(getData(cells[5])))
                            .vb1(Double.parseDouble(getData(cells[6])))
                            .vb2(Double.parseDouble(getData(cells[7])))
                            .vc(Double.parseDouble(getData(cells[8])))
                            .build();
                    items.add(item);



                }
            }

            repository.saveAllAndFlush(items);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getData(XSSFCell cell) {
        if (cell == null) return "";
        boolean isNumber = cell.getCellType() == CellType.NUMERIC;
        cell.setCellType(CellType.STRING);

        String value = cell.getStringCellValue();
        if (isNumber) {
            value = value.substring(0, value.lastIndexOf(".") + 2);
        }
        if (value.equals("-")) value = "0";
        return value;
    }
}
