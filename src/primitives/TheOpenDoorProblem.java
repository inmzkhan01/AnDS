package primitives;

public class TheOpenDoorProblem {

    public static boolean isDoorOpen(int i) {
        double sqrt_i = Math.sqrt(i);
        int floor_sqrt_i = (int) Math.floor(sqrt_i);
        System.out.println("Total Door Open " + floor_sqrt_i);
        return floor_sqrt_i * floor_sqrt_i == i;
    }

    public static void main(String[] args) {
        System.out.println("is door open "+ isDoorOpen(500));
        System.out.println("is door open "+ isDoorOpen(10));
        System.out.println("is door open "+ isDoorOpen(9));
    }
}
