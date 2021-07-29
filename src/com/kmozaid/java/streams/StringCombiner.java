package streams;

public class StringCombiner {

    private String delimiter;
    private String prefix;
    private String suffix;

    private StringBuilder builder = new StringBuilder();

    public StringCombiner(String delimiter, String prefix, String suffix) {
        this.delimiter = delimiter;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public StringCombiner add(String element) {
        if (builder.toString().isEmpty()) {
            builder.append(prefix);
        } else {
            builder.append(delimiter+" ");
        }
        builder.append(element);
        return this;
    }

    public StringCombiner merge(StringCombiner other) {
        builder.append(other);
        return this;
    }

    @Override
    public String toString() {
        return builder.append(suffix).toString();
    }
}
