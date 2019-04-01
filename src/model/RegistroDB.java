package model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;

public class RegistroDB implements PropertyChangeListener {
	private String usuario, tipoConsulta, consulta;
	private int numRegistros;
	private Calendar fechaHora;

	public RegistroDB() {
	}

	/**
	 * Constructor de la clase RegistroDB
	 * 
	 * @param usuario
	 * @param tipoConsulta
	 * @param numRegistros
	 * @param fechaHora
	 */
	public RegistroDB(String usuario, String tipoConsulta, int numRegistros, Calendar fechaHora) {
		super();
		this.usuario = usuario;
		this.tipoConsulta = tipoConsulta;
		this.numRegistros = numRegistros;
		this.fechaHora = fechaHora;
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
	 * @return the tipoConsulta
	 */
	public String getTipoConsulta() {
		return tipoConsulta;
	}

	/**
	 * @param tipoConsulta
	 *            the tipoConsulta to set
	 */
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	/**
	 * @return the consulta
	 */
	public String getConsulta() {
		return consulta;
	}

	/**
	 * @param consulta
	 *            the consulta to set
	 */
	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	/**
	 * @return the numRegistros
	 */
	public int getNumRegistros() {
		return numRegistros;
	}

	/**
	 * @param numRegistros
	 *            the numRegistros to set
	 */
	public void setNumRegistros(int numRegistros) {
		this.numRegistros = numRegistros;
	}

	/**
	 * @return the fechaHora
	 */
	public Calendar getFechaHora() {
		return fechaHora;
	}

	/**
	 * @param fechaHora
	 *            the fechaHora to set
	 */
	public void setFechaHora(Calendar fechaHora) {
		this.fechaHora = fechaHora;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RegistroDB [usuario=" + usuario + ", tipoConsulta=" + tipoConsulta + ", consulta=" + consulta
				+ ", numRegistros=" + numRegistros + ", fechaHora=" + fechaHora.get(Calendar.HOUR_OF_DAY) + ":"
                + fechaHora.get(Calendar.MINUTE) + ":" + fechaHora.get(Calendar.SECOND) + " "
                + fechaHora.get(Calendar.DAY_OF_MONTH) + "/" + fechaHora.get(Calendar.MONTH) + "/"
                + fechaHora.get(Calendar.YEAR);
	}

}
