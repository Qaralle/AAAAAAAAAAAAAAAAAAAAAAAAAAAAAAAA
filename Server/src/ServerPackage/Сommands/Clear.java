package ServerPackage.Сommands;

import ServerPackage.IWillNameItLater.receiver;

/**
 * Класс, реализующий команду clear (очистка коллекции)
 * @author Maxim Antonov and Andrey Lyubkin
 */
public class Clear implements Command {
    /**
     * выполнение команды
     * @param res Receiver (объект класса CollectionUnit)
     */
    @Override
    public void execute(receiver res) {
        res.clear();
        res.addCommandToHistory("clear");
    }
}