package Commands;

import CollectionManager.CollectionManager;
/**
 * Команда удаления элемента коллекции по индексу.
 * Удаляет маршрут, находящийся на указанной позиции в коллекции.
 *
 * @author artem_bahetkin
 * @version 1.0
 */
public class RemoveAtCommand implements Command {
    private final CollectionManager<Long> collectionManager;
    private int index = -1;

    public RemoveAtCommand(CollectionManager<Long> collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void setArgument(int index){
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

    @Override
    public String toString() {
        return "удалить элемент, находящийся в заданной позиции коллекции (index)";
    }
}
