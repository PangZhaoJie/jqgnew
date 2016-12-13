/**  
 * @Project: jqgP2P
 * @Title: InitFilter.java
 * @Package com.jqg.filter
 * @date 2015-2-9 下午2:03:23
 * @Copyright: 2015 
 */
package com.jqg.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 类名：InitFilter 功能： 详细： 作者：曹中德(caozhongde) 版本：1.0 日期：2015-2-9 下午2:03:23
 * 
 */
public class InitFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		

		request.setCharacterEncoding("UTF-8");	//设置请求编码“UTF-8”比较通用
		response.setCharacterEncoding("UTF-8");	//设置相应编码
		
		
        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse res=(HttpServletResponse)response;
         //获得所有请求参数名
        Enumeration params = req.getParameterNames();
        String sql = "";
        while (params.hasMoreElements()) {
            //得到参数名
            String name = params.nextElement().toString();
            //System.out.println("name===========================" + name + "--");
            //得到参数对应值
            String[] value = req.getParameterValues(name);
            for (int i = 0; i < value.length; i++) {
                sql = sql + value[i];
            }
        }
        //System.out.println("============================SQL"+sql);
        //有sql关键字，跳转到error.html
        if (sqlValidate(sql)) {
        	 res.sendRedirect(req.getContextPath()+"/sqlError.jsp");  
//            throw new IOException("您发送请求中的参数中含有非法字符");
        	 System.err.println("您发送请求中的参数中含有非法字符");
            //String ip = req.getRemoteAddr();
           
        } else{
		
		
		try {
			// 将字符串参数去掉空格
//			HttpServletRequest req = (HttpServletRequest) request;
			Map<String, Object> params2 = req.getParameterMap();
			for (String key : params2.keySet()) {
				Object value = params2.get(key);
				if (value instanceof String[]) {
					String[] o = (String[]) value;
					for (int i = 0; i < o.length; i++) {
//						System.out.println("==参数===" + key + "：" + o[i]);
					}
				}

			}
			chain.doFilter(request, response);

		} catch (Exception e) {
			e.printStackTrace();

		}
        }
	}
	 protected static boolean sqlValidate(String str) {
	        str = str.toLowerCase();//统一转为小写
//	        String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|" +
//	                "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|" +
//	                "table|from|grant|use|group_concat|column_name|" +
//	                "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|" +
//	                "chr|mid|master|truncate|char|declare|or|;|-|--|+|,|like|//|/|%|#";

	        String badStr = " '| and | exec | execute | insert | select | delete | update | count | drop | * | % | chr | mid | master | truncate |" +
	                " char | declare | sitename | net user | xp_cmdshell | ; | or | - | + | , | like' | and | exec | execute | insert | create | drop |" +
	                " table | from | grant | use | group_concat | column_name |" +
	                " information_schema.columns | table_schema | union | where | select | delete | update | order | by | count | * |" +
	                " chr | mid | master | truncate | char | declare | or | ; | - | -- | + | , | like | // | / | % | #|<script>";

	        String[] badStrs = badStr.split("\\|");
	        for (int i = 0; i < badStrs.length; i++) {
	        	
	            if (str.indexOf(badStrs[i]) >= 0) {
	            	System.err.println("SQL注入1："+str);
	            	System.err.println("SQL注入2："+badStrs[i]);
	                return true;
	            } 
	        }
	        return false;
	    }
}
