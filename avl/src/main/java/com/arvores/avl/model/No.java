package com.arvores.avl.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class No<T extends Comparable<T>> {

    @NonNull
    private T dados;

    private int altura = 1;

    private No<T> esquerda;
    private No<T> direita;

}