package com.dbserver.desafiovotacao.service.interfaces;

public interface IValidadorCpfService {
	/**
     * Método que valida um cpf retornando true=valido / false=inválido
     * @param cpf : número a ser validado
     * @return
     */
    public boolean isCpfValido(String cpf);
    /**
     * Método que retorna resultados aleatórios se pode votar ou não.
     * @param cpf : Identificação do associado
     * @return
     */
    public boolean isCpfPodeVotar(String cpf);
}
