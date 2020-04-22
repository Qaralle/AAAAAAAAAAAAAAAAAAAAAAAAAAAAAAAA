package ServerPackage.Сommands;

import ServerPackage.IWillNameItLater.receiver;

/**
 * Класс, предоставляющий реализацию команды info (вывести информацию о коллекции)
 * @author Maxim Antonov and Andrey Lyubkin
 */
public class Info implements Command{
    /**
     * выполнение команды
     * @param res Receiver (объект класса CollectionUnit)
     */
    @Override
    public void execute(receiver res) {
        res.info();
        res.addCommandToHistory("info");
    }

}
