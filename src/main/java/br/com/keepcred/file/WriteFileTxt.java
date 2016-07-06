package br.com.keepcred.file;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.keepcred.entities.Cliente;

public class WriteFileTxt {

	public void write(List<Cliente> lista, String flag, String dateTime) throws IOException {

		String saida = "";
		Date today = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
		String data = sd.format(today);
		if (flag.equals("faepu")) {
			saida = "FAEPU";
		} else {
			saida = "UFU";
		}

		FileWriter arq = new FileWriter("/root/dados/ftp/pagamentos/" + saida + "_Salario_Saida_" + data + ".txt");
		PrintWriter gravarArq = new PrintWriter(arq);
		
		String emp;
		String codempconv;
		String dtpagto = "";
		String formatlinhas = "";
		Integer numregistros = 0;
		long somatoriosalario = 0;
		long codigoconvenio = 0;

		Integer listsize = lista.size();
		String espacos;

		for (int i = 0; i < listsize; i++) {

			// Cabeçalho
			// ---------------------------------------------------------------------------------------------------------------
			if (i == 0) {
				if (flag.equals("faepu")) {
					emp = "FAEPU     ";
					codempconv = "0024341";
				} else {
					emp = "UFU-SERV  ";
					codempconv = "0024333";
				}

				String importacao = "01";
				String bancob = "756";
				String coop = "4363";
				String codlancamento = "53";
				String destmov = "03";
				String espacoscab = "                                                                                                                                                                  ";

				// Date date = new Date();
				// SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				// String now = sdf.format(date);

				String formatcabecalho = String.format("%2s%3s%4s%7s%10s%2s%2s%8s%s", importacao, bancob, coop,
						codempconv, emp, codlancamento, destmov, dateTime, espacoscab);
				gravarArq.printf(formatcabecalho + "\n");
			}

			// Corpo
			// ---------------------------------------------------------------------------------------------------------------
			String umD = "1C";
			String cc = String.format("%010d", lista.get(i).getNUMCONTACORRENTE());
			String nome = lista.get(i).getDESCNOMECLIENTE();
			long cpf = Long.parseLong(lista.get(i).getNUMCPFCNPJ());
			String livre = "PROVTOS";
			String convenio = "000N";
			if (flag.equals("faepu")) {
				codigoconvenio = lista.get(i).getIDCLIENTE();
				espacos = "                                                     ";
			} else {
				dtpagto = lista.get(i).getDtpagto();
				espacos = "                                                  ";
			}
			long isalario = Long.parseLong(lista.get(i).getStotal());

			if (flag.equals("faepu")) {
				formatlinhas = String.format("%2.2s%10s%-40.40s%014d%15s%27s%3s%017d%10s%s%05d%s%n", umD, cc, nome, cpf,
						"000", livre, "", isalario, "", convenio, codigoconvenio, espacos);
			} else {
				formatlinhas = String.format("%2.2s%10s%-40.40s%014d%15s%27s%3s%017d%10s%s%8s%s%n", umD, cc, nome, cpf,
						"000", livre, "", isalario, "", convenio, dtpagto, espacos);
			}

			gravarArq.printf(formatlinhas);

			numregistros++;
			somatoriosalario += isalario;

			// Rodapé
			// ---------------------------------------------------------------------------------------------------------------
			if (i == lista.size() - 1) {

				String codfinal = "9000";
				String totalsal = String.format("%017d", somatoriosalario);
				String espacosrod = "                                                                                                                                                           ";

				String formatrodape = String.format("%4s%024d%17s%s", codfinal, numregistros, totalsal, espacosrod);
				gravarArq.printf(formatrodape + "\n");
			}

			// ---------------------------------------------------------------------------------------------------------------
			// End for
		}
		arq.close();
		System.out.printf("Arquivo salvo!");
	}
}
