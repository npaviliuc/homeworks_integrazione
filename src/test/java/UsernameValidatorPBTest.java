import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Assertions;

public class UsernameValidatorPBTest {

    private UsernameValidator validator = null;

    @Provide
    Arbitrary<String> validUsernames() {
        return Arbitraries.strings()
                .withCharRange('a','z')
                .withCharRange('A','Z')
                .withCharRange('0','9')
                .withChars("_-.@#$%^&+=")
                .ofMinLength(6)
                .ofMaxLength(30);
    }

    @Provide
    Arbitrary<String> invalidUsernames() {
        return Arbitraries.strings()
                .filter(s -> s.length() < 6 || s.length() > 30 || !s.matches("^[a-zA-Z0-9_\\-.@#$%^&+=]*$"));
    }

    @Property
    void characterConstraints(@ForAll String username) {
        validator = new UsernameValidator(6, 30, true);
        if(!username.matches("^[a-zA-Z0-9_\\-.@#$%^&+=]*$")){
            Assertions.assertFalse(validator.isValid(username));
        }
    }

    @Property
    void validUsernames(@ForAll("validUsernames") String username) {
        validator = new UsernameValidator(6, 30, true);
        Assertions.assertTrue(validator.isValid(username));
    }

    void notNullNotEmpty() {
        validator = new UsernameValidator(6,30,true);
        Assertions.assertFalse(validator.isValid(null));
        Assertions.assertFalse(validator.isValid(""));
    }

    @Property
    void acceptableLengths(@ForAll @IntRange(min = 1, max = 5) int shortLength,
                          @ForAll @IntRange(min = 31, max = 40) int longLength) {
        validator = new UsernameValidator(6, 30, true);

        String userShort = "o".repeat(shortLength);
        String userLong = "o".repeat(longLength);

        Assertions.assertFalse(validator.isValid(userShort));
        Assertions.assertFalse(validator.isValid(userLong));
    }

    @Property
    void invalidUsernames(@ForAll("invalidUsernames") String username) {
       validator = new UsernameValidator(6, 30, true);
       Assertions.assertFalse(validator.isValid(username));
    }
}