import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ContentHandlerDecorator;
import org.xml.sax.SAXException;

import java.io.Writer;
import java.util.regex.Pattern;

public class FilteringContentHandler extends BodyContentHandler {
    private final StringBuilder buffer = new StringBuilder();
    private final Pattern pattern = Pattern.compile(".*\\.indd.*");

    public FilteringContentHandler(Writer writer) {
        super(writer);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        buffer.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String text = buffer.toString().trim();
        if (!text.isEmpty() && !pattern.matcher(text).matches()) {
            super.characters(text.toCharArray(), 0, text.length());
        }
        buffer.setLength(0);
        super.endElement(uri, localName, qName);
    }
}