package ServerPackage.IWillNameItLater;

import ClassCollection.CollectionTask;
import packet.*;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interface, реализуемый классом CollectionUnit, выступает как получатель/реализатор команд от пользователя
 * @author Maxim Antonov and Andrey Lyubkin
 */
public interface receiver {
    void add(String name_, Double height_, Color eyeColor_, Color hairColor_, Country nationality_, Float x_, Double y_, Float x1_, double y1_, String name1_);
    void show();
    void info();
    void update(long id, String name_, Double height_, Color eyeColor_, Color hairColor_, Country nationality_, Float x_, Double y_, Float x1_, double y1_, String name1_, Integer index);
    void clear();
    void remove_by_id(long id);
    void removeHead();
    void removeAnyByNationality(Country nationality); //разработчики осуждают нацизм в любых его проявлениях
    void countLessThanLocation(String namel);
    void filterStartsWithName(String name);
    void save() throws IOException;
    void executeScript(String file_name) throws FileNotFoundException;
    void exit();
    void history();
    void addIfMin(String name_, Double height_, Color eyeColor_, Color hairColor_, Country nationality_, Float x_, Double y_, Float x1_, double y1_, String name1_);
    void help();

    CollectionTask getCT();
    void addCommandToHistory(String userCommand);
    String getResponse();
    void setResponse(String str);
}
