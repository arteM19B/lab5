package Commands;

import CollectionManager.CollectionManager;
/**
 * Команда удаления последнего элемента коллекции.
 * Удаляет маршрут, находящийся в конце коллекции.
 *
 * @author artem_bahetkin
 * @version 1.0
 */
public class RemoveLastCommand implements Command {
    private final CollectionManager<Long> collectionManager;

    public RemoveLastCommand(CollectionManager<Long> collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.remove_last();
    }

    @Override
    public String toString() {
        return "удалить последний элемент из коллекции";
    }
}
