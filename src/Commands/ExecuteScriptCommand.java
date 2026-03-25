package Commands;

import CollectionManager.Invoker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
/**
 * Команда выполнения скрипта.
 * Считывает и последовательно выполняет команды из указанного файла.
 *
 * @author artem_bahetkin
 * @version 1.0
 */
public class ExecuteScriptCommand implements Command {

    private final Invoker invoker;
    private String fileName;

    private static final Set<String> executingScripts = new HashSet<>();

    public ExecuteScriptCommand(Invoker invoker) {
        this.invoker = invoker;
    }

    public void setArgument(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        if (fileName == null || fileName.trim().isEmpty()) {
            System.out.println("Ошибка: укажите имя файла скрипта");
            return;
        }

        File file = new File(fileName);
        String normalizedPath;
        try {
            normalizedPath = file.getCanonicalPath();
        } catch (Exception e) {
            normalizedPath = file.getAbsolutePath();
        }

        if (executingScripts.contains(normalizedPath)) {
            System.out.println("Ошибка: обнаружен рекурсивный (циклический) вызов скрипта!");
            System.out.println("Файл уже выполняется: " + fileName);
            return;
        }

        System.out.println("Выполняется скрипт: " + fileName);

        executingScripts.add(normalizedPath);

        try (Scanner fileScanner = new Scanner(file)) {

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();

                if (line.isEmpty() || line.startsWith("#") || line.startsWith("//")) {
                    continue;
                }

                String[] parts = line.split("\\s+", 2);
                String cmdName = parts[0];
                String arg = (parts.length > 1) ? parts[1].trim() : null;

                Command command = invoker.getCommand(cmdName);

                if (command == null) {
                    System.out.println("Неизвестная команда в скрипте: " + cmdName);
                    continue;
                }

                if (arg != null) {
                    if (command instanceof UpdateCommand) {
                        ((UpdateCommand) command).setArgument(Long.parseLong(arg));
                    } else if (command instanceof RemoveIdCommand) {
                        ((RemoveIdCommand) command).setArgument(Long.parseLong(arg));
                    } else if (command instanceof RemoveAtCommand) {
                        ((RemoveAtCommand) command).setArgument(Integer.parseInt(arg));
                    } else if (command instanceof RemoveAllByDistanceCommand) {
                        ((RemoveAllByDistanceCommand) command).setArgument(Long.parseLong(arg));
                    } else if (command instanceof CounGreaterThanDistanceCommand) {
                        ((CounGreaterThanDistanceCommand) command).setArgument(Long.parseLong(arg));
                    } else if (command instanceof FilterLessThanDistanceCommand) {
                        ((FilterLessThanDistanceCommand) command).setArgument(Long.parseLong(arg));
                    } else if (command instanceof ExecuteScriptCommand) {
                        ((ExecuteScriptCommand) command).setArgument(arg);
                    }
                }

                if (command instanceof AddCommand) {
                    ((AddCommand) command).setScriptScanner(fileScanner);
                } else if (command instanceof UpdateCommand) {
                    ((UpdateCommand) command).setScriptScanner(fileScanner);
                }

                command.execute();

                if (command instanceof AddCommand) ((AddCommand) command).setScriptScanner(null);
                if (command instanceof UpdateCommand) ((UpdateCommand) command).setScriptScanner(null);
            }

            System.out.println("Скрипт успешно завершён: " + fileName);

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + fileName);
        } catch (Exception e) {
            System.out.println("Ошибка выполнения скрипта: " + e.getMessage());
        } finally {
            executingScripts.remove(normalizedPath);
        }
    }

    @Override
    public String toString() {
        return "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}