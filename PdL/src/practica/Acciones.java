package practica;

import java.util.List;
import java.util.Stack;

import practica.TablaSintactico.S_Terminal;


public class Acciones {

	static final int ENTERO = 1;
	static final int LOGICO = 2;
	static final int CADENA = 3;
	static final int FUNCION = 4;
	static final int VACIO = 5;
	static final int TIPO_OK = 6;
	static final int TIPO_ERROR= 7;

	GestorErrores ge;
	Lexico lex;
	Stack<Par> pila;
	List<Par> pilaAux;
	TS tsG;
	//Variables auxiliares para comprobaciones semanticas
	TS TSActiva;
	boolean zona_func;
	//boolean zona_switch;
	String str;



	public Acciones(GestorErrores ge, Lexico lex,Stack<Par> pila, List<Par> pilaAux,TS tsG, TS TSActiva) {
		this.ge=ge;
		this.lex = lex;
		this.pila = pila;
		this.pilaAux = pilaAux;
		this.tsG=tsG;
		this.TSActiva = TSActiva;
		this.zona_func=false;
		this.str = "";
	}

	public void run(String cod) throws Exception{
		switch(cod) {
		case "1.1":
			auxPop(2);
			break;
		case "2.1":
			auxPop(2);
			break;
		case "4.1":
			cambiarTSActiva(new TS());
			insertarTipoTS(auxTope(0), FUNCION);
			break;
		case "4.2":
			insertarTipoDevTS(auxTope(1), auxTope(0));
			break;
		case "4.3":
			
			insertarTipoParamTS(auxTope(4), auxTope(1));
			pila.peek().setTipoDev(auxTope(3).getTipo());
			
			break;
		case "4.4":
			//TODO-insertaEtiquetaTS( auxTope(5), nueva_etiqueta() )
			str=str+"TABLA FUNCION\n"+TSActiva.devuelveArchivoTablaSimbolos()+"------------------------\n";
			cambiarTSActiva(tsG);
			auxPop(7);
			break;
		case "5.1":
			zona_func=true;
			pila.peek().setTipoDev(auxTope(1).getTipoDev());
			break;
		case "5.2":
			zona_func=false;
			break;
		case "5.3":
			auxPop(3);
			break;
		case "6.1":
			auxTope(1).setTipo(auxTope(0).getTipo());
			auxPop(1);
			break;
		case "7.1":
			auxTope(0).setTipo(VACIO);
			break;
		case "8.1":
			auxTope(3).getTipoParam().add(auxTope(2).getTipo());
			insertarTipoTS(auxTope(1), auxTope(2));
			auxTope(3).getTipoParam().addAll(auxTope(0).getTipoParam());
			auxPop(3);
			break;
		case "10.1":
			auxTope(4).getTipoParam().add(auxTope(2).getTipo());
			insertarTipoTS(auxTope(1), auxTope(2));
			auxTope(4).getTipoParam().addAll(auxTope(0).getTipoParam());
			auxPop(4);
			break;
		case "12.1":
			if(zona_func) {
				pila.peek().setTipoDev(auxTope(0).getTipoDev());			}
			break;
		case "12.2":
			if(zona_func) {
				pila.peek().setTipoDev(auxTope(1).getTipoDev());			}
			break;
		case "12.3":
			if(auxTope(1).getTipo()==TIPO_OK	&& auxTope(0).getTipo()==TIPO_OK) {
				auxTope(2).setTipo(TIPO_OK);
			}else {
				auxTope(2).setTipo(TIPO_ERROR);
			}
			auxPop(2);
			break;
		case "13.1":
			auxTope(0).setTipo(TIPO_OK);
			break;
		case "14.1":
			if(zona_func) {
				pila.peek().setTipoDev(auxTope(4).getTipoDev());			}
			break;
		case "14.2":
			if(auxTope(2).getTipo()==LOGICO	&&	auxTope(0).getTipo()==TIPO_OK) {
				auxTope(5).setTipo(TIPO_OK);
			}else{
				auxTope(5).setTipo(TIPO_ERROR);
				if(auxTope(2).getTipo()!=LOGICO) {
					ge.run(44);
				}else {
				}
			}
			auxPop(5);
			break;
		case "15.1":
			insertarTipoTS(auxTope(2), auxTope(1));
			//añadeTamañoTS();
			//desp+=auxTipo(1).tamaño;
			auxTope(4).setTipo(TIPO_OK);
			auxPop(4);
			break;
		case "16.1":
			if(zona_func) 
				pila.peek().setTipoDev(auxTope(0).getTipoDev());
			break;
		case "16.2":
			auxTope(1).setTipo(auxTope(0).getTipo());
			auxPop(1);
			break;
		case "17.1":
			//zona_switch=true;
			if(zona_func) 
				pila.peek().setTipoDev(auxTope(0).getTipoDev());
			break;
		case "17.2":
			//zona_switch=false;
			auxTope(1).setTipo(auxTope(0).getTipo());
			auxPop(1);
			break;
		case "18.1":
			auxTope(1).setTipo(ENTERO);
			//auxTope(1).setTamaño(2);
			auxPop(1);
			break;
		case "19.1":
			auxTope(1).setTipo(LOGICO);
			//auxTope(1).setTamaño(1);
			auxPop(1);
			break;
		case "20.1":
			auxTope(1).setTipo(CADENA);
			//auxTope(1).setTamaño(64);
			auxPop(1);
			break;
		case "21.1":
			pila.peek().setTipo(buscarTipoTS(auxTope(0)));
			if(buscarTipoTS(auxTope(0))==FUNCION) {
				pila.peek().setItTipoParam(buscarParamTS(auxTope(0)).iterator());
			}
			break;
		case "21.2":
			auxTope(3).setTipo(auxTope(1).getTipo());
			auxPop(3);
			break;
		case "22.1":
			if(auxTope(2).getTipo()==auxTope(0).getTipo())
				auxTope(2).setTipo(TIPO_OK);
			else {
				auxTope(2).setTipo(TIPO_ERROR);
				ge.run(45);
			}
			auxPop(2);
			break;
		case "23.1":
			if(auxTope(0).getTipo()==LOGICO	&&	auxTope(2).getTipo()==LOGICO)
				auxTope(2).setTipo(TIPO_OK);
			else {
				auxTope(2).setTipo(TIPO_ERROR);
				ge.run(45);
			}
			auxPop(2);
			break;
		case "24.1":
			if(auxTope(1).getTipo()==FUNCION) {
				pila.peek().setItTipoParam(auxTope(1).getItTipoParam());
			}
			break;
		case "24.2":
			if(auxTope(3).getTipo()==FUNCION	&&	auxTope(1).getTipo()==TIPO_OK) {
				auxTope(3).setTipo(TIPO_OK);
			}else {
				auxTope(3).setTipo(TIPO_ERROR);
				ge.run(46);
			}
			auxPop(3);
			break;
		case "25.1":
			if(auxTope(1).getTipo()==CADENA	||	auxTope(1).getTipo()==ENTERO)
				auxTope(3).setTipo(TIPO_OK);
			else {
				auxTope(3).setTipo(TIPO_ERROR);
				ge.run(47);
			}
			auxPop(3);
			break;
		case "26.1":
			if(buscarTipoTS(auxTope(1))==CADENA	||	buscarTipoTS(auxTope(1))==ENTERO) {
				auxTope(3).setTipo(TIPO_OK);
			}else {
				auxTope(3).setTipo(TIPO_ERROR);
				ge.run(48);
			}
			auxPop(3);
			break;
		case "27.1":
			if(zona_func==true	&&	auxTope(1).getTipo()==auxTope(3).getTipoDev())
				auxTope(3).setTipo(TIPO_OK);
			else {
				auxTope(3).setTipo(TIPO_ERROR);
				if(zona_func) {
					ge.run(49);
				}else {
					ge.run(50);
				}
			}
			auxPop(3);
			break;
		case "28.1":
			pila.peek().setItTipoParam(auxTope(1).getItTipoParam());
			if(auxTope(1).getItTipoParam().hasNext()	&&	auxTope(0).getTipo()!=auxTope(1).getItTipoParam().next()) {
				auxTope(1).setTipo(TIPO_ERROR);
				ge.run(51);
			}
			break;
		case "28.2":
			if(auxTope(2).getTipo()==TIPO_ERROR) {
				auxTope(2).setTipo(TIPO_ERROR);
				ge.run(51);
			}else {
				auxTope(2).setTipo(auxTope(0).getTipo());
				
			}
			auxPop(2);
			break;
		case "29.1":
			if(!auxTope(0).getItTipoParam().hasNext()) {
				auxTope(0).setTipo(TIPO_OK);
			}else {
				auxTope(0).setTipo(TIPO_ERROR);
				ge.run(51);
			}
			break;
		case "30.1":
			pila.peek().setItTipoParam(auxTope(2).getItTipoParam());
			if(auxTope(0).getTipo()!=auxTope(3).getItTipoParam().next()) {
				auxTope(2).setTipo(TIPO_ERROR);
				ge.run(51);
			}
			break;
		case "30.2":
			if(auxTope(3).getTipo()==TIPO_ERROR) {
				auxTope(3).setTipo(TIPO_ERROR);
				ge.run(51);
			}else {
				auxTope(3).setTipo(auxTope(0).getTipo());
				
			}
			auxPop(3);
			break;
		case "31.1":
			if(!auxTope(0).getItTipoParam().hasNext()) {
				auxTope(0).setTipo(TIPO_OK);
			}else {
				auxTope(0).setTipo(TIPO_ERROR);
				ge.run(51);
			}
			break;
		case "32.1":
			auxTope(1).setTipo(auxTope(0).getTipo());
			auxPop(1);
			break;
		case "33.1":
			auxTope(0).setTipo(VACIO);
			break;
		case "34.1":
			if(auxTope(0).getTipo()==VACIO) {
				auxTope(2).setTipo(auxTope(1).getTipo());
			}else if(auxTope(0).getTipo()==TIPO_OK	&&	auxTope(1).getTipo()==LOGICO) {
				auxTope(2).setTipo(LOGICO);
			}else {
				auxTope(2).setTipo(TIPO_ERROR);
				ge.run(52);
			}
			auxPop(2);
			break;
		case "35.1":
			if(auxTope(1).getTipo()==LOGICO	&&	auxTope(0).getTipo()==VACIO) {
				auxTope(3).setTipo(TIPO_OK);
			}else {
				auxTope(3).setTipo(TIPO_ERROR);
				ge.run(52);
			}
			auxPop(3);
			break;
		case "36.1":
			auxTope(0).setTipo(VACIO);
			break;
		case "37.1":
			if(auxTope(0).getTipo()==VACIO) {
				auxTope(2).setTipo(auxTope(1).getTipo());
			}else if(auxTope(0).getTipo()==TIPO_OK	&&	auxTope(1).getTipo()==ENTERO) {
				auxTope(2).setTipo(LOGICO);
			}else {
				auxTope(2).setTipo(TIPO_ERROR);
				ge.run(52);
			}
			auxPop(2);
			break;
		case "38.1":
			if(auxTope(1).getTipo()==ENTERO	&&	auxTope(0).getTipo()==VACIO) {
				auxTope(3).setTipo(TIPO_OK);
			}else {
				auxTope(3).setTipo(TIPO_ERROR);
				ge.run(52);
			}
			auxPop(3);
			break;
		case "39.1":
			auxTope(0).setTipo(VACIO);
			break;
		case "40.1":
			if(auxTope(0).getTipo()==VACIO) {
				auxTope(2).setTipo(auxTope(1).getTipo());
			}else if(auxTope(0).getTipo()==TIPO_OK	&&	auxTope(1).getTipo()==ENTERO) {
				auxTope(2).setTipo(ENTERO);
			}else {
				auxTope(2).setTipo(TIPO_ERROR);
				ge.run(52);
			}
			auxPop(2);
			break;
		case "41.1":
			if(auxTope(1).getTipo()==ENTERO	&&	auxTope(0).getTipo()!=TIPO_ERROR) {
				auxTope(3).setTipo(TIPO_OK);
			}else {
				auxTope(3).setTipo(TIPO_ERROR);
				ge.run(52);
			}
			auxPop(3);
			break;
		case "42.1":
			auxTope(0).setTipo(VACIO);
			break;
		case "43.1":
			pila.peek().setTipo(buscarTipoTS(auxTope(0)));
			if(buscarTipoTS(auxTope(0))==FUNCION) {
				pila.peek().setItTipoParam(buscarParamTS(auxTope(0)).iterator());
			} 
			break;
		case "43.2":
			if(auxTope(0).getTipo()==TIPO_OK) {
				auxTope(2).setTipo((buscarTipoTS(auxTope(1))==FUNCION)?buscarTipoDevTS(auxTope(1)):buscarTipoTS(auxTope(1)));
			}else {
				auxTope(2).setTipo(TIPO_ERROR);
				
			}
			auxPop(2);
			break;
		case "44.1":
			auxTope(1).setTipo(ENTERO);
			auxPop(1);
			break;
		case "45.1":
			auxTope(1).setTipo(CADENA);
			auxPop(1);
			break;	
		case "46.1":
			auxTope(3).setTipo(auxTope(1).getTipo());
			auxPop(3);
			break;
		case "47.1":
			if(auxTope(1).getTipo()==FUNCION) {
				pila.peek().setItTipoParam(auxTope(1).getItTipoParam());
			}
			break;
		case "47.2":
			if(auxTope(3).getTipo()==FUNCION	&&	auxTope(1).getTipo()==TIPO_OK) {
				auxTope(3).setTipo(TIPO_OK);
			}else {
				auxTope(3).setTipo(TIPO_ERROR);
			}
			auxPop(3);
			break;
		case "48.1":
			if(auxTope(0).getTipo()!=FUNCION) {
				auxTope(0).setTipo(TIPO_OK);
			}else {
				auxTope(0).setTipo(TIPO_ERROR);
			}
			break;
		case "49.1":
			if(zona_func) {
				pila.peek().setTipoDev(auxTope(4).getTipoDev());
			}
			break;
		case "49.2":
			if(auxTope(2).getTipo()==ENTERO	&&	auxTope(0).getTipo()==TIPO_OK) {
				auxTope(5).setTipo(TIPO_OK);
			}else {
				auxTope(5).setTipo(TIPO_ERROR);
			}
			auxPop(5);
			break;
		case "50.1":
			if(zona_func) {
				pila.peek().setTipoDev(auxTope(1).getTipoDev());
			}
			break;
		case "50.2":
			if(zona_func) {
				pila.peek().setTipoDev(auxTope(2).getTipoDev());
			}
			break;
		case "50.3":
			if(auxTope(2).getTipo()==TIPO_OK	&&	auxTope(1).getTipo()==TIPO_OK) {
				auxTope(4).setTipo(TIPO_OK);
			}else {
				auxTope(4).setTipo(TIPO_ERROR);
			}
			auxPop(4);
			break;
		case "51.1":
			if(zona_func) {
				pila.peek().setTipoDev(auxTope(2).getTipoDev());
			}
			break;
		case "51.2":
			if(zona_func) {
				pila.peek().setTipoDev(auxTope(3).getTipoDev());
			}
			break;
		case "51.3":
			if(auxTope(1).getTipo()==TIPO_OK	&&	auxTope(0).getTipo()==TIPO_OK) {
				auxTope(4).setTipo(TIPO_OK);
			}else {
				auxTope(4).setTipo(TIPO_ERROR);
			}
			auxPop(4);
			break;
		case "52.1":
			auxTope(0).setTipo(TIPO_OK);
			break;
		case "53.1":
			if(zona_func) {
				pila.peek().setTipoDev(auxTope(1).getTipoDev());
			}
			break;
		case "53.2":
			auxTope(2).setTipo(auxTope(0).getTipo());
			auxPop(2);
			break;
		case "54.1":
			auxTope(0).setTipo(TIPO_OK);
			break;
		case "55.1":
			if(zona_func) {
				pila.peek().setTipoDev(auxTope(1).getTipoDev());
			}
			break;
		case "55.2":
			if(auxTope(1).getTipo()==TIPO_OK	&&	auxTope(0).getTipo()==TIPO_OK) {
				auxTope(3).setTipo(TIPO_OK);
			}else {
				auxTope(3).setTipo(TIPO_ERROR);
			}
			auxPop(3);
			break;
		case "56.1":
			auxTope(2).setTipo(TIPO_OK);
			auxPop(2);
			break;
		case "57.1":
			auxTope(2).setTipo(TIPO_OK);
			break;
		}
	}

