import org.apache.tika.extractor.EmbeddedDocumentExtractor;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParserConfig;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.io.TikaInputStream;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class TikaDirectFileWriterUTF8 {
    public static void extractTextToFile(String inputFilePath, String outputFilePath) {
        AutoDetectParser parser = new AutoDetectParser(); // Auto-detects file type
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();

        PDFParserConfig pdfConfig = new PDFParserConfig();
        pdfConfig.setExtractInlineImages(false);          // Skip images
        pdfConfig.setOcrStrategy(PDFParserConfig.OCR_STRATEGY.NO_OCR);
        context.set(PDFParserConfig.class, pdfConfig);

        context.set(EmbeddedDocumentExtractor.class, new EmbeddedDocumentExtractor() {
            @Override
            public boolean shouldParseEmbedded(Metadata metadata) {
                MediaType contentType = MediaType.parse(metadata.get(Metadata.CONTENT_TYPE));
                return contentType != null && contentType.getType().equals("text");
            }

            @Override
            public void parseEmbedded(InputStream stream, ContentHandler handler, Metadata metadata, boolean outputHtml) {
                // No-op: Skip processing non-text content
            }
        });

        try (TikaInputStream tikaInputStream = TikaInputStream.get(Path.of(inputFilePath));
             OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outputFilePath), StandardCharsets.UTF_8);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

            // Use a content handler that writes directly to BufferedWriter
            ContentHandler handler = new BodyContentHandler(bufferedWriter);

            // Parse and write text directly to file
            parser.parse(tikaInputStream, handler, metadata, context);

            System.out.println("Extracted text successfully written to: " + outputFilePath);

        } catch (IOException | SAXException | org.apache.tika.exception.TikaException e) {
            e.printStackTrace();
            System.out.println("Error extracting text: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String inputFilePath = "F:\\Vo Huong\\JavaApp\\ExtractText\\src\\main\\resources\\input\\basic-korean-grammar-and-workbook-by-andrew-byon.pdf";  // Change this
        String outputFilePath = "F:\\Vo Huong\\JavaApp\\ExtractText\\src\\main\\resources\\output\\output1.txt"; // Change this

        extractTextToFile(inputFilePath, outputFilePath);
    }
}
