package com.cts.inb.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ExcelUtils - Data-driven utility using Apache POI
 * Responsibilities:
 * - Read inputs (e.g., menu texts) from Excel (sheet-based key/value lookup)
 * - Write logs and listings to Excel (append-only)
 * - Provide input and output Excel paths for visibility
 */
public class ExcelUtils {

	// Base paths (Windows-friendly) — files live under src/main/resources/testdata
	private static final String BASE_DIR = System.getProperty("user.dir") + File.separator +
			"src" + File.separator + "main" + File.separator + "resources" + File.separator + "testdata";

	private static final String INPUT_XLSX = BASE_DIR + File.separator + "inb_input.xlsx";
	private static final String OUTPUT_XLSX = BASE_DIR + File.separator + "inb_output.xlsx";

	// Common sheet names
	private static final String SHEET_MENUS = "Menus";     // Key | Value
	private static final String SHEET_LOGS = "Logs";       // Timestamp | Scenario | Step | Details

	/**
	 * Returns absolute path to the input Excel (for reading config/menu text).
	 */
	public static String getInputExcelPath() {
		return INPUT_XLSX;
	}

	/**
	 * Returns absolute path to the output Excel (for writing logs and listings).
	 */
	public static String getOutputExcelPath() {
		return OUTPUT_XLSX;
	}

	// ===== Reading APIs =====

	/**
	 * Read a value from a sheet that has two columns: Key | Value.
	 * It finds the row where the first cell equals 'key' (case-sensitive) and returns the second cell value.
	 * @param sheetName Sheet with Key/Value pairs
	 * @param key       Lookup key (e.g., "NEW_BIKES_MENU", "USED_CARS_MENU")
	 * @return value or null if not found
	 */
	public static String readKeyValue(String sheetName, String key) {
		File file = new File(INPUT_XLSX);
		if (!file.exists()) {
			System.out.println("[ExcelUtils] Input Excel not found: " + INPUT_XLSX);
			return null;
		}

		try (FileInputStream fis = new FileInputStream(file); XSSFWorkbook wb = new XSSFWorkbook(fis)) {
			Sheet sheet = wb.getSheet(sheetName);
			if (sheet == null) return null;

			for (int r = sheet.getFirstRowNum(); r <= sheet.getLastRowNum(); r++) {
				Row row = sheet.getRow(r);
				if (row == null) continue;
				Cell keyCell = row.getCell(0);
				Cell valCell = row.getCell(1);
				String keyText = getCellString(keyCell);
				if (keyText != null && keyText.equals(key)) {
					return getCellString(valCell);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Convenience: Read menu text by key from the "Menus" sheet.
	 */
	public static String readMenuText(String menuKey) {
		return readKeyValue(SHEET_MENUS, menuKey);
	}

	// ===== Writing APIs =====

	/**
	 * Append a structured log entry to the "Logs" sheet in the output Excel.
	 * Columns: Timestamp | Scenario | Step | Details
	 * Creates workbook/sheet if missing.
	 */
	public static void writeLog(String scenario, String step, String details) {
		try (Workbook wb = getOrCreateWorkbook(OUTPUT_XLSX)) {
			Sheet logs = getOrCreateSheet(wb, SHEET_LOGS);

			int nextRow = logs.getLastRowNum() + 1;
			// If sheet is empty, add header
			if (logs.getLastRowNum() == 0 && isRowEmpty(logs.getRow(0))) {
				Row header = logs.createRow(0);
				header.createCell(0).setCellValue("Timestamp");
				header.createCell(1).setCellValue("Scenario");
				header.createCell(2).setCellValue("Step");
				header.createCell(3).setCellValue("Details");
				nextRow = 1;
			}

			Row row = logs.createRow(nextRow);
			String ts = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			row.createCell(0).setCellValue(ts);
			row.createCell(1).setCellValue(scenario == null ? "" : scenario);
			row.createCell(2).setCellValue(step == null ? "" : step);
			row.createCell(3).setCellValue(details == null ? "" : details);

			saveWorkbook(wb, OUTPUT_XLSX);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Write a list of strings into a dedicated sheet named after listTitle.
	 * The sheet will contain a header and each item on a new row in column A.
	 * @param listTitle Sheet name (sanitized)
	 * @param items     Items to write
	 */
	public static void writeListing(String listTitle, List<String> items) {
		if (items == null) items = new ArrayList<>();
		String sheetName = sanitizeSheetName(listTitle == null ? "Listing" : listTitle);
		try (Workbook wb = getOrCreateWorkbook(OUTPUT_XLSX)) {
			Sheet sheet = getOrCreateSheet(wb, sheetName);

			// Clear existing content except header if present
			if (sheet.getLastRowNum() > 0) {
				for (int r = sheet.getLastRowNum(); r >= 1; r--) {
					Row row = sheet.getRow(r);
					if (row != null) sheet.removeRow(row);
				}
			}

			// Header
			Row header = sheet.getRow(0);
			if (header == null) {
				header = sheet.createRow(0);
				header.createCell(0).setCellValue("Items");
			}

			// Rows
			int rowIndex = 1;
			for (String item : items) {
				Row row = sheet.createRow(rowIndex++);
				row.createCell(0).setCellValue(item == null ? "" : item);
			}

			saveWorkbook(wb, OUTPUT_XLSX);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ===== Internal helpers =====

	private static Workbook getOrCreateWorkbook(String path) throws IOException {
		Path p = Paths.get(path);
		// Ensure directory exists
		Files.createDirectories(p.getParent());

		if (Files.exists(p)) {
			try (FileInputStream fis = new FileInputStream(p.toFile())) {
				return new XSSFWorkbook(fis);
			}
		}
		return new XSSFWorkbook();
	}

	private static Sheet getOrCreateSheet(Workbook wb, String name) {
		Sheet s = wb.getSheet(name);
		if (s == null) {
			s = wb.createSheet(name);
		}
		return s;
	}

	private static void saveWorkbook(Workbook wb, String path) throws IOException {
		try (FileOutputStream fos = new FileOutputStream(path)) {
			wb.write(fos);
		}
	}

	private static String getCellString(Cell cell) {
		if (cell == null) return null;
		if (cell.getCellType() == CellType.STRING) {
			return cell.getStringCellValue();
		}
		if (cell.getCellType() == CellType.NUMERIC) {
			if (DateUtil.isCellDateFormatted(cell)) {
				return new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
			}
			return String.valueOf(cell.getNumericCellValue());
		}
		if (cell.getCellType() == CellType.BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		}
		if (cell.getCellType() == CellType.FORMULA) {
			return cell.getRichStringCellValue().getString();
		}
		return null;
	}

	private static String sanitizeSheetName(String name) {
		String n = name.replaceAll("[\\/:*?\"<>|]", "_");
		return n.length() > 31 ? n.substring(0, 31) : n;
	}

	private static boolean isRowEmpty(Row row) {
		if (row == null) return true;
		for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);
			if (cell != null && cell.getCellType() != CellType.BLANK) return false;
		}
		return true;
	}
}
