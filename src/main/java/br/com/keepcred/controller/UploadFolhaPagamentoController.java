package br.com.keepcred.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import br.com.keepcred.entities.Cliente;
import br.com.keepcred.file.WriteFileExcel;
import br.com.keepcred.file.WriteFileTxt;
import br.com.keepcred.repositories.ClienteRepository;

@Controller
public class UploadFolhaPagamentoController {

	@Autowired
	private ClienteRepository clienteRepository;

	Pattern patternFaepuSalarios = Pattern
			.compile("([0-9|]{5})([0-9|]{6})([A-Za-zÀ-Ú|\\s]{41})([0-9|]{6})([0-9|]{2})([0-9|X]{16})([0-9,]{9})");

	Pattern patternUfuSalarios = Pattern.compile("([0-9/]{8})([A-Za-zÀ-Ú\\s]{41})([0-9\\s]{5})([0-9\\s.,]{12})");

	@RequestMapping(value = "/uploadfolhapagamento", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String provideUploadInfo() {
		return "You can upload a file by posting to this same URL.";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file,
			@RequestParam("fileType") String fileType, @RequestParam("dateTime") String dateTime) throws IOException {

		Matcher matcher = null;

		if (!file.isEmpty()) {
			try {
				// byte[] bytes = file.getBytes();
				// FileInputStream arquivo = null;
				HSSFWorkbook workbook = null;
				HSSFSheet sheetCustomer = null;

				// BufferedOutputStream stream = null;
				InputStream inputStream;
				Charset inputCharsetutf8 = Charset.forName("utf-8");
				BufferedReader data = null;

				dateTime = dateTime.replaceAll("-", "");

				if (fileType.equals("FILE_TYPE_FAEPU_FARMACIA") || fileType.equals("FILE_TYPE_UFU_FARMACIA")) {
					inputStream = file.getInputStream();
					workbook = new HSSFWorkbook(inputStream);
					sheetCustomer = workbook.getSheetAt(0);
				} else {
					// stream = new BufferedOutputStream(new
					// FileOutputStream(new File(file.getName())));
					// stream.write(bytes);

					inputStream = file.getInputStream();
					data = new BufferedReader(new InputStreamReader(inputStream, inputCharsetutf8));
				}

				// For Debug
				System.out.println();
				System.out.println(dateTime);
				System.out.println("========");
				// System.out.println(fileType);

				String flag;
				List<Cliente> listaclientebanco = new ArrayList<Cliente>();
				List<Cliente> listacliente = new ArrayList<Cliente>();
				listaclientebanco = clienteRepository.getAllData();

				// For Debug
				// ---------------------------------------------------------------------------------
				// int cpfscapturados = 0;
				// ---

				// FAEPU
				if (fileType.equals("FILE_TYPE_FAEPU_SALARIOS")) {
					String line;
					flag = "faepu";

					while ((line = data.readLine()) != null) {
						// System.out.println("========");
						// System.out.println(line);

						matcher = patternFaepuSalarios.matcher(line);

						Cliente cliente = new Cliente();

						// Se encontrar algo
						if (matcher.find()) {

							// IDCLIENTE = CODCONVENIO
							cliente.setIDCLIENTE(Long.parseLong(matcher.group(2).replaceAll("([|])", " ").trim()));
							String cc = matcher.group(6).replaceAll("([X])", "0");
							cliente.setNUMCONTACORRENTE(Long.parseLong(cc.replaceAll("([|])", " ".trim()).trim()));
							String total = matcher.group(7).replaceAll(",", "");
							cliente.setStotal(total.replaceAll("([|])", " ").trim());

							for (Cliente c : listaclientebanco) {
								if (cliente.getNUMCONTACORRENTE().longValue() == c.getNUMCONTACORRENTE().longValue()) {
									cliente.setDESCNOMECLIENTE(c.getDESCNOMECLIENTE());
									cliente.setNUMCPFCNPJ(c.getNUMCPFCNPJ());
									break;
								}
							}
							if (!cliente.getStotal().equals("00000000")) {
								listacliente.add(cliente);
							}
						} else {
							System.out.println("Matcher Faepu Salarios Fail: " + line);
						}
					}
					// For Debug
					// ------------------------------------------------------------------------------------------
					// for (Cliente c : listacliente) {
					// System.out.println(c);
					// }
					// -------------------------------------------------------------------------------------------

					WriteFileTxt classe = new WriteFileTxt();
					classe.write(listacliente, flag, dateTime);

					// stream.close();

					// return "Importado Com Sucesso!";

					// UFU
				} else if (fileType.equals("FILE_TYPE_UFU_SALARIOS")) {
					String line;
					flag = "ufu";

					while ((line = data.readLine()) != null) {
						// System.out.println("========");
						// System.out.println(line);

						matcher = patternUfuSalarios.matcher(line);

						Cliente cliente = new Cliente();

						if (matcher.find()) {

							cliente.setDtpagto((matcher.group(1).trim()));
							cliente.setNUMCONTACORRENTE(Long.parseLong(matcher.group(3).trim()));
							String stotal = matcher.group(4).replaceAll(",", "").trim();
							cliente.setStotal(stotal.replaceAll("([.])", ""));
							// System.out.println(cliente.getStotal());

							for (Cliente c : listaclientebanco) {
								if (cliente.getNUMCONTACORRENTE().longValue() == c.getNUMCONTACORRENTE().longValue()) {
									cliente.setDESCNOMECLIENTE(c.getDESCNOMECLIENTE());
									cliente.setNUMCPFCNPJ(c.getNUMCPFCNPJ());
									break;
								}
							}
							listacliente.add(cliente);
						} else {
							System.out.println("Matcher UFU Salarios Fail: " + line);
						}
					}
					// For Debug
					// ------------------------------------------------------------------------------------------
					// for (Cliente c : listacliente) {
					// System.out.println(c);
					// }
					// -------------------------------------------------------------------------------------------

					WriteFileTxt classe = new WriteFileTxt();
					classe.write(listacliente, flag, dateTime);

					// stream.close();

					// return "Importado Com Sucesso!";

				} else if (fileType.equals("FILE_TYPE_FAEPU_FARMACIA") || fileType.equals("FILE_TYPE_UFU_FARMACIA")) {

					if (fileType.equals("FILE_TYPE_FAEPU_FARMACIA")) {
						flag = "faepu";
					} else {
						flag = "ufu";
					}

					Iterator<Row> rowIterator = sheetCustomer.iterator();
					while (rowIterator.hasNext()) {
						Row row = rowIterator.next();
						if (row.getRowNum() == 0 || row.getRowNum() == 1 || row.getRowNum() == 2) {
							continue;
						}

						Iterator<Cell> cellIterator = row.cellIterator();
						Cliente cliente = new Cliente();
						listacliente.add(cliente);

						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();

							switch (cell.getColumnIndex()) {

							case 0:
								cliente.setNUMCONTACORRENTE((long) cell.getNumericCellValue());
								break;

							case 1:
								cliente.setDESCNOMECLIENTE(cell.getStringCellValue());
								break;

							case 2:
								cliente.setTotal((cell.getNumericCellValue()));
								for (Cliente c : listaclientebanco) {
									if (cliente.getNUMCONTACORRENTE().longValue() == c.getNUMCONTACORRENTE()
											.longValue()) {
										cliente.setDESCNOMECLIENTE(c.getDESCNOMECLIENTE());
										cliente.setNUMCPFCNPJ(c.getNUMCPFCNPJ());
										// For Debug
										// ---------------------------------------------------------------------------------
										// cpfscapturados++;
										// -------------------------------------------------------------------------------------------
										break;
									}
								}
								break;
							}

						}
					}

					listacliente.remove(listacliente.size() - 1);
					// arquivo.close();

					// For Debug
					// ---------------------------------------------------------------------------------
					if (listacliente.size() == 0) {

						System.out.println("Nenhum cliente encontrado!");

					} else {

						// int numclientes = 0;
						// for (Cliente c : listacliente) {
						// System.out.println(c);
						// numclientes++;
						// }
						// System.out.println("Clientes: " + (numclientes) + "/
						// cpfscapturados: " + cpfscapturados);
						//
						// ---------------------------------------------------------------------------------------------

						WriteFileExcel classe = new WriteFileExcel();
						classe.write(listacliente, flag, dateTime);

						// return "Importado Com Sucesso!";
					}
				}

				// stream.close();
				// arquivo.close();
				// workbook.close();

			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return "Arquivo não encontrado!";
			}
		} else

		{
			return "Arquivo Vazio!";
		}
		return "END";
	}
}
