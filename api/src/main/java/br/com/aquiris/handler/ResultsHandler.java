package br.com.aquiris.handler;


public class ResultsHandler {

    public static String handlerResult(final Object result) {
        if (result == null) return "null";
        else if (result instanceof Integer)
            if ((Integer) result == -1) return "null";
            else return String.valueOf(result);
        else return String.valueOf(result);
    }
}
