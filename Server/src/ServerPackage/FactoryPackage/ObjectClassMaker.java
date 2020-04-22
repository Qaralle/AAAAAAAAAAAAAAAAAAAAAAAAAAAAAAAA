package ServerPackage.FactoryPackage;

import packet.*;

/**
 * фабрика Person
 * @author Maxim Antonov and Andrey Lyubkin
 */
public class ObjectClassMaker implements ObjectMaker<Person> {
    /**
     * создание объекта класса Person
     * @return объект класса Person
     */
    @Override
    public Person create() {
        return new Person();
    }
}
