package model.enums;

import java.util.Scanner;

public enum Investimento {
	RENDA_FIXA, RENDA_VARIAVEL, FUNDO_IMOBILIARIO;
	
	public static Investimento getInvestimentoPorNumero(String numString) {
		Scanner sc = new Scanner(System.in);
		Investimento investimentoSelecionado = null;
		
		while(!numString.matches("[1-3]")) {
			System.out.println("Numero de investimento invalido, digite novamente!");
			numString = sc.next();
		}
		int numInt = Integer.parseInt(numString);
		if(numInt == 1) {
			investimentoSelecionado = RENDA_FIXA;
		}
		else if(numInt == 2){
			investimentoSelecionado =  RENDA_VARIAVEL;
		}
		else if(numInt == 3) {
			investimentoSelecionado = FUNDO_IMOBILIARIO;
		}
		else {			
			System.out.println("Algum erro inesperado ocorreu!");
		}
		return investimentoSelecionado;
	}
}
