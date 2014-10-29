package punareo.maori_app;

import android.content.Context;
import android.util.Xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/**
 * Created by 21002282 on 25/08/2014.
 * A class that parses an XML document containing information and references to the objects to be used by the application
 * Uses a string passed to it to filter which data should be stored and which should be ignored
 * This ensures specific categories of object (Animals, Numbers, Shapes etc) are used in each Learning or Game session
 */


public class XMLParser
{
    private static final XMLParser INSTANCE = new XMLParser();
    private ArrayList<ContentObject> content_object_list;
    private static final String ns = null;

    private XMLParser() {};

    //The XMLParser is a static class. This method returns the parser without requiring instantiation outside of the class
    public static XMLParser get_instance()
    {
        return INSTANCE;
    }

    //Initializes an XMLPullParser and starts the process of reading the XML file line by line
    //@param Context c represents the context of the Activity calling the parser
    //@param Inputstream in represents the XML file to be read by the parser
    //@param String category represents the value of a <category> tag in the XML file
    //This is used to filter which XML <object> tags should be stored into ContentObjects
    public List<ContentObject> parse_xml( Context c, InputStream in, String category ) throws XmlPullParserException, IOException, SAXException {
        try {
            content_object_list = new ArrayList<ContentObject>();

            //Initializing the XMLPullParser
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.next();

            parser.require( XmlPullParser.START_TAG, ns, "file" );
            while( parser.next() != XmlPullParser.END_TAG ) {
                if( parser.getEventType() != XmlPullParser.START_TAG )
                    continue;

                if( parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals( "category" )
                                                    && parser.getAttributeValue(ns, "name").equals( category ) ) {
                    while( parser.next() != XmlPullParser.END_TAG ) {
                        if( parser.getEventType() != XmlPullParser.START_TAG )
                            continue;

                        content_object_list.add( read_object(c, parser) );
                    }
                }
                else Skip( parser );
            }
        } finally { in.close(); }
        return content_object_list;
    }

    public int Get_Size()
    {
        return content_object_list.size();
    }

    //A function to parse the tags inside an <object> tag and call the read_text function when it reaches any of the inner data tags
    //@param Context context represents the context passed from the parse_xml function, representing the context of the activity calling the XMLParser
    //@XMLPullparser parser represents the XMLPullParser being used by the class
    private ContentObject read_object( Context context, XmlPullParser parser ) throws XmlPullParserException, IOException, SAXException {
        try {
            String name = "", img = "", snd = "";
            while( parser.next() != XmlPullParser.END_TAG ) {
                if( parser.getEventType() != XmlPullParser.START_TAG )
                    continue;

                String tag_name = parser.getName();
                if( tag_name.equals( "name" ) )
                    name = read_text(parser, "name");
                else if( tag_name.equals( "imgpath" ) )
                    img = read_text(parser, "imgpath");
                else if( tag_name.equals( "soundpath" ) )
                    snd = read_text(parser, "soundpath");
            }
            return new ContentObject( context, name, img, snd );
        } finally {}
    }

    //A function to read string data stored inside data tags within an <object> tag
    //@param XMLPullParser parser represents the parser being used by the class
    //@param String tag_name represents the name of the tag inside an <object> tag so the parser knows where the data tag begins and ends
    private String read_text( XmlPullParser parser, String tag_name ) throws XmlPullParserException, IOException, SAXException {
        String result = "";
        parser.require( XmlPullParser.START_TAG, ns, tag_name );
        if( parser.next() == XmlPullParser.TEXT )
            result = parser.getText();

        parser.nextTag();
        parser.require( XmlPullParser.END_TAG, ns, tag_name );

        return result;
    }

    //A function to skip over unwanted tags and resume after the skipped tag is closed
    //@param XMLPullParser parser represents the parser being used by the class
    private void Skip( XmlPullParser parser ) throws XmlPullParserException, IOException {
        if( parser.getEventType() != XmlPullParser.START_TAG )
            throw new IllegalStateException();

        int depth = 1;
        while( depth != 0 ) {
            switch( parser.next() ) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
