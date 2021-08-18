package centroVacunacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;



public class CentroVacunacion {
	
	private String nombreCentro;
	private int capAplicacionDiaria;
	
	private LinkedList<Turno> turnos; //Lista de turnos.
	private Map<Integer, String> personasVacunadas; //Lista de los DNI´s de los pacientes vacunados. Clave:DNI, Valor: vacuna.
	private ArrayList<Integer> listaEspera; //Lista de los DNI´s de los paciente que se inscribieron.
	private ArrayList<Integer> pacientesConTurno; //Lista de los DNI´s de los paciente que cuentan con turno asignado.
	private Queue<Paciente> prio1;
	private Queue<Paciente> prio2;
	private Queue<Paciente> prio3;
	private Queue<Paciente> prio4;
	
	public GestionVacunas gv;
	
	public CentroVacunacion(String nombre, int capacidadVacunacionDiaria) {
		if(nombre != "UNGS" && nombre != "UNGS 2")
			throw new RuntimeException("Nombre de Centro inválido");
		if(capacidadVacunacionDiaria < 0)
			throw new RuntimeException("Capacidad de vacunación inválida");
		
		this.nombreCentro = nombre;
		this.capAplicacionDiaria = capacidadVacunacionDiaria;
		
		this.turnos = new LinkedList<Turno>();
		
		this.personasVacunadas = new HashMap<Integer, String>();
		this.listaEspera = new ArrayList<Integer>();
		
		this.pacientesConTurno = new ArrayList<Integer>();
		
		this.prio1 = new LinkedList<Paciente>();
		this.prio2 = new LinkedList<Paciente>();
		this.prio3 = new LinkedList<Paciente>();
		this.prio4 = new LinkedList<Paciente>();
		
		this. gv = new GestionVacunas();
		
		
	}
	
	public void inscribirPersona(int dni, Fecha nacimiento, boolean tienePadecimientos, boolean esEmpleadoSalud) {
		if(listaEspera.contains(dni))
			throw new RuntimeException("Ya está inscripto");
		
		if(personasVacunadas.containsKey(dni))
			throw new RuntimeException("Ya fue vacunado");
		
		Paciente pac = new Paciente(dni,nacimiento,tienePadecimientos,esEmpleadoSalud);
	
		if(pac.getEdad() < 18)
			throw new RuntimeException("Debe ser mayor de 18 años para inscribirse");
	
		
		this.listaEspera.add(dni);
		
		
		
		switch (pac.getPrioridad()){ //Segun la prioridad del Paciente se asiganará una prioridad.
		case 1 :					 
			prio1.add(pac);
			break;
		case 2:
			prio2.add(pac);
			break;
		case 3:
			prio3.add(pac);
			break;
		case 4:
			prio4.add(pac);
			break;
		}
	
	}
	
	public void ingresarVacunas(String nombreVacuna, int cantidad, Fecha fechaIngreso) {
		if(cantidad <= 0)
			throw new RuntimeException("La cantidad de vacunas ingresadas es inválida");
			
		
		gv.ingresarVacunas(nombreVacuna, cantidad, fechaIngreso);  //Ingresa vacunas al stock
		
	}
	
	
		
