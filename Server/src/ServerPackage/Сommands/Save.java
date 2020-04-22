package ServerPackage.Сommands;

import ServerPackage.IWillNameItLater.receiver;

import java.io.IOException;

/**
 * Класс, предоставляющий реализацию команды save (сохранить коллекцию в файл)
 * @author Maxim Antonov and Andrey Lyubkin
 */
public class Save implements Command {
    /**
     * выполнить команду
     * @param res Receiver (объект класса CollectionUnit)
     */
    @Override
    public void execute(receiver res) {
        try {
            res.save();
            res.addCommandToHistory("save");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
