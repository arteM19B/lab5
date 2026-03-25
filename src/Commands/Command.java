package Commands;
/**
 * Интерфейс, который реализуют все команды программы.
 * Реализует паттерн "Command" для унифицированного выполнения действий.
 *
 * @author artem_bahetkin
 * @version 1.0
 */
public interface Command {
    void execute();
}
