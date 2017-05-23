package ua.goryainov.service;

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
public class excelImportService {
    public void saveToFile(List<User> users, String filepath){
        // создание самого excel файла в памяти
        HSSFWorkbook workbook = new HSSFWorkbook();
        // создание листа с названием "Просто лист"
        HSSFSheet sheet = workbook.createSheet("Клієнти");

        // заполняем список какими-то данными
        ;

        // счетчик для строк
        int rowNum = 0;

        // создаем подписи к столбцам (это будет первая строчка в листе Excel файла)
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue("Особа");
        row.createCell(1).setCellValue("П.І.Б./Назва підприємства");
        row.createCell(2).setCellValue("Пошта");
        row.createCell(3).setCellValue("Адреса доставки");
        row.createCell(4).setCellValue("ЕДРПОУ");
        row.createCell(5).setCellValue("Контактний телефон");
        row.createCell(6).setCellValue("Альтернативний телефон");
        row.createCell(7).setCellValue("Рейтинг");
        row.createCell(8).setCellValue("Закупівлі на сумму");

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
        row.createCell(4).setCellValue(dataModel.getTel1());
        row.createCell(5).setCellValue(dataModel.getTel2());
        row.createCell(6).setCellValue(rowNum+1);
        row.createCell(7).setCellValue(dataModel.getRating());
    }
}
