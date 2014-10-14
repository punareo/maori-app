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
 */


public class XMLParser
{
    private static final XMLParser INSTANCE = new XMLParser();
    private ArrayList<ContentObject> content_object_list;
    private static final String ns = null;

    private XMLParser() {};

    public static XMLParser get_instance()
    {
        return INSTANCE;
    }

    public List<ContentObject> parse_xml( Context c, InputStream in, String category ) throws XmlPullParserException, IOException, SAXException {
        try {
            content_object_list = new ArrayList<ContentObject>();
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

    private String read_text( XmlPullParser parser, String tag_name ) throws XmlPullParserException, IOException, SAXException {
        String result = "";
        parser.require( XmlPullParser.START_TAG, ns, tag_name );
        if( parser.next() == XmlPullParser.TEXT )
            result = parser.getText();

        parser.nextTag();
        parser.require( XmlPullParser.END_TAG, ns, tag_name );

        return result;
    }

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
