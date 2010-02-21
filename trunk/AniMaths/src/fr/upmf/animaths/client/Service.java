package fr.upmf.animaths.client;

import java.io.Serializable;
import java.util.Date;

import  fr.upmf.animaths.client.ServiceAsync;
import fr.upmf.animaths.client.mvp.MathObject.Equation;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *  The RPC api available to the client. The asynchronous version that is used
 * directly by the client is {@link ServiceAsync}.
 * @author Édouard Lopez
 *
 */
@RemoteServiceRelativePath("service")
public interface Service extends RemoteService {
	
	  Boolean saveExercice();
	  Equation loadExercice(String id);	  

//	  @SuppressWarnings("serial")
//	  static class Exercice implements Serializable {
//		  private String userName;
//		  private Date date;
//		  
//		  public Exercice() {
//			  
//		  }
//		  
//		  public Date getDate() {
//			return date;
//		}
//		public void setDate(Date date) {
//			this.date = date;
//		}
//		public void setUserName(String userName) {
//			this.userName = userName;
//		}		  
//		 public String getUserName() {
//			  return userName;
//		  }
//		  
//	  }

}
