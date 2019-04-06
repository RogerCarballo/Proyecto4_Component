package event;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import model.RegistroDB;

public class Evento implements PropertyChangeListener {

	private List<RegistroDB> registros = new ArrayList<>();

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		registros.add((RegistroDB) evt.getNewValue());
	}

	public List<RegistroDB> getRegistros() {
		return registros;
	}

	public void setRegistro(List<RegistroDB> registros) {
		this.registros = registros;
	}

	public RegistroDB getRegistro(int i) {
		return registros.get(i);
	}

	public void setLog(RegistroDB registro, int i) {
		this.registros.set(i, registro);
	}

}
