package centroVacunacion;

public class Vacuna {
  private String nombre;
  private int edadMinAplicable;
  private int tempAlmacenamiento;
  protected int cantidad;
  private int aplicadas;
  protected Fecha fechaIngreso;
  
  
  public Vacuna (String nombre, int edadMinima, int temp, Fecha fechaIngreso, int cantidad) {
    this.nombre=nombre;
    this.edadMinAplicable=edadMinima;
    this.tempAlmacenamiento=temp;
    this.cantidad=cantidad;
    this.aplicadas=0;
    this.fechaIngreso=fechaIngreso;
  }
  
  
  public void sumar(int num) {
    this.cantidad+=num;
  }
  
  public void restar(int num) {
    this.cantidad-=num;
  }
  
  public int verCantidad() {
    return this.cantidad;
  }
  
  public int verAplicadas() {
    return aplicadas;
  }
  
  public String getNombre() {
    return this.nombre;
  }
  
  public int getEdadMinima() {
    return this.edadMinAplicable;
  }
  public int getTemp() {
	    return this.tempAlmacenamiento;
	  }
  
  public Fecha getFechaIn() {
	    return this.fechaIngreso;
	  }
  public void editarCant(int cant) {
	    this.cantidad=cant;
	  }
  
  
  public String toString() {
	  StringBuilder sb = new StringBuilder();
			sb= sb.append(this.nombre).append(" ").append("Cantidad: ").append(this.cantidad);
		
		
		return sb.toString();
  }


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + edadMinAplicable;
	result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
	result = prime * result + tempAlmacenamiento;
	return result;
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Vacuna other = (Vacuna) obj;
	if (edadMinAplicable != other.edadMinAplicable)
		return false;
	if (nombre == null) {
		if (other.nombre != null)
			return false;
	} else if (!nombre.equals(other.nombre))
		return false;
	if (tempAlmacenamiento != other.tempAlmacenamiento)
		return false;
	return true;
}
		

}