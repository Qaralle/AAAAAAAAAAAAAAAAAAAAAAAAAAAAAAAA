package packet;

/**
 * Класс, реализующий проверку полей на заполненность
 * @author Maxim Antonov and Andrey Lyubkin
 */
public class NullPolice implements Police<Person,Coordinates> {
    /**
     * проверка полей объекта класса packet.Person
     * @param p объект класса packet.Person
     */
    @Override
    public void PersonReplace(Person p){
        if (p.getName()==null){
            System.out.println("У одного из объектов null поле будет перезаписано автоматически");
            p.setName("noname"+ p.getId());
        }
        if (p.getCoordinates()==null){
            System.out.println("У одного из объектов null поле будет перезаписано автоматически");
            p.setCoordinates(new Coordinates(0f, 0d));
        }
        if (p.getHeight()==null){
            System.out.println("У одного из объектов null поле будет перезаписано автоматически");
            p.setHeight(123.0);
        }
        if (p.getHairColor()==null){
            System.out.println("У одного из объектов null поле будет перезаписано автоматически");
            p.setHairColor(Color.GREEN);
        }
        if (p.getEyeColor()==null){
            System.out.println("У одного из объектов null поле будет перезаписано автоматически");
            p.setEyeColor(Color.GREEN);
        }
        if (p.getNationality()==null){
            System.out.println("У одного из объектов null поле будет перезаписано автоматически");
            p.setNationality(Country.CHINA);
        }
        if (p.getLocation()==null){
            System.out.println("У одного из объектов null поле будет перезаписано автоматически");
            p.setLocation(new Location(0f, 0d, "Udomlya"));
        }
    }

    /**
     * проверка полей объекта класса packet.Coordinates
     * @param coo объект класса packet.Coordinates
     */
    @Override
    public void CoordinatesReplace(Coordinates coo){
        try {

            if (coo.getX()==null){
                System.out.println("У одного из объектов null поле будет перезаписано автоматически");
                coo.SetX(0f);
            }
            if (coo.getY()==null){
                System.out.println("У одного из объектов null поле будет перезаписано автоматически");
                coo.SetY(0d);
            }
        }catch (NullPointerException ex){
            coo=new Coordinates(0.0f,0.0d);
        }

    }
    /**
     * проверка всех полей
     * @param p объект класса packet.Person
     * @param coo объект класса packet.Coordinates
     */
    public void ReplaceEverything(Person p, Coordinates coo){
        this.PersonReplace(p);
        this.CoordinatesReplace(coo);
    }
}
