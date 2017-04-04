#include <stdio.h>
#include <queue>
#include "ArvRB.h"

bool RBehVerm(ArvRB *a) {
  if (a != NULL) {
    return a->verm;
  }
  else {
    return false;
  }
}

void RBtrocarCor(ArvRB *a) {
  if (a != NULL) {
    a->verm = !a->verm;
    if (a->esq != NULL) {
      a->esq->verm = !a->esq->verm;
    }
    if (a->dir != NULL) {
      a->dir->verm = !a->dir->verm;
    }
  }
}

void RBrotacionarDireita(ArvRB *&a) {
  ArvRB *p = a->esq;
  a->esq = p->dir;
  p->dir = a;
  a->verm = !a->verm;
  p->verm = !p->verm;
  a = p;
}

void RBrotacionarEsquerda(ArvRB *&a) {
  ArvRB *p = a->dir;
  a->dir = p->esq;
  p->esq = a;
  a->verm = !a->verm;
  p->verm = !p->verm;
  a = p;
}

void RBrotacionarDuplaDireita(ArvRB *&a) {
  RBrotacionarEsquerda(a->esq);
  RBrotacionarDireita(a);
}

void RBrotacionarDuplaEsquerda(ArvRB *&a) {
  RBrotacionarDireita(a->dir);
  RBrotacionarEsquerda(a);
}

void RBrebalancear(ArvRB *&a, ArvRB *&p, ArvRB *&x) {
  if (RBehVerm(x) && p != NULL) {
    if (RBehVerm(p)) {
      if (a == NULL) {                              // caso 1
        p->verm = false;
      }
      else if (!RBehVerm(a)) {
        if (RBehVerm(a->esq) && RBehVerm(a->dir)) { // caso 2
          a->verm = true;
          a->esq->verm = a->dir->verm = false;
        }
        else if (p == a->esq) {
          if (x == p->esq) {                        // caso 3a
            RBrotacionarDireita(a);
          }
          else {                                    // caso 3d
            RBrotacionarDuplaDireita(a);
          }
        }
        else {
          if (x == p->dir) {                        // caso 3b
            RBrotacionarEsquerda(a);
          }
          else {                                    // caso 3c
            RBrotacionarDuplaEsquerda(a);
          }
        }
      }
    }
  }
  else if (RBehVerm(x)) {                           // nova raiz ser preta
    x->verm = false;
  }
}

void RBinserirRec(int v, ArvRB *&a, ArvRB *&p, ArvRB *&x) {
  if (x == NULL) {
    x = new ArvRB;
    x->esq = x->dir = NULL;
    x->info = v;
    x->verm = true;
  }
  else if (v < x->info) {
    RBinserirRec(v, p, x, x->esq);
  }
  else if (v > x->info) {
    RBinserirRec(v, p, x, x->dir);
  }
  RBrebalancear(a, p, x);
}

void RBinserir(int v, ArvRB *&x) {
  ArvRB *a = NULL, *p = NULL;
  RBinserirRec(v, a, p, x);
}

void RBpassearNivel(ArvRB *x) {
  std::queue<ArvRB *> fila;
  ArvRB *nula = NULL;
  ArvRB *elem;
  int nivel = 0;
  if (x != NULL) {
    fila.push(x);
    fila.push(nula);
    while (!fila.empty()) {
      elem = fila.front();
      fila.pop();
      if (elem == NULL) {
        ++nivel;
        printf("\n");
        if (!fila.empty()) {
          fila.push(nula);
        }
      }
      else {
        if (elem->esq != NULL) {
          fila.push(elem->esq);
        }
        if (elem->dir != NULL) {
          fila.push(elem->dir);
        }
        printf("%d(%s) ", elem->info, elem->verm ? "R" : "N");
      }
    }
  }
  printf("\n\n");
}