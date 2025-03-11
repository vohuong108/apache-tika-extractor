//import java.io.*;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.*;
//
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hwpf.HWPFDocument;
//import org.apache.poi.hwpf.extractor.WordExtractor;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
//import org.apache.poi.xslf.usermodel.XMLSlideShow;
//import org.apache.poi.xslf.usermodel.XSLFSlide;
//import org.apache.poi.xslf.usermodel.XSLFShape;
//import org.apache.poi.xslf.usermodel.XSLFTextShape;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.text.PDFTextStripper;
//import org.jsoup.Jsoup;
//import org.simplejavamail.api.email.Email;
//import org.simplejavamail.converter.EmailConverter;
//import org.apache.poi.ss.usermodel.*;
//
//public class FileTextExtractor {
//
//    public static String extractText(File file) throws Exception {
//        String fileName = file.getName().toLowerCase();
//        if (fileName.endsWith(".doc")) {
//            return extractDoc(file);
//        } else if (fileName.endsWith(".docx")) {
//            return extractDocx(file);
//        } else if (fileName.endsWith(".pdf")) {
//            return extractPdf(file);
//        } else if (fileName.endsWith(".pptx")) {
//            return extractPptx(file);
//        } else if (fileName.endsWith(".xls")) {
//            return extractXls(file);
//        } else if (fileName.endsWith(".xlsx")) {
//            return extractXlsx(file);
//        } else if (fileName.endsWith(".mht") || fileName.endsWith(".eml")) {
//            return extractMhtEml(file);
//        } else {
//            throw new IllegalArgumentException("Unsupported file format: " + fileName);
//        }
//    }
//
//    private static String extractDoc(File file) throws Exception {
//        try (FileInputStream fis = new FileInputStream(file);
//             HWPFDocument doc = new HWPFDocument(fis)) {
//            WordExtractor extractor = new WordExtractor(doc);
//            return extractor.getText();
//        }
//    }
//
//    private static String extractDocx(File file) throws Exception {
//        try (FileInputStream fis = new FileInputStream(file);
//             XWPFDocument docx = new XWPFDocument(fis)) {
//            XWPFWordExtractor extractor = new XWPFWordExtractor(docx);
//            return extractor.getText();
//        }
//    }
//
//    private static String extractPdf(File file) throws Exception {
//        try (PDDocument document = PDDocument.load(file)) {
//            PDFTextStripper stripper = new PDFTextStripper();
//            return stripper.getText(document);
//        }
//    }
//
//    private static String extractPptx(File file) throws Exception {
//        try (FileInputStream fis = new FileInputStream(file);
//             XMLSlideShow ppt = new XMLSlideShow(fis)) {
//            StringBuilder text = new StringBuilder();
//            for (XSLFSlide slide : ppt.getSlides()) {
//                for (XSLFShape shape : slide.getShapes()) {
//                    if (shape instanceof XSLFTextShape) {
//                        text.append(((XSLFTextShape) shape).getText()).append("\n");
//                    }
//                }
//            }
//            return text.toString().trim();
//        }
//    }
//
//    private static String extractXls(File file) throws Exception {
//        try (FileInputStream fis = new FileInputStream(file);
//             HSSFWorkbook workbook = new HSSFWorkbook(fis)) {
//            return extractExcelText(workbook);
//        }
//    }
//
//    private static String extractXlsx(File file) throws Exception {
//        try (FileInputStream fis = new FileInputStream(file);
//             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
//            return extractExcelText(workbook);
//        }
//    }
//
//    private static String extractExcelText(Workbook workbook) {
//        StringBuilder text = new StringBuilder();
//        for (Sheet sheet : workbook) {
//            for (Row row : sheet) {
//                for (Cell cell : row) {
//                    switch (cell.getCellType()) {
//                        case STRING:
//                            text.append(cell.getStringCellValue());
//                            break;
//                        case NUMERIC:
//                            text.append(cell.getNumericCellValue());
//                            break;
//                        case BOOLEAN:
//                            text.append(cell.getBooleanCellValue());
//                            break;
//                        case FORMULA:
//                            text.append(cell.getCachedFormulaResultType() == CellType.STRING
//                                    ? cell.getStringCellValue()
//                                    : cell.getNumericCellValue());
//                            break;
//                        default:
//                            break;
//                    }
//                    text.append("\t");
//                }
//                text.append("\n");
//            }
//        }
//        return text.toString().trim();
//    }
//
//    private static String extractMhtEml(File file) throws Exception {
//        byte[] bytes = Files.readAllBytes(file.toPath());
//        String content = new String(bytes, StandardCharsets.ISO_8859_1);
//        if (file.getName().endsWith(".eml")) {
//            Email email = EmailConverter.emlToEmail(content);
//            return email.getPlainText();
//        } else {
//            return Jsoup.parse(content).text();
//        }
//    }
//
//    public static void main(String[] args) {
//        File file = new File("F:\\Laurentiu Spilca - Spring Security in Action, Second Edition-Manning (2024).pdf");
//
//        try {
//            String text = extractText(file);
//            System.out.println("Extracted text:\n" + text);
//
//            BufferedWriter writer = new BufferedWriter(new FileWriter("F:\\outputkr3.txt"));
//            writer.write(text);
//            writer.close();
//        } catch (Exception e) {
//            System.err.println("Error extracting text: " + e.getMessage());
//        }
//    }
//}
//
