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
			throw new SaldoInsuficienteException("Sua conta n�o tem saldo para essa transa��o");
		}
 		else {
 			saldo = saldo - saque;
 			extrato.put(LocalDateTime.now(), -saque);
 		}
	}
}