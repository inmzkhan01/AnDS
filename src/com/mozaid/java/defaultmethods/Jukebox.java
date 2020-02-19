package defaultmethods;

interface Jukebox {
    default String rock() {
        return "... all over the world!";
    }
}
