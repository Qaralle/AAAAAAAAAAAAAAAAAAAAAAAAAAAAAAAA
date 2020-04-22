package ServerPackage;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

public class Log4J2 extends PrintStream {

    public static List<String> printList = new LinkedList<>();

    public Log4J2(PrintStream org) {
        super(org);
    }

    @Override
    public void println(String line) {
        printList.add(line);
        super.println(line);
    }

    public String getLast() {
        if (printList.isEmpty()){
            return "";
        }else return printList.get(printList.size() - 1);
    }

    @Override
    public void print(String line) {
        super.print(line+"\n");


    }
    public void addText(String line) {
        printList.add(line);
    }

    public String sendTxt(){
        int i=0;
        String res="";
        for (String s: printList){
           if (i != printList.size()-1) {
               res = res + s + "\n";
           }
            else {res = res + s;}
            i++;
        }
        return res;
    }




    public void clear(){
        printList.clear();
    }

}