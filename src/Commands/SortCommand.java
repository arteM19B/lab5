package Commands;


import CollectionManager.CollectionManager;
/**
 * Команда сортировки коллекции.
 * Сортирует коллекцию маршрутов в естественном порядке (по расстоянию).
 *
 * @author artem_bahetkin
 * @version 1.0
 */
public class SortCommand implements Command {
    private final CollectionManager collectionManager;


    public SortCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.sort();
    }

    @Override
    public String toString() {
        return "отсортировать коллекцию в естественном порядке";
    }
}
