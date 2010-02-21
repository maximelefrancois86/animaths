package fr.upmf.animaths.server;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.upmf.animaths.client.AniMathsService;

/**
 * @description server-side load and save current exercice 
 */
public class AniMathsServiceImpl extends RemoteServiceServlet implements AniMathsService {

	private static final long serialVersionUID = 8772776810359697699L;

	@Override
	public String loadEquation(String id) {
		try {
			File file = new File("/equation.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			Document doc;
			db = dbf.newDocumentBuilder();
			doc = db.parse(file);
			doc.getDocumentElement().normalize();
			System.out.println(doc.getDocumentElement().toString());
			Window.alert("Server side");
			return doc.getDocumentElement().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
