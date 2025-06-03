package components;

public class Paiero extends Produto implements RestringivelPorIdade {
    private boolean artesanal;
    private static final int IDADE_MINIMA = 18;

    public Paiero(String id, String nome, double preco, int estoque, String descricao, boolean artesanal) {
        super(id, nome, preco, estoque, descricao);
        this.artesanal = artesanal;
    }

    public boolean isArtesanal() { return artesanal; }
    public void setArtesanal(boolean artesanal) { this.artesanal = artesanal; }

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
        return "Venda proibida para menores de " + IDADE_MINIMA + " anos.";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Artesanal: %s", artesanal ? "Sim" : "Não");
    }
}
