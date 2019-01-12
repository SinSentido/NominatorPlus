package keyboard;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Keyboard {
	 static Scanner keyboard = new Scanner(System.in);
	 
	 //ENUM PARA LAS FUNCIONES DE LEER NÚMERO MAYOR/MENOR/IGUAL
	 public enum MayorMenor
	 { 
		 MAYOR_IGUAL, MENOR_IGUAL, MAYOR, MENOR
	 }
	 
	 //ENUM PARA LA FUNCIÓN DE LEER NÚMERO ENTRE UN VALOR MÍNIMO Y UN VALOR MÁXIMO
	 public enum Limit{
		 MAX_MIN_INCLUDED, MAX_MIN_EXCLUDED, MIN_INCLUDED, MAX_INCLUDED
	 }
	
	 //FUNCIÓN PARA CERRAR TECLADO
	 public static void closeKeyboard() {
		 keyboard.close();
	 }
	 
	 //FUNCIÓN PARA LIMPIAR BUFFER
	 public static void cleanBuffer() {
		 keyboard.nextLine();
	 }
	 
	 /*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  * FUNCIÓN PARA LEER CARÁCTER
	  * 
	  * 1. Se le pide un caracter al usuario.
	  * 2. Se introduce un caracter.
	  * 3. Si se introduce más de un caracter:
	  * 	3.1. Se le pregunta al usuario si está conforme con coger el primer carácter.
	  * 	3.2. Si el usuario está de acuerdo se coge el caracter, en caso de que no esté de acuerdo vuelve a escribir
	  * 		el carácter.
	  *////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
	 
	 //1. Se le pide un caracter al usuario.
	 public static char readChar() {
		 char c='a';
		 String s;
		 boolean sentinel = true;
		
		 while(sentinel) {
			//2. Se introduce un caracter.
		 	s = keyboard.next();
			cleanBuffer();//Limpia Buffer
		 	c = s.charAt(0);
		 	sentinel = false;
		 	//3. Si se introduce más de un caracter:
		 	if(s.length() > 1) {
		 		//3.1. Se le pregunta al usuario si está conforme con coger el primer carácter.
		 		System.out.println("Has introducido más de un carácter.\nSe tomará el primer carácter introducido: " + c);
		 		//3.2. Si el usuario está de acuerdo se coge el caracter, en caso de que no esté de acuerdo vuelve a escribir
		 		//el carácter.
		 		if(!readBoolean("¿Estas de acuerdo?(Pulsa n para volver a elegir)")) {
		 			sentinel = true;
		 			System.out.printf("Escribe otro carácter:%n");
		 		}
		 	}
		 }
		 return c;
	 }
	 
	 /*////////////////////////////////////
	  * FUNCIÓN PARA LEER UNA CADENA
	  * 1. Se le pide una cadena al usuario
	  * 2. El usuario introduce una cadena
	  */////////////////////////////////*/
	 
	 //1. Se le pide una cadena al usuario
	 public static String readString() {
		 String s;
		 //2. El usuario introduce una cadena
		 s = keyboard.nextLine();
		 return s;
	 }
	 
	 /*////////////////////////////////////////////////////////////////////////////////////////
	  * FUNCIÓN PARA LEER DATOS BOOLEANOS CON MENÚ
	  * 
	  * 1. Se le muestra al usuario un mensaje para elegir entre 2 opciones.
	  * 2. Si el usuario intenta elegir una opción distinta se le pedira que vuelva a elegir.
	  * 3. Cuando el usuario elija la primera o la segunda opción se guarda su elección.
	  *//////////////////////////////////////////////////////////////////////////////////////*/
	
	 public static boolean readBoolean(String message, String option1, String option2) {
		 byte choice=0;

		 //1. Se le muestra al usuario un mensaje para elegir entre 2 opciones.
		 System.out.printf("%s%n	1.%s%n	2.%s%n", message, option1, option2);
		 //2. Si el usuario intenta elegir una opción distinta se le pedira que vuelva a elegir.
		 choice = readNumberInRange((byte)1,(byte)2, Limit.MAX_MIN_INCLUDED);

		 //3. Cuando el usuario elija la primera o la segunda opción se guarda su elección.
		 return choice == 1;
	}
	 
	 /*///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  * FUNCIÓN PARA LEER DATOS BOOLEANOS CON PREGUNTA
	  * 
	  * 1. Se le hace una pregunta al usuario que tiene que ser respondida con si o no(S/N)
	  * 2. Si el usuario responde si o no (S/N) se guarda su respuesta y se continua.
	  * 3. Si el usuario responde algo distinto a si o no (S/N) se le pedirá que responda correctamente a la pregunta.
	  */////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
	 
	 public static boolean readBoolean(String question) {
		 char choice;
		 
		 do {
			 //1. Se le hace una pregunta al usuario que tiene que ser respondida con si o no(S/N)
			 System.out.printf("%s (s/n)%n", question);
			 choice = Character.toLowerCase(readChar()); //Pasa la variable choice a minúscula para hacer las comprobaciones
			 //2. Si el usuario responde si o no (S/N) se guarda su respuesta y se continua.
		 }
		 while(choice != 's' && choice != 'n');

		 return choice == 's';
	 }
	 
	 /*//////////////////////////////////////////////////////////////////////////////////////////////
	  * FUNCIÓN PARA LEER DATOS NÚMERICOS TIPO BYTE
	  * 
	  * 1. Se le pide un número al usuario.
	  * 2. El usuario escribe un número
	  * 3. Si escribe algo distinto a un número se le volveŕa a pedir que escriba un número.
	  * 4. Si escribe un número se guarda y se continua.
	  * 
	  * Se repite este mismo planteamiento para todas las funciones de leer un número cualquiera.
	  *////////////////////////////////////////////////////////////////////////////////////////// */
	 
	 //1. Se le pide un número al usuario.
	 public static byte readByte() {
		 byte x=0;
		 boolean sentinel = true;
		 
		 do {
			 try {
				 //2. El usuario escribe un número
				 x = keyboard.nextByte();
				 sentinel = false;
			 }
			 //3. Si escribe algo distinto a un número se le volveŕa a pedir que escriba un número.
			 catch(InputMismatchException e){
				 System.out.println("Has introducido un valor no válido. Introduce un número(byte): ");
				 sentinel = true;

			 }
			 finally {
				 cleanBuffer();
			 }
		 }
		 while(sentinel);
		 //4. Si escribe un número se guarda y se continua.
		 return x;
	 }
	 
	 //FUNCIÓN PARA LEER DATOS NUMÉRICOS TIPO SHORT
	 // 1. Se le pide un número al usuario.
	 public static short readShort() {
		 short x=0;
		 boolean sentinel = true;
		 
		 do {
			 try {
				 //2. El usuario escribe un número
				 x = keyboard.nextShort();
				 sentinel = false;
				 }
			 //3. Si escribe algo distinto a un número se le volveŕa a pedir que escriba un número.
			 catch(InputMismatchException e){
				 System.out.println("Has introducido un valor no válido. Introduce un número(short): ");
				 sentinel = true;
			 }		 
			 finally {
				 cleanBuffer();
			 }
		 }
		 while(sentinel);
		 //4. Si escribe un número se guarda y se continua.
		 return x;
	 }
	 
	 //FUNCIÓN PARA LEER DATOS NUMÉRICOS TIPO INT
	 // // 1. Se le pide un número al usuario.
	 public static int readInt() {
		 int x=0;
		 boolean sentinel = true;
		 
		 do {
			 try {
				 //2. El usuario escribe un número
				 x = keyboard.nextInt();
				 sentinel = false;
			 }
			//3. Si escribe algo distinto a un número se le volveŕa a pedir que escriba un número.
			 catch(InputMismatchException e){
				 System.out.println("Has introducido un valor no válido. Introduce un número(int): ");
				 sentinel = true;
			 }		 
			 finally {
				 cleanBuffer();
			 }
		 }
		 while(sentinel);
		 
		 //4. Si escribe un número se guarda y se continua.
		 return x;
	 }
	 
	 //FUNCIÓN PARA LEER DATOS NUMÉRICOS TIPO LONG
	 // 1. Se le pide un número al usuario.
	 public static long readLong() {
		 long x=0L;
		 boolean sentinel = true;
		 
		 do {
			 try {
				 //2. El usuario escribe un número
				 x = keyboard.nextLong();
				 sentinel = false;
			 }
			//3. Si escribe algo distinto a un número se le volveŕa a pedir que escriba un número.
			 catch(InputMismatchException e){
				 System.out.println("Has introducido un valor no válido. Introduce un número(long): ");
				 sentinel = true;
			 }		 
			 finally {
				 cleanBuffer();
			 }
		 }
		 while(sentinel);
		 //4. Si escribe un número se guarda y se continua.
		 return x;
	 }
	 
	 //FUNCIÓN PARA LEER DATOS NUMÉRICOS TIPO FLOAT
	 // 1. Se le pide un número al usuario.
	 public static float readFloat() {
		 float x=0f;
		 boolean sentinel = true;
		 
		 do {
			 try {
				 //2. El usuario escribe un número
				 x = keyboard.nextFloat();
				 sentinel = false;
			 }
			//3. Si escribe algo distinto a un número se le volveŕa a pedir que escriba un número.
			 catch(InputMismatchException e){
				 System.out.println("Has introducido un valor no válido. Introduce un número(float): ");
				 sentinel = true;
			 }		 
			 finally {
				 cleanBuffer();
			 }
		 }
		 while(sentinel);
		 //4. Si escribe un número se guarda y se continua.
		 return x;
	 }
	 
	 //FUNCIÓN PARA LEER DATOS NUMÉRICOS TIPO DOUBLE
	 // 1. Se le pide un número al usuario.
	 public static double readDouble() {
		 double x=0.0;
		 boolean sentinel = true;
		 
		 do {
			 try {
				 //2. El usuario escribe un número
				 x = keyboard.nextDouble();
				 sentinel = false;
			 }
			//3. Si escribe algo distinto a un número se le volveŕa a pedir que escriba un número.
			 catch(InputMismatchException e){
				 System.out.println("Has introducido un valor no válido. Introduce un número(double): ");
				 keyboard.nextLine(); //Limpia el buffer
				 sentinel = true;
			 }		 
			 finally {
				 cleanBuffer();
			 }
		 }
		 while(sentinel); 
		 //4. Si escribe un número se guarda y se continua.
		 return x;
	 }
	 
	 /*///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  * FUNCIÓN PARA LEER NÚMERO MAYOR/MENOR/IGUAL 
	  * 
	  * 1. Se le indica al usuario que tiene que escribir un número mayor o igual, menor o igual, mayor o menor que un número que se le indica.
	  * 2. Si es mayor o igual:
	  * 	2.1. El usuario escribe un número.
	  * 	2.2. Si el número es menor se le pide que vuelva a escribir otro número.
	  * 3. Si es menor o igual:
	  * 	3.1. El usuario escribe un número.
	  * 	3.2. Si el número es mayor se le pide que vuelva a escribir otro número.
	  * 4. Si es mayor:
	  * 	4.1. El usuario escribe un número.
	  * 	4.2. Si es menor o igual se le pide que vuelva a escribir otro número.
	  * 5. Si es menor:
	  * 	5.1. El usuario escribe un número.
	  * 	5.2. Si es mayor o igual se le pide que vuelva a escribir otro número.
	  * 6. Si escribe algo que no sea un número se le vueve a pedir que escriba un número.
	  * 
	  * Se repetira este planteamiento para todas las funciones de leer un número mayor/menor/igual
	  *///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
	 
	 //FUNCIÓN PARA LEER NÚMERO MAYOR/MENOR/IGUAL TYPO BYTE
	 public static byte readMayorMenor(byte x, MayorMenor VALUE) {
		 byte y=0;
		 boolean sentinel = true;		
		 do {
			 //1. Se le indica al usuario que tiene que introducir un número mayor o igual, menor o igual, mayor o menor que otro número.
			 switch(VALUE){
			 	//2. Si es mayor o igual:
			 	case MAYOR_IGUAL:
			 		//2.1. El usuario escribe un número.
			 		y = readByte();
			 		sentinel = false;
			 		//2.2. Si el número es menor se le pide que vuelva a escribir otro número.
			 		if(y < x) {
			 			System.out.printf("Has introducido un número menor. Tienes que introducir un número mayor o igual que %d%n", x);
			 			sentinel = true;
			 		}
			 		break;
				//3. Si es menor o igual:
			 	case MENOR_IGUAL:
			 		//3.1. El usuario escribe un número.
			 		y = readByte();
			 		sentinel = false;
			 		//3.2. Si el número es mayor se le pide que vuelva a escribir otro número.
			 		if(y > x) {
				 		System.out.printf("Has introducido un número mayor. Tienes que introducir un número menor o igual que %d%n", x);
						sentinel = true;
			 		}
			 		break;
			 	//4. Si es mayor:
				case MAYOR:
					//4.1. El usuario escribe un número.
					y = readByte();
				 	sentinel = false;
				 	//4.2. Si es menor o igual se le pide que vuelva a escribir otro número.
				 	if(y <= x) {
						System.out.printf("Has introducido un número menor o igual. Tienes que introducir un número mayor que %d%n", x);
			 			sentinel = true;
			 		}
					break;
			 	//5. Si es menor:
			 	case MENOR:
				 	//5.1. El usuario escribe un número.
			 		y = readByte();
					sentinel = false;
			 		//5.2. Si es mayor o igual se le pide que vuelva a escribir otro número.
			 		if(y >= x) {
			 			System.out.printf("Has introducido un mayor o igual . Tienes que introducir un número menor que %d%n", x);
			 			sentinel = true;
				 	}
				 	break;
			}
		 }
		 while(sentinel);
		 return y;
	 }
	 
	 //FUNCIÓN PARA LEER NÚMERO MAYOR/MENOR/IGUAL TYPO SHORT
	 public static short readMayorMenor(short x, MayorMenor VALUE) {
		 short y=0;
		 boolean sentinel = true;		
		 do {
			 //1. Se le indica al usuario que tiene que introducir un número mayor o igual, menor o igual, mayor o menor que otro número.
			 switch(VALUE){
			 	//2. Si es mayor o igual:
			 	case MAYOR_IGUAL:
			 		//2.1. El usuario escribe un número.
			 		y = readShort();
			 		sentinel = false;
			 		//2.2. Si el número es menor se le pide que vuelva a escribir otro número.
			 		if(y < x) {
			 			System.out.printf("Has introducido un número menor. Tienes que introducir un número mayor o igual que %d%n", x);
			 			sentinel = true;
			 		}
			 		break;
				//3. Si es menor o igual:
			 	case MENOR_IGUAL:
			 		//3.1. El usuario escribe un número.
			 		y = readShort();
			 		sentinel = false;
			 		//3.2. Si el número es mayor se le pide que vuelva a escribir otro número.
			 		if(y > x) {
				 		System.out.printf("Has introducido un número mayor. Tienes que introducir un número menor o igual que %d%n", x);
						sentinel = true;
			 		}
			 		break;
			 	//4. Si es mayor:
				case MAYOR:
					//4.1. El usuario escribe un número.
					y = readShort();
				 	sentinel = false;
				 	//4.2. Si es menor o igual se le pide que vuelva a escribir otro número.
				 	if(y <= x) {
						System.out.printf("Has introducido un número menor o igual. Tienes que introducir un número mayor que %d%n", x);
			 			sentinel = true;
			 		}
					break;
			 	//5. Si es menor:
			 	case MENOR:
				 	//5.1. El usuario escribe un número.
			 		y = readShort();
					sentinel = false;
			 		//5.2. Si es mayor o igual se le pide que vuelva a escribir otro número.
			 		if(y >= x) {
			 			System.out.printf("Has introducido un mayor o igual . Tienes que introducir un número menor que %d%n", x);
			 			sentinel = true;
				 	}
				 	break;
			}
		 }
		 while(sentinel);
		 return y;
	 }
	 
	 //FUNCIÓN PARA LEER NÚMERO MAYOR/MENOR/IGUAL TYPO INT
	 public static int readMayorMenor(int x, MayorMenor VALUE) {
		 int y=0;
		 boolean sentinel = true;	
		 do {
			 //1. Se le indica al usuario que tiene que introducir un número mayor o igual, menor o igual, mayor o menor que otro número.
			 switch(VALUE){
			 	//2. Si es mayor o igual:
			 	case MAYOR_IGUAL:
			 		//2.1. El usuario escribe un número.
			 		y = readInt();
			 		sentinel = false;
			 		//2.2. Si el número es menor se le pide que vuelva a escribir otro número.
			 		if(y < x) {
			 			System.out.printf("Has introducido un número menor. Tienes que introducir un número mayor o igual que %d%n", x);
			 			sentinel = true;
			 		}
			 		break;
				//3. Si es menor o igual:
			 	case MENOR_IGUAL:
			 		//3.1. El usuario escribe un número.
			 		y = readInt();
			 		sentinel = false;
			 		//3.2. Si el número es mayor se le pide que vuelva a escribir otro número.
			 		if(y > x) {
				 		System.out.printf("Has introducido un número mayor. Tienes que introducir un número menor o igual que %d%n", x);
						sentinel = true;
			 		}
			 		break;
			 	//4. Si es mayor:
				case MAYOR:
					//4.1. El usuario escribe un número.
					y = readInt();
				 	sentinel = false;
				 	//4.2. Si es menor o igual se le pide que vuelva a escribir otro número.
				 	if(y <= x) {
						System.out.printf("Has introducido un número menor o igual. Tienes que introducir un número mayor que %d%n", x);
			 			sentinel = true;
			 		}
					break;
			 	//5. Si es menor:
			 	case MENOR:
				 	//5.1. El usuario escribe un número.
			 		y = readInt();
					sentinel = false;
			 		//5.2. Si es mayor o igual se le pide que vuelva a escribir otro número.
			 		if(y >= x) {
			 			System.out.printf("Has introducido un mayor o igual . Tienes que introducir un número menor que %d%n", x);
			 			sentinel = true;
				 	}
				 	break;
			}
		 }
		 while(sentinel);
		 return y;
	 }
	 
	 //FUNCIÓN PARA LEER NÚMERO MAYOR/MENOR/IGUAL TYPO LONG
	 public static long readMayorMenor(long x, MayorMenor VALUE) {
		 long y=0L;
		 boolean sentinel = true;
		 do {
			 //1. Se le indica al usuario que tiene que introducir un número mayor o igual, menor o igual, mayor o menor que otro número.
			 switch(VALUE){
			 	//2. Si es mayor o igual:
			 	case MAYOR_IGUAL:
			 		//2.1. El usuario escribe un número.
			 		y = readLong();
			 		sentinel = false;
			 		//2.2. Si el número es menor se le pide que vuelva a escribir otro número.
			 		if(y < x) {
			 			System.out.printf("Has introducido un número menor. Tienes que introducir un número mayor o igual que %d%n", x);
			 			sentinel = true;
			 		}
			 		break;
				//3. Si es menor o igual:
			 	case MENOR_IGUAL:
			 		//3.1. El usuario escribe un número.
			 		y = readLong();
			 		sentinel = false;
			 		//3.2. Si el número es mayor se le pide que vuelva a escribir otro número.
			 		if(y > x) {
				 		System.out.printf("Has introducido un número mayor. Tienes que introducir un número menor o igual que %d%n", x);
						sentinel = true;
			 		}
			 		break;
			 	//4. Si es mayor:
				case MAYOR:
					//4.1. El usuario escribe un número.
					y = readLong();
				 	sentinel = false;
				 	//4.2. Si es menor o igual se le pide que vuelva a escribir otro número.
				 	if(y <= x) {
						System.out.printf("Has introducido un número menor o igual. Tienes que introducir un número mayor que %d%n", x);
			 			sentinel = true;
			 		}
					break;
			 	//5. Si es menor:
			 	case MENOR:
				 	//5.1. El usuario escribe un número.
			 		y = readLong();
					sentinel = false;
			 		//5.2. Si es mayor o igual se le pide que vuelva a escribir otro número.
			 		if(y >= x) {
			 			System.out.printf("Has introducido un mayor o igual . Tienes que introducir un número menor que %d%n", x);
			 			sentinel = true;
				 	}
				 	break;
			}
		 }
		 while(sentinel);
		 return y;
	 }
	 
	 //FUNCIÓN PARA LEER NÚMERO MAYOR/MENOR/IGUAL TYPO FLOAT
	 public static float readMayorMenor(float x, MayorMenor VALUE) {
		 float y=0.0f;
		 boolean sentinel = true;
		 do {
			 //1. Se le indica al usuario que tiene que introducir un número mayor o igual, menor o igual, mayor o menor que otro número.
			 switch(VALUE){
			 	//2. Si es mayor o igual:
			 	case MAYOR_IGUAL:
			 		//2.1. El usuario escribe un número.
			 		y = readFloat();
			 		sentinel = false;
			 		//2.2. Si el número es menor se le pide que vuelva a escribir otro número.
			 		if(y < x) {
			 			System.out.printf("Has introducido un número menor. Tienes que introducir un número mayor o igual que %.2f%n", x);
			 			sentinel = true;
			 		}
			 		break;
				//3. Si es menor o igual:
			 	case MENOR_IGUAL:
			 		//3.1. El usuario escribe un número.
			 		y = readFloat();
			 		sentinel = false;
			 		//3.2. Si el número es mayor se le pide que vuelva a escribir otro número.
			 		if(y > x) {
				 		System.out.printf("Has introducido un número mayor. Tienes que introducir un número menor o igual que %.2f%n", x);
						sentinel = true;
			 		}
			 		break;
			 	//4. Si es mayor:
				case MAYOR:
					//4.1. El usuario escribe un número.
					y = readFloat();
				 	sentinel = false;
				 	//4.2. Si es menor o igual se le pide que vuelva a escribir otro número.
				 	if(y <= x) {
						System.out.printf("Has introducido un número menor o igual. Tienes que introducir un número mayor que %.2f%n", x);
			 			sentinel = true;
			 		}
					break;
			 	//5. Si es menor:
			 	case MENOR:
				 	//5.1. El usuario escribe un número.
			 		y = readFloat();
					sentinel = false;
			 		//5.2. Si es mayor o igual se le pide que vuelva a escribir otro número.
			 		if(y >= x) {
			 			System.out.printf("Has introducido un número mayor o igual . Tienes que introducir un número menor que %.2f%n", x);
			 			sentinel = true;
				 	}
				 	break;
			}
		 }
		 while(sentinel);
		 return y;
	 }
	 
	 //FUNCIÓN PARA LEER NÚMERO MAYOR/MENOR/IGUAL TYPO DOUBLE
	 public static double readMayorMenor(double x, MayorMenor VALUE) {
		 double y=0.0;
		 boolean sentinel = true;		
		 do {
			 //1. Se le indica al usuario que tiene que introducir un número mayor o igual, menor o igual, mayor o menor que otro número.
			 switch(VALUE){
			 	//2. Si es mayor o igual:
			 	case MAYOR_IGUAL:
			 		//2.1. El usuario escribe un número.
			 		y = readDouble();
			 		sentinel = false;
			 		//2.2. Si el número es menor se le pide que vuelva a escribir otro número.
			 		if(y < x) {
			 			System.out.printf("Has introducido un número menor. Tienes que introducir un número mayor o igual que %.2f%n", x);
			 			sentinel = true;
			 		}
			 		break;
				//3. Si es menor o igual:
			 	case MENOR_IGUAL:
			 		//3.1. El usuario escribe un número.
			 		y = readDouble();
			 		sentinel = false;
			 		//3.2. Si el número es mayor se le pide que vuelva a escribir otro número.
			 		if(y > x) {
				 		System.out.printf("Has introducido un número mayor. Tienes que introducir un número menor o igual que %.2f%n", x);
						sentinel = true;
			 		}
			 		break;
			 	//4. Si es mayor:
				case MAYOR:
					//4.1. El usuario escribe un número.
					y = readDouble();
				 	sentinel = false;
				 	//4.2. Si es menor o igual se le pide que vuelva a escribir otro número.
				 	if(y <= x) {
						System.out.printf("Has introducido un número menor o igual. Tienes que introducir un número mayor que %.2f%n", x);
			 			sentinel = true;
			 		}
					break;
			 	//5. Si es menor:
			 	case MENOR:
				 	//5.1. El usuario escribe un número.
			 		y = readDouble();
					sentinel = false;
			 		//5.2. Si es mayor o igual se le pide que vuelva a escribir otro número.
			 		if(y >= x) {
			 			System.out.printf("Has introducido un número mayor o igual . Tienes que introducir un número menor que %.2f%n", x);
			 			sentinel = true;
				 	}
				 	break;
			}
		 }
		 while(sentinel);
		 return y;
	 }
	  
	 /*////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  * FUNCIÓN PARA LEER NÚMERO ENTRE UN VALOR MÍNIMO Y UN VALOR MÁXIMO
	  * 
	  * 1. Se le pide al usuario que escriba un número dentro de un rango con un valor mínimo y un valor máximo.
	  * 2. Se le indica al usuario los valores límites:
	  * 	2.1. Valor mínimo y valor máximo incluidos en el rango
	  * 	2.2. Valor mínimo y valor máximo excluidos del rango
	  * 	2.3. Valor mínimo incluido en el rango, valor máximo no.
	  * 	2.4. Valor máximo incluido en el rango, valor mínimo no.
	  * 3. El usuario escribe un número.
	  * 4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
	  * 5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
	  * 
	  * Se repite este planteamiento para todas las sobrecargas de la función.
	  *//////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
	 	 
	 //FUNCIÓN LEER NÚMERO ENTRE UN VALOR MÍNIMO Y UN VALOR MÁXIMO TIPO BYTE
	 //1. Se le pide al usuario que escriba un número dentro de un rango con un valor mínimo y un valor máximo.
	 public static byte readNumberInRange(byte min, byte max, Limit VALUE) {
		 if(min > max) {
			 throw new IllegalArgumentException("ERROR. El valor mínimo es mayor que el valor máximo.");
		 }
		 byte y = 0;
		 boolean sentinel = true;
		 do {
			 //2. Se le indica al usuario los valores límites:
			 switch(VALUE) {
			 	//2.1. Valor mínimo y valor máximo incluidos en el rango
			 	case MAX_MIN_INCLUDED:
				 	//3. El usuario escribe un número.
			 		y = readByte();
					//4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
			 		sentinel = false;
			 		//5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
			 		if(y < min || y > max) {
						System.out.println("El número que has introducido no está dentro del rango");
			 			sentinel = true;
			 		}
			 		break;
			 	//2.2. Valor mínimo y valor máximo excluidos del rango
			 	case MAX_MIN_EXCLUDED:
				 	//3. El usuario escribe un número.
			 		y = readByte();
			 		//4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
				 	sentinel = false;
					//5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
			 		if(y <= min || y >= max) {
			 			System.out.println("El número que has introducido no está dentro del rango");
			 			sentinel = true;
				 	}
					break;				 		
				 //2.3. Valor mínimo incluido en el rango, valor máximo no.
				case MIN_INCLUDED:
			 		//3. El usuario escribe un número.
			 		y = readByte();
			 		//4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
			 		sentinel = false;
			 		//5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
				 	if(y < min || y >= max) {
						System.out.println("El número que has introducido no está dentro del rango");
			 			sentinel = true;
			 		}
					break;				 		
				//2.4. Valor máximo incluido en el rango, valor mínimo no.
			 	case MAX_INCLUDED:
			 		//3. El usuario escribe un número.
			 		y = readByte();
				 	//4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
					sentinel = false;
			 		//5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
			 		if(y <= min || y > max) {
			 			System.out.println("El número que has introducido no está dentro del rango");
						sentinel = true;
			 		}
			 		break;
			 }
		 }
		 while(sentinel);
		 return y;
	 }
	 
	 //FUNCIÓN LEER NÚMERO ENTRE UN VALOR MÍNIMO Y UN VALOR MÁXIMO TIPO SHORT
	 //1. Se le pide al usuario que escriba un número dentro de un rango con un valor mínimo y un valor máximo.
	 public static short readNumberInRange(short min, short max, Limit VALUE) {
		 if(min > max) {
			 throw new IllegalArgumentException("ERROR. El valor mínimo es mayor que el valor máximo.");
		 }
		 short y = 0;
		 boolean sentinel = true;
		 do {
			 //2. Se le indica al usuario los valores límites:
			 switch(VALUE) {
			 	//2.1. Valor mínimo y valor máximo incluidos en el rango
			 	case MAX_MIN_INCLUDED:
				 	//3. El usuario escribe un número.
			 		y = readShort();
					//4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
			 		sentinel = false;
			 		//5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
			 		if(y < min || y > max) {
						System.out.println("El número que has introducido no está dentro del rango");
			 			sentinel = true;
			 		}
			 		break;
			 	//2.2. Valor mínimo y valor máximo excluidos del rango
			 	case MAX_MIN_EXCLUDED:
				 	//3. El usuario escribe un número.
			 		y = readShort();
			 		//4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
				 	sentinel = false;
					//5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
			 		if(y <= min || y >= max) {
			 			System.out.println("El número que has introducido no está dentro del rango");
			 			sentinel = true;
				 	}
					break;				 		
				 //2.3. Valor mínimo incluido en el rango, valor máximo no.
				case MIN_INCLUDED:
			 		//3. El usuario escribe un número.
			 		y = readShort();
			 		//4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
			 		sentinel = false;
			 		//5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
				 	if(y < min || y >= max) {
						System.out.println("El número que has introducido no está dentro del rango");
			 			sentinel = true;
			 		}
					break;				 		
				//2.4. Valor máximo incluido en el rango, valor mínimo no.
			 	case MAX_INCLUDED:
			 		//3. El usuario escribe un número.
			 		y = readShort();
				 	//4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
					sentinel = false;
			 		//5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
			 		if(y <= min || y > max) {
			 			System.out.println("El número que has introducido no está dentro del rango");
						sentinel = true;
			 		}
			 		break;
			 }
		 }
		 while(sentinel);
		 return y;
	 }
	 
	 //FUNCIÓN LEER NÚMERO ENTRE UN VALOR MÍNIMO Y UN VALOR MÁXIMO TIPO INT
	 //1. Se le pide al usuario que escriba un número dentro de un rango con un valor mínimo y un valor máximo.
	 public static int readNumberInRange(int min, int max, Limit VALUE) {
		 //Lanza excepción si el valor mínimo es mayor que el máximo.
		 if(min > max) {
			 throw new IllegalArgumentException("ERROR. El valor mínimo es mayor que el valor máximo.");
		 }
		 int y = 0;
		 boolean sentinel = true;
		 do {
			 //2. Se le indica al usuario los valores límites:
			 switch(VALUE) {
			 	//2.1. Valor mínimo y valor máximo incluidos en el rango
			 	case MAX_MIN_INCLUDED:
				 	//3. El usuario escribe un número.
			 		y = readInt();
					//4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
			 		sentinel = false;
			 		//5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
			 		if(y < min || y > max) {
						System.out.println("El número que has introducido no está dentro del rango");
			 			sentinel = true;
			 		}
			 		break;
			 	//2.2. Valor mínimo y valor máximo excluidos del rango
			 	case MAX_MIN_EXCLUDED:
				 	//3. El usuario escribe un número.
			 		y = readInt();
			 		//4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
				 	sentinel = false;
					//5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
			 		if(y <= min || y >= max) {
			 			System.out.println("El número que has introducido no está dentro del rango");
			 			sentinel = true;
				 	}
					break;				 		
				 //2.3. Valor mínimo incluido en el rango, valor máximo no.
				case MIN_INCLUDED:
			 		//3. El usuario escribe un número.
			 		y = readInt();
			 		//4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
			 		sentinel = false;
			 		//5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
				 	if(y < min || y >= max) {
						System.out.println("El número que has introducido no está dentro del rango");
			 			sentinel = true;
			 		}
					break;				 		
				//2.4. Valor máximo incluido en el rango, valor mínimo no.
			 	case MAX_INCLUDED:
			 		//3. El usuario escribe un número.
			 		y = readInt();
				 	//4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
					sentinel = false;
			 		//5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
			 		if(y <= min || y > max) {
			 			System.out.println("El número que has introducido no está dentro del rango");
						sentinel = true;
			 		}
			 		break;
			 }
		 }
		 while(sentinel);
		 return y;
	 }	
	 
	 //FUNCIÓN LEER NÚMERO ENTRE UN VALOR MÍNIMO Y UN VALOR MÁXIMO TIPO LONG
	 //1. Se le pide al usuario que escriba un número dentro de un rango con un valor mínimo y un valor máximo.
	 public static long readNumberInRange(long min, long max, Limit VALUE) {
		 if(min > max) {
			 throw new IllegalArgumentException("ERROR. El valor mínimo es mayor que el valor máximo.");
		 }
		 long y = 0L;
		 boolean sentinel = true;
		 do {
			 //2. Se le indica al usuario los valores límites:
			 switch(VALUE) {
			 	//2.1. Valor mínimo y valor máximo incluidos en el rango
			 	case MAX_MIN_INCLUDED:
				 	//3. El usuario escribe un número.
			 		y = readLong();
					//4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
			 		sentinel = false;
			 		//5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
			 		if(y < min || y > max) {
						System.out.println("El número que has introducido no está dentro del rango");
			 			sentinel = true;
			 		}
			 		break;
			 	//2.2. Valor mínimo y valor máximo excluidos del rango
			 	case MAX_MIN_EXCLUDED:
				 	//3. El usuario escribe un número.
			 		y = readLong();
			 		//4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
				 	sentinel = false;
					//5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
			 		if(y <= min || y >= max) {
			 			System.out.println("El número que has introducido no está dentro del rango");
			 			sentinel = true;
				 	}
					break;				 		
				 //2.3. Valor mínimo incluido en el rango, valor máximo no.
				case MIN_INCLUDED:
			 		//3. El usuario escribe un número.
			 		y = readLong();
			 		//4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
			 		sentinel = false;
			 		//5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
				 	if(y < min || y >= max) {
						System.out.println("El número que has introducido no está dentro del rango");
			 			sentinel = true;
			 		}
					break;				 		
				//2.4. Valor máximo incluido en el rango, valor mínimo no.
			 	case MAX_INCLUDED:
			 		//3. El usuario escribe un número.
			 		y = readLong();
				 	//4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
					sentinel = false;
			 		//5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
			 		if(y <= min || y > max) {
			 			System.out.println("El número que has introducido no está dentro del rango");
						sentinel = true;
			 		}
			 		break;
			 }
		 }
		 while(sentinel);
		 return y;
	 }
	 
	 //FUNCIÓN LEER NÚMERO ENTRE UN VALOR MÍNIMO Y UN VALOR MÁXIMO TIPO FLOAT
	 //1. Se le pide al usuario que escriba un número dentro de un rango con un valor mínimo y un valor máximo.
	 public static float readNumberInRange(float min, float max, Limit VALUE) {
		 if(min > max) {
			 throw new IllegalArgumentException("ERROR. El valor mínimo es mayor que el valor máximo.");
		 }
		 float y = 0f;
		 boolean sentinel = true;
		 do {
			 //2. Se le indica al usuario los valores límites:
			 switch(VALUE) {
			 	//2.1. Valor mínimo y valor máximo incluidos en el rango
			 	case MAX_MIN_INCLUDED:
				 	//3. El usuario escribe un número.
			 		y = readFloat();
					//4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
			 		sentinel = false;
			 		//5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
			 		if(y < min || y > max) {
						System.out.println("El número que has introducido no está dentro del rango");
			 			sentinel = true;
			 		}
			 		break;
			 	//2.2. Valor mínimo y valor máximo excluidos del rango
			 	case MAX_MIN_EXCLUDED:
				 	//3. El usuario escribe un número.
			 		y = readFloat();
			 		//4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
				 	sentinel = false;
					//5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
			 		if(y <= min || y >= max) {
			 			System.out.println("El número que has introducido no está dentro del rango");
			 			sentinel = true;
				 	}
					break;				 		
				 //2.3. Valor mínimo incluido en el rango, valor máximo no.
				case MIN_INCLUDED:
			 		//3. El usuario escribe un número.
			 		y = readFloat();
			 		//4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
			 		sentinel = false;
			 		//5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
				 	if(y < min || y >= max) {
						System.out.println("El número que has introducido no está dentro del rango");
			 			sentinel = true;
			 		}
					break;				 		
				//2.4. Valor máximo incluido en el rango, valor mínimo no.
			 	case MAX_INCLUDED:
			 		//3. El usuario escribe un número.
			 		y = readFloat();
				 	//4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
					sentinel = false;
			 		//5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
			 		if(y <= min || y > max) {
			 			System.out.println("El número que has introducido no está dentro del rango");
						sentinel = true;
			 		}
			 		break;
			 }
		 }
		 while(sentinel);
		 return y;
	 }
	 
	 //FUNCIÓN LEER NÚMERO ENTRE UN VALOR MÍNIMO Y UN VALOR MÁXIMO TIPO DOUBLE
	 //1. Se le pide al usuario que escriba un número dentro de un rango con un valor mínimo y un valor máximo.
	 public static double readNumberInRange(double min, double max, Limit VALUE) {
		 if(min > max) {
			 throw new IllegalArgumentException("ERROR. El valor mínimo es mayor que el valor máximo.");
		 }
		 double y = 0;
		 boolean sentinel = true;
		 do {
			 //2. Se le indica al usuario los valores límites:
			 switch(VALUE) {
			 	//2.1. Valor mínimo y valor máximo incluidos en el rango
			 	case MAX_MIN_INCLUDED:
				 	//3. El usuario escribe un número.
			 		y = readDouble();
					//4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
			 		sentinel = false;
			 		//5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
			 		if(y < min || y > max) {
						System.out.println("El número que has introducido no está dentro del rango");
			 			sentinel = true;
			 		}
			 		break;
			 	//2.2. Valor mínimo y valor máximo excluidos del rango
			 	case MAX_MIN_EXCLUDED:
				 	//3. El usuario escribe un número.
			 		y = readDouble();
			 		//4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
				 	sentinel = false;
					//5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
			 		if(y <= min || y >= max) {
			 			System.out.println("El número que has introducido no está dentro del rango");
			 			sentinel = true;
				 	}
					break;				 		
				 //2.3. Valor mínimo incluido en el rango, valor máximo no.
				case MIN_INCLUDED:
			 		//3. El usuario escribe un número.
			 		y = readDouble();
			 		//4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
			 		sentinel = false;
			 		//5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
				 	if(y < min || y >= max) {
						System.out.println("El número que has introducido no está dentro del rango");
			 			sentinel = true;
			 		}
					break;				 		
				//2.4. Valor máximo incluido en el rango, valor mínimo no.
			 	case MAX_INCLUDED:
			 		//3. El usuario escribe un número.
			 		y = readDouble();
				 	//4. Si el número del usuario está dentro de los límites indicados guarda la respuesta y continua.
					sentinel = false;
			 		//5. Si el número del usuario no está dentro de los límites indicados se le vuelve a pedir que introduzca un número.
			 		if(y <= min || y > max) {
			 			System.out.println("El número que has introducido no está dentro del rango");
						sentinel = true;
			 		}
			 		break;
			 }
		 }
		 while(sentinel);
		 return y;
	 }
}