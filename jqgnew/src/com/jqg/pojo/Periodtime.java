 package com.jqg.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Periodtime
  implements Serializable
{
  private Integer periodTimeId;
  private String periodTimeName;
  private Set lssuingheralds = new HashSet(0);
  private Set lssuings = new HashSet(0);

  public Periodtime()
  {
  }

  public Periodtime(Integer periodTimeId)
  {
    this.periodTimeId = periodTimeId;
  }

  public Periodtime(Integer periodTimeId, String periodTimeName, Set lssuingheralds, Set lssuings)
  {
    this.periodTimeId = periodTimeId;
    this.periodTimeName = periodTimeName;
    this.lssuingheralds = lssuingheralds;
    this.lssuings = lssuings;
  }

  public Integer getPeriodTimeId()
  {
    return this.periodTimeId;
  }

  public void setPeriodTimeId(Integer periodTimeId) {
    this.periodTimeId = periodTimeId;
  }

  public String getPeriodTimeName() {
    return this.periodTimeName;
  }

  public void setPeriodTimeName(String periodTimeName) {
    this.periodTimeName = periodTimeName;
  }

  public Set getLssuingheralds() {
    return this.lssuingheralds;
  }

  public void setLssuingheralds(Set lssuingheralds) {
    this.lssuingheralds = lssuingheralds;
  }

  public Set getLssuings() {
    return this.lssuings;
  }

  public void setLssuings(Set lssuings) {
    this.lssuings = lssuings;
  }
}
