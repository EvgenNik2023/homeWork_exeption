/*
 * Напишите приложение, которое будет запрашивать у пользователя следующие данные, разделенные пробелом:

Фамилия Имя Отчество дата _ рождения номер _ телефона пол

Форматы данных:

фамилия, имя, отчество - строки
дата _ рождения - строка формата dd.mm.yyyy
номер _ телефона - целое беззнаковое число без форматирования
пол - символ латиницей f или m.

Приложение должно проверить введенные данные по количеству. Если количество не совпадает, вернуть код ошибки,
 обработать его и показать пользователю сообщение, что он ввел меньше или больше данных, чем требуется.

Приложение должно распарсить полученную строку и выделить из них требуемые значения. Если форматы данных не совпадают,
 нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные типы java и создавать свои.
  Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией, что именно неверно.

Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии,
 в него в одну строку должны записаться полученные данные, вида
<Фамилия> <Имя> <Отчество> <дата _ рождения> <номер _ телефона> <пол>

Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
Не забудьте закрыть соединение с файлом.
При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано,
 пользователь должен увидеть стектрейс ошибки.

сдавать работу в виде ссылки на гит репозиторий
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    String[] nameArr = input();
    String fileName = nameArr[0];

    writeToFile(fileName, nameArr);

  }

  public static String[] input() {

    String[] gudBay = { "До новых встреч!" };
    System.out.println("Введите " +
        "Фамилия Имя Отчество дата _ рождения(dd.mm.yyyy) номер _ телефона(10 цифр без пробелов) пол(f, m) через пробел. "
        +
        "Или 'с' для выхода:  ");

    Scanner s = new Scanner(System.in);
    String name = s.nextLine();
    if (name.equals("c")) {
      return gudBay;
    }
    System.out.println(name);
    String[] arr = name.split(" ");
    if (arr.length > 6) {
      System.out.println("Число параметров больше необходимого");
      return input();
    } else if (arr.length < 6) {
      System.out.println("Число параметров меньше необходимого");
      return input();
    }
    if (arr[5].equals("f") || arr[5].equals("m")) {
      boolean dateFormat = arr[3].matches("\\d{2}[.]\\d{2}[.]\\d{4}");
      if (!dateFormat) {
        System.out.println("Неверный формат даты рождения");
        return input();
      }

      boolean numberPhone = arr[4].matches("\\d{10}");
      if (!numberPhone) {
        System.out.println("Неверный формат номера телефона");
        return input();
      }

    } else {
      System.out.println("Не корректно указан пол");
      return input();
    }
    return arr;
  }

  static void writeToFile(String fileName, String[] nameArr) {
    try (FileWriter writer = new FileWriter(fileName, true)) {

      writer.write(Arrays.toString(nameArr));
      writer.append('\n');

    } catch (IOException ex) {

      System.out.println(ex.getMessage());
    }

  }

}