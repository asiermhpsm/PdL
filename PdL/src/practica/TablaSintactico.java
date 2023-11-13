package practica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;


public class TablaSintactico {


	public class Simbolo{
		private String valor;
		public Simbolo(String valor){
			this.valor = valor;
		}
		public String get() {
			return valor;
		}
	}
	public class S_Terminal extends Simbolo{
		//En este caso el valor sera numerico
		Token token;
		public S_Terminal(String cod) {
			super(cod);
			this.token=null;
		}
		public S_Terminal(Token token) {
			super(String.valueOf( token.getCodigo() ) );
			this.token=token;
		}
		
		public int getPos() throws Exception{
			return token.getValor();
		}
	}
	public class S_No_Terminal extends Simbolo{
		//En este caso el valor sera no numerico
		public S_No_Terminal(String simbolo) {	super(simbolo);	}
	}
	public class Error extends Simbolo{
		//En este caso el valor sera numerico
		public Error(String cod) {	super(cod);	}
	}
	public class Accion extends Simbolo{
		public Accion(String cod) {	super(cod); }
	}

	S_Terminal [] COLUMNAS=
		{new S_Terminal("24"),new S_Terminal("14"),new S_Terminal("19"),new S_Terminal("20"),new S_Terminal("23"),new S_Terminal("16"),
				new S_Terminal("18"),new S_Terminal("17"),new S_Terminal("25"),new S_Terminal("15"),new S_Terminal("1"),
				new S_Terminal("2"),new S_Terminal("27"),new S_Terminal("3"),new S_Terminal("13"),new S_Terminal("26"),
				new S_Terminal("4"),new S_Terminal("28"),new S_Terminal("5"),new S_Terminal("6"),new S_Terminal("7"),
				new S_Terminal("8"),new S_Terminal("9"),new S_Terminal("10"),new S_Terminal("11"),new S_Terminal("12"),
				new S_Terminal("21"),new S_Terminal("22"),new S_Terminal("30")};
	S_No_Terminal[] FILAS = 
		{new S_No_Terminal("A"), new S_No_Terminal("AI"), new S_No_Terminal("B"), new S_No_Terminal("C"), new S_No_Terminal("D"),
				new S_No_Terminal("DE"), new S_No_Terminal("E"), new S_No_Terminal("EE"), new S_No_Terminal("F"), new S_No_Terminal("G"),
				new S_No_Terminal("H"), new S_No_Terminal("J"), new S_No_Terminal("L"), new S_No_Terminal("LI"), new S_No_Terminal("P"), 
				new S_No_Terminal("R"), new S_No_Terminal("RR"), new S_No_Terminal("S"), new S_No_Terminal("SS"), new S_No_Terminal("SW"), 
				new S_No_Terminal("T"), new S_No_Terminal("U"), new S_No_Terminal("UU"), new S_No_Terminal("V"), new S_No_Terminal("VV"), 
				new S_No_Terminal("X"), new S_No_Terminal("Y"), new S_No_Terminal("Z")};

	GestorErrores ge;

	//Lista de reglas, el 0 son los errores
	List<List<Simbolo>> reglas= new ArrayList<>();

	//Matriz
	List<List<List<Simbolo>>> matriz= new ArrayList<>();

