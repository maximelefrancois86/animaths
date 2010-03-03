package fr.upmf.animaths.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.upmf.animaths.client.LoadEquationService;

/**
 * @author Édouard Lopez & Maxime Lefrançois server-side code, return the
 *         content of the XML file given as parameter
 * @param path
 *          file to fetch and return
 */
public class LoadEquationServiceImpl extends RemoteServiceServlet implements
		LoadEquationService {

	private static final long serialVersionUID = 8772776573359873699L;

//	@Override
//	public String loadEquation(String path) {
//		System.out.println("okImplLoadEquation " + path);
//		try {
//			StringBuffer fileData = new StringBuffer(1000);
//			BufferedReader reader = new BufferedReader(new FileReader(path));
//			char[] buf = new char[1024];
//			int numRead = 0;
//			while ((numRead = reader.read(buf)) != -1) {
//				String readData = String.valueOf(buf, 0, numRead);
//				fileData.append(readData);
//				buf = new char[1024];
//			}
//			reader.close();
//			String string = fileData.toString();
//			string = string.trim();
//			byte[] bytes = string.getBytes("UTF-8");
//			return new String(bytes, "UTF-8");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	@Override
	public String loadEquation(String path) {
		System.out.println("okImplLoadEquation " + path);
		try {
			String result = "";
			String str;
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path),
      "UTF8"));
			while ((str = in.readLine()) != null) {
				result = result.concat(str);				
			}
			in.close();
			System.out.println(result);
			return result;
		} catch (FileNotFoundException e) {
			System.out.println("+++++++++ Missing file +++++++++");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("+++++++++ IO Error +++++++++");
			e.printStackTrace();
		}
		
//			StringBuffer fileData = new StringBuffer(1000);
//			BufferedReader reader = new BufferedReader(new FileReader(path));
//			char[] buf = new char[1024];
//			int numRead = 0;
//			while ((numRead = reader.read(buf)) != -1) {
//				String readData = String.valueOf(buf, 0, numRead);
//				fileData.append(readData);
//				buf = new char[1024];
//			}
//			reader.close();
//			String string = fileData.toString();
//			string = string.trim();
//			byte[] bytes = string.getBytes("UTF-8");
//			return new String(bytes, "UTF-8");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return null;
	}
	
//	 @Override
//	 public String loadEquation(String path) throws IOException {
//			BufferedReader in = new BufferedReader(
//				   new InputStreamReader(
//	                             new FileInputStream(path), "UTF8"));	          	 
//			        String str;
//			        while ((str = in.readLine()) != null) {
//			            System.out.println(str);		        	
//			        }
//			        in.close();
//
//	 }
}