package com.arvores.avl.controller;

import com.arvores.avl.model.Arvore;
import com.arvores.avl.model.No;

public class AVL<T extends Comparable<T>> implements Arvore<T> {

    private No<T> no;

    @Override
    public Arvore<T> inserir(T dados) {
        no = inserir(dados, no);
        return this;
    }

    private No<T> inserir(T dados, No<T> no) {
        if (no == null) {
            return new No<>(dados);
        }
        if (dados.compareTo(no.getDados()) < 0) {
            no.setEsquerda(inserir(dados, no.getEsquerda()));

        } else if (dados.compareTo(no.getDados()) > 0) {
            no.setDireita(inserir(dados, no.getDireita()));

        } else {
            return no;
        }

        updateAltura(no);
        return rotacao(no);
    }

    @Override
    public void excluir(T dados) {
        no = excluir(dados, no);
    }

    private No<T> excluir(T dados, No<T> no) {
        if (no == null) {
            return null;
        }

        if (dados.compareTo(no.getDados()) < 0) {
            no.setEsquerda(excluir(dados, no.getEsquerda()));

        } else if (dados.compareTo(no.getDados()) > 0) {
            no.setDireita(excluir(dados, no.getDireita()));

        } else {
            if (no.getEsquerda() == null) {
                return no.getDireita();
            } else if (no.getDireita() == null) {
                return no.getEsquerda();
            }

            no.setDados(noMax(no.getEsquerda()));
            no.setEsquerda(excluir(no.getDados(), no.getEsquerda()));
        }

        updateAltura(no);
        return rotacao(no);
    }

    @Override
    public void percorrer() {
        preOrdem(no);
    }

    private void preOrdem(No<T> no) {
        if (no == null) {
            return;
        }
        System.out.println(no);
        preOrdem(no.getEsquerda());
        preOrdem(no.getDireita());
    }

    @Override
    public T noMax() {
        if (isVazio()) {
            return null;
        }
        return noMax(no);
    }

    private T noMax(No<T> no) {
        if (no.getDireita() != null) {
            return noMax(no.getDireita());
        }
        return no.getDados();
    }

    @Override
    public T noMin() {
        if (isVazio()) {
            return null;
        }
        return noMin(no);
    }

    private T noMin(No<T> no) {
        if (no.getEsquerda() != null) {
            return noMin(no.getEsquerda());
        }
        return no.getDados();
    }

    @Override
    public boolean isVazio() {
        return no == null;
    }

    private No<T> rotacao(No<T> no) {
        int equilibra = equilibrar(no);

        if (equilibra > 1) {
            if (equilibrar(no.getEsquerda()) < 0) {
                no.setEsquerda(giroEsquerda(no.getEsquerda()));
            }
            return giroDireita(no);
        }
        if (equilibra < -1) {
            if (equilibrar(no.getDireita()) > 0) {
                no.setDireita(giroDireita(no.getDireita()));
            }
            return giroEsquerda(no);
        }
        return no;
    }

    private No<T> giroDireita(No<T> no) {
        No<T> esquerdaNo = no.getEsquerda();
        No<T> centroNo = esquerdaNo.getDireita();

        esquerdaNo.setDireita(no);
        no.setEsquerda(centroNo);

        updateAltura(no);
        updateAltura(esquerdaNo);

        return esquerdaNo;
    }

    private No<T> giroEsquerda(No<T> no) {
        No<T> direitaNo = no.getDireita();
        No<T> centroNo = direitaNo.getEsquerda();

        direitaNo.setEsquerda(no);
        no.setDireita(centroNo);

        updateAltura(no);
        updateAltura(direitaNo);

        return direitaNo;
    }

    private void updateAltura(No<T> no) {
        int maxAltura = Math.max(altura(no.getEsquerda()), altura(no.getDireita()));
        no.setAltura(maxAltura + 1);
    }

    private int equilibrar(No<T> no) {
        return no != null ? altura(no.getEsquerda()) - altura(no.getDireita()) : 0;
    }

    private int altura(No<T> no) {
        return no != null ? no.getAltura() : 0;
    }
}