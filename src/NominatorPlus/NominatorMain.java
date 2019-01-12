package NominatorPlus;
import static keyboard.Keyboard.*;
import static NominatorPlus.NominatorFunctions.*;

public class NominatorMain {
	
	/*
	 * 1. Se pide el tipo de contrato y otros datos necesarios.
	 * 	1.1 Se pide el salario del mes.
	 *  1.2 Se pide los pluses con su concepto.
	 *  1.3 Se pide la paga extra si la cobra ese mes.
	 *  1.4 Se piden las horas extra si las ha realizado.
	 * 2. Se calcula el devengo total.
	 * 3. Se calculan las bases.
	 *  3.1 Se calcula el BCCC
	 *  3.2 Se calcula el BCCP
	 *  3.3 Se calcula el BChhee
	 * 4. Se calculan las deducciones
	 * 	4.1 Deducciones a la seguridad social dependiendo del tipo de contrato.
	 * 		4.1.1 Deduccion por contingencias comunes
	 * 		4.1.2 Deduccion por desempleo
	 * 		4.1.3 Deducción por formación profesional
	 * 		4.1.4 Deducción por horas extra.
	 * 		4.1.5 Se calcula el total.
	 *  4.2 Se calcula el IRPF sobre el total devengado.
	 * 5. Se suma el total de deduccion de la seguridad social y del IRPF y se resta al total devengado.
	 * */
	
	public static void main(String[] args) {
		
		int contractType, numberExtraPays, plusNumber;	
		double salary, extraPay, devengoTotal, plusAmount[], hhee=0, bccc, bccp, bchhee, irpf, segSocial, neto;
		String plusConcept[];
		boolean programRunning = true;
		
		do {
			System.out.println("-----NOMINATOR PLUS-----");
			
			//1. Se pide el tipo de contrato y otros datos necesarios.
			System.out.printf("¿Qué tipo de contrato tienes?%n1.Contrato indefinido%n2.Contrato temporal%n");
			contractType = readNumberInRange(1,2,Limit.MAX_MIN_INCLUDED);
			
			//Salario mensual
			System.out.println("¿Cual es tu salario mensual?");
			salary = readDouble();
			
			//Información sobre horas extras
			if(readBoolean("¿Has realizado horas extra este mes?")) {
				System.out.println("¿Cuanto cobras por las horas extra?");
				hhee = readDouble();
			}
			
			//Información sobre las pagas extra
			//Número de pagas 
			System.out.println("¿Cuantas pagas extra recibes al año?");
			numberExtraPays = readInt();
			//Cantidad de las pagas
			System.out.println("¿De que cantidad es la paga extra?");
			extraPay = readDouble();
			
			basicData(contractType, salary, extraPay, numberExtraPays);
			
			//Informacion sobre pluses y suplementos
			System.out.println("¿Cuantos pluses tienes que introducir?");
			plusNumber = readInt();
			plusConcept = new String[plusNumber];
			plusAmount = new double[plusNumber];
			//Se rellenan los pluses
			pluses(plusConcept, plusAmount);
			
			//2. Se calcula el devengo total.
			devengoTotal = totalDevengado(salary, extraPay, plusAmount, hhee);
			
			//3. Se calculan las bases.
			bccc = bccc(salary, extraPay, numberExtraPays, plusAmount);
			bccp = bccp(bccc, hhee);
			bchhee = hhee;
			
			//4. Se calculan las deducciones
			//4.1 Deducciones a la seguridad social dependiendo del tipo de contrato.
			segSocial = deduccionesSS(bccc, bccp, bchhee, contractType);
			
			//4.2 Se calcula el IRPF sobre el total devengado.
			irpf = irpfCalc(devengoTotal);
			
			//5. Se suma el total de deduccion de la seguridad social y del IRPF y se resta al total devengado.
			neto = neto(irpf, segSocial, devengoTotal);
			
			System.out.println(neto);
			
			//Se pregunta al usuario si quiere seguir haciendo nóminas.
			programRunning = readBoolean("¿Quieres hacer otra nómina? Pulsa 'n' para salir del programa.");
			
		}while(programRunning);
		
	}
}
