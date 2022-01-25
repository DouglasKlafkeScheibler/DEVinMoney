package model.entidades;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import model.enums.Agencia;

public abstract class Conta {
	
	private String nome;
	private String cPF;
	private Double rendaMensal;
	private Long conta;
	private Agencia agencia;
	protected Double saldo = 0D;
	
	protected Map<LocalDateTime, Double> extrato = new HashMap<>();
	
	private static int ID = 1;
	
	
	public Conta(String nome, String cPF, Double rendaMensal, Agencia agencia) {
		this.conta = getProximaContaID();
		this.nome = nome;
		this.cPF = cPF;
		this.rendaMensal = rendaMensal;
		this.agencia = agencia;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getcPF() {
		return cPF;
	}


	public void setcPF(String cPF) {
		this.cPF = cPF;
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
	
	public Map<LocalDateTime, Double> getExtrato() {
		return extrato;
	}
	
	public abstract void saque(Double saque);
	
	public void deposito(Double deposito) {
		saldo += deposito;
		extrato.put(LocalDateTime.now(), deposito);
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
