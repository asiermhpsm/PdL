package practica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import practica.TablaSintactico.Simbolo;

public class Par {
	
	
	Simbolo simb;
	String lexema;
	int tipo;
	int numParam;
	List<Integer> tipoParam;
	Iterator<Integer> itTipoParam;
	int tipoDev;
	String etiqueta;
	
	public Par(Simbolo simb) {
		this.simb= simb;
		this.tipoParam=new ArrayList<>();
	}
	
	public Simbolo getSimb() {
		return this.simb;
	}
	
	public String getLexema(){
		return this.lexema;
	}
	
	public int getTipo() {
		return this.tipo;
	}
	
	public int getNumParam(){
		return this.numParam;
	}
	
	public List<Integer> getTipoParam(){
		return this.tipoParam;
	}
	
	public int getTipoDev(){
		return this.tipoDev;
	}
	
	public Iterator<Integer> getItTipoParam(){
		return this.itTipoParam;
	}
	
	public String getEtiqueta(){
		return this.etiqueta;
	}
	
	public void setSimb(Simbolo simb) {
		this.simb=simb;
	}
	
	public void setLexema(String lexema){
		this.lexema=lexema;
	}
	
	public void setTipo(int tipo) {
		this.tipo=tipo;
	}
	
	public void setNumParam(int numParam){
		this.numParam=numParam;
	}
	
	public void setTipoParam(List<Integer> tipoParam){
		this.tipoParam=tipoParam;
	}
	
	public void setTipoDev(int tipoDev){
		this.tipoDev=tipoDev;
	}
	
	public void setEtiqueta(String etiqueta){
		this.etiqueta=etiqueta;
	}
		
	public void setItTipoParam(Iterator<Integer> it) {
		this.itTipoParam=it;
	}

}
