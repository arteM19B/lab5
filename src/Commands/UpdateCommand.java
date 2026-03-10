package Commands;

import CollectionManager.CollectionManager;
import Collection.*;
import java.util.Scanner;

public class UpdateCommand implements Command {
    private CollectionManager collectionManager;
    private long id;
    private final Scanner scanner;


    public UpdateCommand(CollectionManager collectionManager, Scanner scanner) {
        this.collectionManager = collectionManager;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        try {
            // 1. Запрашиваем ID элемента, который нужно обновить
            System.out.println("Введите ID маршрута для обновления:");
            long id = Long.parseLong(scanner.nextLine().trim());

            // 2. Собираем данные для НОВОГО маршрута (аналогично AddCommand)
            System.out.println("Введите новое имя маршрута:");
            String name = scanner.nextLine();

            System.out.println("Введите координату X:");
            Long x = Long.parseLong(scanner.nextLine());

            System.out.println("Введите координату Y:");
            Integer y = Integer.parseInt(scanner.nextLine());

            Coordinates coordinates = new Coordinates(x, y);

            System.out.println("Введите расстояние:");
            Long distance = Long.parseLong(scanner.nextLine());

            // Ввод точки "Откуда"
            System.out.println("Откуда (X Y Имя):");
            String line = scanner.nextLine().trim();
            String[] parts1 = line.split("\\s+");
            Float fromX = Float.parseFloat(parts1[0]);
            double fromY = Double.parseDouble(parts1[1]);
            String fromName = parts1.length > 2 ? parts1[2] : scanner.nextLine();

            // Ввод точки "Куда"
            System.out.println("Куда (X Y Имя):");
            line = scanner.nextLine().trim();
            String[] parts2 = line.split("\\s+");
            Float toX = Float.parseFloat(parts2[0]);
            double toY = Double.parseDouble(parts2[1]);
            String toName = parts2.length > 2 ? parts2[2] : scanner.nextLine();

            Location from = new Location(fromX, fromY, fromName);
            Location to = new Location(toX, toY, toName);

            // 3. Создаем новый объект Route
            Route newRoute = new Route(name, coordinates, from, to, distance);

            // Устанавливаем тот же ID, чтобы не потерять идентификатор в коллекции
            newRoute.setId(id);

            // 4. Вызываем метод обновления с обоими параметрами
            collectionManager.update(id, newRoute);

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: неверный формат числа.");
        } catch (Exception e) {
            System.out.println("Ошибка при обновлении: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "обновить значение элемента коллекции, id которого равен заданному";
    }

    public void setArgument(long id) {
        this.id = id;
    }
}
