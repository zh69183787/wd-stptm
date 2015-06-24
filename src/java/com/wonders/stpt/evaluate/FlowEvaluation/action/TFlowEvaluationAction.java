/** 
 * Copyright (c) 1995-2011 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of WondersGroup.
 * You shall not disclose such Confidential Information and shall use it only in accordance 
 * with the terms of the license agreement you entered into with WondersGroup. 
 *
 */

package com.wonders.stpt.evaluate.FlowEvaluation.action;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.BeanUtils;

import com.wonders.stpt.evaluate.FlowEvaluation.entity.bo.TFlowEvaluation;
import com.wonders.stpt.evaluate.FlowEvaluation.entity.vo.TFlowEvaluationVO;
import com.wonders.stpt.evaluate.FlowEvaluation.service.TFlowEvaluationService;
import com.wonders.stpt.evaluate.FlowEvaluationItem.entity.bo.TFlowEvaluationItem;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-4-11
 * @author modify by $Author$
 * @since 1.0
 */
@SuppressWarnings("serial")
public class TFlowEvaluationAction extends BaseAjaxAction {
	private TFlowEvaluationVO tFlowEvaluationVO = new TFlowEvaluationVO();
	private TFlowEvaluationService flowEvaluationService;
	private Map<String, String> flowMap = new TreeMap<String, String>();
	
	/**
	 * @author ycl
	 * @description 初始化流程
	 */
	public TFlowEvaluationAction() {
		flowMap.put("001", "流程1");
		flowMap.put("002", "流程2");
		flowMap.put("003", "流程3");
		flowMap.put("004", "流程4");
		flowMap.put("005", "流程5");
	}

