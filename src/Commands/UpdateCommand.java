package Commands;

import CollectionManager.CollectionManager;

public class UpdateCommand implements Command {
    CollectionManager collectionManager;

    public UpdateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {

    }

    @Override
    public String toString() {
        return "обновить значение элемента коллекции, id которого равен заданному";
    }
}
