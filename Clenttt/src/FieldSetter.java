import packet.*;

import java.util.Arrays;
import java.util.Scanner;

public class FieldSetter {
    protected Scanner scan;
    private String sb;


    protected int index;
    protected long id;
    protected String name;
    protected Double height; //Поле не может быть null, Значение поля должно быть больше 0
    protected Color eyeColor; //Поле может быть null
    protected Color hairColor; //Поле не может быть null
    protected Country nationality; //Поле может быть null
    protected Float x; //Значение поля должно быть больше -652, Поле не может быть null
    protected Double y; //Поле не может быть null
    protected Float x1; //Поле не может быть null
    protected double y1;
    protected String name1; //Длина строки не должна быть больше 222, Поле не может быть null
    protected Location loc;
    protected String file_name;


    private String stringToSend;


    protected String[] buffer;
    protected String catchN;

    public void setFields() throws WrongTypeOfFieldException {

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
        stringToSend  = "="+"name"+"="+name+"="+
                "height"+"="+height+"="+
                "eyeColor"+"="+eyeColor+"="+
                "hairColor"+"="+hairColor+"="+
                "x"+"="+x+"="+
                "x1"+"="+x1+"="+
                "y"+"="+y+"="+
                "y1"+"="+y1+"="+
                "nationality"+"="+nationality+"="+
                "name1"+"="+name1;
    }

    public String getStringToSend(){ return stringToSend;}
    public long getId() { return id; }
    public String getName() { return name; }
    public Color getEyeColor() { return eyeColor; }
    public Color getHairColor() { return hairColor; }
    public Country getNationality() { return nationality; }
    public Double getHeight() { return height; }
    public Double getY() { return y; }
    public double getY1() { return y1; }
    public Float getX() { return x; }
    public Float getX1() { return x1; }
    public String getName1() { return name1; }
    public Location getLoc(){ return loc;}
    public String getFile_name() { return file_name; }
    public int getIndex() { return index; }
}
