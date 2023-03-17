package cl.ips.pacs.vo;

public class ElegibilidadRequest {
	
	int codigoTipoElegibilidad;
	int run;
	String dv;
	int rutEntidad;
	String dvEntidad;
	String apellidoPaterno;
	String apellidoMaterno;
	String nombre;
	String fechaNacimiento;
	int edad;
	String fechaDefuncion;
	String sexo;
	String fechaPension;
	int numeroCotizacionesPACS;
	int cotizacionesAFP;
	int cotizacionesDIPRECA;
	int cotizacionesCAPREDENA;
	int cotizacionesIPS;
	String bonoReconocimientoCAPREDENA;
	String bonoReconocimientoDIPRECA;
	String bonoReconocimientoIPS;
	float montoPensionPagadaRP;
	float montoPensionPagadaRV;
	float montoPensionDIPRECA;
	float montoPensionCAPREDENA;
	float montoPensionMutual;
	float montoPilarSolidario;
	float montoSubsidioDependencia;
	float montoSeguroDependecia;
	float montoPensionIPS;
	float montoTGR;
	String fechaDevengamiento;
	String vectorPACSFijo;
	String vectorPACSVariable;
	String entidadPagadora;
	float montoBeneficioFijopesos;
	float montoBeneficioVariablePesos;
	float montoREBAJABeneficioFijopesos;
	float montoREBAJABeneficioVariablePesos;
	float valorUFUtilizado;
	String periodo;
	String fechaCreacion;
	String fechaActualizacion;
	float montoInicialPACS;
	float pensionTotalUF;
	float totalPensiones;
	String tipoPACS;
	int beneficiarioPACSFijo;
	int beneficiarioPACSVariable;
	int informadoPCPVID;
	int montoInconsistencia;
	
	int cotizacionesExigidas;
	
	
	
