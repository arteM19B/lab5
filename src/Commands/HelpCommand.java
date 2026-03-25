package Commands;
import java.util.Map;
/**
 * Команда вывода справки по доступным командам.
 * Выводит список всех зарегистрированных команд и их описания.
 *
 * @author artem_bahetkin
 * @version 1.0
 */
public class HelpCommand implements Command {
    private Map<String, Command> commands;
    public HelpCommand(Map<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        for (Map.Entry<String, Command> entry : commands.entrySet()) {
            System.out.println(entry.getKey() + " -- " + entry.getValue());
        }
    }

//    @Override
//    public String toString() {
//
//    }
}
