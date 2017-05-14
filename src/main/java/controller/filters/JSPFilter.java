package controller.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FilterJsp
 */
@WebFilter("/JSPFilter")
public class JSPFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public JSPFilter() {}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		System.out.println("JSPFilter control!");
		//System.out.println("req.getServletPath()" + req.getServletPath());
		
		String urlServlet = req.getServletPath();
		if (("/loggin.jsp").equals(urlServlet) || ("/error.jsp").equals(urlServlet))
			// pass the request along the filter chain
			chain.doFilter(request, response);
		else
			res.sendRedirect("error.jsp");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {}
}
