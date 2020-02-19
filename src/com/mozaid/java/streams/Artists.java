package streams;

import java.util.List;
import java.util.Optional;

public class Artists {
    private List<Artist> artists;

    public Artists(List<Artist> artists) {
        this.artists = artists;
    }

    /*
    public Artist getArtist(int index) {
        if (index < 0 || index >= artists.size()) {
            indexException(index);
        }
        return artists.get(index);
    }*/


    public Optional<Artist> getArtist(int index) {
        if (index < 0 || index >= artists.size()) {
            return Optional.empty();
        }
        return Optional.of(artists.get(index));
    }

    private void indexException(int index) {
        throw new IllegalArgumentException(index + "doesn't correspond to an Artist");
    }

    /*
    private String getArtistName(int index) {
        try {
            Artist artist = getArtist(index);
            return artist.getName();
        } catch (IllegalArgumentException ex) {
            return "unknown";
        }
    } */

    private String getArtistName(int index) {
        Optional<Artist> artist = getArtist(index);
        return artist.isPresent() ? artist.get().getName() : "unknown";
    }

    private String allArtistName() {
        StringBuilder reduced = artists.stream().
                map(Artist::getName)
                .reduce(new StringBuilder(), (builder, name) -> {
                        if (builder.length() > 0)
                            builder.append(", ");
                        return builder;
                    }, (left, right) -> left.append(right));
        reduced.insert(0, "[");
        reduced.append("]");
        return reduced.toString();
    }

    private String allArtistName2() {
        StringCombiner combined = artists.stream()
                .map(Artist::getName)
                .reduce(new StringCombiner(",", "[", "]"), StringCombiner::add, StringCombiner::merge);
        return combined.toString();
    }

    public static void main(String[] args) {
        Artists artists = new Artists(List.of(new Artist("Hozier"), new Artist("Daljeet"), new Artist("DJ Snake")));
        System.out.println("Using reduce with Java Builder :\n"+ artists.allArtistName());
        System.out.println("Using reduce with Customer Combiner :\n"+ artists.allArtistName2());

    }

}
