package com.arvores.avl;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arvores.avl.controller.AVL;
import com.arvores.avl.model.Arvore;

@SpringBootApplication
public class AvlApplication {

	public static void main(String[] args) {

		Arvore<Integer> avl = new AVL<>();

		avl.inserir(10).inserir(2).inserir(6).inserir(8).inserir(25)
				.inserir(18).inserir(35).inserir(15).inserir(22).inserir(42)
				.inserir(30).inserir(40).inserir(12).inserir(17).inserir(19)
				.inserir(24).inserir(28).inserir(33).inserir(38);

		avl.percorrer();
		System.out.println("maior no: " + avl.noMax());
		System.out.println("menor no: " + avl.noMin());

		System.out.println("excluindo nó..." + avl.noMax());
		avl.excluir(avl.noMax());

		avl.percorrer();
		System.out.println("maior no: " + avl.noMax());
		System.out.println("menor no: " + avl.noMin());

	}

}
