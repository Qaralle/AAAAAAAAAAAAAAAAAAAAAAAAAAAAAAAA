package ServerPackage;


import ClassCollection.CollectionTask;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import packet.CommandA;
import packet.Person;
import ServerPackage.IWillNameItLater.WrongTypeOfFieldException;
import ServerPackage.IWillNameItLater.receiver;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.sql.*;
import java.util.*;
import java.util.stream.Stream;

public class ServerMain
{

    private static boolean AUTHORIZATIONCHECK=false;
    private static boolean BDCHECKING=false;


    private static String ACCESS;

    private static Log4J2 SustemOut = new Log4J2(System.out);
    private static CollectionTask collectionTask;
    private static receiver CU;
    private static String str;
    private static boolean isRight=false;
    private static List<String> ConnectionKeies= new ArrayList<>();

    private static int cpuCost = (int) Math.pow(2, 14); // factor to increase CPU costs
    private static int memoryCost = 8;      // increases memory usage
    private static int parallelization = 1; // currently not supported by Spring Security
    private static int keyLength = 32;      // key length in bytes
    private static int saltLength = 64;     // salt length in bytes

    private static SCryptPasswordEncoder sCryptPasswordEncoder = new SCryptPasswordEncoder(
            cpuCost,
            memoryCost,
            parallelization,
            keyLength,
            saltLength);


