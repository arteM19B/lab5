package Commands;

import CollectionManager.CollectionManager;

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
