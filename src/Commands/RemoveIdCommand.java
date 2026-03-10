package Commands;

import Collection.Route;
import CollectionManager.CollectionManager;

import java.util.Iterator;

public class RemoveIdCommand implements Command {
    private CollectionManager collectionManager;
    private long id;

    public RemoveIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        Iterator<Route> iterator = collectionManager.getCollection().iterator();
        while (iterator.hasNext()) {
            long removeId = iterator.next().getId();

            if (removeId == id) {
                iterator.remove();
                System.out.println("Дорога с данным id удалена");
            }
        }
    }

    public void setArgument(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Удалить элемент из коллекции по его id";
    }
}
