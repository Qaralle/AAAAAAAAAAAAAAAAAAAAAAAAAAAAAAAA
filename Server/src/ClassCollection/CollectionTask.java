package ClassCollection;

import ServerPackage.BDconnector;
import ServerPackage.PersonList;
import packet.*;
import ServerPackage.IWillNameItLater.ConsoleTransporter;
import ServerPackage.Сommands.*;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import javax.swing.plaf.nimbus.State;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Stream;

/**
 * Класс, создающий коллекцию из Json.
 * @author Maxim Antonov and Andrey Lyubkin
 * @version 1231.1231.213546.1(alpha)
 */



public class CollectionTask {

    private CommandWithPars add;
    private Command show;
    private Command info;
    private CommandWithPars update;
    private Command clear;
    private CommandWithPars remove_by_id;
    private Command removeHead;
    private CommandWithPars removeAnyByNationality;
    private CommandWithPars countLessThanLocation;
    private CommandWithPars filterStartsWithName;
    private Command save;
    private ExecuteScript executeScript;
    private Command exit;
    private Command history;
    private CommandWithPars addIfMin;
    private Command help;
    
    private PersonList collection;
    //private Gson serializer;
    private FieldPolice fp;
    private NullPolice np;
    private String dateInit;
    private String[] historyOfCommands;

    private Person p;;

    private Map<String, Command> commandMap;

    {
        //nullPolice = new NullPolice();
        //serializer = new Gson();
        collection = new PersonList();
        fp=new FieldPolice();
        np=new NullPolice();

        Calendar calendar = Calendar.getInstance();
        dateInit = calendar.get(Calendar.DAY_OF_MONTH) +".";
        dateInit += (calendar.get(Calendar.MONTH) + 1) +".";
        dateInit += Integer.toString(calendar.get(Calendar.YEAR));
        historyOfCommands = new String[8];

        add=new Add(new ConsoleTransporter());
        show=new Show();
        info=new Info();
        update=new Update(new ConsoleTransporter());
        clear=new Clear();
        remove_by_id=new RemoveById(new ConsoleTransporter());
        removeHead=new RemoveHead();
        removeAnyByNationality=new RemoveAnyByNationality(new ConsoleTransporter());
        countLessThanLocation=new CountLessThanLocation(new ConsoleTransporter());
        filterStartsWithName=new FilterStartsWithName(new ConsoleTransporter());
        save=new Save();
        executeScript = new ExecuteScript(new ConsoleTransporter());
        exit=new Exit();
        history=new History();
        addIfMin = new AddIfMin(new ConsoleTransporter());
        help=new Help();

        commandMap = new HashMap<>();
        commandMap.put("add", add);
        commandMap.put("info", info);
        commandMap.put("filter_starts_with_name", filterStartsWithName);
        commandMap.put("add_if_min", addIfMin);
        commandMap.put("clear", clear);
        commandMap.put("help", help);
        commandMap.put("history", history);
        commandMap.put("exit", exit);
        commandMap.put("execute_script", executeScript);
        commandMap.put("update", update);
        commandMap.put("remove_any_by_nationality", removeAnyByNationality);
        commandMap.put("remove_head", removeHead);
        commandMap.put("remove_by_id", remove_by_id);
        commandMap.put("save", save);
        commandMap.put("show", show);
        commandMap.put("count_less_than_location", countLessThanLocation);
    }


    public void load(BDconnector bd){
        /*Scanner scanner = new Scanner(new File(pathname));
        System.out.println("Collection loading");
        StringBuffer data = new StringBuffer();
        while (scanner.hasNext()) {
            data.append(scanner.nextLine()).append("\n");
        }
        Type collectionType = new TypeToken<PersonList>() {}.getType();
        try {
            PersonList addedPerson = serializer.fromJson(data.toString(), collectionType);


            for (Person s : addedPerson) {
                Objects.requireNonNull(s.getName());
                Objects.requireNonNull(s.getCoordinates());
                Objects.requireNonNull(s.getHeight());
                Objects.requireNonNull(s.getEyeColor());
                Objects.requireNonNull(s.getHairColor());
                Objects.requireNonNull(s.getNationality());
                Objects.requireNonNull(s.getLocation());
                if (!collection.contains(s)){
                    fp.ReplaceEverything(s, s.getLocation(), s.getCoordinates());
                    collection.add(s);
                }

            }
            System.out.println("Коллекций успешно загружена");
        } catch (JsonSyntaxException e ){
            System.out.println("Ошибка синтаксиса файл json!");
            System.exit(0);
        } catch (NullPointerException e){
            //System.out.println("У одного из объектов null поле будет перезаписано автоматически");
            PersonList addedShorty = serializer.fromJson(data.toString(), collectionType);
            for (Person s : addedShorty) {
                np.ReplaceEverything(s, s.getCoordinates());
                fp.ReplaceEverything(s, s.getLocation(), s.getCoordinates());
                if (!collection.contains(s)) collection.add(s);


            }
            System.out.println("Коллекций успешно загружена");
        }*/
        Connection connection = bd.getCon();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from collection");
            while(resultSet.next()){
                p = new Person();
                p.setID(resultSet.getInt("id"));
                String name = resultSet.getString("name");
                Coordinates coo = new Coordinates(resultSet.getFloat("x"), resultSet.getDouble("y"));
                double height = resultSet.getDouble("height");
                Location loc = new Location(resultSet.getFloat("locationx"), resultSet.getDouble("locationy"), resultSet.getString("locationname"));
                Country nationality = null;
                Color eyeColor = null;
                Color hairColor = null;
                try {
                    nationality = Country.valueOf(resultSet.getString("country").toUpperCase());
                }catch (NullPointerException ex){}
                try {
                    eyeColor = Color.valueOf(resultSet.getString("eyecolor").toUpperCase());
                }catch (NullPointerException ex) {}
                try {
                    hairColor = Color.valueOf(resultSet.getString("haircolor").toUpperCase());
                }catch (NullPointerException ex) {}
                p.setEverything(name, coo, height, eyeColor, hairColor, nationality, loc);
                if(!collection.contains(p)) {
                    np.ReplaceEverything(p, p.getCoordinates(), p.getLocation());
                    fp.ReplaceEverything(p, p.getLocation(), p.getCoordinates());
                    collection.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException ex){
            System.out.println("fff");
        }


    }

    public PersonList GetCollection(){
        return collection;
    }

    /**
     * Метод, осуществляющий запись элемента в коллекцию
     * @param p1 Объект класса Person
     */
    public void add(Person p1){
        collection.add(p1);
    }

    /**
     * Метод, осуществляющий сортировку коллекции
     */
    public void CollectionSort(){
        Collections.sort(collection,new CompareCenter());
    }

    public String getDateInit(){ return dateInit;}

    public String[] getHistoryOfCommands(){return historyOfCommands;}

    public Map<String, Command> getCommandMap(){ return commandMap;}
}

