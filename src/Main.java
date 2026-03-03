import CollectionManager.CollectionManager;
import CollectionManager.Invoker;

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

        allCommands.put("add", addCommand);
        allCommands.put("info", infoCommand);
        allCommands.put("show", showCommand);

        HelpCommand helpCommand = new HelpCommand(allCommands);

        invoker.registerCommand("help", helpCommand);
        invoker.registerCommand("add", addCommand);
        invoker.registerCommand("info", infoCommand);
        invoker.registerCommand("show", showCommand);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            String[] parts = input.split(" ", 2);
            String commandName = parts[0];


            Command command = invoker.getCommand(commandName);

            if (command == null) {
                System.out.println("Неизвестная команда. Введите 'help' для справки.");
                continue;
            }

            // Выполняем команду
            command.execute();
        }



    }
}