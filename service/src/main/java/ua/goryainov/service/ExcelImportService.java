package ua.goryainov.service;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import ua.goryainov.hibernate.model.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by gerasymiuk on 24.05.17.
 */
public class ExcelImportService {
    public static void saveToFile(List<User> users, String filepath){
        // создание самого excel файла в памяти
        HSSFWorkbook workbook = new HSSFWorkbook();
        // создание листа с названием "Просто лист"
        HSSFSheet sheet = workbook.createSheet("Клієнти");
        sheet.autoSizeColumn(0);
        // заполняем список какими-то данными
        ;

        // счетчик для строк
        int rowNum = 0;

        // создаем подписи к столбцам (это будет первая строчка в листе Excel файла)

        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue("Особа");
        sheet.setColumnWidth(0,4000);
        row.createCell(1).setCellValue("П.І.Б./Назва підприємства");
        sheet.setColumnWidth(1,6500);
        row.createCell(2).setCellValue("Пошта");
        sheet.setColumnWidth(2,4000);
        row.createCell(3).setCellValue("Адреса доставки");
        sheet.setColumnWidth(3,4000);
        row.createCell(4).setCellValue("ЕДРПОУ");
        sheet.setColumnWidth(4,4000);
        row.createCell(5).setCellValue("Контактний телефон");
        sheet.setColumnWidth(5,5000);
        row.createCell(6).setCellValue("Альтернативний телефон");
        sheet.setColumnWidth(6,5000);
        row.createCell(7).setCellValue("Рейтинг");
        sheet.setColumnWidth(7,3000);
        row.createCell(8).setCellValue("Закупівлі на сумму");
        sheet.setColumnWidth(8,4000);

        // заполняем лист данными
        for (User dataModel : users) {
            createSheetHeader(sheet, ++rowNum, dataModel);
        }

        // записываем созданный в памяти Excel документ в файл
        try (FileOutputStream out = new FileOutputStream(new File(filepath))) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Excel файл успешно создан!");
    }

    // заполнение строки (rowNum) определенного листа (sheet)
    // данными  из dataModel созданного в памяти Excel файла
    private static void createSheetHeader(HSSFSheet sheet, int rowNum, User dataModel) {
        Row row = sheet.createRow(rowNum);
        if (dataModel.getEDRPOU().equals(""))
        row.createCell(0).setCellValue("Фізична особа");
        else
            row.createCell(0).setCellValue("Юридична особа");
        row.createCell(1).setCellValue(dataModel.getName());
        row.createCell(2).setCellValue(dataModel.getMail());
        row.createCell(3).setCellValue(dataModel.getDeliveryAdress());
        row.createCell(4).setCellValue(dataModel.getEDRPOU());
        row.createCell(5).setCellValue(dataModel.getTel1());
        row.createCell(6).setCellValue(dataModel.getTel2());
        row.createCell(7).setCellValue(rowNum+1);
        row.createCell(8).setCellValue(dataModel.getRating());
    }
}
