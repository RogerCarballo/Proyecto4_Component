package beans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import event.Evento;
import model.RegistroDB;

public class GestorBDBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1187624062513779766L;
	private String ip;
	private String usuario;
	private String database;
	private String password;
	private Connection connection;
	private Statement statement;
	private ResultSet resultset;
	private Evento evento;
	private PropertyChangeSupport changeSupp;

	/**
	 * Constructor de la clase GestorBDBean
	 */
	public GestorBDBean() {
		evento = new Evento();
		changeSupp = new PropertyChangeSupport(this);
		addPropertyChangeListener(evento);
	}

	/**
	 * Metodo conexion que permite conectarnos a la base de datos que hayamos
	 * indicado en el constructor.
	 */
	public void conexion(String ip, String database, String usuario, String password) {
		this.ip = ip;
		this.usuario = usuario;
		this.database = database;
		this.password = password;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + database, usuario, password);
		} catch (SQLException e) {
			System.err.println("Error en la conexion");
		}
	}

	public void select(String consulta) {
		RegistroDB registro = new RegistroDB();
		try {
			registro.setUsuario(this.usuario);
			registro.setTipoConsulta("SELECT");
			registro.setConsulta(consulta);
			registro.setBd(this.database);
			statement = connection.createStatement();
			resultset = statement.executeQuery(consulta);
			int count = 0;
			while (resultset.next()) {
				count++;
			}
			registro.setNumRegistros(count);
			registro.setFechaHora(Calendar.getInstance());
			changeSupp.firePropertyChange("SELECT", null, registro);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(String consulta) {
		RegistroDB registro = new RegistroDB();
		try {
			statement = connection.createStatement();
			int registros = statement.executeUpdate(consulta);
			registro.setUsuario(this.usuario);
			registro.setBd(this.database);
			registro.setTipoConsulta("delete");
			registro.setConsulta(consulta);
			registro.setNumRegistros(registros);
			registro.setFechaHora(Calendar.getInstance());
			changeSupp.firePropertyChange("DELETE", null, registro);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(String consulta) {
		RegistroDB registro = new RegistroDB();
		try {
			statement = connection.createStatement();
			int registros = statement.executeUpdate(consulta);
			registro.setBd(this.database);
			registro.setUsuario(this.usuario);
			registro.setTipoConsulta("update");
			registro.setConsulta(consulta);
			registro.setNumRegistros(registros);
			registro.setFechaHora(Calendar.getInstance());
			changeSupp.firePropertyChange("update", null, registro);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(String consulta) {
		RegistroDB registro = new RegistroDB();
		try {
			statement = connection.createStatement();
			int numRegistros = statement.executeUpdate(consulta);
			registro.setBd(this.database);
			registro.setUsuario(this.usuario);
			registro.setTipoConsulta("insert");
			registro.setConsulta(consulta);
			registro.setNumRegistros(numRegistros);
			registro.setFechaHora(Calendar.getInstance());
			changeSupp.firePropertyChange("INSERT", null, registro);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void procedure(String consulta) {
		RegistroDB registro = new RegistroDB();

		Statement st;
		try {
			st = (Statement) connection.createStatement();
			st.execute(consulta);
			registro.setBd(this.database);
			registro.setUsuario(this.usuario);
			registro.setTipoConsulta("procedure");
			registro.setConsulta(consulta);
			registro.setFechaHora(Calendar.getInstance());
			changeSupp.firePropertyChange("INSERT", null, registro);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error en el procedure");
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

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupp.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupp.removePropertyChangeListener(listener);
	}

	public List<RegistroDB> consultar(String db, String usuario, String tipoConsulta) {
		List<RegistroDB> consulta = new ArrayList<RegistroDB>();
		for (RegistroDB registro : evento.getRegistros()) {
			if (registro.getBd().equalsIgnoreCase(db) && registro.getUsuario().equalsIgnoreCase(usuario)
					&& registro.getTipoConsulta().equalsIgnoreCase(tipoConsulta)) {
				System.out.println(registro.getConsulta() + " " + registro.getFecha());
				consulta.add(registro);
			}

		}
		return consulta;

	}

	public List<RegistroDB> consultar(String db, String tipoDato) {
		List<RegistroDB> consulta = new ArrayList<RegistroDB>();
		switch (tipoDato.toLowerCase()) {
		case "select":
		case "delete":
		case "update":
		case "insert":
		case "procedure":
			for (RegistroDB registro : evento.getRegistros()) {
				if (registro.getBd().equalsIgnoreCase(db) && registro.getTipoConsulta().equalsIgnoreCase(tipoDato)) {
					consulta.add(registro);
					System.out
							.println(registro.getConsulta() + " " + registro.getFecha() + " " + registro.getUsuario());
				}
			}
			break;
		default:
			for (RegistroDB registro : evento.getRegistros()) {
				if (registro.getBd().equalsIgnoreCase(db) && registro.getUsuario().equalsIgnoreCase(tipoDato)) {
					System.out.println(
							registro.getConsulta() + " " + registro.getFecha() + " " + registro.getTipoConsulta());
					consulta.add(registro);
				}
			}
			break;
		}
		return consulta;
	}
}
