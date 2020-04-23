package ServerPackage.Сommands;


import ServerPackage.IWillNameItLater.Transporter;
import ServerPackage.IWillNameItLater.WrongTypeOfFieldException;
import ServerPackage.IWillNameItLater.receiver;

import java.io.FileNotFoundException;

/**
 * Класс, предоставляющий реализацию команды update, (обновление элемента с заданным id)
 * @author Maxim Antonov and Andrey Lyubkin
 */
public class Update extends CommandWithPars{
    /**
     * @param tr Transporter
     */
    public Update(Transporter tr) {
        super(tr);
    }

    /**
     * выполнение команы
     * @param res Receiver (объект класса CollectionUnit)
     * @throws FileNotFoundException файл не найден
     * @throws WrongTypeOfFieldException обработка некорректных типов полей
     */
    @Override
    public void execute(receiver res) throws FileNotFoundException, WrongTypeOfFieldException {
        if ((this.getTransporter().getIndex()!= -1) && (this.getTransporter().getIndex()!=null)) this.getTransporter().setFields(res);
        this.setFieldsFromTransporter();
        res.update(id, name, height, eyeColor, hairColor, nationality, x, y, x1, y1, name1, transporter.getIndex());
        res.addCommandToHistory("update");
        this.getTransporter().setIndeh();
    }

}