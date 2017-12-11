package br.edu.ifpr.utils.validators;

import br.edu.ifpr.utils.docs.CpfStatus;

public final class CpfValidator {
	public static CpfStatus validateCpf(final String cpf) {
		// 3 0 1 . 4 1 6 . 6 8 3 - 9 3
		// * 10 9 8 7 6 5 4 3 2
		// * 11 10 9 8 7 6 5 4 3 2
		if (cpf == null || cpf.trim().trim().isEmpty())
			return CpfStatus.DOC_ERROR;
		if (!cpf.matches("\\d{11}"))
			return CpfStatus.DOC_SIZE;

		// realiza o somatório das multiplicações dos pesos pelos dígitos
		int digitosAMenos = 2, digitosCalculados = 10, posicaoDV = 9;
		for (int passos = 1; passos <= 2; passos++) {
			int soma = 0;
			for (int i = 0; i < (cpf.length() - digitosAMenos); i++) {
				int digito = cpf.charAt(i) - 48;
				int multiplicacao = (digitosCalculados - i) * digito;

				soma += multiplicacao;
			} // for (int i = 0; i < (cpf.length() - 2); i++)
				// encontra o primeiro DV
			int resto = 11 - (soma % 11);
			int dv = (resto == 10 || resto == 11) ? 0 : resto;
			// se o DV calculado for diferente do DV1 do CPF retorna com erro
			if (dv != (cpf.charAt(posicaoDV) - 48))
				if (passos == 1)
					return CpfStatus.DOC_DV1;
				else
					return CpfStatus.DOC_DV2;

			--digitosAMenos;
			++digitosCalculados;
			++posicaoDV;
		} // for (int passos = 1; passos <= 2; passos++)
		return CpfStatus.DOC_OK;
	}
}
