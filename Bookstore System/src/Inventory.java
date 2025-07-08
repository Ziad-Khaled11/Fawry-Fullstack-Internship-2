import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Scanner;

public class Inventory {
    public static HashMap<String, Book> books = new HashMap<>();

    public static void buyBook(String ISBN, Customer cust){
        Book returned = books.get(ISBN.toLowerCase());
        if (returned != null) {
            Type book_type = returned.getBook_type();
            Scanner scanner = new Scanner(System.in);
            int quantity = -1;
            switch (book_type){
                case DEMO :
                    System.out.println("This book can't be purchased");
                    return;
                case EBOOK :
                    if (cust.getBalance() - returned.getPrice() < 0){
                        System.err.println("Balance doesn't satisfy the price");
                    }
                    else{
                        System.out.println("The book is purchased successfully");
                        System.out.println("1x " + returned.getTitle() + "      " + returned.getPrice());
                        cust.setBalance(cust.getBalance() - returned.getPrice());
                        System.out.println("Your current balance is " + cust.getBalance());
                        System.out.println("The book is sent to your email" + cust.getEmail());
                        System.out.println("Thanks for purchasing from our web bookstore");
                    }
                    return;
                case PAPER:
                    while (quantity < 0) {
                        System.out.println("Please enter the new quantity");
                        quantity = scanner.nextInt();
                        if (quantity < 0) {
                            System.err.println("Invalid quantity value");
                        }
                    }
                    if (cust.getBalance() - returned.getPrice() < 0){
                        System.err.println("Balance doesn't satisfy the price");
                    }
                    else if (returned.getQuantity() - quantity <  0) {
                        System.err.println("The book is out of stock");
                    }
                    else {
                        System.out.println("The book is purchased successfully");
                        System.out.println(quantity + "x " + returned.getTitle() + "      " + (returned.getPrice() * quantity));
                        cust.setBalance(cust.getBalance() - returned.getPrice());
                        returned.setQuantity(returned.getQuantity() - quantity);
                        System.out.println("Your current balance is " + cust.getBalance());
                        System.out.println("The book will be shipped very soon\n" +
                                "Check your email and the spam section for the shipment details");
                        System.out.println("Thanks for purchasing from our web bookstore");
                    }
                    return;
                case NEW:
                    while (quantity < 0) {
                        System.out.println("Please enter the new quantity");
                        quantity = scanner.nextInt();
                        if (quantity < 0) {
                            System.err.println("Invalid quantity value");
                        }
                    }
                    if (cust.getBalance() - returned.getPrice() < 0){
                        System.err.println("Balance doesn't satisfy the price");
                    }
                    else if (returned.getQuantity() - quantity <  0) {
                        System.err.println("The product is out of stock");
                    }
                    else{
                        System.out.println("The product is purchased successfully");
                        System.out.println(quantity + "x " + returned.getTitle() + "      " + (returned.getPrice() * quantity));
                        cust.setBalance(cust.getBalance() - (returned.getPrice() * quantity));
                        returned.setQuantity(returned.getQuantity() - quantity);
                        System.out.println("Your current balance is " + cust.getBalance());
                        System.out.println("Thanks for purchasing from our web bookstore");
                    }
                    return;
                case null, default:
                    System.err.println("Error happened\nPlease try again");
            }
        }
        else{
            System.err.println("This book not found");
        }
    }
    public static void returnBook(String ISBN, int years){
        LocalDate current = LocalDate.now();
        Book returned = books.get(ISBN.toLowerCase());
        if (returned != null) {
            Period agePeriod = Period.between(returned.getPublish_year(), current);
            int years_period = agePeriod.getYears();
            if (years_period > years) {
                System.out.println("This book should be returned and removed");
                books.remove(ISBN.toLowerCase());
                System.out.println("The book removed successfully");
            } else {
                System.out.println("This book isn't outdated yet");
            }
        }
        else {
            System.err.println("Book is not found");
        }
    }

