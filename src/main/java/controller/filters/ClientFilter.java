package controller.filters;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

/**
 * Session Bean implementation class Client
 */
@Stateless
@LocalBean
public class ClientFilter {

	FilterManager filterManager;

	   public void setFilterManager(FilterManager filterManager){
	      this.filterManager = filterManager;
	   }

	   public boolean sendRequest(HttpServletRequest request){
	      return filterManager.filterRequest(request);
	   }
}
