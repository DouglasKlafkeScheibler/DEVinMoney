package aplicacao;

import model.entidades.Conta;
import model.entidades.enums.Agencia;

public class Programa {

	public static void main(String[] args) {
		Conta conta1 = new Conta("Douglas", "07048933984", (double) 1000, Agencia.FLORIANOPOLIS);
		Conta conta2 = new Conta("Luiza", "123456789", (double) 1500, Agencia.FLORIANOPOLIS);
		Conta conta3 = new Conta("Jose", "123456789", (double) 5000, Agencia.SÃO_JOSE);
		
		System.out.println(String.format("%04d", conta1.getConta()));
		//System.out.println(conta1.getConta());
		
		
		
		System.out.println(conta1.getSaldo());
		conta1.deposito(1500D);
		System.out.println(conta1.getSaldo());
		conta1.saque(1000D);
		System.out.println(conta1.getSaldo());
		
		System.out.println(conta1.getExtrato());
		
	}

}
