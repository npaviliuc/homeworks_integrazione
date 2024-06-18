import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RemoveSubStringTest {

    private static RemoveSubString remove;

    @BeforeAll
    public static void init() {
        remove = new RemoveSubString();
    }

    @Test
    public void testStringNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> remove.removeSubString(null, "Ciao"));
    }

    @Test
    public void testStringEmpty() {
        Assertions.assertEquals("Substring not found!", remove.removeSubString("","Bella"));
    }

    @Test
    public void testSubstringNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> remove.removeSubString("Come va?", null));
    }

    @Test
    public void testSubstringEmpty() {
        Assertions.assertEquals("Come andiamo?", remove.removeSubString("Come andiamo?", ""));
    }

    @Test
    public void testStringEqualsToSubstring() {
        Assertions.assertEquals("", remove.removeSubString("Sono felice che il test funzioni", "Sono felice che il test funzioni"));
    }

    @Test
    public void testSubstringPresentMultipleTimes() {
        Assertions.assertEquals("testtest", remove.removeSubString("testtesttest", "test"));
    }

    @Test
    public void testSubstringWithNumber() {
        Assertions.assertEquals("Mancano  persone", remove.removeSubString("Mancano 10 persone", "10"));
    }

    @Test
    public void testSubstringWithSpecialCharacter() {
        Assertions.assertEquals("Piacere mio", remove.removeSubString("Piacere mio!", "!"));
    }

    @Test
    public void testSubstringWithSpaces() {
        Assertions.assertEquals("Tuttobene", remove.removeSubString("Tutto bene", " "));
    }
}