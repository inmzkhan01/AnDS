package streams;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class IncrementSupplier implements Supplier<Integer> {

    private int value;

    @Override
    public Integer get() {
        return ++value;
    }

    public static void main(String[] args) {
        Stream.generate(new IncrementSupplier());
    }
}
