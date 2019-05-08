import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class UtilityMethods {
    //Required because BigDecimal and Int cannot be multiplied otherwise
    public BigDecimal calculateTotalPrice(int quantity, BigDecimal price) {
        return BigDecimal.valueOf(quantity).multiply(price);

    }

    public void createNewBookAndInsertIntoBookList(String title, String author, BigDecimal price, int quantity) {
        ArrayList<Book> temporaryListOfBooks = getBookDataFromURL();
        Book book = new Book(title, author, price, quantity);
        temporaryListOfBooks.add(book);
    }

    public void createNewBookAndInsertIntoShoppingCart(String title, String author, BigDecimal price, int quantity) {
        BookStore bookStore = new BookStore();
        Book book = new Book(title, author, price, quantity);
        bookStore.add(book, quantity);
    }

    public void displaySearches(ArrayList<Book> searches) {
        for (Book book : searches) {
            System.out.println("Title of book found through search: " + book.getTitle() + "\n" +
                    "By author: " + book.getAuthor() + "\n" +
                    "The price is: " + book.getPrice() + "\n");
        }
    }


    public ArrayList<Book> getBookDataFromURL() {
        ArrayList<Book> list = new ArrayList<>();
        Scanner scanner = null;
        URL url;
        String title;
        String author;
        BigDecimal price;
        int quantity;

        try {

            url = new URL("https://raw.githubusercontent.com/contribe/contribe/dev/bookstoredata/bookstoredata.txt");
            scanner = new Scanner(url.openStream(), "UTF-8")
                    .useDelimiter("\\s*[;]\\s*|\\s*\n\\s*")
                    .useLocale(Locale.ROOT);

            while (scanner.hasNextLine()) {
                title = scanner.next();
                author = scanner.next();
                price = scanner.nextBigDecimal();
                quantity = scanner.nextInt();
                list.add(new Book(title, author, price, quantity));
            }

        } catch (
                MalformedURLException mue) {
            System.out.println("MalformedURLException occured.");
            mue.printStackTrace();
            System.exit(1);
        } catch (
                IOException ioe) {
            System.out.println("IOException occured.");
            ioe.printStackTrace();
            System.exit(1);
        } finally {
            try {
                scanner.close();
            } catch (NullPointerException npe) {
                System.out.println("NullPointerException happened when attempting to close the Scanner");
                npe.printStackTrace();
                System.exit(1);
            }
        }
        return list;
    }

    public void PrintTitles() {
        ArrayList<Book> test = getBookDataFromURL();

        //Cast arraylist to array of Book
        Book[] books;
        books = new Book[test.size()];
        books = test.toArray(books);
        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }
}
