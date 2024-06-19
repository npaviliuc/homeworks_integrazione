import net.jqwik.*;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Provide;

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



}
