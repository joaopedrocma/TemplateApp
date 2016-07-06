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

import br.com.keepcred.entities.Fundacoes;

@Controller
public class UploadFundacoesController {

	Pattern patternFund = Pattern.compile("([0-9]{8})([A-Za-zÀ-Ú\\s]{50})([0-9]{5})([0-9,]{8})");

	Pattern patternFaepuFund = Pattern
			.compile("([0-9|]{3})([0-9A-Za-z|\\s]{9})([0-9|]{6})([A-Za-zÀ-Ú|\\s]{51})([0-9|]{7})([0-9.\\s]{11})");

	@RequestMapping(value = "/uploadfundacoes", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String provideUploadInfo() {
		return "You can upload a file by posting to this same URL.";
	}

	@RequestMapping(value = "/uploadfundacoes", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody String uploadFundacoes(@RequestParam("filecap") MultipartFile fileCap,
			@RequestParam("fileemp") MultipartFile fileEmp, @RequestParam("fileType") String fileType)
					throws IOException {

		String nomeinstarqxls = "";
		String nomeinstarqtxt = "";
		Date today = new Date();
		SimpleDateFormat sdfout = new SimpleDateFormat("dd-MM-yyyy");
		
		String dateout = sdfout.format(today);

		Long totalclicap = 0l;
		Long totalcliemp = 0l;

		if (fileType.equals("FILE_TYPE_FAU")) {
			nomeinstarqxls = "FAU_";
			nomeinstarqtxt = "FAU_Log_";
		} else if (fileType.equals("FILE_TYPE_FUNDAP")) {
			nomeinstarqxls = "FUNDAP_";
			nomeinstarqtxt = "FUNDAP_Log_";
		} else if (fileType.equals("FILE_TYPE_RTU")) {
			nomeinstarqxls = "RTU_";
			nomeinstarqtxt = "RTU_Log_";
		} else if (fileType.equals("FILE_TYPE_SINTET")) {
			nomeinstarqxls = "SINTET_";
			nomeinstarqtxt = "SINTET_Log_";
		} else if (fileType.equals("FILE_TYPE_FAEPU")) {
			nomeinstarqxls = "FAEPU_";
			nomeinstarqtxt = "FAEPU_Log_";
		}

		String fileName = "/root/dados/ftp/fundacoes/Fundacoes_" + nomeinstarqxls + dateout + ".xls";

		Matcher matcher = null;

		if (!fileCap.isEmpty() && !fileEmp.isEmpty()) {
			try {

				FileWriter arq = new FileWriter("/root/dados/ftp/fundacoes/Fundacoes_" + nomeinstarqtxt + dateout + ".txt");
				PrintWriter gravarArq = new PrintWriter(arq);

				InputStream inputStream;
				Charset inputCharsetcp1252 = Charset.forName("Cp1252");
				Charset inputCharsetutf8 = Charset.forName("utf-8");
				BufferedReader bufferedReader = null;

				String line;

				List<Fundacoes> listacapfundacoes = new ArrayList<Fundacoes>();
				List<Fundacoes> listaempfundacoes = new ArrayList<Fundacoes>();

				// Lista de Logs
				List<String> listalog = new ArrayList<String>();

				if (fileType.equals("FILE_TYPE_FAU") || fileType.equals("FILE_TYPE_FUNDAP")
						|| fileType.equals("FILE_TYPE_RTU") || fileType.equals("FILE_TYPE_SINTET")) {

					// Capital
					inputStream = fileCap.getInputStream();

					if (fileType.equals("FILE_TYPE_RTU")) {
						bufferedReader = new BufferedReader(new InputStreamReader(inputStream, inputCharsetcp1252));
					} else {
						bufferedReader = new BufferedReader(new InputStreamReader(inputStream, inputCharsetutf8));
					}

					while ((line = bufferedReader.readLine()) != null) {

						matcher = patternFund.matcher(line);

						Fundacoes clientefund = new Fundacoes();

						if (matcher.find()) {
							clientefund.setCodcliente(Long.parseLong(matcher.group(1).trim()));
							clientefund.setNome(matcher.group(2).trim());
							clientefund.setMatricula(Long.parseLong(matcher.group(3).trim()));
							clientefund.setValrubrica(Long.parseLong(matcher.group(4).replaceAll(",", "").trim()));
							totalclicap += clientefund.getValrubrica();
							listacapfundacoes.add(clientefund);
						} else {
							System.out.println("Matcher patternFund Cap Fail: " + line);
							listalog.add("Matcher Capital Falhou: " + line);
						}
					}

					// Emprestimo
					inputStream = fileEmp.getInputStream();

					if (fileType.equals("FILE_TYPE_RTU")) {
						bufferedReader = new BufferedReader(new InputStreamReader(inputStream, inputCharsetcp1252));
					} else {
						bufferedReader = new BufferedReader(new InputStreamReader(inputStream, inputCharsetutf8));
					}

					while ((line = bufferedReader.readLine()) != null) {

						matcher = patternFund.matcher(line);

						Fundacoes clientefund = new Fundacoes();

						if (matcher.find()) {
							clientefund.setCodcliente(Long.parseLong(matcher.group(1).trim()));
							clientefund.setNome(matcher.group(2).trim());
							clientefund.setMatricula(Long.parseLong(matcher.group(3).trim()));
							clientefund.setValrubrica(Long.parseLong(matcher.group(4).replaceAll(",", "").trim()));
							totalcliemp += clientefund.getValrubrica();
							listaempfundacoes.add(clientefund);
						} else {
							System.out.println("Matcher patternFund Emp Fail: " + line);
							listalog.add("Matcher Emprestimo Falhou: " + line);
						}
					}

				} else if (fileType.equals("FILE_TYPE_FAEPU")) {

					// Capital
					inputStream = fileCap.getInputStream();

					bufferedReader = new BufferedReader(new InputStreamReader(inputStream, inputCharsetcp1252));

					while ((line = bufferedReader.readLine()) != null) {

						matcher = patternFaepuFund.matcher(line);

						Fundacoes clientefund = new Fundacoes();

						if (matcher.find()) {
							clientefund.setCodcliente(Long.parseLong(matcher.group(3).replaceAll("([|])", "").trim()));
							clientefund.setNome(matcher.group(4).replaceAll("([|])", "").trim());
							clientefund.setMatricula(
									Long.parseLong(matcher.group(2).substring(3).replaceAll("([|])", "").trim()));
							String valrub = matcher.group(6).replaceAll("([|])", "").trim();
							clientefund.setValrubrica(Long.parseLong(valrub.replaceAll("([.])", "").trim()));
							totalclicap += clientefund.getValrubrica();
							listacapfundacoes.add(clientefund);
						} else {
							System.out.println("Matcher patternFaepuFund Cap Fail: " + line);
							listalog.add("Matcher Capital Falhou: " + line);
						}
					}

					// Emprestimo
					inputStream = fileEmp.getInputStream();

					bufferedReader = new BufferedReader(new InputStreamReader(inputStream, inputCharsetcp1252));

					while ((line = bufferedReader.readLine()) != null) {

						matcher = patternFaepuFund.matcher(line);

						Fundacoes clientefund = new Fundacoes();

						if (matcher.find()) {
							clientefund.setCodcliente(Long.parseLong(matcher.group(3).replaceAll("([|])", "").trim()));
							clientefund.setNome(matcher.group(4).replaceAll("([|])", "").trim());
							clientefund.setMatricula(
									Long.parseLong(matcher.group(2).substring(3).replaceAll("([|])", "").trim()));
							String valrub = matcher.group(6).replaceAll("([|])", "").trim();
							clientefund.setValrubrica(Long.parseLong(valrub.replaceAll("([.])", "").trim()));
							totalcliemp += clientefund.getValrubrica();
							listaempfundacoes.add(clientefund);
						} else {
							System.out.println("Matcher patternFaepuFund Emp Fail: " + line);
							listalog.add("Matcher Emprestimo Falhou: " + line);
						}
					}

				}

				System.out.println("-----------------------------------------------------------");

				System.out.println("Lista Cap Fundacoes: " + listacapfundacoes.size());
				System.out.println("Lista Emp Fundacoes: " + listaempfundacoes.size());

				System.out.println("-----------------------------------------------------------");

				// Escrever Excel

				// Capital/Emprestimo
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheetfundacoescap = workbook.createSheet("Capital_Emprestimo");

				int rownum = 0;
				int cellnum = 0;

				Row rowt = sheetfundacoescap.createRow(rownum++);

				Cell titlecodcliente = rowt.createCell(cellnum++);
				titlecodcliente.setCellValue("CodCliente");

				Cell titlenome = rowt.createCell(cellnum++);
				titlenome.setCellValue("Nome");

				Cell titlematricula = rowt.createCell(cellnum++);
				titlematricula.setCellValue("Matricula");

				Cell titlevalrubcap = rowt.createCell(cellnum++);
				titlevalrubcap.setCellValue("Capital");

				Cell titlevalrubemp = rowt.createCell(cellnum++);
				titlevalrubemp.setCellValue("Emprestimo");

				boolean foundMatch = false;
				Fundacoes cap = new Fundacoes();
				Fundacoes emp = new Fundacoes();

				Iterator<Fundacoes> iteratorcap = listacapfundacoes.iterator();
				while (iteratorcap.hasNext()) {
					foundMatch = false;

					cap = iteratorcap.next();

					Row rowp = sheetfundacoescap.createRow(rownum++);
					cellnum = 0;

					Cell cellcodcliente = rowp.createCell(cellnum++);
					cellcodcliente.setCellValue(cap.getCodcliente());

					Cell cellnome = rowp.createCell(cellnum++);
					cellnome.setCellValue(cap.getNome());

					Cell cellmatricula = rowp.createCell(cellnum++);
					cellmatricula.setCellValue(cap.getMatricula());

					Cell cellvalrubcap = rowp.createCell(cellnum++);
					cellvalrubcap.setCellValue(cap.getValrubrica());

					Iterator<Fundacoes> iteratoremp = listaempfundacoes.iterator();
					while (iteratoremp.hasNext()) {
						emp = iteratoremp.next();
						if (cap.getCodcliente().equals(emp.getCodcliente())) {
							foundMatch = true;
							Cell cellvalrubemp = rowp.createCell(cellnum++);
							cellvalrubemp.setCellValue(emp.getValrubrica());
							iteratoremp.remove();
							break;
						}
					}
					if (!foundMatch) {
						Cell cellvalrubemp = rowp.createCell(cellnum++);
						cellvalrubemp.setCellValue(0);
					}
				}

				//Adicionar Clientes sem Capital
				for (Fundacoes f : listaempfundacoes) {
					Row rowp = sheetfundacoescap.createRow(rownum++);
					cellnum = 0;

					Cell cellcodcliente = rowp.createCell(cellnum++);
					cellcodcliente.setCellValue(f.getCodcliente());

					Cell cellnome = rowp.createCell(cellnum++);
					cellnome.setCellValue(f.getNome());

					Cell cellmatricula = rowp.createCell(cellnum++);
					cellmatricula.setCellValue(f.getMatricula());

					Cell cellvalrubcap = rowp.createCell(cellnum++);
					cellvalrubcap.setCellValue(0);

					Cell cellvalrubemp = rowp.createCell(cellnum++);
					cellvalrubemp.setCellValue(f.getValrubrica());
				}

				try

				{
					FileOutputStream out = new FileOutputStream(new File(fileName));
					workbook.write(out);
					out.close();
					System.out.println("Arquivo Excel criado com sucesso!");
					workbook.close();

					for (String s : listalog) {
						gravarArq.printf(s + "\n");
					}
					int listafundacoestotal = listacapfundacoes.size() + listaempfundacoes.size();
					gravarArq.printf("\n" + "Clientes: " + listafundacoestotal);

					gravarArq.printf("\n\n" + "Valor Total Capital: " + totalclicap);
					gravarArq.printf("\n" + "Valor Total Emprestimo: " + totalcliemp);

					arq.close();
					gravarArq.close();

				} catch (FileNotFoundException e) {
					e.printStackTrace();
					System.out.println("Arquivo não encontrado!");
					return "Arquivo não encontrado!";
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Erro na edição do arquivo!");
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "Arquivo Não Encontrado!";
			}
		} else {
			return "Capital ou Emprestimo Vazio!";
		}
		return "END";
	}

}
