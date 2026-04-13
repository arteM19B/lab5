package Commands;

import CollectionManager.CollectionManager;
/**
 * Команда фильтрации и вывода элементов, у которых значение поля distance
 * меньше заданного.
 *
 * @author artem_bahetkin
 * @version 1.0
 */
public class FilterLessThanDistanceCommand implements Command {
    private final CollectionManager<Long> collectionManager;
    private long distance = -1;

    public FilterLessThanDistanceCommand(CollectionManager<Long> collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void setArgument(long distance) {
        this.distance = distance;
    }

    @Override
    public void execute() {
        if (distance <= 1) {
            System.out.println("Ошибка: укажите корректное расстояние (>1)");
            return;
        }

        int count = collectionManager.filterLessThanDistance(distance);

        if (count == 0) {
            System.out.println("Дорог с расстоянием, меньшим " + distance + " не найдено");
        } else {
            System.out.println("Выведено " + count + " дорог");
        }
    }

    @Override
    public String toString() {
        return "вывести элементы, значение поля distance которых меньше заданного";
    }
}
