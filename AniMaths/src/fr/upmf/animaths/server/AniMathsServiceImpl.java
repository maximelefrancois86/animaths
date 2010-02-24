package fr.upmf.animaths.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.upmf.animaths.client.LoadEquationService;
import fr.upmf.animaths.client.LoadPathNamesService;

/**
 * @description server-side load and save current exercice 
 */
public class AniMathsServiceImpl extends RemoteServiceServlet implements LoadPathNamesService, LoadEquationService {

	private static final long serialVersionUID = 8772776810359697699L;

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
	        return string;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<String> loadPathNames(String path) {
//		System.out.println("okImplLoadPaths "+path);
//		String list = "";
//		File dir = new File(path);
//		assert dir.isDirectory();
//		for(String fileName : dir.list())
//			if(fileName.contains(".xml")) {
//				System.out.println(path+File.pathSeparator+fileName);
//				list.concat(";").concat(path+File.pathSeparator+fileName);
//			}
//		return list;
		System.out.println("okImplLoadPaths "+path);
		List<String> list = new ArrayList<String>();
		File dir = new File(path);
		assert dir.isDirectory();
		for(String fileName : dir.list())
			if(fileName.contains(".xml")) {
				System.out.println(path+"/"+fileName);
				list.add(path+"/"+fileName);
			}
		return list;
	}
}
