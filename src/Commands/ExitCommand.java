package Commands;
/**
 * Команда завершения работы программы.
 * Завершает выполнение приложения без сохранения коллекции.
 *
 * @author artem_bahetkin
 * @version 1.0
 */
public class ExitCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Программа завершена");
        System.exit(0);
    }

    @Override
    public String toString() {
        return "завершить программу (без сохранения в файл)";
    }
}
