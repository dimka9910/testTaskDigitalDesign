import java.util.LinkedList;


/**
 * Element
 */
class Element{

    /**
     * исходная строка, и все вложенные строки разбиваются на 4 элемента, например имея строку "ae3[bc2[s]]d5[r]"
     * preBrackets - хранит данные до скобок - "ae"
     * multiplier - хранит коэффициент перед скобками - 3
     * inBrackets - хранит всё что внутри скобок "bc2[s]"
     * postBrackets - значения после скобок "d" до появления следующего коэффициента
     */
    private int multiplier;
    private String preBrackets;
    private String inBrackets;
    private String postBrackets;

    /**
     * Element
     * Конструктор для нулевого элемента
     * на вход исходная строка необходимая для обработки.
     * @param inBrackets in brackets
     */
    public Element(String inBrackets) {
        this.multiplier = 1;
        this.preBrackets = "";
        this.inBrackets = f(inBrackets);
        this.postBrackets = "";
    }

    /**
     * Element
     */
    public Element(int multiplier, String preBrackets, String inBrackets, String postBrackets) {
        this.multiplier = multiplier;
        this.preBrackets = preBrackets;
        this.inBrackets = f(inBrackets);
        this.postBrackets = postBrackets;
    }

    /**
     * F string
     *
     * @param str str
     * @return the string
     */
    public static String f(String str) {

        // так как строка может иметь на одном уровне несколько выражений в скобках, все элементы
        // парсятся в list 
        LinkedList<Element> list = new LinkedList<>();
        while (str.matches(".*\\[.*].*")) {

            long i;

            // парсинг строки до коэффициента
            i = str.chars().takeWhile(c -> !Character.isDigit(c)).count();
            String preBrackets = str.substring(0, (int) i);
            str = str.substring((int) i, str.length());

            // парсинг коэффициента
            i = str.chars().takeWhile(c -> Character.isDigit(c)).count();
            String num = str.substring(0, (int) i);
            int multiplier = num.equals("") ? 1 : Integer.parseInt(num);
            str = str.substring((int) i, str.length());

            // парсинг значений внутри скобок, метод closeBracketPos
            // позволяет найти позицию закрывающейся скобки
            i = closeBracketPos(str);
            String inBrackets = str.substring(1,(int)i);
            str = str.substring((int)i + 1, str.length());

            // парсинг строки до появления следующего коэффициента
            i = str.chars().takeWhile(c -> !Character.isDigit(c)).count();
            String postBrackets = str.substring(0,(int)i);
            str = str.substring((int)i, str.length());

            list.add(new Element(multiplier, preBrackets, inBrackets, postBrackets));
        }

        if (list.isEmpty())
            return str;

        String result = "";

        for(var v : list){
            System.out.println(v.inBrackets);
            System.out.println(v.multiplier);
            result = result + v.preBrackets + v.inBrackets.repeat(v.multiplier) + v.postBrackets;
        }
        return result;
    }

    /**
     * Close bracket pos long
     *
     * @param str str
     * @return the long
     */
    public static long closeBracketPos(String str){
        int pos = 0;
        int bracketsCounter = 0;

        for (char c : str.toCharArray()) {
            if (c == '[')
                bracketsCounter++;
            else if (c == ']')
                bracketsCounter--;
            if (bracketsCounter <= 0) {
                break;
            }
            pos++;
        }
        return pos;
    }

    /**
     * Get result string
     *
     * @return the string
     */
    public String getResult(){
        return inBrackets;
    }
}