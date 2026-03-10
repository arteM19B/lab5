package Collection;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Route {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate = LocalDate.now(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location from; //Поле может быть null
    private Location to; //Поле может быть null
    private long distance; //Значение поля должно быть больше 1

    public Route(String name, Coordinates coordinates, Location from, Location to, long distance) {
        if (coordinates == null) {
            throw new IllegalArgumentException("coordinates can't be null");
        }
        if (distance <= 1) {
            throw new IllegalArgumentException("distance must be greater than 1");
        }
        if (name == null) {
            throw new IllegalArgumentException("name can't be null");
        }

//        this.id = ;
        this.coordinates = coordinates;
        this.name = name;
        this.creationDate = LocalDate.now();
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.id = IdGenerator.next();
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public java.time.LocalDate getCreationDate() {
        return creationDate;
    }
    public Location getFrom() {
        return from;
    }
    public Location getTo() {
        return to;
    }
    public long getDistance() {
        return distance;
    }
    public LocalDateTime getStartTime() {
        return creationDate.atStartOfDay();
    }
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return(name + "; " + coordinates + "; " + from + " -> " + to + "; " + distance + "; Дата создания: " + creationDate + "; ID маршрута: " + id);
    }
}
