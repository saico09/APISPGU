package cl.ips.pacs.vo;

import java.io.Serializable;
import org.kie.api.definition.type.Label;

public class DatosBeneficiarioPacsFijoVector implements Serializable {
  static final long serialVersionUID = 1L;
  
  @Label("RUN")
  private String run;
  
  @Label("Fecha Defuncion")
  private String fechaDefuncion;
  
  @Label("Edad")
  private int edad;
  
  @Label("Monto Inconsistencia")
  private int montoInconsistencia;
  
  @Label("Pension UF")
  private float pensionUF;
  
  @Label("Cotizaciones Exigidas")
  private int cotizacionesExigidas;
  
  @Label("Inconsistencia IPS")
  private int inconsistenciaIPS;
  
  @Label("Beneficiario PACS")
  private int beneficiarioPACS;
  
  @Label("Cotizaciones Posee Total")
  private int cotizacionesPoseeTotal;
  
  @Label("cotizaciones Posee AFP Dipreca Capredena")
  private int cotizacionesPoseeAFPDiCa;
  
  @Label("Pensionado")
  private int pensionado;
  
  public DatosBeneficiarioPacsFijoVector() {}
  
  public String getRun() {
    return this.run;
  }
  
  public void setRun(String run) {
    this.run = run;
  }
  
  public String getFechaDefuncion() {
    return this.fechaDefuncion;
  }
  
  public void setFechaDefuncion(String fechaDefuncion) {
    this.fechaDefuncion = fechaDefuncion;
  }
  
  public int getEdad() {
    return this.edad;
  }
  
  public void setEdad(int edad) {
    this.edad = edad;
  }
  
  public float getPensionUF() {
    return this.pensionUF;
  }
  
  public void setPensionUF(float pensionUF) {
    this.pensionUF = pensionUF;
  }
  
  public int getCotizacionesExigidas() {
    return this.cotizacionesExigidas;
  }
  
  public void setCotizacionesExigidas(int cotizacionesExigidas) {
    this.cotizacionesExigidas = cotizacionesExigidas;
  }
  
  public int getCotizacionesPoseeTotal() {
    return this.cotizacionesPoseeTotal;
  }
  
  public void setCotizacionesPoseeTotal(int cotizacionesPoseeTotal) {
    this.cotizacionesPoseeTotal = cotizacionesPoseeTotal;
  }
  
  public int getCotizacionesPoseeAFPDiCa() {
    return this.cotizacionesPoseeAFPDiCa;
  }
  
  public void setCotizacionesPoseeAFPDiCa(int cotizacionesPoseeAFPDiCa) {
    this.cotizacionesPoseeAFPDiCa = cotizacionesPoseeAFPDiCa;
  }
  
  public int getInconsistenciaIPS() {
    return this.inconsistenciaIPS;
  }
  
  public void setInconsistenciaIPS(int inconsistenciaIPS) {
    this.inconsistenciaIPS = inconsistenciaIPS;
  }
  
  public int getBeneficiarioPACS() {
    return this.beneficiarioPACS;
  }
  
  public void setBeneficiarioPACS(int beneficiarioPACS) {
    this.beneficiarioPACS = beneficiarioPACS;
  }
  
  public int getMontoInconsistencia() {
    return this.montoInconsistencia;
  }
  
  public void setMontoInconsistencia(int montoInconsistencia) {
    this.montoInconsistencia = montoInconsistencia;
  }
  
  public int getPensionado() {
    return this.pensionado;
  }
  
  public void setPensionado(int pensionado) {
    this.pensionado = pensionado;
  }
  
  public DatosBeneficiarioPacsFijoVector(String run, String fechaDefuncion, int edad, int montoInconsistencia, float pensionUF, int cotizacionesExigidas, int inconsistenciaIPS, int beneficiarioPACS, int cotizacionesPoseeTotal, int cotizacionesPoseeAFPDiCa, int pensionado) {
    this.run = run;
    this.fechaDefuncion = fechaDefuncion;
    this.edad = edad;
    this.montoInconsistencia = montoInconsistencia;
    this.pensionUF = pensionUF;
    this.cotizacionesExigidas = cotizacionesExigidas;
    this.inconsistenciaIPS = inconsistenciaIPS;
    this.beneficiarioPACS = beneficiarioPACS;
    this.cotizacionesPoseeTotal = cotizacionesPoseeTotal;
    this.cotizacionesPoseeAFPDiCa = cotizacionesPoseeAFPDiCa;
    this.pensionado = pensionado;
  }

@Override
public String toString() {
	return "DatosBeneficiarioPacsFijoVector [run=" + run + ", fechaDefuncion=" + fechaDefuncion + ", edad=" + edad
			+ ", montoInconsistencia=" + montoInconsistencia + ", pensionUF=" + pensionUF + ", cotizacionesExigidas="
			+ cotizacionesExigidas + ", inconsistenciaIPS=" + inconsistenciaIPS + ", beneficiarioPACS="
			+ beneficiarioPACS + ", cotizacionesPoseeTotal=" + cotizacionesPoseeTotal + ", cotizacionesPoseeAFPDiCa="
			+ cotizacionesPoseeAFPDiCa + ", pensionado=" + pensionado + "]";
}
  
  
}
