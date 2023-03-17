package cl.gob.ips.poc.test_drl_poc_ips;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.kie.api.definition.type.Label;

public class RespuestaPACSVectorNuevo implements Serializable {
  static final long serialVersionUID = 1L;
  
  @Label("RUN")
  private String run;
  
  @Label("Evaluacion Descripcion")
  private String evaluacion;
  
  @Label("Tipo PACS")
  private String tipoPACS;
  
  @Label("Vector")
  private List<Integer> vector = new ArrayList<Integer>();
  
  @Label("Activar (oculto)")
  private Boolean activar;
  
  @Label("Numero Temporal")
  private Integer numerotmp;
  
  public RespuestaPACSVectorNuevo() {}
  
  public void addItem(Integer valor, String runNew) {
    if (runNew.equals(this.run) && !this.vector.contains(valor))
      this.vector.add(valor); 
  }
  
  public List<Integer> getVector() {
    return this.vector;
  }
  
  public void setVector(List<Integer> vector) {
    this.vector = vector;
  }
  
  public String getRun() {
    return this.run;
  }
  
  public void setRun(String run) {
    this.run = run;
  }
  
  public String getEvaluacion() {
    return this.evaluacion;
  }
  
  public void setEvaluacion(String evaluacion) {
    this.evaluacion = evaluacion;
  }
  
  public String getTipoPACS() {
    return this.tipoPACS;
  }
  
  public void setTipoPACS(String tipoPACS) {
    this.tipoPACS = tipoPACS;
  }
  
  public Boolean getActivar() {
    return this.activar;
  }
  
  public void setActivar(Boolean activar) {
    this.activar = activar;
  }
  
  public Integer getNumerotmp() {
    return this.numerotmp;
  }
  
  public void setNumerotmp(Integer numerotmp) {
    this.numerotmp = numerotmp;
  }
  
  public RespuestaPACSVectorNuevo(String run, String evaluacion, String tipoPACS, List<Integer> vector, Boolean activar, Integer numerotmp) {
    this.run = run;
    this.evaluacion = evaluacion;
    this.tipoPACS = tipoPACS;
    this.vector = vector;
    this.activar = activar;
    this.numerotmp = numerotmp;
  }

@Override
public String toString() {
	return "RespuestaPACSVectorNuevo [run=" + run + ", evaluacion=" + evaluacion + ", tipoPACS=" + tipoPACS
			+ ", vector=" + vector + ", activar=" + activar + ", numerotmp=" + numerotmp + "]";
}
  
  
}
