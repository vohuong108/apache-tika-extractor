import org.apache.poi.util.IOUtils;
import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

import java.io.FileInputStream;
import java.io.InputStream;

public class TikaTextExtractorDS {
    public static void main(String[] args) throws Exception {
        IOUtils.setByteArrayMaxOverride(Integer.MAX_VALUE);
        // Load custom Tika configuration
        TikaConfig config = new TikaConfig(TikaTextExtractorDS.class.getClassLoader().getResourceAsStream("tika-config.xml"));
        AutoDetectParser parser = new AutoDetectParser(config);
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();
        context.set(Parser.class, parser);


        try (InputStream stream = new FileInputStream("C:\\Users\\Admin\\Documents\\korean\\vanNgheKorSub.docx");) {
            parser.parse(stream, handler, metadata, context);
            System.out.println(handler.toString());
        }
    }
}