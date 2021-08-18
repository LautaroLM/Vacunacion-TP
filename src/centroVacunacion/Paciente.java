package centroVacunacion;

public class Paciente {
	private Fecha nacimiento;
	private int DNI;
	private boolean esEmpleadoSalud;
	private boolean tienePadecimientos;
	private int prioridad;
	
	
	public Paciente(int dni, Fecha nac, boolean tienePadecimientos,boolean esEmpleadoSalud) {
		this.nacimiento = nac;
		this.DNI = dni;
		this.esEmpleadoSalud = esEmpleadoSalud;
		this.tienePadecimientos = tienePadecimientos;
		this.prioridad=definirPrioridad();

		
	}
	
	private int definirPrioridad() {
		if(esEmpleadoSalud)
			return 1;
		
		if(getEdad()>=60)
			return 2;
		
		if(tienePadecimientos)
			return 3;
		
		else
			return 4;
	}
	

	public int getPrioridad() {
		return this.prioridad;
	}

	public int getDNI() {
		return this.DNI;
	}

	public int getEdad() {
		return Fecha.diferenciaAnios(Fecha.hoy(), this.nacimiento);
	}
	
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    
	  sb.append(("DNI: ")).append(this.DNI).append(" / Prioridad: ").append(this.prioridad);
	    
	    return sb.toString();
	  }

	
}
	


