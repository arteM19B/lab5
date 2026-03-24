package CollectionManager;

import Collection.Route;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

    public void removeAt(int index) {
        if (index < 0 || index >= collection.size()) {
            System.out.println("Неверный индекс");
            return;
        }
        collection.remove(index);
        System.out.println("Коллекция на позиции " + index + "удалена");
    }

    public void update(long id, Route newRoute) {
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getId() == id) {
                collection.set(i, newRoute);
                System.out.println("Обновлённая дорога: " + newRoute.toString());
                return;
            }
        }
        System.out.println("Не найдено дороги с таким id: " + id);
    }

    public void clear() {
        collection.clear();
        System.out.println("Коллекция очищена");
    }

    public void remove_last() {
        collection.removeLast();
    }

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

    public void sort() {
        Collections.sort(collection);
        System.out.println("Коллекция отсортирована по естественному порядку (distance)");
    }

    public int removeAllByDistance(long distance) {
        int count = (int) collection.stream().filter(route -> route.getDistance() == distance).count();
        collection.removeIf(route -> route.getDistance() == distance);
        return count;
    }

    public int countGreaterThanDistance(long distance) {
        int count = (int) collection.stream().filter(route -> route.getDistance() > distance).count();
        return count;
    }

    public int filterLessThanDistance(long distance) {
        int count = 0;
        for (Route route : collection) {
            if (route.getDistance() < distance) {
                count++;
                System.out.println(route);
            }
        }
        return count;
    }

    public void save() {
        if (fileName == null || fileName.isEmpty()) {
            System.out.println("Имя файла не найдено");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<routes>\n");
            for (Route route : collection) {
                writer.write(route.toXML() + "\n");
            }
            writer.write("</routes>\n");
            System.out.println("Коллекция успешно сохранена в " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
        }
    }

}
