package Collection;

import CollectionManager.CollectionManager;

import java.util.Scanner;

public class RouteXMLParser {
    StringBuilder xml = new StringBuilder();
    boolean insideRoute = false;
    private final CollectionManager collectionManager;
    private final Scanner scanner;

    public RouteXMLParser(CollectionManager collectionManager, Scanner scanner) {
        this.collectionManager = collectionManager;
        this.scanner = scanner;
    }

    public static Route parse(Scanner scanner) {
        StringBuilder xml = new StringBuilder();
        boolean insideRoute = false;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            if (line.startsWith("<route>")) {
                insideRoute = true;
            }

            if (insideRoute) {
                xml.append(line).append("\n");
            }

            if (line.endsWith("</route>") || line.equals("</route>")) {
                break;
            }
        }
        if (xml.length() == 0) {
            //
        }

        return parseXmlString(xml.toString());
    }

    private static Route parseXmlString(String xmlString) {
        Scanner tempScanner = new Scanner(xmlString);
        return CollectionManager.getInstance().parseRoute(tempScanner);
    }
}
