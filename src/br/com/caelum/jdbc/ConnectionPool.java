package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.postgresql.jdbc3.Jdbc3PoolingDataSource;

@SuppressWarnings("deprecation")
public class ConnectionPool {
	
	private DataSource dataSource;

	public ConnectionPool() {
		Jdbc3PoolingDataSource pool = new Jdbc3PoolingDataSource();
		pool.setUrl("jdbc:postgresql://localhost:5432/loja_virtual");
		pool.setUser("root");
		pool.setPassword("root");
		pool.setMaxConnections(100);
		this.dataSource = pool;
	}
	
	public Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
}
