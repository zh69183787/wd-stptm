package com.wonders.stpt.overtimeWarn.service;

import java.util.List;

import com.wonders.stpt.overtimeWarn.entity.bo.HtXx;
import com.wonders.stpt.overtimeWarn.entity.bo.TDocReceive;
import com.wonders.stpt.overtimeWarn.entity.bo.TDocSend;
import com.wonders.stpt.overtimeWarn.entity.bo.TReceiveDirective;

public interface OverTimeService {

	

	
	//收文
	public List findRecordByPage(TDocReceive bean, int pageNo, int pageSize);
	public int recordCount(TDocReceive bean);

	public List findRecordByPage(HtXx bean, int pageNo, int pageSize);
	public int recordCount(HtXx bean);
	
	public List findRecordByPagei(HtXx bean, int pageNo, int pageSize);
	public int recordCounti(HtXx bean);
	
	public List findRecordByPaget(HtXx bean, int pageNo, int pageSize);
	public int recordCountt(HtXx bean);
	//发文
	public List findRecordByPage(TDocSend bean, int pageNo,int pageSize);
	public int recordCount(TDocSend bean);
	//呈批
	public List findRecordByPage(TReceiveDirective bean, int pageNo,int pageSize);
	public int recordCount(TReceiveDirective bean);
	//合同审批
	public List findRecordByPageL(HtXx bean, int pageNo,int pageSize);
	public int recordCountL(HtXx bean);
}
