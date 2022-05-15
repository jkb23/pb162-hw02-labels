package cz.muni.fi.pb162.hw02.impl;

import java.util.List;

/**
 * @author Matus Jakab
 */
public class Validator {
    private final String expression;

    /**
     *
     * @param expression String to validate
     */
    public Validator(String expression) {
        this.expression = expression.trim();
    }

    /**
     *
     * @return true if expression is valid, otherwise false
     */
    public boolean validate(){
        if (expression.equals("")){
            return false;
        }

        for (int i = 0; i < expression.length(); i++){
            String chr = Character.toString(expression.charAt(i));

            if (!chr.matches("[a-zA-Z]") && !" |!&".contains(chr)){
                return false;
            }

            if (chr.equals("!") && i != 0){
                String prev = Character.toString(expression.charAt(i - 1));
                if (!"! |&".contains(prev)) {
                    return false;
                }
            }
        }

        List<String> operators = ListUtils.getOperators(expression);
        List<String> exprs = ListUtils.splitToList(expression);
        return operators.size() + 1 == exprs.size();
    }
}
