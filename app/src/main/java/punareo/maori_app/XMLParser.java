package punareo.maori_app;

import android.util.Xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/**
 * Created by 21002282 on 25/08/2014.
 */


public class XMLParser
{
    private static final XMLParser INSTANCE = new XMLParser();
    private ArrayList<Content_Object> content_object_list;
    private static final String ns = null;

    private XMLParser() {};

    public static XMLParser Get_Instance()
    {
        return INSTANCE;
    }

    public List<Content_Object> Get_List(InputStream in, String category) throws XmlPullParserException, IOException, SAXException
    {

        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();

            //Read File
            parser.require(XmlPullParser.START_TAG, ns, "file");
            while (parser.next() != XmlPullParser.END_TAG)
            {
                if (parser.getEventType() != XmlPullParser.START_TAG)
                    continue;
                String name = parser.getName();
                String cat = parser.getAttributeName(0);

                if (name.equals("category") && cat.equals(category))
                {
                    //Read Category
                    parser.require(XmlPullParser.START_TAG, ns, "category");
                    while (parser.next() != XmlPullParser.END_TAG)
                    {
                        if (parser.getEventType() != XmlPullParser.START_TAG)
                            continue;

                        if (parser.getName().equals("object"))
                        {
                            //Read Objects
                            parser.require(XmlPullParser.START_TAG, ns, "object");
                            while (parser.next() != XmlPullParser.END_TAG)
                            {
                                if (parser.getEventType() != XmlPullParser.START_TAG)
                                    continue;

                                String str = parser.getName();
                                String one = "", two = "", three = "";
                                if (str.equals("name"))
                                    if (parser.next() == XmlPullParser.TEXT)
                                        one = parser.getText();
                                else if (str.equals("imgpath"))
                                    if (parser.next() == XmlPullParser.TEXT)
                                        two = parser.getText();
                                else if (str.equals("soundpath"))
                                    if (parser.next() == XmlPullParser.TEXT)
                                        three = parser.getText();

                                content_object_list.add(new Content_Object(one, two, three));
                            }
                        }
                    }
                }
            }
        } finally { in.close(); }
        return content_object_list;
    }
}
