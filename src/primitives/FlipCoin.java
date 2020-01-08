package primitives;

public class FlipCoin {

    static String toss() {
        String output;
        double random = Math.random();

        if(random <= 0.5) {
            output = "Heads";
        } else {
            output = "Tails";
        }

        System.out.println(output);
        return output;
    }

    static String toss(double p) {
        String output;

        double random = Math.random();

        if(random <= p) {
            output = "Heads";
        } else {
            output = "Tails";
        }
        System.out.println(output);
        return output;
    }

    public static void main(String[] args) {
        toss(0.9);
        toss(0.9);
        toss(0.9);
        toss(0.2);

    }
}
