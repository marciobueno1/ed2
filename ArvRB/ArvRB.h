#ifndef __ARV_RB__
#define __ARV_RB__

typedef struct ArvRB {
  int info;
  struct ArvRB *esq;
  struct ArvRB *dir;
  bool verm;
} ArvRB;

bool RBehVerm(ArvRB *a);
void RBtrocarCor(ArvRB *a);
void RBinserir(int v, ArvRB *&x);
void RBpassearNivel(ArvRB *x);
#endif // __ARV_RB__
