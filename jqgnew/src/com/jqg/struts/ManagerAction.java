package com.jqg.struts;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.jqg.pojo.Manager;
import com.jqg.pojo.Oerationlog;
import com.jqg.pojo.Power;
import com.jqg.pojo.Role;
import com.jqg.service.ManagerService;
import com.jqg.service.MenuService;
import com.jqg.service.OerationlogService;
import com.jqg.service.PowerService;
import com.jqg.service.RoleService;
import com.jqg.service.impl.ManagerServiceImpl;
import com.jqg.service.impl.MenuServiceImpl;
import com.jqg.service.impl.OerationlogServiceImpl;
import com.jqg.service.impl.PowerServiceImpl;
import com.jqg.service.impl.RoleServiceImpl;
import com.jqg.util.MD5Util;
import com.opensymphony.xwork2.ActionContext;

public class ManagerAction extends BaseAction implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private int currentPage;
	private int pageSize = 10;
	private int totalPage;
	private Integer managerId;
	private Role role;
	private String managerName;
	private String password;
	private String realName;
	private String qq;
	private String telephone;
	private Integer roleId;
	private Integer isBan;
	private Integer isCustomer;
	private String passwordWord;
	private String result;
	private String error;
	private String ip;
	private String mark="0";
	private ManagerService managerService = new ManagerServiceImpl();
	private OerationlogService oerationlogService = new OerationlogServiceImpl();
	private RoleService roleService = new RoleServiceImpl();
	// 权限
	private PowerService powerService = new PowerServiceImpl();
	private MenuService menuService = new MenuServiceImpl();
	Manager manager3 = (Manager) ActionContext.getContext().getSession()
			.get("manager");
	private static HttpServletRequest request;

	// 实现接口中的方法
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 添加管理员
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addmanager() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = request.getHeader("X-Forwarded-For"); 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_CLIENT_IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        } 
	     Role role1 = this.roleService.findRoleByRoleId(this.roleId);
	     Manager manager1 = this.managerService.findManagerByname(this.managerName);
	     if (manager1 == null)
	     {
	       this.result = "success";
	       Manager manager = new Manager();
	       manager.setManagerName(this.managerName);
	       manager.setPassword(MD5Util.md5(this.password));
	       manager.setPasswordWord(this.passwordWord);
	       manager.setRole(role1);
	 
	       manager.setRealName(this.realName);
	       manager.setQq(this.qq);
	       manager.setTelephone(this.telephone);
	       manager.setIsBan(Integer.valueOf(1));
	       manager.setIsCustomer(Integer.valueOf(0));
	       manager.setDisplay(Integer.valueOf(1));
	       boolean flag = this.managerService.addManager(manager);
	       Oerationlog oerationlog = new Oerationlog();
	       oerationlog.setManager(this.manager3);
	       oerationlog.setOerationCategory("addmanager");
	       oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
	       oerationlog.setOerationRemaks("添加管理员");
	       oerationlog.setOrationIp(ip);
	       this.oerationlogService.createOerationlog(oerationlog);
	     }
	     else {
	       this.result = "error";
	     }
	 
	     return "success";
	}

	/**
	 * 更新管理员
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updatemanager() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = request.getHeader("X-Forwarded-For"); 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_CLIENT_IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        } 
        
	     Role role1 = this.roleService.findRoleByRoleId(this.roleId);
	     Manager manager1 = this.managerService.findManagerByname(this.managerName);
	     Manager manager = this.managerService.findManagerByManagerId(this.managerId);
	     if (manager1 == null)
	     {
	       manager.setManagerName(this.managerName);
	       if (this.password.equals(manager.getPassword()))
	       {
	         manager.setPassword(this.password);
	       }
	       else
	       {
	         manager.setPassword(MD5Util.md5(this.password));
	       }
	       manager.setPasswordWord(this.passwordWord);
	       manager.setRole(role1);
	 
	       manager.setRealName(this.realName);
	       manager.setQq(this.qq);
	       manager.setTelephone(this.telephone);
	       manager.setIsBan(Integer.valueOf(1));
	       manager.setIsCustomer(Integer.valueOf(0));
	       boolean flag = this.managerService.updateManager(manager);
	       this.result = "success";
	     }
	     else if (manager.getManagerId() == this.managerId) {
	       manager.setManagerName(this.managerName);
	       if (this.password.equals(manager.getPassword()))
	       {
	         manager.setPassword(this.password);
	       }
	       else
	       {
	         manager.setPassword(MD5Util.md5(this.password));
	       }
	       manager.setPasswordWord(this.passwordWord);
	       manager.setRole(role1);
	 
	       manager.setRealName(this.realName);
	       manager.setQq(this.qq);
	       manager.setTelephone(this.telephone);
	       manager.setIsBan(Integer.valueOf(1));
	       manager.setIsCustomer(Integer.valueOf(0));
	       boolean flag = this.managerService.updateManager(manager);
	       this.result = "success";
	       Oerationlog oerationlog = new Oerationlog();
	       oerationlog.setManager(manager);
	       oerationlog.setOerationCategory("uptmanager");
	       oerationlog.setOerationChangeTime(new Timestamp(new Date().getTime()));
	       oerationlog.setOerationRemaks("更新管理员");
	       oerationlog.setOrationIp(ip);
	       this.oerationlogService.createOerationlog(oerationlog);
	     }
	     else
	     {
	       this.result = "error";
	     }
	 
	     return "success";
	}

	/**
	 * 删除管理员
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deletemanager() throws Exception {
		Manager manager = this.managerService
				.findManagerByManagerId(this.managerId);
			List<Oerationlog> list=this.oerationlogService.findByManager(manager.getManagerId());
//			for(Oerationlog  o:list){
//				this.oerationlogService.deleteOerationlog(o);
//			}
			if(list.size()>0){
				this.mark="1";
				return "success";
			}
			boolean flag = this.managerService.deleteManager(manager);
			if (flag) {
				Oerationlog oerationlog = new Oerationlog();
				oerationlog.setManager(this.manager3);
				oerationlog.setOerationCategory("dltmanager");
				oerationlog.setOerationChangeTime(new Timestamp(new Date()
						.getTime()));
				oerationlog.setOerationRemaks("删除管理员");
				oerationlog.setOrationIp(this.ip);
				this.oerationlogService.createOerationlog(oerationlog);
				this.result = "success";
			} else {
				
				this.result = "error";
			}

		return "success";
	}

	/**
	 * 查找所有管理员并分页
	 * 
	 * @throws Exception
	 */
	public void mangerlist() throws Exception {
		List managers = this.managerService.findManagers1(Integer.valueOf(0));
		int totalRecord = managers.size();
		if (totalRecord % this.pageSize == 0)
			this.totalPage = (totalRecord / this.pageSize);
		else {
			this.totalPage = (totalRecord / this.pageSize + 1);
		}
		if (this.currentPage >= this.totalPage) {
			this.currentPage = this.totalPage;
		}
		if (this.currentPage <= 1) {
			this.currentPage = 1;
		}
		List<Manager> managers2 = this.managerService.findManagerspage1(
				(this.currentPage - 1) * this.pageSize, this.pageSize,
				Integer.valueOf(0));

		StringBuffer str = new StringBuffer();
		str.append("{\"totalPage\":\"" + this.totalPage + "\",");
		str.append("\"currentPage\":\"" + this.currentPage + "\",");
		str.append("\"jsonRoot\":[");
		for (Manager manager : managers2) {
			List roless = this.roleService.findRoles();
			String strs = "";
			for (int i = 0; i < roless.size(); i++) {
				Role role1 = (Role) roless.get(i);
				strs = strs + "<option value='" + role1.getRoleId() + "'";
				if (role1.getRoleId() == manager.getRole().getRoleId()) {
					strs = strs + "  selected='selected'";
				}
				strs = strs + ">" + role1.getRoleName() + "</option>";
			}

			str.append("{\"id\":\"" + manager.getManagerId() + "\",");
			str.append("\"strs\":\"" + strs + "\",");
			str.append("\"name\":\"" + manager.getManagerName() + "\",");
			str.append("\"password\":\"" + manager.getPasswordWord() + "\",");
			str.append("\"rid\":\"" + manager.getRole().getRoleId() + "\",");
			str.append("\"pwd\":\"" + manager.getPassword() + "\",");

			str.append("\"realName\":\"" + manager.getRealName() + "\",");
			str.append("\"qq\":\"" + manager.getQq() + "\",");
			str.append("\"telephone\":\"" + manager.getTelephone() + "\",");
			str.append("\"rolename\":\"" + manager.getRole().getRoleName()
					+ "\"},");
		}

		str.deleteCharAt(str.lastIndexOf(","));
		str.append("]}");

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String out() throws Exception {
		ActionContext.getContext().getSession().remove("manager");
		return "success";
	}

	public String login() throws Exception {

		String path = request.getContextPath();
		String address = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		ActionContext.getContext().getSession().put("address", address);

		if ((this.password == null) || (this.managerName == null)
				|| (this.passwordWord == null)) {
			return "error";
		}
		String md5 = MD5Util.md5(this.password);
		Manager manager = this.managerService.findManagerByLogin(
				this.managerName, md5, this.passwordWord);
		if (manager != null) {
			ActionContext.getContext().getSession().put("manager", manager);

			Integer roleId = manager.getRole().getRoleId();
			Integer[] powers = new Integer[150];
			List powers1 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(1), roleId);
			powers[0] = ((Power) powers1.get(0)).getPshow();
			powers[1] = ((Power) powers1.get(0)).getPupdate();
			List powers2 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(2), roleId);
			powers[2] = ((Power) powers2.get(0)).getPshow();
			powers[3] = ((Power) powers2.get(0)).getPadd();
			powers[4] = ((Power) powers2.get(0)).getPdelete();
			powers[5] = ((Power) powers2.get(0)).getPupdate();
			List powers3 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(3), roleId);
			powers[6] = ((Power) powers3.get(0)).getPshow();
			powers[7] = ((Power) powers3.get(0)).getPquery();

			List powers4 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(4), roleId);
			powers[8] = ((Power) powers4.get(0)).getPshow();
			powers[9] = ((Power) powers4.get(0)).getPupdate();
			powers[89] = ((Power) powers4.get(0)).getPquery();
			List powers5 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(5), roleId);
			powers[10] = ((Power) powers5.get(0)).getPshow();
			powers[11] = ((Power) powers5.get(0)).getPupdate();
			powers[90] = ((Power) powers5.get(0)).getPquery();
			List powers6 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(6), roleId);
			powers[12] = ((Power) powers6.get(0)).getPshow();
			powers[13] = ((Power) powers6.get(0)).getPupdate();
			powers[91] = ((Power) powers6.get(0)).getPquery();
			List powers7 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(7), roleId);
			powers[14] = ((Power) powers7.get(0)).getPshow();
			powers[15] = ((Power) powers7.get(0)).getInvestNotes();
			powers[92] = ((Power) powers7.get(0)).getPquery();
			powers[93] = ((Power) powers7.get(0)).getPupdate();
			List powers8 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(8), roleId);
			powers[16] = ((Power) powers8.get(0)).getPshow();
			powers[94] = ((Power) powers8.get(0)).getPquery();
			powers[95] = ((Power) powers8.get(0)).getInvestNotes();
			List powers9 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(9), roleId);
			powers[17] = ((Power) powers9.get(0)).getPshow();
			powers[96] = ((Power) powers9.get(0)).getPquery();
			List powers10 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(10), roleId);
			powers[18] = ((Power) powers10.get(0)).getPshow();
			powers[97] = ((Power) powers10.get(0)).getPquery();
			List powers11 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(11), roleId);
			powers[19] = ((Power) powers11.get(0)).getPshow();
			powers[98] = ((Power) powers11.get(0)).getPquery();
			powers[117] = ((Power) powers11.get(0)).getPupdate();
			List powers12 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(12), roleId);
			powers[20] = ((Power) powers12.get(0)).getPshow();
			powers[21] = ((Power) powers12.get(0)).getUpdateBalance();
			powers[22] = ((Power) powers12.get(0)).getUpdateCredit();
			powers[23] = ((Power) powers12.get(0)).getPupdate();
			powers[24] = ((Power) powers12.get(0)).getPquery();
			List powers13 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(13), roleId);
			powers[25] = ((Power) powers13.get(0)).getPshow();
			powers[26] = ((Power) powers13.get(0)).getPupdate();
			List powers14 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(14), roleId);
			powers[27] = ((Power) powers14.get(0)).getPshow();
			powers[28] = ((Power) powers14.get(0)).getPupdate();
			List powers15 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(15), roleId);
			powers[29] = ((Power) powers15.get(0)).getPshow();
			powers[30] = ((Power) powers15.get(0)).getPupdate();
			List powers16 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(16), roleId);
			powers[31] = ((Power) powers16.get(0)).getPshow();
			powers[32] = ((Power) powers16.get(0)).getPupdate();
			List powers17 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(17), roleId);
			powers[33] = ((Power) powers17.get(0)).getPshow();
			powers[34] = ((Power) powers17.get(0)).getPupdate();
			List powers18 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(18), roleId);
			powers[35] = ((Power) powers18.get(0)).getPshow();
			powers[36] = ((Power) powers18.get(0)).getPupdate();

			List powers19 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(19), roleId);
			powers[37] = ((Power) powers19.get(0)).getPshow();
			powers[38] = ((Power) powers19.get(0)).getPupdate();
			List powers20 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(20), roleId);
			powers[39] = ((Power) powers20.get(0)).getPshow();
			powers[40] = ((Power) powers20.get(0)).getPupdate();

			List powers21 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(21), roleId);
			powers[41] = ((Power) powers21.get(0)).getPshow();
			powers[42] = ((Power) powers21.get(0)).getPadd();
			powers[43] = ((Power) powers21.get(0)).getPdelete();
			powers[44] = ((Power) powers21.get(0)).getPupdate();
			powers[86] = ((Power) powers21.get(0)).getPquery();

			List powers22 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(22), roleId);
			powers[45] = ((Power) powers22.get(0)).getPshow();
			powers[46] = ((Power) powers22.get(0)).getPupdate();
			powers[87] = ((Power) powers22.get(0)).getPadd();
			powers[88] = ((Power) powers22.get(0)).getPdelete();

			List powers23 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(23), roleId);
			powers[47] = ((Power) powers23.get(0)).getPshow();
			powers[48] = ((Power) powers23.get(0)).getLeadingOut();
			powers[99] = ((Power) powers23.get(0)).getPquery();
			List powers24 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(24), roleId);
			powers[49] = ((Power) powers24.get(0)).getPshow();
			powers[50] = ((Power) powers24.get(0)).getLeadingOut();
			powers[100] = ((Power) powers24.get(0)).getPquery();
			List powers25 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(25), roleId);
			powers[51] = ((Power) powers25.get(0)).getPshow();
			powers[52] = ((Power) powers25.get(0)).getLeadingOut();
			powers[101] = ((Power) powers25.get(0)).getPquery();
			List powers26 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(26), roleId);
			powers[53] = ((Power) powers26.get(0)).getPshow();
			powers[54] = ((Power) powers26.get(0)).getLeadingOut();
			powers[102] = ((Power) powers26.get(0)).getPquery();
			List powers27 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(27), roleId);
			powers[55] = ((Power) powers27.get(0)).getPshow();
			powers[103] = ((Power) powers27.get(0)).getPquery();

			List powers29 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(29), roleId);
			powers[56] = ((Power) powers29.get(0)).getPshow();
			powers[57] = ((Power) powers29.get(0)).getPadd();
			powers[58] = ((Power) powers29.get(0)).getPupdate();

			List powers28 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(28), roleId);
			powers[59] = ((Power) powers28.get(0)).getPshow();
			powers[60] = ((Power) powers28.get(0)).getPadd();
			powers[61] = ((Power) powers28.get(0)).getPdelete();
			powers[62] = ((Power) powers28.get(0)).getPupdate();

			List powers30 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(30), roleId);
			powers[63] = ((Power) powers30.get(0)).getPshow();
			powers[64] = ((Power) powers30.get(0)).getPupdate();
			List powers31 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(31), roleId);
			powers[65] = ((Power) powers31.get(0)).getPshow();
			powers[66] = ((Power) powers31.get(0)).getPadd();
			powers[67] = ((Power) powers31.get(0)).getPupdate();
			List powers32 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(32), roleId);
			powers[68] = ((Power) powers32.get(0)).getPshow();
			powers[69] = ((Power) powers32.get(0)).getPadd();
			powers[70] = ((Power) powers32.get(0)).getPupdate();
			List powers33 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(33), roleId);
			powers[71] = ((Power) powers33.get(0)).getPshow();
			powers[72] = ((Power) powers33.get(0)).getPadd();
			powers[73] = ((Power) powers33.get(0)).getPupdate();
			List powers34 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(34), roleId);
			powers[74] = ((Power) powers34.get(0)).getPshow();
			powers[75] = ((Power) powers34.get(0)).getPadd();
			powers[76] = ((Power) powers34.get(0)).getPdelete();
			powers[124] = ((Power)powers34.get(0)).getPupdate();
			List powers35 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(35), roleId);
			powers[77] = ((Power) powers35.get(0)).getPshow();
			powers[78] = ((Power) powers35.get(0)).getPadd();
			powers[79] = ((Power) powers35.get(0)).getPupdate();
			powers[121]=((Power) powers35.get(0)).getPdelete();
			List powers36 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(36), roleId);
			powers[80] = ((Power) powers36.get(0)).getPshow();
			powers[81] = ((Power) powers36.get(0)).getPupdate();
			List powers37 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(37), roleId);
			powers[82] = ((Power) powers37.get(0)).getPshow();
			powers[83] = ((Power) powers37.get(0)).getPupdate();
			List powers38 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(38), roleId);
			powers[84] = ((Power) powers38.get(0)).getPshow();
			powers[85] = ((Power) powers38.get(0)).getPupdate();

			List powers39 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(39), roleId);
			if ((powers39 == null) || (powers39.size() == 0)) {
				Power power = new Power();
				power.setRole(manager.getRole());
				power.setMenu(this.menuService.findMenuByMenuId(Integer
						.valueOf(39)));
				this.powerService.addPower(power);
				powers[104] = Integer.valueOf(0);
				powers[105] = Integer.valueOf(0);
				powers[106] = Integer.valueOf(0);
			} else {
				powers[104] = ((Power) powers39.get(0)).getPadd();
				powers[105] = ((Power) powers39.get(0)).getPupdate();
				powers[106] = ((Power) powers39.get(0)).getPdelete();
			}

			List powers40 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(40), roleId);
			if ((powers40 == null) || (powers40.size() == 0)) {
				Power power = new Power();
				power.setRole(manager.getRole());
				power.setMenu(this.menuService.findMenuByMenuId(Integer
						.valueOf(40)));
				this.powerService.addPower(power);
				powers[107] = Integer.valueOf(0);
			} else {
				powers[107] = ((Power) powers40.get(0)).getPupdate();
			}

			List powers41 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(41), roleId);
			if ((powers41 == null) || (powers41.size() == 0)) {
				Power power = new Power();
				power.setRole(manager.getRole());
				power.setMenu(this.menuService.findMenuByMenuId(Integer
						.valueOf(41)));
				this.powerService.addPower(power);
				powers[108] = Integer.valueOf(0);
				powers[109] = Integer.valueOf(0);
			} else {
				powers[108] = ((Power) powers41.get(0)).getPquery();
				powers[109] = ((Power) powers41.get(0)).getLeadingOut();
			}

			List powers42 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(42), roleId);
			if ((powers42 == null) || (powers42.size() == 0)) {
				Power power = new Power();
				power.setRole(manager.getRole());
				power.setMenu(this.menuService.findMenuByMenuId(Integer
						.valueOf(42)));
				this.powerService.addPower(power);
				powers[110] = Integer.valueOf(0);
			} else {
				powers[110] = ((Power) powers42.get(0)).getPupdate();
			}

			List powers43 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(43), roleId);
			if ((powers43 == null) || (powers43.size() == 0)) {
				Power power = new Power();
				power.setRole(manager.getRole());
				power.setMenu(this.menuService.findMenuByMenuId(Integer
						.valueOf(43)));
				this.powerService.addPower(power);
				powers[111] = Integer.valueOf(0);
			} else {
				powers[111] = ((Power) powers43.get(0)).getPupdate();
			}

			List powers44 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(44), roleId);
			if ((powers44 == null) || (powers44.size() == 0)) {
				Power power = new Power();
				power.setRole(manager.getRole());
				power.setMenu(this.menuService.findMenuByMenuId(Integer
						.valueOf(44)));
				this.powerService.addPower(power);
				powers[112] = Integer.valueOf(0);
				powers[113] = Integer.valueOf(0);
				powers[114] = Integer.valueOf(0);
			} else {
				powers[112] = ((Power) powers44.get(0)).getPadd();
				powers[113] = ((Power) powers44.get(0)).getPupdate();
				powers[114] = ((Power) powers44.get(0)).getPdelete();
			}

			List powers45 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(45), roleId);
			if ((powers45 == null) || (powers45.size() == 0)) {
				Power power = new Power();
				power.setRole(manager.getRole());
				power.setMenu(this.menuService.findMenuByMenuId(Integer
						.valueOf(45)));
				this.powerService.addPower(power);
				powers[115] = Integer.valueOf(0);
			} else {
				powers[115] = ((Power) powers45.get(0)).getPupdate();
			}

			List powers46 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(46), roleId);
			if ((powers46 == null) || (powers46.size() == 0)) {
				Power power = new Power();
				power.setRole(manager.getRole());
				power.setMenu(this.menuService.findMenuByMenuId(Integer
						.valueOf(46)));
				this.powerService.addPower(power);
				powers[118] = Integer.valueOf(0);
			} else {
				powers[118] = ((Power) powers46.get(0)).getPupdate();
			}

			List powers47 = this.powerService.findPowersByMeuAndRole(
					Integer.valueOf(47), roleId);
			if ((powers47 == null) || (powers47.size() == 0)) {
				Power power = new Power();
				power.setRole(manager.getRole());
				power.setMenu(this.menuService.findMenuByMenuId(Integer
						.valueOf(47)));
				this.powerService.addPower(power);
				powers[119] = Integer.valueOf(0);
			} else {
				powers[119] = ((Power) powers47.get(0)).getPupdate();
			}

			 List powers50 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(50), roleId);
		     powers[125] = ((Power)powers50.get(0)).getPshow();
		     powers[126] = ((Power)powers50.get(0)).getPadd();
		     powers[127] = ((Power)powers50.get(0)).getPdelete();
		     powers[128] = ((Power)powers50.get(0)).getPupdate();
		     
		     List powers51 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(51), roleId);
		     powers[129] = ((Power)powers51.get(0)).getPshow();
		     powers[130] = ((Power)powers51.get(0)).getPadd();
		     powers[131] = ((Power)powers51.get(0)).getPdelete();
		     powers[132] = ((Power)powers51.get(0)).getPupdate();
			
			
			ActionContext.getContext().getSession().put("powerss", powers);

			Oerationlog oerationlog = new Oerationlog();
			oerationlog.setManager(manager);
			oerationlog.setOerationCategory("login");
			oerationlog.setOerationChangeTime(new Timestamp(new Date()
					.getTime()));
			InetAddress in = InetAddress.getLocalHost();
			oerationlog.setOerationRemaks("管理员登录成功");
			oerationlog.setOrationIp(in.getHostAddress().toString());
			this.oerationlogService.createOerationlog(oerationlog);
			this.result = "success";
		} else {
			this.error = "输入有误，登录失败！";
			this.result = "error";
		}
		return this.result;
	}
	/**
	 * 查询客服信息
	 * @return
	 */
	public String findKefu() throws Exception{
		List<Manager> managers =managerService.findManagerByIsCustomer();
		ActionContext.getContext().put("managers", managers);
		return "register1";
	}
	public Integer getManagerId() {
		return this.managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getManagerName() {
		return this.managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getIsBan() {
		return this.isBan;
	}

	public void setIsBan(Integer isBan) {
		this.isBan = isBan;
	}

	public Integer getIsCustomer() {
		return this.isCustomer;
	}

	public void setIsCustomer(Integer isCustomer) {
		this.isCustomer = isCustomer;
	}

	public String getPasswordWord() {
		return this.passwordWord;
	}

	public void setPasswordWord(String passwordWord) {
		this.passwordWord = passwordWord;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getError() {
		return this.error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return this.totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
	
}