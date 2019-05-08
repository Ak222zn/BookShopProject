import java.math.BigDecimal;
import java.util.*;

public class BookStore implements BookList {
    UtilityMethods utilityMethods = new UtilityMethods();

    @Override
    public Book[] list(String searchString) {
        ArrayList<Book> bookList = utilityMethods.getBookDataFromURL();
        ArrayList<Book> searches = new ArrayList<>();

        //Cast arraylist to array of Book
        Book[] books;
        books = new Book[bookList.size()];
        books = bookList.toArray(books);
        for (Book book : books) {
            if (book.getAuthor().contains(searchString) || book.getTitle().contains(searchString)) {
                searches.add(book);
            }
        }
        if (!searches.isEmpty()) {
            utilityMethods.displaySearches(searches);
        } else {
            System.out.println("No result for " + searchString);
        }
        return books;
    }

    @Override
    public boolean add(Book book, int quantity) {
        ArrayList<Book> bookList = utilityMethods.getBookDataFromURL();
        bookList.add(book);

        Book[] books;
        books = new Book[bookList.size()];
        books = bookList.toArray(books);
        for (Book singleBook : books) {
            System.out.println(singleBook.getTitle());
        }
        return true;
    }

    @Override
    public int[] buy(Book... books) {
        ShoppingCart shoppingCart = ShoppingCart.getInstance();
        Book[] items = shoppingCart.transferCartToRegister();
        ArrayList<Book> itemsInCart = new ArrayList<>(Arrays.asList(items));
        ArrayList<Book> bookList = utilityMethods.getBookDataFromURL();
        BigDecimal sum = BigDecimal.ZERO;
        int status;

        boolean bookDoesNotExist = false;
        boolean booksIsNotInStock = false;

        bookList.retainAll(itemsInCart);
        if (bookList.isEmpty()) {
            bookDoesNotExist = false;
        } else {
            bookDoesNotExist = true;
        }

        //Check  bookquantity
        for (Book item : items) {
            if (item.getQuantity() == 0) {
                booksIsNotInStock = true;
                System.out.println("Item: " + item.getTitle() + " is currently out of stock.");
            } else {
                booksIsNotInStock = false;
            }
        }

        if (bookDoesNotExist) {
            status = BuyStatus.DOES_NOT_EXIST.getBuyStatus();
            System.out.println("BuyStatus: " + status);
            System.exit(1);
        } else if (booksIsNotInStock) {
            status = BuyStatus.NOT_IN_STOCK.getBuyStatus();
            System.out.println("BuyStatus: " + status);
            System.exit(1);

        } else {
            status = BuyStatus.OK.getBuyStatus();
            System.out.println("BuyStatus: " + status);
            for (Book item : items) {
                BigDecimal total = utilityMethods.calculateTotalPrice(item.getQuantity(), item.getPrice());
                sum = sum.add(total);
                System.out.println("Total sum of your purchase is: " + "$" + sum);
                System.out.println("Thank you for your purchase!");
                System.exit(1);
            }
        }
        return new int[0];
    }
}




