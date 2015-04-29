import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;




public class XMLParser {
	
	private File getWorkingXML(File file){
		
		PrintWriter writer = null;
		Scanner sn = null;
		
		try{
			sn = new Scanner(file);
	        
	        writer = new PrintWriter("temp.xml", "UTF-8");
	        
	        int i=0;
	        while(sn.hasNext()){
	        	if(i==1) continue;
	        	String s = sn.nextLine();
	        	writer.println(s);
	        	i++;
	        }
	        
	        
		}
		
		catch (Exception e){
			e.printStackTrace();
		}
		
		finally{
			sn.close();
			writer.close();
			return new File("temp.xml");
		}
		 
	}
	
	public static void main(String[] args){
		
		    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		    try {
		        SAXParser saxParser = saxParserFactory.newSAXParser();
		        MyHandler handler = new MyHandler();
		        
		        File file = new File("/home/ishan/Webscope_L5/01/01/0000015.xml");
		        
		       
		        
		        
		        saxParser.parse(new File("/home/ishan/Webscope_L5/01/01/0000015.xml"), handler);
		       
		        List<String> Aspects = handler.getAspects();
		        
		        for(String aspect : Aspects){
		        	System.out.println(aspect);
		        }
		       
		    } catch (Throwable e) {
		        e.printStackTrace();
		    }
	}
}
	 


class MyHandler extends DefaultHandler {
	 
	List<String> Aspects = new ArrayList<String>();
	boolean isClassifier = false;
	
	public List<String> getAspects(){
		return Aspects;
	}
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	
    	System.out.println("Hey");
 
        if (qName.equalsIgnoreCase("classifier")) {
        	isClassifier = true;
        }
    }
 
 
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("classifier")) {
            isClassifier = false;
        }
    }
 
 
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
    	System.out.println("inchar");
        if(isClassifier){
        	String aspect = new String(ch, start, length);
        	Aspects.add(aspect.trim().toLowerCase());
        }
    }
}
