package model.enums;

public enum Agencia {
	FLORIANOPOLIS, S�O_JOSE;
	
	public static Agencia getAgenciaPorNumero(int num) {
		if(num == 1) {
			return FLORIANOPOLIS;
		}
		else if(num == 2){
			return S�O_JOSE;
		}
		else {
			throw new IllegalArgumentException("Numero de agencia invalido!");
		}
	}
}
