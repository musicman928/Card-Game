import java.util.Scanner;

public class PlayerInputHandler {
    Scanner in;
    public PlayerInputHandler(Scanner in) {
        this.in = in;
    }

    public boolean getCoinSide() {
        
        System.out.print("Pick a side, heads or tails: ");
        System.out.println();
        while (true) {
            String s = in.next();
            if (s.equalsIgnoreCase("heads")) {
                return true;
            } else if (s.equalsIgnoreCase("tails")) {
                return false;
            } else if (s.equalsIgnoreCase("/")) {
                System.out.println("You are absolutely hilarious, you should pursue a career in comedy!\nI would be delighted to watch your career crash and burn.");
            }
            else {
                System.out.println("Invalid choice, choose one of the following; heads / tails");
            }
        }
    }

}
