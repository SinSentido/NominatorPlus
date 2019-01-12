package NominatorPlus;
import static keyboard.Keyboard.*;
import java.io.FileWriter;
import java.io.PrintWriter;

public class NominatorFunctions {
	
	static FileWriter nomina = null;
	static PrintWriter pw = null;
	
	//FUNCION PARA IMPRIMIR LA CABECERA Y LOS DATOS BÁSICOS DE LA NÓMINA
	public static void basicData(int contract, double salary, double extraPay, int numberExtraPays, double hhee) {
		try {
			nomina = new FileWriter("/home/sinsentido/Documentos/Nomina.txt", true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
		pw = new PrintWriter(nomina);
		
		pw.printf("%n----------NÓMINA FOL----------%n%n");
			
		if(contract == 1) {
			pw.printf("TIPO DE CONTRATO: Indefinido%n%n");
		}
		else {
			pw.printf("TIPO DE CONTRATO: Temporal%n%n");
		}
		pw.printf("%n----------I DEVENGOS----------%n%n");	
		pw.printf("Salario base:_______________ %f€%n", salary);
		pw.printf("Pagas extra:________________ %f€ (%d anuales)%n", extraPay, numberExtraPays);
		pw.printf("Horas extra:________________ %f€%n", hhee);
		pw.close();
	}

	//FUNCION PARA INTRODUCIR LOS PLUSES EN LOS ARRAYS.
	public static void pluses(String plusConcept[], double plusAmount[]) {
		try {
			nomina = new FileWriter("/home/sinsentido/Documentos/Nomina.txt", true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		pw = new PrintWriter(nomina);
		
		//Se van rellenando el concepto del plus y su cantidad
		for(int i=0; i<plusConcept.length; i++) {
			System.out.println("Introduce el concepto del plus");
			plusConcept[i] = readString();
			pw.printf("%s_______________ ", plusConcept[i]); //Imprime en el documento
			System.out.println("Introduce la cantidad de " + plusConcept[i] + ":");
			plusAmount[i] = readDouble();
			pw.printf("%f %n", plusAmount[i]); //Imprime en el documento
		}
		pw.close();
	}
	
	//FUNCION PARA CALCULAR EL TOTAL DEVENGADO.
	public static double totalDevengado(double salary, double extrapay, double plusAmount[], double hhee) {
		double total = 0, plusTotal = 0;
		boolean extraPayThisMonth;
		
		try {
			nomina = new FileWriter("/home/sinsentido/Documentos/Nomina.txt", true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		pw = new PrintWriter(nomina);
		
		//Para calcular el total devengado simplemente se suman todos los ingresos que se perciben.
		//Primero calculamos el total de los pluses
		for(int i=0; i<plusAmount.length; i++) {
			plusTotal += plusAmount[i];
		}
		
		//Se pregunta si cobra paga extra este mes
		extraPayThisMonth = readBoolean("¿Cobras paga extra este mes?");
		//En caso de que si la cobre se calcula el total devengado con la paga extra
		if(extraPayThisMonth) {
			total = salary + extrapay + plusTotal + hhee;
		}
		//Si no la cobra se calcula sin la paga extra
		else {
			total = salary + plusTotal + hhee;
		}
		pw.printf("%nTOTAL DEVENGADO _______________  %f%n%n", total);
		pw.close();
		return total;
	}
	
	//FUNCION PARA CALCULAR EL BCCC
	public static double bccc(double salary, double extraPay, int numberExtraPays, double plusAmount[]) {
		double total = 0;
		double plusTotal = 0;
		
		try {
			nomina = new FileWriter("/home/sinsentido/Documentos/Nomina.txt", true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		pw = new PrintWriter(nomina);

		//Para calcular el BCCC sumamos todos los ingresos menos las horas extra, y las pagas extra van prorrateadas
		//Primero calculamos el total de los pluses
		for(int i=0; i<plusAmount.length; i++) {
			plusTotal += plusAmount[i];
		}
		
		//Ahora se suman todos los elemento prorrateando las pagas extra.
		total = salary + plusTotal + ((extraPay*numberExtraPays)/12);
		
		pw.printf("%n----------BASES DE COTIZACIÓN----------%n%n");
		pw.printf("BCCC_______________ %f%n", total);
		pw.close();
		return total;
	}
	
	//FUNCION PARA CALCULAR EL BCCP
	public static double bccp(double bccc, double hhee) {
		double total = 0;
		
		try {
			nomina = new FileWriter("/home/sinsentido/Documentos/Nomina.txt", true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		pw = new PrintWriter(nomina);
		
		//Para calcular el bccp basta con sumarle las horas extra al bccc que ya tiene que estar calculado.
		total = bccc + hhee;
		pw.printf("BCCP_______________ %f%n", total);
		pw.printf("BChhee_______________ %f%n%n", hhee); //Impirme el BChhee aquí porque no hay ninguna funcion para calcularlo.
		pw.close();
		return total;
	}
	
	//FUNCION PARA CALCULAR EL TOTAL A DEDUCIR POR SEGURIDAD SOCIAL
	public static double deduccionesSS(double bccc, double bccp, double bchhee, int contract) {
		double total = 0, cc=0, desempleo=0, fp=0, hhee=0;
		
		try {
			nomina = new FileWriter("/home/sinsentido/Documentos/Nomina.txt", true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		pw = new PrintWriter(nomina);
		
		pw.printf("%n----------II DEDUCCIONES----------%n%n");
		switch(contract) {
		//Si el contrato es indefinido
		case 1:
			//Contingencias comunes
			cc = bccc*0.047;
			pw.printf("Contingencias comunes_____4,70 de BCCC(%f)_____%f€%n", bccc, cc);
			//Desempleo
			desempleo = bccp*0.0155;
			pw.printf("Desempleo_________________1,55 de BCCP(%f)_____%f€%n", bccp, desempleo);
			//Formación profesional
			fp = bccp*0.001;
			pw.printf("Formación profesional_____0,10 de BCCP(%f)_____%f€%n", bccp, fp);
			//Horas extras
			hhee = bchhee*0.047;
			pw.printf("Horas extras______________4,70 de BChhee(%f)___%f€%n%n", bchhee, hhee);
			break;
		//Si el contrato es temporal
		case 2:
			//Contingencias comunes
			cc = bccc*0.047;
			pw.printf("Contingencias comunes_____4,70 de BCCC(%f)_____%f€%n", bccc, cc);
			//Desempleo
			desempleo = bccp*0.016;
			pw.printf("Desempleo_________________1,60 de BCCP(%f)_____%f€%n", bccp, desempleo);
			//Formación profesional
			fp = bccp*0.001;
			pw.printf("Formación profesional_____0,10 de BCCP(%f)_____%f€%n", bccp, fp);
			//Horas extras
			hhee = bchhee*0.047;
			pw.printf("Horas extras______________4,70 de BChhee(%f)___%f€%n%n", bchhee, hhee);
			break;
		}
		
		total = cc + desempleo + fp + hhee;
		pw.printf("Total Seguridad Social:___________________________%f%n%n", total);
		pw.close();
		return total;
	}
	
	//FUNCION PARA CALCULAR EL IRPF
	public static double irpfCalc(double totalDevengado) {
		double total = 0;
		double irpf;
		
		try {
			nomina = new FileWriter("/home/sinsentido/Documentos/Nomina.txt", true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		pw = new PrintWriter(nomina);
		
		//Se introduce el porcentaje de irpf;
		System.out.println("¿Que porcentaje de irpf se aplica?");
		irpf = readDouble();
		
		total = totalDevengado*(irpf/100);
		pw.printf("IRPF %f de %f ________________________%f€%n%n",irpf, totalDevengado, total);
		pw.close();
		return total;
	}
	
	//FUNCION PARA CALCULAR EL NETO
	public static double neto(double irpf, double ss, double totalDevengado) {
		double total = 0;
		
		try {
			nomina = new FileWriter("/home/sinsentido/Documentos/Nomina.txt", true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		pw = new PrintWriter(nomina);
		
		total = totalDevengado - (irpf + ss);
		pw.printf("%n----------III LIQUIDO A PERCIBIR----------%n%n");
		pw.printf("TOTAL A DEDUCIR (Seguridad Social + IRPF) _____________ %f%n%n", irpf+ss);
		pw.printf("SALARIO NETO (Total devengado - Total a deducir)_______ %f%n", total);
		pw.close();
		return total;
	}
	
}
