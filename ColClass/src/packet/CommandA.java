package packet;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

public class CommandA implements Serializable {
    private String command_key;
    private long id;
    private String file_name;
    private String location;
    private Country nationality;
    private String sb;
    private int index;
    private String name;
    private Double height; //Поле не может быть null, Значение поля должно быть больше 0
    private Color eyeColor; //Поле может быть null
    private Color hairColor; //Поле не может быть null
    private Float x; //Значение поля должно быть больше -652, Поле не может быть null
    private Double y; //Поле не может быть null
    private Float x1; //Поле не может быть null
    private double y1;
    private String name1; //Длина строки не должна быть больше 222, Поле не может быть null
    private Location loc;
    private String stringToSend="";
    private String login;
    private boolean TYPE=true;
    private String ACCESS="DEFAULT";
    private String password;


    public CommandA(String command_key_,String login_, String password_, String ACCESS_){
        this.command_key=command_key_;
        this.login=login_;
        this.password=password_;
        this.ACCESS=ACCESS_;
    }

    public CommandA(String command_key_,String ACCESS_){
        this.command_key=command_key_;
        this.ACCESS=ACCESS_;
    }
    public CommandA(String command_key_, long id_, String ACCESS_){
        this.command_key=command_key_;
        this.id=id_;
        this.ACCESS=ACCESS_;
    }

    public CommandA(String command_key_, String location_, int i, String ACCESS_) {
        this.command_key = command_key_;
        this.location = location_;
        this.ACCESS=ACCESS_;
    }
    public CommandA(String command_key_, Country nationality_, String ACCESS_) {

        this.nationality = nationality_;
        this.command_key = command_key_;
        this.ACCESS=ACCESS_;
    }
    public CommandA(String command_key_, String file_name_, String ACCESS_){
        this.command_key=command_key_;
        this.file_name=file_name_;
        this.ACCESS=ACCESS_;
    }

    public void setFields(Scanner scan) throws WrongTypeOfFieldException {


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
            location = scan.nextLine();
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
                "name1"+"="+location;

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
    public String getLogin() {return login;}
    public boolean getType() {return TYPE;}
    public String GETACCESS() {
        return ACCESS;
    }

    @Override
    public String toString(){
        return command_key;
    }

    public String gettoUpdate_s(){
        return command_key+"="+id;
    }
    public String gettoUpdate_b(){
        return command_key+"="+"id"+"="+id;
    }
    public String getToremove(){
        return command_key+"="+"id"+"="+id;
    }

    public String getPass(){return password;}
    public String getLocat(){return command_key+"="+"location"+"="+location;}
    public String getStartWithName(){return  command_key+"="+"name"+"="+file_name;}
    public String getToremovenat(){
        return command_key+"="+"nationality"+"="+nationality;
    }
    public String getFileName(){
        return command_key+"="+"file_name"+"="+file_name;
    }

}
