package fr.upmf.animaths.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.upmf.animaths.client.LoadEquationService;

/**
 * @description server-side load and save current exercice 
 */
public class LoadEquationServiceImpl extends RemoteServiceServlet implements LoadEquationService {

	private static final long serialVersionUID = 8772776573359873699L;

	
	@Override
	public String loadEquation(String path) {
		System.out.println("okImplLoadEquation "+path);
		try {
	        StringBuffer fileData = new StringBuffer(1000);
	        BufferedReader reader = new BufferedReader(new FileReader(path));
	        char[] buf = new char[1024];
	        int numRead=0;
	        while((numRead=reader.read(buf)) != -1){
	            String readData = String.valueOf(buf, 0, numRead);
	            fileData.append(readData);
	            buf = new char[1024];
	        }
	        reader.close();
	        String string = fileData.toString();
	        string = string.trim();
	        byte[] bytes = string.getBytes("UTF-8");
	        return new String(bytes,"UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