	public int getCodigoTipoElegibilidad() {
		return codigoTipoElegibilidad;
	}
	public void setCodigoTipoElegibilidad(int codigoTipoElegibilidad) {
		this.codigoTipoElegibilidad = codigoTipoElegibilidad;
	}
	public int getRun() {
		return run;
	}
	public void setRun(int run) {
		this.run = run;
	}
	public String getDv() {
		return dv;
	}
	public void setDv(String dv) {
		this.dv = dv;
	}
	public int getRutEntidad() {
		return rutEntidad;
	}
	public void setRutEntidad(int rutEntidad) {
		this.rutEntidad = rutEntidad;
	}
	public String getDvEntidad() {
		return dvEntidad;
	}
	public void setDvEntidad(String dvEntidad) {
		this.dvEntidad = dvEntidad;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		if (!apellidoPaterno.contains("''")) {
			apellidoPaterno = apellidoPaterno.replace("'", "''");
		}
		apellidoPaterno = apellidoPaterno.replace("?", "");
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		if (!apellidoMaterno.contains("''")) {
			apellidoMaterno = apellidoMaterno.replace("'", "''");
		}
		apellidoMaterno = apellidoMaterno.replace("?", "");
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		if (!nombre.contains("''")) {
			nombre = nombre.replace("'", "''");
		}
		nombre = nombre.replace("?", "");
		this.nombre = nombre;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getFechaDefuncion() {
		return fechaDefuncion;
	}
	public void setFechaDefuncion(String fechaDefuncion) {
		this.fechaDefuncion = fechaDefuncion;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getFechaPension() {
		return fechaPension;
	}
	public void setFechaPension(String fechaPension) {
		this.fechaPension = fechaPension;
	}
	public int getNumeroCotizacionesPACS() {
		return numeroCotizacionesPACS;
	}
	public void setNumeroCotizacionesPACS(int numeroCotizacionesPACS) {
		this.numeroCotizacionesPACS = numeroCotizacionesPACS;
	}
	public int getCotizacionesAFP() {
		return cotizacionesAFP;
	}
	public void setCotizacionesAFP(int cotizacionesAFP) {
		this.cotizacionesAFP = cotizacionesAFP;
	}
	public int getCotizacionesDIPRECA() {
		return cotizacionesDIPRECA;
	}
	public void setCotizacionesDIPRECA(int cotizacionesDIPRECA) {
		this.cotizacionesDIPRECA = cotizacionesDIPRECA;
	}
	public int getCotizacionesCAPREDENA() {
		return cotizacionesCAPREDENA;
	}
	public void setCotizacionesCAPREDENA(int cotizacionesCAPREDENA) {
		this.cotizacionesCAPREDENA = cotizacionesCAPREDENA;
	}
	public int getCotizacionesIPS() {
		return cotizacionesIPS;
	}
	public void setCotizacionesIPS(int cotizacionesIPS) {
		this.cotizacionesIPS = cotizacionesIPS;
	}
	public String getBonoReconocimientoCAPREDENA() {
		return bonoReconocimientoCAPREDENA;
	}
	public void setBonoReconocimientoCAPREDENA(String bonoReconocimientoCAPREDENA) {
		this.bonoReconocimientoCAPREDENA = bonoReconocimientoCAPREDENA;
	}
	public String getBonoReconocimientoDIPRECA() {
		return bonoReconocimientoDIPRECA;
	}
	public void setBonoReconocimientoDIPRECA(String bonoReconocimientoDIPRECA) {
		this.bonoReconocimientoDIPRECA = bonoReconocimientoDIPRECA;
	}
	public String getBonoReconocimientoIPS() {
		return bonoReconocimientoIPS;
	}
	public void setBonoReconocimientoIPS(String bonoReconocimientoIPS) {
		this.bonoReconocimientoIPS = bonoReconocimientoIPS;
	}
	public float getMontoPensionPagadaRP() {
		return montoPensionPagadaRP;
	}
	public void setMontoPensionPagadaRP(float montoPensionPagadaRP) {
		this.montoPensionPagadaRP = montoPensionPagadaRP;
	}
	public float getMontoPensionPagadaRV() {
		return montoPensionPagadaRV;
	}
	public void setMontoPensionPagadaRV(float montoPensionPagadaRV) {
		this.montoPensionPagadaRV = montoPensionPagadaRV;
	}
	public float getMontoPensionDIPRECA() {
		return montoPensionDIPRECA;
	}
	public void setMontoPensionDIPRECA(float montoPensionDIPRECA) {
		this.montoPensionDIPRECA = montoPensionDIPRECA;
	}
	public float getMontoPensionCAPREDENA() {
		return montoPensionCAPREDENA;
	}
	public void setMontoPensionCAPREDENA(float montoPensionCAPREDENA) {
		this.montoPensionCAPREDENA = montoPensionCAPREDENA;
	}
	public float getMontoPensionMutual() {
		return montoPensionMutual;
	}
	public void setMontoPensionMutual(float montoPensionMutual) {
		this.montoPensionMutual = montoPensionMutual;
	}
	public float getMontoPilarSolidario() {
		return montoPilarSolidario;
	}
	public void setMontoPilarSolidario(float montoPilarSolidario) {
		this.montoPilarSolidario = montoPilarSolidario;
	}
	public float getMontoSubsidioDependencia() {
		return montoSubsidioDependencia;
	}
	public void setMontoSubsidioDependencia(float montoSubsidioDependencia) {
		this.montoSubsidioDependencia = montoSubsidioDependencia;
	}
	public float getMontoSeguroDependecia() {
		return montoSeguroDependecia;
	}
	public void setMontoSeguroDependecia(float montoSeguroDependecia) {
		this.montoSeguroDependecia = montoSeguroDependecia;
	}
	public float getMontoPensionIPS() {
		return montoPensionIPS;
	}
	public void setMontoPensionIPS(float montoPensionIPS) {
		this.montoPensionIPS = montoPensionIPS;
	}
	public float getMontoTGR() {
		return montoTGR;
	}
	public void setMontoTGR(float montoTGR) {
		this.montoTGR = montoTGR;
	}
	public String getFechaDevengamiento() {
		return fechaDevengamiento;
	}
	public void setFechaDevengamiento(String fechaDevengamiento) {
		this.fechaDevengamiento = fechaDevengamiento;
	}
	public String getVectorPACSFijo() {
		return vectorPACSFijo;
	}
	public void setVectorPACSFijo(String vectorPACSFijo) {
		this.vectorPACSFijo = vectorPACSFijo;
	}
	public String getVectorPACSVariable() {
		return vectorPACSVariable;
	}
	public void setVectorPACSVariable(String vectorPACSVariable) {
		this.vectorPACSVariable = vectorPACSVariable;
	}
	public String getEntidadPagadora() {
		return entidadPagadora;
	}
	public void setEntidadPagadora(String entidadPagadora) {
		this.entidadPagadora = entidadPagadora;
	}
	public float getMontoBeneficioFijopesos() {
		return montoBeneficioFijopesos;
	}
	public void setMontoBeneficioFijopesos(float montoBeneficioFijopesos) {
		this.montoBeneficioFijopesos = montoBeneficioFijopesos;
	}
	public float getMontoBeneficioVariablePesos() {
		return montoBeneficioVariablePesos;
	}
	public void setMontoBeneficioVariablePesos(float montoBeneficioVariablePesos) {
		this.montoBeneficioVariablePesos = montoBeneficioVariablePesos;
	}
	public float getMontoREBAJABeneficioFijopesos() {
		return montoREBAJABeneficioFijopesos;
	}
	public void setMontoREBAJABeneficioFijopesos(float montoREBAJABeneficioFijopesos) {
		this.montoREBAJABeneficioFijopesos = montoREBAJABeneficioFijopesos;
	}
	public float getMontoREBAJABeneficioVariablePesos() {
		return montoREBAJABeneficioVariablePesos;
	}
	public void setMontoREBAJABeneficioVariablePesos(float montoREBAJABeneficioVariablePesos) {
		this.montoREBAJABeneficioVariablePesos = montoREBAJABeneficioVariablePesos;
	}
	public float getValorUFUtilizado() {
		return valorUFUtilizado;
	}
	public void setValorUFUtilizado(float valorUFUtilizado) {
		this.valorUFUtilizado = valorUFUtilizado;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public float getMontoInicialPACS() {
		return montoInicialPACS;
	}
	public void setMontoInicialPACS(float montoInicialPACS) {
		this.montoInicialPACS = montoInicialPACS;
	}
	public float getPensionTotalUF() {
		return pensionTotalUF;
	}
	public void setPensionTotalUF(float pensionTotalUF) {
		this.pensionTotalUF = pensionTotalUF;
	}
	public float getTotalPensiones() {
		return totalPensiones;
	}
	public void setTotalPensiones(float totalPensiones) {
		this.totalPensiones = totalPensiones;
	}
	public String getTipoPACS() {
		return tipoPACS;
	}
	public void setTipoPACS(String tipoPACS) {
		this.tipoPACS = tipoPACS;
	}
	public int getBeneficiarioPACSFijo() {
		return beneficiarioPACSFijo;
	}
	public void setBeneficiarioPACSFijo(int beneficiarioPACSFijo) {
		this.beneficiarioPACSFijo = beneficiarioPACSFijo;
	}
	public int getBeneficiarioPACSVariable() {
		return beneficiarioPACSVariable;
	}
	public void setBeneficiarioPACSVariable(int beneficiarioPACSVariable) {
		this.beneficiarioPACSVariable = beneficiarioPACSVariable;
	}
	public int getInformadoPCPVID() {
		return informadoPCPVID;
	}
	public void setInformadoPCPVID(int informadoPCPVID) {
		this.informadoPCPVID = informadoPCPVID;
	}
	public int getMontoInconsistencia() {
		return montoInconsistencia;
	}
	public void setMontoInconsistencia(int montoInconsistencia) {
		this.montoInconsistencia = montoInconsistencia;
	}
	public int getCotizacionesExigidas() {
		return cotizacionesExigidas;
	}
	public void setCotizacionesExigidas(int cotizacionesExigidas) {
		this.cotizacionesExigidas = cotizacionesExigidas;
	}

	
}
