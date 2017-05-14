package controller.filters;

import javax.servlet.http.HttpServletRequest;

public interface Filter {
	public boolean execute(HttpServletRequest request);
}
