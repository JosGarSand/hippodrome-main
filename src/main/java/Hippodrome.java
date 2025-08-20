import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.isNull;

public class Hippodrome {
    private final static Logger logger = LogManager.getLogger(Hippodrome.class);


    private final List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        if (isNull(horses)) {

            logger.error("Horses list cannot be null.");

            throw new IllegalArgumentException("Horses cannot be null.");

        } else if (horses.isEmpty()) {

            logger.error( "Horse list is empty" );

            throw new IllegalArgumentException("Horses cannot be empty.");
        }

        this.horses = horses;

        logger.debug("Hippodrome created with {} horses.", horses.size());
    }

    public List<Horse> getHorses() {
        return Collections.unmodifiableList(horses);
    }

    public void move() {
        horses.forEach(Horse::move);
    }

    public Horse getWinner() {
        return horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();
    }
}
