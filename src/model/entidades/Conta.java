package model.entidades;

import java.util.ArrayList;
import java.util.List;

import model.enums.Agencia;

public abstract class Conta {
	
	private String nome;
	private String CPF;
	private Double rendaMensal;
	private Long conta;
	private Agencia agencia;
	protected Double saldo = 0D;
	
	protected List<Double> extrato = new ArrayList<>();
	
	private static int ID = 1;
	
	
	public Conta(String nome, String CPF, Double rendaMensal, Agencia agencia) {
		this.conta = getProximaContaID();
		this.nome = nome;
		this.CPF = CPF;
		this.rendaMensal = rendaMensal;
		this.agencia = agencia;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCPF() {
		return CPF;
	}


	public void setcPF(String CPF) {
		this.CPF = CPF;
	}


	public Double getRendaMensal() {
		return rendaMensal;
	}


	public void setRendaMensal(Double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}


	public Agencia getAgencia() {
		return agencia;
	}


	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}


	public Long getConta() {
		return conta;
	}

	public Double getSaldo() {
		return saldo;
	}
	
	public static Long getProximaContaID() {
        return (long) ID++;
    }
	
	public List<Double> getExtrato() {
		return extrato;
	}
	
	public abstract void saque(Double saque);
	
	public void deposito(Double deposito) {
		saldo += deposito;
		extrato.add(deposito);
	}

	public void transferencia(Conta contaTransferida, Double valor) {
		if(contaTransferida.conta == this.conta) {
			throw new IllegalArgumentException("Não é possivel transferir para si mesmo!");
		}
			saque(valor);
			contaTransferida.deposito(valor);
	}
	
	public void alterarDadosCadastrais(String nomeNovo, Double rendaMensalNova, Agencia agenciaNova) {
		setNome(nomeNovo);
		setRendaMensal(rendaMensalNova);
		setAgencia(agenciaNova);
	}
}
