package Commands;

import CollectionManager.CollectionManager;

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