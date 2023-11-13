package practica;

import java.io.FileReader;

public class Lexico {
	//CONSTANTES

		//carácter de nueva línea
		static final char n_l= 10;
		//lista de palabras reservadas, el codigo de una palabra reservada es su posicion en el array
		static final String[] PAL_RESERVADAS= {"boolean", "break", "case", "function", "if", "input", "int", "let", "print", "return", "string", "switch", "default"};

		//lista de simbolos, su codigo es PAL_RESERVADAS.length + posicion
		static final String[] SIMBOLO_RESERVADOS = {"&=", "=", ",", ";", ":", "(", ")", "{", "}", "+", "&&", "<"};

		//codigo de datos
		static final int ENTERO = PAL_RESERVADAS.length + SIMBOLO_RESERVADOS.length + 1;
		static final int CADENA = PAL_RESERVADAS.length + SIMBOLO_RESERVADOS.length + 2;
		static final int ID = PAL_RESERVADAS.length + SIMBOLO_RESERVADOS.length + 3;



		//BUSQUEDA DE CONSTANTES

		//Busca y devuelve el codigo de una palabra reservada, si no es una palabra reservada devuelve -1
		private int buscarPalabraReservada(String lexema) throws Exception {
			for(int i=0; i<PAL_RESERVADAS.length;i++) {
				if(PAL_RESERVADAS[i].equals(lexema)) {
					return i+1;
				}
			}
			return -1;
		}

		//Busca y devuelve el codigo de una simbolo reservado, si no es una palabra reservada devuelve -1
		private int buscarSimboloReservado(String lexema) throws Exception{
			for(int i=0; i<SIMBOLO_RESERVADOS.length;i++) {
				if(SIMBOLO_RESERVADOS[i].equals(lexema)) {
					return PAL_RESERVADAS.length + i + 	1;
				}
			}
			return -1;
		}


		//VARIABLES

		//archivo a leer
		FileReader fr;
		//Proximo caracter a evaluar
		private char car;
		//Numero ASCII de car, vale -1 si es el final del fichero
		private int ASCIIcar;
		//Estado actual
		private int estado;
		//Accion a realizar
		private String accion;
		//Matriz de transiciones
		private MatrizTransiciones mt;
		//Atributo para saber si he generado token
		private boolean generado= false;
		//variable donde se almacena un entero
		private int num=0;
		//variable donde se almacena una cadena
		private String lexema="";
		//variable para comprobar que ni enteros ni cadenas se salen de rango 
		private int cont=0;
		//Gestor de errores
		GestorErrores ge;
		//Tabla de simbolos
		TS tsG;
		TS tsActiva;


		//METODOS

		//Constructor
		public Lexico(FileReader fr, GestorErrores ge, TS tsG, TS tsActiva) {
			//inicializo el lector del archivo
			this.fr = fr;
			leer();
			this.estado = 0;
			this.mt = new MatrizTransiciones();
			this.ge = ge;
			this.tsG = tsG;
			this.tsActiva = tsActiva;
		}

		//Ejecucion de lexico, devuelve el token leido o null si se ha llegado al final del archivo
		public Token run() throws Exception{
			while(ASCIIcar!=-1) {
				if(generado) {
					estado=0;
					num=0;
					lexema="";
					cont=0;
				}
				generado= false;
				if(car == n_l)
					ge.nextLine();
				accion = mt.accion(estado, car);
				estado = mt.estado(estado, car);
				if(estado == -1) {
					ge.run(Integer.valueOf(accion));
				}
				else {

					switch(accion.charAt(0)) {
					case 'A':
						leer();
						break;
					case 'B':
						leer();
						generado= true;
						return new Token(buscarSimboloReservado("&&"),null);
					case 'C':
						leer();
						generado= true;
						return new Token(buscarSimboloReservado("&="),null);
					case 'D':
						num = Character.getNumericValue(car);
						leer();
						break;
					case 'E':
						num = num *10 + Character.getNumericValue(car);
						leer();
						break;
					case 'F':
						if(car == ' ' || car == '\t' || car == '\r')
							leer();
						generado= true;
						if(num<=32767) {
							return new Token(ENTERO,num);
						}else {
							ge.run(40);
						}
						break;
					case 'G':
						lexema = "";
						cont = 0;
						leer();
						break;
					case 'H':
						lexema+=car;
						cont++;
						leer();
						break;
					case 'I':
						leer();
						generado= true;
						if(cont<64) {
							return new Token(CADENA,lexema);
						}else {
							ge.run(41);
						}
						break;
					case 'J':
						lexema = ""+car;
						leer();
						break;
					case 'K':
						lexema+= car;
						leer();
						break;
					case 'L':
						if(car == ' ' || car == '\t' || car == '\r')
							leer();
						int cod_pal_reserv = buscarPalabraReservada(lexema);
						generado= true;
						if(cod_pal_reserv>=0) {
							return new Token(cod_pal_reserv,null);
						}else {
							int pos;
							if(tsActiva==tsG) {
								pos=tsActiva.buscarLexema(lexema, true);
							}else if(tsActiva.buscarLexema(lexema, false)>0){
								pos=-tsActiva.buscarLexema(lexema, false);
							}else if(tsG.buscarLexema(lexema, false)>0) {
								pos = tsG.buscarLexema(lexema, false);
							}else {
								pos=-tsActiva.buscarLexema(lexema, true);
							}
							return new Token(ID, pos );
						}
					case 'M':
						leer();
						generado= true;
						return new Token(buscarSimboloReservado(","),null);
					case 'N':
						leer();
						generado= true;
						return new Token(buscarSimboloReservado(";"),null);
					case 'O':
						leer();
						generado= true;
						return new Token(buscarSimboloReservado(":"),null);
					case 'P':
						leer();
						generado= true;
						return new Token(buscarSimboloReservado("("),null);
					case 'Q':
						leer();
						generado= true;
						return new Token(buscarSimboloReservado(")"),null);
					case 'R':
						leer();
						generado= true;
						return new Token(buscarSimboloReservado("{"),null);
					case 'S':
						leer();
						generado= true;
						return new Token(buscarSimboloReservado("}"),null);
					case 'T':
						leer();
						generado= true;
						return new Token(buscarSimboloReservado("+"),null);
					case 'U':
						leer();
						generado= true;
						return new Token(buscarSimboloReservado("="),null);
					case 'V':
						leer();
						generado= true;
						return new Token(buscarSimboloReservado("<"),null);

					}
				}
			}

			//Cierro fichero
			if(fr!=null) {
				try {
					fr.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;
		}


		//Lee el siguiente caracter del archivo
		private void leer() {
			try {
				//leo el codigo ASCII del siguiente caracter, da -1 si es el final del fichero
				ASCIIcar = fr.read();
				//si no es el final del fichero convierto el codigo en caracter
				if(ASCIIcar>=0) {
					this.car = (char) ASCIIcar;
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		

}
