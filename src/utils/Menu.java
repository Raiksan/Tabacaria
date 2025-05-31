package utils;

import components.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Menu {

    private ArrayList<Produto> produtos = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    private double caixa;
    private int vendasTotais;

    public void spawn() {
        letterSpawn();
        int op;
        do {
            System.out.println("\n=== Menu Tabacaria ===");
            System.out.println("1 - Cadastrar produtos");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Venda de produtos");
            System.out.println("4 - Reposição de produtos");
            System.out.println("5 - Exibir estatísticas da loja");
            System.out.println("6 - Remover produto da loja");
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
                case 3:
                    vendaDeProdutos();
                    break;
                case 4:
                    reporEstoque();
                    break;
                case 5:
                    exibirStats();
                    break;
                case 6:
                    removerProdutos();
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

        switch (tipo){
            case 1->{
                novoProduto=new Cigarro(nome,preco, estoque, descricao);
                produtos.add(novoProduto);
            }
            case 2->{
                novoProduto=new Marijuana(nome,preco, estoque, descricao);
                produtos.add(novoProduto);
            }
            case 3->{
                novoProduto=new Paiero(nome,preco, estoque, descricao);
                produtos.add(novoProduto);
            }
            default->System.out.println("Opção invalida");
        }

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

    private void vendaDeProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto foi cadastrado, logo, não pode ser comprado.");
            return;
        }

        listarProdutos();

        System.out.print("Digite o nome do produto que deseja comprar: ");
        String nomeBusca = sc.nextLine();

        Produto produtoSelecionado = null;

        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nomeBusca)) {
                produtoSelecionado = p;
                break;
            }
        }

        if (produtoSelecionado == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Digite a quantidade que deseja comprar: ");
        try {
            int quantidade = Integer.parseInt(sc.nextLine());

            if (produtoSelecionado instanceof Vendavel) {
                ((Vendavel) produtoSelecionado).vender(quantidade);
                vendasTotais++;
                caixa +=quantidade*produtoSelecionado.getPreco();
            } else {
                System.out.println("Este produto não pode ser vendido.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Quantidade inválida. Venda cancelada.");
        }
    }

    private void reporEstoque() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        listarProdutos();

        System.out.print("Digite o nome do produto para repor estoque: ");
        String nomeBusca = sc.nextLine();

        Produto produtoSelecionado = null;

        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nomeBusca)) {
                produtoSelecionado = p;
                break;
            }
        }

        if (produtoSelecionado == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        produtoSelecionado.reporEstoque();
    }

    private void exibirStats(){
        System.out.format("Foram realizadas %d vendas\nTotal de valor liquido:%f",vendasTotais,caixa);
    }

    private void removerProdutos(){
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto foi cadastrado, logo, não pode ser removido.");
            return;
        }
        listarProdutos();
        System.out.print("Digite o nome do produto que deseja remover: ");
        String nomeBusca = sc.nextLine();
        Produto produtoSelecionado = null;
        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nomeBusca)) {
                produtoSelecionado = p;
                break;
            }
        }
        if (produtoSelecionado == null) {
            System.out.println("Produto não encontrado.");
            return;
        }
        System.out.print("Você tem certeza que deseja remover esse produto?:");
        String rep = sc.nextLine();
        try{
            if(rep.equalsIgnoreCase("sim")){
                produtos.remove(produtoSelecionado);
            } else{
                return;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}