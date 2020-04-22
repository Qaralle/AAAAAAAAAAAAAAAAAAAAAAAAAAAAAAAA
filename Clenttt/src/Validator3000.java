import packet.CommandA;
import packet.Country;
import packet.WrongTypeOfFieldException;
import sun.nio.ch.DefaultAsynchronousChannelProvider;

import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
/**
 * Абстрактный класс, выполняющий функци инвокера
 * @author Maxim Antonov and Andrey Lyubkin
 */
public class Validator3000 implements invoker {
    protected Scanner scan;

    private String ACCESS;
    protected String userCommand;
    protected String[] userCommand_;
    //protected receiver res;

    CommandA sentence;
    String key = new String();
    byte[] finalReceiveData;
    byte[] sendData;
    byte[] receiveData = new byte[4*1024];
    DatagramSocket clientSocket;

    protected Map<String, String> bufferMap;
    protected String[] bufferStringForArgs;


    {
        ACCESS="DEFAULT";
        userCommand = "";
        scan=new Scanner(System.in);
    }

    @Override
    public void interactiveMod(String del) throws IOException, SocketException, UnknownHostException {


        System.out.print(del);
        while (true) {


            if (scan.hasNextLine()) {

                try {


                    userCommand = scan.nextLine();
                    userCommand_ = userCommand.trim().split(" ", 3);
                    userCommand_[0] = userCommand_[0].toLowerCase();

                    switch (userCommand_[0]) {

                        case ("add"):
                            if (userCommand_.length==1) {
                                key = "add";
                                CommandA com = new CommandA(key,ACCESS);
                                com.setFields(new Scanner(System.in));
                                sendSmth(com);
                                //add.execute(res);
                            }else {
                                System.out.print("Неверный синтаксис команды. Используйте help."+"\n$");
                            }
                            break;

                        case ("show"):
                            if (userCommand_.length==1) {
                                key = "show";
                                CommandA sendCommand = new CommandA(key,ACCESS);
                                sendSmth(sendCommand);
                            }else {
                                System.out.print("Неверный синтаксис команды. Используйте help."+"\n$");
                            }
                            break;

                        case ("login"):
                            if (userCommand_.length==3) {
                                key = "login";
                                CommandA sendCommand = new CommandA(key,userCommand_[1],userCommand_[2],ACCESS);
                                sendSmth(sendCommand);
                            }else {
                                System.out.print(userCommand_[1]);
                            }
                            break;


                        case ("sign_up"):
                            if (userCommand_.length==3) {
                                key = "sign_up";
                                CommandA sendCommand = new CommandA(key,userCommand_[1],userCommand_[2],ACCESS);
                                sendSmth(sendCommand);
                            }else {
                                System.out.print("Неверный синтаксис команды. Используйте help."+"\n$");
                            }
                            break;


                        case ("info"):
                            if (userCommand_.length==1) {
                                key = "info";
                                CommandA sendCommand = new CommandA(key,ACCESS);
                                sendSmth(sendCommand);
                                //info.execute(res);
                            }else {
                                System.out.print("Неверный синтаксис команды. Используйте help."+"\n$");
                            }
                            break;

                        case ("update"):

                            if (userCommand_.length == 2) {
                                long checkType1 = Long.parseLong(userCommand_[1]);
                                key = "update";
                                CommandA sendCommand = new CommandA(key,checkType1,ACCESS);
                                sendSmth(sendCommand);
                                String s = new String(finalReceiveData);
                                if(s.equals("Объект с таким id найден"+"\n" )){
                                    sendCommand.setFields(new Scanner(System.in));
                                    sendSmth(sendCommand);
                                }
                            }else {
                                System.out.print("Неверный синтаксис команды. Используйте help."+"\n$");
                            }
                            break;

                        case ("clear"):
                            if (userCommand_.length==1) {
                                //clear.execute(res);
                                key = "clear";
                                CommandA sendCommand = new CommandA(key,ACCESS);
                                sendSmth(sendCommand);
                                //sendSmth(key);
                            }else {
                                System.out.print("Неверный синтаксис команды. Используйте help."+"\n$");
                            }
                            break;


                        case ("remove_by_id"):
                            if(userCommand_.length == 2) {
                                long checkType = Long.parseLong(userCommand_[1]);

                                key = "remove_by_id";
                                CommandA sendCommand = new CommandA(key,checkType,ACCESS);
                                sendSmth(sendCommand);
                                //sendSmth(key+"="+"id"+"="+userCommand_[1]);
                            }else {
                                System.out.print("Неверный синтаксис команды. Используйте help."+"\n$");
                            }
                            break;


                        case ("remove_head"):
                            if (userCommand_.length==1) {
                                //removeHead.execute(res);
                                key = "remove_head";
                                CommandA sendCommand = new CommandA(key,ACCESS);
                                sendSmth(sendCommand);
                            }else {
                                System.out.print("Неверный синтаксис команды. Используйте help."+"\n$");
                            }
                            break;


                        case ("remove_any_by_nationality"):
                            if(userCommand_.length == 2) {
                                userCommand_[1] = userCommand_[1].toUpperCase();
                                Country checkType2 = Country.valueOf(userCommand_[1]);

                                key = "remove_any_by_nationality";
                                CommandA sendCommand = new CommandA(key, checkType2,ACCESS);
                                sendSmth(sendCommand);
                                //sendSmth(key+"="+"nationality"+"="+userCommand_[1]);
                            }else {
                                System.out.print("Неверный синтаксис команды. Используйте help."+"\n$");
                            }
                            break;


                        case ("count_less_than_location"):
                            if(userCommand_.length == 2) {

                                key = "count_less_than_location";
                                CommandA sendCommand = new CommandA(key, userCommand_[1],1,ACCESS);
                                sendSmth(sendCommand);

                            }else {
                                System.out.print("Неверный синтаксис команды. Используйте help."+"\n$");
                            }
                            break;


                        case ("filter_starts_with_name"):
                            if(userCommand_.length == 2) {
                                key = "filter_starts_with_name";
                                CommandA sendCommand = new CommandA(key, userCommand_[1],ACCESS);
                                sendSmth(sendCommand);

                                //sendSmth(key+"="+"name"+"="+userCommand_[1]);
                            }else {
                                System.out.print("Неверный синтаксис команды. Используйте help."+"\n$");
                            }
                            break;


                        case ("save"):
                            if (userCommand_.length==1) {

                                key = "save";
                                CommandA sendCommand = new CommandA(key,ACCESS);
                                sendSmth(sendCommand);
                            }else {
                                System.out.print("Неверный синтаксис команды. Используйте help."+"\n$");
                            }
                            break;


                        case ("execute_script"):
                            if(userCommand_.length == 2) {
                                //executeScript.getTransporter().SetParams(bufferMap);
                                //executeScript.execute(res);
                                File file = new File(userCommand_[1]);
                                if (file.exists()==false){
                                    throw new FileNotFoundException();
                                }
                                key = "execute_script";
                                CommandA sendCommand = new CommandA(key,userCommand_[1],ACCESS);
                                sendSmth(sendCommand);
                            }else {
                                System.out.print("Неверный синтаксис команды. Используйте help."+"\n$");
                            }
                            break;

                        case ("exit"):
                            if (userCommand_.length==1) {
                                //exit.execute(res);
                                key = "exit";
                                CommandA sendCommand = new CommandA(key,ACCESS);

                                try {
                                    sendSmth(sendCommand);
                                } catch (SocketTimeoutException ex){
                                    System.exit(0);
                                }
                            }else {
                                System.out.print("Неверный синтаксис команды. Используйте help."+"\n$");
                            }
                            break;





                        case ("history"):
                            if (userCommand_.length==1) {
                                //history.execute(res);
                                key = "history";
                                CommandA sendCommand = new CommandA(key,ACCESS);
                                sendSmth(sendCommand);
                            }else {
                                System.out.print("Неверный синтаксис команды. Используйте help."+"\n$");
                            }
                            break;


                        case ("add_if_min"):
                            if (userCommand_.length==1) {
                                //addIfMin.execute(res);
                                key = "add_if_min";
                                CommandA com = new CommandA(key,ACCESS);
                                com.setFields(new Scanner(System.in));
                                sendSmth(com);
                            }else {
                                System.out.print("Неверный синтаксис команды. Используйте help."+"\n$");
                            }
                            break;


                        case ("help"):
                            if (userCommand_.length==1) {
                                //help.execute(res);
                                key = "help";
                                CommandA sendCommand = new CommandA(key,ACCESS);
                                sendSmth(sendCommand);

                            }else {
                                System.out.print("Неверный синтаксис команды. Используйте help."+"\n$");
                            }
                            break;


                        default:
                            if (!userCommand_[0].equals("")) {
                                System.out.print("Кажется, что-то пошло не так. Чтобы посмотреть доступные команды, используйте 'help'"+"\n$");
                            }else System.out.print("$");
                            break;


                    }
                } catch (NumberFormatException ex) {
                    System.out.print("Неподходящее значения для поля\n$");


                } catch (FileNotFoundException ex) {
                    System.out.print("Файл не найден\n$");

                } catch (WrongTypeOfFieldException e) {
                    e.printStackTrace();
                }catch (SocketTimeoutException ex){
                    System.out.println("Превышен интервал ожидания");
                    System.out.print("Попробуйте заново"+"\n$");
                }

            }else break;


        }


    }
    private byte[] serialaze(CommandA ob){

        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream(2048);
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(ob);
            oos.close();
            byte[] obj = baos.toByteArray();
            baos.close();
            return obj;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    private void sendSmth(CommandA data) throws IOException {
        clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(null);
        sentence = data;
        sendData = serialaze(data);
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1228);
        clientSocket.send(sendPacket);
        clientSocket.setSoTimeout(50000000);
        String str_ = getSmth();
        System.out.print(str_);

        if (str_.indexOf("&") != -1){
            ACCESS=Optional.ofNullable(str_.split("&",2)[1])
                    .filter(str -> str.length() != 0)
                    .map(str -> str.substring(0, str.length() - 1))
                    .orElse(str_.split("&",2)[1]);


        }

    }
    private String getSmth() throws IOException {
        DatagramPacket pack = new DatagramPacket(receiveData,receiveData.length);
        clientSocket.receive(pack);
        int size = pack.getLength();
        finalReceiveData = new byte[size];
        for (int i = 0; i < size; ++i){
            finalReceiveData[i] = receiveData[i];
        }
        return new String(finalReceiveData);
    }

}
