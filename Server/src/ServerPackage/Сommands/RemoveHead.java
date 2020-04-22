package ServerPackage.Сommands;

import ServerPackage.IWillNameItLater.receiver;

/**
 * Класс, предоставляющий реализацию команды remove_head (удалить первый элемент коллекции)
 * @author Maxim Antonov and Andrey Lyubkin
 */
public class RemoveHead implements Command {
    /**
     * выполнить команду
     * @param res Receiver (объект класса CollectionUnit)
     */
    @Override
    public void execute(receiver res) {
        res.removeHead();
        res.addCommandToHistory("remove_head");
    }
}
