package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.modelo.Categoria;
import br.com.caelum.jdbc.modelo.Produto;

public class ProdutosDAO {
	private Connection connection;

	public ProdutosDAO(Connection con) {
		this.connection = con;
	}

	public void salva(Produto produto) throws SQLException {

		String sql = "insert into Produto (nome, descricao) values (?,?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, produto.getNome());
			stmt.setString(2, produto.getDescricao());
			stmt.execute();

			try (ResultSet rs = stmt.getGeneratedKeys()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					produto.setId(id);
				}
			}
		}
	}

	public List<Produto> lista() throws SQLException {
		String sql = "select * from Produto";
		List<Produto> produtos = new ArrayList<>();
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.execute();
			transformaResultadoEmProdutos(produtos, stmt);
		}
		return produtos;
	}
	
	public List<Produto> busca(Categoria categoria) throws SQLException{
		String sql = "select * from Produto where categoria_id = ?";
		List<Produto> produtos = new ArrayList<>();
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, categoria.getId());
			stmt.execute();
			transformaResultadoEmProdutos(produtos, stmt);
		}
		return produtos;
	}

	private void transformaResultadoEmProdutos(List<Produto> produtos, PreparedStatement stmt) throws SQLException {
		try(ResultSet rs = stmt.getResultSet()){
			while(rs.next()){
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				Produto produto = new Produto(nome, descricao);
				produto.setId(id);
				produtos.add(produto);
			}
		}
	}

}
