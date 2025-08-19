import org.junit.jupiter.api.Test;

import java.util.List;

public class HippodromeTest {
    @Test
    public void nullHorsesException(){
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Horses cannot be null.");
        }
    }

    @Test
    public void emptyHorsesException(){
        try {
            new Hippodrome( List.of());
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Horses cannot be empty.");
        }
    }

    @Test
    public void getHorsesTest(){
        List<Horse> horses = List.of(
                new Horse("Houdini", 2.4),
                new Horse("Comanche", 2.5),
                new Horse("Ranger", 2.6)
        );
        Hippodrome hippodrome = new Hippodrome(horses);
        assert hippodrome.getHorses().equals(horses);
    }

    @Test
    public void moveTest() {
        List<Horse> horses = List.of(
                new Horse("Houdini", 2.4),
                new Horse("Comanche", 2.5),
                new Horse("Ranger", 2.6)
        );
        Hippodrome hippodrome = new Hippodrome(horses);
        double initialDistance = horses.get(0).getDistance();
        hippodrome.move();
        assert horses.get(0).getDistance() > initialDistance;
    }

    @Test
    public void getWinnerTest() {
        List<Horse> horses = List.of(
                new Horse("Houdini", 2.4, 10),
                new Horse("Comanche", 2.5, 20),
                new Horse("Ranger", 2.6, 15)
        );
        Hippodrome hippodrome = new Hippodrome(horses);
        assert hippodrome.getWinner().getName().equals("Comanche");
    }
}
