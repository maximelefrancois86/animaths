package fr.upmf.animaths.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.upmf.animaths.client.LoadPathNamesService;

/**
 * @description server-side load and save current exercice 
 */
public class LoadPathNamesServiceImpl extends RemoteServiceServlet implements LoadPathNamesService {

	private static final long serialVersionUID = 8772776573359697699L;

	@Override
	public List<String> loadPathNames(String path) {
		List<String> list = new ArrayList<String>();
		File dir = new File(path);
		assert dir.isDirectory();
		for(String fileName : dir.list())
			if(fileName.contains(".xml"))
				list.add(path+"/"+fileName);
		return list;
	}

}
