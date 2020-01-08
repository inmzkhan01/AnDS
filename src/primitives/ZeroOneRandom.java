package primitives;

public class ZeroOneRandom {

    public static int random() {
        double random = Math.random();

        if (random <= 0.5)
            return 0;
        else
            return 1;
    }
}
