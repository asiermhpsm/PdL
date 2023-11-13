package practica;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class TS {
	//CONSTANTES
	static final int ENTERO = 1;
	static final int LOGICO = 2;
	static final int CADENA = 3;
	static final int FUNCION = 4;
	static final int VACIO = 5;
	static final int TIPO_OK = 6;
	static final int TIPO_ERROR= 7;
	
	
	//VARIABLE
	List<String> lexemas;
	List<Integer> tipo;
	List<Integer> numParam;
	List<List<Integer>> tipoParam;
	List<Integer> tipoDev;
	List<String> etiqueta;
	
	//constructor
	public TS() {
		this.lexemas = new ArrayList<>();
		this.tipo = new ArrayList<>();
		this.numParam = new ArrayList<>();
		this.tipoParam = new ArrayList<>();
		this.tipoDev = new ArrayList<>();
		this.etiqueta = new ArrayList<>();
	}
	
	//Busca y devuelve la posicion de lexema en la tabla, si no existe lo añade si añadir=true
	public int buscarLexema(String lexema, boolean anadir) throws Exception{
		int i=0;
		for(; i< lexemas.size(); i++) {
			if(lexemas.get(i).equals(lexema)) {
				return i+1;
			}
		}if(anadir) {
			lexemas.add(lexema);
			tipo.add(null);
			numParam.add(null);
			tipoParam.add(null);
			tipoDev.add(null);
			//TODO-etiqueta?
			return lexemas.size();
		}
		return 0;
	}
	
	public void setTipo(int pos, int tipo) {
		this.tipo.set(abs(pos)-1, tipo);
	}
	
	public void setTipoPram(int pos, List<Integer> param) {
		this.tipoParam.set(abs(pos)-1, param);
		this.numParam.set(abs(pos)-1, param.size());
	}
	
	public void setTipoDev(int pos, int tipo) {
		this.tipoDev.set(abs(pos)-1, tipo);
	}
	
	public int getTipo(int pos) throws Exception{
		return tipo.get(abs(pos)-1);
	}
	
	public int numParam(int pos) throws Exception{
		return numParam.get(abs(pos)-1);
	}
	
	public List<Integer> getTiposParam(int pos) throws Exception{
		return tipoParam.get(abs(pos)-1);
	}
	
	public int getTipoDev(int pos) throws Exception{
		return tipoDev.get(abs(pos)-1);
	}
	
	
	private int abs(int n) {
		return (n<0)?-n:n;
	}
	
	public String devuelveArchivoTablaSimbolos() {
		String res="";
		try {
			String tipo;
			String tipoParam;
			for(int i=0;i<lexemas.size();i++) {
				tipo=cambiaTipo(this.tipo.get(i));
				res+="* LEXEMA : '"+lexemas.get(i)+"'"+"\n";
				res+="  + tipo : '"+tipo+"'"+"\n";
				if(tipo == "Funcion") {			
					tipo= cambiaTipo(tipoDev.get(i));
					res+="  + numParam : '"+numParam.get(i)+"'"+"\n";
					res+="  + tipoDev : '"+tipo+"'"+"\n";
					for(int j= 0; j < numParam.get(i); j++) {
						tipoParam= cambiaTipo(this.tipoParam.get(i).get(j));
						res+="  + tipoParam : '"+tipoParam+"'"+"\n";
						}
					}
				}
			res+="------------------------\n";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	private String cambiaTipo (int n) {
		String tipo;
		switch (n) {
		case 1:
			return tipo= "Entero";
		case 2:
			return tipo= "Logico";
		case 3:
			return tipo= "Cadena";
		default: 
			return tipo = "Funcion";
		}
	}
	

}