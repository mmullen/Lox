package com.craftinginterpreters.lox;

import java.util.List;

/**
 * Some terminology notes:
 * - Terminal: A letter from the grammar's alphabet. A literal value, for us. Terminals are individual lexemes for us. `if` or `1234`
 *   (think 'end' - once you hit them, no more parsing)
 * - NonTerminal: Named reference to another rule. "Play that rule and insert the result here".
 * - Derivations: Strings generated as a result of rules in the grammar
 * - Productions: AKA Rules. They produce strings in the grammar
 * - Precedence determines which operator is evaluated first in an expression containing a mixture of different operators. Higher precedence goes first.
 * - Associativity determines which operator is evaluated first in a series of the same operator. When an operator is left-associative (think “left-to-right”), operators on the left evaluate before those on the right.
 */

abstract class Expr {
  interface Visitor<R> {
    R visitBinaryExpr(Binary expr);
    R visitGroupingExpr(Grouping expr);
    R visitLiteralExpr(Literal expr);
    R visitUnaryExpr(Unary expr);
  }
  static class Binary extends Expr {
    Binary(Expr left, Token operator, Expr right) {
      this.left = left;
      this.operator = operator;
      this.right = right;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitBinaryExpr(this);
    }

    final Expr left;
    final Token operator;
    final Expr right;
  }
  static class Grouping extends Expr {
    Grouping(Expr expression) {
      this.expression = expression;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitGroupingExpr(this);
    }

    final Expr expression;
  }
  static class Literal extends Expr {
    Literal(Object value) {
      this.value = value;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitLiteralExpr(this);
    }

    final Object value;
  }
  static class Unary extends Expr {
    Unary(Token operator, Expr right) {
      this.operator = operator;
      this.right = right;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitUnaryExpr(this);
    }

    final Token operator;
    final Expr right;
  }

  abstract <R> R accept(Visitor<R> visitor);
}
