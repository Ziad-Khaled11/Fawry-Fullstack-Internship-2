import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);
    public static void main(String[] args) {
        Inventory.dummyBooks();
        System.out.println("Welcome to our bookstore");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name");
        String name = scanner.next();
        String email;
        while (true) {
            System.out.println("Please enter your email");
            email = scanner.next();
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                System.err.println("Invalid email address");
            } else break;
        }
        Customer cust = new Customer(500, name, email);
        System.out.println("Welcome " + cust.getName() + " we added an initial balance 500LE as a gift");
        while (true){
            System.out.println("1 to add a book\n2 to remove an outdated book\n3 to buy a book\n4 to exit");
            int num = scanner.nextInt();
            String ISBN;
            if (num == 4){
                System.out.println("Thanks for visiting our web bookstore");
                break;
            }
            switch (num) {
                case 1:
                    Inventory.addBook();
                    break;
                case 2:
                    int years = -1;
                    System.out.println("Please enter the book ISBN");
                    ISBN = scanner.next();
                    while (years < 0) {
                        System.out.println("Please enter the specific years");
                        years = scanner.nextInt();
                        if (years < 0) {
                            System.err.println("Invalid quantity value");
                        }
                    }
                    Inventory.returnBook(ISBN, years);
                    break;
                case 3:
                    System.out.println("Please enter the book ISBN");
                    ISBN = scanner.next();
                    Inventory.buyBook(ISBN, cust);
                    break;
                default:
                    System.err.println("Invalid number");

            }
        }
    }
}
