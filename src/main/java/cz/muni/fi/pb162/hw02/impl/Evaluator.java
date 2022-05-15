package cz.muni.fi.pb162.hw02.impl;

import cz.muni.fi.pb162.hw02.HasLabels;

import java.util.List;
import java.util.Set;

/**
 * @author Matus Jakab
 */
public class Evaluator {
    private final String expression;

    /**
     *
     * @param expression String to evaluate
     */
    public Evaluator(String expression){
        this.expression = expression;
    }

    /**
     *
     * @param operators list of operators left to right
     * @param bools list of booleans left to right
     * @return true if given booleans and operators are true, otherwise false
     */
    public boolean check(List<String> operators, List<Boolean> bools){
        boolean ret = bools.get(0);

        for (int i = 0; i < operators.size(); i++){
            if (operators.get(i).equals("|")) {
                ret = ret || bools.get(i + 1);
            } else {
                ret = ret && bools.get(i + 1);
            }
        }

        return ret;
    }

    /**
     *
     * @param labeled iterable of labeled items
     * @return true if expression is true, otherwise false
     */
    public boolean evaluate(HasLabels labeled){
        Set<String> labels = labeled.getLabels();
        List<String> operators = ListUtils.getOperators(expression);
        List<String> exprs = ListUtils.splitToList(expression);
        List<Boolean> bools = ListUtils.listStringToBool(exprs, labels);

        if (bools.size() == 1){
            return bools.get(0);
        }

        return check(operators, bools);
    }
}
