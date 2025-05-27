package components;

public abstract class Produto {

    protected String nome;
    protected double preco;

    protected int estoque;
    protected String descricao;

    public abstract void reporEstoque();
    public abstract void vender(int qnt);
    public double getPreco() {
        return preco;
    }
}
