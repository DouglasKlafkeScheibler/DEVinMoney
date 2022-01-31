package model.entidades;

import model.enums.Agencia;

public class ContaPoupanca extends Conta{

	public ContaPoupanca(String nome, String cPF, Double rendaMensal, Agencia agencia) {
		super(nome, cPF, rendaMensal, agencia);
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
	
	public Double calculoRentabilidade(int mes, Double rendimentoAnual) {
		Double rendimentoMensal = (rendimentoAnual/12)/100;
		Double rendimentoSaldo = saldo;
		
		for (int i = 0; i < mes; i++) {
			rendimentoSaldo += rendimentoSaldo * rendimentoMensal;
		}
		return rendimentoSaldo;
	}
}