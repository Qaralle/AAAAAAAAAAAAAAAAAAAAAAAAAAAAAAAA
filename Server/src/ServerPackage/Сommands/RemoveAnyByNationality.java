package ServerPackage.Сommands;

import ServerPackage.IWillNameItLater.Transporter;
import ServerPackage.IWillNameItLater.receiver;

/**
 * Класс, предоставляющий реализацию команды remove_any_by_nationality (удаляет 1 элемент с заданной национальностью)
 * @author Maxim Antonov and Andrey Lyubkin
 */
public class RemoveAnyByNationality extends CommandWithPars {
    /**
     * @param tr Transporter
     */
    public RemoveAnyByNationality(Transporter tr) {
        super(tr);
    }

    /**
     * выполнить команду
     * @param res Receiver (объект класса CollectionUnit)
     */
    @Override
    public void execute(receiver res) {
        this.setFieldsFromTransporter();
        res.removeAnyByNationality(nationality);
        res.addCommandToHistory("remove_any_by_nationality");
    }
}
