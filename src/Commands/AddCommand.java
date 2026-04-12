package Commands;
import Collection.Route;
import Collection.RouteBuilder;
import CollectionManager.CollectionManager;
import Exceptions.ExitException;


import java.util.Scanner;
/**
 * Команда добавления нового элемента в коллекцию.
 * Добавляет новый маршрут, запрашивая все необходимые поля у пользователя.
 *
 * @author artem_bahetkin
 * @version 1.0
 */
public class AddCommand implements Command{
    private final CollectionManager collectionManager;
    private final Scanner scanner;
    private Scanner scriptScanner;
    private Route routeFromXml = null;

    public AddCommand(CollectionManager collectionManager, Scanner scanner) {
        this.collectionManager = collectionManager;
        this.scanner = scanner;
        this.scriptScanner = null;
    }

    public void setRoute(Route route) {
        this.routeFromXml = route;
    }

    public void setScriptScanner(Scanner scriptScanner) {
        this.scriptScanner = scriptScanner;
    }

    @Override
    public void execute() {
        Scanner activeScanner = (scriptScanner != null) ? scriptScanner : scanner;
        boolean isConsole = (scriptScanner == null);
        try {
            if (routeFromXml != null) {
                collectionManager.add(routeFromXml);
                routeFromXml = null;
            } else {
                RouteBuilder builder = new RouteBuilder(activeScanner, true);
                Route route = builder.build();
                collectionManager.add(route);
            }
        } catch (ExitException e) {
            System.out.println("Выход из режима ввода маршрута");
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
