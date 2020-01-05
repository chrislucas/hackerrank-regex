package solution;

public class StartEndMetachar {

    private static final String [] SAMPLES = {
            "1aaaa."
            ,"1$$$$."
            ,"1++++."
            ,"1****."
            ,"abbbb."
            ,"1____."
    };

    public static void checker(String regex) {
        UtilsCheckRegex.checker(regex, SAMPLES);
    }

    /*
    * ^ corresponde ao inicio de uma String
    * $ corresponde ao fim
    *
    * */

    public static void main(String[] args) {
        /**
         * Tarefa: Escrever uma regex que corresponda com o padrao? Xxxxx.
         * x = word character
         * X = digito
         *
         * A string deve comecao com digito e terminar com ponto
         * */

        //checker("^\\d+\\w{4}\\.+$");
        checker("^\\d\\w{4}\\.$");
    }
}
