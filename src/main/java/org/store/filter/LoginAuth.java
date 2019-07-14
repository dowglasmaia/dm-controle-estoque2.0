package org.store.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import org.store.entity.Usuario;

@WebFilter("/login.jsf")
public class LoginAuth implements Filter {

	public LoginAuth() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = (HttpSession) req.getSession();

		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

		if (usuario == null) {
			chain.doFilter(request, response);
		} else {
			res.sendRedirect(req.getContextPath() + "/pages/listagem.jsf");
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
