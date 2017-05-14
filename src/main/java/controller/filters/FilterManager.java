package controller.filters;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

/**
 * Session Bean implementation class FilterManager
 */
@Stateless
@LocalBean
public class FilterManager {

	/**
	 * Default constructor.
	 */

	FilterChain filterChain;

	public FilterManager() {
		filterChain = new FilterChain();
	}

	public void setFilter(Filter filter) {
		filterChain.addFilter(filter);
	}

	public boolean  filterRequest(HttpServletRequest request) {
		return filterChain.execute(request);
	}
}
