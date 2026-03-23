package Commands;

import CollectionManager.CollectionManager;
import CollectionManager.Invoker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExecuteScriptCommand implements Command {

    private final Invoker invoker;
    private String fileName;

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
            System.out.println("Пример: execute_script script.txt");
            return;
        }

        File scriptFile = new File(fileName);
        if (!scriptFile.exists() || !scriptFile.isFile() || !scriptFile.canRead()) {
            System.out.println("Ошибка: файл не найден, не является файлом или недоступен для чтения");
            System.out.println("Путь: " + scriptFile.getAbsolutePath());
            return;
        }

        System.out.println("Выполняется скрипт: " + fileName);

        try (Scanner fileScanner = new Scanner(scriptFile)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();

                // пропускаем пустые строки и комментарии
                if (line.isEmpty() || line.startsWith("#") || line.startsWith("//")) {
                    continue;
                }

                // разбиваем на команду и аргумент (если есть)
                String[] parts = line.split("\\s+", 2);
                String commandName = parts[0];
                String argument = (parts.length > 1) ? parts[1].trim() : null;

                Command command = invoker.getCommand(commandName);

                if (command == null) {
                    System.out.println("Неизвестная команда в скрипте: " + commandName);
                    continue;
                }

                // передача аргумента, если команда его ожидает
                try {
                    if (argument != null) {
                        if (command instanceof UpdateCommand) {
                            long id = Long.parseLong(argument);
                            ((UpdateCommand) command).setArgument(id);
                        } else if (command instanceof RemoveIdCommand) {
                            long id = Long.parseLong(argument);
                            ((RemoveIdCommand) command).setArgument(id);
//                        } else if (command instanceof RemoveAtCommand) {
//                            int index = Integer.parseInt(argument);
//                            ((RemoveAtCommand) command).setArgument(index);
//                        } else if (command instanceof RemoveAllByDistanceCommand) {
//                            long dist = Long.parseLong(argument);
//                            ((RemoveAllByDistanceCommand) command).setArgument(dist);
//                        } else if (command instanceof CountGreaterThanDistanceCommand) {
//                            long dist = Long.parseLong(argument);
//                            ((CountGreaterThanDistanceCommand) command).setArgument(dist);
//                        } else if (command instanceof FilterLessThanDistanceCommand) {
//                            long dist = Long.parseLong(argument);
//                            ((FilterLessThanDistanceCommand) command).setArgument(dist);
                        }
                        // сюда можно добавить другие команды с аргументами
                    }

                    // ← важная часть: переключаем сканер для add и update
                    if (command instanceof AddCommand) {
                        ((AddCommand) command).setScriptScanner(fileScanner);
                    } else if (command instanceof UpdateCommand) {
                        ((UpdateCommand) command).setScriptScanner(fileScanner);
                    }

                    // выполняем команду
                    command.execute();

                } catch (NumberFormatException e) {
                    System.out.println("Ошибка в скрипте: неверный формат аргумента для команды '" +
                            commandName + "': " + argument);
                } catch (Exception e) {
                    System.out.println("Ошибка при выполнении команды '" + commandName + "': " +
                            e.getMessage());
                } finally {
                    // обязательно сбрасываем сканер после выполнения
                    if (command instanceof AddCommand) {
                        ((AddCommand) command).setScriptScanner(null);
                    } else if (command instanceof UpdateCommand) {
                        ((UpdateCommand) command).setScriptScanner(null);
                    }
                }
            }

            System.out.println("Скрипт выполнен: " + fileName);

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + fileName);
        } catch (Exception e) {
            System.out.println("Ошибка при чтении скрипта: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "считать и исполнить скрипт из указанного файла";
    }
}