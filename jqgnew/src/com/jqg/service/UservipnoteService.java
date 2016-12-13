package com.jqg.service;

import com.jqg.pojo.Uservipnote;

import java.util.List;

public abstract interface UservipnoteService
{
  public abstract boolean addUservipnote(Uservipnote paramUservipnote)
    throws Exception;

  public abstract boolean updateUservipnote(Uservipnote paramUservipnote)
    throws Exception;

  public abstract boolean deleteUservipnote(Uservipnote paramUservipnote)
    throws Exception;

  public abstract List<Uservipnote> findUservipnotes()
    throws Exception;

  public abstract Uservipnote findUservipByUserVipNoteId(int paramInt)
    throws Exception;
}
