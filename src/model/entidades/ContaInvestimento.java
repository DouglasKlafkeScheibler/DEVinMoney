package model.entidades;

import java.util.Random;

import model.enums.Agencia;
import model.enums.Investimento;

public class ContaInvestimento extends Conta{
	
	Random r = new Random();
	
	private Investimento investimento;
	
	public ContaInvestimento(String nome, String cPF, Double rendaMensal, Agencia agencia) {
		super(nome, cPF, rendaMensal, agencia);
	}

	public Investimento getInvestimento() {
		return investimento;
	}

	public void setInvestimento(Investimento investimento) {
		this.investimento = investimento;
	}

	@Override
	public void saque(Double saque) {
		if(getSaldo().compareTo(saque) < 0) {
			//Era pra ser uma excecao
			System.out.println("Sua conta não tem saldo para essa transação");
		}
 		else {
 			saldo = saldo - saque;
 			extrato.add(saque);
 		}
	}

	//VALOR INCREMENTA CONSTANTE
	public void simularInvestimentoRendaFixa() {
		//0,5% ao mes - 6% ao ano
		Double rendimento = 5/1000D;
		Double rendimentoSaldo = saldo;
		
		for (int i = 0; i < 12; i++) {
			rendimentoSaldo += rendimentoSaldo*rendimento;
		}
		System.out.println("A simulação do rendimento anual da sua conta em Renda fixa é: " + rendimentoSaldo);		
	}
	
	//VALOR INCREMENTA POREM VARIAVEL
	public void simularInvestimentoFundoImobiliario() {
		
		//Varia entre 0,4% a 0,6% ao mes - 4,8% a 7,2% ao ano
		Double numeroMinimo = 0.4D;
		Double numeroMaximo = 0.6D;
		
		Double rendimentoSaldo = saldo;
		
		for (int i = 0; i < 12; i++) {
			Double rendimento = r.nextDouble(numeroMaximo-numeroMinimo) + numeroMinimo;
			rendimentoSaldo += rendimentoSaldo*(rendimento/100);
		}
		System.out.println("A simulação do rendimento anual da sua conta em Fundos imobiliarios é: " + rendimentoSaldo);
	}
	
	//VALOR PODE INCREMENTAR OU DECREMENTAR
	public void simularInvestimentoRendaVariavel() {
		
		//Varia entre -0,1% a 1% ao mes - -1,2% a 12% ao ano
		Double numeroMinimo = -0.1D;
		Double numeroMaximo = 1D;
		
		Double rendimentoSaldo = saldo;
		
		for (int i = 0; i < 12; i++) {
			Double rendimento = r.nextDouble(numeroMaximo-numeroMinimo) + numeroMinimo;
			rendimentoSaldo += rendimentoSaldo*(rendimento/100);
		}
		System.out.println("A simulação do rendimento anual da sua conta em Renda variavel é: " + rendimentoSaldo);
	}
}
	

