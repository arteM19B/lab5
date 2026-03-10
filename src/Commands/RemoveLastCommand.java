package Commands;

import CollectionManager.CollectionManager;

public class RemoveLastCommand implements Command {
    private final CollectionManager collectionManager;

    public RemoveLastCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.remove_last();
    }
}
