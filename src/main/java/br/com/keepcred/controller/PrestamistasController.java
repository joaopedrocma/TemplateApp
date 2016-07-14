package br.com.keepcred.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.keepcred.entities.Prestamistas;
import br.com.keepcred.entities.Recusados;
import br.com.keepcred.repositories.PrestamistasRepository;
import br.com.keepcred.repositories.RecusadosRepository;

@Controller
public class PrestamistasController {

	@Autowired
	private PrestamistasRepository prestamistaRepository;

	@Autowired
	private RecusadosRepository recusadosRepository;

	private static String fileName;

	@RequestMapping(value = "/prestamistas", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String showPrestamistas() {

		BigDecimal highlimit = new BigDecimal("400000");
		BigDecimal mediumlimit = new BigDecimal("20000");
		BigDecimal lowlimit = new BigDecimal("2000");

		Date today = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.getTime();

		SimpleDateFormat sdfnasc = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfout = new SimpleDateFormat("dd-MM-yyyy");

		String dateout = sdfout.format(today);

		fileName = "/root/dados/ftp/prestamistas/Prestamistas_" + dateout + ".xls";

		List<Prestamistas> prestamistas = new ArrayList<Prestamistas>();
		prestamistas = prestamistaRepository.findAll();

		List<Recusados> recusados = new ArrayList<Recusados>();
		recusados = recusadosRepository.findAll();

		// Remove Recusados
		for (Recusados r : recusados) {
			for (Prestamistas p : prestamistas) {
				if (p.getNumcpfcnpj().equals(r.getNUMCPFCNPJ())) {
					prestamistas.remove(p);
					break;
				}
			}
		}

		// Corrigir titulares de conta conjunta
		int breakpoint = 0;
		for (Prestamistas p : prestamistas) {
			if (breakpoint >= 2) {
				break;
			}
			if (p.getNumcpfcnpj().equals("41592085172")) {
				GregorianCalendar gc2 = new GregorianCalendar();
				Date datatc = new Date();
				p.setDescnomecliente("ELSIENI COELHO DA SILVA E OU");
				gc2.set(1967, 05, 14);
				datatc = gc2.getTime();
				p.setDatanascimento(datatc);
				p.setNumcpfcnpj("48510785600");
				breakpoint++;
			}
			if (p.getNumcpfcnpj().equals("48514675672")) {
				GregorianCalendar gc2 = new GregorianCalendar();
				Date datatc = new Date();
				p.setDescnomecliente("OSVALDO SOUZA PINA DE CAMPOS E OU");
				gc2.set(1966, 10, 18);
				datatc = gc2.getTime();
				p.setDatanascimento(datatc);
				p.setNumcpfcnpj("64066240682");
				breakpoint++;
			}
		}

		// System.out.println(totalpsc);

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheetPrestamistas = workbook.createSheet("Prestamistas");

		CellStyle stylecs = workbook.createCellStyle();
		DataFormat df = workbook.createDataFormat();
		stylecs.setDataFormat(df.getFormat("#,##0.00\\ _$"));

		int rownum = 0;
		int cellnum = 0;

		// Titulos
		// ----------------------------------------------------------------------------------------------------
		Row rowt = sheetPrestamistas.createRow(rownum++);

		Cell titlenome = rowt.createCell(cellnum++);
		titlenome.setCellValue("Nome");

		Cell titlenascimento = rowt.createCell(cellnum++);
		titlenascimento.setCellValue("Nascimento");

		Cell titleinivig = rowt.createCell(cellnum++);
		titleinivig.setCellValue("Inicio Vigencia");

		Cell titlefimvig = rowt.createCell(cellnum++);
		titlefimvig.setCellValue("Fim Vigencia");

		Cell titleop = rowt.createCell(cellnum++);
		titleop.setCellValue("Operação");

		Cell titlecpf = rowt.createCell(cellnum++);

		titlecpf.setCellValue("CPF");

		Cell titlecp = rowt.createCell(cellnum++);
		titlecp.setCellValue("Capital Segurado");

		// Prestamistas
		// -------------------------------------------------------------------------------------------------------
		for (Prestamistas p : prestamistas) {

			BigDecimal numcontasbd = new BigDecimal(p.getNumcontas());
			BigDecimal valorlimitebd = new BigDecimal(p.getValorlimite());
			long d1 = p.getDatanascimento().getTime();
			long d2 = gc.getTime().getTime();
			long age = Math.abs((d1 - d2) / (1000 * 60 * 60 * 24)) / 365;

			if (p.getCodtiposituacaotitulo() == 2 && p.getValorlimite() == 0 || age >= 81) {
				continue;
			}

			// System.out.println(p.getDESCNOMECLIENTE() + " - " + age);

			Row rowp = sheetPrestamistas.createRow(rownum++);
			cellnum = 0;

			Cell cellnome = rowp.createCell(cellnum++);
			cellnome.setCellValue(p.getDescnomecliente());

			Cell celldatanasc = rowp.createCell(cellnum++);
			celldatanasc.setCellValue(sdfnasc.format(p.getDatanascimento()));

			gc.set(Calendar.MONTH, gc.get(Calendar.MONTH) - 1);

			String month = String.format("%02d", gc.get(Calendar.MONTH) + 1);

			Cell cellinivig = rowp.createCell(cellnum++);
			cellinivig.setCellValue("0" + String.valueOf(gc.getMinimum(Calendar.DAY_OF_MONTH))
					.concat("/" + String.valueOf(month).concat("/" + String.valueOf(gc.get(Calendar.YEAR)))));

			Cell cellfimvig = rowp.createCell(cellnum++);
			cellfimvig.setCellValue(String.valueOf(gc.getActualMaximum(Calendar.DAY_OF_MONTH))
					.concat("/" + String.valueOf(month).concat("/" + String.valueOf(gc.get(Calendar.YEAR)))));

			gc.set(Calendar.MONTH, gc.get(Calendar.MONTH) + 1);

			Cell cellop = rowp.createCell(cellnum++);
			if (p.getCodtiposituacaotitulo() == 1) {
				cellop.setCellValue(2);
			} else {
				cellop.setCellValue(1);
			}

			// Set CPF
			String scpf = p.getNumcpfcnpj();
			String p1 = scpf.substring(0, 3).concat(".");
			String p2 = scpf.substring(3, 6).concat(".");
			String p3 = scpf.substring(6, 9).concat("-");
			String p4 = scpf.substring(9, 11);
			scpf = p1.concat(p2).concat(p3).concat(p4);
			Cell cellcpf = rowp.createCell(cellnum++);
			cellcpf.setCellValue(scpf);

			Cell cellcs = rowp.createCell(cellnum++);

			if (p.getCodtiposituacaotitulo() == 2) {
				if (age < 66 && p.getValorlimite() > 400000) {
					p.setValorlimite(400000l);
				} else if (age >= 66 && age < 76 && p.getValorlimite() > 20000) {
					p.setValorlimite(20000l);
				} else if (age >= 76 && age < 81 && p.getValorlimite() > 2000) {
					p.setValorlimite(2000l);
				}
				cellcs.setCellValue(p.getValorlimite());
				cellcs.setCellStyle(stylecs);

			} else {
				p.setCapitalsegurado((p.getCapitalsegurado().divide(numcontasbd)).add(valorlimitebd));

				if (age < 66 && p.getCapitalsegurado().compareTo(highlimit) > 0) {
					p.setCapitalsegurado(highlimit);
				} else if (age >= 66 && age < 76 && p.getCapitalsegurado().compareTo(mediumlimit) > 0) {
					p.setCapitalsegurado(mediumlimit);
				} else if (age >= 76 && age < 81 && p.getCapitalsegurado().compareTo(lowlimit) > 0) {
					p.setCapitalsegurado(lowlimit);
				}
				double d = p.getCapitalsegurado().doubleValue();
				cellcs.setCellValue(d);
				cellcs.setCellStyle(stylecs);
			}

		}

		// -------------------------------------------------------------------------------------------------------

		try

		{
			FileOutputStream out = new FileOutputStream(new File(PrestamistasController.fileName));
			workbook.write(out);
			out.close();
			System.out.println("Arquivo Excel criado com sucesso!");
			workbook.close();

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
		return "END";
	}

}
