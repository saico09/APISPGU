package co.api.producer.apiproducerreglas.vo;

import java.util.Arrays;

public class UsuariosMasivos {

	private String[] ejecutaReglas;

	public String[] getEjecutaReglas() {
		return ejecutaReglas;
	}

	public void setEjecutaReglas(String[] ejecutaReglas) {
		this.ejecutaReglas = ejecutaReglas;
	}

	@Override
	public String toString() {
		return "UsuariosMasivos [ejecutaReglas=" + Arrays.toString(ejecutaReglas) + "]";
	}

}
