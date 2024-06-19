import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.statistics.Histogram;
import net.jqwik.api.statistics.Statistics;
import net.jqwik.api.statistics.StatisticsReport;
import org.junit.jupiter.api.Assertions;

public class UsernameValidatorPBTest {

    private UsernameValidator validator = null;

    @Provide
    Arbitrary<String> validUsernamesGeneration() {
        return Arbitraries.strings()
                .withCharRange('a','z')
                .withCharRange('A','Z')
                .withCharRange('0','9')
                .withChars("_-.@#$%^&+=")
                .ofMinLength(6)
                .ofMaxLength(30);
    }

    @Provide
    Arbitrary<String> invalidUsernamesGeneration() {
        return Arbitraries.strings()
                .filter(s -> s.length() < 6 || s.length() > 30 || !s.matches("^[a-zA-Z0-9_\\-.@#$%^&+=]*$"));
    }

    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport(format = Histogram.class)
    void validUsernames(@ForAll("validUsernamesGeneration") String username) {
        validator = new UsernameValidator(6, 30, true);
        Assertions.assertTrue(validator.isValid(username));

        Statistics.collect("Length of valid usernames", username.length());
    }

    @Example
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
    @StatisticsReport(format = Histogram.class)
    void invalidUsernames(@ForAll("invalidUsernamesGeneration") String username) {
       validator = new UsernameValidator(6, 30, true);
       Assertions.assertFalse(validator.isValid(username));

       Statistics.collect("Length of invalid usernames", username.length());
    }
}