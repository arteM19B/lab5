package Commands;

public class ExitCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Программа завершена");
        System.exit(0);
    }

    @Override
    public String toString() {
        return "завершить программу (без сохранения в файл)";
    }
}
