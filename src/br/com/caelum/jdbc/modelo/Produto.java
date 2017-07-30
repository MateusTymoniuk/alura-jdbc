package br.com.caelum.jdbc.modelo;

public class Produto {
	
	private int id;
	private String nome;
	private String descricao;
	
	public Produto(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return String.format("[produto]-> id: %d, nome: %s, descricao: %s", id, nome, descricao);
	}
	
}
