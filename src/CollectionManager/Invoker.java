package CollectionManager;

import Commands.Command;

import java.util.HashMap;
import java.util.Map;

public class Invoker {
    private Map<String, Command> commandsMap = new HashMap<>();
    public void registerCommand(String name, Command command) {
            commandsMap.put(name, command);
    }

    public Command getCommand(String name) {
        return commandsMap.get(name);
    }

    public boolean HasCommand(String name) {
        return commandsMap.containsKey(name);
    }
}
