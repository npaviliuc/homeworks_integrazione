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
}
