package me.raycai.java102.repl.parse;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
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
    private Node leftChild;
    private Node rightChild;
    private Node parent;

    public Node(final Token value) {
      this.value = value;
    }

    public Node addChild(final Node node) {
      if (node == null) {
        return null;
      }
      if (leftChild == null) {
        leftChild = node;
      } else if (rightChild == null) {
        rightChild = node;
      }
      node.parent = this;

      return node;
    }

    public Node removeChild(final Node node) {
      if (node == null) {
        return null;
      }
      if (rightChild == node) {
        rightChild = null;
      } else if (leftChild == node) {
        leftChild = null;
      }

      node.parent = null;

      return node;
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
    Node lastNode = null;
    for (final Token token : tokens) {
      final Node newNode = new Node(token);
      if (lastNode == null) {
        lastNode = newNode;
      } else {
        if (lastNode.leftChild != null && lastNode.rightChild != null) {
          throw new ParseException("Last node is full");
        }
        lastNode.addChild(newNode);
        lastNode = newNode;
        raise(lastNode);
      }
    }

    return findRoot(lastNode);
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
        return new Addition(treeToExpression(tree.leftChild), treeToExpression(tree.rightChild));
      case SUBTRACTION:
        return new Subtraction(treeToExpression(tree.leftChild), treeToExpression(tree.rightChild));
      case MULTIPLICATION:
        return new Multiplication(treeToExpression(tree.leftChild),
            treeToExpression(tree.rightChild));
      case DIVISION:
        return new Division(treeToExpression(tree.leftChild), treeToExpression(tree.rightChild));
      default:
        throw new ParseException(String.format("Unexpected token type: %s", tree.value.type));
    }
  }

  private void raise(final Node lastNode) {
    if (lastNode.parent == null) {
      return;
    }
    while (lastNode.parent != null && !isLessThan(lastNode.parent, lastNode)) {
      final Node parent = lastNode.parent;
      final Node parentOfParent = lastNode.parent.parent;
      final Node leftChild = lastNode.leftChild;
      if (parentOfParent != null) {
        parentOfParent.removeChild(parent);
        
      }
      parent.removeChild(lastNode);
      lastNode.removeChild(leftChild);
      
      if (parentOfParent != null) {
        parentOfParent.addChild(lastNode);
      }
      parent.addChild(leftChild);
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
    Node currentNode = node;
    while (currentNode.parent != null) {
      currentNode = currentNode.parent;
    }

    return currentNode;
  }
}
