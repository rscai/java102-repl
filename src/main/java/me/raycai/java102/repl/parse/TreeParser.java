package me.raycai.java102.repl.parse;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import me.raycai.java102.repl.model.Addition;
import me.raycai.java102.repl.model.Division;
import me.raycai.java102.repl.model.Expression;
import me.raycai.java102.repl.model.Multiplication;
import me.raycai.java102.repl.model.Numeric;
import me.raycai.java102.repl.model.Subtraction;
import me.raycai.java102.repl.parse.Token.Type;

public class TreeParser extends Parser {

  private class Node {

    private Token value;
    private Optional<Node> leftChild;
    private Optional<Node> rightChild;
    private Optional<Node> parent;

    public Node(final Token value) {
      this.value = value;
      this.leftChild = Optional.empty();
      this.rightChild = Optional.empty();
      this.parent = Optional.empty();
    }

    public void addChild(final Optional<Node> node) {
      if (!node.isPresent()) {
        return;
      }
      if (!leftChild.isPresent()) {
        leftChild = node;
      } else if (!rightChild.isPresent()) {
        rightChild = node;
      }
      node.get().parent = Optional.of(this);
    }

    public void removeChild(final Optional<Node> node) {
      if (!node.isPresent()) {
        return;
      }
      if (rightChild.isPresent() && rightChild.equals(node)) {
        rightChild = Optional.empty();
      } else if (leftChild.isPresent() && leftChild.equals(node)) {
        leftChild = Optional.empty();
      }

      node.get().parent = Optional.empty();

    }
  }

  public TreeParser(final Lexer lexer) {
    super(lexer);
  }

  @Override
  protected Expression tokensToExpression(List<Token> tokens) throws ParseException {
    final Node tree = tokensToTree(tokens);
    return treeToExpression(tree);


  }

  private Node tokensToTree(final List<Token> tokens) throws ParseException {
    Optional<Node> lastNode = Optional.empty();
    for (final Token token : tokens) {
      final Optional<Node> newNode = Optional.of(new Node(token));
      if (!lastNode.isPresent()) {
        lastNode = newNode;
      } else {
        if (lastNode.get().leftChild.isPresent() && lastNode.get().rightChild.isPresent()) {
          throw new ParseException("Last node is full");
        }
        lastNode.get().addChild(newNode);
        lastNode = newNode;
        raise(lastNode.get());
      }
    }

    return findRoot(lastNode.get());
  }

  private Expression treeToExpression(final Node tree) throws ParseException {
    switch (tree.value.type) {
      case NUMERIC:
        if (tree.value.text.contains(".")) {
          return new Numeric(BigDecimal.valueOf(Double.valueOf(tree.value.text)));
        } else {
          return new Numeric(BigDecimal.valueOf(Long.valueOf(tree.value.text)));
        }
      case ADDITION:
        return new Addition(treeToExpression(tree.leftChild.get()),
            treeToExpression(tree.rightChild.get()));
      case SUBTRACTION:
        return new Subtraction(treeToExpression(tree.leftChild.get()),
            treeToExpression(tree.rightChild.get()));
      case MULTIPLICATION:
        return new Multiplication(treeToExpression(tree.leftChild.get()),
            treeToExpression(tree.rightChild.get()));
      case DIVISION:
        return new Division(treeToExpression(tree.leftChild.get()),
            treeToExpression(tree.rightChild.get()));
      default:
        throw new ParseException(String.format("Unexpected token type: %s", tree.value.type));
    }
  }

  private void raise(final Node lastNode) {
    if (lastNode.parent == null) {
      return;
    }
    while (lastNode.parent.isPresent() && !isLessThan(lastNode.parent.get(), lastNode)) {
      final Optional<Node> parent = lastNode.parent;
      final Optional<Node> parentOfParent =
          parent.isPresent() ? parent.get().parent : Optional.empty();
      final Optional<Node> leftChild = lastNode.leftChild;
      if (parentOfParent.isPresent()) {
        parentOfParent.get().removeChild(parent);

      }
      if (parent.isPresent()) {
        parent.get().removeChild(Optional.of(lastNode));
      }
      lastNode.removeChild(leftChild);

      if (parentOfParent.isPresent()) {
        parentOfParent.get().addChild(Optional.of(lastNode));
      }
      if (parent.isPresent()) {
        parent.get().addChild(leftChild);
      }
      lastNode.addChild(parent);

    }
  }

  private boolean isLessThan(final Node left, final Node right) {
    if (Arrays.asList(Type.ADDITION, Type.SUBTRACTION).contains(left.value.type) && Arrays
        .asList(Type.MULTIPLICATION, Type.DIVISION, Type.NUMERIC).contains(right.value.type)) {
      return true;
    }
    if (Arrays.asList(Type.MULTIPLICATION, Type.DIVISION).contains(left.value.type) && Arrays
        .asList(Type.NUMERIC).contains(right.value.type)) {
      return true;
    }
    return false;
  }

  private Node findRoot(final Node node) {
    Optional<Node> currentNode = Optional.of(node);
    while (currentNode.get().parent.isPresent()) {
      currentNode = currentNode.get().parent;
    }

    return currentNode.get();
  }
}
