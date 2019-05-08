import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

class ShoppingCart {
    private static ShoppingCart instance = new ShoppingCart();
    private static Vector<Book> shoppingCart;

    public ShoppingCart() {
        shoppingCart = new Vector();
    }

    //Singelton Design Pattern
    public static ShoppingCart getInstance() {
        return instance;
    }

    public void addToCart(String title, String author, BigDecimal price, int quantity) {
        Book tempBook = new Book(title, author, price, quantity);
        shoppingCart.addElement(tempBook);

    }

    public void removeFromCart(String title) {
        for (int i = 0; i < shoppingCart.size(); i++) {
            Book bookToBeRemoved = shoppingCart.get(i);
            if (title.equals(bookToBeRemoved.getTitle())) {
                shoppingCart.remove(i);
                i--;
            }
        }
        System.out.println("Item " + title + " has been removed from your basket.");
    }

    public void getItemsInCart() {
        if (shoppingCart.size() == 0) {
            System.out.println("Shopping cart is currently empty");
        } else {
            System.out.println("Shopping basket: ");
            for (Book books : shoppingCart) {
                System.out.println(books);
            }
        }

    }

    public Book[] transferCartToRegister() {
        Book[] books = shoppingCart.toArray(new Book[shoppingCart.size()]);
        return books;
    }
}
