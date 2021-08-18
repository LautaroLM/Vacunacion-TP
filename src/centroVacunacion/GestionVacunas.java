package centroVacunacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class GestionVacunas {
  
  public ArrayList <Vacuna> vacunas ;
  public int vacunasAplicadas;
  private Paciente paciente;
  private Vacuna vacuna;
  public HashMap <String, Integer> reporteVacunasVencidas;
  
 
  
  
  
  
  public GestionVacunas() {
    vacunas = new  ArrayList <Vacuna>();      
    this.vacunasAplicadas=0;
    this.paciente= new Paciente(0, Fecha.hoy(), false, false);
    this.vacuna= new Vacuna("", 0,0,null,0);
    
    reporteVacunasVencidas = new HashMap <String, Integer>() ;
    
   
  }
  
  
  public void ingresarVacunas( String nombreVacuna,  int cantidad,  Fecha fechaIngreso) {
    
    if (nombreVacuna=="Pfizer") {
      this.vacuna = new VacunaConVencimiento (nombreVacuna, 60, -18, fechaIngreso,cantidad,30);
      vacunas.add(vacuna);
    }

    else if (nombreVacuna=="Moderna") {
      Vacuna vacuna = new VacunaConVencimiento (nombreVacuna, 18, -18, fechaIngreso,cantidad,60);
      
      this.vacunas.add(vacuna);
    
    }
    
    else if (nombreVacuna=="AstraZeneca" || nombreVacuna=="Sinopharm") {
      Vacuna vacuna = new Vacuna (nombreVacuna, 18, 3, fechaIngreso, cantidad);
   
      vacunas.add(vacuna);
     
    }
    
    else if (nombreVacuna=="Sputnik") {
      Vacuna vacuna = new Vacuna (nombreVacuna, 60, 3, fechaIngreso, cantidad);
      
      vacunas.add(vacuna);

    }
    
    
    else {throw new RuntimeException("El nombre ingresado es incorrecto, la vacuna no fue ingresada");}
    }
    
  public void ingresarVacunas(Vacuna vac) {
	
	  vac.editarCant(1);
	  vacunas.add(vac);
	  
  }

  
  public boolean aplicableA(Paciente p, Vacuna v) {
    
    if (p.getEdad()>v.getEdadMinima()){
      return true;
    }
    else {  
      return false;
    }
  }
  
  
  public int vacunasCant() {
	int cantLocal=0;
	
    for (int i=0; i<this.vacunas.size();i++){
    	cantLocal += vacunas.get(i).verCantidad();
    }
    return cantLocal;
  }
  
  
  public int vacunasDisponibles(String nombreVacuna) {
    int cantidadLocal = 0;
    for (Vacuna vac: this.vacunas){
      if(nombreVacuna==vac.getNombre()) {
        cantidadLocal += vac.verCantidad();
      }
    }
    return cantidadLocal ;
  }
  
  
  public void controlVencVacunas() {
    
	  for (Vacuna vac : vacunas) {
		 int aux=0;
		  if (  vac.getClass().getName().equals("centroVacunacion.VacunaConVencimiento") ) {
			  
			   ((VacunaConVencimiento) vac).controlVencidas();
			   
			   
			   //Ingreso de los datos al reporte 
			   if (reporteVacunasVencidas.containsKey(vac.getNombre())){
				   aux = ((VacunaConVencimiento)vac).cantVencidas() + reporteVacunasVencidas.get(vac.getNombre());
			   }
			   else {aux=((VacunaConVencimiento) vac).cantVencidas();}
			   
			   this. reporteVacunasVencidas.put(vac.getNombre(), aux);
		
			   
		  }
	  }
  }
  
 
  public Vacuna getVacuna(Paciente pac) {
	  Vacuna vacunaAux = vacuna;
	  int vacunaAsignada=0;  
	  for( Vacuna vac: vacunas) {
	  		
	  	if (vac.cantidad>0  && aplicableA(pac, vac)&&vacunaAsignada==0) {
	  			vacunaAux = new Vacuna(vac.getNombre(), vac.getEdadMinima(), vac.getTemp(), vac.fechaIngreso,1);
	  		    vac.restar(1);
	  		    vacunaAsignada=1;
		   }		
		}
	  	 return vacunaAux;
	  }


  public Map<String, Integer> reporteVencidas(){
	return reporteVacunasVencidas;}

  public String toString() {
	StringBuilder sb = new StringBuilder();
	for (Vacuna vac : vacunas) {
		if (vac.cantidad>0)
		sb= sb.append(vac.toString()).append("\n");
	}
	
	return sb.toString();
	
	
}

}