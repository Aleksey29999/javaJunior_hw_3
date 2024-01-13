package ru.geekbrains.junior.lesson3.task1;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.geekbrains.junior.lesson3.task1.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Program {


    /**
     * Задача 1
     * ========
     Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
     Обеспечьте поддержку сериализации для этого класса.
     Создайте объект класса Student и инициализируйте его данными.
     Сериализуйте этот объект в файл.
     Десериализуйте объект обратно в программу из файла.
     Выведите все поля объекта, включая GPA, и ответьте на вопрос,
     почему значение GPA не было сохранено/восстановлено.

     2. * Выполнить задачу 1 используя другие типы сериализаторов (в xml и json документы).
     */

    public static void main(String[] args) {

        final String FILE_JSON = "student.json";
        final String FILE_BIN = "student.bin";
        final String FILE_XML = "student.xml";
        final ObjectMapper outJonson = new ObjectMapper();
        final XmlMapper outXml = new XmlMapper();

        Student student = new Student("Алексей", 37, 4.4);
        System.out.println("_________________");
        System.out.println("Исходный объект:");
        System.out.println("Имя: " + student.getName());
        System.out.println("Возраст: " + student.getAge());
        System.out.println("Средний бал : " + student.GPA());
        System.out.println("_________________");
        System.out.println("   ");

        try (FileOutputStream fileOutBin = new FileOutputStream(FILE_BIN);


            ObjectOutputStream outBin = new ObjectOutputStream(fileOutBin)){
            outBin.writeObject(student);

            outXml.configure(SerializationFeature.INDENT_OUTPUT, true);
            outXml.writeValue(new File(FILE_XML),student);

            outJonson.configure(SerializationFeature.INDENT_OUTPUT, true);
            outJonson.writeValue(new File(FILE_JSON),student);

            System.out.println("Объект Student сериализован.");
        }
        catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("_________________");
        System.out.println(" ");
        try (FileInputStream fileInBin = new FileInputStream(FILE_BIN);
             ObjectInputStream inBin = new ObjectInputStream(fileInBin)){
            student = (Student)inBin.readObject();
            System.out.println("Объект Student десериализован.");
            System.out.println("Имя: " + student.getName());
            System.out.println("Возраст: " + student.getAge());
            System.out.println("Средний бал (должен быть null, так как transient): " + student.GPA());
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        System.out.println("_________________");
    }

}
