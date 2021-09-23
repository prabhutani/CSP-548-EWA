
/*********


http://www.saxproject.org/

SAX is the Simple API for XML, originally a Java-only API. 
SAX was the first widely adopted API for XML in Java, and is a �de facto� standard. 
The current version is SAX 2.0.1, and there are versions for several programming language environments other than Java. 

The following URL from Oracle is the JAVA documentation for the API

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html


*********/
import org.xml.sax.InputSource;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import  java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


////////////////////////////////////////////////////////////

/**************

SAX parser use callback function  to notify client object of the XML document structure. 
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SaxParserDataStore extends DefaultHandler {
    VoiceAssistant voiceAssistant;
    Laptop laptop;
    Phone phone;
    Accessory accessory;
	Wearable wearable;
	Wearable smartwatch;
	Wearable headphone;
	Wearable fitwatch;
	Wearable vr;
	Wearable pettracker;
    static HashMap<String,VoiceAssistant> voiceAssistants;
    static HashMap<String,Laptop> laptops;
    static HashMap<String,Phone> phones;
    static HashMap<String,Accessory> accessories;
	static HashMap<String, Wearable> wearables;
    String voiceAssistantXmlFileName;
	HashMap<String,String> accessoryHashMap;
    String elementValueRead;
	String currentElement="";
    public SaxParserDataStore()
	{
	}
	public SaxParserDataStore(String voiceAssistantXmlFileName) {
    this.voiceAssistantXmlFileName = voiceAssistantXmlFileName;
    voiceAssistants = new HashMap<String, VoiceAssistant>();
	laptops=new  HashMap<String, Laptop>();
	phones=new HashMap<String, Phone>();
	accessories=new HashMap<String, Accessory>();
	wearables = new HashMap<String, Wearable>();
	accessoryHashMap=new HashMap<String,String>();
	parseDocument();
    }

   //parse the xml using sax parser to get the data
    private void parseDocument() 
	{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try 
		{
	    SAXParser parser = factory.newSAXParser();
	    parser.parse(voiceAssistantXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
	}

   

////////////////////////////////////////////////////////////

/*************

There are a number of methods to override in SAX handler  when parsing your XML document :

    Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document. 
    Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.  
    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.


There are few other methods that you could use for notification for different purposes, check the API at the following URL:

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

***************/

