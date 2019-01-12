package NominatorPlus;
import static keyboard.Keyboard.*;

public class NominatorFunctions {

	//FUNCION PARA INTRODUCIR LOS PLUSES EN LOS ARRAYS.
	public static void pluses(String plusConcept[], double plusAmount[]) {
		
		//Se van rellenando el concepto del plus y su cantidad
		for(int i=0; i<plusConcept.length; i++) {
			System.out.println("Introduce el concepto del plus");
			plusConcept[i] = readString();
			System.out.println("Introduce la cantidad de" + plusConcept[i] + ":");
			plusAmount[i] = readDouble();
		}
	}
	
	//FUNCION PARA CALCULAR EL TOTAL DEVENGADO.
	public static double totalDevengado(double salary, double extrapay, double plusAmount[], double hhee) {
		double total = 0, plusTotal = 0;
		boolean extraPayThisMonth;
		
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
		return total;
	}
	
	//FUNCION PARA CALCULAR EL BCCC
	public static double bccc(double salary, double extraPay, int numberExtraPays, double plusAmount[]) {
		double total = 0;
		double plusTotal = 0;

		//Para calcular el BCCC sumamos todos los ingresos menos las horas extra, y las pagas extra van prorrateadas
		//Primero calculamos el total de los pluses
		for(int i=0; i<plusAmount.length; i++) {
			plusTotal += plusAmount[i];
		}
		
		//Ahora se suman todos los elemento prorrateando las pagas extra.
		total = salary + plusTotal + ((extraPay*numberExtraPays)/12);
		
		return total;
	}
	
	//FUNCION PARA CALCULAR EL BCCP
	public static double bccp(double bccc, double hhee) {
		double total = 0;
		
		//Para calcular el bccp basta con sumarle las horas extra al bccc que ya tiene que estar calculado.
		total = bccc + hhee;
		return total;
	}
	
	//FUNCION PARA CALCULAR EL TOTAL A DEDUCIR POR SEGURIDAD SOCIAL
	public static double deduccionesSS(double bccc, double bccp, double bchhee, int contract) {
		double total = 0, cc=0, desempleo=0, fp=0, hhee=0;
		
		switch(contract) {
		//Si el contrato es indefinido
		case 1:
			//Contingencias comunes
			cc = bccc*0.047;
			//Desempleo
			desempleo = bccp*0.0155;
			//Formación profesional
			fp = bccp*0.001;
			//Horas extras
			hhee = bchhee*0.047;
			break;
		//Si el contrato es temporal
		case 2:
			//Contingencias comunes
			cc = bccc*0.047;
			//Desempleo
			desempleo = bccp*0.016;
			//Formación profesional
			fp = bccp*0.001;
			//Horas extras
			hhee = bchhee*0.047;
			break;
		}
		total = cc + desempleo + fp + hhee;
		return total;
	}
	
	//FUNCION PARA CALCULAR EL IRPF
	public static double irpfCalc(double totalDevengado) {
		double total = 0;
		double irpf;
		
		//Se introduce el porcentaje de irpf;
		System.out.println("¿Que porcentaje de irpf se aplica?");
		irpf = readDouble();
		
		total = totalDevengado*(irpf/100);
		return total;
	}
	
}
