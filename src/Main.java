import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        if (CheckValidity.isValid(str))
            System.out.println(new Element(str).getResult());
    }
}