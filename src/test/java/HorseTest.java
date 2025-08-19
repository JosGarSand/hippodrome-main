import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

public class HorseTest {

    @Test
    public void nullNameException() {
        assertThrows( IllegalArgumentException.class, () -> {
            new Horse( null, 1, 1 );
        } );
    }

    @Test
    public void nullNameMessage() {
        try {
            new Horse( null, 1, 1 );
        } catch (IllegalArgumentException e) {
            assertEquals( "Name cannot be null.", e.getMessage() );
        }
    }

    @Test
    public void blankNameException() {
        try {
            new Horse( "   ", 1, 1 );
        } catch (IllegalArgumentException e) {
            assertEquals( "Name cannot be blank.", e.getMessage() );
        }
    }

    @Test
    public void negativeSpeedException() {
        assertThrows( IllegalArgumentException.class, () -> {
            new Horse( "Test", -1, 1 );
        } );
    }

    @Test
    public void negativeSpeedMessage() {
        try {
            new Horse( "Test", -1, 1 );
        } catch (IllegalArgumentException e) {
            assertEquals( "Speed cannot be negative.", e.getMessage() );
        }
    }

    @Test
    public void negativeDistanceException() {
        assertThrows( IllegalArgumentException.class, () -> {
            new Horse( "Test", 1, -1 );
        } );
    }

    @Test
    public void negativeDistanceMessage() {
        try {
            new Horse( "Test", 1, -1 );
        } catch (IllegalArgumentException e) {
            assertEquals( "Distance cannot be negative.", e.getMessage() );
        }
    }

    @Test
    public void getName(){
        Horse horse = new Horse("Test", 1, 1);
        assertEquals("Test", horse.getName());
    }

    @Test
    public void getSpeed(){
        Horse horse = new Horse ("Test", 1, 1);
        assertEquals(1, horse.getSpeed());
    }

    @Test
    public void getDistance(){
        Horse horse = new Horse("Test", 1, 1);
        assertEquals(1, horse.getDistance());
    }

    @Test
    public void moveTest(){
        Horse horse = new Horse("Test", 10);
        horse.move();
        assertNotEquals(0, horse.getDistance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 0.9})
    void testMove_LlamaGetRandomDoubleYCalculaDistancia(double fakeRandom) {

        double speed = 10.0;
        double initialDistance = 5.0;
        Horse horse = new Horse("Test", speed, initialDistance);

        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {

            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9))
                    .thenReturn(fakeRandom);

            horse.move();

            double expected = initialDistance + speed * fakeRandom;
            assertEquals(expected, horse.getDistance());

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9), times(1));
        }
    }
}
