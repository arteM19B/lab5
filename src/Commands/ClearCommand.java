package Commands;

import CollectionManager.CollectionManager;
/**
 * Команда очистки всей коллекции.
 * Удаляет все элементы из коллекции.
 *
 * @author artem_bahetkin
 * @version 1.0
 */
public class ClearCommand implements Command {
    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.clear();
    }

    @Override
    public String toString() {
        return "очистить коллекцию";
    }

}
