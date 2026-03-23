package Collection;

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
