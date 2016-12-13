 package com.jqg.struts;
 
 import com.opensymphony.xwork2.ActionSupport;
 import com.jqg.pojo.Power;
 import com.jqg.pojo.Role;
 import com.jqg.service.ManagerService;
 import com.jqg.service.MenuService;
 import com.jqg.service.PowerService;
 import com.jqg.service.RoleService;
 import com.jqg.service.impl.ManagerServiceImpl;
 import com.jqg.service.impl.MenuServiceImpl;
 import com.jqg.service.impl.PowerServiceImpl;
 import com.jqg.service.impl.RoleServiceImpl;
 import java.util.List;
 
 public class PowerAction extends ActionSupport
 {
   private RoleService roleService = new RoleServiceImpl();
   private PowerService powerService = new PowerServiceImpl();
   private MenuService menuService = new MenuServiceImpl();
   private ManagerService managerService = new ManagerServiceImpl();
   private List<Role> roles;
   private String roleName;
   private Integer roleId;
   private String globals;
   private String borrow;
   private String member;
   private String paylog;
   private String article;
   private String navigation;
   private String capitalaccount;
   private String acl;
   private String adminuser;
   private String mfield;
   private Integer[] powers = new Integer[150];
   private String result;
   private String mark;
 
   public String searchRoles()
     throws Exception
   {
     this.roles = this.roleService.findRoles();
     return "success";
   }
   public String savePowers() throws Exception {
     if (this.roleId != null) {
       Role role = this.roleService.findRoleByRoleId(this.roleId);
       role.setRoleName(this.roleName);
       this.roleService.updateRole(role);
       String[] global = this.globals.split(",");
 
       List powers1 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(1), this.roleId);
       Power power11 = (Power)powers1.get(0);
       power11.setPshow(Integer.valueOf(Integer.parseInt(global[0])));
       power11.setPupdate(Integer.valueOf(Integer.parseInt(global[1])));
       this.powerService.updatePower(power11);
 
       List powers2 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(2), this.roleId);
       Power power12 = (Power)powers2.get(0);
       power12.setPshow(Integer.valueOf(Integer.parseInt(global[2])));
       power12.setPadd(Integer.valueOf(Integer.parseInt(global[3])));
       power12.setPdelete(Integer.valueOf(Integer.parseInt(global[4])));
       power12.setPupdate(Integer.valueOf(Integer.parseInt(global[5])));
       this.powerService.updatePower(power12);
 
       List powers45 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(45), this.roleId);
       Power power14 = (Power)powers45.get(0);
       power14.setPupdate(Integer.valueOf(Integer.parseInt(global[6])));
       this.powerService.updatePower(power14);
 
       List powers3 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(3), this.roleId);
       Power power13 = (Power)powers3.get(0);
       power13.setPshow(Integer.valueOf(Integer.parseInt(global[7])));
       power13.setPquery(Integer.valueOf(Integer.parseInt(global[8])));
       this.powerService.updatePower(power13);
       String[] borrows = this.borrow.split(",");
 
       List powers4 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(4), this.roleId);
       Power power21 = (Power)powers4.get(0);
       power21.setPshow(Integer.valueOf(Integer.parseInt(borrows[0])));
       power21.setPupdate(Integer.valueOf(Integer.parseInt(borrows[1])));
       power21.setPquery(Integer.valueOf(Integer.parseInt(borrows[2])));
       this.powerService.updatePower(power21);
 
       List powers5 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(5), this.roleId);
       Power power22 = (Power)powers5.get(0);
       power22.setPshow(Integer.valueOf(Integer.parseInt(borrows[3])));
       power22.setPupdate(Integer.valueOf(Integer.parseInt(borrows[4])));
       power22.setPquery(Integer.valueOf(Integer.parseInt(borrows[5])));
       this.powerService.updatePower(power22);
 
       List powers6 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(6), this.roleId);
       Power power23 = (Power)powers6.get(0);
       power23.setPshow(Integer.valueOf(Integer.parseInt(borrows[6])));
       power23.setPupdate(Integer.valueOf(Integer.parseInt(borrows[7])));
       power23.setPquery(Integer.valueOf(Integer.parseInt(borrows[8])));
       this.powerService.updatePower(power23);
 
       List powers7 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(7), this.roleId);
       Power power24 = (Power)powers7.get(0);
       power24.setPshow(Integer.valueOf(Integer.parseInt(borrows[9])));
       power24.setPupdate(Integer.valueOf(Integer.parseInt(borrows[10])));
       power24.setPquery(Integer.valueOf(Integer.parseInt(borrows[11])));
       power24.setInvestNotes(Integer.valueOf(Integer.parseInt(borrows[12])));
       power24.setWeekMark(Integer.valueOf(Integer.parseInt(borrows[13])));
       this.powerService.updatePower(power24);
 
       List powers8 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(8), this.roleId);
       Power power25 = (Power)powers8.get(0);
       power25.setPshow(Integer.valueOf(Integer.parseInt(borrows[14])));
       power25.setPquery(Integer.valueOf(Integer.parseInt(borrows[15])));
       power25.setInvestNotes(Integer.valueOf(Integer.parseInt(borrows[16])));
       this.powerService.updatePower(power25);
 
       List powers9 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(9), this.roleId);
       Power power26 = (Power)powers9.get(0);
       power26.setPshow(Integer.valueOf(Integer.parseInt(borrows[17])));
       power26.setPquery(Integer.valueOf(Integer.parseInt(borrows[18])));
       this.powerService.updatePower(power26);
 
       List powers10 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(10), this.roleId);
       Power power27 = (Power)powers10.get(0);
       power27.setPshow(Integer.valueOf(Integer.parseInt(borrows[19])));
       power27.setPquery(Integer.valueOf(Integer.parseInt(borrows[20])));
       this.powerService.updatePower(power27);
 
       List powers11 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(11), this.roleId);
       Power power28 = (Power)powers11.get(0);
       power28.setPshow(Integer.valueOf(Integer.parseInt(borrows[21])));
       power28.setPquery(Integer.valueOf(Integer.parseInt(borrows[22])));
       power28.setPupdate(Integer.valueOf(Integer.parseInt(borrows[23])));
       this.powerService.updatePower(power28);
 
       String[] members = this.member.split(",");
 
       List powers12 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(12), this.roleId);
       Power power31 = (Power)powers12.get(0);
       power31.setPshow(Integer.valueOf(Integer.parseInt(members[0])));
       power31.setUpdateBalance(Integer.valueOf(Integer.parseInt(members[1])));
       power31.setUpdateCredit(Integer.valueOf(Integer.parseInt(members[2])));
       power31.setPupdate(Integer.valueOf(Integer.parseInt(members[3])));
       power31.setPquery(Integer.valueOf(Integer.parseInt(members[4])));
       this.powerService.updatePower(power31);
 
       List powers13 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(13), this.roleId);
       Power power32 = (Power)powers13.get(0);
       power32.setPshow(Integer.valueOf(Integer.parseInt(members[5])));
       power32.setPupdate(Integer.valueOf(Integer.parseInt(members[6])));
       this.powerService.updatePower(power32);
 
       List powers14 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(14), this.roleId);
       Power power33 = (Power)powers14.get(0);
       power33.setPshow(Integer.valueOf(Integer.parseInt(members[7])));
       power33.setPupdate(Integer.valueOf(Integer.parseInt(members[8])));
       this.powerService.updatePower(power33);
 
       List powers15 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(15), this.roleId);
       Power power34 = (Power)powers15.get(0);
       power34.setPshow(Integer.valueOf(Integer.parseInt(members[9])));
       power34.setPupdate(Integer.valueOf(Integer.parseInt(members[10])));
       this.powerService.updatePower(power34);
 
       List powers16 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(16), this.roleId);
       Power power35 = (Power)powers16.get(0);
       power35.setPshow(Integer.valueOf(Integer.parseInt(members[11])));
       power35.setPupdate(Integer.valueOf(Integer.parseInt(members[12])));
       this.powerService.updatePower(power35);
 
       List powers17 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(17), this.roleId);
       Power power36 = (Power)powers17.get(0);
       power36.setPshow(Integer.valueOf(Integer.parseInt(members[13])));
       power36.setPupdate(Integer.valueOf(Integer.parseInt(members[14])));
       this.powerService.updatePower(power36);
 
       List powers18 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(18), this.roleId);
       Power power37 = (Power)powers18.get(0);
       power37.setPshow(Integer.valueOf(Integer.parseInt(members[15])));
       power37.setPupdate(Integer.valueOf(Integer.parseInt(members[16])));
       this.powerService.updatePower(power37);
 
       List powers46 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(46), this.roleId);
       Power power38 = (Power)powers46.get(0);
       power38.setPupdate(Integer.valueOf(Integer.parseInt(members[17])));
       this.powerService.updatePower(power38);
 
       String[] paylogs = this.paylog.split(",");
 
       List powers19 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(19), this.roleId);
       Power power41 = (Power)powers19.get(0);
       power41.setPshow(Integer.valueOf(Integer.parseInt(paylogs[0])));
       power41.setPupdate(Integer.valueOf(Integer.parseInt(paylogs[1])));
       this.powerService.updatePower(power41);
 
       List powers20 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(20), this.roleId);
       Power power42 = (Power)powers20.get(0);
       power42.setPshow(Integer.valueOf(Integer.parseInt(paylogs[2])));
       power42.setPupdate(Integer.valueOf(Integer.parseInt(paylogs[3])));
       this.powerService.updatePower(power42);
       
      
       
 
       String[] articles = this.article.split(",");
 
       List powers21 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(21), this.roleId);
       Power power51 = (Power)powers21.get(0);
       power51.setPshow(Integer.valueOf(Integer.parseInt(articles[0])));
       power51.setPadd(Integer.valueOf(Integer.parseInt(articles[1])));
       power51.setPdelete(Integer.valueOf(Integer.parseInt(articles[2])));
       power51.setPupdate(Integer.valueOf(Integer.parseInt(articles[3])));
       power51.setPquery(Integer.valueOf(Integer.parseInt(articles[4])));
       this.powerService.updatePower(power51);
 
       String[] navigations = this.navigation.split(",");
 
       List powers22 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(22), this.roleId);
       Power power61 = (Power)powers22.get(0);
       power61.setPshow(Integer.valueOf(Integer.parseInt(navigations[0])));
       power61.setPupdate(Integer.valueOf(Integer.parseInt(navigations[1])));
       power61.setPadd(Integer.valueOf(Integer.parseInt(navigations[2])));
       power61.setPdelete(Integer.valueOf(Integer.parseInt(navigations[3])));
       this.powerService.updatePower(power61);
 
       String[] capitalaccounts = this.capitalaccount.split(",");
 
       List powers23 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(23), this.roleId);
       Power power71 = (Power)powers23.get(0);
       power71.setPshow(Integer.valueOf(Integer.parseInt(capitalaccounts[0])));
       power71.setLeadingOut(Integer.valueOf(Integer.parseInt(capitalaccounts[1])));
       power71.setPquery(Integer.valueOf(Integer.parseInt(capitalaccounts[2])));
       this.powerService.updatePower(power71);
 
       List powers24 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(24), this.roleId);
       Power power72 = (Power)powers24.get(0);
       power72.setPshow(Integer.valueOf(Integer.parseInt(capitalaccounts[3])));
       power72.setLeadingOut(Integer.valueOf(Integer.parseInt(capitalaccounts[4])));
       power72.setPquery(Integer.valueOf(Integer.parseInt(capitalaccounts[5])));
       this.powerService.updatePower(power72);
 
       List powers25 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(25), this.roleId);
       Power power73 = (Power)powers25.get(0);
       power73.setPshow(Integer.valueOf(Integer.parseInt(capitalaccounts[6])));
       power73.setLeadingOut(Integer.valueOf(Integer.parseInt(capitalaccounts[7])));
       power73.setPquery(Integer.valueOf(Integer.parseInt(capitalaccounts[8])));
       this.powerService.updatePower(power73);
 
       List powers26 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(26), this.roleId);
       Power power74 = (Power)powers26.get(0);
       power74.setPshow(Integer.valueOf(Integer.parseInt(capitalaccounts[9])));
       power74.setLeadingOut(Integer.valueOf(Integer.parseInt(capitalaccounts[10])));
       power74.setPquery(Integer.valueOf(Integer.parseInt(capitalaccounts[11])));
       this.powerService.updatePower(power74);
 
       List powers27 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(27), this.roleId);
       Power power75 = (Power)powers27.get(0);
       power75.setPshow(Integer.valueOf(Integer.parseInt(capitalaccounts[12])));
       power75.setPquery(Integer.valueOf(Integer.parseInt(capitalaccounts[13])));
       this.powerService.updatePower(power75);
 
       List powers49 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(49), this.roleId);
       Power power118 = (Power)powers49.get(0);
       power118.setPshow(Integer.valueOf(Integer.parseInt(capitalaccounts[14])));
       power118.setPquery(Integer.valueOf(Integer.parseInt(capitalaccounts[15])));
       this.powerService.updatePower(power118);
       
       
       String[] acls = this.acl.split(",");
 
       List powers28 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(29), this.roleId);
       Power power81 = (Power)powers28.get(0);
       power81.setPshow(Integer.valueOf(Integer.parseInt(acls[0])));
       power81.setPadd(Integer.valueOf(Integer.parseInt(acls[1])));
       power81.setPupdate(Integer.valueOf(Integer.parseInt(acls[2])));
       this.powerService.updatePower(power81);
 
       String[] adminusers = this.adminuser.split(",");
 
       List powers29 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(28), this.roleId);
       Power power91 = (Power)powers29.get(0);
       power91.setPshow(Integer.valueOf(Integer.parseInt(adminusers[0])));
       power91.setPadd(Integer.valueOf(Integer.parseInt(adminusers[1])));
       power91.setPdelete(Integer.valueOf(Integer.parseInt(adminusers[2])));
       power91.setPupdate(Integer.valueOf(Integer.parseInt(adminusers[3])));
       this.powerService.updatePower(power91);
 
       String[] mfields = this.mfield.split(",");
 
       List powers30 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(30), this.roleId);
       Power power101 = (Power)powers30.get(0);
       power101.setPshow(Integer.valueOf(Integer.parseInt(mfields[0])));
       power101.setPupdate(Integer.valueOf(Integer.parseInt(mfields[1])));
       this.powerService.updatePower(power101);
 
       List powers31 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(31), this.roleId);
       Power power102 = (Power)powers31.get(0);
       power102.setPshow(Integer.valueOf(Integer.parseInt(mfields[2])));
       power102.setPadd(Integer.valueOf(Integer.parseInt(mfields[3])));
       power102.setPupdate(Integer.valueOf(Integer.parseInt(mfields[4])));
       this.powerService.updatePower(power102);
 
       List powers32 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(32), this.roleId);
       Power power103 = (Power)powers32.get(0);
       power103.setPshow(Integer.valueOf(Integer.parseInt(mfields[5])));
       power103.setPadd(Integer.valueOf(Integer.parseInt(mfields[6])));
       power103.setPupdate(Integer.valueOf(Integer.parseInt(mfields[7])));
       this.powerService.updatePower(power103);
 
       List powers33 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(33), this.roleId);
       Power power104 = (Power)powers33.get(0);
       power104.setPshow(Integer.valueOf(Integer.parseInt(mfields[8])));
       power104.setPadd(Integer.valueOf(Integer.parseInt(mfields[9])));
       power104.setPupdate(Integer.valueOf(Integer.parseInt(mfields[10])));
       this.powerService.updatePower(power104);
 
       List powers34 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(34), this.roleId);
       Power power105 = (Power)powers34.get(0);
       power105.setPshow(Integer.valueOf(Integer.parseInt(mfields[11])));
       power105.setPadd(Integer.valueOf(Integer.parseInt(mfields[12])));
       power105.setPupdate(Integer.valueOf(Integer.parseInt(mfields[13])));
       power105.setPdelete(Integer.valueOf(Integer.parseInt(mfields[14])));
       
       this.powerService.updatePower(power105);
 
       List powers35 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(35), this.roleId);
       Power power106 = (Power)powers35.get(0);
       power106.setPshow(Integer.valueOf(Integer.parseInt(mfields[15])));
       power106.setPadd(Integer.valueOf(Integer.parseInt(mfields[16])));
       power106.setPupdate(Integer.valueOf(Integer.parseInt(mfields[17])));
       power106.setPdelete(Integer.valueOf(Integer.parseInt(mfields[18])));
       this.powerService.updatePower(power106);
 
       List powers36 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(36), this.roleId);
       Power power107 = (Power)powers36.get(0);
       power107.setPshow(Integer.valueOf(Integer.parseInt(mfields[19])));
       power107.setPupdate(Integer.valueOf(Integer.parseInt(mfields[20])));
       this.powerService.updatePower(power107);
 
       List powers37 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(37), this.roleId);
       Power power108 = (Power)powers37.get(0);
       power108.setPshow(Integer.valueOf(Integer.parseInt(mfields[21])));
       power108.setPupdate(Integer.valueOf(Integer.parseInt(mfields[22])));
       this.powerService.updatePower(power108);
 
       List powers38 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(38), this.roleId);
       Power power109 = (Power)powers38.get(0);
       power109.setPshow(Integer.valueOf(Integer.parseInt(mfields[23])));
       power109.setPupdate(Integer.valueOf(Integer.parseInt(mfields[24])));
       this.powerService.updatePower(power109);
 
       List powers39 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(39), this.roleId);
       Power power110 = (Power)powers39.get(0);
       power110.setPadd(Integer.valueOf(Integer.parseInt(mfields[25])));
       power110.setPupdate(Integer.valueOf(Integer.parseInt(mfields[26])));
       power110.setPdelete(Integer.valueOf(Integer.parseInt(mfields[27])));
       this.powerService.updatePower(power110);
 
       List powers40 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(40), this.roleId);
       Power power111 = (Power)powers40.get(0);
       power111.setPupdate(Integer.valueOf(Integer.parseInt(mfields[28])));
       this.powerService.updatePower(power111);
 
       List powers41 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(41), this.roleId);
       Power power112 = (Power)powers41.get(0);
       power112.setPquery(Integer.valueOf(Integer.parseInt(mfields[29])));
       power112.setLeadingOut(Integer.valueOf(Integer.parseInt(mfields[30])));
       this.powerService.updatePower(power112);
 
       List powers42 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(42), this.roleId);
       Power power113 = (Power)powers42.get(0);
       power113.setPupdate(Integer.valueOf(Integer.parseInt(mfields[31])));
       this.powerService.updatePower(power113);
 
       List powers43 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(43), this.roleId);
       Power power114 = (Power)powers43.get(0);
       power114.setPupdate(Integer.valueOf(Integer.parseInt(mfields[32])));
       this.powerService.updatePower(power114);
 
       List powers44 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(44), this.roleId);
       Power power115 = (Power)powers44.get(0);
       power115.setPadd(Integer.valueOf(Integer.parseInt(mfields[33])));
       power115.setPupdate(Integer.valueOf(Integer.parseInt(mfields[34])));
       power115.setPdelete(Integer.valueOf(Integer.parseInt(mfields[35])));
       this.powerService.updatePower(power115);
 
       List powers47 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(47), this.roleId);
       Power power116 = (Power)powers47.get(0);
       power116.setPupdate(Integer.valueOf(Integer.parseInt(mfields[36])));
       this.powerService.updatePower(power116);
 
       List powers48 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(48), this.roleId);
       Power power117 = (Power)powers48.get(0);
       power117.setPupdate(Integer.valueOf(Integer.parseInt(mfields[37])));
       this.powerService.updatePower(power117);
 
       List powers50 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(50), this.roleId);
       Power power119 = (Power)powers50.get(0);
       power119.setPshow(Integer.valueOf(Integer.parseInt(mfields[38])));
       power119.setPadd(Integer.valueOf(Integer.parseInt(mfields[39])));
       power119.setPupdate(Integer.valueOf(Integer.parseInt(mfields[40])));
       power119.setPdelete(Integer.valueOf(Integer.parseInt(mfields[41])));
       this.powerService.updatePower(power119);
       
       List powers51 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(51), this.roleId);
       Power power120 = (Power)powers51.get(0);
       power120.setPshow(Integer.valueOf(Integer.parseInt(mfields[42])));
       power120.setPadd(Integer.valueOf(Integer.parseInt(mfields[43])));
       power120.setPupdate(Integer.valueOf(Integer.parseInt(mfields[44])));
       power120.setPdelete(Integer.valueOf(Integer.parseInt(mfields[45])));
       this.powerService.updatePower(power120);
       
       this.roleId = null;
     } else {
       Role role = new Role();
       role.setRoleName(this.roleName);
       Integer roleId1 = this.roleService.createRole(role);
       String[] global = this.globals.split(",");
 
       Power power11 = new Power();
       power11.setRole(this.roleService.findRoleByRoleId(roleId1));
       power11.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(1)));
       power11.setPshow(Integer.valueOf(Integer.parseInt(global[0])));
       power11.setPupdate(Integer.valueOf(Integer.parseInt(global[1])));
       this.powerService.addPower(power11);
 
       Power power12 = new Power();
       power12.setRole(this.roleService.findRoleByRoleId(roleId1));
       power12.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(2)));
       power12.setPshow(Integer.valueOf(Integer.parseInt(global[2])));
       power12.setPadd(Integer.valueOf(Integer.parseInt(global[3])));
       power12.setPdelete(Integer.valueOf(Integer.parseInt(global[4])));
       power12.setPupdate(Integer.valueOf(Integer.parseInt(global[5])));
       this.powerService.addPower(power12);
 
       Power power14 = new Power();
       power14.setRole(this.roleService.findRoleByRoleId(roleId1));
       power14.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(45)));
       power14.setPupdate(Integer.valueOf(Integer.parseInt(global[6])));
       this.powerService.addPower(power14);
 
       Power power13 = new Power();
       power13.setRole(this.roleService.findRoleByRoleId(roleId1));
       power13.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(3)));
       power13.setPshow(Integer.valueOf(Integer.parseInt(global[7])));
       power13.setPquery(Integer.valueOf(Integer.parseInt(global[8])));
       this.powerService.addPower(power13);
       String[] borrows = this.borrow.split(",");
 
       Power power21 = new Power();
       power21.setRole(this.roleService.findRoleByRoleId(roleId1));
       power21.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(4)));
       power21.setPshow(Integer.valueOf(Integer.parseInt(borrows[0])));
       power21.setPupdate(Integer.valueOf(Integer.parseInt(borrows[1])));
       power21.setPquery(Integer.valueOf(Integer.parseInt(borrows[2])));
       this.powerService.addPower(power21);
 
       Power power22 = new Power();
       power22.setRole(this.roleService.findRoleByRoleId(roleId1));
       power22.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(5)));
       power22.setPshow(Integer.valueOf(Integer.parseInt(borrows[3])));
       power22.setPupdate(Integer.valueOf(Integer.parseInt(borrows[4])));
       power22.setPquery(Integer.valueOf(Integer.parseInt(borrows[5])));
       this.powerService.addPower(power22);
 
       Power power23 = new Power();
       power23.setRole(this.roleService.findRoleByRoleId(roleId1));
       power23.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(6)));
       power23.setPshow(Integer.valueOf(Integer.parseInt(borrows[6])));
       power23.setPupdate(Integer.valueOf(Integer.parseInt(borrows[7])));
       power23.setPquery(Integer.valueOf(Integer.parseInt(borrows[8])));
       this.powerService.addPower(power23);
 
       Power power24 = new Power();
       power24.setRole(this.roleService.findRoleByRoleId(roleId1));
       power24.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(7)));
       power24.setPshow(Integer.valueOf(Integer.parseInt(borrows[9])));
       power24.setPupdate(Integer.valueOf(Integer.parseInt(borrows[10])));
       power24.setPquery(Integer.valueOf(Integer.parseInt(borrows[11])));
       power24.setInvestNotes(Integer.valueOf(Integer.parseInt(borrows[12])));
       power24.setWeekMark(Integer.valueOf(Integer.parseInt(borrows[13])));
       this.powerService.addPower(power24);
 
       Power power25 = new Power();
       power25.setRole(this.roleService.findRoleByRoleId(roleId1));
       power25.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(8)));
       power25.setPshow(Integer.valueOf(Integer.parseInt(borrows[14])));
       power25.setPquery(Integer.valueOf(Integer.parseInt(borrows[15])));
       power25.setInvestNotes(Integer.valueOf(Integer.parseInt(borrows[16])));
       this.powerService.addPower(power25);
 
       Power power26 = new Power();
       power26.setRole(this.roleService.findRoleByRoleId(roleId1));
       power26.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(9)));
       power26.setPshow(Integer.valueOf(Integer.parseInt(borrows[17])));
       power26.setPquery(Integer.valueOf(Integer.parseInt(borrows[18])));
       this.powerService.addPower(power26);
 
       Power power27 = new Power();
       power27.setRole(this.roleService.findRoleByRoleId(roleId1));
       power27.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(10)));
       power27.setPshow(Integer.valueOf(Integer.parseInt(borrows[19])));
       power27.setPquery(Integer.valueOf(Integer.parseInt(borrows[20])));
       this.powerService.addPower(power27);
 
       Power power28 = new Power();
       power28.setRole(this.roleService.findRoleByRoleId(roleId1));
       power28.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(11)));
       power28.setPshow(Integer.valueOf(Integer.parseInt(borrows[21])));
       power28.setPquery(Integer.valueOf(Integer.parseInt(borrows[22])));
       power28.setPupdate(Integer.valueOf(Integer.parseInt(borrows[23])));
       this.powerService.addPower(power28);
 
       String[] members = this.member.split(",");
 
       Power power31 = new Power();
       power31.setRole(this.roleService.findRoleByRoleId(roleId1));
       power31.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(12)));
       power31.setPshow(Integer.valueOf(Integer.parseInt(members[0])));
       power31.setUpdateBalance(Integer.valueOf(Integer.parseInt(members[1])));
       power31.setUpdateCredit(Integer.valueOf(Integer.parseInt(members[2])));
       power31.setPupdate(Integer.valueOf(Integer.parseInt(members[3])));
       power31.setPquery(Integer.valueOf(Integer.parseInt(members[4])));
       this.powerService.addPower(power31);
 
       Power power32 = new Power();
       power32.setRole(this.roleService.findRoleByRoleId(roleId1));
       power32.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(13)));
       power32.setPshow(Integer.valueOf(Integer.parseInt(members[5])));
       power32.setPupdate(Integer.valueOf(Integer.parseInt(members[6])));
       this.powerService.addPower(power32);
 
       Power power33 = new Power();
       power33.setRole(this.roleService.findRoleByRoleId(roleId1));
       power33.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(14)));
       power33.setPshow(Integer.valueOf(Integer.parseInt(members[7])));
       power33.setPupdate(Integer.valueOf(Integer.parseInt(members[8])));
       this.powerService.addPower(power33);
 
       Power power34 = new Power();
       power34.setRole(this.roleService.findRoleByRoleId(roleId1));
       power34.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(15)));
       power34.setPshow(Integer.valueOf(Integer.parseInt(members[9])));
       power34.setPupdate(Integer.valueOf(Integer.parseInt(members[10])));
       this.powerService.addPower(power34);
 
       Power power35 = new Power();
       power35.setRole(this.roleService.findRoleByRoleId(roleId1));
       power35.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(16)));
       power35.setPshow(Integer.valueOf(Integer.parseInt(members[11])));
       power35.setPupdate(Integer.valueOf(Integer.parseInt(members[12])));
       this.powerService.addPower(power35);
 
       Power power36 = new Power();
       power36.setRole(this.roleService.findRoleByRoleId(roleId1));
       power36.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(17)));
       power36.setPshow(Integer.valueOf(Integer.parseInt(members[13])));
       power36.setPupdate(Integer.valueOf(Integer.parseInt(members[14])));
       this.powerService.addPower(power36);
 
       Power power37 = new Power();
       power37.setRole(this.roleService.findRoleByRoleId(roleId1));
       power37.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(18)));
       power37.setPshow(Integer.valueOf(Integer.parseInt(members[15])));
       power37.setPupdate(Integer.valueOf(Integer.parseInt(members[16])));
       this.powerService.addPower(power37);
 
       Power power38 = new Power();
       power38.setRole(this.roleService.findRoleByRoleId(roleId1));
       power38.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(46)));
       power38.setPupdate(Integer.valueOf(Integer.parseInt(members[17])));
       this.powerService.addPower(power38);
 
       String[] paylogs = this.paylog.split(",");
 
       Power power41 = new Power();
       power41.setRole(this.roleService.findRoleByRoleId(roleId1));
       power41.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(19)));
       power41.setPshow(Integer.valueOf(Integer.parseInt(paylogs[0])));
       power41.setPupdate(Integer.valueOf(Integer.parseInt(paylogs[1])));
       this.powerService.addPower(power41);
 
       Power power42 = new Power();
       power42.setRole(this.roleService.findRoleByRoleId(roleId1));
       power42.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(20)));
       power42.setPshow(Integer.valueOf(Integer.parseInt(paylogs[2])));
       power42.setPupdate(Integer.valueOf(Integer.parseInt(paylogs[3])));
       this.powerService.addPower(power42);
 
       String[] articles = this.article.split(",");
 
       Power power51 = new Power();
       power51.setRole(this.roleService.findRoleByRoleId(roleId1));
       power51.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(21)));
       power51.setPshow(Integer.valueOf(Integer.parseInt(articles[0])));
       power51.setPadd(Integer.valueOf(Integer.parseInt(articles[1])));
       power51.setPdelete(Integer.valueOf(Integer.parseInt(articles[2])));
       power51.setPupdate(Integer.valueOf(Integer.parseInt(articles[3])));
       power51.setPquery(Integer.valueOf(Integer.parseInt(articles[4])));
       this.powerService.addPower(power51);
 
       String[] navigations = this.navigation.split(",");
 
       Power power61 = new Power();
       power61.setRole(this.roleService.findRoleByRoleId(roleId1));
       power61.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(22)));
       power61.setPshow(Integer.valueOf(Integer.parseInt(navigations[0])));
       power61.setPupdate(Integer.valueOf(Integer.parseInt(navigations[1])));
       power61.setPadd(Integer.valueOf(Integer.parseInt(navigations[2])));
       power61.setPdelete(Integer.valueOf(Integer.parseInt(navigations[3])));
       this.powerService.addPower(power61);
 
       String[] capitalaccounts = this.capitalaccount.split(",");
 
       Power power71 = new Power();
       power71.setRole(this.roleService.findRoleByRoleId(roleId1));
       power71.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(23)));
       power71.setPshow(Integer.valueOf(Integer.parseInt(capitalaccounts[0])));
       power71.setLeadingOut(Integer.valueOf(Integer.parseInt(capitalaccounts[1])));
       power71.setPquery(Integer.valueOf(Integer.parseInt(capitalaccounts[2])));
       this.powerService.addPower(power71);
 
       Power power72 = new Power();
       power72.setRole(this.roleService.findRoleByRoleId(roleId1));
       power72.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(24)));
       power72.setPshow(Integer.valueOf(Integer.parseInt(capitalaccounts[3])));
       power72.setLeadingOut(Integer.valueOf(Integer.parseInt(capitalaccounts[4])));
       power72.setPquery(Integer.valueOf(Integer.parseInt(capitalaccounts[5])));
       this.powerService.addPower(power72);
 
       Power power73 = new Power();
       power73.setRole(this.roleService.findRoleByRoleId(roleId1));
       power73.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(25)));
       power73.setPshow(Integer.valueOf(Integer.parseInt(capitalaccounts[6])));
       power73.setLeadingOut(Integer.valueOf(Integer.parseInt(capitalaccounts[7])));
       power73.setPquery(Integer.valueOf(Integer.parseInt(capitalaccounts[8])));
       this.powerService.addPower(power73);
 
       Power power74 = new Power();
       power74.setRole(this.roleService.findRoleByRoleId(roleId1));
       power74.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(26)));
       power74.setPshow(Integer.valueOf(Integer.parseInt(capitalaccounts[9])));
       power74.setLeadingOut(Integer.valueOf(Integer.parseInt(capitalaccounts[10])));
       power74.setPquery(Integer.valueOf(Integer.parseInt(capitalaccounts[11])));
       this.powerService.addPower(power74);
 
       Power power75 = new Power();
       power75.setRole(this.roleService.findRoleByRoleId(roleId1));
       power75.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(27)));
       power75.setPshow(Integer.valueOf(Integer.parseInt(capitalaccounts[12])));
       power75.setPquery(Integer.valueOf(Integer.parseInt(capitalaccounts[13])));
       this.powerService.addPower(power75);
 
       Power power118 = new Power();
       power118.setRole(this.roleService.findRoleByRoleId(roleId1));
       power118.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(49)));
       power118.setPshow(Integer.valueOf(Integer.parseInt(capitalaccounts[14])));
       power118.setPquery(Integer.valueOf(Integer.parseInt(capitalaccounts[15])));
       this.powerService.updatePower(power118);
       
       String[] acls = this.acl.split(",");
 
       Power power81 = new Power();
       power81.setRole(this.roleService.findRoleByRoleId(roleId1));
       power81.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(29)));
       power81.setPshow(Integer.valueOf(Integer.parseInt(acls[0])));
       power81.setPadd(Integer.valueOf(Integer.parseInt(acls[1])));
       power81.setPupdate(Integer.valueOf(Integer.parseInt(acls[2])));
       this.powerService.addPower(power81);
 
       String[] adminusers = this.adminuser.split(",");
 
       Power power91 = new Power();
       power91.setRole(this.roleService.findRoleByRoleId(roleId1));
       power91.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(28)));
       power91.setPshow(Integer.valueOf(Integer.parseInt(adminusers[0])));
       power91.setPadd(Integer.valueOf(Integer.parseInt(adminusers[1])));
       power91.setPdelete(Integer.valueOf(Integer.parseInt(adminusers[2])));
       power91.setPupdate(Integer.valueOf(Integer.parseInt(adminusers[3])));
       this.powerService.addPower(power91);
 
       String[] mfields = this.mfield.split(",");
 
       Power power101 = new Power();
       power101.setRole(this.roleService.findRoleByRoleId(roleId1));
       power101.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(30)));
       power101.setPshow(Integer.valueOf(Integer.parseInt(mfields[0])));
       power101.setPupdate(Integer.valueOf(Integer.parseInt(mfields[1])));
       this.powerService.addPower(power101);
 
       Power power102 = new Power();
       power102.setRole(this.roleService.findRoleByRoleId(roleId1));
       power102.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(31)));
       power102.setPshow(Integer.valueOf(Integer.parseInt(mfields[2])));
       power102.setPadd(Integer.valueOf(Integer.parseInt(mfields[3])));
       power102.setPupdate(Integer.valueOf(Integer.parseInt(mfields[4])));
       this.powerService.addPower(power102);
 
       Power power103 = new Power();
       power103.setRole(this.roleService.findRoleByRoleId(roleId1));
       power103.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(32)));
       power103.setPshow(Integer.valueOf(Integer.parseInt(mfields[5])));
       power103.setPadd(Integer.valueOf(Integer.parseInt(mfields[6])));
       power103.setPupdate(Integer.valueOf(Integer.parseInt(mfields[7])));
       this.powerService.addPower(power103);
 
       Power power104 = new Power();
       power104.setRole(this.roleService.findRoleByRoleId(roleId1));
       power104.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(33)));
       power104.setPshow(Integer.valueOf(Integer.parseInt(mfields[8])));
       power104.setPadd(Integer.valueOf(Integer.parseInt(mfields[9])));
       power104.setPupdate(Integer.valueOf(Integer.parseInt(mfields[10])));
       this.powerService.addPower(power104);
 
       Power power105 = new Power();
       power105.setRole(this.roleService.findRoleByRoleId(roleId1));
       power105.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(34)));
       power105.setPshow(Integer.valueOf(Integer.parseInt(mfields[11])));
       power105.setPadd(Integer.valueOf(Integer.parseInt(mfields[12])));
       power105.setPdelete(Integer.valueOf(Integer.parseInt(mfields[13])));
       power105.setPupdate(Integer.valueOf(Integer.parseInt(mfields[14])));
       this.powerService.addPower(power105);
 
       Power power106 = new Power();
       power106.setRole(this.roleService.findRoleByRoleId(roleId1));
       power106.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(35)));
       power106.setPshow(Integer.valueOf(Integer.parseInt(mfields[15])));
       power106.setPadd(Integer.valueOf(Integer.parseInt(mfields[16])));
       power106.setPupdate(Integer.valueOf(Integer.parseInt(mfields[17])));
       power106.setPdelete(Integer.valueOf(Integer.parseInt(mfields[18])));
       this.powerService.addPower(power106);
 
       Power power107 = new Power();
       power107.setRole(this.roleService.findRoleByRoleId(roleId1));
       power107.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(36)));
       power107.setPshow(Integer.valueOf(Integer.parseInt(mfields[19])));
       power107.setPupdate(Integer.valueOf(Integer.parseInt(mfields[20])));
       this.powerService.addPower(power107);
 
       Power power108 = new Power();
       power108.setRole(this.roleService.findRoleByRoleId(roleId1));
       power108.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(37)));
       power108.setPshow(Integer.valueOf(Integer.parseInt(mfields[21])));
       power108.setPupdate(Integer.valueOf(Integer.parseInt(mfields[22])));
       this.powerService.addPower(power108);
 
       Power power109 = new Power();
       power109.setRole(this.roleService.findRoleByRoleId(roleId1));
       power109.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(38)));
       power109.setPshow(Integer.valueOf(Integer.parseInt(mfields[23])));
       power109.setPupdate(Integer.valueOf(Integer.parseInt(mfields[24])));
       this.powerService.addPower(power109);
 
       Power power110 = new Power();
       power110.setRole(this.roleService.findRoleByRoleId(roleId1));
       power110.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(39)));
       power110.setPadd(Integer.valueOf(Integer.parseInt(mfields[25])));
       power110.setPupdate(Integer.valueOf(Integer.parseInt(mfields[26])));
       power110.setPdelete(Integer.valueOf(Integer.parseInt(mfields[27])));
       this.powerService.addPower(power110);
 
       Power power111 = new Power();
       power111.setRole(this.roleService.findRoleByRoleId(roleId1));
       power111.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(40)));
       power111.setPupdate(Integer.valueOf(Integer.parseInt(mfields[28])));
       this.powerService.addPower(power111);
 
       Power power112 = new Power();
       power112.setRole(this.roleService.findRoleByRoleId(roleId1));
       power112.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(41)));
       power112.setPquery(Integer.valueOf(Integer.parseInt(mfields[29])));
       power112.setLeadingOut(Integer.valueOf(Integer.parseInt(mfields[30])));
       this.powerService.addPower(power112);
 
       Power power113 = new Power();
       power113.setRole(this.roleService.findRoleByRoleId(roleId1));
       power113.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(42)));
       power113.setPupdate(Integer.valueOf(Integer.parseInt(mfields[31])));
       this.powerService.addPower(power113);
 
       Power power114 = new Power();
       power114.setRole(this.roleService.findRoleByRoleId(roleId1));
       power114.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(43)));
       power114.setPupdate(Integer.valueOf(Integer.parseInt(mfields[32])));
       this.powerService.addPower(power114);
 
       Power power115 = new Power();
       power115.setRole(this.roleService.findRoleByRoleId(roleId1));
       power115.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(44)));
       power115.setPadd(Integer.valueOf(Integer.parseInt(mfields[33])));
       power115.setPupdate(Integer.valueOf(Integer.parseInt(mfields[34])));
       power115.setPdelete(Integer.valueOf(Integer.parseInt(mfields[35])));
       this.powerService.addPower(power115);
 
       Power power116 = new Power();
       power116.setRole(this.roleService.findRoleByRoleId(roleId1));
       power116.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(47)));
       power116.setPupdate(Integer.valueOf(Integer.parseInt(mfields[36])));
       this.powerService.addPower(power116);
 
       Power power117 = new Power();
       power117.setRole(this.roleService.findRoleByRoleId(roleId1));
       power117.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(48)));
       power117.setPupdate(Integer.valueOf(Integer.parseInt(mfields[37])));
       this.powerService.addPower(power117);
       
       
       Power power119 = new Power();
       power119.setRole(this.roleService.findRoleByRoleId(roleId1));
       power119.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(50)));
       power119.setPshow(Integer.valueOf(Integer.parseInt(mfields[38])));
       power119.setPadd(Integer.valueOf(Integer.parseInt(mfields[39])));
       power119.setPupdate(Integer.valueOf(Integer.parseInt(mfields[40])));
       power119.setPdelete(Integer.valueOf(Integer.parseInt(mfields[41])));
       this.powerService.updatePower(power119);
       
       Power power120 = new Power();
       power120.setRole(this.roleService.findRoleByRoleId(roleId1));
       power120.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(51)));
       power120.setPshow(Integer.valueOf(Integer.parseInt(mfields[42])));
       power120.setPadd(Integer.valueOf(Integer.parseInt(mfields[43])));
       power120.setPupdate(Integer.valueOf(Integer.parseInt(mfields[44])));
       power120.setPdelete(Integer.valueOf(Integer.parseInt(mfields[45])));
       this.powerService.updatePower(power120);
       
     }
     searchRoles();
     return "success";
   }
   //±à¼­
   public String editPowers() throws Exception {
     Role role = this.roleService.findRoleByRoleId(this.roleId);
     this.roleName = role.getRoleName();
 
     List powers1 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(1), this.roleId);
     this.powers[0] = ((Power)powers1.get(0)).getPshow();
     this.powers[1] = ((Power)powers1.get(0)).getPupdate();
     List powers2 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(2), this.roleId);
     this.powers[2] = ((Power)powers2.get(0)).getPshow();
     this.powers[3] = ((Power)powers2.get(0)).getPadd();
     this.powers[4] = ((Power)powers2.get(0)).getPdelete();
     this.powers[5] = ((Power)powers2.get(0)).getPupdate();
     List powers3 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(3), this.roleId);
     this.powers[6] = ((Power)powers3.get(0)).getPshow();
     this.powers[7] = ((Power)powers3.get(0)).getPquery();
     List powers45 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(45), this.roleId);
     this.powers[115] = ((Power)powers45.get(0)).getPupdate();
 
     List powers4 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(4), this.roleId);
     this.powers[8] = ((Power)powers4.get(0)).getPshow();
     this.powers[9] = ((Power)powers4.get(0)).getPupdate();
     this.powers[89] = ((Power)powers4.get(0)).getPquery();
     List powers5 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(5), this.roleId);
     this.powers[10] = ((Power)powers5.get(0)).getPshow();
     this.powers[11] = ((Power)powers5.get(0)).getPupdate();
     this.powers[90] = ((Power)powers5.get(0)).getPquery();
     List powers6 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(6), this.roleId);
     this.powers[12] = ((Power)powers6.get(0)).getPshow();
     this.powers[13] = ((Power)powers6.get(0)).getPupdate();
     this.powers[91] = ((Power)powers6.get(0)).getPquery();
     List powers7 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(7), this.roleId);
     this.powers[14] = ((Power)powers7.get(0)).getPshow();
     this.powers[15] = ((Power)powers7.get(0)).getInvestNotes();
     this.powers[92] = ((Power)powers7.get(0)).getPquery();
     this.powers[93] = ((Power)powers7.get(0)).getPupdate();
     this.powers[116] = ((Power)powers7.get(0)).getWeekMark();
     List powers8 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(8), this.roleId);
     this.powers[16] = ((Power)powers8.get(0)).getPshow();
     this.powers[94] = ((Power)powers8.get(0)).getPquery();
     this.powers[95] = ((Power)powers8.get(0)).getInvestNotes();
     List powers9 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(9), this.roleId);
     this.powers[17] = ((Power)powers9.get(0)).getPshow();
     this.powers[96] = ((Power)powers9.get(0)).getPquery();
     List powers10 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(10), this.roleId);
     this.powers[18] = ((Power)powers10.get(0)).getPshow();
     this.powers[97] = ((Power)powers10.get(0)).getPquery();
     List powers11 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(11), this.roleId);
     this.powers[19] = ((Power)powers11.get(0)).getPshow();
     this.powers[98] = ((Power)powers11.get(0)).getPquery();
     this.powers[117] = ((Power)powers11.get(0)).getPupdate();
 
     List powers12 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(12), this.roleId);
     this.powers[20] = ((Power)powers12.get(0)).getPshow();
     this.powers[21] = ((Power)powers12.get(0)).getUpdateBalance();
     this.powers[22] = ((Power)powers12.get(0)).getUpdateCredit();
     this.powers[23] = ((Power)powers12.get(0)).getPupdate();
     this.powers[24] = ((Power)powers12.get(0)).getPquery();
     List powers13 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(13), this.roleId);
     this.powers[25] = ((Power)powers13.get(0)).getPshow();
     this.powers[26] = ((Power)powers13.get(0)).getPupdate();
     List powers14 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(14), this.roleId);
     this.powers[27] = ((Power)powers14.get(0)).getPshow();
     this.powers[28] = ((Power)powers14.get(0)).getPupdate();
     List powers15 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(15), this.roleId);
     this.powers[29] = ((Power)powers15.get(0)).getPshow();
     this.powers[30] = ((Power)powers15.get(0)).getPupdate();
     List powers16 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(16), this.roleId);
     this.powers[31] = ((Power)powers16.get(0)).getPshow();
     this.powers[32] = ((Power)powers16.get(0)).getPupdate();
     List powers17 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(17), this.roleId);
     this.powers[33] = ((Power)powers17.get(0)).getPshow();
     this.powers[34] = ((Power)powers17.get(0)).getPupdate();
     List powers18 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(18), this.roleId);
     this.powers[35] = ((Power)powers18.get(0)).getPshow();
     this.powers[36] = ((Power)powers18.get(0)).getPupdate();
 
     List powers46 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(46), this.roleId);
     this.powers[118] = ((Power)powers46.get(0)).getPupdate();
 
     List powers19 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(19), this.roleId);
     this.powers[37] = ((Power)powers19.get(0)).getPshow();
     this.powers[38] = ((Power)powers19.get(0)).getPupdate();
     List powers20 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(20), this.roleId);
     this.powers[39] = ((Power)powers20.get(0)).getPshow();
     this.powers[40] = ((Power)powers20.get(0)).getPupdate();
 
     List powers21 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(21), this.roleId);
     this.powers[41] = ((Power)powers21.get(0)).getPshow();
     this.powers[42] = ((Power)powers21.get(0)).getPadd();
     this.powers[43] = ((Power)powers21.get(0)).getPdelete();
     this.powers[44] = ((Power)powers21.get(0)).getPupdate();
     this.powers[86] = ((Power)powers21.get(0)).getPquery();
 
     List powers22 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(22), this.roleId);
     this.powers[45] = ((Power)powers22.get(0)).getPshow();
     this.powers[46] = ((Power)powers22.get(0)).getPupdate();
     this.powers[87] = ((Power)powers22.get(0)).getPadd();
     this.powers[88] = ((Power)powers22.get(0)).getPdelete();
 
     List powers23 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(23), this.roleId);
     this.powers[47] = ((Power)powers23.get(0)).getPshow();
     this.powers[48] = ((Power)powers23.get(0)).getLeadingOut();
     this.powers[99] = ((Power)powers23.get(0)).getPquery();
     List powers24 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(24), this.roleId);
     this.powers[49] = ((Power)powers24.get(0)).getPshow();
     this.powers[50] = ((Power)powers24.get(0)).getLeadingOut();
     this.powers[100] = ((Power)powers24.get(0)).getPquery();
     List powers25 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(25), this.roleId);
     this.powers[51] = ((Power)powers25.get(0)).getPshow();
     this.powers[52] = ((Power)powers25.get(0)).getLeadingOut();
     this.powers[101] = ((Power)powers25.get(0)).getPquery();
     List powers26 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(26), this.roleId);
     this.powers[53] = ((Power)powers26.get(0)).getPshow();
     this.powers[54] = ((Power)powers26.get(0)).getLeadingOut();
     this.powers[102] = ((Power)powers26.get(0)).getPquery();
     List powers27 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(27), this.roleId);
     this.powers[55] = ((Power)powers27.get(0)).getPshow();
     this.powers[103] = ((Power)powers27.get(0)).getPquery();
 
     List powers29 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(29), this.roleId);
     this.powers[56] = ((Power)powers29.get(0)).getPshow();
     this.powers[57] = ((Power)powers29.get(0)).getPadd();
     this.powers[58] = ((Power)powers29.get(0)).getPupdate();
 
     List powers28 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(28), this.roleId);
     this.powers[59] = ((Power)powers28.get(0)).getPshow();
     this.powers[60] = ((Power)powers28.get(0)).getPadd();
     this.powers[61] = ((Power)powers28.get(0)).getPdelete();
     this.powers[62] = ((Power)powers28.get(0)).getPupdate();
 
     List powers30 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(30), this.roleId);
     this.powers[63] = ((Power)powers30.get(0)).getPshow();
     this.powers[64] = ((Power)powers30.get(0)).getPupdate();
     List powers31 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(31), this.roleId);
     this.powers[65] = ((Power)powers31.get(0)).getPshow();
     this.powers[66] = ((Power)powers31.get(0)).getPadd();
     this.powers[67] = ((Power)powers31.get(0)).getPupdate();
     List powers32 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(32), this.roleId);
     this.powers[68] = ((Power)powers32.get(0)).getPshow();
     this.powers[69] = ((Power)powers32.get(0)).getPadd();
     this.powers[70] = ((Power)powers32.get(0)).getPupdate();
     List powers33 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(33), this.roleId);
     this.powers[71] = ((Power)powers33.get(0)).getPshow();
     this.powers[72] = ((Power)powers33.get(0)).getPadd();
     this.powers[73] = ((Power)powers33.get(0)).getPupdate();
     List powers34 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(34), this.roleId);
     this.powers[74] = ((Power)powers34.get(0)).getPshow();
     this.powers[75] = ((Power)powers34.get(0)).getPadd();
     this.powers[76] = ((Power)powers34.get(0)).getPdelete();
     this.powers[124] = ((Power)powers34.get(0)).getPupdate();
     List powers35 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(35), this.roleId);
     this.powers[77] = ((Power)powers35.get(0)).getPshow();
     this.powers[78] = ((Power)powers35.get(0)).getPadd();
     this.powers[79] = ((Power)powers35.get(0)).getPupdate();
     this.powers[121]= ((Power) powers35.get(0)).getPdelete();
     List powers36 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(36), this.roleId);
     this.powers[80] = ((Power)powers36.get(0)).getPshow();
     this.powers[81] = ((Power)powers36.get(0)).getPupdate();
     List powers37 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(37), this.roleId);
     this.powers[82] = ((Power)powers37.get(0)).getPshow();
     this.powers[83] = ((Power)powers37.get(0)).getPupdate();
     List powers38 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(38), this.roleId);
     this.powers[84] = ((Power)powers38.get(0)).getPshow();
     this.powers[85] = ((Power)powers38.get(0)).getPupdate();
 
     List powers39 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(39), this.roleId);
     if ((powers39 == null) || (powers39.size() == 0)) {
       Power power = new Power();
       power.setRole(this.roleService.findRoleByRoleId(this.roleId));
       power.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(39)));
       this.powerService.addPower(power);
       this.powers[104] = Integer.valueOf(0);
       this.powers[105] = Integer.valueOf(0);
       this.powers[106] = Integer.valueOf(0);
     } else {
       this.powers[104] = ((Power)powers39.get(0)).getPadd();
       this.powers[105] = ((Power)powers39.get(0)).getPupdate();
       this.powers[106] = ((Power)powers39.get(0)).getPdelete();
     }
 
     List powers40 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(40), this.roleId);
     if ((powers40 == null) || (powers40.size() == 0)) {
       Power power = new Power();
       power.setRole(this.roleService.findRoleByRoleId(this.roleId));
       power.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(40)));
       this.powerService.addPower(power);
       this.powers[107] = Integer.valueOf(0);
     } else {
       this.powers[107] = ((Power)powers40.get(0)).getPupdate();
     }
 
     List powers41 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(41), this.roleId);
     if ((powers41 == null) || (powers41.size() == 0)) {
       Power power = new Power();
       power.setRole(this.roleService.findRoleByRoleId(this.roleId));
       power.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(41)));
       this.powerService.addPower(power);
       this.powers[108] = Integer.valueOf(0);
       this.powers[109] = Integer.valueOf(0);
     } else {
       this.powers[108] = ((Power)powers41.get(0)).getPquery();
       this.powers[109] = ((Power)powers41.get(0)).getLeadingOut();
     }
 
     List powers42 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(42), this.roleId);
     if ((powers42 == null) || (powers42.size() == 0)) {
       Power power = new Power();
       power.setRole(this.roleService.findRoleByRoleId(this.roleId));
       power.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(42)));
       this.powerService.addPower(power);
       this.powers[110] = Integer.valueOf(0);
     } else {
       this.powers[110] = ((Power)powers42.get(0)).getPupdate();
     }
 
     List powers43 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(43), this.roleId);
     if ((powers43 == null) || (powers43.size() == 0)) {
       Power power = new Power();
       power.setRole(this.roleService.findRoleByRoleId(this.roleId));
       power.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(43)));
       this.powerService.addPower(power);
       this.powers[111] = Integer.valueOf(0);
     } else {
       this.powers[111] = ((Power)powers43.get(0)).getPupdate();
     }
 
     List powers44 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(44), this.roleId);
     if ((powers44 == null) || (powers44.size() == 0)) {
       Power power = new Power();
       power.setRole(this.roleService.findRoleByRoleId(this.roleId));
       power.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(44)));
       this.powerService.addPower(power);
       this.powers[112] = Integer.valueOf(0);
       this.powers[113] = Integer.valueOf(0);
       this.powers[114] = Integer.valueOf(0);
     } else {
       this.powers[112] = ((Power)powers44.get(0)).getPadd();
       this.powers[113] = ((Power)powers44.get(0)).getPupdate();
       this.powers[114] = ((Power)powers44.get(0)).getPdelete();
     }
 
     List powers47 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(47), this.roleId);
     if ((powers47 == null) || (powers47.size() == 0)) {
       Power power = new Power();
       power.setRole(this.roleService.findRoleByRoleId(this.roleId));
       power.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(47)));
       power.setPupdate(Integer.valueOf(0));
       this.powerService.addPower(power);
       this.powers[119] = Integer.valueOf(0);
     } else {
       this.powers[119] = ((Power)powers47.get(0)).getPupdate();
     }
 
     List powers48 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(48), this.roleId);
     if ((powers48 == null) || (powers48.size() == 0)) {
       Power power = new Power();
       power.setRole(this.roleService.findRoleByRoleId(this.roleId));
       power.setMenu(this.menuService.findMenuByMenuId(Integer.valueOf(48)));
       power.setPupdate(Integer.valueOf(0));
       this.powerService.addPower(power);
       this.powers[120] = Integer.valueOf(0);
     } else {
       this.powers[120] = ((Power)powers48.get(0)).getPupdate();
     }
     
     
     List powers49 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(49), this.roleId);
     this.powers[122] = ((Power)powers49.get(0)).getPshow();
     this.powers[123] = ((Power)powers49.get(0)).getPquery();
     
     List powers50 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(50), this.roleId);
     this.powers[125] = ((Power)powers50.get(0)).getPshow();
     this.powers[126] = ((Power)powers50.get(0)).getPadd();
     this.powers[127] = ((Power)powers50.get(0)).getPdelete();
     this.powers[128] = ((Power)powers50.get(0)).getPupdate();
     
     List powers51 = this.powerService.findPowersByMeuAndRole(Integer.valueOf(51), this.roleId);
     this.powers[129] = ((Power)powers51.get(0)).getPshow();
     this.powers[130] = ((Power)powers51.get(0)).getPadd();
     this.powers[131] = ((Power)powers51.get(0)).getPdelete();
     this.powers[132] = ((Power)powers51.get(0)).getPupdate();
     
     return "success";
   }
   public String delRole() throws Exception {
     List managers = this.managerService.findManagerBySql("select * from manager where isBan=1 and rol_roleId=" + this.roleId);
     if ((managers != null) && (managers.size() > 0)) {
       this.mark = "1";
     } else {
       Role role = this.roleService.findRoleByRoleId(this.roleId);
       List powers = this.powerService.findPowersBySql("select * from power where roleId=" + this.roleId);
       for (int i = 0; i < powers.size(); i++) {
         Power power = (Power)powers.get(i);
         this.powerService.deletePower(power);
       }
       this.roleService.deleteRole(role);
     }
     searchRoles();
     return "success";
   }
   public String checkRole() throws Exception {
	     if (this.roleId == null) {
	       this.roleId = Integer.valueOf(0);
	     }
	     String flag = null;
	     flag = this.roleService.findRoleByName(this.roleName, this.roleId);
	     if ("1".equals(flag))
	       this.result = "error";
	     else {
	       this.result = "success";
	     }
	     return "success";
	   }
   public List<Role> getRoles() {
     return this.roles;
   }
   public void setRoles(List<Role> roles) {
     this.roles = roles;
   }
   public String getRoleName() {
     return this.roleName;
   }
   public void setRoleName(String roleName) {
     this.roleName = roleName;
   }
   public String getGlobals() {
     return this.globals;
   }
   public void setGlobals(String globals) {
     this.globals = globals;
   }
   public String getBorrow() {
     return this.borrow;
   }
   public void setBorrow(String borrow) {
     this.borrow = borrow;
   }
   public String getMember() {
     return this.member;
   }
   public void setMember(String member) {
     this.member = member;
   }
   public String getPaylog() {
     return this.paylog;
   }
   public void setPaylog(String paylog) {
     this.paylog = paylog;
   }
   public String getArticle() {
     return this.article;
   }
   public void setArticle(String article) {
     this.article = article;
   }
   public String getNavigation() {
     return this.navigation;
   }
   public void setNavigation(String navigation) {
     this.navigation = navigation;
   }
   public String getAcl() {
     return this.acl;
   }
   public void setAcl(String acl) {
     this.acl = acl;
   }
   public String getAdminuser() {
     return this.adminuser;
   }
   public void setAdminuser(String adminuser) {
     this.adminuser = adminuser;
   }
   public String getMfield() {
     return this.mfield;
   }
   public void setMfield(String mfield) {
     this.mfield = mfield;
   }
   public String getCapitalaccount() {
     return this.capitalaccount;
   }
   public void setCapitalaccount(String capitalaccount) {
     this.capitalaccount = capitalaccount;
   }
   public Integer getRoleId() {
     return this.roleId;
   }
   public void setRoleId(Integer roleId) {
     this.roleId = roleId;
   }
   public Integer[] getPowers() {
     return this.powers;
   }
   public void setPowers(Integer[] powers) {
     this.powers = powers;
   }
   public String getResult() {
     return this.result;
   }
   public void setResult(String result) {
     this.result = result;
   }
   public String getMark() {
     return this.mark;
   }
   public void setMark(String mark) {
     this.mark = mark;
   }
 }