	public TablaSintactico(GestorErrores ge) {
		this.ge=ge;
		for(int i=0; i<=57; i++) {
			reglas.add(new ArrayList<>());
		}
		reglas.get(0).add(new Error("43"));
		
		reglas.get(1).add(new S_No_Terminal("B"));
		reglas.get(1).add(new S_No_Terminal("P"));
		reglas.get(1).add(new Accion("1.1"));
		
		reglas.get(2).add(new S_No_Terminal("F"));
		reglas.get(2).add(new S_No_Terminal("P"));
		reglas.get(2).add(new Accion("2.1"));
		
		reglas.get(3).add(new S_No_Terminal("lambda"));
		
		reglas.get(4).add(new S_Terminal("4"));
		reglas.get(4).add(new S_Terminal("28"));
		reglas.get(4).add(new Accion("4.1"));
		reglas.get(4).add(new S_No_Terminal("H"));
		reglas.get(4).add(new Accion("4.2"));
		reglas.get(4).add(new S_Terminal("19"));
		reglas.get(4).add(new S_No_Terminal("A"));
		reglas.get(4).add(new S_Terminal("20"));
		reglas.get(4).add(new Accion("4.3"));
		reglas.get(4).add(new S_No_Terminal("D"));
		reglas.get(4).add(new Accion("4.4"));
		
		reglas.get(5).add(new S_Terminal("21"));
		reglas.get(5).add(new Accion("5.1"));
		reglas.get(5).add(new S_No_Terminal("C"));
		reglas.get(5).add(new Accion("5.2"));
		reglas.get(5).add(new S_Terminal("22"));
		reglas.get(5).add(new Accion("5.3"));
		
		reglas.get(6).add(new S_No_Terminal("T"));
		reglas.get(6).add(new Accion("6.1"));
		
		reglas.get(7).add(new S_Terminal("lambda"));
		reglas.get(7).add(new Accion("7.1"));
		
		reglas.get(8).add(new S_No_Terminal("T"));
		reglas.get(8).add(new S_Terminal("28"));
		reglas.get(8).add(new S_No_Terminal("AI"));
		reglas.get(8).add(new Accion("8.1"));
		
		reglas.get(9).add(new S_Terminal("lambda"));
		
		reglas.get(10).add(new S_Terminal("16"));
		reglas.get(10).add(new S_No_Terminal("T"));
		reglas.get(10).add(new S_Terminal("28"));
		reglas.get(10).add(new S_No_Terminal("AI"));
		reglas.get(10).add(new Accion("10.1"));
		
		reglas.get(11).add(new S_Terminal("lambda"));
		
		reglas.get(12).add(new Accion("12.1"));
		reglas.get(12).add(new S_No_Terminal("B"));
		reglas.get(12).add(new Accion("12.2"));
		reglas.get(12).add(new S_No_Terminal("C"));
		reglas.get(12).add(new Accion("12.3"));
		
		reglas.get(13).add(new S_Terminal("lambda"));
		reglas.get(13).add(new Accion("13.1"));
		
		reglas.get(14).add(new S_Terminal("5"));
		reglas.get(14).add(new S_Terminal("19"));
		reglas.get(14).add(new S_No_Terminal("E"));
		reglas.get(14).add(new S_Terminal("20"));
		reglas.get(14).add(new Accion("14.1"));
		reglas.get(14).add(new S_No_Terminal("S"));
		reglas.get(14).add(new Accion("14.2"));
		
		reglas.get(15).add(new S_Terminal("8"));
		reglas.get(15).add(new S_Terminal("28"));
		reglas.get(15).add(new S_No_Terminal("T"));
		reglas.get(15).add(new S_Terminal("17"));
		reglas.get(15).add(new Accion("15.1"));
		
		reglas.get(16).add(new Accion("16.1"));
		reglas.get(16).add(new S_No_Terminal("S"));
		reglas.get(16).add(new Accion("16.2"));
		
		reglas.get(17).add(new Accion("17.1"));
		reglas.get(17).add(new S_No_Terminal("SW"));
		reglas.get(17).add(new Accion("17.2"));
		
		reglas.get(18).add(new S_Terminal("7"));
		reglas.get(18).add(new Accion("18.1"));
		
		reglas.get(19).add(new S_Terminal("1"));
		reglas.get(19).add(new Accion("19.1"));
		
		reglas.get(20).add(new S_Terminal("11"));
		reglas.get(20).add(new Accion("20.1"));
		
		reglas.get(21).add(new S_Terminal("28"));
		reglas.get(21).add(new Accion("21.1"));
		reglas.get(21).add(new S_No_Terminal("SS"));
		reglas.get(21).add(new S_Terminal("17"));
		reglas.get(21).add(new Accion("21.2"));
		
		reglas.get(22).add(new S_Terminal("15"));
		reglas.get(22).add(new S_No_Terminal("E"));
		reglas.get(22).add(new Accion("22.1"));
		
		reglas.get(23).add(new S_Terminal("14"));
		reglas.get(23).add(new S_No_Terminal("E"));
		reglas.get(23).add(new Accion("23.1"));
		
		reglas.get(24).add(new S_Terminal("19"));
		reglas.get(24).add(new Accion("24.1"));
		reglas.get(24).add(new S_No_Terminal("L"));
		reglas.get(24).add(new S_Terminal("20"));
		reglas.get(24).add(new Accion("24.2"));
		
		reglas.get(25).add(new S_Terminal("9"));
		reglas.get(25).add(new S_No_Terminal("E"));
		reglas.get(25).add(new S_Terminal("17"));
		reglas.get(25).add(new Accion("25.1"));
		
		reglas.get(26).add(new S_Terminal("6"));
		reglas.get(26).add(new S_Terminal("28"));
		reglas.get(26).add(new S_Terminal("17"));
		reglas.get(26).add(new Accion("26.1"));
		
		reglas.get(27).add(new S_Terminal("10"));
		reglas.get(27).add(new S_No_Terminal("X"));
		reglas.get(27).add(new S_Terminal("17"));
		reglas.get(27).add(new Accion("27.1"));
		
		reglas.get(28).add(new S_No_Terminal("E"));
		reglas.get(28).add(new Accion("28.1"));
		reglas.get(28).add(new S_No_Terminal("LI"));
		reglas.get(28).add(new Accion("28.2"));
		
		reglas.get(29).add(new S_Terminal("lambda"));
		reglas.get(29).add(new Accion("29.1"));
		
		reglas.get(30).add(new S_Terminal("16"));
		reglas.get(30).add(new S_No_Terminal("E"));
		reglas.get(30).add(new Accion("30.1"));
		reglas.get(30).add(new S_No_Terminal("LI"));
		reglas.get(30).add(new Accion("30.2"));
		
		reglas.get(31).add(new S_Terminal("lambda"));
		reglas.get(31).add(new Accion("31.1"));
		
		reglas.get(32).add(new S_No_Terminal("E"));
		reglas.get(32).add(new Accion("32.1"));
		
		reglas.get(33).add(new S_Terminal("lambda"));
		reglas.get(33).add(new Accion("33.1"));
		
		reglas.get(34).add(new S_No_Terminal("R"));
		reglas.get(34).add(new S_No_Terminal("EE"));
		reglas.get(34).add(new Accion("34.1"));
		
		reglas.get(35).add(new S_Terminal("24"));
		reglas.get(35).add(new S_No_Terminal("R"));
		reglas.get(35).add(new S_No_Terminal("EE"));
		reglas.get(35).add(new Accion("35.1"));
		
		reglas.get(36).add(new S_Terminal("lambda"));
		reglas.get(36).add(new Accion("36.1"));
		
		reglas.get(37).add(new S_No_Terminal("U"));
		reglas.get(37).add(new S_No_Terminal("RR"));
		reglas.get(37).add(new Accion("37.1"));
		
		reglas.get(38).add(new S_Terminal("25"));
		reglas.get(38).add(new S_No_Terminal("U"));
		reglas.get(38).add(new S_No_Terminal("RR"));
		reglas.get(38).add(new Accion("38.1"));
		
		reglas.get(39).add(new S_Terminal("lambda"));
		reglas.get(39).add(new Accion("39.1"));
		
		reglas.get(40).add(new S_No_Terminal("V"));
		reglas.get(40).add(new S_No_Terminal("UU"));
		reglas.get(40).add(new Accion("40.1"));
		
		reglas.get(41).add(new S_Terminal("23"));
		reglas.get(41).add(new S_No_Terminal("V"));
		reglas.get(41).add(new S_No_Terminal("UU"));
		reglas.get(41).add(new Accion("41.1"));
		
		reglas.get(42).add(new S_Terminal("lambda"));
		reglas.get(42).add(new Accion("42.1"));
		
		reglas.get(43).add(new S_Terminal("28"));
		reglas.get(43).add(new Accion("43.1"));
		reglas.get(43).add(new S_No_Terminal("VV"));
		reglas.get(43).add(new Accion("43.2"));
		
		reglas.get(44).add(new S_Terminal("26"));
		reglas.get(44).add(new Accion("44.1"));
		
		reglas.get(45).add(new S_Terminal("27"));
		reglas.get(45).add(new Accion("45.1"));
		
		reglas.get(46).add(new S_Terminal("19"));
		reglas.get(46).add(new S_No_Terminal("E"));
		reglas.get(46).add(new S_Terminal("20"));
		reglas.get(46).add(new Accion("46.1"));
		
		reglas.get(47).add(new S_Terminal("19"));
		reglas.get(47).add(new Accion("47.1"));
		reglas.get(47).add(new S_No_Terminal("L"));
		reglas.get(47).add(new S_Terminal("20"));
		reglas.get(47).add(new Accion("47.2"));
		
		reglas.get(48).add(new S_Terminal("lambda"));
		reglas.get(48).add(new Accion("48.1"));
		
		reglas.get(49).add(new S_Terminal("12"));
		reglas.get(49).add(new S_Terminal("19"));
		reglas.get(49).add(new S_No_Terminal("E"));
		reglas.get(49).add(new S_Terminal("20"));
		reglas.get(49).add(new Accion("49.1"));
		reglas.get(49).add(new S_No_Terminal("G"));
		reglas.get(49).add(new Accion("49.2"));
		
		reglas.get(50).add(new S_Terminal("21"));
		reglas.get(50).add(new Accion("50.1"));
		reglas.get(50).add(new S_No_Terminal("Y"));
		reglas.get(50).add(new Accion("50.2"));
		reglas.get(50).add(new S_No_Terminal("DE"));
		reglas.get(50).add(new S_Terminal("22"));
		reglas.get(50).add(new Accion("50.3"));
		
		reglas.get(51).add(new S_Terminal("3"));
		reglas.get(51).add(new S_Terminal("26"));
		reglas.get(51).add(new Accion("51.1"));
		reglas.get(51).add(new S_No_Terminal("J"));
		reglas.get(51).add(new Accion("51.2"));
		reglas.get(51).add(new S_No_Terminal("Y"));
		reglas.get(51).add(new Accion("51.3"));
		
		reglas.get(52).add(new S_Terminal("lambda"));
		reglas.get(52).add(new Accion("52.1"));
		
		reglas.get(53).add(new S_Terminal("13"));
		reglas.get(53).add(new Accion("53.1"));
		reglas.get(53).add(new S_No_Terminal("J"));
		reglas.get(53).add(new Accion("53.2"));
		
		reglas.get(54).add(new S_Terminal("lambda"));
		reglas.get(54).add(new Accion("54.1"));
		
		reglas.get(55).add(new S_Terminal("18"));
		reglas.get(55).add(new Accion("55.1"));
		reglas.get(55).add(new S_No_Terminal("C"));
		reglas.get(55).add(new S_No_Terminal("Z"));
		reglas.get(55).add(new Accion("55.2"));
		
		reglas.get(56).add(new S_Terminal("2"));
		reglas.get(56).add(new S_Terminal("17"));
		reglas.get(56).add(new Accion("56.1"));
		
		reglas.get(57).add(new S_Terminal("lambda"));
		reglas.get(57).add(new Accion("57.1"));
		


		//29columnasx28filas
		for(int i=0; i< 28; i++) {
			List<List<Simbolo>> list = new ArrayList<>();
			for(int j=0; j<29; j++) {
				list.add(reglas.get(0));
			}
			matriz.add(list);
		}

		matriz.get(0).set(3, reglas.get(9));
		matriz.get(0).set(10, reglas.get(8));
		matriz.get(0).set(20, reglas.get(8));
		matriz.get(0).set(24, reglas.get(8));

		matriz.get(1).set(3, reglas.get(11));
		matriz.get(1).set(5, reglas.get(10));

		matriz.get(2).set(17, reglas.get(16));
		matriz.get(2).set(18, reglas.get(14));
		matriz.get(2).set(19, reglas.get(16));
		matriz.get(2).set(21, reglas.get(15));
		matriz.get(2).set(22, reglas.get(16));
		matriz.get(2).set(23, reglas.get(16));
		matriz.get(2).set(25, reglas.get(17));

		matriz.get(3).set(11, reglas.get(13));
		matriz.get(3).set(13, reglas.get(13));
		matriz.get(3).set(14, reglas.get(13));
		matriz.get(3).set(17, reglas.get(12));
		matriz.get(3).set(18, reglas.get(12));
		matriz.get(3).set(19, reglas.get(12));
		matriz.get(3).set(21, reglas.get(12));
		matriz.get(3).set(22, reglas.get(12));
		matriz.get(3).set(23, reglas.get(12));
		matriz.get(3).set(25, reglas.get(12));
		matriz.get(3).set(27, reglas.get(13));

		matriz.get(4).set(26, reglas.get(5));

		matriz.get(5).set(14, reglas.get(53));
		matriz.get(5).set(27, reglas.get(54));

		matriz.get(6).set(2, reglas.get(34));
		matriz.get(6).set(12, reglas.get(34));
		matriz.get(6).set(15, reglas.get(34));
		matriz.get(6).set(17, reglas.get(34));

		matriz.get(7).set(0, reglas.get(35));
		matriz.get(7).set(3, reglas.get(36));
		matriz.get(7).set(5, reglas.get(36));
		matriz.get(7).set(7, reglas.get(36));

		matriz.get(8).set(16, reglas.get(4));

		matriz.get(9).set(26, reglas.get(50));

		matriz.get(10).set(2, reglas.get(7));
		matriz.get(10).set(10, reglas.get(6));
		matriz.get(10).set(20, reglas.get(6));
		matriz.get(10).set(24, reglas.get(6));

		matriz.get(11).set(6, reglas.get(55));

		matriz.get(12).set(2, reglas.get(28));
		matriz.get(12).set(3, reglas.get(29));
		matriz.get(12).set(12, reglas.get(28));
		matriz.get(12).set(15, reglas.get(28));
		matriz.get(12).set(17, reglas.get(28));

		matriz.get(13).set(3, reglas.get(31));
		matriz.get(13).set(5, reglas.get(30));

		matriz.get(14).set(16, reglas.get(2));
		matriz.get(14).set(17, reglas.get(1));
		matriz.get(14).set(18, reglas.get(1));
		matriz.get(14).set(19, reglas.get(1));
		matriz.get(14).set(21, reglas.get(1));
		matriz.get(14).set(22, reglas.get(1));
		matriz.get(14).set(23, reglas.get(1));
		matriz.get(14).set(25, reglas.get(1));
		matriz.get(14).set(28, reglas.get(3));

		matriz.get(15).set(2, reglas.get(37));
		matriz.get(15).set(12, reglas.get(37));
		matriz.get(15).set(15, reglas.get(37));
		matriz.get(15).set(17, reglas.get(37));

		matriz.get(16).set(0, reglas.get(39));
		matriz.get(16).set(3, reglas.get(39));
		matriz.get(16).set(5, reglas.get(39));
		matriz.get(16).set(7, reglas.get(39));
		matriz.get(16).set(8, reglas.get(38));

		matriz.get(17).set(17, reglas.get(21));
		matriz.get(17).set(19, reglas.get(26));
		matriz.get(17).set(22, reglas.get(25));
		matriz.get(17).set(23, reglas.get(27));

		matriz.get(18).set(1, reglas.get(23));
		matriz.get(18).set(2, reglas.get(24));
		matriz.get(18).set(9, reglas.get(22));

		matriz.get(19).set(25, reglas.get(49));

		matriz.get(20).set(10, reglas.get(19));
		matriz.get(20).set(20, reglas.get(18));
		matriz.get(20).set(24, reglas.get(20));

		matriz.get(21).set(2, reglas.get(40));
		matriz.get(21).set(12, reglas.get(40));
		matriz.get(21).set(15, reglas.get(40));
		matriz.get(21).set(17, reglas.get(40));

		matriz.get(22).set(0, reglas.get(42));
		matriz.get(22).set(3, reglas.get(42));
		matriz.get(22).set(4, reglas.get(41));
		matriz.get(22).set(5, reglas.get(42));
		matriz.get(22).set(7, reglas.get(42));
		matriz.get(22).set(8, reglas.get(42));

		matriz.get(23).set(2, reglas.get(46));
		matriz.get(23).set(12, reglas.get(45));
		matriz.get(23).set(15, reglas.get(44));
		matriz.get(23).set(17, reglas.get(43));

		matriz.get(24).set(0, reglas.get(48));
		matriz.get(24).set(2, reglas.get(47));
		matriz.get(24).set(3, reglas.get(48));
		matriz.get(24).set(4, reglas.get(48));
		matriz.get(24).set(5, reglas.get(48));
		matriz.get(24).set(7, reglas.get(48));
		matriz.get(24).set(8, reglas.get(48));

		matriz.get(25).set(2, reglas.get(32));
		matriz.get(25).set(7, reglas.get(33));
		matriz.get(25).set(12, reglas.get(32));
		matriz.get(25).set(15, reglas.get(32));
		matriz.get(25).set(17, reglas.get(32));

		matriz.get(26).set(13, reglas.get(51));
		matriz.get(26).set(14, reglas.get(52));
		matriz.get(26).set(27, reglas.get(52));

		matriz.get(27).set(11, reglas.get(56));
		matriz.get(27).set(13, reglas.get(57));
		matriz.get(27).set(14, reglas.get(57));
		matriz.get(27).set(27, reglas.get(57));
	}

