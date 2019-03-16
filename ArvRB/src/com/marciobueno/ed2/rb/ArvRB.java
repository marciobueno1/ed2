package com.marciobueno.ed2.rb;

import java.util.ArrayDeque;

public class ArvRB {
    NodeRB raiz;

    public ArvRB() {
        raiz = null;
    }
    
    public void inserir(int info) {
        this.raiz = inserir(this.raiz, info);
        this.raiz.setVerm(false);
    }

    private NodeRB inserir(NodeRB a, int info) {
        NodeRB p, t, x;
        if (a == null) {
            a = new NodeRB(info);
        } else if (info < a.getInfo()) {
            a.setEsq(inserir(a.getEsq(), info));
            p = a.getEsq();
            if (p.isVerm()) {
                t = a.getDir();
                if (t != null && t.isVerm()) {
                    a.movePretoParaBaixo();
                } else if (p.getEsq() != null && p.getEsq().isVerm()) { // rotação simples
                    a = rotacaoDir(a);
                } else if (p.getDir() != null && p.getDir().isVerm()) { // rotação dupla
                    a.setEsq(rotacaoEsq(p));
                    a = rotacaoDir(a);
                }
            }
        } else if (info > a.getInfo()) {
            a.setDir(inserir(a.getDir(), info));
            p = a.getDir();
            if (p.isVerm()) {
                t = a.getEsq();
                if (t != null && t.isVerm()) {
                    a.movePretoParaBaixo();
                } else if (p.getDir() != null && p.getDir().isVerm()) { // rotação simples
                    a = rotacaoEsq(a);
                } else if (p.getEsq() != null && p.getEsq().isVerm()) { // rotação dupla
                    a.setDir(rotacaoDir(p));
                    a = rotacaoEsq(a);
                }
            }
        } else {
            System.out.println("Valor duplicado não inserido!");
        }
        return a;
    }
    
    private NodeRB rotacaoEsq(NodeRB a) {
        boolean aux;
        NodeRB p = a.getDir();
        a.setDir(p.getEsq());
        p.setEsq(a);
        a.trocaCor(p);
        return p;
    }

    private NodeRB rotacaoDir(NodeRB a) {
        boolean aux;
        NodeRB p = a.getEsq();
        a.setEsq(p.getDir());
        p.setDir(a);
        a.trocaCor(p);
        return p;        
    }
    
    public void passeioNivel() {
        ArrayDeque<NodeRB> fila = new ArrayDeque<NodeRB>();
        NodeRB elem, nula = new NodeRB(0);
        int nivel = 0;
        System.out.print(nivel + ": ");
        if (this.raiz != null) {
            fila.add(this.raiz);
            fila.add(nula);
            while (!fila.isEmpty()) {
                elem = fila.remove();
                if (elem == nula) {
                    ++nivel;
                    System.out.println();
                    System.out.print(nivel + ": ");
                    if (!fila.isEmpty()) {
                        fila.add(nula);
                    }
                } else {
                    if (elem.getEsq() != null) {
                        fila.add(elem.getEsq());
                    }
                    if (elem.getDir() != null) {
                        fila.add(elem.getDir());
                    }
                    System.out.print(elem + " ");
                }
            }
        }
        System.out.print("\n\n");
    }
}
