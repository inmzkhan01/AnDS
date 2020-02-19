package defaultmethods;

public class MusicalCarriage implements Carriage, Jukebox {

    @Override
    public String rock() {
        //New super syntax to delegate
        return Carriage.super.rock();
    }

    public static void main(String[] args) {
        MusicalCarriage musicalCarriage = new MusicalCarriage();
        System.out.println(musicalCarriage.rock());
    }
}
