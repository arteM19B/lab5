public class Location {
    private Float x; //Поле не может быть null
    private double y;
    private String name; //Строка не может быть пустой, Поле может быть null

    public Location(Float x, double y, String name) {
        if (x == null) {
            throw new IllegalArgumentException("x is null");
        }
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Float getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public String getName() { return name;}

    @Override
    public String toString() {
        return(name + "(" + x + ",  " + y + ")");
    }
}