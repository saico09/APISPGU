package cl.ips.pacs.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//ref: https://javaee.github.io/tutorial/bean-validation002.html
public class RequestService {

	@NotNull(message = "{message.error.nonulo}")
	private String RutEmpresa;
	
	@Email(message = "{message.error.email}")
	private String RutTrab;
	
	@Size(min=2, max=30, message = "{message.error.nonulo}")
	private String Contexto;
	private String PorVencer;
	private String Tramo;
	public String getRutEmpresa() {
		return RutEmpresa;
	}
	public void setRutEmpresa(String rutEmpresa) {
		RutEmpresa = rutEmpresa;
	}
	public String getRutTrab() {
		return RutTrab;
	}
	public void setRutTrab(String rutTrab) {
		RutTrab = rutTrab;
	}
	public String getContexto() {
		return Contexto;
	}
	public void setContexto(String contexto) {
		Contexto = contexto;
	}
	public String getPorVencer() {
		return PorVencer;
	}
	public void setPorVencer(String porVencer) {
		PorVencer = porVencer;
	}
	public String getTramo() {
		return Tramo;
	}
	public void setTramo(String tramo) {
		Tramo = tramo;
	}
	public RequestService(String rutEmpresa, String rutTrab, String contexto, String porVencer, String tramo) {
		super();
		RutEmpresa = rutEmpresa;
		RutTrab = rutTrab;
		Contexto = contexto;
		PorVencer = porVencer;
		Tramo = tramo;
	}
	public RequestService() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
