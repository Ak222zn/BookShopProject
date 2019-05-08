import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

public class Menu {

    public void MainMenu() {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.ROOT);
        int userchoice;
        boolean quitProgramme = false;
        String choice;
        String searchString;
        BigDecimal price;
        BookStore bookStore = new BookStore();
        ShoppingCart shoppingCart = new ShoppingCart();
        UtilityMethods utilityMethods = new UtilityMethods();

        do {
            System.out.println();
            System.out.println("Welcome to our book shop!");
            System.out.println();
            System.out.println("1) Add book to stock");
            System.out.println("2) List the titles of all books");
            System.out.println("3) Search for book by title or author");
            System.out.println("4) Add book to shopping cart");
            System.out.println("5) Remove book from shopping cart");
            System.out.println("6) List items in shopping cart");
            System.out.println("7) Buy the items in your basket");
            System.out.println("8) Quit");
            userchoice = scanner.nextInt();
            switch (userchoice) {

                case 1:
                    System.out.println("Please enter the Title of book that you wish to add to stock.");
                    String title = scanner.next();
                    System.out.println("Please enter Author of book that you wish to add to stock.");
                    String author = scanner.next();
                    System.out.println("Please enter Price of book that you wish to add to stock.");
                    price = scanner.nextBigDecimal();
                    System.out.println("Please enter quantity of books that you wish to add to stock.");
                    int quantity = scanner.nextInt();

                    utilityMethods.createNewBookAndInsertIntoBookList(title, author, price, quantity);
                    break;

                case 2:
                    System.out.println("Book titles:");
                    utilityMethods.PrintTitles();
                    break;

                case 3:
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Input Author or Title.");
                    searchString = scan.nextLine();
                    bookStore.list(searchString);
                    break;

                case 4:
                    String keepShopping;
                    do {
                        System.out.println("Please enter the Title of Book to add to shopping cart.");
                        scanner.nextLine();
                        title = scanner.nextLine();
                        System.out.println("Please enter Author.");
                        author = scanner.nextLine();
                        System.out.println("Please enter Price.");
                        price = scanner.nextBigDecimal();
                        System.out.println("Please enter quantity.");
                        quantity = scanner.nextInt();
                        shoppingCart.addToCart(title, author, price, quantity);

                        System.out.println("Would you like to continue shopping? \n" +
                                "Enter *yes* to continue shopping.\n" +
                                "Enter *no* to go back to the main menu.");
                        keepShopping = scanner.next();
                    }
                    while (keepShopping.equals("yes"));

                    break;

                case 5:
                    System.out.println("Please enter the title of the book that you would like to remove from your basket.");
                    choice = scanner.next();
                    shoppingCart.removeFromCart(choice);
                    break;

                case 6:
                    shoppingCart.getItemsInCart();
                    break;

                case 7:
                    bookStore.buy();
                    break;

                case 8:
                    System.out.println("Thank you for your time.");
                    quitProgramme = true;
                    break;

                default:
                    System.out.println("Your input is invalid at this store.");
            }
        }
        while (!quitProgramme);
        scanner.close();
    }

}
