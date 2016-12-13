package com.jqg.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;

import com.jqg.session.factory.HibernateSessionFactory;

public class HibernateThreadFilter implements Filter {
	private SessionFactory sf = null;

	public void init(FilterConfig arg0) throws ServletException {

		sf = HibernateSessionFactory.getSessionFactory();
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getRequestURI().indexOf("borrow/toUpdateLssuing") == -1 &&
				req.getRequestURI().indexOf("loan/loantenderauditnotify") == -1
				) {
			try {

				sf.getCurrentSession().beginTransaction();

				chain.doFilter(request, response);

				sf.getCurrentSession().getTransaction().commit();

			} catch (StaleObjectStateException staleEx) {
				throw staleEx;
			} catch (Throwable ex) {
				ex.printStackTrace();
				try {
					// 如果发生异常，让事务回滚。
					if (sf.getCurrentSession().getTransaction().isActive()) {
						sf.getCurrentSession().getTransaction().rollback();
					}
				} catch (Throwable rbEx) {
					System.err.println(rbEx.toString());
				}
			} finally {
				sf.getCurrentSession().close();
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	public void destroy() {
	}

}
