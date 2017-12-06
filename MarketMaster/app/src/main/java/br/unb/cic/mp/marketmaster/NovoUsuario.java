package br.unb.cic.mp.marketmaster;

/**
 * Classe que implementa um novo Usuário
 */

public class NovoUsuario {

    private String nome;
    private String email;
    private String cep;

    public NovoUsuario(String nome, String email, String cep) {
        this.nome = nome;
        this.email = email;
        this.cep = cep;
    }

    public NovoUsuario() {

    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCep() {
        return cep;
    }
}
