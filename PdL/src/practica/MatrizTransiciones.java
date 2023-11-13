package practica;

public class MatrizTransiciones {
	//cabezera de las columnas	L=A...Z,a...z,_ 	d=espacio o tabulacion	c=cualquier otro caracter
	char[] columnas = {'/','&','n','\'','L',',',';',':','(',')','{','}','+','=','<','d','\r', Lexico.n_l,'c'};

	//matriz con los estados a los que transita a continuacion, -1 si no se transita
	int[][] matrizEstados={
			{ 1,	3,	4,	5,	6,	9,	10,	11,	12,	13,	14,	15,	16,	17,	18,	0, 0, 0, -1},
			{ 2,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1, -1, -1},
			{ 2,	2,	2,	2,	2,	2,	2,	2,	2,	2,	2,	2,	2,	2,	2,	2, 0,-1, 2},
			{ -1,	19,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,	20,	-1,	-1,	-1,	-1, -1},
			{7,	7,	4, 7, 7, 7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	7,	7, 7},
			{ 5,	5,	5,	21,	5,	5,	5,	5,	5,	5,	5,	5,	5,	5,	5,	5, -1,	-1, 5},
			{8,	8,	6,	8,	6, 8, 8, 8	, 8, 8, 8, 8, 8, 8, 8, 8, 8,	8, 8}};

	//matriz con las acciones a realizar o el numero de error
	String[][] matrizAcciones= {
			{"A","A","D","G","J","M","N","O","P","Q","R","S","T","U","V","A","A","A","1"},
			{"A","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19"},
			{"A","A","A","A","A","A","A","A","A","A","A","A","A","A","A","A","A","20","A"},
			{"21","B","22","23","24","25","26","27","28","29","30","31","32","C","33","34","35","36","37"},
			{"F","F","E","F","F","F","F","F","F","F","F","F","F","F","F","F","F","F","F"},
			{"H","H","H","I","H","H","H","H","H","H","H","H","H","H","H","H","38","39","H"},
			{"L","L","K","L","K","L","L","L","L","L","L","L","L","L","L","L","L","L","L"}};

	//constructor
	public MatrizTransiciones() {
	}

	//Devuelve la columna que representa el caracter car
	private int columna(char car) throws Exception{
		char c=car;
		if(car=='_'	||	((int)'A'<=(int)car	&&	(int)car<=(int)'Z')	||	((int)'a'<=(int)car	&&	(int)car<=(int)'z')) {
			c = 'L';
		}else {
			if(car==' '	||	car=='\t') 
				c = 'd';

			else { 
				if((int)'0'<= (int)car && (int)car<=(int)'9') 
					c= 'n';
			}
		}
		int i=0;
		while(i<columnas.length-1) {
			if(columnas[i]==c) {
				return i;
			}
			i++;
		}
		return columnas.length-1;
	}

	//Devuelve el estado (o error) al que transito estando en cierto estado y leyendo cierto car
	public int estado(int estado, char car) throws Exception{
		return matrizEstados[estado][columna(car)];
	}

	//Devuelve la accion a realizar o error estando en cierto estado y leyendo cierto car
	public String accion(int estado, char car) throws Exception{
		return matrizAcciones[estado][columna(car)];
	}
}
