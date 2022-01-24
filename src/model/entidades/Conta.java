package model.entidades;

import java.util.ArrayList;

import model.entidades.enums.Agencia;

public class Conta {
	
	private String nome;
	private String cPF;
	private Double rendaMensal;
	private Long conta;
	private Agencia agencia;
	private Double saldo = 0D;
	
	private ArrayList<Double> extrato = new ArrayList<>();
	
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
	
	public ArrayList<Double> getExtrato() {
		return extrato;
	}
	
	public void saque(Double saque) {
		if(getSaldo().compareTo(saque) <= 0) {
			System.out.println("Sua conta não tem saldo para essa transação");
		}
		else {
			saldo = saldo - saque;
			extrato.add(-saque);
		}
	}
	
	public void deposito(Double deposito) {
		saldo = saldo + deposito;
		extrato.add(deposito);
	}

	public void transferencia(Conta contaTransferir, Conta contaTransferida, Double valor) {
		if(contaTransferir.getSaldo().compareTo(valor) <= 0) {
			System.out.println("Sua conta não tem saldo para essa transação");
		}
		else {
			//saldo = saldo - saque;
			//extrato.add(-saque);
		}
	}
	
	public void alterarDadosCadastrais(Conta contaAlterar, String nomeNovo, Double rendaMensalNova, Agencia agenciaNova) {
		contaAlterar.setNome(nomeNovo);
		contaAlterar.setRendaMensal(rendaMensalNova);
		contaAlterar.setAgencia(agenciaNova);
	}
	
}

