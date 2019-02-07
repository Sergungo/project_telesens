package ua.golenko.security.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.golenko.security.model.User;
import ua.golenko.security.request.UserRoleRequestWrapper;
import ua.golenko.security.utils.AppUtils;
import ua.golenko.security.utils.SecurityUtils;

@WebFilter("/*")
public class SecurityFilter implements Filter {

	public SecurityFilter() {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String servletPath = request.getServletPath();

		// ���������� ������������ ��������� � Session
		// (����� ��������� ����� � �������).
		User loginedUser = AppUtils.getLoginedUser(request.getSession());

		if (servletPath.equals("/login")) {
			chain.doFilter(request, response);
			return;
		}
		HttpServletRequest wrapRequest = request;

		if (loginedUser != null) {
			// User Name
			String username = loginedUser.getUsername();

			// ���� (Role).
			List<String> roles = loginedUser.getRoles();

			// ������ ����� request � ������� ������ Request � ����������� username � Roles.
			wrapRequest = new UserRoleRequestWrapper(username, roles, request);
		}

		// �������� ��������� ����� � �������.
		if (SecurityUtils.isSecurityPage(request)) {

			// ���� ������������ ��� �� ����� � �������,
			// Redirect (�������������) � �������� ������.
			if (loginedUser == null) {

				String requestUri = request.getRequestURI();

				// ��������� ������� �������� ��� ��������������� (redirect) ����� ���������
				// ����� � �������.
				int redirectId = AppUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);

				response.sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);
				return;
			}

			// ��������� ������������ ����� �������������� ���� ��� ���?
			boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
			if (!hasPermission) {

				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/accessDeniedView.jsp");

				dispatcher.forward(request, response);
				return;
			}
		}

		chain.doFilter(wrapRequest, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
