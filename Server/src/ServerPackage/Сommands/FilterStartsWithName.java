package ServerPackage.Сommands;

import ServerPackage.IWillNameItLater.Transporter;
import ServerPackage.IWillNameItLater.receiver;

/**
 * Класс, предоставляющий реализацию команды filter_starts_with_name (вывести все элементы, имя которых начинается с заанной строки)
 * @author Maxim Antonov and Andrey Lyubkin
 */
public class FilterStartsWithName extends CommandWithPars {
    public FilterStartsWithName(Transporter tr) {
        super(tr);
    }

    /**
     * выполнение команды
     * @param res Receiver (объект класса CollectionUnit)
     */
    @Override
    public void execute(receiver res) {
        this.setFieldsFromTransporter();
        res.filterStartsWithName(name);
        res.addCommandToHistory("filter_starts_with_name");
    }
}
