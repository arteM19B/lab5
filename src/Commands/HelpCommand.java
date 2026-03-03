package Commands;
import java.util.Map;

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
