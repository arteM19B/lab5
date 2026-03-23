package Commands;

import CollectionManager.CollectionManager;

public class RemoveAtCommand implements Command {
    private final CollectionManager collectionManager;
    private int index = -1;

    public RemoveAtCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void setIndex(int index){
        this.index = index;
    }

    @Override
    public void execute() {
        if (index == 1) {
            System.out.println("Укажите индекс: remove_at <index>");
            return;
        }
        collectionManager.removeAt(index);
    }
}
