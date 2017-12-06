package br.unb.cic.mp.marketmaster;

/**
 * Classe que implementa uma Nova lista
 */

public class NovaLista {

    private String nome;
    private String descricao;

    public NovaLista(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public NovaLista() {

    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
