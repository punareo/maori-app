package punareo.maori_app;

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

/**
 * Created by 21002282 on 25/08/2014.
 */


public class XMLParser
{
    private static final XMLParser INSTANCE = new XMLParser();
    List<Content_Object> content_object_list;

    private XMLParser() {};

    public static XMLParser Get_Instance()
    {
        return INSTANCE;
    }

    public List<Content_Object> Get_List(String filename, String category) throws ParserConfigurationException, IOException, SAXException
    {
        content_object_list = new ArrayList<Content_Object>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(filename));

        //Sets cat_list as a list of all category nodes
        NodeList cat_list = doc.getDocumentElement().getChildNodes();
        for (int i = 0; i < cat_list.getLength(); i++)
        {
            //Finds category with attribute value matching passed in value
            if (cat_list.item(i).getAttributes().toString() == "Animal")
            {
                //Sets obj_list as a list of all object nodes
                NodeList obj_list = cat_list.item(i).getChildNodes();
                for (int j = 0; j < obj_list.getLength(); j++)
                {
                    //Sets object as an object inside obj_list
                    Node object = obj_list.item(j);
                    Node obj_name = object.getFirstChild();
                    Node obj_img = obj_name.getNextSibling();
                    Node obj_snd = obj_img.getNextSibling();

                    content_object_list.add(new Content_Object(obj_name.getTextContent(), obj_img.getTextContent(), obj_snd.getTextContent()));
                }
            }
        }
        return content_object_list;
    }
}
