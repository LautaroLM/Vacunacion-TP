package centroVacunacion;

public class Turno {
	private Paciente paciente;
	private Vacuna vacuna;
	private Fecha fecha;
	private int estado;
	
	public Turno(Paciente pac, Vacuna vac, Fecha fech) {
		this.paciente = pac;
		this.vacuna = vac;
		this.fecha = fech;
		this.estado = 0; //0 es paciente sin vacunar, 1 paciente vacunado.
	}
	
	
	
	public void concluir() {
		this.estado = 1;
	}
	
	
	public int getDNI() {
		return this.paciente.getDNI();
	}

	public Fecha getFecha() {
		return fecha;
	}

	public Vacuna getVacuna() {
		return vacuna;
	}
	
	public boolean pacienteVacunado() {
		if (estado==1) {
			return true;
		}
		else return false;
		
	}
	
	
	public int getEstado() {
		return estado;
	}
	
	public void borrar() {
		this.paciente =null;
		this.vacuna = null;
		this.fecha = null;
		this.estado = 0; 
	}
	
	  public String toString() {
			StringBuilder sb = new StringBuilder();
			sb=sb.append(" \n "+ "[DNI Paciente: " + this.paciente.getDNI() + " // ").
					append("Fecha Asignada: "+this.fecha.toString() + " // ").
				append("Aplicar Vacuna: "+this.vacuna.getNombre().toString()+ "]");
			
			
			return sb.toString();
			
			
		}
	
	


}