    public static void main(String args[]) throws Exception
    {



    BDconnector bc =new BDconnector();

    try {

        DatagramChannel chan = DatagramChannel.open();
        chan.socket().bind(new InetSocketAddress(1228));
        chan.configureBlocking(false);

        Selector selector = Selector.open();
        chan.register(selector, SelectionKey.OP_READ);
        ByteBuffer buffer = ByteBuffer.allocate(4 * 1024);


        collectionTask = new CollectionTask();
        try {
            collectionTask.load(args[0]);
            CU = new CollectionUnit(collectionTask, args[0]);
        } catch (Exception ex) {
            collectionTask.load("C:\\Users\\proge\\IdeaProjects\\test\\src\\PersonClassTest.json");
            CU = new CollectionUnit(collectionTask, "C:\\Users\\proge\\IdeaProjects\\test\\src\\PersonClassTest.json");
        }

        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {

                SelectionKey key = iter.next();

                if (key.isReadable()) {
                    buffer.clear();
                    buffer.put(new byte[4 * 1024]);
                    buffer.clear();



                    DatagramChannel channel = (DatagramChannel)key.channel();
                    buffer.clear();
                    SustemOut.print("----Соеденение с клментом----");
                    SocketAddress from = channel.receive(buffer);
                    SustemOut.print("----Было успешно установлено----");

                    ByteBuffer finalBuffer = ByteBuffer.allocate(buffer.position());
                    if (getNameCom(buffer,finalBuffer,from).equals("login")){
                        if (getAccess(buffer,finalBuffer,from).equals("DEFAULT")){
                            CheckLogin(bc.getCon(),channel,from,getLogin(buffer,finalBuffer,from),getPass(buffer,finalBuffer,from));
                        } else {

                            SustemOut.addText("Ты уже тута");
                            str = SustemOut.sendTxt()+"\n$";
                            printsmth(channel,from);



                        }
                    }else if(getNameCom(buffer,finalBuffer,from).equals("sign_up")){
                        insert(bc.getCon(),getLogin(buffer,finalBuffer,from),sCryptPasswordEncoder.encode(getPass(buffer,finalBuffer,from)));
                        ACCESS=UUID.randomUUID().toString();
                        ConnectionKeies.add(ACCESS);
                        SustemOut.addText("доступ открыт можете срать&"+ACCESS);
                        str = SustemOut.sendTxt()+"\n$";
                        printsmth(channel,from);

                    } else {


                        for (String Conkey: ConnectionKeies) {
                            if (Conkey.equals(getAccess(buffer, finalBuffer, from).trim())) {
                                action(buffer,finalBuffer,channel,from);
                                AUTHORIZATIONCHECK=true;
                            }

                        }
                        if (AUTHORIZATIONCHECK==false){
                            SustemOut.addText("Пищов нахуй");
                            str = SustemOut.sendTxt()+"\n$";
                            printsmth(channel,from);

                        }
                        AUTHORIZATIONCHECK =false;

                    }

                }
                iter.remove();
            }
        }
    }catch (IllegalAccessError e){
        SustemOut.println("Вийди звiдси розбiйник");
    }


    }


    //dlya zapisi v BD
    private static void insert(Connection con, String Login, String Pass) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO JC_CONTACT (LOGIN,pass) VALUES (?, ?)");
        stmt.setString(1, Login);
        stmt.setString(2, Pass);
        stmt.executeUpdate();
        stmt.close();
    }





    //Dlya proverki logina po pd
    private static void CheckLogin(Connection con, DatagramChannel channel, SocketAddress from, String login, String pass) throws SQLException, IOException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM JC_CONTACT");
        while (rs.next()) {
            if ((rs.getString(2).equals(login))&&(sCryptPasswordEncoder.matches(pass,rs.getString(3)))){
                ACCESS=UUID.randomUUID().toString();
                ConnectionKeies.add(ACCESS);
                SustemOut.addText("доступ открыт можете срать&"+ACCESS);
                str = SustemOut.sendTxt()+"\n$";
                printsmth(channel,from);
                BDCHECKING=true;

            }
        }
        if (BDCHECKING==false){
            SustemOut.addText("Неверный логи или пароль");
            str = SustemOut.sendTxt()+"\n$";
            printsmth(channel,from);

        }
        BDCHECKING=false;
        rs.close();
        stmt.close();

    }



    //hui ego znaet tip perepisivaem 4to poluchii v drugoi buffer
    private static ByteBuffer getFinalBuffer(ByteBuffer buffer, ByteBuffer finalBuffer){
        for (int i = 0; i < buffer.position(); ++i){
            finalBuffer.put(i, buffer.get(i));
        }
        return finalBuffer;
    }



    //poluchenie logina
    private static String getLogin(ByteBuffer buffer,ByteBuffer finalBuffer, SocketAddress from){

        ByteBuffer finalBuffer_=getFinalBuffer(buffer,finalBuffer);
        if (from != null) {
            buffer.flip();
            CommandA cam = deserialize(finalBuffer_.array());
            String val = cam.getLogin();
            return val;
        }
        return null;
    }


    //poluchenie logina
    private static String getPass(ByteBuffer buffer,ByteBuffer finalBuffer, SocketAddress from){

        ByteBuffer finalBuffer_=getFinalBuffer(buffer,finalBuffer);
        if (from != null) {
            buffer.flip();
            CommandA cam = deserialize(finalBuffer_.array());
            String val = cam.getPass();
            return val;
        }
        return null;
    }

    //HZ KAK TUT IZBEJAT DUBLIROVANIya potom eshe podumau
    private static String getAccess(ByteBuffer buffer,ByteBuffer finalBuffer, SocketAddress from){

        ByteBuffer finalBuffer_=getFinalBuffer(buffer,finalBuffer);
        if (from != null) {
            buffer.flip();
            CommandA cam = deserialize(finalBuffer_.array());
            String val = cam.GETACCESS();
            return val;
        }
        return null;
    }

    //polucheniya imya comandi
    public static String getNameCom(ByteBuffer buffer,ByteBuffer finalBuffer, SocketAddress from){
        ByteBuffer finalBuffer_=getFinalBuffer(buffer,finalBuffer);
        if (from != null) {
            buffer.flip();
            CommandA cam = deserialize(finalBuffer_.array());
            String val = cam.toString();
            return val;
        }
        return null;
    }


    //dlya rapoti s kollekciei
    private static void action(ByteBuffer buffer,ByteBuffer finalBuffer, DatagramChannel channel,SocketAddress from) throws IOException, InterruptedException {


        ByteBuffer finalBuffer_=getFinalBuffer(buffer,finalBuffer);
        if (from != null) {
            buffer.flip();
            CommandA cam = deserialize(finalBuffer_.array());
            String val = cam.toString()+cam.getStringToSend();

                if (isRight){
                    val=cam.gettoUpdate_b()+cam.getStringToSend();
                    isRight=false;


                }else if ((val.equals("update")) && (!isRight)){
                    val=cam.gettoUpdate_s();
                    isRight=false;
                }
                if (val.equals("remove_by_id")){
                    val=cam.getToremove();
                }
                if (val.equals("remove_any_by_nationality")){
                    val=cam.getToremovenat();
                }
                if (val.equals("count_less_than_location")){
                    val=cam.getLocat();
                }
                if (val.equals("filter_starts_with_name")){
                    val=cam.getStartWithName();
                }
                if (val.equals("execute_script")){
                    val=cam.getFileName();
                }

                SustemOut.print("----"+from.toString()+"----");
                SustemOut.print("----"+channel.toString()+"----");
                SustemOut.print("----Сервер получил сообщение со стороны клиента: "+ val+"-----");
              //  SustemOut.("Сервер получил сообщение со стороны клиента: "+val);

                try {
                    String userCommand[] = val.split("=");
                    HashMap<String, String> fields = new HashMap<>();
                    if((userCommand[0].equals("update")) && (userCommand.length == 2)){
                        Stream<Person> personStream = CU.getCT().GetCollection().stream();
                        if(personStream.anyMatch(person -> person.getId() == Long.parseLong(userCommand[1]))){
                            SustemOut.addText("Объект с таким id найден"+"\n");
                            isRight=true;
                        }else SustemOut.addText("Объект с таким id не найден"+"\n");
                    }else {
                        for (int i = 1; i < userCommand.length; i += 2) {
                            fields.put(userCommand[i], userCommand[i + 1]);
                            collectionTask.getCommandMap().get(userCommand[0]).getTransporter().SetParams(fields); //костыль чтоб работал, потом переделать нормально (добавить всем тарнспортер)
                        }
                        collectionTask.getCommandMap().get(userCommand[0]).execute(CU);
                    }
                } catch (WrongTypeOfFieldException e) {
                    e.printStackTrace();

                }
                 if(SustemOut.getLast().equals("Объект с таким id найден"+"\n")) str = SustemOut.sendTxt();
                    else str = SustemOut.sendTxt()+"\n$";
                    printsmth(channel,from);

            }
        SustemOut.print( "----Отключение----" );

    }


    //visrat 4to-to clientu
    private static void printsmth(DatagramChannel channel, SocketAddress from) throws IOException {
        SustemOut.clear();
        ByteBuffer lol = ByteBuffer.wrap(str.getBytes());
        channel.send(lol, from);
        CU.setResponse("");
    }



    //tut o4evidno
    private static CommandA deserialize(byte[] data){
        try {
            ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(data));
            CommandA obj = (CommandA) iStream.readObject();
            iStream.close();
            return obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}