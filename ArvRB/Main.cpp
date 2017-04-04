#include <stdio.h>
#include "ArvRB.h"

int main() {
  ArvRB *raiz = NULL;
  RBinserir(10, raiz);
  RBpassearNivel(raiz);
  RBinserir(20, raiz);
  RBpassearNivel(raiz);
  RBinserir(30, raiz);
  RBpassearNivel(raiz);
  RBinserir(40, raiz);
  RBpassearNivel(raiz);
  RBinserir(50, raiz);
  RBpassearNivel(raiz);
  return 0;
}