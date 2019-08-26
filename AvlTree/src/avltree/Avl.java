package avltree;

public class Avl {

    private Node root;
    private boolean status;
    private boolean added;
    
    public Avl() {
        this.root = null;
    }
    
    public boolean add(int info) {
        this.status = false;
        this.added = true;
        this.root = this.add(this.root, info);
        return this.added;
    }
    
    private Node add(Node root, int info) {
        if (root == null) {
            root = new Node(info);
            this.status = true;
        } else if (root.getInfo() == info) {
            this.added = false;
        } else if (info < root.getInfo()) {
            root.setLeft(add(root.getLeft(), info));
            if (this.status) {
                switch (root.getBalance()) {
                    case 1:
                        root.setBalance(0);
                        this.status = false;
                        break;
                    case 0:
                        root.setBalance(-1);
                        break;
                    case -1:
                        root = this.rotateRight(root);
                }
            }
        } else {
            root.setRight(add(root.getRight(), info));
            if (this.status) {
                switch (root.getBalance()) {
                    case -1:
                        root.setBalance(0);
                        this.status = false;
                        break;
                    case 0:
                        root.setBalance(1);
                        break;
                    case 1:
                        root = this.rotateLeft(root);
                }
            }
        }
        return root;
    }
    
    private Node rotateRight(Node a) {
        Node b = a.getLeft();
        if (b.getBalance() <= 0) { // rotação simples
            a.setLeft(b.getRight());
            b.setRight(a);
            if (b.getBalance() == -1) {
                a.setBalance(0);
                b.setBalance(0);
            } else {
                a.setBalance(-1);
                b.setBalance(1);
            }
            a = b;
        } else { // rotação dupla
            Node c = b.getRight();
            b.setRight(c.getLeft());
            a.setLeft(c.getRight());
            c.setLeft(b);
            c.setRight(a);
            if (c.getBalance() == -1) {
                a.setBalance(1);
                b.setBalance(0);
            } else if (c.getBalance() == 1) {
                a.setBalance(0);
                b.setBalance(-1);
            } else {
                a.setBalance(0);
                b.setBalance(0);
            }
            c.setBalance(0);
            a = c;
        }
        return a;
    }
    
    private Node rotateLeft(Node a) {
        Node b = a.getRight();
        if (b.getBalance() >= 0) { // rotação simples
            a.setRight(b.getLeft());
            b.setLeft(a);
            if (b.getBalance() == 1) { // REVER VALORES BALANCE
                a.setBalance(0);
                b.setBalance(0);
            } else {
                a.setBalance(1);
                b.setBalance(-1);
            }
            a = b;
        } else { // rotação dupla
            Node c = b.getLeft();
            b.setLeft(c.getRight());
            a.setRight(c.getLeft());
            c.setRight(b);
            c.setLeft(a);
            if (c.getBalance() == 1) { // REVER VALORES DO BALANCE
                a.setBalance(-1);
                b.setBalance(0);
            } else if (c.getBalance() == -1) {
                a.setBalance(0);
                b.setBalance(1);
            } else {
                a.setBalance(0);
                b.setBalance(0);
            }
            c.setBalance(0);
            a = c;
        }
        return a;
    }
    
    @Override
    public String toString() {
        return "" + (this.root != null ? this.root.getInfo() : null);
    }
}
