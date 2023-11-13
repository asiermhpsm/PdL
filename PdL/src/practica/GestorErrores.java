package practica;

public class GestorErrores {
	private int linea;
	private int numErrores;
	
	public GestorErrores() {
		this.linea=1;
		this.numErrores=0;
	}

	public void run(int num) {
		this.numErrores++;
		String mensaje = "Linea "+linea+": ERROR ";
		if(num==0) {
			mensaje+="INESPERADO";
		}
		else if(num<=42) {
			mensaje+="LEXICO"+num+":";
			if(num==40) {
				mensaje+=" Entero fuera de rango";
			}else if(num==41) {
				mensaje+=" Londitud de la cadena fuera de rango";
			}else if(num==42) {
				mensaje+=" Caracter invalido";
			}else {
				mensaje+=" Token invalido";
			}
		}else if(num==43) {
			mensaje+="SINTACTICO "+num;
		}else if(num>=44){
			mensaje+="SEMANTICO "+num;
			switch(num){
			case 44:
				mensaje+=": condicion de if debe ser logica";
				break;
			case 45:
				mensaje+=": asignacion no valida";
				break;
			case 46:
				mensaje+=": llamada a variable incorrecta";
				break;
			case 47:
				mensaje+=": print debe ir acompañado de cadena o entero";
				break;
			case 48:
				mensaje+=": input debe ir acompañado de cadena o entero";
				break;
			case 49:
				mensaje+=": tipo de retorno incorrecto";
				break;
			case 50:
				mensaje+=": no se puede retornar fuera de una funcion";
				break;
			case 51:
				mensaje+=": parametro incorrecto";
				break;
			case 52:
				mensaje+=": expresion incorrecta";
				break;
			default: break;
			}
		}
		System.out.println(mensaje);
		
	}
	
	public void nextLine() {
		linea+=1;
	}
	
	public void finComp() {
		System.out.println("Fin de compiladion\nERRORES: "+this.numErrores);
	}
}
