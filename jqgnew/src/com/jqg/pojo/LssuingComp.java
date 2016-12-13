package com.jqg.pojo;

import java.util.HashSet;
import java.util.Set;


/**
 * LssuingComp entity. @author MyEclipse Persistence Tools
 */

public class LssuingComp  implements java.io.Serializable {


    // Fields    

     private Integer compId;
     private String compInfo;
     private String showImg;
     private String borrowUse;
     private String compFund;
     private String compWinCon;
     private Set lssuings = new HashSet(0);


    // Constructors

    /** default constructor */
    public LssuingComp() {
    }

    
    /** full constructor */
    public LssuingComp(String compInfo, String showImg, String borrowUse, String compFund, String compWinCon, Set lssuings) {
        this.compInfo = compInfo;
        this.showImg = showImg;
        this.borrowUse = borrowUse;
        this.compFund = compFund;
        this.compWinCon = compWinCon;
        this.lssuings = lssuings;
    }

   
    // Property accessors

    public Integer getCompId() {
        return this.compId;
    }
    
    public void setCompId(Integer compId) {
        this.compId = compId;
    }

    public String getCompInfo() {
        return this.compInfo;
    }
    
    public void setCompInfo(String compInfo) {
        this.compInfo = compInfo;
    }

    public String getShowImg() {
        return this.showImg;
    }
    
    public void setShowImg(String showImg) {
        this.showImg = showImg;
    }

    public String getBorrowUse() {
        return this.borrowUse;
    }
    
    public void setBorrowUse(String borrowUse) {
        this.borrowUse = borrowUse;
    }

    public String getCompFund() {
        return this.compFund;
    }
    
    public void setCompFund(String compFund) {
        this.compFund = compFund;
    }

    public String getCompWinCon() {
        return this.compWinCon;
    }
    
    public void setCompWinCon(String compWinCon) {
        this.compWinCon = compWinCon;
    }


	public Set getLssuings() {
		return lssuings;
	}


	public void setLssuings(Set lssuings) {
		this.lssuings = lssuings;
	}

   








}