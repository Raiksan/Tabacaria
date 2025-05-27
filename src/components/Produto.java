package components;

public abstract class Produto {

    protected String nome;
    protected double preco;
    protected int estoque;
    protected String descricao;

    public Produto(String nome, double preco, int estoque, String descricao) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.descricao = descricao;
    }

    public abstract void reporEstoque();
    public abstract void vender(int qnt);

    public double getPreco() {
        return preco;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Nome do produto: " + nome + " || Preço: R$" + preco + " || Estoque: " + estoque + " || Descrição: " + descricao;
    }
}