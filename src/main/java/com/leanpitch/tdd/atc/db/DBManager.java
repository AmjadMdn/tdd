package com.leanpitch.tdd.atc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.h2.tools.Server;

public class DBManager {
	
	private Server server;
	
	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public void startDb() throws SQLException{
		server = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/~/stackoverflow","sa","");
		return conn;
	}
	
	public void shutDownDb() throws SQLException{
		Server.shutdownTcpServer("tcp://localhost:9092", "", true, true);
	}

}
