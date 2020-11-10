package testsource;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import kotlin.Pair;
import normalizer.Normalizer;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class ReadXmlFile {

    int i = 0;
    String t = null;

    public static interface XmlReadCompletionListener
    {
        void OnXmlReadCompleted(ArrayList<Pair<String,String>> pairs);
    }

    public void readXml(String xmlFilePath,XmlReadCompletionListener xmlReadCompletionListener) {

        ArrayList<Pair<String,String>> pairs = new ArrayList<Pair<String,String>>();

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();


            DefaultHandler handler = new DefaultHandler() {


                boolean title = false;
                boolean articleAbstract = false;

                public void startElement(String uri, String localName,String qName,
                                         Attributes attributes) throws SAXException {

                    if (qName.equalsIgnoreCase("title")) {
                        title = true;
                    }

                    if (qName.equalsIgnoreCase("abstract")) {
                        articleAbstract = true;
                    }

                }

//                public void endElement(String uri, String localName,
//                                       String qName) throws SAXException {
//
//
//
//                }

                public void characters(char ch[], int start, int length) throws SAXException {

                    if (title) {
                        t = new String(ch, start, length);
                        title = false;
                    }

                    if (articleAbstract)
                    {
                        articleAbstract = false;

                        if (t != null)
                        {
                            i++;
//                            System.out.println(i);
                            pairs.add(new Pair<>(t , new String(ch, start, length)));

//                            System.out.println(i + " - " + "title : " + t + "\n" + "abstract : " + new Normalizer().normalize(new String(ch, start, length)));
                        }
                    }

//                    752216
                    if (i == 752216) {
                        throw new SAXException();
                    }

                }

            };

            saxParser.parse(xmlFilePath, handler);


        } catch (Exception e) {
            System.out.println("done");
            xmlReadCompletionListener.OnXmlReadCompleted(pairs);
        }

    }

}