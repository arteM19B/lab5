package Commands;
import Collection.Coordinates;
import Collection.Location;
import Collection.Route;
import CollectionManager.CollectionManager;


import java.util.Scanner;

public class AddCommand  implements Command{
    private CollectionManager collectionManager;
    private Scanner scanner;

    public AddCommand(CollectionManager collectionManager, Scanner scanner) {
        this.collectionManager = collectionManager;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        try {
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

            Route route = new Route(name, coordinates, from, to, distance);
            collectionManager.add(route);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "добавить новый элемент в коллекцию";
    }
}
