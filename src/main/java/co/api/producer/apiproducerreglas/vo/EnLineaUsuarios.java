package co.api.producer.apiproducerreglas.vo;

import java.util.Arrays;

public class EnLineaUsuarios {

	private Integer[] posNumDocumento;
	private String[] ejecutaReglas;

	public Integer[] getPosNumDocumento() {
		return posNumDocumento;
	}

	public void setPosNumDocumento(Integer[] posNumDocumento) {
		this.posNumDocumento = posNumDocumento;
	}

	public String[] getEjecutaReglas() {
		return ejecutaReglas;
	}

	public void setEjecutaReglas(String[] ejecutaReglas) {
		this.ejecutaReglas = ejecutaReglas;
	}	
	
	@Override
	public String toString() {
		return "EnLineaUsuarios [posNumDocumento=" + Arrays.toString(posNumDocumento) + ", ejecutaReglas="
				+ Arrays.toString(ejecutaReglas) + "]";
	}

}
