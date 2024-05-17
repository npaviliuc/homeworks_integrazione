public class UsernameValidator {

    private int minLength;

    private int maxLength;

    private boolean needsNumbersAndChars;


    public UsernameValidator(int minLength, int maxLength, boolean needsNumbersAndChars) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.needsNumbersAndChars = needsNumbersAndChars;
    }

    public boolean isValid(String userName) {
        if(userName == null || userName.isEmpty()) {
            return false;
        }

        if(userName.length() < minLength || userName.length() > maxLength) {
            return false;
        }

        if(needsNumbersAndChars && !userName.matches("^[a-zA-Z0-9_\\-.@#$%^&+=]*$")) {
            return false;
        }

        return true;
    }
}
