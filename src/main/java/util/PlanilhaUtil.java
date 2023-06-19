package util;

import excel.Planilha;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlanilhaUtil {
    public static List<Planilha> getPlanilhas(String mainPath) {

        List<Planilha> dadosPlanilhas = new ArrayList<>();

        try {

            Workbook workbook = WorkbookFactory.create(new File(mainPath));

            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                Cell nomeCell = row.getCell(0);
                Cell emailCell = row.getCell(1);
                Cell pathCell = row.getCell(2);

                String nome = "";
                String email = "";
                String path = "";

                if (nomeCell != null && nomeCell.getCellType() == CellType.STRING) {
                    nome = nomeCell.getStringCellValue();
                }

                if (emailCell != null && emailCell.getCellType() == CellType.STRING) {
                    email = emailCell.getStringCellValue();
                }

                if (pathCell != null && pathCell.getCellType() == CellType.STRING) {
                    path = pathCell.getStringCellValue();
                }

                if (nome.isEmpty() || email.isEmpty() || path.isEmpty()) {
                    continue;
                }

                Planilha planilha = new Planilha(nome, email, path);

                dadosPlanilhas.add(planilha);
            }

            workbook.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return dadosPlanilhas;
    }
}
