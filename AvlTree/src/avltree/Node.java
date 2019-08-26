package avltree;

public class Node {
    private int info;
    private Node left;
    private Node right;
    private int balance;
    
    public Node(int info) {
        this.info = info;
        this.left = null;
        this.right = null;
        this.balance = 0;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    
    
}
