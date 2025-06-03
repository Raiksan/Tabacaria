package components;

public enum Cupom {
    SATANISTA(0.5, "Desconto de 50% pra você visitar seus amigos mais rápido lá em baixo!"),
    GADO(0.75, "Desconto de 25% para ajudar você a aguentar sua vida triste"),
    GRINGO(2.0, "Pra gringo é mais caro"),
    NENHUM(1.0, "Sem cupom aplicado.");

    private final double fator;
    private final String mensagem;

    Cupom(double fator, String mensagem) {
        this.fator = fator;
        this.mensagem = mensagem;
    }

    public double aplicar(double valor) {
        return valor * fator;
    }

    public String getMensagem() {
        return mensagem;
    }

    public static Cupom obterPorNome(String nome) {
        if (nome == null) return NENHUM;
        switch (nome.trim().toUpperCase()) {
            case "SATANISTA": return SATANISTA;
            case "GADO": return GADO;
            case "GRINGO": return GRINGO;
            default: return NENHUM;
        }
    }
}
