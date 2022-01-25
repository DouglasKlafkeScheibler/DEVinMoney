package model.entidades;

import java.time.LocalDateTime;

import model.enums.Agencia;
import model.excecoes.SaldoInsuficienteException;

public class ContaCorrente extends Conta{

	public ContaCorrente(String nome, String cPF, Double rendaMensal, Agencia agencia) {
		super(nome, cPF, rendaMensal, agencia);
	}

	@Override
	public void saque(Double saque) {
		if((getSaldo() - saque) < getRendaMensal()*-1) {
			throw new SaldoInsuficienteException("Sua conta não tem saldo para essa transação");
		}
 		else {
 			saldo = saldo - saque;
 			extrato.put(LocalDateTime.now(), -saque);
 		}
	}
}