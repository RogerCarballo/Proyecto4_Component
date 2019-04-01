package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import model.RegistroDB;

public class GestorBDBean implements Serializable {

	private String ip;
	private String usuario;
	private String database;
	private String password;
	private Connection connection;
	private Statement statement;
	private ResultSet resultset;
	private RegistroDB registro = new RegistroDB();

	/**
	 * Constructor de la clase GestorBDBean
	 */
	public GestorBDBean() {

	}

	/**
	 * Constructor de la clase GestorBDBean
	 * 
	 * @param ip
	 * @param database
	 * @param usuario
	 * @param password
	 */
	public GestorBDBean(String ip, String database, String usuario, String password) {
		this.ip = ip;
		this.usuario = usuario;
		this.database = database;
		this.password = password;
	}

	/**
	 * Metodo conexion que permite conectarnos a la base de datos que hayamos
	 * indicado en el constructor.
	 */
	private void conexion() {
		try {
			registro.setUsuario(usuario);
			connection = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + database, usuario, password);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void select(String consulta) {
		try {
			registro.setTipoConsulta("SELECT");
			registro.setConsulta(consulta);

			statement = connection.createStatement();
			resultset = statement.executeQuery(consulta);
			int count = 0;
			while (resultset.next()) {
				count++;
			}
			registro.setNumRegistros(count);
			registro.setFechaHora(Calendar.getInstance());

			System.out.println(registro.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(String consulta) {
		try {
			statement = connection.createStatement();

			int registros = statement.executeUpdate(consulta);

			registro.setUsuario(this.usuario);
			registro.setTipoConsulta("DELETE");
			registro.setConsulta(consulta);
			registro.setNumRegistros(registros);
			registro.setFechaHora(Calendar.getInstance());
			System.out.println(registro.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(String consulta) {
		try {
			statement = connection.createStatement();

			int registros = statement.executeUpdate(consulta);

			registro.setUsuario(this.usuario);
			registro.setTipoConsulta("UPDATE");
			registro.setConsulta(consulta);
			registro.setNumRegistros(registros);
			registro.setFechaHora(Calendar.getInstance());

			System.out.println(registro.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(String consulta) {
		try {
			statement = connection.createStatement();
			statement.executeUpdate(consulta);

			registro.setUsuario(this.usuario);
			registro.setTipoConsulta("INSERT");
			registro.setConsulta(consulta);
			registro.setNumRegistros(1);
			registro.setFechaHora(Calendar.getInstance());

			System.out.println(registro.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip
	 *            the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the database
	 */
	public String getDatabase() {
		return database;
	}

	/**
	 * @param database
	 *            the database to set
	 */
	public void setDatabase(String database) {
		this.database = database;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public static void main(String[] args) {
		GestorBDBean db = new GestorBDBean("localhost:3306", "bbdd_jbdc", "root", "");
		db.conexion();
		db.select("SELECT * FROM CLIENTES");
	}
}
