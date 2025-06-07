package components;

public class Cigarro extends Produto implements RestringivelPorIdade {

    private String sabor;
    private static final int IDADE_MINIMA = 18;

    public Cigarro(String id, String nome, double preco, int estoque, String descricao, String sabor) {
        super(id, nome, preco, estoque, descricao);
        this.sabor = sabor;
    }

    public String getSabor() { return sabor; }
    public void setSabor(String sabor) { this.sabor = sabor; }

    @Override
    public void vender(int quantidade) throws EstoqueInsuficienteException {
        if (quantidade <= 0)
            throw new IllegalArgumentException("Quantidade inválida para venda.");
        if (getEstoque() >= quantidade) {
            setEstoque(getEstoque() - quantidade);
        } else {
            throw new EstoqueInsuficienteException("Estoque insuficiente para '" + getNome() + "'. Disponível: " + getEstoque());
        }
    }

    @Override
    public int getIdadeMinimaRequerida() { return IDADE_MINIMA; }

    @Override
    public String getMensagemRestricao() {
        return "Venda e consumo proibidos para menores de " + IDADE_MINIMA + " anos.";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Sabor: %s", sabor);
    }
}
