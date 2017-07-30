package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {
		Connection conn = new ConnectionPool().getConnection();
		Statement stmt = conn.createStatement();
		boolean execute = stmt.execute("delete from Produto where id>3");
		int updateCount = stmt.getUpdateCount();
		System.out.println(updateCount + " linhas atualizadas");
		
		stmt.close();
		conn.close();
	}

}
