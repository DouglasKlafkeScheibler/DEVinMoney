package model.entidades;

import java.time.LocalDateTime;

import model.enums.Agencia;
import model.excecoes.SaldoInsuficienteException;

public class ContaPoupanca extends Conta{

	public ContaPoupanca(String nome, String cPF, Double rendaMensal, Agencia agencia) {
		super(nome, cPF, rendaMensal, agencia);
	}

	@Override
	public void saque(Double saque) {
		if(getSaldo().compareTo(saque) < 0) {
			throw new SaldoInsuficienteException("Sua conta não tem saldo para essa transação");
		}
 		else {
 			saldo = saldo - saque;
 			extrato.put(LocalDateTime.now(), saque);
 		}
	}
	
	public Double calculoRentabilidade(int mes, int porcentagemRentabilidade) {
		Double saldoRendimento = 0D;
		Double saldoTemporario = saldo;
		
		for (int i = 0; i < mes; i++) {
			Double rendimento = saldoTemporario/100 ;
			saldoRendimento += rendimento;
		}
			
		return saldoRendimento;
		
	}
}