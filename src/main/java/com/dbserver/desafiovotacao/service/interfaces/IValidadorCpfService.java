package com.dbserver.desafiovotacao.service.interfaces;

public interface IValidadorCpfService {
	/**
     * M�todo que valida um cpf retornando true=valido / false=inv�lido
     * @param cpf : n�mero a ser validado
     * @return
     */
    public boolean isCpfValido(String cpf);
    /**
     * M�todo que retorna resultados aleat�rios se pode votar ou n�o.
     * @param cpf : Identifica��o do associado
     * @return
     */
    public boolean isCpfPodeVotar(String cpf);
}
