package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException {
		try (Connection conn = new ConnectionPool().getConnection()) {
			conn.setAutoCommit(false);

			try (PreparedStatement stmt = conn.prepareStatement("insert into Produto (nome, descricao) values(?,?)",
					Statement.RETURN_GENERATED_KEYS)) {
				adiciona("TV LCD", "32 polegadas", stmt);
				adiciona("Bluray", "Full HDMI", stmt);
				conn.commit();
			} catch (Exception e){
				e.printStackTrace();
				conn.rollback();
				System.out.println("Rollback efetuado");
			}
		}
	}

	private static void adiciona(String nome, String descricao, PreparedStatement stmt) throws SQLException {

		if (nome.equals("Bluray")) {
			throw new IllegalArgumentException("Ocorreu um erro");
		}

		stmt.setString(1, nome);
		stmt.setString(2, descricao);

		boolean result = stmt.execute();
		System.out.println("Result " + result);
		try (ResultSet rs = stmt.getGeneratedKeys()) {
			while (rs.next()) {
				String id = rs.getString("id");
				System.out.println(id + " gerado");
			}
		}
	}

}
