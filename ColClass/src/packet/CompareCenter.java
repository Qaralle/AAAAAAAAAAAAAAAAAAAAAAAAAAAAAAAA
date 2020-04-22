package packet;

import java.util.Comparator;


/**
 * Класс, реализующий компаратор
 * @author Maxim Antonov and Andrey Lyubkin
 */
public class CompareCenter implements Comparator<Person> {

    /**
     * Метод, реализующий сравнение двух объектов класса packet.Person
     * @param o1 packet.Person
     * @param o2 packet.Person
     * @return результат сравнения
     */
    @Override
    public int compare(Person o1, Person o2) {
        int compRes=0;
        if (o1.getId() - o2.getId() > 0){
            compRes=compRes+1;
        }
        else if (o1.getId() - o2.getId() < 0){
            compRes=compRes-1;
        }
        compRes=compRes+(o1.getName().compareTo(o2.getName()));
        compRes=compRes+(o1.getCoordinates().compareTo(o2.getCoordinates()));
        compRes=compRes+(o1.getHeight().compareTo(o2.getHeight()));
        compRes=compRes+(o1.getEyeColor().compareTo(o2.getEyeColor()));
        compRes=compRes+(o1.getHairColor().compareTo(o2.getHairColor()));
        compRes=compRes+(o1.getNationality().compareTo(o2.getNationality()));
        compRes=compRes+(o1.getLocation().compareTo(o2.getLocation()));
        compRes=compRes+(o1.getData().compareTo(o2.getData()));
        return compRes;
    }

}


