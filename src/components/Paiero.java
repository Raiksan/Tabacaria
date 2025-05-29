package components;

import java.util.Scanner;

public class Paiero extends Produto implements Vendavel{

    public Paiero(String nome, double preco, int estoque, String descricao) {
        super(nome, preco, estoque, descricao);
    }

    @Override
    public void reporEstoque() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Quantos itens deseja adicionar ao estoque de " + nome + "? ");
        int qnt = sc.nextInt();
        estoque += qnt;
    }

    @Override
    public void vender(int qnt) {
        if (estoque >= qnt) {
            estoque -= qnt;
            System.out.println("Venda realizada com sucesso.");
        } else {
            System.out.println("Estoque insuficiente.");
        }
    }
}