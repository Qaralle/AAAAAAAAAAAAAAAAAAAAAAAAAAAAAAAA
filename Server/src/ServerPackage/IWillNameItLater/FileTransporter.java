package ServerPackage.IWillNameItLater;

import ServerPackage.Log4J2;
import packet.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Класс, предоставляющий Transporter для файлов
 * @author Maxim Antonov and Andrey Lyubkin
 */
public class FileTransporter extends Transporter {
    private String file_name;
    private String catchV;
    private Scanner scan;
    private Log4J2 SO = new Log4J2(System.out);

    /**
     * @param scan_ объект класса Scanner
     */
    public FileTransporter(Scanner scan_){
        this.scan=scan_;
    }

    /**
     * Установка полей из файла
     * @param res Receiver
     * @throws FileNotFoundException файл не найден
     * @throws WrongTypeOfFieldException обработка некорректных типов полей
     */
    public void setFields(receiver res) throws FileNotFoundException, WrongTypeOfFieldException {
        if (scan.hasNextLine()) {
            name = scan.nextLine();
            //catchN = scan.nextLine();
        }

    try {

        height = scan.nextDouble();
        catchN = scan.nextLine();

        hairColor = Color.valueOf(scan.nextLine().trim());

        eyeColor = Color.valueOf(scan.nextLine().trim());

        nationality = Country.valueOf(scan.nextLine());

        x = scan.nextFloat();
        catchN = scan.nextLine();

        y = scan.nextDouble();
        catchN = scan.nextLine();

        name1 = scan.nextLine();

        x1 = scan.nextFloat();
        catchN = scan.nextLine();

        y1 = scan.nextDouble();
        if (scan.hasNextLine()) {
            catchN = scan.nextLine();
        }
    }catch(Exception e){
        throw new WrongTypeOfFieldException();
    }
    }
}
