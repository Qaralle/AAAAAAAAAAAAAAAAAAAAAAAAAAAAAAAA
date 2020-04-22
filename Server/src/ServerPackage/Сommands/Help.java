package ServerPackage.Сommands;

import ServerPackage.IWillNameItLater.receiver;

;

/**
 * Класс, предоставляющий реализацию команды help (вывести справку по доступным командам)
 * @author Maxim Antonov and Andrey Lyubkin
 */
public class Help implements Command {
    /**
     * выполнение команды
     * @param res Receiver (объект класса CollectionUnit)
     */
    @Override
    public void execute(receiver res) {
        res.help();
        res.addCommandToHistory("help");
    }
}
