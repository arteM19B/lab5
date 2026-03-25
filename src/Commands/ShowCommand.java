package Commands;

import CollectionManager.CollectionManager;
/**
 * Команда вывода всех элементов коллекции.
 * Выводит в стандартный поток вывода все маршруты в строковом представлении.
 *
 * @author artem_bahetkin
 * @version 1.0
 */
public class ShowCommand implements Command {
    private CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.show();
    }

    @Override
    public String toString() {
        return "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
