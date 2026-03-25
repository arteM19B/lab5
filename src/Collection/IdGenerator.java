package Collection;
/**
 * Утилитный класс для генерации уникальных идентификаторов маршрутов.
 * Реализует простой счётчик для автоматического присвоения id.
 *
 * @author artem_bahetkin
 * @version 1.0
 */
public class IdGenerator {
    private static long counter = 0;

    public static long next() {
        return ++counter;
    }

    public static long getCounter() {
        return counter;
    }

    public static void setCounter(long value) {
        counter = value;
    }


}
