package com.marciobueno.ed2.rb;

public class NodeRB {
    private int info;
    private NodeRB esq;
    private NodeRB dir;
    private boolean verm;

    public NodeRB(int info) {
        this.info = info;
        this.esq = null;
        this.dir = null;
        this.verm = true;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public NodeRB getEsq() {
        return esq;
    }

    public void setEsq(NodeRB esq) {
        this.esq = esq;
    }

    public NodeRB getDir() {
        return dir;
    }

    public void setDir(NodeRB dir) {
        this.dir = dir;
    }

    public boolean isVerm() {
        return verm;
    }

    public void setVerm(boolean verm) {
        this.verm = verm;
    }
    
    public void movePretoParaBaixo() {
        this.esq.verm = false;
        this.dir.verm = false;
        this.verm = true;
    }
    
    public void trocaCor(NodeRB n) {
        boolean aux = this.verm;
        this.verm = n.verm;
        n.verm = aux;
    }
    
    @Override
    public String toString() {
        return (this.verm ? "V(" : "P(") + this.info + ")";
    }

}
