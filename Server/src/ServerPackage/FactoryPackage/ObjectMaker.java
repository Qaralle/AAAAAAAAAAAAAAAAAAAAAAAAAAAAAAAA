package ServerPackage.FactoryPackage;

/**
 * Interface дял фабрики
 * @author Maxim Antonov and Andrey Lyubkin
 */
public interface ObjectMaker<T> {
    /**
     * создать объект
     * @return объект заданного класса
     */
    T create();
}
