//import org.apache.tika.Tika;
//import org.apache.tika.exception.TikaException;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class TikaTextExtractor {
//    public static String extractText(String filePath) {
//        Tika tika = new Tika();
//        try {
//            File file = new File(filePath);
//            return tika.parseToString(file);
//        } catch (IOException | TikaException e) {
//            e.printStackTrace();
//            return "Error extracting text: " + e.getMessage();
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        String filePath = "C:\\Users\\Admin\\Documents\\korean\\vanNgheKorSub.docx"; // Change this to your file path
//        String extractedText = extractText(filePath);
////        System.out.println("Extracted Text:\n" + extractedText);
//
//        BufferedWriter writer = new BufferedWriter(new FileWriter("F:\\output1.txt"));
//        writer.write(extractedText);
//
//        writer.close();
//    }
//}
