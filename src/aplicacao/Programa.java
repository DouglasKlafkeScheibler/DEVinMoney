package aplicacao;

import java.util.InputMismatchException;

import model.entidades.Conta;
import model.entidades.ContaCorrente;
import model.entidades.ContaInvestimento;
import model.entidades.ContaPoupanca;
import model.enums.Agencia;
import model.excecoes.SaldoInsuficienteException;

public class Programa {	
	public static void main(String[] args) {
		try {			
			Conta conta1 = new ContaCorrente("Douglas", "07048933984", 1000D, Agencia.FLORIANOPOLIS);
			Conta conta2 = new ContaCorrente("Luiza", "11451500980", 1500D, Agencia.FLORIANOPOLIS);
			ContaPoupanca conta3 = new ContaPoupanca("Jose", "123456789", 5000D, Agencia.SÃO_JOSE);
			ContaInvestimento conta4 = new ContaInvestimento("Jose", "123456789", 5000D, Agencia.SÃO_JOSE);
			
			//System.out.println(String.format("%04d", conta1.getConta()));
			//System.out.println(conta1.getConta());
			String cpf = conta2.getCPF();
			System.out.println(Programa.validaCPF(cpf));
			conta1.deposito(1500D);
			conta1.transferencia(conta2, 500D);
			
			System.out.println(conta1.getExtrato());
			
			conta3.deposito(1000D);
			System.out.println(conta3.getSaldo());
			System.out.println(conta3.calculoRentabilidade(12, 6D));
			//System.out.println(conta1.getSaldo());
			//System.out.println("A rentabilidade foi: " + conta3.calculoRentabilidade(12, 1D)); 
		
			//System.out.println(conta1.getSaldo());
			
			conta4.deposito(1000D);
			System.out.println(conta4.getSaldo());
			conta4.simularInvestimentoRendaFixa();
		} 
		catch (SaldoInsuficienteException e) {
			System.out.println(e.getMessage());
		} 
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public static boolean validaCPF(String CPF) {
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

        public static String imprimeCPF(String CPF) {
            return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
            CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
        }
}
