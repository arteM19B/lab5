package Commands;

import Collection.Route;
import CollectionManager.CollectionManager;

public class SortCommand implements Command {
    private CollectionManager collectionManager;


    public SortCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.sort();
    }
}
