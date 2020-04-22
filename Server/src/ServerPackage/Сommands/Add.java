package ServerPackage.Сommands;


import ServerPackage.IWillNameItLater.Transporter;
import ServerPackage.IWillNameItLater.WrongTypeOfFieldException;
import ServerPackage.IWillNameItLater.receiver;

import java.io.FileNotFoundException;

/**
 * Класс, реализующий комманду Add (добавление элемента в коллекцию)
 * @author Maxim Antonov and Andrey Lyubkin
 */
public class Add extends CommandWithPars{

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
        res.add(name,height,eyeColor,hairColor,nationality,x,y,x1,y1,name1);
        res.addCommandToHistory("add");
    }

    /**
     * @param tr Transporter
     */
    public Add(Transporter tr){
        super(tr);
    }

}
