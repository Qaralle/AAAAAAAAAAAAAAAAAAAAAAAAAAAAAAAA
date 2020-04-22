package ServerPackage.Сommands;

import ServerPackage.IWillNameItLater.receiver;

/**
 * Класс, предоставляющий реализацию команды history (вывести последние 8 команд)
 * @author Maxim Antonov and Andrey Lyubkin
 */
public class History implements Command {
    /**
     * выполнение команды
     * @param res Receiver (объект класса CollectionUnit)
     */
    @Override
    public void execute(receiver res) {
        res.addCommandToHistory("history");
        res.history();
    }
}
