package Collection;

import java.util.Scanner;
/**
 * Класс-помощник для интерактивного и скриптового создания объектов {@link Route}.
 * Отвечает за чтение данных от пользователя с валидацией вводимых значений.
 *
 * @author artem_bahetkin
 * @version 1.0
 */
public class RouteBuilder {
    private final Scanner scanner;
    private final boolean isConsole;

    public RouteBuilder(Scanner scanner, boolean isConsole) {
        this.scanner = scanner;
        this.isConsole = isConsole;
    }

    public Route build() {
        String name = readName();
        Coordinates coordinates = readCoordinates();
        Location from = readLocation("откуда (from)", true);
        Location to = readLocation("куда (to)", true);
        long distance = readDistance();

        return new Route(name, coordinates, from, to, distance);
    }

    private String readName() {
        while (true) {
            printConsole("Введите имя маршрута (нужно хоть что-то ввести): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                printError("Имя не может быть пустым");
                continue;
            }
            return input;
        }
    }

    private Coordinates readCoordinates() {
        while (true) {
            printConsole("Координата X (Long, > -248): ");
            String xStr = scanner.nextLine().trim();
            printConsole("Координата Y (Integer, > -448): ");
            String yStr = scanner.nextLine().trim();

            try {
                long x = Long.parseLong(xStr);
                int y = Integer.parseInt(yStr);

                if (x <= -248) {
                    printError("X должен быть больше -248");
                    continue;
                }
                if (y <= -448) {
                    printError("Y должен быть больше -448");
                    continue;
                }

                return new Coordinates(x, y);
            } catch (NumberFormatException e) {
                printError("Неверный формат числа");
            }
        }
    }

    private long readDistance() {
        while (true) {
            printConsole("Расстояние (long, > 1): ");
            String input = scanner.nextLine().trim();
            try {
                long dist = Long.parseLong(input);
                if (dist <= 1) {
                    printError("Расстояние должно быть больше 1");
                    continue;
                }
                return dist;
            } catch (NumberFormatException e) {
                printError("Неверный формат числа");
            }
        }
    }

    private Location readLocation(String promptText, boolean canBeNull) {
        printConsole("Локация " + promptText + (canBeNull ? " (Enter для null)" : "") + "\n");

        printConsole("  Введите X Y (Float double) или Enter для null: ");
        String line = scanner.nextLine().trim();

        if (line.isEmpty() && canBeNull) {
            return null;
        }

        if (line.isEmpty()) {
            printError("Поле не может быть пустым");
            return readLocation(promptText, canBeNull); // повтор
        }

        String[] parts = line.split("\\s+");
        if (parts.length != 2) {
            printError("Ожидается два числа: X Y");
            return readLocation(promptText, canBeNull);
        }

        try {
            float x = Float.parseFloat(parts[0]);
            double y = Double.parseDouble(parts[1]);

            printConsole("  Имя локации (или Enter для null): ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) name = null;

            return new Location(x, y, name);
        } catch (NumberFormatException e) {
            printError("Неверный формат координат");
            return readLocation(promptText, canBeNull);
        }
    }

    private void printConsole(String msg) {
        if (isConsole) {
            System.out.print(msg);
        }
    }

    private void printError(String msg) {
        if (isConsole) {
            System.out.println("Ошибка: " + msg + ". Повторите ввод.");
        } else {
            throw new IllegalArgumentException(msg);
        }
    }

}
