package defaultmethods;

interface Carriage {
    default String rock() {
        return "... from side to side";
    }
}
