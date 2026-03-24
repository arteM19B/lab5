package Commands;

import CollectionManager.Invoker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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

        // Проверка на рекурсию (циклический вызов)
        if (executingScripts.contains(normalizedPath)) {
            System.out.println("Ошибка: обнаружен рекурсивный (циклический) вызов скрипта!");
            System.out.println("Файл уже выполняется: " + fileName);
            return;
        }

        System.out.println("Выполняется скрипт: " + fileName);

        executingScripts.add(normalizedPath);   // Добавляем

        try (Scanner fileScanner = new Scanner(file)) {

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();

                // пропускаем комменты
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
                        ((RemoveAllByDistanceCommand) command).setArgument(Integer.parseInt(arg));
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
}