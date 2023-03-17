package co.api.producer.apiproducerreglas.vo;

public class GestionDelBeneficio {

	private String run;
	private Integer tipoBeneficio;
	private Integer beneficioVigente;
	private Integer fechaActual;
	private Integer estadoPostulacion;
	private Integer edadMeses;
	private String derechoPension;
	private Integer fechaNacimiento;
	private Integer codDipreca;
	private Integer codCapredena;
	private Boolean rshVigente;
	private Integer garantiaEstatal;
	private Integer pgu;
	private Integer pensionGracia;
	private Integer leyesEspeciales;
	private Integer pensionBase;
	private Integer pensionSuperior;
	private Boolean residencia;
	private Boolean tiempoEstadia;
	private Integer fechaDefuncion;

	public GestionDelBeneficio(
			String posRun,
			Integer tipBenef,
			Integer benefVig,
			Integer fechaHoy,
			Integer estPostu,
			Integer edaMes,
			String derechPens,
			Integer fechNace,
			Integer codDipr,
			Integer codCapr,
			Boolean rshVig,
			Integer garanEstat,
			Integer montoPGU,
			Integer pensGracia,
			Integer leyEspec,
			Integer pensBase,
			Integer pensSup,
			Boolean reside,
			Boolean tiempoEstad,
			Integer fechMuerte) {
		super();

		this.run = posRun;
		this.tipoBeneficio = tipBenef;
		this.beneficioVigente = benefVig;
		this.fechaActual = fechaHoy;
		this.estadoPostulacion = estPostu;
		this.edadMeses = edaMes;
		this.derechoPension = derechPens;
		this.fechaNacimiento = fechNace;
		this.codDipreca = codDipr;
		this.codCapredena = codCapr;
		this.rshVigente = rshVig;
		this.garantiaEstatal = garanEstat;
		this.pgu = montoPGU;
		this.pensionGracia = pensGracia;
		this.leyesEspeciales = leyEspec;
		this.pensionBase = pensBase;
		this.pensionSuperior = pensSup;
		this.residencia = reside;
		this.tiempoEstadia = tiempoEstad;
		this.fechaDefuncion = fechMuerte;
	}

	public GestionDelBeneficio(String run, Integer tipoBeneficio, Integer beneficioVigente, Integer fechaActual,
			Integer estadoPostulacion, Integer edadMeses, String derechoPension, Integer fechaNacimiento,
			Integer codDipreca, Integer codCapredena, Boolean rshVigente, Integer garantiaEstatal,
			Integer garantiaEstatal2, Integer pgu, Integer pensionGracia, Integer leyesEspeciales, Integer pensionBase,
			Integer pensionSuperior, Boolean residencia, Boolean tiempoEstadia, Boolean tiempoEstadia2,
			Integer fechaDefuncion) {
	}

	public String getRun() {
		return run;
	}

	public void setRun(String run) {
		this.run = run;
	}

	public Integer getTipoBeneficio() {
		return tipoBeneficio;
	}

	public void setTipoBeneficio(Integer tipoBeneficio) {
		this.tipoBeneficio = tipoBeneficio;
	}

	public Integer getBeneficioVigente() {
		return beneficioVigente;
	}

	public void setBeneficioVigente(Integer beneficioVigente) {
		this.beneficioVigente = beneficioVigente;
	}

	public Integer getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Integer fechaActual) {
		this.fechaActual = fechaActual;
	}

	public Integer getEstadoPostulacion() {
		return estadoPostulacion;
	}

	public void setEstadoPostulacion(Integer estadoPostulacion) {
		this.estadoPostulacion = estadoPostulacion;
	}

	public Integer getEdadMeses() {
		return edadMeses;
	}

	public void setEdadMeses(Integer edadMeses) {
		this.edadMeses = edadMeses;
	}

	public String getDerechoPension() {
		return derechoPension;
	}

	public void setDerechoPension(String derechoPension) {
		this.derechoPension = derechoPension;
	}

	public Integer getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Integer fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getCodDipreca() {
		return codDipreca;
	}

	public void setCodDipreca(Integer codDipreca) {
		this.codDipreca = codDipreca;
	}

	public Integer getCodCapredena() {
		return codCapredena;
	}

	public void setCodCapredena(Integer codCapredena) {
		this.codCapredena = codCapredena;
	}

	public Boolean getRshVigente() {
		return rshVigente;
	}

	public void setRshVigente(Boolean rshVigente) {
		this.rshVigente = rshVigente;
	}

	public Integer getGarantiaEstatal() {
		return garantiaEstatal;
	}

	public void setGarantiaEstatal(Integer garantiaEstatal) {
		this.garantiaEstatal = garantiaEstatal;
	}

	public Integer getPgu() {
		return pgu;
	}

	public void setPgu(Integer pgu) {
		this.pgu = pgu;
	}

	public Integer getPensionGracia() {
		return pensionGracia;
	}

	public void setPensionGracia(Integer pensionGracia) {
		this.pensionGracia = pensionGracia;
	}

	public Integer getLeyesEspeciales() {
		return leyesEspeciales;
	}

	public void setLeyesEspeciales(Integer leyesEspeciales) {
		this.leyesEspeciales = leyesEspeciales;
	}

	public Integer getPensionBase() {
		return pensionBase;
	}

	public void setPensionBase(Integer pensionBase) {
		this.pensionBase = pensionBase;
	}

	public Integer getPensionSuperior() {
		return pensionSuperior;
	}

	public void setPensionSuperior(Integer pensionSuperior) {
		this.pensionSuperior = pensionSuperior;
	}

	public Boolean getResidencia() {
		return residencia;
	}

	public void setResidencia(Boolean residencia) {
		this.residencia = residencia;
	}

	public Boolean getTiempoEstadia() {
		return tiempoEstadia;
	}

	public void setTiempoEstadia(Boolean tiempoEstadia) {
		this.tiempoEstadia = tiempoEstadia;
	}

	public Integer getFechaDefuncion() {
		return fechaDefuncion;
	}

	public void setFechaDefuncion(Integer fechaDefuncion) {
		this.fechaDefuncion = fechaDefuncion;
	}	

	@Override
	public String toString() {
		return "gestionDelSubsidio [run=" + run + ", tipoBeneficio=" + tipoBeneficio
				+ ", beneficioVigente=" + beneficioVigente + ", fechaActual=" + fechaActual + ", estadoPostulacion="
				+ estadoPostulacion + ", edadMeses=" + edadMeses + ", derechoPension=" + derechoPension
				+ ", fechaNacimiento=" + fechaNacimiento + ", codDipreca=" + codDipreca + ", codCapredena=" + codCapredena +
				", rshVigente=" + rshVigente +  ", garantiaEstatal=" + garantiaEstatal + ", pgu=" + pgu + 
				", pensionGracia=" + pensionGracia + ", leyesEspeciales=" + leyesEspeciales + ", pensionBase=" + pensionBase + 
				", pensionSuperior=" + pensionSuperior + ", residencia=" + residencia + ", tiempoEstadia=" + tiempoEstadia + 
				", fechaDefuncion=" + fechaDefuncion + "]";
	}

}
