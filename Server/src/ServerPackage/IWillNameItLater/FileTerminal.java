package ServerPackage.IWillNameItLater;

import ServerPackage.Сommands.*;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Класс, предоставляющий терминал для работы с файлами
 * @author Maxim Antonov and Andrey Lyubkin
 */
public class FileTerminal extends Terminal {
    private Scanner scan;
    private String file_name;
    private static List<String> files_names=new ArrayList<>();
    private Iterator<String> it = files_names.iterator();
    private static boolean i=false;


    /**
     * @param file_name_ имя фалйа
     * @param scan объект класса Scanner
     * @param rec_ Receiver
     * @throws FileNotFoundException файл не найден
     */
    public FileTerminal(String file_name_, Scanner scan, receiver rec_ ) throws FileNotFoundException {
        super(rec_,scan);

        this.file_name=file_name_;
        this.scan=scan;
        add=new Add(new FileTransporter(scan));
        show=new Show();
        info=new Info();
        update=new Update(new FileTransporter(scan));
        clear=new Clear();
        remove_by_id=new RemoveById(new FileTransporter(scan));
        removeHead=new RemoveHead();
        removeAnyByNationality=new RemoveAnyByNationality(new FileTransporter(scan));
        countLessThanLocation=new CountLessThanLocation(new FileTransporter(scan));
        filterStartsWithName=new FilterStartsWithName(new FileTransporter(scan));
        save=new Save();
        bufferMap = new HashMap<>();

        executeScript = new ExecuteScript(new FileTransporter(scan));

        exit=new Exit();
        history=new History();
        addIfMin = new AddIfMin(new FileTransporter(scan));
        help=new Help();


        /*
        !!
        !!
        НЕ ЕБУ ЧТО С НИМ ДЕЛАТЬ, ПОКА ОН НЕ ИСПОЛЬЗУЕТСЯ, ЕГО ПОТОМ ДОРАБОТАЕМ
        !!
        !!
         */

        if (files_names.size()>0) {

            for (int j=0; j<files_names.size();j++) {

                if (file_name.equals(files_names.get(j))) {
                    System.out.println("Упс! Не балуй!!!!");
                    i = false;
                    while (it.hasNext()){
                        String st= it.next();
                            it.remove();
                            break;
                        }

                } else i=true;

            }
        }else i=true;

        files_names.add(file_name);

        if (i==true) {
            this.startWorking("");
            files_names.clear();
        }


    }



}
