package Collection;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Route implements Comparable<Route>{
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate = LocalDate.now(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location from; //Поле может быть null
    private Location to; //Поле может быть null
    private long distance; //Значение поля должно быть больше 1

    public Route(long id, String name, Coordinates coordinates, Location from, Location to, long distance) {
        if (coordinates == null) {
            throw new IllegalArgumentException("coordinates can't be null");
        }
        if (distance <= 1) {
            throw new IllegalArgumentException("distance must be greater than 1");
        }
        if (name == null) {
            throw new IllegalArgumentException("name can't be null");
        }
        this.id = id;
        this.coordinates = coordinates;
        this.name = name;
        this.creationDate = LocalDate.now();
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

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
        this.id = IdGenerator.next();
        this.coordinates = coordinates;
        this.name = name;
        this.creationDate = LocalDate.now();
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    @Override
    public int compareTo(Route o) {
        return Long.compare(this.distance, o.distance);
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("  <route>\n");
        sb.append("    <id>").append(id).append("</id>\n");
        sb.append("    <name>").append(name).append("</name>\n");
        sb.append("    <coordinates>\n");
        sb.append("      <x>").append(coordinates.getX()).append("</x>\n");
        sb.append("      <y>").append(coordinates.getY()).append("</y>\n");
        sb.append("    </coordinates>\n");
        sb.append("    <creationDate>").append(creationDate).append("</creationDate>\n");
        sb.append("    <from>").append(from != null ? from.toXML() : "null").append("</from>\n");
        sb.append("    <to>").append(to != null ? to.toXML() : "null").append("</to>\n");
        sb.append("    <distance>").append(distance).append("</distance>\n");
        sb.append("  </route>");
        return sb.toString();
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
