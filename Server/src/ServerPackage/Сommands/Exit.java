package ServerPackage.Сommands;

import ServerPackage.IWillNameItLater.receiver;

/**
 * Класс, предоставляющий реализацию команды exit, (выход из программы)
 * @author Maxim Antonov and Andrey Lyubkin
 */
public class Exit implements Command {
    @Override
    public void execute(receiver res) {
        res.exit();
        res.addCommandToHistory("exit");
    }
}