	//aplica una unica regla y hace los cambios necesarios en la pila,(se asume que hay no terminal en la cima)
	//devuelve la regla que se ha aplicado
	public int aplicaRegla(S_Terminal simbTerminal, Stack<Par> pila, List<Par> pilaAux) throws Exception{
		Par cima = pila.pop();
		pilaAux.add(cima);
		List<Simbolo> implicacion = matriz.get(buscarFila((S_No_Terminal)cima.getSimb())).get(buscarColumna(simbTerminal));
		if(implicacion.get(0) instanceof Error){
			ge.run(Integer.valueOf( implicacion.get(0).get() ) );
			return 0;
		}
		for(int i=implicacion.size()-1; i>=0; i--) {
			if(implicacion.get(i).valor != "lambda")
				pila.push(new Par(implicacion.get(i)));
		}
		return buscaNumImplicacion(implicacion);
	}

	private int buscarFila(S_No_Terminal simbolo) throws Exception{
		for(int i=0; i<FILAS.length; i++) {
			if(simbolo.get().equals(FILAS[i].get())) {
				return i;
			}
		}
		return -1;
	}

	private int buscarColumna(S_Terminal simbolo) throws Exception{
		for(int i=0; i<COLUMNAS.length; i++) {
			if(simbolo.get().equals(COLUMNAS[i].get())) {
				return i;
			}
		}
		return -1;
	}

	private int buscaNumImplicacion(List<Simbolo> implicacion) throws Exception{
		Iterator<List<Simbolo>> it = reglas.iterator();
		int i=0;
		while(it.hasNext()) {
			if(it.next()==implicacion) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	

}