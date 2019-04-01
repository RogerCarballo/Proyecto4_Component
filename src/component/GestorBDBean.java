package component;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestorBDBean implements Serializable {

	private String ip;
	private String usuario;
	private String database;
	private String password;

	public GestorBDBean() {

	}

	public GestorBDBean(String ip, String usuario, String database, String password) {
		this.ip = ip;
		this.usuario = usuario;
		this.database = database;
		this.password = password;
	}

	private void conexion() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + database, usuario,
					password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		GestorBDBean db = new GestorBDBean("localhost:3306", "forhonor", "root", "");
		db.conexion();
	}
}
