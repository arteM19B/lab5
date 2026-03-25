package Commands;
import Collection.Coordinates;
import Collection.IdGenerator;
import Collection.Location;
import Collection.Route;
import CollectionManager.CollectionManager;


import java.util.Scanner;
/**
 * Команда добавления нового элемента в коллекцию.
 * Добавляет новый маршрут, запрашивая все необходимые поля у пользователя.
 *
 * @author artem_bahetkin
 * @version 1.0
 */
public class AddCommand  implements Command{
    private final CollectionManager collectionManager;
    private final Scanner scanner;
    private Scanner scriptScanner;

    public AddCommand(CollectionManager collectionManager, Scanner scanner) {
        this.collectionManager = collectionManager;
        this.scanner = scanner;
        this.scriptScanner = null;
    }

    public void setScriptScanner(Scanner scriptScanner) {
        this.scriptScanner = scriptScanner;
    }

    @Override
    public void execute() {
        Scanner activeScanner = (scriptScanner != null) ? scriptScanner : scanner;
        boolean isConsole = (scriptScanner == null);
        try {
            RouteBuilder builder = new RouteBuilder(activeScanner, isConsole);
            Route route = builder.build();
            collectionManager.add(route);
        } catch (Exception e) {
            System.out.println("Не удалось добавить маршрут: " + e.getMessage());
        } finally {
            scriptScanner = null;
        }
    }

    @Override
    public String toString() {
        return "добавить новый элемент в коллекцию";
    }
}
