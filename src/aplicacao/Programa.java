package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import model.entidades.Conta;
import model.entidades.ContaCorrente;
import model.entidades.ContaInvestimento;
import model.entidades.ContaPoupanca;
import model.enums.Agencia;
import model.enums.Investimento;
import model.excecoes.SaldoInsuficienteException;

public class Programa {	
	
	static Scanner sc = new Scanner(System.in);
	
	public static List<Conta> contasCadastradas = uploadUsuariosCadastrados();
	
	public static void main(String[] args) {
		try {			
			
			System.out.println("Bem vindo ao DEVInMoney");	
			System.out.println("Informe seus dados cadastrais");
			
			System.out.print("Primeiro nome: ");
			String nome = validaNome(sc.nextLine());
			
			System.out.print("CPF: ");
			String CPF = validaCPF(sc.next());
			
			System.out.print("Renda mensal: ");
			Double rendaMensal = validaDouble(sc.next());
			
			System.out.println("Selecione sua agencia!\n1 - Florianopolis \n2 - São jose");
			Agencia agenciaUsuario = Agencia.getAgenciaPorNumero(sc.next());
			
			
			System.out.println("Qual conta voce deseja abrir?");
			System.out.println("1 - Conta corrente \n2 - Conta poupança \n3 - Conta Investimento");
			int escolhaConta = validaMenuEscolha(sc.next(), 3);
			
			Boolean contaOK = true;
			while(contaOK) {
				switch (escolhaConta) {
				
				case 1:			
					ContaCorrente contaCorrente = new ContaCorrente(nome, CPF, rendaMensal, agenciaUsuario);
					logicaManipulacaoConta(contaCorrente);
					contasCadastradas.add(contaCorrente);
					contaOK = false;
					break;
				case 2:	
					ContaPoupanca contaPoupanca = new ContaPoupanca(nome, CPF, rendaMensal, agenciaUsuario);
					logicaManipulacaoConta(contaPoupanca);
					System.out.println("Simule o valor de rentabilidade na poupança.");
					System.out.println("Indique a quantidade de meses que deseja deixar rendendo");
					int meses = validaInt(sc.next());
					System.out.println("Indique a rentabilidade anual da poupança");
					Double rentabilidade = validaDouble(sc.next());
					System.out.println("A simulacao da sua rentabilidade é: " + contaPoupanca.calculoRentabilidade(meses, rentabilidade));
					contasCadastradas.add(contaPoupanca);
					contaOK = false;
					break;
				case 3:			
					ContaInvestimento contaInvestimento = new ContaInvestimento(nome, CPF, rendaMensal, agenciaUsuario);
					logicaManipulacaoConta(contaInvestimento);
					simulacaoInvestimento(contaInvestimento);
					System.out.println("Qual o investimento voce quer escolher?");
					System.out.println("1 - Renda fixa \n2 - Fundo imobiliario \n3 - Renda variavel");
					Investimento investimentoUsuario = Investimento.getInvestimentoPorNumero(sc.next());
					contaInvestimento.setInvestimento(investimentoUsuario);
					contasCadastradas.add(contaInvestimento);
					contaOK = false;
					break;

				default:
					System.out.println("Opcao invalida");
					break;
				}
			}
		} 
		catch (SaldoInsuficienteException e) {
			System.out.println(e.getMessage());
		} 
		catch (IllegalArgumentException e) {
			System.out.println("Digitou campo errado, reiniciando sistema!");
			System.out.println(e.getMessage());
		}
		catch (InputMismatchException e) {
			System.out.println("Digitou campo errado, reiniciando sistema!");
		}
		catch (RuntimeException e) {
			System.out.println("Aconteceu algo de inesperado, reiniciando sistema!");
			System.out.println(e.getMessage());
		}
		finally {
			System.out.println("Obrigado por nos escolher, volte sempre!!");
		}
	}

	
	public static void logicaManipulacaoConta(Conta contaCliente) {
		int saida = 0;
		while(saida < 999) {
			System.out.println("Bom dia " + contaCliente.getNome() + "! O que voce deseja fazer?");
			System.out.println("1 - Deposito \n2 - Saque \n3 - Saldo \n4 - Extrato \n5 - Transferencia");
			
			int escolhaOperacao = validaMenuEscolha(sc.next(), 5);
			
			switch (escolhaOperacao) {
			case 1:
				System.out.print("Quanto voce deseja depositar:");
				Double deposito = validaDouble(sc.next());
				contaCliente.deposito(deposito);
				break;
				
			case 2:
				System.out.print("Quanto voce deseja sacar:");
				Double saque = validaDouble(sc.next());
				contaCliente.saque(saque);
				break;
			
			case 3:
				System.out.println("Seu saldo é: " + contaCliente.getSaldo());
				break;
				
			case 4:
				System.out.println("Segue seu extrato");
				System.out.println(contaCliente.getExtrato());
				break;
			case 5:
				System.out.println("Digite o CPF do cliente voce quer transferir?");
				int c=0;
				for (Conta conta : contasCadastradas) {
					c++;
					System.out.println("Digite o cpf:" + conta.getCPF() + " para transferir para " + conta.getNome());		
				}
				
				String CPFEscolhido = validaCPF(sc.next());
				
				Conta ContaEscolhida = contasCadastradas.stream()
										.filter(cliente -> CPFEscolhido.equals(cliente.getCPF()))
										.findAny()
										.orElse(null);
				
				if(ContaEscolhida == null) {
					System.out.println("Conta não encontrada");
				}
				else {
					System.out.println("Quanto deseja transferir?");
					Double valorTransferir = validaDouble(sc.next());
					contaCliente.transferencia(ContaEscolhida, valorTransferir);
				}
				break;
			default:
				System.out.println("Opcao invalida");
				break;
			}
			System.out.println("**********************************");
			System.out.println("Se desejar fazer outra operação digite qualquer numero, se desejar sair digite 999?");
			saida = validaInt(sc.next());
		}
	}
	
