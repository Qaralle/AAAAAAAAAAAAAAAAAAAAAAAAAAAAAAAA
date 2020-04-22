package ServerPackage.Сommands;

import ServerPackage.IWillNameItLater.Transporter;
import ServerPackage.IWillNameItLater.receiver;

/**
 * Класс, реализующий комады count_less_than_location
 * @author Maxim Antonov and Andrey Lyubkin
 */
public class CountLessThanLocation extends CommandWithPars {
    /**
     * @param tr Transporter
     */
    public CountLessThanLocation(Transporter tr) {
        super(tr);
    }

    /**
     * подсчет количества элементов коллекции, значение поле Location которых меньше, чем заданное
     * @param res Receiver (объект класса CollectionUnit)
     */
    @Override
    public void execute(receiver res) {
        res.countLessThanLocation(transporter.getName1());
        res.addCommandToHistory("Count_less_than_location");
    }
}
