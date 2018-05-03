
import se.chalmers.cse.dat216.project.CartEvent;

public class Main {

    public static void main(String[] args){



        Object t = new Object();
        CartEvent cartEvent = new CartEvent(t);
        
        System.out.println(cartEvent.getShoppingItem());
    }

}