	public static void simulacaoInvestimento(ContaInvestimento conta) {
		int saida = 0;
		while(saida < 999) {
			System.out.println("Qual o tipo de simulação voce deseja fazer?");
			System.out.println("1 - Renda fixa \n2 - Fundo imobiliario \n3 - Renda variavel");
			
			int escolhaOperacao = validaMenuEscolha(sc.next(), 3);
			
			switch (escolhaOperacao) {
			case 1:
				conta.simularInvestimentoRendaFixa();	
				break;
				
			case 2:
				conta.simularInvestimentoFundoImobiliario();;
				break;
			
			case 3:
				conta.simularInvestimentoRendaVariavel();
				break;
			default:
				System.out.println("Opcao invalida");
				break;
			}
			System.out.println("**********************************");
			System.out.println("Se desejar fazer outra simulação digite qualquer numero, se desejar sair digite 999?");
			saida = validaInt(sc.next());
		}
	}
	
	public static boolean isCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
            CPF.equals("11111111111") ||
            CPF.equals("22222222222") || CPF.equals("33333333333") ||
            CPF.equals("44444444444") || CPF.equals("55555555555") ||
            CPF.equals("66666666666") || CPF.equals("77777777777") ||
            CPF.equals("88888888888") || CPF.equals("99999999999") ||
            (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
        // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
        // converte o i-esimo caractere do CPF em um numero:
        // por exemplo, transforma o caractere '0' no inteiro 0
        // (48 eh a posicao de '0' na tabela ASCII)
            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

        // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                 dig11 = '0';
            else dig11 = (char)(r + 48);

        // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                 return(true);
            else return(false);
                } catch (InputMismatchException erro) {
                return(false);
            }
        }

		public static String validaNome(String nome) {
			while(nome == null || nome == "" || (!nome.matches("[a-zA-Z]+"))) {
				System.out.println("Nome invalido, digite novamente!");
				nome = sc.nextLine();
			}
			return nome;
		}
		
		public static String validaCPF(String CPF) {
			while (!isCPF(CPF)) {
				System.out.println("CPF invalido, digite novamente!");
				CPF = sc.next();
			}
			return CPF;
		}
		
		public static Double validaDouble(String numeroString) {
			Boolean isDouble = false;
			Double numeroDouble = 0D;
			while(!isDouble) {
				try {  
				    numeroDouble = Double.parseDouble(numeroString);
				    isDouble = true;
				  } catch(NumberFormatException e){
					  System.out.println("Voce nao digitou um numero valido, tente novamente!");
					  numeroString = sc.next();
				  }  
			}
			return numeroDouble;
		}
		
		public static int validaInt(String intString) {
			Boolean isInt = false;
			Integer numeroInt = 0;
			while(!isInt) {
				try {  
					numeroInt = Integer.parseInt(intString);
					isInt = true;
				  } catch(NumberFormatException e){
					  System.out.println("Voce nao digitou um numero valido, tente novamente!");
					  intString = sc.next();
				  }  
			}
			return numeroInt;
		}
			
		public static int validaMenuEscolha(String stringEscolhaConta, int numeroOpcoes) {
			String regex = "[1-" + numeroOpcoes + "]";
			int intEscolhaConta = 0;
			
			while(!stringEscolhaConta.matches(regex)) {
				System.out.println("Numero da opcao invalido, digite novamente!");
				stringEscolhaConta = sc.next();
			}
			intEscolhaConta = Integer.parseInt(stringEscolhaConta);
			return intEscolhaConta;
		}
	
        public static String imprimeCPF(String CPF) {
            return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
            CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
        }
        
        public static List<Conta> uploadUsuariosCadastrados() {
        	List<Conta> contasCadastradas = new ArrayList<>();
        	ContaCorrente contaCorrente1 = new ContaCorrente("Pedro", "45441111020", 2000.0, Agencia.SÃO_JOSE);
        	contaCorrente1.deposito(1000D);
        	
        	ContaPoupanca contaPoupanca1 = new ContaPoupanca("João", "73792271222", 1500.0, Agencia.SÃO_JOSE);
        	contaPoupanca1.deposito(5000D);
        	
        	ContaInvestimento contaInvestimento = new ContaInvestimento("Paulo", "60448252643", 2500.0, Agencia.FLORIANOPOLIS);
        	contaInvestimento.deposito(1500D);
        	contaInvestimento.setInvestimento(Investimento.FUNDO_IMOBILIARIO);
        	
        	contasCadastradas.add(contaCorrente1);
        	contasCadastradas.add(contaPoupanca1);
        	contasCadastradas.add(contaInvestimento);
        	
			return contasCadastradas;
        }
}
