package practica;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import practica.TablaSintactico.Accion;
import practica.TablaSintactico.S_No_Terminal;
import practica.TablaSintactico.S_Terminal;


public class Sintactico {	
	TS tsG;
	GestorErrores ge;
	Lexico lexico;
	TablaSintactico TabSint;
	List<Token> tokens;
	List<Integer> parse;
	Stack<Par> pila;
	List<Par> pilaAux;
	Acciones acciones;
	TS TSActiva;

	public Sintactico(String nombreArchivo) {
		FileReader fr = null;
		try {
			fr = new FileReader(nombreArchivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.tsG = new TS();
		this.TSActiva=tsG;
		this.ge = new GestorErrores();
		this.lexico = new Lexico(fr, ge, tsG, TSActiva);
		this.tokens = new ArrayList<>();
		this.TabSint = new TablaSintactico(ge);
		this.parse = new ArrayList<>();
		this.pila = new Stack<>();
		this.pila.push(new Par(TabSint.new S_Terminal("30")));
		this.pila.push(new Par(TabSint.new S_No_Terminal("P")));
		this.pilaAux = new ArrayList<>();
		this.acciones = new Acciones(ge, lexico, pila, pilaAux,tsG,  TSActiva);
	}

	public void run() {
		Token token=null;
		try {
			token = lexico.run();
		} catch (Exception e) {
			ge.run(0);
		}
		try {
			while(token != null) {
				tokens.add(token);
				iterar(token);
				token = lexico.run();
			}
		} catch (Exception e) {
			ge.run(0);
		}

		try {
			while(pila.size() > 1) {
				if(pila.peek().getSimb() instanceof S_No_Terminal) {
					parse.add(TabSint.aplicaRegla( TabSint.new S_Terminal(String.valueOf("30")), pila, pilaAux));
				}else if(pila.peek().getSimb() instanceof Accion) {
					acciones.run(pila.pop().getSimb().get());
				}
			}
		} catch (Exception e) {
			ge.run(0);
		}

		try {
			if(pila.peek().getSimb().get()!="30") {
				ge.run(43);
			}
		} catch (Exception e) {
			ge.run(0);
		}
		

		ge.finComp();
		
		devuelveArchivoTokens(); 
		devuelveArchivoTablaSimbolos();
		devuelveParse();
	}

	//realiza las iteraciones necesarias hasta que sea necesario el siguiente token
	private void iterar(Token token) throws Exception {
		TablaSintactico.S_Terminal S_Term_token = TabSint.new S_Terminal(token);
		while(pila.peek().getSimb()instanceof S_No_Terminal	||	pila.peek().getSimb()instanceof Accion) {
			if(pila.peek().getSimb() instanceof S_No_Terminal) {
				parse.add(TabSint.aplicaRegla( S_Term_token, pila, pilaAux));
			}else if(pila.peek().getSimb() instanceof Accion) {
				acciones.run(pila.pop().getSimb().get());
			}
		}
		if(Integer.valueOf(pila.pop().getSimb().get()) != token.getCodigo()) {
			ge.run(43);
		}
		pilaAux.add(new Par(S_Term_token));
	}


	//Devuelve los tokens en el formato pedido
	public void devuelveArchivoTokens() {
		try {
			FileWriter fichero = new FileWriter("tokens.txt");
			for(Token token: tokens) {
				String linea = "<";
				int codigo = token.getCodigo();
				linea += codigo + ", ";
				if(token.getValor()!=-1) {
					linea += token.getValor();

				}else if(token.getLexema()!=null) {
					linea +=(char)34 + token.getLexema() + (char)34;
				}
				linea += ">\t";

				String comentario = "//token ";
				if(codigo < Lexico.PAL_RESERVADAS.length) {
					comentario = comentario + "palabra reservada "+ Lexico.PAL_RESERVADAS[codigo-1];
				}else if(Lexico.PAL_RESERVADAS.length< codigo && codigo <= (Lexico.SIMBOLO_RESERVADOS.length + Lexico.PAL_RESERVADAS.length)) {
					comentario += Lexico.SIMBOLO_RESERVADOS[codigo - Lexico.PAL_RESERVADAS.length - 1];
				}else if(codigo == Lexico.ENTERO) {
					comentario += "numero entero";
				}else if(codigo == Lexico.CADENA) {
					comentario += "cadena";
				}else if(codigo == Lexico.ID) {
					comentario += "identificador";
				}

				fichero.write(linea+comentario+"\n");
			}
			fichero.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void devuelveArchivoTablaSimbolos() {
		try {
			FileWriter fichero = new FileWriter("TablaSimbolos.txt");
			fichero.write(tsG.devuelveArchivoTablaSimbolos());
			fichero.write(acciones.str);
			fichero.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void devuelveParse() {
		try {
			FileWriter fichero = new FileWriter("Parse.txt");
			String frase = "Descendente";
			for(int num: parse) {
				frase = frase + " " + String.valueOf(num);
			}
			fichero.write(frase);
			fichero.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	private void imprimePila() {
		Iterator<Par> itP=pila.iterator();
		Iterator<Par> itAux=pilaAux.iterator();
		Par par;
		System.out.println("\nPILA:");
		while(itP.hasNext()) {
			par=itP.next();
			System.out.println(par.getSimb().get());
		}
		System.out.println("\nPILAAUX:");
		while(itAux.hasNext()) {
			par=itAux.next();
			System.out.println(par.getSimb().get());
		}
		System.out.println("\nFIN PILAS\n");
	}


}
