import practica.Compilador;

public class Main {

	public static void main(String[] args) {
		Compilador cmp = new Compilador();
		/**
		 * Cambiar el nombre del archivo a compilar
		 * Archivos correctos: ej1.txt al ej5.txt
		 * Archivos incorrectos: ej6.txt al ej10.txt
		 * **/
		cmp.run("ej1.txt");
		
		/**
		 * Los ficheros tokens, parse y tabla de simbolos aparecerán la carpeta del proyecto,
		 * estos archivos se reescriben cada ejecucion
		 * **/
		
	}
}

