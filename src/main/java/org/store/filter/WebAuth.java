package org.store.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import org.store.entity.Usuario;

@WebFilter("/pages/*")
public class WebAuth implements Filter {

	public WebAuth() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = (HttpSession) req.getSession();

		String url = req.getRequestURL().toString(); // pega a URL do Navegador

		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

		if (usuario == null) {
			res.sendRedirect(req.getContextPath() + "/login.xhtml");

		} else
			chain.doFilter(request, response);

	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
