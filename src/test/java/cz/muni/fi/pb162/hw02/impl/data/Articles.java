package cz.muni.fi.pb162.hw02.impl.data;

import java.util.Set;

public final class Articles {
    public static final Article BEES
            = new Article("About Bees",Set.of("News", "animals", "bees", "Nature"));
    public static final Article PLANTS
            = new Article("Fish eating plants", Set.of("News", "Nature", "plants", "fish"));
    public static final Article DOGS
            = new Article("Man's Best Friend", Set.of("News", "animals", "Society", "dogs"));
    public static final Article SHARKS
            = new Article("The Great Whites", Set.of("Story", "fish", "Nature", "animals"));
    public static final Article STING
            = new Article("That's Gonna Sting!", Set.of("News", "animals", "bees", "Health"));
    public static final Article MOBY
            = new Article("Moby-Dick Strikes Back", Set.of("Story", "fish", "Nature", "animals", "Society"));
    public static final Article POOH
            = new Article("Blame the Pooh", Set.of("Story", "animals", "honey", "bees", "bears")) ;
    public static final Article FERRETS
            = new Article("Do Ferrets bite?", Set.of("animals", "Nature"));

    private Articles() {
        // intentionally private
    }
}
