package cz.muni.fi.pb162.hw02.impl;

import org.junit.jupiter.api.Test;

public abstract class AbstractLabelOperationsTest {

    protected abstract void shouldThrowOnExpr(String expr);

    @Test
    public void shouldThrowOnInvalidEmptyExpr() {
        shouldThrowOnExpr("");
    }

    @Test
    public void shouldThrowOnInvalidExprWithUnexpectedCharacter() {
        shouldThrowOnExpr(">");
    }

    @Test
    public void shouldThrowOnInvalidExprWithUnexpectedCharacterInLabel() {
        shouldThrowOnExpr("l*abel");
    }

    @Test
    public void shouldThrowOnInvalidExprWithMisplacedNot() {
        shouldThrowOnExpr("label!");
    }

    @Test
    public void shouldThrowOnInvalidExprWithMissingRhs() {
        shouldThrowOnExpr("label &");
    }

    @Test
    public void shouldThrowOnInvalidExprWithMissingLhs() {
        shouldThrowOnExpr("& label");
    }

    @Test
    public void shouldThrowOnInvalidExprWithMissingLhsInParentheses() {
        shouldThrowOnExpr("(& label)");
    }
}
