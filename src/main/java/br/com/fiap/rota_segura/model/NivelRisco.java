package br.com.fiap.rota_segura.model;

public enum NivelRisco {
    BAIXO(0), MEDIO(60), ALTO(180), CRITICO(600);

    /** Penalidade (em segundos) somada ao custo de um trecho dentro de uma zona deste nível. */
    private final int penalidadeSegundos;

    NivelRisco(int penalidadeSegundos) {
        this.penalidadeSegundos = penalidadeSegundos;
    }

    public int getPenalidadeSegundos() {
        return penalidadeSegundos;
    }
}