	public void generarTurnos(Fecha fechaInicial) {
		Fecha auxF= new Fecha(fechaInicial);
		
		if (fechaInicial.compareTo(Fecha.hoy())<=0) { 
			throw new RuntimeException ("La fecha de inicio de generar turnos esta vencida");
			}
		
		gv.controlVencVacunas(); //verifica si hay vacunas vencidas y las quita del sistema.
		
		if(!turnos.isEmpty()) {
			for(Turno tur: turnos) {	
				if(tur.getFecha().compareTo(Fecha.hoy())<0 && tur.getEstado()!=1 ) {
					gv.ingresarVacunas(tur.getVacuna());//Regresa las vacunas de los turnos vencidos
					tur.borrar();;//la persona que no asistió al turno se "BORRA" del sistema
					
			
				}
				
			}
		}	
		
		int auxContAplicDiaria = capAplicacionDiaria;
		
		
		int aux = 1;
		while(aux==1 && gv.vacunasCant()>0 && !listaEspera.isEmpty()) { 		//En caso de que no haya mas vacunas o no haya pacientes en la lista de espera
																				//se deja de generar turnos.
			
			for(Paciente pac: prio1) { 											//se recorren la cola de prioridad 1.
				turnos.add(new Turno(pac, gv.getVacuna(pac), new Fecha(auxF))); //asigna turno
				auxContAplicDiaria--;														
				if (listaEspera.contains(pac.getDNI())) {
					listaEspera.remove(new Integer(pac.getDNI()));				//generado el turno al Paciente, la sacamos de la lista de espera
					pacientesConTurno.add(pac.getDNI());  						//Agregamos al paciente a la lista de turnos
					}
				
				if(auxContAplicDiaria <= 0) { 									
					auxContAplicDiaria = capAplicacionDiaria;					//si la capacidad diara de vacunacion llega a su limite,
					auxF.avanzarUnDia();										//avanzamos un dia.
					}			
				}


			for(Paciente pac: prio2) { 
				turnos.add(new Turno(pac, gv.getVacuna(pac),new Fecha(auxF))); 
				auxContAplicDiaria--; 
				if (listaEspera.contains(pac.getDNI())){ 
					listaEspera.remove(new Integer(pac.getDNI()));
					pacientesConTurno.add(pac.getDNI()); 
				}


				if(auxContAplicDiaria <= 0) { 
					auxContAplicDiaria = capAplicacionDiaria;
					auxF.avanzarUnDia();
				} 
			}


			for(Paciente pac: prio3) {
				turnos.add(new Turno(pac, gv.getVacuna(pac), new Fecha(auxF)));
				auxContAplicDiaria--; 

				if (listaEspera.contains(pac.getDNI())) {
					listaEspera.remove(new Integer(pac.getDNI()));
					pacientesConTurno.add(pac.getDNI()); 
				}

				if(auxContAplicDiaria <= 0) { 
					auxContAplicDiaria = capAplicacionDiaria;
					auxF.avanzarUnDia(); 
				} 
			}

			for(Paciente pac: prio4) {

				turnos.add(new Turno(pac, gv.getVacuna(pac), new Fecha(auxF)));
				auxContAplicDiaria--; 

				if (listaEspera.contains(pac.getDNI())) {
					listaEspera.remove(new Integer(pac.getDNI()));
					pacientesConTurno.add(pac.getDNI()); 
				}


				if(auxContAplicDiaria <= 0) { 
					auxContAplicDiaria = capAplicacionDiaria;
					auxF.avanzarUnDia();
				} 
			}

			aux=0;		//Variable para controlar el While.

		}
	}
		
	
	
	public void vacunarInscripto(int dni, Fecha fechaVacunacion) {

		if(!pacientesConTurno.contains(dni))
			throw new RuntimeException("No está inscripto");

		int inscripto=0;
		
		for(Turno tur: turnos) {
			if(tur.getFecha().compareTo(fechaVacunacion) == 0 && tur.getDNI() == dni && tur.getEstado() !=1) { //si la persona tiene turno la misma fecha que la del
				personasVacunadas.put(dni, tur.getVacuna().getNombre());									   //parametro y su turno no esta concluido, se lo agrega
				this.gv.vacunasAplicadas++;
				tur.concluir();																				   //a la lista de personas vacunadas y da concluido el turno.
				
				inscripto=1;
			}			
		}
		
		if (inscripto==0) {
			throw new RuntimeException("No tiene turno");
		}
	}	
			
	public int vacunasDisponibles() {
		return gv.vacunasCant();
	}
	
	public int vacunasDisponibles(String nombre) {
		
		return gv.vacunasDisponibles(nombre);
	}
	
				
	public List<Integer> listaDeEspera(){
		return listaEspera;
	}
	
	public List<Integer> turnosConFecha(Fecha fecha){

		ArrayList<Integer> turnosConFecha = new ArrayList<Integer>(); //creamos un arreglo vacio interno para los dni
																	  //con turno para la fecha pasada por parametro.
		Iterator it = turnos.iterator();
		Turno turno =new Turno(null, null, fecha);
		while(it.hasNext()) { 	                         //Recorremos la lista de turnos
			turno= (Turno) it.next();
			if (turno.getFecha().compareTo(fecha)==0) {  //si el turno tiene la misma fecha que la del parametro
				turnosConFecha.add(turno.getDNI());		 //agregamos el dni del paciente a un arreglo.
			}
		}
		return turnosConFecha;
	}
	
	public Map<Integer, String> reporteVacunacion(){
		return personasVacunadas;
	}

	public Map<String, Integer> reporteVacunasVencidas(){
		return gv.reporteVacunasVencidas;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Nombre: " + this.nombreCentro + " - Capacidad Diaria: " + this.capAplicacionDiaria +"\n" ).
		append("\n").
		append("Lista de Espera: " + this.listaEspera + "\n").
		append("\n").
		append("Turnos: " + this.turnos.toString() + "\n").
		append("\n").
		append("Vacunas: " + "\n"  + this.gv.toString() + "\n").
		append("\n").
		append("Total Vacunas Disponibles: " + "\n"  + this.vacunasDisponibles() + "\n").
		append("\n").
		append("Vacuna Aplicadas: " + "\n"  + this.gv.vacunasAplicadas + "\n");
		
		
		
		return sb.toString();
		
	}

}
