package br.com.keepcred.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import br.com.keepcred.entities.ClienteCapital;
import br.com.keepcred.entities.ClienteEmprestimo;
import br.com.keepcred.entities.Servidor_Pensionista;

@Controller
public class UploadFederaisController {

	Pattern patternServidoresD8 = Pattern.compile(
			"([0-9]{5})([0-9-]{7})([0-9]{9})([A-Za-z]{2})([A-Za-zÀ-Ú/\\s]{50})([0-9-]{11})([0-9-]{5})([0-9-]{1})([0-9]{11})([0-9]{9})");

	Pattern patternPensionistasD8 = Pattern.compile(
			"([0-9]{5})([0-9]{7})([0-9-]{8})([0-9]{9})([A-Za-z]{2})([A-Za-zÀ-Ú/\\s]{45})([0-9-]{11})([0-9-]{5})([0-9-]{1})([0-9]{11})([0-9]{9})");

	Pattern patternCap = Pattern.compile("([0-9-]{7})([0-9-]{8})([A-Za-zÀ-Ú/.\\s]{50})([0-9-]{11})([0-9-]{11})");

	Pattern patternEmp = Pattern.compile(
			"([0-9A-Za-z]{2})([0-9]{10})([0-9A-Za-zÀ-Ú/.\\s]{40})([0-9-]{14})([0-9]{3})([0-9]{8})([0-9]{3})([0-9A-Za-zÀ-Ú-\\s]{20})([0-9-]{17})([0-9]{3})([0-9]{8})([0-9]{1})");

