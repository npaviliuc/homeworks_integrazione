import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class RemoveSubStringTest {

    private static RemoveSubString remove;

    @BeforeAll
    public static void init() {
        remove = new RemoveSubString();
    }

    // Prove per testare gli input
    @Test
    public void testVariousInputs() {
        Assertions.assertEquals("Ciao", remove.removeSubString("Ciao Mondo!", "Mondo!"));
        Assertions.assertEquals("stai?", remove.removeSubString("Come stai?", "Come"));
        Assertions.assertEquals("Substring not found!", remove.removeSubString("Che bella giornata!", "ciao"));
    }

    // Esecuzione T1
    @Test
    public void testStringNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> remove.removeSubString(null, "Ciao"));
    }

    // Esecuzione T2
    @Test
    public void testStringEmpty() {
        Assertions.assertEquals("Substring not found!", remove.removeSubString("","Bella"));
    }

    // Esecuzione T3
    @Test
    public void testSubstringNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> remove.removeSubString("Come va?", null));
    }

    // Esecuzione T4
    @Test
    public void testSubstringEmpty() {
        Assertions.assertEquals("Come andiamo?", remove.removeSubString("Come andiamo?", ""));
    }

    // Codice per flusso di parametri per i test successivi
    static Stream<Arguments> forOtherTests() {
        return Stream.of(
                Arguments.of("", remove.removeSubString("A","A")), // T5
                Arguments.of("Substring not found!", remove.removeSubString("A", "B")), // T6
                Arguments.of("iao", remove.removeSubString("Ciao","C")), //T7
                Arguments.of("Substring not found!", remove.removeSubString("Ciao","X")), // T8
                Arguments.of("Ye", remove.removeSubString("Yeah","ah")), // T9
                Arguments.of("Substring not found!", remove.removeSubString("Yeah","gg")) // T10
        );
    }

    // Esecuzione da T5 a T10
    @ParameterizedTest
    @MethodSource("forOtherTests")
    void getResults(String expected, String result) {
        Assertions.assertEquals(expected, result);
    }

    // Esecuzione T11
    @Test
    public void testStringEqualsToSubstring() {
        Assertions.assertEquals("", remove.removeSubString("Sono felice che il test funzioni", "Sono felice che il test funzioni"));
    }

    // Esecuzione T12
    @Test
    public void testSubstringGreaterThanString() {
        Assertions.assertEquals("Substring not found!", remove.removeSubString("Ehilà", "Ehilàboh"));
    }

    // Esecuzione T13
    @Test
    public void testSubstringWithNumber() {
        Assertions.assertEquals("Mancano  persone", remove.removeSubString("Mancano 10 persone", "10"));
    }

    // Esecuzione T14
    @Test
    public void testSubstringWithSpecialCharacter() {
        Assertions.assertEquals("Piacere mio", remove.removeSubString("Piacere mio!", "!"));
    }

    // Esecuzione T15
    @Test
    public void testSubstringWithSpaces() {
        Assertions.assertEquals("Tuttobene", remove.removeSubString("Tutto bene", " "));
    }

    // Esecuzione T16
    @Test
    public void testSubstringPresentMultipleTimes() {
        Assertions.assertEquals("testtest", remove.removeSubString("testtesttest", "test"));
    }
}