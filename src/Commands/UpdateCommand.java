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
            Route existingRoute = collectionManager.getById(id);
            if (existingRoute == null) {
                System.out.println("Ошибка: Маршрут с ID " + id + " не найден в коллекции.");
                return;
            }

            System.out.println("Введите имя маршрута");
            String name = scanner.nextLine();

            System.out.println("Координата X");
            Long x = Long.parseLong(scanner.nextLine());

            System.out.println("Координата Y");
            Integer y = Integer.parseInt(scanner.nextLine());

            Coordinates coordinates = new Coordinates(x, y);

            System.out.println("Расстояние");
            Long distance = Long.parseLong(scanner.nextLine());

            System.out.println("ОТКУДА");
            System.out.println("Введите (X Y)");
            String line = scanner.nextLine().trim();
            String[] parts1 = line.split("\\s+");
            Float fromX = Float.parseFloat(parts1[0]);
            double fromY = Double.parseDouble(parts1[1]);
            System.out.println("Имя");
            String fromName = scanner.nextLine();

            System.out.println("КУДА");
            System.out.println("Введите X Y");
            line = scanner.nextLine().trim();
            String[] parts2 = line.split("\\s+");
            Float toX = Float.parseFloat(parts2[0]);
            double toY = Double.parseDouble(parts2[1]);
            System.out.println("Имя");
            String toName = scanner.nextLine();

            Location from = new Location(fromX, fromY, fromName);
            Location to = new Location(toX, toY, toName);

            Route newRoute = new Route(name, coordinates, from, to, distance);
            newRoute.setId(id);
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
