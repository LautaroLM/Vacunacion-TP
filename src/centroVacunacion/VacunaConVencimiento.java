package centroVacunacion;

public class VacunaConVencimiento extends Vacuna {
  
  private int diasVencimiento;
  private int contadorVencidas;
  private Fecha fVencimiento;
  

  
  public VacunaConVencimiento(String nombre, int edadMinima, int temp, Fecha fechaIngreso, int cantidad, int diasVencimiento) {
    super(nombre, edadMinima, temp,fechaIngreso,cantidad);
    this.diasVencimiento=diasVencimiento;
    this.contadorVencidas=0;
    

    //OJO ver si existe manera de hacer sin editar la clase fecha.
    fVencimiento = new Fecha(fechaIngreso);

    fVencimiento.avanzarDias(diasVencimiento); 

    
  }
  
  
  public void controlVencidas() {
	 
	  
    if (fVencimiento.compareTo(Fecha.hoy()) <= 0 && super.cantidad!=0)  {
      contadorVencidas=cantidad;
      this.cantidad=0;
    }    
  }
  
  public int cantVencidas() {
    return contadorVencidas;
  }


@Override
public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + diasVencimiento;
	result = prime * result + ((fVencimiento == null) ? 0 : fVencimiento.hashCode());
	return result;
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (!super.equals(obj))
		return false;
	if (getClass() != obj.getClass())
		return false;
	VacunaConVencimiento other = (VacunaConVencimiento) obj;
	if (diasVencimiento != other.diasVencimiento)
		return false;
	if (fVencimiento == null) {
		if (other.fVencimiento != null)
			return false;
	} else if (!fVencimiento.equals(other.fVencimiento))
		return false;
	return true;
}

}
