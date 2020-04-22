package ServerPackage;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BDconnector {
    private String strSshUser = "s283809"; // SSH loging username
    private String strSshPassword = "hzn178"; // SSH login password
    private String strSshHost = "se.ifmo.ru"; // hostname or ip or SSH server
    private int nSshPort = 2222; // remote SSH host port number
    private String strRemoteHost = "pg"; // hostname or ip of your database server
    private int nLocalPort = 3745; // local port number use to bind SSH tunnel
    private int nRemotePort = 5432; // remote port number of your database
    private String strDbUser = "s283809"; // database loging username
    private String strDbPassword = "hzn178"; // database login password
    private Connection con;

    {
        try {
            this.doSshTunnel(strSshUser, strSshPassword, strSshHost, nSshPort, strRemoteHost, nLocalPort, nRemotePort);
        } catch (JSchException e) {
            e.printStackTrace();
        }
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.con = DriverManager.getConnection("jdbc:postgresql://localhost:"+nLocalPort+"/studs", strDbUser, strDbPassword);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void doSshTunnel( String strSshUser, String strSshPassword, String strSshHost, int nSshPort, String strRemoteHost, int nLocalPort, int nRemotePort ) throws JSchException
    {
        final JSch jsch = new JSch();
        Session session = jsch.getSession( strSshUser, strSshHost, 2222 );
        session.setPassword( strSshPassword );

        final Properties config = new Properties(); //Properties – это подкласс Hashtable. Он используется для хранения списков значени
        config.put( "StrictHostKeyChecking", "no" ); //отключаем запрос о доверие серверу
        session.setConfig( config );

        session.connect();
        session.setPortForwardingL(nLocalPort, strRemoteHost, nRemotePort); //регестрирует переадресацию локального порта для обмена связями
    }
    public Connection getCon(){
        return con;
    }
}
