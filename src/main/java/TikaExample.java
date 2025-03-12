import org.apache.poi.util.IOUtils;
import org.apache.tika.extractor.EmbeddedDocumentExtractor;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.sax.WriteOutContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class TikaExample {
    public static void main(String[] args) throws Exception {
        IOUtils.setByteArrayMaxOverride(Integer.MAX_VALUE);
        InputStream stream = new FileInputStream("C:\\Users\\Admin\\Documents\\korean\\vanNgheKorSub.docx");// your input file stream;
        Parser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
//        BodyContentHandler handler = new BodyContentHandler(-1); // -1 for unlimited text

        ParseContext context = new ParseContext();
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
//        context.set(Parser.class, parser);

        try (OutputStream output = new FileOutputStream("F:\\Vo Huong\\JavaApp\\ExtractText\\src\\main\\resources\\output\\output3.txt")) {
            OutputStreamWriter writer = new OutputStreamWriter(output, StandardCharsets.UTF_8);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            // Use a content handler that writes directly to BufferedWriter
            ContentHandler handler = new BodyContentHandler(bufferedWriter);

            parser.parse(stream, handler, metadata, context);
        }

    }
}