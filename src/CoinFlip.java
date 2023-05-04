import java.io.Serializable;
import java.util.Random;

public class CoinFlip implements Serializable {

    Random random;

    CoinFlip() {
        random = new Random();
    }

    CoinFlip(long seed) {
        random = new Random(seed);
    }

    public boolean flip() {
        return random.nextBoolean();
    }
}
