import CollectionManager.CollectionManager;
import CollectionManager.Invoker;
import Commands.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Ошибка: укажите имя файла как аргумент командной строки!");
            System.exit(1);
        }

        String fileName = args[0];
        CollectionManager collectionManager = new CollectionManager(fileName);


        Invoker invoker = new Invoker();
        Scanner scanner = new Scanner(System.in);

        Map<String, Command> allCommands = new HashMap<>();

        AddCommand addCommand = new AddCommand(collectionManager, scanner);
        ShowCommand showCommand = new ShowCommand(collectionManager);
        InfoCommand infoCommand = new InfoCommand(collectionManager);
        UpdateCommand updateCommand = new UpdateCommand(collectionManager, scanner);
        RemoveIdCommand removeIdCommand = new RemoveIdCommand(collectionManager);
        ClearCommand clearCommand = new ClearCommand(collectionManager);
        RemoveLastCommand removeLastCommand = new RemoveLastCommand(collectionManager);
        SortCommand sortCommand = new SortCommand(collectionManager);
        SaveCommand saveCommand = new SaveCommand(collectionManager);
        ExitCommand exitCommand = new ExitCommand();
        ExecuteScriptCommand executeScriptCommand = new ExecuteScriptCommand(invoker);
        RemoveAtCommand removeAtCommand = new RemoveAtCommand(collectionManager);


        allCommands.put("add", addCommand);
        allCommands.put("info", infoCommand);
        allCommands.put("show", showCommand);
        allCommands.put("update", updateCommand);
        allCommands.put("remove_by_id", removeIdCommand);
        allCommands.put("clear", clearCommand);
        allCommands.put("remove_last", removeLastCommand);
        allCommands.put("sort", sortCommand);
        allCommands.put("save", saveCommand);
        allCommands.put("exit", exitCommand);
        allCommands.put("execute_script", executeScriptCommand);
        allCommands.put("remove_at", removeLastCommand);


        HelpCommand helpCommand = new HelpCommand(allCommands);

        invoker.registerCommand("help", helpCommand);
        invoker.registerCommand("add", addCommand);
        invoker.registerCommand("info", infoCommand);
        invoker.registerCommand("show", showCommand);
        invoker.registerCommand("update", updateCommand);
        invoker.registerCommand("remove_by_id", removeIdCommand);
        invoker.registerCommand("clear", clearCommand);
        invoker.registerCommand("remove_last", removeLastCommand);
        invoker.registerCommand("sort", sortCommand);
        invoker.registerCommand("save", saveCommand);
        invoker.registerCommand("exit", exitCommand);
        invoker.registerCommand("execute_script", executeScriptCommand);
        invoker.registerCommand("remove_at", removeAtCommand);

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
                } else if (command instanceof ExecuteScriptCommand) {
                    String argument = parts[1];
                    ((ExecuteScriptCommand) command).setArgument(argument);   // ← вот это важно добавить!
                } else if (command instanceof RemoveAtCommand) {
                    int argument = Integer.parseInt(parts[1]);
                    ((RemoveAtCommand) command).setArgument(argument);   // ← вот это важно добавить!
                }
                command.execute();
            }

            if (command == null) {
                System.out.println("Неизвестная команда. Введите 'help' для справки.");
            }
        }



    }
}