//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.text.PDFTextStripper;
//import java.io.File;
//import java.io.IOException;
//
//public class PDFTextExtractor {
//    public static void main(String[] args) {
//        String pdfFilePath = "C:\\Users\\Admin\\Documents\\basic-korean-grammar-and-workbook-by-andrew-byon.pdf"; // Change to your PDF file path
//        try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
//            PDFTextStripper pdfStripper = new PDFTextStripper();
//            String text = pdfStripper.getText(document);
//            System.out.println(text);  // Korean text should be correctly extracted
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
