package packet;

import java.io.Serializable;

/**
 * Класс, предоставляющий локацию для элемента коллекции
 * @author Maxim Antonov and Andrey Lyubkin
 */
public class Location implements Comparable<Location>, Serializable {
    private Float x; //Поле не может быть null
    private Double y;
    private String name; //Длина строки не должна быть больше 222, Поле не может быть null

    public Location(){
    }

    /**
     * @param x_ координата x
     * @param y_ координата y
     * @param name_ название локации
     */
    public Location(Float x_,Double y_,String name_){
        this.x=x_;
        this.y=y_;
        this.name=name_;
    }
    public String getName(){
        return name;
    }
    public Float getX() { return x; }
    public Double getY() { return y; }
    /**
     * Метод, реализующий сравнение с другой локацией
     * @param o объект класса packet.Location
     * @return результат сравнения
     */
    @Override
    public int compareTo(Location o) {
        return name.compareTo(o.getName());

    }

    public void SetX(Float x_){
        this.x=x_;
    }

    public void SetY(double y_){
        this.y=y_;
    }

    public void SetName(String name_){
        this.name=name_;
    }
}