////////////////////////////////////////////////////////////
	
	// when xml start element is parsed store the id into respective hashmap for voiceAssistant,laptops etc 
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("voiceAssistant")) 
		{
			currentElement="voiceAssistant";
			voiceAssistant = new VoiceAssistant();
            voiceAssistant.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("fitwatch")) 
		{
			currentElement="fitwatch";
			fitwatch = new Wearable();
            fitwatch.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("smartwatch")) 
		{
			currentElement="smartwatch";
			smartwatch = new Wearable();
            smartwatch.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("headphone")) 
		{
			currentElement="headphone";
			headphone = new Wearable();
            headphone.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("vr")) 
		{
			currentElement="vr";
			vr = new Wearable();
            vr.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("pettracker")) 
		{
			currentElement="pettracker";
			pettracker = new Wearable();
            pettracker.setId(attributes.getValue("id"));
		}
        if (elementName.equalsIgnoreCase("phone"))
		{
			currentElement="phone";
			phone = new Phone();
            phone.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("laptop"))
		{
			currentElement="laptop";
			laptop= new Laptop();
            laptop.setId(attributes.getValue("id"));
        }
        if (elementName.equals("accessory") &&  !currentElement.equals("voiceAssistant"))
		{
			currentElement="accessory";
			accessory=new Accessory();
			accessory.setId(attributes.getValue("id"));
	    }
    }
	// when xml end element is parsed store the data into respective hashmap for voiceAssistant,laptops etc respectively
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equals("voiceAssistant")) {
			voiceAssistants.put(voiceAssistant.getId(),voiceAssistant);
			return;
        }

		if (element.equals("fitwatch")) {
			wearables.put(fitwatch.getId(),fitwatch);
			return;
        }
 
        
		if (element.equals("smartwatch")) {
			wearables.put(smartwatch.getId(),smartwatch);
			return;
        }
 
		if (element.equals("headphone")) {
			wearables.put(headphone.getId(),headphone);
			return;
        }
 
		if (element.equals("pettracker")) {
			wearables.put(pettracker.getId(),pettracker);
			return;
        }
 
		if (element.equals("vr")) {
			wearables.put(vr.getId(),vr);
			return;
        }
 
		if (element.equals("phone")) {	
			phones.put(phone.getId(),phone);
			return;
        }
        if (element.equals("laptop")) {	  
			laptops.put(laptop.getId(),laptop);
			return;
        }
        if (element.equals("accessory") && currentElement.equals("accessory")) {
			accessories.put(accessory.getId(),accessory);       
			return; 
        }
		if (element.equals("accessory") && currentElement.equals("voiceAssistant")) 
		{
			accessoryHashMap.put(elementValueRead,elementValueRead);
		}
      	if (element.equalsIgnoreCase("accessories") && currentElement.equals("voiceAssistant")) {
			voiceAssistant.setAccessories(accessoryHashMap);
			accessoryHashMap=new HashMap<String,String>();
			return;
		}
        if (element.equalsIgnoreCase("image")) {
		    if(currentElement.equals("voiceAssistant"))
				voiceAssistant.setImage(elementValueRead);
        	if(currentElement.equals("laptop"))
				laptop.setImage(elementValueRead);
            if(currentElement.equals("phone"))
				phone.setImage(elementValueRead);
            if(currentElement.equals("accessory"))
			accessory.setImage(elementValueRead);
			if(currentElement.equals("fitwatch"))
			fitwatch.setImage(elementValueRead);
			if(currentElement.equals("smartwatch"))
			smartwatch.setImage(elementValueRead);
			if(currentElement.equals("headphone"))
			headphone.setImage(elementValueRead);
			if(currentElement.equals("pettracker"))
			pettracker.setImage(elementValueRead);
			if(currentElement.equals("vr"))
			vr.setImage(elementValueRead);          
			return;
        }
        

		if (element.equalsIgnoreCase("discount")) {
            if(currentElement.equals("voiceAssistant"))
				voiceAssistant.setDiscount(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("laptop"))
				laptop.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("phone"))
				phone.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setDiscount(Double.parseDouble(elementValueRead));
				if(currentElement.equals("fitwatch"))
				fitwatch.setDiscount(Double.parseDouble(elementValueRead));
				if(currentElement.equals("smartwatch"))
				smartwatch.setDiscount(Double.parseDouble(elementValueRead));
				if(currentElement.equals("headphone"))
				headphone.setDiscount(Double.parseDouble(elementValueRead));
				if(currentElement.equals("pettracker"))
				pettracker.setDiscount(Double.parseDouble(elementValueRead));
				if(currentElement.equals("vr"))
				vr.setDiscount(Double.parseDouble(elementValueRead));          
			return;
	    }


		if (element.equalsIgnoreCase("condition")) {
            if(currentElement.equals("voiceAssistant"))
				voiceAssistant.setCondition(elementValueRead);
        	if(currentElement.equals("laptop"))
				laptop.setCondition(elementValueRead);
            if(currentElement.equals("phone"))
				phone.setCondition(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setCondition(elementValueRead);          
			if(currentElement.equals("fitwatch"))
			fitwatch.setCondition(elementValueRead);          
			
			if(currentElement.equals("smartwatch"))
			smartwatch.setCondition(elementValueRead);          
			
			if(currentElement.equals("headphone"))
			headphone.setCondition(elementValueRead);          
			
			if(currentElement.equals("pettracker"))
			pettracker.setCondition(elementValueRead);          
			
			if(currentElement.equals("vr"))
				vr.setCondition(elementValueRead);          
			return; 
		}

		if (element.equalsIgnoreCase("manufacturer")) {
            if(currentElement.equals("voiceAssistant"))
				voiceAssistant.setRetailer(elementValueRead);
        	if(currentElement.equals("laptop"))
				laptop.setRetailer(elementValueRead);
            if(currentElement.equals("phone"))
				phone.setRetailer(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setRetailer(elementValueRead);
				if(currentElement.equals("fitwatch"))
				fitwatch.setRetailer(elementValueRead); 
				if(currentElement.equals("smartwatch"))
				smartwatch.setRetailer(elementValueRead); 
				if(currentElement.equals("headphone"))
				headphone.setRetailer(elementValueRead); 
				if(currentElement.equals("pettracker"))
				pettracker.setRetailer(elementValueRead); 
				if(currentElement.equals("vr"))
				vr.setRetailer(elementValueRead);           
			return;
		}

        if (element.equalsIgnoreCase("name")) {
            if(currentElement.equals("voiceAssistant"))
				voiceAssistant.setName(elementValueRead);
        	if(currentElement.equals("laptop"))
				laptop.setName(elementValueRead);
            if(currentElement.equals("phone"))
				phone.setName(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setName(elementValueRead);   
				if(currentElement.equals("fitwatch"))
				fitwatch.setName(elementValueRead); 
				if(currentElement.equals("smartwatch"))
				smartwatch.setName(elementValueRead); 
				if(currentElement.equals("headphone"))
				headphone.setName(elementValueRead); 
				if(currentElement.equals("pettracker"))
				pettracker.setName(elementValueRead); 
				if(currentElement.equals("vr"))
				vr.setName(elementValueRead);        
			return;
	    }
	
        if(element.equalsIgnoreCase("price")){
			if(currentElement.equals("voiceAssistant"))
				voiceAssistant.setPrice(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("laptop"))
				laptop.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("phone"))
				phone.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("fitwatch"))
				fitwatch.setPrice(Double.parseDouble(elementValueRead));          
			if(currentElement.equals("smartwatch"))
			smartwatch.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("headphone"))
				headphone.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("pettracker"))
			pettracker.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("vr"))
				vr.setPrice(Double.parseDouble(elementValueRead));
			return;
        }

	}
	//get each element in xml tag
    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }


    /////////////////////////////////////////
    // 	     Kick-Start SAX in main       //
    ////////////////////////////////////////
	
//call the constructor to parse the xml and get product details
 public static void addHashmap() {
		String TOMCAT_HOME = System.getProperty("catalina.home");	
		new SaxParserDataStore(TOMCAT_HOME+"\\webapps\\Ass_sol\\ProductCatalog.xml");
    } 
}
