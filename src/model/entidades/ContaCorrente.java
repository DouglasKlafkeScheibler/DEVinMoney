package model.entidades;

import model.enums.Agencia;

public class ContaCorrente extends Conta{

	public ContaCorrente(String nome, String cPF, Double rendaMensal, Agencia agencia) {
		super(nome, cPF, rendaMensal, agencia);
	}

	@Override
	public void saque(Double saque) {
		if((getSaldo() - saque) < getRendaMensal()*-1) {
			//Era pra ser uma excecao		
			System.out.println("Sua conta não tem saldo para essa transação");
		}
 		else {
 			saldo = saldo - saque;
 			extrato.add(-saque);
 		}
	}
}