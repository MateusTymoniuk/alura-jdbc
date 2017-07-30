package br.com.caelum.jdbc.teste;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.caelum.jdbc.ConnectionPool;
import br.com.caelum.jdbc.dao.CategoriasDAO;
import br.com.caelum.jdbc.dao.ProdutosDAO;
import br.com.caelum.jdbc.modelo.Categoria;
import br.com.caelum.jdbc.modelo.Produto;

public class TestaCategorias {

	public static void main(String[] args) throws SQLException {
		try (Connection conn = new ConnectionPool().getConnection()) {
			List<Categoria> categorias = new CategoriasDAO(conn).listaComProdutos();
			for (Categoria categoria : categorias) {
				System.out.println(categoria.getNome());
				for (Produto produto : new ProdutosDAO(conn).busca(categoria)) {
					System.out.println(categoria.getNome() + " - " + produto.getNome());
				}
			}
		}
	}

}
