package components;

public abstract class Produto implements Vendavel {
    // Encapsulamento estrito
    private final String id;
    private String nome;
    private double preco;
    private int estoque;
    private String descricao;

    public Produto(String id, String nome, double preco, int estoque, String descricao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.descricao = descricao;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getEstoque() { return estoque; }
    public String getDescricao() { return descricao; }

    // Setters protegidos, permitindo modificação só para subclasses
    protected void setNome(String nome) { this.nome = nome; }
    protected void setPreco(double preco) { this.preco = preco; }
    protected void setEstoque(int estoque) { if (estoque >= 0) this.estoque = estoque; }
    protected void setDescricao(String descricao) { this.descricao = descricao; }

    public void reporEstoque(int quantidade) {
        if (quantidade > 0) {
            this.estoque += quantidade;
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Nome: %-15s | Preço: R$%.2f | Estoque: %d | Descrição: %s",
                id, nome, preco, estoque, descricao);
    }
}
