package Commands;

import CollectionManager.CollectionManager;
import Collection.*;
import Exceptions.ExitException;

import java.util.Scanner;
/**
 * Команда обновления существующего элемента коллекции по его ID.
 * Заменяет маршрут с заданным идентификатором на новый.
 *
 * @author artem_bahetkin
 * @version 1.0
 */
public class UpdateCommand implements Command {
    private final CollectionManager<Long> collectionManager;
    private Long id = -1L;
    private final Scanner scanner;
    private Scanner scriptScanner;
    private Route<Long> routeFromXml = null;

    public UpdateCommand(CollectionManager<Long> collectionManager, Scanner scanner) {
        this.collectionManager = collectionManager;
        this.scanner = scanner;
        this.scriptScanner = null;
    }

    public void setArgument(Long id) {
        this.id = id;
    }

    public void setRoute(Route<Long> route) {
        this.routeFromXml = route;
    }

    public void setScriptScanner(Scanner scriptScanner) {
        this.scriptScanner = scriptScanner;
    }

    @Override
    public void execute() {
        if (id <= 0) {
            System.out.println("Ошибка: ID не задан или некорректен. Используйте: update <id>");
            return;
        }

        Route<Long> existing = collectionManager.getById(id);
        if (existing == null) {
            System.out.println("Маршрут с ID " + id + " не найден.");
            return;
        }

        Scanner activeScanner = (scriptScanner != null) ? scriptScanner : scanner;
        boolean isInteractive = (scriptScanner == null);

        try {
            if (routeFromXml != null) {
                routeFromXml.setId(id);
                collectionManager.update(id, routeFromXml);
                routeFromXml = null;
            } else {
                System.out.println("Обновление маршрута с ID = " + id);
                RouteBuilder builder = new RouteBuilder(activeScanner, isInteractive);
                Route<Long> newRoute = builder.build();
                newRoute.setId(id);
                collectionManager.update(id, newRoute);
            }
        } catch (ExitException e) {
            System.out.println("Выход из режима обновления маршрута");
        } catch (Exception e) {
            System.out.println("Не удалось обновить маршрут: " + e.getMessage());
        } finally {
            scriptScanner = null;
        }
    }

    @Override
    public String toString() {
        return "обновить значение элемента коллекции, id которого равен заданному";
    }


}
