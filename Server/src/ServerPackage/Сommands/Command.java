package ServerPackage.Сommands;

import ServerPackage.IWillNameItLater.Transporter;
import ServerPackage.IWillNameItLater.WrongTypeOfFieldException;
import ServerPackage.IWillNameItLater.receiver;

import java.io.FileNotFoundException;


/**
 * Interface, реализуемый всеми командами
 * @author Maxim Antonov and Andrey Lyubkin
 */
public interface Command {
     /**
      * Выполнение команды
      * @param res Receiver (объект класса CollectionUnit)
      * @throws FileNotFoundException Не найден файл
      * @throws WrongTypeOfFieldException обработка некорректных типов полей
      */
     void execute(receiver res) throws FileNotFoundException, WrongTypeOfFieldException;
     default void setFieldsFromTransporter(){ }
     default Transporter getTransporter(){return null;}
}
