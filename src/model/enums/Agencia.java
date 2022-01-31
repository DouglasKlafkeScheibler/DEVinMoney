package model.enums;

import java.util.Scanner;

public enum Agencia {
	FLORIANOPOLIS, SÃO_JOSE;
	
	public static Agencia getAgenciaPorNumero(String numString) {
		Scanner sc = new Scanner(System.in);
		Agencia agenciaSelecionada = null;
		
		while(!numString.matches("[1-2]")) {
			System.out.println("Numero de agencia invalido, digite novamente!");
			numString = sc.next();
		}
		int numInt = Integer.parseInt(numString);
		if(numInt == 1) {
			agenciaSelecionada =  FLORIANOPOLIS;
		}
		else if(numInt == 2){
			agenciaSelecionada = SÃO_JOSE;
		}
		else {
			System.out.println("Algum erro inesperado ocorreu!");
		}
		return agenciaSelecionada;
	}
}
