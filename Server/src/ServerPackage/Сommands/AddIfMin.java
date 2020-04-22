package ServerPackage.Сommands;

import ServerPackage.IWillNameItLater.Transporter;
import ServerPackage.IWillNameItLater.WrongTypeOfFieldException;
import ServerPackage.IWillNameItLater.receiver;

import java.io.FileNotFoundException;

/**
 * Класс, реализующйи команду add_if_mon (добавить в коллекцию элемент, если он меньше, чем любой из существующих)
 * @author Maxim Antonov and Andrey Lyubkin
 */
public class AddIfMin extends CommandWithPars {
    /**
     * @param tr Transporter
     */
    public AddIfMin(Transporter tr) {
        super(tr);
    }

    /**
     * выполнение команды
     * @param res Receiver (объект класса CollectionUnit)
     * @throws FileNotFoundException Не найден файл
     * @throws WrongTypeOfFieldException обработка некорректных типов полей
     */
    @Override
    public void execute(receiver res) throws FileNotFoundException, WrongTypeOfFieldException {
        //transporter.setFields(res);
        this.setFieldsFromTransporter();
        res.addIfMin(name,height,eyeColor,hairColor,nationality,x,y,x1,y1,name1);
        res.addCommandToHistory("add_if_min");
    }
}
