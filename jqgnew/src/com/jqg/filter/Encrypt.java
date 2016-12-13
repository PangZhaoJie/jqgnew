package com.jqg.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jqg.app.session.MySessionContext;

public class Encrypt implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		MySessionContext myc = MySessionContext.getInstance();
		String sessionId = request.getParameter("sessionId");
		boolean boo = true;
		String state = "";
		HttpServletRequest req = (HttpServletRequest) request;
		
		if (req.getRequestURI().indexOf("appjson/login") == -1
				&& req.getRequestURI().indexOf("appjson/register") == -1
				&& req.getRequestURI().indexOf("appjson/findPhotosByType") == -1
				&& req.getRequestURI().indexOf("appjson/captcha") == -1
						&& req.getRequestURI().indexOf("appjson/GetInvestmentList") == -1
						&& req.getRequestURI().indexOf("appjson/findPhotosByType") == -1
						&& req.getRequestURI().indexOf("appjson/GetActivity") == -1
								&& req.getRequestURI().indexOf("appjson/Recommend") == -1
				&& req.getRequestURI().indexOf("appjson/save") == -1
				&& req.getRequestURI().indexOf("appjson/sendCode") == -1
				&& req.getRequestURI().indexOf("appjson/updatePassword") == -1) {
			
			
			if (sessionId != null && !"".equals(sessionId)) {
				HttpSession sess = myc.getSession(sessionId);
				if (sess != null && !"".equals(sess)) {
					String key1 = (String) sess.getAttribute("key");
					String key = req.getParameter("key");
					System.err.println(key1);
					System.err.println(key);
					if (!key1.equals(key)) {
						state = "102";// 密钥不正确
						boo = false;
					}
				} else {
					state = "101";// session不存在
					boo = false;
				}
			} else {
				state = "100";// sessionId是空
				boo = false;
			}

		}
		if (boo) {
			chain.doFilter(request, response);
		} else {
			StringBuffer str = new StringBuffer();
			str.append("{");
			str.append("\"state\":\"" + state + "\"");
			str.append("}");
			response.setCharacterEncoding("utf-8");
			try {
				response.getWriter().print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
