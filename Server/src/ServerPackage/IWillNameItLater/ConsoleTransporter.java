package ServerPackage.IWillNameItLater;

import packet.*;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Tansporter, используемый при работе с консольным вводом
 * @author Maxim Antonov and Andrey Lyubkin
 */
public class ConsoleTransporter extends Transporter {
    private Integer i=0;
    String sb;

    /**
     * установка значений составных полей
     * @param res Receiver
     * @throws WrongTypeOfFieldException обработка некорректных типов полей
     */
    public void setFields(receiver res) throws WrongTypeOfFieldException {

        scan = new Scanner(System.in);

        System.out.println("Введите параметры. Сначала имя:");
        System.out.print("$");
        if(scan.hasNextLine()) {
            name = scan.nextLine();
            //catchN = scan.nextLine();
        }

        System.out.println("Введите рост. Для отделения дробной части используйте точку.");
        while (true) {
            try {
                System.out.print("$");
                sb = scan.nextLine();
                if (!sb.equals("")){
                    height = Double.parseDouble(sb);
                    break;
                }
            } catch (Exception ex) {
                System.out.println("Некорректное поле, try again");
            }
        }

        while (true) {
            System.out.println("Введите Цвет волос. Возможные цвета: " + Arrays.toString(Color.values()));
            System.out.print("$");
            try {
                if (scan.hasNextLine()) {
                    hairColor = Color.valueOf(scan.nextLine().trim().toUpperCase());
                    break;
                }
            }catch (Exception ex){
                System.err.println("Некорректное поле.");
            }
        }
        while (true) {
            System.out.println("Введите Цвет глаз. Возможные цвета: " + Arrays.toString(Color.values()));
            System.out.print("$");
            try {
                if (scan.hasNextLine()) {
                    eyeColor = Color.valueOf(scan.nextLine().trim().toUpperCase());
                    break;
                }
            }catch (Exception ex){
                System.err.println("Некорректное поле.");

            }
        }
        while (true) {
            try {
                System.out.println("Введите национальность : " + Arrays.toString(Country.values()));
                System.out.print("$");
                nationality = Country.valueOf(scan.nextLine().toUpperCase());
                break;
            } catch (Exception ex) {
                System.err.println("Некорректное поле.");
            }
        }

        System.out.println("Введите кооридинаты X для точного описания объекта точки. Для отделения дробной части используйте точку. ");
        while (true) {
            try {
                System.out.print("$");
                sb = scan.nextLine();
                if (!sb.equals("")){
                    x = Float.parseFloat(sb);
                    break;
                }
            } catch (Exception ex) {
                System.out.println("Некорректное поле, try again");
            }
        }

        System.out.println("Введите кооридинаты Y для точного описания объекта точки. Для отделения дробной части используйте точку.");
        while (true) {
            try {
                System.out.print("$");
                sb = scan.nextLine();
                if (!sb.equals("")){
                    y = Double.parseDouble(sb);
                    break;
                }
            } catch (Exception ex) {
                System.out.println("Некорректное поле, try again");
            }
        }

        System.out.println("Введите название локации");
        System.out.print("$");
        if(scan.hasNextLine()) {
                    name1 = scan.nextLine();
                    //catchN = scan.nextLine();
                }
        System.out.println("Введите кооридинаты X для точного описания точки локации. Для отделения дробной части используйте точку. ");
        while (true) {
            try {
                System.out.print("$");
                sb = scan.nextLine();
                if (!sb.equals("")){
                    x1 = Float.parseFloat(sb);
                    break;
                }
            } catch (Exception ex) {
                System.out.println("Некорректное поле, try again");
            }
        }

        System.out.println("Введите кооридинаты Y для точного описания точки локации. Для отделения дробной части используйте точку.");
        while (true) {
            try {
                System.out.print("$");
                sb = scan.nextLine();
                if (!sb.equals("")){
                    y1 = Double.parseDouble(sb);
                    break;
                }
            } catch (Exception ex) {
                System.out.println("Некорректное поле, try again");
            }
        }
    }
}
