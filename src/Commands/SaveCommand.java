package Commands;

import CollectionManager.CollectionManager;

/**
 * Команда сохранения коллекции в файл.
 * Сохраняет текущую коллекцию в XML-файл, указанный при запуске программы.
 *
 * @author artem_bahetkin
 * @version 1.0
 */
public class SaveCommand implements Command {
    private final CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.save();
    }

    @Override
    public String toString() {
        return "сохранить коллекцию в файл";
    }
}