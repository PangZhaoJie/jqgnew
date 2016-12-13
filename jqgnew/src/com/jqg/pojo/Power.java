 package com.jqg.pojo;
 
 import java.io.Serializable;
 
 public class Power
   implements Serializable
 {
   private Integer powerId;
   private Menu menu;
   private Role role;
   private Integer padd;
   private Integer pdelete;
   private Integer pquery;
   private Integer pupdate;
   private Integer pshow;
   private Integer deleteMonth;
   private Integer manualHandling;
   private Integer weekMark;
   private Integer investNotes;
   private Integer updateBalance;
   private Integer updateCredit;
   private Integer accountCall;
   private Integer concreteCall;
   private Integer holidayCall;
   private Integer callNotes;
   private Integer leadingOut;
   private Integer uploadData;
   private Integer uploadShowData;
   private Integer batchAdd;
   private Integer uploadHeadPhoto;
   private Integer backups;
   private Integer recoverBackups;
   private Integer packDown;
   private Integer photoUpload;
   private Integer safeCheck;
   private Integer spaceCheck;
 
   public Power()
   {
   }
 
   public Power(Integer powerId, Menu menu, Role role, Integer padd, Integer pdelete, Integer pquery, Integer pupdate, Integer pshow, Integer deleteMonth, Integer manualHandling, Integer weekMark, Integer investNotes, Integer updateBalance, Integer updateCredit, Integer accountCall, Integer concreteCall, Integer holidayCall, Integer callNotes, Integer leadingOut, Integer uploadData, Integer uploadShowData, Integer batchAdd, Integer uploadHeadPhoto, Integer backups, Integer recoverBackups, Integer packDown, Integer photoUpload, Integer safeCheck, Integer spaceCheck)
   {
     this.powerId = powerId;
     this.menu = menu;
     this.role = role;
     this.padd = padd;
     this.pdelete = pdelete;
     this.pquery = pquery;
     this.pupdate = pupdate;
     this.pshow = pshow;
     this.deleteMonth = deleteMonth;
     this.manualHandling = manualHandling;
     this.weekMark = weekMark;
     this.investNotes = investNotes;
     this.updateBalance = updateBalance;
     this.updateCredit = updateCredit;
     this.accountCall = accountCall;
     this.concreteCall = concreteCall;
     this.holidayCall = holidayCall;
     this.callNotes = callNotes;
     this.leadingOut = leadingOut;
     this.uploadData = uploadData;
     this.uploadShowData = uploadShowData;
     this.batchAdd = batchAdd;
     this.uploadHeadPhoto = uploadHeadPhoto;
     this.backups = backups;
     this.recoverBackups = recoverBackups;
     this.packDown = packDown;
     this.photoUpload = photoUpload;
     this.safeCheck = safeCheck;
     this.spaceCheck = spaceCheck;
   }
 
   public Integer getPowerId()
   {
     return this.powerId;
   }
 
   public void setPowerId(Integer powerId) {
     this.powerId = powerId;
   }
 
   public Menu getMenu() {
     return this.menu;
   }
 
   public void setMenu(Menu menu) {
     this.menu = menu;
   }
 
   public Role getRole() {
     return this.role;
   }
 
   public void setRole(Role role) {
     this.role = role;
   }
 
   public Integer getPadd() {
     return this.padd;
   }
 
   public void setPadd(Integer padd) {
     this.padd = padd;
   }
 
   public Integer getPdelete() {
     return this.pdelete;
   }
 
   public void setPdelete(Integer pdelete) {
     this.pdelete = pdelete;
   }
 
   public Integer getPquery() {
     return this.pquery;
   }
 
   public void setPquery(Integer pquery) {
     this.pquery = pquery;
   }
 
   public Integer getPupdate() {
     return this.pupdate;
   }
 
   public void setPupdate(Integer pupdate) {
     this.pupdate = pupdate;
   }
 
   public Integer getPshow() {
     return this.pshow;
   }
 
   public void setPshow(Integer pshow) {
     this.pshow = pshow;
   }
 
   public Integer getDeleteMonth() {
     return this.deleteMonth;
   }
 
   public void setDeleteMonth(Integer deleteMonth) {
     this.deleteMonth = deleteMonth;
   }
 
   public Integer getManualHandling() {
     return this.manualHandling;
   }
 
   public void setManualHandling(Integer manualHandling) {
     this.manualHandling = manualHandling;
   }
 
   public Integer getWeekMark() {
     return this.weekMark;
   }
 
   public void setWeekMark(Integer weekMark) {
     this.weekMark = weekMark;
   }
 
   public Integer getInvestNotes() {
     return this.investNotes;
   }
 
   public void setInvestNotes(Integer investNotes) {
     this.investNotes = investNotes;
   }
 
   public Integer getUpdateBalance() {
     return this.updateBalance;
   }
 
   public void setUpdateBalance(Integer updateBalance) {
     this.updateBalance = updateBalance;
   }
 
   public Integer getUpdateCredit() {
     return this.updateCredit;
   }
 
   public void setUpdateCredit(Integer updateCredit) {
     this.updateCredit = updateCredit;
   }
 
   public Integer getAccountCall() {
     return this.accountCall;
   }
 
   public void setAccountCall(Integer accountCall) {
     this.accountCall = accountCall;
   }
 
   public Integer getConcreteCall() {
     return this.concreteCall;
   }
 
   public void setConcreteCall(Integer concreteCall) {
     this.concreteCall = concreteCall;
   }
 
   public Integer getHolidayCall() {
     return this.holidayCall;
   }
 
   public void setHolidayCall(Integer holidayCall) {
     this.holidayCall = holidayCall;
   }
 
   public Integer getCallNotes() {
     return this.callNotes;
   }
 
   public void setCallNotes(Integer callNotes) {
     this.callNotes = callNotes;
   }
 
   public Integer getLeadingOut() {
     return this.leadingOut;
   }
 
   public void setLeadingOut(Integer leadingOut) {
     this.leadingOut = leadingOut;
   }
 
   public Integer getUploadData() {
     return this.uploadData;
   }
 
   public void setUploadData(Integer uploadData) {
     this.uploadData = uploadData;
   }
 
   public Integer getUploadShowData() {
     return this.uploadShowData;
   }
 
   public void setUploadShowData(Integer uploadShowData) {
     this.uploadShowData = uploadShowData;
   }
 
   public Integer getBatchAdd() {
     return this.batchAdd;
   }
 
   public void setBatchAdd(Integer batchAdd) {
     this.batchAdd = batchAdd;
   }
 
   public Integer getUploadHeadPhoto() {
     return this.uploadHeadPhoto;
   }
 
   public void setUploadHeadPhoto(Integer uploadHeadPhoto) {
     this.uploadHeadPhoto = uploadHeadPhoto;
   }
 
   public Integer getBackups() {
     return this.backups;
   }
 
   public void setBackups(Integer backups) {
     this.backups = backups;
   }
 
   public Integer getRecoverBackups() {
     return this.recoverBackups;
   }
 
   public void setRecoverBackups(Integer recoverBackups) {
     this.recoverBackups = recoverBackups;
   }
 
   public Integer getPackDown() {
     return this.packDown;
   }
 
   public void setPackDown(Integer packDown) {
     this.packDown = packDown;
   }
 
   public Integer getPhotoUpload() {
     return this.photoUpload;
   }
 
   public void setPhotoUpload(Integer photoUpload) {
     this.photoUpload = photoUpload;
   }
 
   public Integer getSafeCheck() {
     return this.safeCheck;
   }
 
   public void setSafeCheck(Integer safeCheck) {
     this.safeCheck = safeCheck;
   }
 
   public Integer getSpaceCheck() {
     return this.spaceCheck;
   }
 
   public void setSpaceCheck(Integer spaceCheck) {
     this.spaceCheck = spaceCheck;
   }
 }

