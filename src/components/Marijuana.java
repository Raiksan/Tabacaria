package components;

public class Marijuana extends Produto implements RestringivelPorIdade {
    private double teorTHC;
    private static final int IDADE_MINIMA = 21;

    public Marijuana(String id, String nome, double preco, int estoque, String descricao, double teorTHC) {
        super(id, nome, preco, estoque, descricao);
        this.teorTHC = teorTHC;
    }

    public double getTeorTHC() { return teorTHC; }
    public void setTeorTHC(double teorTHC) { this.teorTHC = teorTHC; }

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
        return super.toString() + String.format(" | Teor THC: %.2f%%", teorTHC);
    }
}
