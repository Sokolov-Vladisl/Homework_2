
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            boolean test = false; // Если true - создаёт несуществующий файл
            boolean dev = false; // Если true - показывает более детально

            Start.main(args,dev,test);

            if(dev){
                System.out.println("\n\n_-_-_-_- ДЕМОНСТРАЦИЯ СОЗДАННЫХ ФАЙЛОВ -_-_-_-_");
                Start.readAndPrintFile("test_file1.txt");
                Start.readAndPrintFile("extra_file/test_file2.txt");
            }

            System.out.println("\nПрограмма завершена успешно!\n\n");

        } catch (Exception e) {
            System.err.println("Тест завершился с ошибкой: " + e.getMessage());

        }
    }
}