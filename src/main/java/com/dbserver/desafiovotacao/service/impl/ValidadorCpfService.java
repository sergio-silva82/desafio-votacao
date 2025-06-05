package com.dbserver.desafiovotacao.service.impl;

import org.springframework.stereotype.Service;

import com.dbserver.desafiovotacao.service.interfaces.IValidadorCpfService;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
@Service
public class ValidadorCpfService implements IValidadorCpfService { 

    private final Random RANDOM = new Random();

    public boolean isCpfValido(String cpf) {
        if (cpf == null) {
            return false;
        }

        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^\\d]", "");
        
        // Valida a quantidade de 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }
        
        // Valida se é uma sequência repetida.
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
        
        try {
            int digito1 = calcularDigito(cpf.substring(0, 9));
            int digito2 = calcularDigito(cpf.substring(0, 9) + digito1);

            return cpf.equals(cpf.substring(0, 9) + digito1 + digito2);
        } catch (Exception e) {
        	log.error("Erro na validação do CPF: "+mascararCpf(cpf)+" ", e);
            return false;
        }
    }

    private int calcularDigito(String str) throws NumberFormatException{
        try {
			int soma = 0;
			int peso = str.length() + 1;

			for (char c : str.toCharArray()) {
			    int num = c - '0';
			    soma += num * peso--;
			}

			int resto = soma % 11;
			return (resto < 2) ? 0 : 11 - resto;
		} catch (NumberFormatException e) {
			log.error("Erro ao calcular o dígito do CPF: ", e);
			return 0;
		}
    }
    
    private String mascararCpf(String cpf) {
        cpf = cpf.replaceAll("[^\\d]", "");
        if (cpf.length() != 11) return "***";
        return "***.***.***-" + cpf.substring(9);
    }

    public boolean isCpfPodeVotar(String cpf) {
        return RANDOM.nextBoolean();
    }

}