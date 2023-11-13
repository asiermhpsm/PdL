package practica;

public class Token {
	//codigo del token
	private int codigo;
	//valor numerico o posicion (negativo si es de una TS de funcion) en la TS
	private int valor;
	//cadena
	private String lexema;
	//TODO- flag para saber tipo de token

	
	//constructor si token cadena, palabra reservada o simbolo especial (atributo=null)
	public Token(int codigo, String lexema) {
		this.codigo = codigo;
		if(lexema!=null) {
			this.lexema = lexema;
		}
	}

	//constructor si token numero entero o identificador
	public Token(int codigo, int valor) {
		this.codigo = codigo;
		this.valor = valor;
	}

	public int getCodigo() {
		return codigo;
	}

	public int getValor() {
		return valor;
	}

	public String getLexema() {
		return lexema;
	}
	
	

}