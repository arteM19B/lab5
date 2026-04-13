package Commands;

import CollectionManager.CollectionManager;
/**
 * Команда вывода информации о коллекции.
 * Выводит тип коллекции, дату инициализации, количество элементов и имя файла.
 *
 * @author artem_bahetkin
 * @version 1.0
 */
public class InfoCommand implements Command {
    private final CollectionManager<Long> collectionManager;

    public InfoCommand(CollectionManager<Long> collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        System.out.println("Тип: " + collectionManager.getType());
        System.out.println("Дата инициализации: " + collectionManager.
                getInitializationTime());
        System.out.println("Размер: " + collectionManager.size());

        if (collectionManager.getFileName() != null) {
            System.out.println("Имя коллекции: " + collectionManager.getFileName());
        }

        if (collectionManager.size() > 0) {
            System.out.println(collectionManager.getCollection().
                    getFirst().getClass().getSimpleName());
        }
    }

    @Override
    public String toString() {
        return "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }


}
