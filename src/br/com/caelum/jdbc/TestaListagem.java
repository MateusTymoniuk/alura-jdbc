package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem {
	public static void main(String[] args) throws SQLException {
		ConnectionPool database = new ConnectionPool();

		for (int i = 0; i < 100; i++) {
			Connection conn = database.getConnection();
			Statement stmt = conn.createStatement();
			boolean result = stmt.execute("select * from Produto");
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				System.out.println("ID: " + id + "\nNOME: " + nome + "\nDESCRICAO: " + descricao);
			}
			System.out.println(result);
			rs.close();
			stmt.close();
			conn.close();
		}
	}
}
