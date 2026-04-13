package Commands;

import Collection.Route;
import CollectionManager.CollectionManager;

import java.util.Iterator;
/**
 * Команда удаления элемента коллекции по его ID.
 * Удаляет маршрут с указанным идентификатором.
 *
 * @author artem_bahetkin
 * @version 1.0
 */
public class RemoveIdCommand implements Command {
    private final CollectionManager<Long> collectionManager;
    private Long id;

    public RemoveIdCommand(CollectionManager<Long> collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        Iterator<Route<Long>> iterator = collectionManager.getCollection().iterator();
        while (iterator.hasNext()) {
            long removeId = iterator.next().getId();
            if (removeId == id) {
                iterator.remove();
                System.out.println("Дорога с данным id удалена");
            }
        }
    }

    public void setArgument(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Удалить элемент из коллекции по его id";
    }
}
