package aplicacao;

import model.entidades.Conta;
import model.entidades.ContaCorrente;
import model.entidades.ContaPoupanca;
import model.enums.Agencia;
import model.excecoes.SaldoInsuficienteException;

public class Programa {

	public static void main(String[] args) {
		try {
			Conta conta1 = new ContaCorrente("Douglas", "07048933", 1000D, Agencia.FLORIANOPOLIS);
			Conta conta2 = new ContaCorrente("Luiza", "123456789", 1500D, Agencia.FLORIANOPOLIS);
			ContaPoupanca conta3 = new ContaPoupanca("Jose", "123456789", 5000D, Agencia.SÃO_JOSE);
			
			//System.out.println(String.format("%04d", conta1.getConta()));
			//System.out.println(conta1.getConta());
			
			
			
			conta1.deposito(1500D);
			conta1.transferencia(conta2, 500D);
			
			System.out.println(conta1.getExtrato());
			
			//conta3.deposito(1000D);
			//System.out.println(conta1.getSaldo());
			//System.out.println("A rentabilidade foi: " + conta3.calculoRentabilidade(6, 1)); 
		
			//System.out.println(conta1.getSaldo());
		} 
		catch (SaldoInsuficienteException e) {
			System.out.println(e.getMessage());
		} 
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
