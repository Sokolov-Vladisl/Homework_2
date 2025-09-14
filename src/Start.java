
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Start {

    public static void main(String[] args,boolean dev,boolean test) {
        String[] filePaths = {"test_file1.txt", "extra_file/test_file2.txt",
        "extra_nan/nonexist.txt"
        };


        try {

            createFileWithOverwrite(filePaths[0], "Привет! Я первый текст. эта строка - база.",dev);
            if(dev) System.out.println("(+) Файл создан с перезаписью: " + filePaths[0]);

            appendToFile(filePaths[0], "\n\nэта строка - добавлена.",dev);
            if(dev) System.out.println("(+) Текст добавлен в файл: " + filePaths[0]);

            createFileIfNotExists(filePaths[1], "Привет! Я ВТОРОЙ и немноооожечко обидчивый текст.\nТолько попробуй создать меня снова и я выдам ошибку!",dev);
            if(dev) System.out.println("(+) Файл создан (значит - не существовал): " + filePaths[1]);

            if(test){

                createFileWithOverwrite(filePaths[2], "ОУ. ЗРЯ. Очевидная ошибка",dev);


                // Можно убрать из комментария, но всё равно программа остановится на первом exception и всё
                /*
                appendToFile(filePaths[2], "ОУ. ЗРЯ. Очевидная ошибка",dev);
                if(dev) System.out.println("(-) Текст не добавлен в несуществующий файл: " + filePaths[2]);

                createFileIfNotExists(filePaths[2], "ОУ. ЗРЯ. Очевидная ошибка",dev);
                if(dev) System.out.println("(-) Файл не создан (никогда): " + filePaths[2]);
                */


            }


            System.out.println("\n >!!!< РЕЗУЛЬТАТ >!!!<");
            System.out.println("(+) Все файлы успешно созданы! :)");
            System.out.println("(?) Проверьте созданные файлы в указанных директориях:\n > первый файл: "+filePaths[0]+"\n > второй файл: "+filePaths[1]);

        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлами: " + e.getMessage());
        }
    }



    //-----------------------------------------------------------------------


    private static void createFileWithOverwrite(String filePath, String story,boolean dev) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(story);
        }
        catch (IOException e) {
            if(dev) System.out.println("(-) Несуществующи файл не создан: " + filePath);
            throw new IOException("Не удалось найти директорию для файла: " + filePath+"\n > Это тестовая ошибка! Всё работает, просто измените параметр test на false.");
        }
    }


    private static void appendToFile(String filePath, String extra_story,boolean dev) throws IOException {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(extra_story);
        }
        catch (IOException e) {
            if(dev) System.out.println("(-) Несуществующи файл не создан: " + filePath);
            throw new IOException("Не удалось найти директорию для файла: " + filePath+"\n > Это тестовая ошибка! Всё работает, просто измените параметр test на false.");
        }
    }

    private static void createFileIfNotExists(String filePath, String story,boolean dev) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            if(dev) System.out.println("(-) Файл не создан (значит - существовал): " + filePath);
            throw new IOException("Файл уже существует: " + filePath+"\n > Это тестовая ошибка! Всё работает, просто удалите файл.");
        }


        try (FileWriter writer = new FileWriter(file)) {
            writer.write(story);
        }
        catch (IOException e) {
            if(dev) System.out.println("(-) Несуществующи файл не создан: " + filePath);
            throw new IOException("Не удалось найти директорию для файла: " + filePath+"\n > Это тестовая ошибка! Всё работает, просто измените параметр test на false.");
        }
    }


    //-----------------------------------------------------------------------


    public static void readAndPrintFile(String filePath) throws IOException {
        System.out.println("\n   > СОДЕРЖИМОЕ ФАЙЛА " + filePath + "\n - - - - - - - - - - - - - - - - -");
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
        System.out.println(" - - - - - - - - - - - - - - - - -");
    }
}