import CollectionManager.CollectionManager;
import CollectionManager.Invoker;
import Commands.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CollectionManager collectionManager = new CollectionManager();
        Invoker invoker = new Invoker();

        Map<String, Command> allCommands = new HashMap<>();

        AddCommand addCommand = new AddCommand(collectionManager, scanner);
        ShowCommand showCommand = new ShowCommand(collectionManager);
        InfoCommand infoCommand = new InfoCommand(collectionManager);
        UpdateCommand updateCommand = new UpdateCommand(collectionManager, scanner);
        RemoveIdCommand removeIdCommand = new RemoveIdCommand(collectionManager);

        allCommands.put("add", addCommand);
        allCommands.put("info", infoCommand);
        allCommands.put("show", showCommand);
        allCommands.put("update", updateCommand);
        allCommands.put("remove_by_id", removeIdCommand);


        HelpCommand helpCommand = new HelpCommand(allCommands);

        invoker.registerCommand("help", helpCommand);
        invoker.registerCommand("add", addCommand);
        invoker.registerCommand("info", infoCommand);
        invoker.registerCommand("show", showCommand);
        invoker.registerCommand("update", updateCommand);
        invoker.registerCommand("remove_by_id", removeIdCommand);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            String[] parts = input.split(" ", 2);
            String commandName = parts[0];
            Command command = invoker.getCommand(commandName);

            if (command != null) {
                if (command instanceof UpdateCommand && parts.length > 1) {
                    try {
                        long id = Long.parseLong(parts[1].trim());
                        ((UpdateCommand) command).setArgument(id);
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный формат ID");
                        continue;
                    }
                } else if (command instanceof RemoveIdCommand && parts.length > 1) {
                    try {
                        long id = Long.parseLong(parts[1].trim());
                        ((RemoveIdCommand) command).setArgument(id);
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный формат ID");
                        continue;
                    }
                }
                command.execute();
            }

            if (command == null) {
                System.out.println("Неизвестная команда. Введите 'help' для справки.");
            }
        }



    }
}