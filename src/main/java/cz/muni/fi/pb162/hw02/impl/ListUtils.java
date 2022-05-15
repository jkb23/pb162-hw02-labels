package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.error.InvalidExpressionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author Matus Jakab
 */
public final class ListUtils {
    private ListUtils() {
    }

    /**
     *
     * @param expression String to evaluate
     * @return list of operators
     * @throws InvalidExpressionException if operator is not surrounded by labels
     */
    public static List<String> getOperators(String expression){
        List<String> operators = new ArrayList<>();
        boolean seenLabel = false;
        boolean lastOperator = false;
        for (int i = 0; i < expression.length(); i++) {
            String chr = Character.toString(expression.charAt(i));
            if (chr.matches("[a-zA-Z]")){
                seenLabel = true;
                lastOperator = false;
                continue;
            }

            if (chr.equals("|") || chr.equals("&")) {
                if (!seenLabel){
                    throw new InvalidExpressionException(expression);
                }

                lastOperator = true;
                operators.add(Character.toString(expression.charAt(i)));
            }
        }

        if (lastOperator){
            throw new InvalidExpressionException(expression);
        }

        return operators;
    }

    /**
     *
     * @param expression String to evaluate
     * @return expression split to list
     */
    public static List<String> splitToList(String expression){
        String splitted = Arrays.toString(expression.split("\\|"));
        splitted = Arrays.toString(splitted.split("&"));
        splitted = splitted.replace("!!", "");
        splitted = splitted.replace("[", "");
        splitted = splitted.replace("]", "");
        splitted = splitted.replace(",", "");
        while (splitted.contains("  ")){
            splitted = splitted.replace("  ", " ");
        }

        return new ArrayList<>(Arrays.asList(splitted.split(" ")));
    }

    /**
     *
     * @param exprs list of expression split to list
     * @param labels set of labels
     * @return list of booleans from given labels
     */
    public static List<Boolean> listStringToBool(List<String> exprs, Set<String> labels){
        List<Boolean> ret = new ArrayList<>();
        for (String expr : exprs){
            if (expr.contains("!") && labels.contains(expr.replace("!", ""))){
                ret.add(false);
            } else if (expr.contains("!") && !labels.contains(expr.replace("!", ""))){
                ret.add(true);
            } else if (!expr.contains("!") && labels.contains(expr)){
                ret.add(true);
            } else {
                ret.add(false);
            }
        }

        return ret;
    }
}