	public Object getValueByParamName(Object obj, String paramName) {
		if (paramName == null || paramName.trim().length() == 0) {
			return null;
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		String varName = null;
		for (int i = 0; i < fields.length; i++) {
			varName = fields[i].getName();
			if (paramName.equalsIgnoreCase(varName)) {
				boolean accessFlag = fields[i].isAccessible();
				fields[i].setAccessible(true);
				Object res = null;
				try {
					res = fields[i].get(obj);

				} catch (Exception e) {
				}
				fields[i].setAccessible(accessFlag);
				return res;
			}
		}
		return null;
	}

	private TFlowEvaluation convertVOToBO(TFlowEvaluationVO tFlowEvaluationVO) {
		TFlowEvaluation tFlowEvaluation = new TFlowEvaluation();
		BeanUtils.copyProperties(tFlowEvaluationVO, tFlowEvaluation,new String[] { "id" });
		return tFlowEvaluation;
	}

	public TFlowEvaluationVO getTFlowEvaluationVO() {
		return tFlowEvaluationVO;
	}

	public void setTFlowEvaluationVO(TFlowEvaluationVO tFlowEvaluationVO) {
		this.tFlowEvaluationVO = tFlowEvaluationVO;
	}

	public void setFlowEvaluationService(
			TFlowEvaluationService tFlowEvaluationService) {
		this.flowEvaluationService = tFlowEvaluationService;
	}
	
	/**
	 * @author ycl
	 * @description 显示列表页面
	 */
	public String showFlowEvaluations(){
		
		int size = 10;
		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		
		if(tFlowEvaluationVO.getFlowId()!=null && !"".equals(tFlowEvaluationVO.getFlowId())){
			filter.put("flowId", tFlowEvaluationVO.getFlowId());
		}
		if(tFlowEvaluationVO.getGoodMediumBad()!=null && !"".equals(tFlowEvaluationVO.getGoodMediumBad())){
			filter.put("goodMediumBad", tFlowEvaluationVO.getGoodMediumBad());
		}
		if(tFlowEvaluationVO.getEvaluateDept()!=null && !"".equals(tFlowEvaluationVO.getEvaluateDept())){
			filter.put("evaluateDept", tFlowEvaluationVO.getEvaluateDept());
		}
		if(tFlowEvaluationVO.getBeEvaluatedDept()!=null && !"".equals(tFlowEvaluationVO.getBeEvaluatedDept())){
			filter.put("beEvaluatedDept", tFlowEvaluationVO.getBeEvaluatedDept());
		}
		
		String sDate = this.servletRequest.getParameter("startDate");
		String eDate = this.servletRequest.getParameter("endDate");
		if(sDate!=null && !sDate.equals("")){
			filter.put("startDate", sDate);
		}
		if(eDate!=null && !eDate.equals("")){
			filter.put("endDate", eDate);
		}
		int pageNo = 1;
		String no = this.servletRequest.getParameter("pageNo"); 
		if(no!=null && !no.equals("")){
			pageNo = Integer.valueOf(no);
		}
		Page page = flowEvaluationService.findTFlowEvaluationByPage(filter, pageNo,size);
		this.servletRequest.setAttribute("startDate", sDate);
		this.servletRequest.setAttribute("endDate", eDate);
		this.servletRequest.setAttribute("page",page);
		this.servletRequest.setAttribute("flowMap", flowMap);
		
		
		return "showFlowEvaluations";
	}
	
	/**
	 * @author ycl
	 * @description 打开新增页面
	 */
	public String showAddPage(){
		String flowId = servletRequest.getParameter("flowId");
		List<String> evaluationList = new ArrayList<String>();
		this.servletRequest.setAttribute("flowId", flowId);
		this.servletRequest.setAttribute("flowMap",flowMap);
		
		List<TFlowEvaluationItem> itemList = flowEvaluationService.findItemByFlowId(flowId);
		if(itemList!=null && itemList.size()>0){
			for(int i=0; i<itemList.size(); i++){
				evaluationList.add(itemList.get(i).getName());
			}
			this.servletRequest.setAttribute("evaluationList", evaluationList);
		}
		return "showAddPage";
	}
	
	
	/**
	 * @author ycl
	 * @description 保存新增
	 */
	public String saveFlowEvaluaton(){
		String names = this.servletRequest.getParameter("names");
		String scores = this.servletRequest.getParameter("scores");
		String comments = this.servletRequest.getParameter("comments");
		String []nameArray = null;
		String []scoreArray = null;
		String []commentArray = null;
		if(names!=null && !names.equals("")){
			nameArray = names.split("——");
		}
		if(scores!=null && !scores.equals("")){
			scoreArray = scores.split("——");
		}
		if(comments!=null && !comments.equals("")){
			commentArray = comments.split("——");
			for(int i=0;i<commentArray.length;i++){
				commentArray[i] = commentArray[i].replace("|", "");
			}
		}
		//创建xml格式的string字符串
		Document document = DocumentHelper.createDocument();
		Element evaluateElem = document.addElement("evaluation");
		TFlowEvaluation evaluationBo = convertVOToBO(tFlowEvaluationVO);
		if(nameArray!=null && nameArray.length>0 && nameArray.length == scoreArray.length && scoreArray.length == commentArray.length){
			for(int i =0;i<nameArray.length;i++){
				Element evaluetionElem = evaluateElem.addElement("item");
				Element idElem = evaluetionElem.addElement("id");
					idElem.addText(String.valueOf((i+1)));
				Element nameElem = evaluetionElem.addElement("name");
					nameElem.addText(nameArray[i]);
				Element scoreElem = evaluetionElem.addElement("score");
					scoreElem.addText(String.valueOf(scoreArray[i]));
				Element commentElem = evaluetionElem.addElement("comment");
					commentElem.addText(commentArray[i]);
			}
			evaluationBo.setEvaluationContent(document.asXML());
		}
		evaluationBo.setFlowName(flowMap.get(evaluationBo.getFlowId()));
		evaluationBo.setEvaluationTime(new Date());
		evaluationBo.setUpdateTime(new Date());
		evaluationBo.setUpdatePerson("");		//更新人，暂时没有，置空
		evaluationBo.setEvaluationPerson("");		//评价人，暂时没有，置空
		evaluationBo.setRemoved("1");
		flowEvaluationService.addTFlowEvaluation(evaluationBo);
		
		return "saveFlowEvaluaton";
	}
	
	/**
	 * @author ycl
	 * @description 根据id查询定量评价
	 * @return
	 */
	public String findFlowById(){
		List<String> evaluationList = new ArrayList<String>();
		String flowId = servletRequest.getParameter("flowId");
		List<TFlowEvaluationItem> itemList = flowEvaluationService.findItemByFlowId(flowId);
		if(itemList!=null && itemList.size()!=0){
			for(int i=0; i<itemList.size(); i++){
				evaluationList.add(itemList.get(i).getName());
			}
			this.servletRequest.setAttribute("evaluationArray", evaluationList);
			
			String jsonData = VOUtils.getJsonDataFromCollection(evaluationList);
			createJSonData(jsonData);
		}
		
		return AJAX;
	}
	
	/**
	 * @author ycl
	 * @description 根据主键删除
	 * @return
	 */
	public String deleteFlowEvaluation(){
		String id = servletRequest.getParameter("id");
		flowEvaluationService.deleteFlowEvaluationById(id);
		return AJAX;
	}
	
	/**
	 * @author ycl
	 * @description 根据主键查询
	 */
	@SuppressWarnings("unchecked")
	public String showFlowEvaluation(){
		Document document = null;
		String id = servletRequest.getParameter("id");
		TFlowEvaluation flowEvaluation = flowEvaluationService.findTFlowEvaluationById(id);
		String xmlString = flowEvaluation.getEvaluationContent();
		if(flowEvaluation!=null && xmlString!=null && !xmlString.equals("")){
			try {
				document = DocumentHelper.parseText(xmlString);
				List<Element> nameList = document.selectNodes("//item/name");
				List<Element> scoreList = document.selectNodes("//item/score");
				List<Element> commentList = document.selectNodes("//item/comment");
				
				if(nameList!=null && scoreList!=null && commentList!=null){
					if(nameList.size()==scoreList.size() && nameList.size()==commentList.size()){
						String []names = new String[nameList.size()];
						String []scores = new String[nameList.size()];
						String []comments = new String[nameList.size()];
						for(int i=0; i<nameList.size(); i++){
							names[i] = nameList.get(i).getText();
							scores[i] = scoreList.get(i).getText();
							comments[i] = commentList.get(i).getText();
						}
						this.servletRequest.setAttribute("names", names);
						this.servletRequest.setAttribute("scores", scores);
						this.servletRequest.setAttribute("comments", comments);
					}
				}
			} catch (DocumentException e) {
				System.out.println("---------xml格式的string字符串转化错误----------");
			}
		}
		servletRequest.setAttribute("flowEvaluation", flowEvaluation);
		return "showFlowEvaluation";
	}
	
	/**
	 * @author ycl
	 * @description 跳转到编辑页面
	 */
	@SuppressWarnings("unchecked")
	public String showEditPage(){
		Document document = null;
		String id = servletRequest.getParameter("id");
		TFlowEvaluation flowEvaluation = flowEvaluationService.findTFlowEvaluationById(id);
		servletRequest.setAttribute("flowEvaluation", flowEvaluation);
		String xmlString = flowEvaluation.getEvaluationContent();
		if(flowEvaluation!=null && xmlString!=null && !xmlString.equals("")){
			try {
				document = DocumentHelper.parseText(xmlString);
				List<Element> nameList = document.selectNodes("//item/name");
				List<Element> scoreList = document.selectNodes("//item/score");
				List<Element> commentList = document.selectNodes("//item/comment");
				if(nameList!=null && scoreList!=null && commentList!=null){
					if(nameList.size()==scoreList.size() && nameList.size()==commentList.size()){
						String []names = new String[nameList.size()];
						String []scores = new String[nameList.size()];
						String []comments = new String[nameList.size()];
						for(int i=0; i<nameList.size(); i++){
							names[i] = nameList.get(i).getText();
							scores[i] = scoreList.get(i).getText();
							comments[i] = commentList.get(i).getText();
						}
						this.servletRequest.setAttribute("names", names);
						this.servletRequest.setAttribute("scores", scores);
						this.servletRequest.setAttribute("comments", comments);
					}
				}
			} catch (DocumentException e) {
				System.out.println("---------xml格式的string字符串转化错误----------");
			}
		}else{				 
			List<String> evaluationList = new ArrayList<String>();
			String flowId = flowEvaluation.getFlowId();
			List<TFlowEvaluationItem> itemList = flowEvaluationService.findItemByFlowId(flowId);
			if(itemList!=null && itemList.size()!=0){
				for(int i=0; i<itemList.size(); i++){
					evaluationList.add(itemList.get(i).getName());
				}
				this.servletRequest.setAttribute("evaluationList", evaluationList);
			}
		}
		return "showEditPage";
	}
	
	/**
	 * @author ycl
	 * @depcription 保存更新
	 */
	public String updateFlowEvaluation(){
		String names = this.servletRequest.getParameter("names");
		String scores = this.servletRequest.getParameter("scores");
		String comments = this.servletRequest.getParameter("comments");
		String []nameArray = null;
		String []scoreArray = null;
		String []commentArray = null;
		if(names!=null && !names.equals("")){
			nameArray = names.split("——");
		}
		if(scores!=null && !scores.equals("")){
			scoreArray = scores.split("——");
		}
		if(comments!=null && !comments.equals("")){
			commentArray = comments.split("——");
			for(int i=0;i<commentArray.length;i++){
				commentArray[i] = commentArray[i].replace("|", "");
			}
		}
		//创建xml格式的string字符串
		Document document = DocumentHelper.createDocument();
		Element evaluateElem = document.addElement("evaluation");
		TFlowEvaluation flowEvaluationBO = flowEvaluationService.findTFlowEvaluationById(tFlowEvaluationVO.getId());
		flowEvaluationBO.setId(tFlowEvaluationVO.getId());
		flowEvaluationBO.setEvaluateDept(tFlowEvaluationVO.getEvaluateDept());
		flowEvaluationBO.setBeEvaluatedDept(tFlowEvaluationVO.getBeEvaluatedDept());
		flowEvaluationBO.setGoodMediumBad(tFlowEvaluationVO.getGoodMediumBad());
		flowEvaluationBO.setUpdateTime(new Date());
		if(nameArray!=null && nameArray.length>0 && nameArray.length == scoreArray.length && scoreArray.length == commentArray.length){
			for(int i =0;i<nameArray.length;i++){
				Element evaluetionElem = evaluateElem.addElement("item");
				Element idElem = evaluetionElem.addElement("id");
					idElem.addText(String.valueOf((i+1)));
				Element nameElem = evaluetionElem.addElement("name");
					nameElem.addText(nameArray[i]);
				Element scoreElem = evaluetionElem.addElement("score");
					scoreElem.addText(String.valueOf(scoreArray[i]));
				Element commentElem = evaluetionElem.addElement("comment");
					commentElem.addText(commentArray[i]);
			}
			flowEvaluationBO.setEvaluationContent(document.asXML());
		}
		flowEvaluationService.updateTFlowEvaluation(flowEvaluationBO);
		
		return "updateFlowEvaluation";
	}
	
	
	
}
