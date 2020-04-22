package packet;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Класс, предоставляющий объект packet.Person для коллекции
 * @author Maxim Antonov and Andrey Lyubkin
 */
public class Person implements Comparable<Person>, Serializable {
    private static final long LIMIT = 10000000000L;
    private static long last = 0;


    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    public Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double height; //Поле не может быть null, Значение поля должно быть больше 0
    private Color eyeColor; //Поле может быть null
    private Color hairColor; //Поле не может быть null
    private Country nationality; //Поле может быть null
    public Location location; //Поле может быть null


    private static long setID() {

        long id = System.currentTimeMillis() % LIMIT;
        if ( id <= last ) {
            id = (last + 1) % LIMIT;
        }
        return last = id;
    }

    public Person()  {
        this.id = setID();
        this.creationDate = LocalDateTime.now();
    }
    public LocalDateTime getData(){
        return creationDate;
    }
    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public Double getHeight(){
        return height;
    }
    public Color getEyeColor(){
        return eyeColor;
    }
    public Color getHairColor(){
        return hairColor;
    }
    public Country getNationality(){
        return nationality;
    }
    public  Location getLocation(){
        return location;
    }


    public void setName(String name_) {
        this.name = name_;
    }
    public void setCoordinates(Coordinates o1) {
        this.coordinates=o1;
    }
    public void setHeight(Double height_){
        this.height=height_;
    }
    public void setEyeColor(Color c1){
        this.eyeColor=c1;
    }
    public void setHairColor(Color c2){
        this.hairColor=c2;
    }
    public void setNationality(Country o1){
        this.nationality=o1;
    }
    public  void setLocation( Location o1){
        this.location=o1;
    }

    /**
     * Установка всех полей
     * @param name Имя Объекта, String
     * @param coo Координаты объекта, packet.Coordinates
     * @param height Высота объекта, Double
     * @param eyeColor Цвет глаз, packet.Color
     * @param hairColor Цвет волос, packet.Color
     * @param nationality Национальность, packet.Country
     * @param loc Локация, packet.Location
     */
    public void setEverything(String name, Coordinates coo, Double height, Color eyeColor, Color hairColor, Country nationality, Location loc){
        this.setName(name);
        this.setCoordinates(coo);
        this.setHeight(height);
        this.setEyeColor(eyeColor);
        this.setHairColor(hairColor);
        this.setNationality(nationality);
        this.setLocation(loc);
        new FieldPolice().PersonReplace(this);
        new NullPolice().PersonReplace(this);
    }


    @Override
    public int compareTo(Person o) {
        return new CompareCenter().compare(this,o);
    }
}