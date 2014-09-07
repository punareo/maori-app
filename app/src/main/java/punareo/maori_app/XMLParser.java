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
    //List<Content_Object>
    public void Parse(InputStream in, String category) throws XmlPullParserException, IOException, SAXException
    {

        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);

            int event_type = parser.getEventType();

            //Read File
            while (event_type != XmlPullParser.END_DOCUMENT)
            {
                //Find Category
                if (event_type == XmlPullParser.START_TAG && parser.getName().equals("category") && parser.getAttributeValue(ns, "name").equals(category))
                {
                    //Loop through category
                    while (event_type != XmlPullParser.END_TAG && parser.getName().equals("category"))
                    {
                        String name = "", img = "", snd = "";
                        if (event_type == XmlPullParser.START_TAG && parser.getName().equals("object"))
                            while (event_type != XmlPullParser.END_TAG && parser.getName().equals("object"))
                            {
                                if (event_type == XmlPullParser.START_TAG && parser.getName().equals("name"))
                                {
                                    event_type = parser.next();
                                    if (event_type == XmlPullParser.TEXT)
                                         name = parser.getText();
                                }
                                else if (event_type == XmlPullParser.START_TAG && parser.getName().equals("imgpath"))
                                {
                                    event_type = parser.next();
                                    if (event_type == XmlPullParser.TEXT)
                                        img = parser.getText();
                                }
                                else if (event_type == XmlPullParser.START_TAG && parser.getName().equals("soundpath"))
                                {
                                    event_type = parser.next();
                                    if (event_type == XmlPullParser.TEXT)
                                        snd = parser.getText();
                                }
                                event_type = parser.next();
                            }
                        System.out.println(name + " " + img + " " + snd);
                        event_type = parser.next();
                    }
                }
                System.out.println(event_type);
                event_type = parser.next();
            }
        } finally { in.close(); }
        //return content_object_list;
    }
}