    public static void addBook(){
        Scanner scanner = new Scanner(System.in);
        String choice;
        System.out.println("Please enter the book ISBN");
        String ISBN = scanner.next();
        Book returned = books.get(ISBN);
        if (returned != null && returned.getBook_type().toString().equals("PAPER")) {
            System.out.println("The book already exists do you need to increase its quantity?");
            System.out.println("Enter Y or N to continue");
            choice = scanner.next();
            if (choice.equalsIgnoreCase("y")) {
                int quantity = -1;
                while (quantity < 0) {
                    System.out.println("Please enter the new quantity");
                    quantity = scanner.nextInt();
                    if (quantity < 0) {
                        System.err.println("Invalid quantity value");
                    }
                }
                returned.setQuantity(returned.getQuantity() + quantity);
            }
        }
        if (returned != null) {
            System.out.println("The book already exists do you need to update the price?");
            System.out.println("Enter Y or N to continue");
            choice = scanner.next();
            if (choice.equalsIgnoreCase("y")) {
                double price = -1;
                while (price < 0) {
                    System.out.println("Please enter the new price");
                    price = scanner.nextDouble();
                    if (price < 0) {
                        System.err.println("Incorrect price value");
                    }
                }
                returned.setPrice(price);
            }
        }
        else {
            System.out.println("Please enter the book title");
            String title = scanner.next();
            String date;
            while(true) {
                System.out.println("Please enter the publish date");
                System.out.println("The format is yyyy-mm-dd");
                date = scanner.next();
                try {
                    LocalDate current_date = LocalDate.now();
                    if (LocalDate.parse(date).isAfter(current_date)) {
                        System.err.println("Date is not valid\nFuture date");
                    }
                    else break;
                } catch (Exception e) {
                    System.err.println("Invalid Date");
                }
            }
            LocalDate parsed_date = LocalDate.parse(date);
            Type book_type = null;
            while (book_type == null) {
                System.out.println("Please enter the book type" +
                        "\n1 for Demo\n2 for EBook\n3 for Paper\n4 for Other");
                int type = scanner.nextInt();
                switch (type) {
                    case 1:
                        book_type = Type.DEMO;
                        break;
                    case 2:
                        book_type = Type.EBOOK;
                        break;
                    case 3:
                        book_type = Type.PAPER;
                        break;
                    case 4:
                        book_type = Type.NEW;
                        break;
                    default:
                        System.err.println("Invalid number");

                }
            }
            double price = -1;
            while (price < 0) {
                System.out.println("Enter the book price");
                price = scanner.nextDouble();
                if(price < 0){
                    System.err.println("Incorrect price value");
                }
            }
            Book book;
            int quantity = -1;
            switch (book_type){
                case DEMO:
                    book = new Book(ISBN.toLowerCase(), title, parsed_date, price, book_type);
                    System.out.println("The book is added successfully");
                    books.put(ISBN.toLowerCase(), book);
                    break;
                case EBOOK:
                    System.out.println("Enter the Ebook file type");
                    String file = scanner.next();
                    book = new Book(ISBN.toLowerCase(), title, parsed_date, price, book_type, file);
                    books.put(ISBN.toLowerCase(), book);
                    System.out.println("The book is added successfully");
                    break;
                case PAPER, NEW:
                    while (quantity < 0) {
                        System.out.println("Please enter the quantity");
                        quantity = scanner.nextInt();
                        if (quantity < 0) {
                            System.err.println("Invalid quantity value");
                        }
                    }
                    book = new Book(ISBN.toLowerCase(), title, parsed_date, price, book_type, quantity);
                    books.put(ISBN.toLowerCase(), book);
                    System.out.println("The book is added successfully");
                    break;
                case null, default:
                    System.err.println("Error happened\nPlease try again");

            }
        }

    }

    public static void dummyBooks(){
        Book paper_book = new Book(
                "java", "Effective Java", LocalDate.of(2018, 12, 27), 45.99, Type.PAPER, 150);
        Book ebook = new Book(
                "programming", "The Art of Programming", LocalDate.of(2023, 1, 15), 29.99, Type.EBOOK, "PDF");
        Book demo_book = new Book(
                "demo", "Learn Java Basics (Demo)", LocalDate.of(2024, 6, 1), 0.00, Type.DEMO);
        Book paper_book2 = new Book(
                "clean", "Clean Code", LocalDate.of(2008, 8, 1), 39.50, Type.PAPER, 1);
        Book ebook2 = new Book(
                "design", "Design Patterns (eBook)", LocalDate.of(2015, 3, 10), 550, Type.NEW, 15
        );
        Inventory.books.put("java", paper_book);
        Inventory.books.put("programming", ebook);
        Inventory.books.put("demo", demo_book);
        Inventory.books.put("clean", paper_book2);
        Inventory.books.put("design",ebook2);
    }
}
