package ServerPackage.Сommands;

import ServerPackage.IWillNameItLater.receiver;

/**
 * Класс, предоставляющий реализацию команды show (вывести все элементы коллекции)
 * @author Maxim Antonov and Andrey Lyubkin
 */
public class Show implements Command {
    /**
     * выполнить команду
     * @param res Receiver (объект класса CollectionUnit)
     */
    @Override
    public void execute(receiver res) {
        res.show();
        res.addCommandToHistory("show");
    }

}
