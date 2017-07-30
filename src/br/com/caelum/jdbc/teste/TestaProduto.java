package br.com.caelum.jdbc.teste;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.ConnectionPool;
import br.com.caelum.jdbc.dao.ProdutosDAO;
import br.com.caelum.jdbc.modelo.Produto;

public class TestaProduto {

	public static void main(String[] args) throws SQLException {
		Produto mesa = new Produto("Mesa azul", "Mesa com 4 pés");
		List<Produto> produtos = new ArrayList<>();

		try (Connection conn = new ConnectionPool().getConnection()) {
			ProdutosDAO dao = new ProdutosDAO(conn);
			dao.salva(mesa);
			produtos = dao.lista();
			for (Produto produto : produtos) {
				System.out.println("Produtos no banco: " + produto);
			}
		}
	}

}
