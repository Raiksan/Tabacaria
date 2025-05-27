package utils;

import components.*;

import java.util.Scanner;
import java.util.ArrayList;

public class Menu {

    private ArrayList<Produto> produtos = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public void spawn() {
        letterSpawn();
        int op;
        do {
            System.out.println("\n=== Menu Tabacaria ===");
            System.out.println("1 - Cadastrar produtos");
            System.out.println("2 - Listar produtos");
            System.out.println("0 - Sair");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    cadastroProdutos();
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (op != 0);
    }

    public void letterSpawn() {
        System.out.println(".___. .                      .     .                   ");
        System.out.println("  | _.|_  _. _. _.._.* _.   _| _   |   . . _.* _.._  _ ");
        System.out.println("  |(_][_)(_](_.(_][  |(_]  (_](/,  |___(_|(_.|(_][ )(_)");
    }

    private void cadastroProdutos() {
        System.out.println("Selecione o tipo de produto:");
        System.out.println("1 - Cigarro | 2 - Marijuana | 3 - Paiero");
        int tipo = sc.nextInt();
        sc.nextLine();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Preço: ");
        double preco = sc.nextDouble();

        System.out.print("Estoque: ");
        int estoque = sc.nextInt();
        sc.nextLine();

        System.out.print("Descrição: ");
        String descricao = sc.nextLine();

        Produto novoProduto;

        if (tipo == 1) {
            novoProduto = new Cigarro(nome, preco, estoque, descricao);
        } else if (tipo == 2) {
            novoProduto = new Marijuana(nome, preco, estoque, descricao);
        } else if (tipo == 3) {
            novoProduto = new Paiero(nome, preco, estoque, descricao);
        } else {
            System.out.println("Tipo inválido.");
            return;
        }

        produtos.add(novoProduto);
        System.out.println("Produto cadastrado com sucesso.");
    }

    private void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("\n--- Lista de Produtos ---");
        for (Produto p : produtos) {
            System.out.println(p);
        }
    }
}