package Commands;

import CollectionManager.CollectionManager;

public class InfoCommand implements Command {
    private CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    //

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
