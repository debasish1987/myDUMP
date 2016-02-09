package com.test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLParser {
	
	Document dom;
	
	public static void main(String[] args){
		String xmlString= "<NewDataSet>  <Table1>  <ActionType>0</ActionType>  <Activity>0</Activity>  <DeptName>MES_G (MES최적화)</DeptName><Duty>E5(책임)</Duty>  <Opinion /> <Sequence>0</Sequence> <UserID>I120601100622C404058</UserID>  <UserName>최창완</UserName> <Delegated>0</Delegated><Approved>20150521052229</Approved><Arbitrary /><Arirved>20150521052229</Arirved>  <BodyModify>-1</BodyModify> <DutyCode>FFDD</DutyCode> <MailAddress>changwan.choi@samsung.com</MailAddress>  <RouteModify>-1</RouteModify> </Table1>   <Table1>     <ActionType>1</ActionType>     <Activity>1</Activity>     <DeptName>MES_G (MES개발)</DeptName>     <Duty>E4(선임)</Duty>     <Opinion />     <Sequence>1</Sequence>     <UserID>R020218102358C604664</UserID>     <UserName>김경하</UserName>     <Delegated>0</Delegated>     <Approved>20150521052444</Approved>     <Arbitrary />     <Arirved>20150521052229</Arirved>     <BodyModify>-1</BodyModify>     <DutyCode>FFFF</DutyCode>     <MailAddress>shyguy.kim@samsung.com</MailAddress>     <RouteModify>-1</RouteModify>   </Table1>   <Table1>     <ActionType>0</ActionType>     <Activity>9</Activity>     <DeptName>MES_G (MES최적화)</DeptName>     <Duty>E5(책임)</Duty>     <Opinion />     <Sequence>2</Sequence>     <UserID>D060725142348C401808</UserID>     <UserName>문설주</UserName>     <Delegated>0</Delegated>     <Approved>20150521052444</Approved>     <Arbitrary />     <Arirved>20150521052444</Arirved>     <BodyModify>-1</BodyModify>     <DutyCode>FFDD</DutyCode>     <MailAddress>seolju.mun@samsung.com</MailAddress>     <RouteModify>-1</RouteModify>   </Table1>   <Table1>     <ActionType>0</ActionType>     <Activity>9</Activity>     <DeptName>MES_G (MES최적화)</DeptName>     <Duty>E5(책임)</Duty>     <Opinion />     <Sequence>3</Sequence>     <UserID>D060725142348C401808</UserID>     <UserName>문설주</UserName>     <Delegated>0</Delegated>     <Approved>20150521052444</Approved>     <Arbitrary />     <Arirved>20150521052444</Arirved>     <BodyModify>-1</BodyModify>     <DutyCode>FFDD</DutyCode>     <MailAddress>seolju.mun@samsung.com</MailAddress>     <RouteModify>-1</RouteModify>   </Table1>   <Table1>     <ActionType>0</ActionType>     <Activity>9</Activity>     <DeptName>MES_G (MES최적화)</DeptName>     <Duty>E5(책임)</Duty>     <Opinion />     <Sequence>4</Sequence>     <UserID>D060725142348C401808</UserID>     <UserName>문설주</UserName>     <Delegated>0</Delegated>     <Approved>20150521052444</Approved>     <Arbitrary />     <Arirved>20150521052444</Arirved>     <BodyModify>-1</BodyModify>     <DutyCode>FFDD</DutyCode>     <MailAddress>seolju.mun@samsung.com</MailAddress>     <RouteModify>-1</RouteModify>   </Table1>   <Table1>     <ActionType>0</ActionType>     <Activity>9</Activity>     <DeptName>MES_G (MES최적화)</DeptName>     <Duty>E5(책임)</Duty>     <Opinion />     <Sequence>5</Sequence>     <UserID>D060725142348C401808</UserID>     <UserName>문설주</UserName>     <Delegated>0</Delegated>     <Approved>20150521052444</Approved>     <Arbitrary />     <Arirved>20150521052444</Arirved>     <BodyModify>-1</BodyModify>     <DutyCode>FFDD</DutyCode>     <MailAddress>seolju.mun@samsung.com</MailAddress>     <RouteModify>-1</RouteModify>   </Table1>   <Table1>     <ActionType>0</ActionType>     <Activity>9</Activity>     <DeptName>MES_G (MES최적화)</DeptName>     <Duty>E3(사원)</Duty>     <Opinion />     <Sequence>6</Sequence>     <UserID>M130620022033C407456</UserID>     <UserName>원지인</UserName>     <Delegated>0</Delegated>     <Approved>20150521052444</Approved>     <Arbitrary />     <Arirved>20150521052444</Arirved>     <BodyModify>-1</BodyModify>     <DutyCode>FFII</DutyCode>     <MailAddress>jiin.won@samsung.com</MailAddress>     <RouteModify>-1</RouteModify>   </Table1>   <Table1>     <ActionType>0</ActionType>     <Activity>9</Activity>     <DeptName>MES_G (MES최적화)</DeptName>     <Duty>E5(책임)</Duty>     <Opinion />     <Sequence>7</Sequence>     <UserID>M121004010047C403818</UserID>     <UserName>이경화</UserName>     <Delegated>0</Delegated>     <Approved>20150521052444</Approved>     <Arbitrary />     <Arirved>20150521052444</Arirved>     <BodyModify>-1</BodyModify>     <DutyCode>FFDD</DutyCode>     <MailAddress>khwa.lee@samsung.com</MailAddress>     <RouteModify>-1</RouteModify>   </Table1>   <Table1>     <ActionType>0</ActionType>     <Activity>9</Activity>     <DeptName>MES_G (MES최적화)</DeptName>     <Duty>Agent</Duty>     <Opinion />     <Sequence>8</Sequence>     <UserID>I140513095754C406428</UserID>     <UserName>이현주</UserName>     <Delegated>0</Delegated>     <Approved>20150521052444</Approved>     <Arbitrary />     <Arirved>20150521052444</Arirved>     <BodyModify>-1</BodyModify>     <DutyCode>JJKK</DutyCode>     <MailAddress>hyunju337.lee@samsung.com</MailAddress>     <RouteModify>-1</RouteModify>   </Table1> </NewDataSet>";
		
		//create an instance
		XMLParser dpe = new XMLParser(xmlString);
		//call run example
		//dpe.runExample();
		dpe.getBeanList(xmlString);
	}

	public XMLParser(String xmlString){
		//create a list to hold the employee objects
		//mListBeans = new ArrayList();
		//runExample();
	}

	public List getBeanList(String xmlString) {
	    parseXmlString(xmlString);
		
		//parse the xml file and get the dom object
		//parseXmlFile();
		
		//get each employee element and create a Employee object
		List mListBeans = parseDocument();
		
		//Iterate through the list and print the data
		//printData();
		return mListBeans;
	}
	
	private void parseXmlString(String xmlString){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			//parse using builder to get DOM representation of the XML String
	       InputSource is = new InputSource(new StringReader(xmlString));
		   dom = db.parse(is);
			

		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
/*	private void parseXmlFile(){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			//parse using builder to get DOM representation of the XML file
		    dom = db.parse("employees.xml");
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}*/

	
	private List parseDocument(){
		List mListBeans = new ArrayList();;
		//get the root elememt
		Element docEle = dom.getDocumentElement();
		//get a nodelist of <Table1> elements
		NodeList nl = docEle.getElementsByTagName("Table1");
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {
				
				//get the employee element
				Element el = (Element)nl.item(i);
				
				//get the Employee object
				AssignSrApprovedBean e = getItems(el);
				
				//add it to list
				mListBeans.add(e);
			}
		}
		return mListBeans;
	}


	/**
	 * I take an employee element and read the values in, create
	 * an Employee object and return it
	 * @param empEl
	 * @return
	 */
	private AssignSrApprovedBean getItems(Element empEl) {
	/*		
		 <ActionType>0</ActionType>
	      <Activity>9</Activity>
	      <UserName>이현주</UserName>
	      <Approved>20150521052444</Approved>
    */
		
		String mApproveStatus = getTextValue(empEl,"ActionType");
		String mSrApprovedType= getTextValue(empEl,"Activity");
		String mSrApprodeName= getTextValue(empEl,"UserName");
		String mSrApprovedDateTime= getTextValue(empEl,"Approved");

		//Create a new AssignSrApprovedBean with the value read from the xml nodes
		AssignSrApprovedBean e = new AssignSrApprovedBean(mApproveStatus,mSrApprovedType,mSrApprodeName,mSrApprovedDateTime);
		
		return e;
	}


	/**
	 * I take a xml element and the tag name, look for the tag and get
	 * the text content 
	 * i.e for <employee><name>John</name></employee> xml snippet if
	 * the Element points to employee node and tagName is name I will return John  
	 * @param ele
	 * @param tagName
	 * @return
	 */
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}
		return textVal;
	}

	
	/**
	 * Calls getTextValue and returns a int value
	 * @param ele
	 * @param tagName
	 * @return
	 */
/*	private int getIntValue(Element ele, String tagName) {
		//in production application you would catch the exception
		return Integer.parseInt(getTextValue(ele,tagName));
	}
	*/
	/**
	 * Iterate through the list and print the 
	 * content to console
	 */
/*	private void printData(){
		
		System.out.println("No of Employees '" + myEmpls.size() + "'.");
		
		Iterator it = myEmpls.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}*/
	
}
