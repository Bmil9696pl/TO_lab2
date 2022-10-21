import DataAcquisition.DataAcquisition;
import DataInterpreter.DataInterpreter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    static URL link;

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        String begin = "";
        System.out.println("Podaj datę początkowa");
        System.out.println("Rok:");
        begin = begin + scan.nextLine();
        System.out.println("Miesiac:");
        begin += scan.nextLine();
        System.out.println("Dzien:");
        begin += scan.nextLine();
        System.out.println("Godzina:");
        begin += scan.nextLine();

        String end = "";
        System.out.println("Podaj datę końcową");
        System.out.println("Rok:");
        end += scan.nextLine();
        System.out.println("Miesiac:");
        end += scan.nextLine();
        System.out.println("Dzien:");
        end += scan.nextLine();
        System.out.println("Godzina:");
        end += scan.nextLine();

        String[] data = DataAcquisition.acquireData(begin, end);
        DataInterpreter.interpret(data);
    }
}
