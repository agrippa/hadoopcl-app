package pipeline;

import javax.xml.parsers.*;
import java.io.IOException;
import org.w3c.dom.Document;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.apache.commons.io.FileUtils;
import java.io.File;
import org.apache.hadoop.io.*;

public class ParseWiki {
    public static void main(String[] args) throws SAXException, javax.xml.parsers.ParserConfigurationException, java.io.IOException {
        if(args.length != 4) {
            System.out.println("usage: java ParseWiki filename output-folder start length");
            return;
        }

        final String outputFolder = args[1];
        final int start = Integer.parseInt(args[2]);
        final int length = Integer.parseInt(args[3]);

        System.out.println("Processing "+length+" documents from "+args[0]+" starting at "+start);

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        DefaultHandler handler = new DefaultHandler() {
            private long iter = 0;
            private SequenceFile.Writer writer;
            private String currentTitle;
            private StringBuffer currentBody;
            private boolean insideTitle = false;
            private boolean insideBody = false;

            public void startElement(String uri, String localName, String qName,
                    Attributes attributes) throws SAXException {
                if(qName.equalsIgnoreCase("TITLE")) {
                    insideTitle = true;
                    this.iter++;
                } else if(qName.equalsIgnoreCase("TEXT")) {
                    currentBody = new StringBuffer();
                    insideBody = true;
                }
            }
            
            public void endElement(String uri, String localName, String qName) {
                long currentIter = this.iter - 1;
                if(insideBody) {
                    System.out.println(currentIter+" Dumping "+this.currentTitle);
                    File newFileObj = new File(outputFolder+"/"+currentIter+".txt");
                    try {
                        FileUtils.writeStringToFile(newFileObj, currentBody.toString());
                    } catch(IOException io) {
                        throw new RuntimeException(io);
                    }

                    if (length != -1 && this.iter >= start + length) {
                        System.out.println("\nExiting");
                        System.exit(0);
                    }
                }
                insideTitle = false;
                insideBody = false;
            }

            public void characters(char ch[], int chStart, int chLength) throws SAXException {
                long currentIter = this.iter - 1;

                if(insideTitle) {
                    this.currentTitle = new String(ch, chStart, chLength);
                }

                if(!insideBody || currentIter < start) return;

                String bodyText = new String(ch, chStart, chLength);
                currentBody.append(bodyText);
                currentBody.append("\n");
            }
        };

        saxParser.parse(args[0], handler);
    }
}
