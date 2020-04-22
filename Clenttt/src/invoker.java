import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * интерфейс, реализуемый классами Terminal, выступает посредником между пользователем и командами
 * @author Maxim Antonov and Andrey Lyubkin
 */
public interface invoker {
    void interactiveMod(String del) throws IOException, SocketException, UnknownHostException;
}
