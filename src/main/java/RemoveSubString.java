public class RemoveSubString {

    public String removeSubString(String string, String subString) {
        if (string == null || subString == null) {
            throw new IllegalArgumentException("String and subString must not be null");
        }

        StringBuilder newString = new StringBuilder(string);
        int subStringLength = subString.length();
        int c = 0, y = 0, counter = 0;
        boolean exit = false, found = false;

        do {
            for(int i = 0; i < subStringLength; i++) {
                if(c < newString.length() && subString.charAt(i) == newString.charAt(c)) {
                    counter++;
                    c++;
                    y++;
                }
            }

            if(counter != subStringLength) {
                c++;
                counter = 0;
                y = 0;
            } else {
                found = true;
                exit = true;
            }

        } while(c < newString.length() && !exit);

        if(found) {
            newString.delete(c - y, c);
            return newString.toString();
        } else {
            return "Substring not found!";
        }
    }
}
