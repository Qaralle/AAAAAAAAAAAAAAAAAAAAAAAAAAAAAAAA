package ServerPackage.Сommands;

import ServerPackage.IWillNameItLater.Transporter;
import ServerPackage.IWillNameItLater.receiver;

/**
 * Класс, предоставляющий реализацию команды remove_by_id (удалить жлемент с заданным id)
 * @author Maxim Antonov and Andrey Lyubkin
 */
public class RemoveById extends CommandWithPars{
    /**
     * @param tr Transporter
     */
    public RemoveById(Transporter tr) {
        super(tr);
    }

    /**
     * выполнить команду
     * @param res Receiver (объект класса CollectionUnit)
     */
    @Override
    public void execute(receiver res) {
        this.setFieldsFromTransporter();
        res.remove_by_id(id);
        res.addCommandToHistory("remove_by_id");
    }
}
