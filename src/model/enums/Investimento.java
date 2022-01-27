package model.enums;

public enum Investimento {
	RENDA_FIXA, RENDA_VARIAVEL, FUNDO_IMOBILIARIO;
	
	public static Investimento getInvestimentoPorNumero(int num) {
		if(num == 1) {
			return RENDA_FIXA;
		}
		else if(num == 2){
			return RENDA_VARIAVEL;
		}
		else if(num == 3) {
			return FUNDO_IMOBILIARIO;
		}
		else {			
			throw new IllegalArgumentException("Numero de agencia invalido!");
		}
	}
}
