package Collection;

public class IdGenerator {
    private static long counter = 0;
    public static long next() {
        return ++counter;
    }
}
