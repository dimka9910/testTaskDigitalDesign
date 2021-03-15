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
        // добавляются в list
        LinkedList<Element> list = new LinkedList<>();

        //так как исходная строка проверена на валидность, достаточно выполнять алгоритм
        //до того как в оставшейся подстроке встречаются скобки
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

            //в list добавляется новый элемент, конструктор которого
            //инициализирует поле inBrackets рекурсивно
            //рекурсия будет продолжаться до тех пор пока имеются в строке внутри скобок другие скобки
            list.add(new Element(multiplier, preBrackets, inBrackets, postBrackets));
        }

        if (list.isEmpty())
            return str;

        StringBuilder result = new StringBuilder();
        for(var v : list){
            System.out.println(v.inBrackets);
            System.out.println(v.multiplier);
            //результат складывается проходя по всем элементам list, имеющим все выражения на одном уровне
            //поле inBracket будет иметь уже рекурсивно раскрытые выражения
            result.append(v.preBrackets).append(v.inBrackets.repeat(v.multiplier)).append(v.postBrackets);
        }
        return result.toString();
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
     * Метод возвращаюший результат алгоритма, хранящийся в нулевом элементе
     * @return the string
     */
    public String getResult(){
        return inBrackets;
    }
}