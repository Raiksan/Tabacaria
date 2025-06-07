package utils;

import components.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class Menu {
    private List<Produto> produtos = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private double caixa;
    private int vendasTotais;

    public void executar() {
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
            System.out.println("7 - Cupons disponíveis");
            System.out.println("0 - Sair");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> cadastrarProduto();
                case 2 -> listarProdutos();
                case 3 -> vendaDeProdutos();
                case 4 -> reporEstoque();
                case 5 -> exibirStats();
                case 6 -> removerProdutos();
                case 7 -> exibirCupons();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida.");
            }
        } while (op != 0);
    }

    private void letterSpawn() {
        System.out.println(".___. .                      .     .                   ");
        System.out.println("  | _.|_  _. _. _.._.* _.   _| _   |   . . _.* _.._  _ ");
        System.out.println("  |(_][_)(_](_.(_][  |(_]  (_](/,  |___(_|(_.|(_][ )(_)");
    }

    private String gerarID() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    private void cadastrarProduto() {
        System.out.println("Selecione o tipo de produto:");
        System.out.println("1 - Cigarro | 2 - Charuto | 3 - Paiero");
        int tipo = sc.nextInt();
        sc.nextLine();

        String id = gerarID();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Preço: ");
        double preco = sc.nextDouble();
        sc.nextLine();

        System.out.print("Estoque: ");
        int estoque = sc.nextInt();
        sc.nextLine();

        System.out.print("Descrição: ");
        String descricao = sc.nextLine();

        Produto novoProduto = null;

        switch (tipo) {
            case 1 -> {
                System.out.print("Sabor do cigarro: ");
                String sabor = sc.nextLine();
                novoProduto = new Cigarro(id, nome, preco, estoque, descricao, sabor);
            }
            case 2 -> {
                System.out.print("Aroma do charuto: ");
                String aroma = sc.nextLine();
                sc.nextLine();
                novoProduto = new Charuto(id, nome, preco, estoque, descricao, aroma);
            }
            case 3 -> {
                System.out.print("É artesanal? (sim/não): ");
                String resp = sc.nextLine();
                boolean artesanal = resp.equalsIgnoreCase("sim");
                novoProduto = new Paiero(id, nome, preco, estoque, descricao, artesanal);
            }
            default -> System.out.println("Opção inválida.");
        }

        if (novoProduto != null) {
            produtos.add(novoProduto);
            System.out.println("Produto cadastrado com sucesso.");
        }
    }

    private void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        System.out.println("\n--- Lista de Produtos ---");
        produtos.forEach(System.out::println);
    }

    private Produto buscarProdutoPorNome() {
        System.out.print("Digite o nome do produto: ");
        String nomeBusca = sc.nextLine();
        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nomeBusca.trim())) {
                return p;
            }
        }
        return null;
    }

    private void exibirCupons() {
        System.out.println("--- Cupons Disponíveis ---");
        for (Cupom c : Cupom.values()) {
            if (c != Cupom.NENHUM) {
                System.out.printf("Nome: %s\nMensagem: %s\n\n", c.name(), c.getMensagem());
            }
        }
    }


    private void vendaDeProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        listarProdutos();

        Produto produto = buscarProdutoPorNome();
        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        // Checa restrição de idade, se aplicável
        if (produto instanceof RestringivelPorIdade restrito) {
            System.out.println("ATENÇÃO: " + restrito.getMensagemRestricao());
            System.out.print("O cliente é maior de " + restrito.getIdadeMinimaRequerida() + " anos? (sim/não): ");
            String confirmacaoIdade = sc.nextLine().trim();
            if (!"sim".equalsIgnoreCase(confirmacaoIdade)) {
                System.out.println("Venda cancelada. O cliente não atende ao requisito de idade.");
                return;
            }
        }

        System.out.print("Digite a quantidade que deseja comprar: ");
        int quantidade;
        try {
            quantidade = Integer.parseInt(sc.nextLine());
            double valorBruto = quantidade * produto.getPreco();

            // Perguntar sobre cupom
            System.out.print("Deseja aplicar um cupom? (Digite o nome do cupom ou pressione Enter para nenhum): ");
            String nomeCupom = sc.nextLine();
            Cupom cupom = Cupom.obterPorNome(nomeCupom);

            double valorFinal = cupom.aplicar(valorBruto);

            // Mostrar mensagem do cupom, se aplicável
            if (cupom != Cupom.NENHUM) {
                System.out.println(cupom.getMensagem());
            }

            produto.vender(quantidade);
            vendasTotais++;
            caixa += valorFinal;
            System.out.printf("Total a pagar: R$ %.2f\n", valorFinal);
            System.out.println("Venda realizada com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Quantidade inválida. Venda cancelada.");
        } catch (EstoqueInsuficienteException | IllegalArgumentException e) {
            System.out.println("Erro na venda: " + e.getMessage());
        }
    }

        private void reporEstoque() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        listarProdutos();

        Produto produto = buscarProdutoPorNome();
        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Digite a quantidade para repor: ");
        int quantidade;
        try {
            quantidade = Integer.parseInt(sc.nextLine());
            produto.reporEstoque(quantidade);
            System.out.println("Estoque atualizado.");
        } catch (NumberFormatException e) {
            System.out.println("Quantidade inválida.");
        }
    }

    private void exibirStats() {
        System.out.printf("Foram realizadas %d vendas\nTotal de valor líquido: R$ %.2f\n", vendasTotais, caixa);
    }

    private void removerProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        listarProdutos();

        Produto produto = buscarProdutoPorNome();
        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Tem certeza que deseja remover? (sim/não): ");
        String resposta = sc.nextLine();

        if (resposta.equalsIgnoreCase("sim")) {
            produtos.remove(produto);
            System.out.println("Produto removido com sucesso.");
        } else {
            System.out.println("Remoção cancelada.");
        }
    }
}
