package com.jqg.struts;

import java.sql.Timestamp;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.jqg.pojo.AppPhotos;
import com.jqg.pojo.Manager;
import com.jqg.service.AppPhotosService;
import com.jqg.service.impl.AppPhotosServiceImpl;
import com.opensymphony.xwork2.ActionContext;

public class PhotoAction extends BaseAction{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 4956366152598294946L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private int currentPage;//当前页
    private int pageSize = 10;//每页10条数据
    private int totalPage;//总页数
    private int total;//总条数
    private String result;
    private AppPhotosService appPhotosService = new AppPhotosServiceImpl();
    Manager manager = (Manager)ActionContext.getContext().getSession().get("manager");
    /**
     * 查找app图片列表
     * */
	public  String findPhotos() throws Exception{
		List<AppPhotos> appphotos = this.appPhotosService.findPhotos();
		this.total = appphotos.size();
		if(this.total % this.pageSize==0){
			this.totalPage=(this.total / this.pageSize);
		}else{
			this.totalPage = (this.total / this.pageSize+1);
		}
		if(this.currentPage >= this.totalPage){
			this.currentPage = this.totalPage;
		}
		if(this.currentPage <=1){
			this.currentPage = 1 ;
		}
		List<AppPhotos> apppage = this.appPhotosService.findPhotosByPage((this.currentPage - 1) * this.pageSize, this.pageSize);
		ActionContext.getContext().put("apppage", apppage);
		return "success";
	}
	
	/**
	 * 添加app图片
	 * */
	public String addAppPhoto() throws Exception{
		this.request = ServletActionContext.getRequest();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String userName = manager.getManagerName();
		String title = request.getParameter("title");
		String type = request.getParameter("type");
		String pathImages = request.getParameter("photo");
		String url = request.getParameter("url");
		String content = request.getParameter("content");
		String content1 = content.replace("%26", "&");
		AppPhotos appPhoto = new AppPhotos();
		appPhoto.setUserName(userName);
		appPhoto.setTitle(title);
		appPhoto.setType(Integer.valueOf(type));
		if(pathImages==null || "".equals(pathImages)){
			appPhoto.setPathImages("0");
		}else{
			appPhoto.setPathImages("/images/new/"+pathImages);
		}
		appPhoto.setUrl(url);
		appPhoto.setContent(content1);
		appPhoto.setPublishTime(timestamp);
		boolean boo = this.appPhotosService.addAppPhotos(appPhoto);
		if(boo){
			this.result = "success";
		}else{
			this.result = "error"; 
		}
		return "success";
	}
	/**
	 * 根据id查找
	 * */
	public String findPhotoByAppId()throws Exception{
		this.request = ServletActionContext.getRequest();
		String appId = request.getParameter("appId");
		AppPhotos appPhoto = this.appPhotosService.findPhotoByAppId(Integer.valueOf(appId));
		ActionContext.getContext().put("appPhoto", appPhoto);
		return "success";
	}
	/**
	 * 更新app图片
	 * */
	public String updatePhoto()throws Exception{
		this.request = ServletActionContext.getRequest();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String appId = request.getParameter("appId");
		String title = request.getParameter("title");
		String type = request.getParameter("type");
		String pathImages = request.getParameter("photo");
		String url = request.getParameter("url");
		String content = request.getParameter("content");
		String content1 = content.replace("%26", "&");
		AppPhotos appPhoto = this.appPhotosService.findPhotoByAppId(Integer.valueOf(appId));
		appPhoto.setTitle(title);
		appPhoto.setType(Integer.valueOf(type));
		if(pathImages==null || "".equals(pathImages)){
			appPhoto.setPathImages("0");
		}else{
			appPhoto.setPathImages("/images/new/"+pathImages);
		}
		appPhoto.setUrl(url);
		appPhoto.setContent(content1);
		appPhoto.setPublishTime(timestamp);
		boolean boo = this.appPhotosService.updatePhoto(appPhoto);
		if(boo){
			this.result = "success";
		}else{
			this.result = "error";
		}
		return "success";
	}
	/**
	 * 删除app图片
	 * */
	public String deletePhotoByApp() throws Exception{
		this.request = ServletActionContext.getRequest();
		String appId = request.getParameter("appId");
		AppPhotos appPhoto = this.appPhotosService.findPhotoByAppId(Integer.valueOf(appId));
		boolean boo = this.appPhotosService.deletePhotoByApp(appPhoto);
		if(boo){
			return "success";
		}
		return "error";
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
