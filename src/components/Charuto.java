package components;

public class Charuto extends Produto implements RestringivelPorIdade {

    private String aroma;
    private static final int IDADE_MINIMA = 21;

    public Charuto(String id, String nome, double preco, int estoque, String descricao, String aroma) {
        super(id, nome, preco, estoque, descricao);
        this.aroma = aroma;
    }

    public String aroma() { return aroma; }
    public void aroma(String aroma) { this.aroma = aroma; }

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
        return "Venda proibida para menores de " + IDADE_MINIMA + " anos. Uso restrito.";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Aroma: %s", aroma);
    }
}