	@RequestMapping(value = "/uploadfederais", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String provideUploadInfo() {
		return "You can upload a file by posting to this same URL.";
	}

	private static String fileName;

	@RequestMapping(value = "/uploadfederais", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody String uploadFederais(@RequestParam("fileServidor") MultipartFile fileServidor,
			@RequestParam("filePensionista") MultipartFile filePensionista,
			@RequestParam("fileCapitalAgu") MultipartFile fileCapitalAgu,
			@RequestParam("fileCapitalDpf") MultipartFile fileCapitalDpf,
			@RequestParam("fileCapitalIftm") MultipartFile fileCapitalIftm,
			@RequestParam("fileCapitalMec") MultipartFile fileCapitalMec,
			@RequestParam("fileCapitalMtb") MultipartFile fileCapitalMtb,
			@RequestParam("fileCapitalUfu") MultipartFile fileCapitalUfu,
			@RequestParam("fileEmprestimoAgu") MultipartFile fileEmprestimoAgu,
			@RequestParam("fileEmprestimoDpf") MultipartFile fileEmprestimoDpf,
			@RequestParam("fileEmprestimoIftm") MultipartFile fileEmprestimoIftm,
			@RequestParam("fileEmprestimoMec") MultipartFile fileEmprestimoMec,
			@RequestParam("fileEmprestimoMtb") MultipartFile fileEmprestimoMtb,
			@RequestParam("fileEmprestimoUfu") MultipartFile fileEmprestimoUfu) throws IOException {

		Date today = new Date();
		SimpleDateFormat sdfout = new SimpleDateFormat("dd-MM-yyyy");
		
		String dateout = sdfout.format(today);
		
		fileName = "/root/dados/ftp/federais/Federais_" + dateout + ".xls";

		Matcher matcher = null;

		if (!fileServidor.isEmpty() && !filePensionista.isEmpty()) {
			try {

				FileWriter arq = new FileWriter("/root/dados/ftp/federais/Federais_Log_" + dateout + ".txt");
				PrintWriter gravarArq = new PrintWriter(arq);

				InputStream inputStream;
				Charset inputCharsetcp1252 = Charset.forName("Cp1252");
				Charset inputCharsetutf8 = Charset.forName("utf-8");
				BufferedReader bufferedReader = null;

				boolean check = false;
				String line;

				Long totalclicompletocap = 0l;
				Long totalclicompletoemp = 0l;
				Long totalcliparcial = 0l;
				Long totalcliestornocap = 0l;
				Long totalcliestornoemp = 0l;

				List<Servidor_Pensionista> lista_servidor_pensionista_cap = new ArrayList<Servidor_Pensionista>();
				List<Servidor_Pensionista> lista_servidor_pensionista_emp = new ArrayList<Servidor_Pensionista>();
				List<Servidor_Pensionista> lista_servidor_pensionista_completo = new ArrayList<Servidor_Pensionista>();

				List<ClienteCapital> listaclientecapital = new ArrayList<ClienteCapital>();
				List<ClienteCapital> listaclientecapestorno = new ArrayList<ClienteCapital>();
				List<ClienteCapital> listaclientecapitalacento = new ArrayList<ClienteCapital>();

				List<ClienteEmprestimo> listaclienteemprestimo = new ArrayList<ClienteEmprestimo>();
				List<ClienteEmprestimo> listaclienteempestorno = new ArrayList<ClienteEmprestimo>();
				List<ClienteEmprestimo> listaclienteemprestimoacento = new ArrayList<ClienteEmprestimo>();

				// Lista de Logs
				List<String> listalog = new ArrayList<String>();

				inputStream = fileServidor.getInputStream();

				bufferedReader = new BufferedReader(new InputStreamReader(inputStream, inputCharsetutf8));

				while ((line = bufferedReader.readLine()) != null) {

					matcher = patternServidoresD8.matcher(line);

					Servidor_Pensionista servpens = new Servidor_Pensionista();

					if (matcher.find()) {
						long l = Long.parseLong(matcher.group(1).replaceAll("-", "").trim());
						if (l == 26413) {
							servpens.setInstituicao("IFTM");
						} else if (l == 26274 || l == 26281 || l == 26271) {
							servpens.setInstituicao("UFU");
						} else if (l == 26000) {
							servpens.setInstituicao("MTB");
						} else if (l == 40106) {
							servpens.setInstituicao("AGU");
						} else if (l == 15000) {
							servpens.setInstituicao("MEC");
						} else if (l == 20115) {
							servpens.setInstituicao("DPF");
						}

						servpens.setMatricula(Long.parseLong(matcher.group(2).replaceAll("-", "").trim()));
						servpens.setNome(matcher.group(5).trim());
						servpens.setCpf(matcher.group(6).replaceAll("-", "").trim());
						servpens.setCodrubrica(Long.parseLong(matcher.group(7).replaceAll("-", "").trim()));
						servpens.setValrubricaretorno(Long.parseLong(matcher.group(9).replaceAll("-", "").trim()));

						if (servpens.getCodrubrica() == 34251) {
							lista_servidor_pensionista_cap.add(servpens);
						} else if (servpens.getCodrubrica() == 34250) {
							lista_servidor_pensionista_emp.add(servpens);
						} else {
							System.out.println("CodRub inválido: " + servpens.getNome());
						}

					} else {
						System.out.println("Matcher Servidor Fail: " + line);
						listalog.add("Matcher Servidor Falhou: " + line);
					}
				}

				inputStream = filePensionista.getInputStream();

				bufferedReader = new BufferedReader(new InputStreamReader(inputStream, inputCharsetutf8));

				while ((line = bufferedReader.readLine()) != null) {

					matcher = patternPensionistasD8.matcher(line);

					Servidor_Pensionista servpens = new Servidor_Pensionista();

					if (matcher.find()) {
						long l = Long.parseLong(matcher.group(1).replaceAll("-", "").trim());
						if (l == 26413) {
							servpens.setInstituicao("IFTM");
						} else if (l == 26274 || l == 26281 || l == 26271) {
							servpens.setInstituicao("UFU");
						} else if (l == 26000) {
							servpens.setInstituicao("MTB");
						} else if (l == 40106) {
							servpens.setInstituicao("AGU");
						} else if (l == 15000) {
							servpens.setInstituicao("MEC");
						} else if (l == 20115) {
							servpens.setInstituicao("DPF");
						}

						servpens.setMatricula(Long.parseLong(matcher.group(3).replaceAll("-", "").trim()));
						servpens.setNome(matcher.group(6).trim());
						servpens.setCpf(matcher.group(7).replaceAll("-", "").trim());
						servpens.setCodrubrica(Long.parseLong(matcher.group(8).replaceAll("-", "").trim()));
						servpens.setValrubricaretorno(Long.parseLong(matcher.group(10).replaceAll("-", "").trim()));

						if (servpens.getCodrubrica() == 34251) {
							lista_servidor_pensionista_cap.add(servpens);
						} else if (servpens.getCodrubrica() == 34250) {
							lista_servidor_pensionista_emp.add(servpens);
						} else {
							System.out.println("CodRub inválido: " + servpens.getNome());
						}
					} else {
						System.out.println("Matcher Pensionista Fail: " + line);
						listalog.add("Matcher Pensionista Falhou: " + line);
					}
				}

				for (int i = 0; i < 6; i++) {

					if (i == 0) {
						inputStream = fileCapitalAgu.getInputStream();
					} else if (i == 1) {
						inputStream = fileCapitalDpf.getInputStream();
					} else if (i == 2) {
						inputStream = fileCapitalIftm.getInputStream();
					} else if (i == 3) {
						inputStream = fileCapitalMec.getInputStream();
					} else if (i == 4) {
						inputStream = fileCapitalMtb.getInputStream();
					} else {
						inputStream = fileCapitalUfu.getInputStream();
					}

					bufferedReader = new BufferedReader(new InputStreamReader(inputStream, inputCharsetcp1252));

					while ((line = bufferedReader.readLine()) != null) {

						matcher = patternCap.matcher(line);

						ClienteCapital clic = new ClienteCapital();

						if (matcher.find()) {
							clic.setInstituicao("Indefinido");
							clic.setMatricula(Long.parseLong(matcher.group(2).replaceAll("-", "").trim()));
							clic.setNome(matcher.group(3).trim());
							clic.setCpf(matcher.group(4).replaceAll("-", "").trim());
							clic.setValrubrica(Long.parseLong(matcher.group(5).replaceAll("-", "").trim()));

							listaclientecapital.add(clic);
						} else {
							System.out.println("Matcher Capital Fail: " + line);
							listaclientecapitalacento.add(clic);
							listalog.add("Matcher Capital Falhou: " + line);
						}
					}
				}

				System.out.println("-----------------------------------------------------------");

				for (int i = 0; i < 6; i++) {

					if (i == 0) {
						inputStream = fileEmprestimoAgu.getInputStream();
					} else if (i == 1) {
						inputStream = fileEmprestimoDpf.getInputStream();
					} else if (i == 2) {
						inputStream = fileEmprestimoIftm.getInputStream();
					} else if (i == 3) {
						inputStream = fileEmprestimoMec.getInputStream();
					} else if (i == 4) {
						inputStream = fileEmprestimoMtb.getInputStream();
					} else {
						inputStream = fileEmprestimoUfu.getInputStream();
					}

					bufferedReader = new BufferedReader(new InputStreamReader(inputStream, inputCharsetcp1252));

					while ((line = bufferedReader.readLine()) != null) {

						matcher = patternEmp.matcher(line);

						ClienteEmprestimo cliemp = new ClienteEmprestimo();

						if (matcher.find()) {
							cliemp.setInstituicao("Indefinido");
							cliemp.setIdentclientecoop(Long.parseLong(matcher.group(2).replaceAll("-", "").trim()));
							cliemp.setNome(matcher.group(3).trim());
							// Set CPF
							String scpf = matcher.group(4).replaceAll("-", "").trim();
							String p1 = scpf.substring(3, 6);
							String p2 = scpf.substring(6, 9);
							String p3 = scpf.substring(9, 12);
							String p4 = scpf.substring(12, 14);
							scpf = p1.concat(p2).concat(p3).concat(p4);
							// Fim Set CPF
							cliemp.setCpfcgc(scpf);
							cliemp.setValrubrica(Long.parseLong(matcher.group(9).replaceAll("-", "").trim()));

							listaclienteemprestimo.add(cliemp);
						} else {
							System.out.println("Matcher Emprestimo Fail: " + line);
							listaclienteemprestimoacento.add(cliemp);
							listalog.add("Matcher Emprestimo Falhou: " + line);
						}
					}
				}

				inputStream.close();

				ClienteCapital cc = new ClienteCapital();
				ClienteEmprestimo ce = new ClienteEmprestimo();

				// Batendo Valores
				Iterator<ClienteCapital> iteratorcc = listaclientecapital.iterator();
				while (iteratorcc.hasNext()) {
					cc = iteratorcc.next();
					Iterator<Servidor_Pensionista> iteratorsp = lista_servidor_pensionista_cap.iterator();
					while (iteratorsp.hasNext()) {
						Servidor_Pensionista sp = iteratorsp.next();
						if (sp.getCpf().equals(cc.getCpf())) {
							check = true;
							if (sp.getValrubricaretorno().equals(cc.getValrubrica())) {
								sp.setValrubricaenvio(sp.getValrubricaretorno());
								cc.setInstituicao((sp.getInstituicao()));
								lista_servidor_pensionista_completo.add(sp);
								totalclicompletocap += sp.getValrubricaretorno();
								iteratorsp.remove();
								break;
							} else {
								sp.setValrubricaenvio(cc.getValrubrica());
							}
						}
					}
					if (!check) {
						listaclientecapestorno.add(cc);
						iteratorcc.remove();
					} else {
						check = false;
					}
				}

				check = false;

				Iterator<ClienteEmprestimo> iteratorce = listaclienteemprestimo.iterator();
				while (iteratorce.hasNext()) {
					ce = iteratorce.next();
					Iterator<Servidor_Pensionista> iteratorsp = lista_servidor_pensionista_emp.iterator();
					while (iteratorsp.hasNext()) {
						Servidor_Pensionista sp = iteratorsp.next();
						if (sp.getCpf().equals(ce.getCpfcgc())) {
							check = true;
							if (sp.getValrubricaretorno().equals(ce.getValrubrica())) {
								ce.setInstituicao((sp.getInstituicao()));
								lista_servidor_pensionista_completo.add(sp);
								totalclicompletoemp += sp.getValrubricaretorno();
								iteratorsp.remove();
								break;
							}
						}
					}
					if (!check) {
						listaclienteempestorno.add(ce);
						iteratorce.remove();
					} else {
						check = false;
					}
				}
				// Fim Batendo Valores

				// Adicionando clientes presentes no retorno e ausentes no envio
				// à lista Completos
				Iterator<Servidor_Pensionista> iteratorsp = lista_servidor_pensionista_cap.iterator();
				while (iteratorsp.hasNext()) {
					Servidor_Pensionista sp = iteratorsp.next();
					Iterator<ClienteCapital> iteratorcc2 = listaclientecapital.iterator();
					while (iteratorcc2.hasNext()) {
						cc = iteratorcc2.next();
						if (sp.getCpf().equals(cc.getCpf())) {
							check = true;
							break;
						}
					}
					if (!check) {
						lista_servidor_pensionista_completo.add(sp);
						totalclicompletocap += sp.getValrubricaretorno();
						iteratorsp.remove();
					} else {
						check = false;
					}
				}

				System.out.println("-----------------------------------------------------------");

				Iterator<Servidor_Pensionista> iteratorspemp = lista_servidor_pensionista_emp.iterator();
				while (iteratorspemp.hasNext()) {
					Servidor_Pensionista sp = iteratorspemp.next();
					Iterator<ClienteEmprestimo> iteratorce2 = listaclienteemprestimo.iterator();
					while (iteratorce2.hasNext()) {
						ce = iteratorce2.next();
						if (sp.getCpf().equals(ce.getCpfcgc())) {
							check = true;
							break;
						}
					}
					if (!check) {
						lista_servidor_pensionista_completo.add(sp);
						totalclicompletoemp += sp.getValrubricaretorno();
						iteratorspemp.remove();
					} else {
						check = false;
					}
				}
				// Fim Adicionando clientes presentes no retorno e ausentes no
				// envio à lista Completos

				// Somando Valores Totais
				for (Servidor_Pensionista sp : lista_servidor_pensionista_cap) {
					totalcliparcial += sp.getValrubricaretorno();
				}
				for (Servidor_Pensionista sp : lista_servidor_pensionista_emp) {
					totalcliparcial += sp.getValrubricaretorno();
				}
				for (ClienteCapital ccap : listaclientecapestorno) {
					totalcliestornocap += ccap.getValrubrica();
				}
				for (ClienteEmprestimo cemp : listaclienteempestorno) {
					totalcliestornoemp += cemp.getValrubrica();
				}
				// Fim Somando Valores Totais

				System.out.println("Cliente Completo: " + lista_servidor_pensionista_completo.size());

				System.out.println("-----------------------------------------------------------");

				System.out.println("Cliente Cap Parcial: " + lista_servidor_pensionista_cap.size());

				System.out.println("-----------------------------------------------------------");

				System.out.println("Cliente Emp Parcial: " + lista_servidor_pensionista_emp.size());

				System.out.println("-----------------------------------------------------------");

				System.out.println("Cliente Cap Estorno: " + listaclientecapestorno.size());
				System.out.println("Cliente Emp Estorno: " + listaclienteempestorno.size());

				System.out.println("-----------------------------------------------------------");

				System.out.println("Cliente Cap Acento: " + listaclientecapitalacento.size());
				System.out.println("Cliente Emp Acento: " + listaclienteemprestimoacento.size());

				System.out.println("-----------------------------------------------------------");

				System.out.println("Total Cliente Completo Capital: " + totalclicompletocap);
				System.out.println("Total Cliente Completo Emprestimo: " + totalclicompletoemp);
				System.out.println("Total Cliente Parcial: " + totalcliparcial);
				System.out.println("Total Cliente Estorno Capital: " + totalcliestornocap);
				System.out.println("Total Cliente Estorno Emprestimo: " + totalcliestornoemp);

				// Escrever Excel

				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheetclientecomp = workbook.createSheet("ClienteCompleto");

				// CellStyle stylevalrub = workbook.createCellStyle();
				// DataFormat df = workbook.createDataFormat();
				// stylevalrub.setDataFormat(df.getFormat("##.##\\ _$"));

				int rownum = 0;
				int cellnum = 0;

				Row rowt = sheetclientecomp.createRow(rownum++);

				Cell titleinst = rowt.createCell(cellnum++);
				titleinst.setCellValue("Instituicao");

				Cell titlenome = rowt.createCell(cellnum++);
				titlenome.setCellValue("Nome");

				Cell titlecpf = rowt.createCell(cellnum++);
				titlecpf.setCellValue("CPF");

				Cell titlecodrub = rowt.createCell(cellnum++);
				titlecodrub.setCellValue("Cod Rubrica");

				Cell titlevalrubd8 = rowt.createCell(cellnum++);
				titlevalrubd8.setCellValue("Valor Rubrica D8");

				for (Servidor_Pensionista sp : lista_servidor_pensionista_completo) {

					Row rowp = sheetclientecomp.createRow(rownum++);
					cellnum = 0;

					Cell cellinst = rowp.createCell(cellnum++);
					cellinst.setCellValue(sp.getInstituicao());

					Cell cellnome = rowp.createCell(cellnum++);
					cellnome.setCellValue(sp.getNome());

					// Set CPF
					// String scpf = sp.getCpf();
					// String p1 = scpf.substring(0, 3).concat(".");
					// String p2 = scpf.substring(3, 6).concat(".");
					// String p3 = scpf.substring(6, 9).concat("-");
					// String p4 = scpf.substring(9, 11);
					// scpf = p1.concat(p2).concat(p3).concat(p4);

					Cell cellcpf = rowp.createCell(cellnum++);
					cellcpf.setCellValue(sp.getCpf());

					Cell cellcodrub = rowp.createCell(cellnum++);
					if (sp.getCodrubrica() == (34251)) {
						cellcodrub.setCellValue("Capital");
					} else if (sp.getCodrubrica() == (34250)) {
						cellcodrub.setCellValue("Emprestimo");
					} else {
						cellcodrub.setCellValue("Invalido! " + sp.getCodrubrica());
					}

					Cell cellvalrubd8 = rowp.createCell(cellnum++);
					cellvalrubd8.setCellValue(sp.getValrubricaretorno());
					// cellvalrub.setCellStyle(stylevalrub);
				}

				HSSFSheet sheetclientecapparcial = workbook.createSheet("ClienteCapParcial");

				// CellStyle stylevalrub = workbook.createCellStyle();
				// DataFormat df = workbook.createDataFormat();
				// stylevalrub.setDataFormat(df.getFormat("##.##\\ _$"));

				rownum = 0;
				cellnum = 0;

				rowt = sheetclientecapparcial.createRow(rownum++);

				titleinst = rowt.createCell(cellnum++);
				titleinst.setCellValue("Instituicao");

				titlenome = rowt.createCell(cellnum++);
				titlenome.setCellValue("Nome");

				titlecpf = rowt.createCell(cellnum++);
				titlecpf.setCellValue("CPF");

				titlecodrub = rowt.createCell(cellnum++);
				titlecodrub.setCellValue("Cod Rubrica");

				titlevalrubd8 = rowt.createCell(cellnum++);
				titlevalrubd8.setCellValue("Valor Rubrica D8");

				Cell titlevalrubenvio = rowt.createCell(cellnum++);
				titlevalrubenvio.setCellValue("Valor Rubrica Envio");

				for (Servidor_Pensionista sp : lista_servidor_pensionista_cap) {

					Row rowp = sheetclientecapparcial.createRow(rownum++);
					cellnum = 0;

					Cell cellinst = rowp.createCell(cellnum++);
					cellinst.setCellValue(sp.getInstituicao());

					Cell cellnome = rowp.createCell(cellnum++);
					cellnome.setCellValue(sp.getNome());

					// Set CPF
					// String scpf = sp.getCpf();
					// String p1 = scpf.substring(0, 3).concat(".");
					// String p2 = scpf.substring(3, 6).concat(".");
					// String p3 = scpf.substring(6, 9).concat("-");
					// String p4 = scpf.substring(9, 11);
					// scpf = p1.concat(p2).concat(p3).concat(p4);

					Cell cellcpf = rowp.createCell(cellnum++);
					cellcpf.setCellValue(sp.getCpf());

					Cell cellcodrub = rowp.createCell(cellnum++);
					if (sp.getCodrubrica() == (34251)) {
						cellcodrub.setCellValue("Capital");
					} else if (sp.getCodrubrica() == (34250)) {
						cellcodrub.setCellValue("Emprestimo");
					} else {
						cellcodrub.setCellValue("Invalido! " + sp.getCodrubrica());
					}

					Cell cellvalrubd8 = rowp.createCell(cellnum++);
					cellvalrubd8.setCellValue(sp.getValrubricaretorno());
					// cellvalrub.setCellStyle(stylevalrub);

					Cell cellvalrubenvio = rowp.createCell(cellnum++);
					cellvalrubenvio.setCellValue(sp.getValrubricaenvio());
				}

				HSSFSheet sheetclienteempparcial = workbook.createSheet("ClienteEmpParcial");

				// CellStyle stylevalrub = workbook.createCellStyle();
				// DataFormat df = workbook.createDataFormat();
				// stylevalrub.setDataFormat(df.getFormat("##.##\\ _$"));

				rownum = 0;
				cellnum = 0;

				rowt = sheetclienteempparcial.createRow(rownum++);

				titleinst = rowt.createCell(cellnum++);
				titleinst.setCellValue("Instituicao");

				titlenome = rowt.createCell(cellnum++);
				titlenome.setCellValue("Nome");

				titlecpf = rowt.createCell(cellnum++);
				titlecpf.setCellValue("CPF");

				titlecodrub = rowt.createCell(cellnum++);
				titlecodrub.setCellValue("Cod Rubrica");

				titlevalrubd8 = rowt.createCell(cellnum++);
				titlevalrubd8.setCellValue("Valor Rubrica D8");

				for (Servidor_Pensionista sp : lista_servidor_pensionista_emp) {

					Row rowp = sheetclienteempparcial.createRow(rownum++);
					cellnum = 0;

					Cell cellinst = rowp.createCell(cellnum++);
					cellinst.setCellValue(sp.getInstituicao());

					Cell cellnome = rowp.createCell(cellnum++);
					cellnome.setCellValue(sp.getNome());

					// Set CPF
					// String scpf = sp.getCpf();
					// String p1 = scpf.substring(0, 3).concat(".");
					// String p2 = scpf.substring(3, 6).concat(".");
					// String p3 = scpf.substring(6, 9).concat("-");
					// String p4 = scpf.substring(9, 11);
					// scpf = p1.concat(p2).concat(p3).concat(p4);

					Cell cellcpf = rowp.createCell(cellnum++);
					cellcpf.setCellValue(sp.getCpf());

					Cell cellcodrub = rowp.createCell(cellnum++);
					if (sp.getCodrubrica() == (34251)) {
						cellcodrub.setCellValue("Capital");
					} else if (sp.getCodrubrica() == (34250)) {
						cellcodrub.setCellValue("Emprestimo");
					} else {
						cellcodrub.setCellValue("Invalido! " + sp.getCodrubrica());
					}

					Cell cellvalrubd8 = rowp.createCell(cellnum++);
					cellvalrubd8.setCellValue(sp.getValrubricaretorno());
					// cellvalrub.setCellStyle(stylevalrub);
				}

				HSSFSheet sheetclientecapestorno = workbook.createSheet("ClienteCapEstorno");

				// CellStyle stylevalrub = workbook.createCellStyle();
				// DataFormat df = workbook.createDataFormat();
				// stylevalrub.setDataFormat(df.getFormat("##.##\\ _$"));

				rownum = 0;
				cellnum = 0;

				rowt = sheetclientecapestorno.createRow(rownum++);

				titleinst = rowt.createCell(cellnum++);
				titleinst.setCellValue("Instituicao");

				titlenome = rowt.createCell(cellnum++);
				titlenome.setCellValue("Nome");

				titlecpf = rowt.createCell(cellnum++);
				titlecpf.setCellValue("CPF");

				titlecodrub = rowt.createCell(cellnum++);
				titlecodrub.setCellValue("Cod Rubrica");

				Cell titlevalrub = rowt.createCell(cellnum++);
				titlevalrub.setCellValue("Valor Rubrica D8");

				for (ClienteCapital cli : listaclientecapestorno) {

					Row rowp = sheetclientecapestorno.createRow(rownum++);
					cellnum = 0;

					Cell cellinst = rowp.createCell(cellnum++);
					cellinst.setCellValue(cli.getInstituicao());

					Cell cellnome = rowp.createCell(cellnum++);
					cellnome.setCellValue(cli.getNome());

					// Set CPF
					// String scpf = (cli.getCpf());
					// String p1 = scpf.substring(0, 3).concat(".");
					// String p2 = scpf.substring(3, 6).concat(".");
					// String p3 = scpf.substring(6, 9).concat("-");
					// String p4 = scpf.substring(9, 11);
					// scpf = p1.concat(p2).concat(p3).concat(p4);

					Cell cellcpf = rowp.createCell(cellnum++);
					cellcpf.setCellValue(cli.getCpf());

					Cell cellcodrub = rowp.createCell(cellnum++);
					cellcodrub.setCellValue("Capital");

					Cell cellvalrub = rowp.createCell(cellnum++);
					cellvalrub.setCellValue(cli.getValrubrica());
					// cellvalrub.setCellStyle(stylevalrub);
				}

				HSSFSheet sheetclienteempestorno = workbook.createSheet("ClienteEmpEstorno");

				// CellStyle stylevalrub = workbook.createCellStyle();
				// DataFormat df = workbook.createDataFormat();
				// stylevalrub.setDataFormat(df.getFormat("##.##\\ _$"));

				rownum = 0;
				cellnum = 0;

				rowt = sheetclienteempestorno.createRow(rownum++);

				titleinst = rowt.createCell(cellnum++);
				titleinst.setCellValue("Instituicao");

				titlenome = rowt.createCell(cellnum++);
				titlenome.setCellValue("Nome");

				titlecpf = rowt.createCell(cellnum++);
				titlecpf.setCellValue("CPF");

				titlecodrub = rowt.createCell(cellnum++);
				titlecodrub.setCellValue("Cod Rubrica");

				titlevalrub = rowt.createCell(cellnum++);
				titlevalrub.setCellValue("Valor Rubrica D8");

				for (ClienteEmprestimo cli : listaclienteempestorno) {

					Row rowp = sheetclienteempestorno.createRow(rownum++);
					cellnum = 0;

					Cell cellinst = rowp.createCell(cellnum++);
					cellinst.setCellValue(cli.getInstituicao());

					Cell cellnome = rowp.createCell(cellnum++);
					cellnome.setCellValue(cli.getNome());

					// Set CPF
					// String scpf = (cli.getCpf());
					// String p1 = scpf.substring(0, 3).concat(".");
					// String p2 = scpf.substring(3, 6).concat(".");
					// String p3 = scpf.substring(6, 9).concat("-");
					// String p4 = scpf.substring(9, 11);
					// scpf = p1.concat(p2).concat(p3).concat(p4);

					Cell cellcpf = rowp.createCell(cellnum++);
					cellcpf.setCellValue(cli.getCpfcgc());

					Cell cellcodrub = rowp.createCell(cellnum++);
					cellcodrub.setCellValue("Emprestimo");

					Cell cellvalrub = rowp.createCell(cellnum++);
					cellvalrub.setCellValue(cli.getValrubrica());
					// cellvalrub.setCellStyle(stylevalrub);
				}

				try

				{
					FileOutputStream out = new FileOutputStream(new File(UploadFederaisController.fileName));
					workbook.write(out);
					out.close();
					System.out.println("Arquivo Excel criado com sucesso!");
					workbook.close();

					for (String s : listalog) {
						gravarArq.printf(s + "\n");
					}
					gravarArq.printf("\n" + "Cliente Completo: " + lista_servidor_pensionista_completo.size());
					gravarArq.printf("\n" + "Cliente Cap Parcial: " + lista_servidor_pensionista_cap.size());
					gravarArq.printf("\n" + "Cliente Emp Parcial: " + lista_servidor_pensionista_emp.size());
					gravarArq.printf("\n" + "Cliente Cap Estorno: " + listaclientecapestorno.size());
					gravarArq.printf("\n" + "Cliente Emp Estorno: " + listaclienteempestorno.size());

					gravarArq.printf("\n\n" + "Valor Total Cliente Completo Capital: " + totalclicompletocap);
					gravarArq.printf("\n" + "Valor Total Cliente Completo Emprestimo: " + totalclicompletoemp);
					gravarArq.printf("\n" + "Valor Total Cliente Parcial: " + totalcliparcial);
					gravarArq.printf("\n" + "Valor Total Estorno Capital: " + totalcliestornocap);
					gravarArq.printf("\n" + "Valor Total Estorno Emprestimo: " + totalcliestornoemp);

					arq.close();
					gravarArq.close();

				} catch (FileNotFoundException e) {
					e.printStackTrace();
					System.out.println("Arquivo não encontrado!");
					return "Arquivo não encontrado!";
				} catch (

				IOException e)

				{
					e.printStackTrace();
					System.out.println("Erro na edição do arquivo!");
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "Arquivo Não Encontrado!";
			}
		} else {
			return "Pensionistas ou Servidores Vazios!";
		}
		return "END";
	}
}