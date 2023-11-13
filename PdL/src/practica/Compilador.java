package practica;


public class Compilador {
	//METODOS
	
	//Constructor
	public Compilador() {
		
	}

	//Ejecucion del compilador
	public void run(String nombreArchivo) {
		System.out.println("Archivo "+nombreArchivo);
		new Sintactico(nombreArchivo).run();
	}
}