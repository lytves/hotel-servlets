package controller.filters;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

/**
 * Session Bean implementation class FilterChain
 */
@Stateless
@LocalBean
public class FilterChain {
	/**
	 * Default constructor.
	 */
	public FilterChain() {
		// TODO Auto-generated constructor stub
	}

	private ArrayList<Filter> filters = new ArrayList<Filter>();

	public void addFilter(Filter filter) {
		filters.add(filter);
	}

	public boolean execute(HttpServletRequest request) {
		ArrayList<Boolean> filtersResults = new ArrayList<Boolean>();
		for (Filter filter : filters) {
			filtersResults.add(filter.execute(request));
		}
		return filtersResults.contains(false);
	}
}
