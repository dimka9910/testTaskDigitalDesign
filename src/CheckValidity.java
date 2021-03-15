public class CheckValidity {

    public static boolean isValid(String str){
        if (!isAppropriateSymbols(str)) {
            System.out.println("Inappropriate Symbols!");
            return false;
        }

        if (!isEverythingOkayWithBrackets(str)) {
            System.out.println("Maybe you missed some brackets");
            return false;
        }

        if (!isCoefficientsOnPlace(str)){
            System.out.println("Something wrong with Coefficients");
            return false;
        }
        return true;
    }

    private static boolean isAppropriateSymbols(String str){
        return !str.matches(".*[^a-zA-Z0-9\\[\\]].*");
    }

    public static boolean isEverythingOkayWithBrackets(String str){
        int bracketsCounter = 0;
        for (char c : str.toCharArray()) {
            if (c == '[')
                bracketsCounter++;
            else if (c == ']')
                bracketsCounter--;
            if (bracketsCounter < 0) {
                return false;
            }
        }
        if (bracketsCounter != 0)
            return false;
        return true;
    }

    public static boolean isCoefficientsOnPlace(String str){
        return !str.matches(".*[0-9][^\\[].*") && !str.matches(".*[^0-9]\\[.*")
                && !str.matches("\\[.*") && !str.matches(".*[0-9]");
    }
}
