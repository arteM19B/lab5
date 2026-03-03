import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;

public class CollectionManager {
    private LinkedList<Route> collection = new LinkedList<>();
    private LocalDateTime initializationTime;
    private String fileName;

    public CollectionManager(String fileName) {
        this.initializationTime = LocalDateTime.now();
        this.fileName = fileName;
    }

    public CollectionManager() {
        this.initializationTime = LocalDateTime.now();
    }

    public void add(Route route) {
        collection.add(route);
        System.out.println("Added route: " + route.toString());
    }

    public void show() {
        for (Route routes : collection) {
            System.out.println(routes);
        }
    }
    public void clear() { collection.clear();}

    public int  size() {
        return collection.size();
    }

    public String getFileName() {
        return fileName;
    }

    public String getType() {
        return collection.getClass().getSimpleName();
    }

    public LocalDateTime getInitializationTime() {
        return initializationTime;
    }

    public LinkedList<Route> getCollection() {
        return collection;
    }

    public Route getById(long id) {
        for (Route route : collection) {
            if (route.getId() == id) {
                return route;
            }
        }
        return null;
    }
//    public void sort() { Collections.sort(collection);}
}
