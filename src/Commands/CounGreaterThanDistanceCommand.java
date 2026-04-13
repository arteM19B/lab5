package Commands;

import CollectionManager.CollectionManager;

/**
 * Команда подсчёта количества элементов, у которых значение поля distance
 * больше заданного.
 *
 * @author artem_bahetkin
 * @version 1.0
 */
public class CounGreaterThanDistanceCommand implements Command {

    private final CollectionManager<Long> collectionManager;
    private long distance = -1;

    public CounGreaterThanDistanceCommand(CollectionManager<Long> collectionManager) {
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
        int count = collectionManager.countGreaterThanDistance(distance);

        if (count == 0) {
            System.out.println("Дорог с таким расстоянием не найдено");
        } else {
            System.out.println("Найдено " + count + " дорог с расстоянием, большим, чем " + distance);
        }
    }

    @Override
    public String toString() {
        return "вывести количество элементов, значение поля distance которых больше заданного";
    }
}
