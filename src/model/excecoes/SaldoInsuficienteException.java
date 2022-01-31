package model.excecoes;

import model.entidades.Conta;

public class SaldoInsuficienteException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SaldoInsuficienteException(String msg, Conta conta) {
		super(msg);
	}
}