	//devuelve el Par tope-i de la pilaAux
	private Par auxTope(int i) throws Exception{
		return pilaAux.get(pilaAux.size()-i-1);
	}

	private void cambiarTSActiva(TS ts) throws Exception{
		lex.tsActiva=ts;
		this.TSActiva=ts;
	}

	//Devuelve el tipo de un id (se supone que par pasado es id)
	private int buscarTipoTS(Par id) throws Exception{
		int pos = ((S_Terminal)id.getSimb()).getPos();
		return (pos<0)?TSActiva.getTipo(pos):tsG.getTipo(pos);
	}

	//Devuelve la lista de tipos parametros de funcion de un id (se supone que par pasado es id)
	private List<Integer> buscarParamTS(Par id) throws Exception{
		int pos = ((S_Terminal)id.getSimb()).getPos();
		return (pos<0)?TSActiva.getTiposParam(pos):tsG.getTiposParam(pos);
	}

	private int buscarTipoDevTS(Par id) throws Exception{
		int pos = ((S_Terminal)id.getSimb()).getPos();
		return (pos<0)?TSActiva.getTipoDev(pos):tsG.getTipoDev(pos);
	}

	private void insertarTipoTS(Par id, int tipo) throws Exception{
		int pos = ((S_Terminal)id.getSimb()).getPos();
		if(pos<0) {
			TSActiva.setTipo(pos, tipo);
		}else {
			tsG.setTipo(pos, tipo);
		}
	}

	private void insertarTipoTS(Par id, Par tipo) throws Exception{
		int pos = ((S_Terminal)id.getSimb()).getPos();
		if(pos<0) {
			TSActiva.setTipo(pos, tipo.getTipo());
		}else {
			tsG.setTipo(pos, tipo.getTipo());
		}
	}

	private void insertarTipoParamTS(Par id, List<Integer> lista) throws Exception{
		tsG.setTipoPram(((S_Terminal)id.getSimb()).getPos(), lista);
	}

	private void insertarTipoParamTS(Par id, Par lista) throws Exception{
		tsG.setTipoPram(((S_Terminal)id.getSimb()).getPos(), lista.getTipoParam());
	}

	private void insertarTipoDevTS(Par id, int tipo) throws Exception{
		tsG.setTipoDev(((S_Terminal)id.getSimb()).getPos(), tipo);
	}

	private void insertarTipoDevTS(Par id, Par tipo) throws Exception{
		tsG.setTipoDev(((S_Terminal)id.getSimb()).getPos(), tipo.getTipo());
	}

	private void auxPop(int n) throws Exception{
		for(int i=0; i<n; i++) {
			pilaAux.remove(pilaAux.size()-1);
		}
	}


}
