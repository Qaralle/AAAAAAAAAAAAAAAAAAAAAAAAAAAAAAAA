package ServerPackage.Сommands;

import packet.*;
import ServerPackage.IWillNameItLater.Transporter;
import ServerPackage.IWillNameItLater.receiver;
import packet.Color;
import packet.Country;

import java.util.Scanner;

/**
 * Абстрактный класс, наследуемый командами, принимающими какие-либо аргументы
 * @author Maxim Antonov and Andrey Lyubkin
 */
public abstract class CommandWithPars implements Command {

    protected Transporter transporter;


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
    protected Scanner scan;

    /**
     * @param tr Transporter
     */
    public CommandWithPars(Transporter tr) {
        this.transporter=tr;
    }

    public CommandWithPars(receiver res) {
    }

    public Transporter getTransporter() { return transporter; }

    public void SetscanForFile(){
    }

    /**
     * Метод, позволяющий заполнить поля команды значениями, полученными из Transporter
     */
    public void setFieldsFromTransporter(){
        this.id = transporter.getId();
        this.name = transporter.getName();
        this.height = transporter.getHeight();
        this.eyeColor = transporter.getEyeColor();
        this.hairColor = transporter.getHairColor();
        this.nationality = transporter.getNationality();
        this.x = transporter.getX();
        this.x1 = transporter.getX1();
        this.y = transporter.getY();
        this.y1 = transporter.getY1();
        this.name1 = transporter.getName1();
    }


}
