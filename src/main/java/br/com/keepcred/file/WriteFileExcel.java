package br.com.keepcred.file;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.keepcred.entities.Cliente;

public class WriteFileExcel {

	public void write(List<Cliente> lista, String flag, String dateTime) throws IOException {

		String saida = "";
		Date today = new Date();
		SimpleDateFormat sdfout = new SimpleDateFormat("dd-MM-yyyy");
		String dateout = sdfout.format(today);
		if (flag.equals("faepu")) {
			saida = "FAEPU";
		} else {
			saida = "UFU";
		}

		FileWriter arq = new FileWriter("/root/dados/ftp/pagamentos/" + saida + "_Farmacia_Saida_" + dateout + ".txt");
		PrintWriter gravarArq = new PrintWriter(arq);

		String emp;
		String codempconv;
		Integer numregistros = 0;
		float somatorioval = 0;

		Integer listsize = lista.size();

		for (int i = 0; i < listsize; i++) {

			// Cabeçalho
			// ---------------------------------------------------------------------------------------------------------------
			String importacao = "01";
			String bancob = "756";
			String coop = "4363";
			String espacoscab = "                                                                                                                                                                  ";
			String espacosrod = "                                                                                                                                                           ";

			if (flag == "faepu") {
				emp = "FAEPU     ";
				codempconv = "0024341";
			} else {
				emp = "UFU-SERV  ";
				codempconv = "0024333";
			}

			String codlancamento = "10";
			String destmov = "03";

			// Date date = new Date();
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			// String now = sdf.format(date);

			// Corpo
			// ---------------------------------------------------------------------------------------------------------------
			String umD = "1D";
			String cc = String.format("%010d", lista.get(i).getNUMCONTACORRENTE());
			String nome = lista.get(i).getDESCNOMECLIENTE();
			long cpf = Long.parseLong(lista.get(i).getNUMCPFCNPJ());
			String livre = "FARMACIA";
			String convenio = "000NConvênio";
			String espacos = "                                                  ";

			String sTotal = String.format("%.02f", lista.get(i).getTotal());
			sTotal = sTotal.replace(".", "");
			Long iTotal = Long.parseLong(sTotal);

			// Rodapé
			// ---------------------------------------------------------------------------------------------------------------
			String codfinal = "9000";
			numregistros++;
			somatorioval += lista.get(i).getTotal();

			// ---------------------------------------------------------------------------------------------------------------

			if (i == 0) {

				String formatcabecalho = String.format("%2s%3s%4s%7s%10s%2s%2s%8s%s", importacao, bancob, coop,
						codempconv, emp, codlancamento, destmov, dateTime, espacoscab);
				gravarArq.printf(formatcabecalho + "\n");
			}

			String formatlinhas = String.format("%2.2s%10s%-40.40s%014d%15s%28s%2s%017d%10s%s%s%n", umD, cc, nome, cpf,
					"000", livre, "", iTotal, "", convenio, espacos);
			gravarArq.printf(formatlinhas);

			if (i == lista.size() - 1) {
				String zeros = "0000000000000000000000";
				//
				String nc = String.valueOf(numregistros);
				StringBuilder nc2 = new StringBuilder(nc);
				nc2.insert(nc.length(), zeros);

				String tot = String.format("%.02f", somatorioval);
				tot = tot.replace(".", "");
				Long tot2 = Long.parseLong(tot);
				// StringBuilder tot2 = new StringBuilder(tot);
				// tot2.insert(tot.length(), zeros);

				String formatrodape = String.format("%4s%-4.4s%015d%22.22s%s", codfinal, nc2, tot2, zeros, espacosrod);
				gravarArq.printf(formatrodape + "\n");
			}
		}

		arq.close();
		System.out.printf("Arquivo salvo!");
	}
}
