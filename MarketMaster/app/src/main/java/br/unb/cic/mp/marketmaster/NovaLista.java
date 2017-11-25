package br.unb.cic.mp.marketmaster;

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
