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

package com.wonders.stpt.asset.entity.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * AssetInfoʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-11
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_ASSET_INFO")
@SequenceGenerator(name="assetInfoSEQ",sequenceName="SEQ_T_ASSETINFO")
public class AssetInfo implements Serializable, BusinessObject {

	private Long id; // id

	private String assetId; // assetId

	private String assetName; // assetName

	private String assetPic; // assetPic

	private String benqizhijiu; // benqizhijiu

	private String bfcljlDbjl; // bfcljlDbjl

	private String bfcljlFsfy; // bfcljlFsfy

	private String bfcljlTime; // bfcljlTime

	private String bfcljlZrr; // bfcljlZrr

	private String buildAddr; // buildAddr

	private String cd; // cd

	private String compactPrice; // compactPrice

	private String czl; // czl

	private String depreciationWay; // depreciationWay

	private String discardTime; // discardTime

	private String dw; // dw

	private String dxpl; // dxpl

	private String equipmentState; // equipmentState

	private String finalAccountPrice; // finalAccountPrice

	private String gxgzjlDbjl; // gxgzjlDbjl

	private String gxgzjlFsfy; // gxgzjlFsfy

	private String gxgzjlTime; // gxgzjlTime

	private String gxgzjlZrr; // gxgzjlZrr

	private String handoverTime; // handoverTime

	//private String historyId; // historyId

	private String jingzhi; // jingzhi

	private String jszlqd; // jszlqd

	private String leaveFactoryPrice; // leaveFactoryPrice

	private String leijizhejiu; // leijizhejiu

	private String maintainDep; // maintainDep

	private String makeTime; // makeTime

	private String manufacturer; // manufacturer

	private String memo; // memo

	private String model; // model

	private String mpztbw; // mpztbw

	private String operateTime; // operateTime

	private String operator; // operator

	private String owner; // owner

	private String ownerDuty; // ownerDuty

	private String payTime; // payTime

	private String provideTime; // provideTime

	private String purchaseTime; // purchaseTime

	private String qtjlDbjl; // qtjlDbjl

	private String qtjlFsfy; // qtjlFsfy

	private String qtjlTime; // qtjlTime

	private String qtjlZrr; // qtjlZrr

	private String removed; // removed

	private String routeNum; // routeNum

	private String shebeiqingdan; // shebeiqingdan

	private String shelfLife; // shelfLife

	private String sjsynx; // sjsynx

	private String sl; // sl

	private String specification; // specification

	private String stationNum; // stationNum

	private String stopuseTime; // stopuseTime

	private String type1; // type1

	private String type2; // type2

	private String type3; // type3

	private String userDuty; // userDuty

	private String useLife; // useLife

	private String usePerson; // usePerson

	private String useState; // useState

	private String useTime; // useTime

	private String vendor; // vendor

	private String wxjlDbjl; // wxjlDbjl

	private String wxjlFsfy; // wxjlFsfy

	private String wxjlTime; // wxjlTime

	private String wxjlZrr; // wxjlZrr

	private String xh; // xh

	private String xjpdjlDbjl; // xjpdjlDbjl

	private String xjpdjlFsfy; // xjpdjlFsfy

	private String xjpdjlTime; // xjpdjlTime

	private String xjpdjlZrr; // xjpdjlZrr

	private String yuanzhi; // yuanzhi

	private String yzjl; // yzjl

	private String zcdbjlDbjl; // zcdbjlDbjl

	private String zcdbjlFsfy; // zcdbjlFsfy

	private String zcdbjlTime; // zcdbjlTime

	private String zcdbjlZrr; // zcdbjlZrr
	
	private String registry;	//是否入册

	public void setId(Long id) {
		this.id = id;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="assetInfoSEQ")
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	@Column(name = "ASSET_ID", nullable = false, length = 120)
	public String getAssetId() {
		return assetId;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	@Column(name = "ASSET_NAME", nullable = true, length = 400)
	public String getAssetName() {
		return assetName;
	}

	public void setAssetPic(String assetPic) {
		this.assetPic = assetPic;
	}

	@Column(name = "ASSET_PIC", nullable = true, length = 1000)
	public String getAssetPic() {
		return assetPic;
	}

	public void setBenqizhijiu(String benqizhijiu) {
		this.benqizhijiu = benqizhijiu;
	}

	@Column(name = "BENQIZHIJIU", nullable = true, length = 200)
	public String getBenqizhijiu() {
		return benqizhijiu;
	}

	public void setBfcljlDbjl(String bfcljlDbjl) {
		this.bfcljlDbjl = bfcljlDbjl;
	}

	@Column(name = "BFCLJL_DBJL", nullable = true, length = 100)
	public String getBfcljlDbjl() {
		return bfcljlDbjl;
	}

	public void setBfcljlFsfy(String bfcljlFsfy) {
		this.bfcljlFsfy = bfcljlFsfy;
	}

	@Column(name = "BFCLJL_FSFY", nullable = true, length = 120)
	public String getBfcljlFsfy() {
		return bfcljlFsfy;
	}

	public void setBfcljlTime(String bfcljlTime) {
		this.bfcljlTime = bfcljlTime;
	}

	@Column(name = "BFCLJL_TIME", nullable = true, length = 60)
	public String getBfcljlTime() {
		return bfcljlTime;
	}

	public void setBfcljlZrr(String bfcljlZrr) {
		this.bfcljlZrr = bfcljlZrr;
	}

	@Column(name = "BFCLJL_ZRR", nullable = true, length = 20)
	public String getBfcljlZrr() {
		return bfcljlZrr;
	}

	public void setBuildAddr(String buildAddr) {
		this.buildAddr = buildAddr;
	}

	@Column(name = "BUILD_ADDR", nullable = true, length = 1000)
	public String getBuildAddr() {
		return buildAddr;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	@Column(name = "CD", nullable = true, length = 400)
	public String getCd() {
		return cd;
	}

	public void setCompactPrice(String compactPrice) {
		this.compactPrice = compactPrice;
	}

	@Column(name = "COMPACT_PRICE", nullable = true, length = 120)
	public String getCompactPrice() {
		return compactPrice;
	}

	public void setCzl(String czl) {
		this.czl = czl;
	}

	@Column(name = "CZL", nullable = true, length = 200)
	public String getCzl() {
		return czl;
	}

	public void setDepreciationWay(String depreciationWay) {
		this.depreciationWay = depreciationWay;
	}

	@Column(name = "DEPRECIATION_WAY", nullable = true, length = 120)
	public String getDepreciationWay() {
		return depreciationWay;
	}

	public void setDiscardTime(String discardTime) {
		this.discardTime = discardTime;
	}

	@Column(name = "DISCARD_TIME", nullable = true, length = 120)
	public String getDiscardTime() {
		return discardTime;
	}

	public void setDw(String dw) {
		this.dw = dw;
	}

	@Column(name = "DW", nullable = true, length = 20)
	public String getDw() {
		return dw;
	}

	public void setDxpl(String dxpl) {
		this.dxpl = dxpl;
	}

	@Column(name = "DXPL", nullable = true, length = 20)
	public String getDxpl() {
		return dxpl;
	}

	public void setEquipmentState(String equipmentState) {
		this.equipmentState = equipmentState;
	}

	@Column(name = "EQUIPMENT_STATE", nullable = true, length = 120)
	public String getEquipmentState() {
		return equipmentState;
	}

	public void setFinalAccountPrice(String finalAccountPrice) {
		this.finalAccountPrice = finalAccountPrice;
	}

	@Column(name = "FINAL_ACCOUNT_PRICE", nullable = true, length = 120)
	public String getFinalAccountPrice() {
		return finalAccountPrice;
	}

	public void setGxgzjlDbjl(String gxgzjlDbjl) {
		this.gxgzjlDbjl = gxgzjlDbjl;
	}

	@Column(name = "GXGZJL_DBJL", nullable = true, length = 100)
	public String getGxgzjlDbjl() {
		return gxgzjlDbjl;
	}

	public void setGxgzjlFsfy(String gxgzjlFsfy) {
		this.gxgzjlFsfy = gxgzjlFsfy;
	}

	@Column(name = "GXGZJL_FSFY", nullable = true, length = 120)
	public String getGxgzjlFsfy() {
		return gxgzjlFsfy;
	}

	public void setGxgzjlTime(String gxgzjlTime) {
		this.gxgzjlTime = gxgzjlTime;
	}

	@Column(name = "GXGZJL_TIME", nullable = true, length = 60)
	public String getGxgzjlTime() {
		return gxgzjlTime;
	}

	public void setGxgzjlZrr(String gxgzjlZrr) {
		this.gxgzjlZrr = gxgzjlZrr;
	}

	@Column(name = "GXGZJL_ZRR", nullable = true, length = 20)
	public String getGxgzjlZrr() {
		return gxgzjlZrr;
	}

	public void setHandoverTime(String handoverTime) {
		this.handoverTime = handoverTime;
	}

	@Column(name = "HANDOVER_TIME", nullable = true, length = 120)
	public String getHandoverTime() {
		return handoverTime;
	}

	/*public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}

	@Column(name = "HISTORY_ID", nullable = true, length = 40)
	public String getHistoryId() {
		return historyId;
	}*/

	public void setJingzhi(String jingzhi) {
		this.jingzhi = jingzhi;
	}

	@Column(name = "JINGZHI", nullable = true, length = 120)
	public String getJingzhi() {
		return jingzhi;
	}

	public void setJszlqd(String jszlqd) {
		this.jszlqd = jszlqd;
	}

	@Column(name = "JSZLQD", nullable = true, length = 2000)
	public String getJszlqd() {
		return jszlqd;
	}

	public void setLeaveFactoryPrice(String leaveFactoryPrice) {
		this.leaveFactoryPrice = leaveFactoryPrice;
	}

	@Column(name = "LEAVE_FACTORY_PRICE", nullable = true, length = 120)
	public String getLeaveFactoryPrice() {
		return leaveFactoryPrice;
	}

	public void setLeijizhejiu(String leijizhejiu) {
		this.leijizhejiu = leijizhejiu;
	}

	@Column(name = "LEIJIZHEJIU", nullable = true, length = 120)
	public String getLeijizhejiu() {
		return leijizhejiu;
	}

	public void setMaintainDep(String maintainDep) {
		this.maintainDep = maintainDep;
	}

	@Column(name = "MAINTAIN_DEP", nullable = true, length = 120)
	public String getMaintainDep() {
		return maintainDep;
	}

	public void setMakeTime(String makeTime) {
		this.makeTime = makeTime;
	}

	@Column(name = "MAKE_TIME", nullable = true, length = 120)
	public String getMakeTime() {
		return makeTime;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Column(name = "MANUFACTURER", nullable = true, length = 1000)
	public String getManufacturer() {
		return manufacturer;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "MEMO", nullable = true, length = 4000)
	public String getMemo() {
		return memo;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "MODEL", nullable = true, length = 1000)
	public String getModel() {
		return model;
	}

	public void setMpztbw(String mpztbw) {
		this.mpztbw = mpztbw;
	}

	@Column(name = "MPZTBW", nullable = true, length = 100)
	public String getMpztbw() {
		return mpztbw;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "OPERATE_TIME", nullable = true, length = 120)
	public String getOperateTime() {
		return operateTime;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "OPERATOR", nullable = true, length = 40)
	public String getOperator() {
		return operator;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Column(name = "OWNER", nullable = true, length = 100)
	public String getOwner() {
		return owner;
	}

	public void setOwnerDuty(String ownerDuty) {
		this.ownerDuty = ownerDuty;
	}

	@Column(name = "OWNER_DUTY", nullable = true, length = 100)
	public String getOwnerDuty() {
		return ownerDuty;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	@Column(name = "PAY_TIME", nullable = true, length = 120)
	public String getPayTime() {
		return payTime;
	}

	public void setProvideTime(String provideTime) {
		this.provideTime = provideTime;
	}

	@Column(name = "PROVIDE_TIME", nullable = true, length = 120)
	public String getProvideTime() {
		return provideTime;
	}

	public void setPurchaseTime(String purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	@Column(name = "PURCHASE_TIME", nullable = true, length = 120)
	public String getPurchaseTime() {
		return purchaseTime;
	}

	public void setQtjlDbjl(String qtjlDbjl) {
		this.qtjlDbjl = qtjlDbjl;
	}

	@Column(name = "QTJL_DBJL", nullable = true, length = 100)
	public String getQtjlDbjl() {
		return qtjlDbjl;
	}

	public void setQtjlFsfy(String qtjlFsfy) {
		this.qtjlFsfy = qtjlFsfy;
	}

	@Column(name = "QTJL_FSFY", nullable = true, length = 120)
	public String getQtjlFsfy() {
		return qtjlFsfy;
	}

	public void setQtjlTime(String qtjlTime) {
		this.qtjlTime = qtjlTime;
	}

	@Column(name = "QTJL_TIME", nullable = true, length = 60)
	public String getQtjlTime() {
		return qtjlTime;
	}

	public void setQtjlZrr(String qtjlZrr) {
		this.qtjlZrr = qtjlZrr;
	}

	@Column(name = "QTJL_ZRR", nullable = true, length = 20)
	public String getQtjlZrr() {
		return qtjlZrr;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 20)
	public String getRemoved() {
		return removed;
	}

	public void setRouteNum(String routeNum) {
		this.routeNum = routeNum;
	}

	@Column(name = "ROUTE_NUM", nullable = true, length = 120)
	public String getRouteNum() {
		return routeNum;
	}

	public void setShebeiqingdan(String shebeiqingdan) {
		this.shebeiqingdan = shebeiqingdan;
	}

	@Column(name = "SHEBEIQINGDAN", nullable = true, length = 2000)
	public String getShebeiqingdan() {
		return shebeiqingdan;
	}

	public void setShelfLife(String shelfLife) {
		this.shelfLife = shelfLife;
	}

	@Column(name = "SHELF_LIFE", nullable = true, length = 120)
	public String getShelfLife() {
		return shelfLife;
	}

	public void setSjsynx(String sjsynx) {
		this.sjsynx = sjsynx;
	}

	@Column(name = "SJSYNX", nullable = true, length = 20)
	public String getSjsynx() {
		return sjsynx;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	@Column(name = "SL", nullable = true, length = 20)
	public String getSl() {
		return sl;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	@Column(name = "SPECIFICATION", nullable = true, length = 1000)
	public String getSpecification() {
		return specification;
	}

	public void setStationNum(String stationNum) {
		this.stationNum = stationNum;
	}

	@Column(name = "STATION_NUM", nullable = true, length = 120)
	public String getStationNum() {
		return stationNum;
	}

	public void setStopuseTime(String stopuseTime) {
		this.stopuseTime = stopuseTime;
	}

	@Column(name = "STOPUSE_TIME", nullable = true, length = 120)
	public String getStopuseTime() {
		return stopuseTime;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	@Column(name = "TYPE_1", nullable = true, length = 60)
	public String getType1() {
		return type1;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	@Column(name = "TYPE_2", nullable = true, length = 60)
	public String getType2() {
		return type2;
	}

	public void setType3(String type3) {
		this.type3 = type3;
	}

	@Column(name = "TYPE_3", nullable = true, length = 60)
	public String getType3() {
		return type3;
	}

	public void setUserDuty(String userDuty) {
		this.userDuty = userDuty;
	}

	@Column(name = "USER_DUTY", nullable = true, length = 200)
	public String getUserDuty() {
		return userDuty;
	}

	public void setUseLife(String useLife) {
		this.useLife = useLife;
	}

	@Column(name = "USE_LIFE", nullable = true, length = 120)
	public String getUseLife() {
		return useLife;
	}

	public void setUsePerson(String usePerson) {
		this.usePerson = usePerson;
	}

	@Column(name = "USE_PERSON", nullable = true, length = 30)
	public String getUsePerson() {
		return usePerson;
	}

	public void setUseState(String useState) {
		this.useState = useState;
	}

	@Column(name = "USE_STATE", nullable = true, length = 200)
	public String getUseState() {
		return useState;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	@Column(name = "USE_TIME", nullable = true, length = 120)
	public String getUseTime() {
		return useTime;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	@Column(name = "VENDOR", nullable = true, length = 400)
	public String getVendor() {
		return vendor;
	}

	public void setWxjlDbjl(String wxjlDbjl) {
		this.wxjlDbjl = wxjlDbjl;
	}

	@Column(name = "WXJL_DBJL", nullable = true, length = 100)
	public String getWxjlDbjl() {
		return wxjlDbjl;
	}

	public void setWxjlFsfy(String wxjlFsfy) {
		this.wxjlFsfy = wxjlFsfy;
	}

	@Column(name = "WXJL_FSFY", nullable = true, length = 120)
	public String getWxjlFsfy() {
		return wxjlFsfy;
	}

	public void setWxjlTime(String wxjlTime) {
		this.wxjlTime = wxjlTime;
	}

	@Column(name = "WXJL_TIME", nullable = true, length = 60)
	public String getWxjlTime() {
		return wxjlTime;
	}

	public void setWxjlZrr(String wxjlZrr) {
		this.wxjlZrr = wxjlZrr;
	}

	@Column(name = "WXJL_ZRR", nullable = true, length = 20)
	public String getWxjlZrr() {
		return wxjlZrr;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	@Column(name = "XH", nullable = true, length = 60)
	public String getXh() {
		return xh;
	}

	public void setXjpdjlDbjl(String xjpdjlDbjl) {
		this.xjpdjlDbjl = xjpdjlDbjl;
	}

	@Column(name = "XJPDJL_DBJL", nullable = true, length = 100)
	public String getXjpdjlDbjl() {
		return xjpdjlDbjl;
	}

	public void setXjpdjlFsfy(String xjpdjlFsfy) {
		this.xjpdjlFsfy = xjpdjlFsfy;
	}

	@Column(name = "XJPDJL_FSFY", nullable = true, length = 120)
	public String getXjpdjlFsfy() {
		return xjpdjlFsfy;
	}

	public void setXjpdjlTime(String xjpdjlTime) {
		this.xjpdjlTime = xjpdjlTime;
	}

	@Column(name = "XJPDJL_TIME", nullable = true, length = 60)
	public String getXjpdjlTime() {
		return xjpdjlTime;
	}

	public void setXjpdjlZrr(String xjpdjlZrr) {
		this.xjpdjlZrr = xjpdjlZrr;
	}

	@Column(name = "XJPDJL_ZRR", nullable = true, length = 20)
	public String getXjpdjlZrr() {
		return xjpdjlZrr;
	}

	public void setYuanzhi(String yuanzhi) {
		this.yuanzhi = yuanzhi;
	}

	@Column(name = "YUANZHI", nullable = true, length = 120)
	public String getYuanzhi() {
		return yuanzhi;
	}

	public void setYzjl(String yzjl) {
		this.yzjl = yzjl;
	}

	@Column(name = "YZJL", nullable = true, length = 20)
	public String getYzjl() {
		return yzjl;
	}

	public void setZcdbjlDbjl(String zcdbjlDbjl) {
		this.zcdbjlDbjl = zcdbjlDbjl;
	}

	@Column(name = "ZCDBJL_DBJL", nullable = true, length = 100)
	public String getZcdbjlDbjl() {
		return zcdbjlDbjl;
	}

	public void setZcdbjlFsfy(String zcdbjlFsfy) {
		this.zcdbjlFsfy = zcdbjlFsfy;
	}

	@Column(name = "ZCDBJL_FSFY", nullable = true, length = 120)
	public String getZcdbjlFsfy() {
		return zcdbjlFsfy;
	}

	public void setZcdbjlTime(String zcdbjlTime) {
		this.zcdbjlTime = zcdbjlTime;
	}

	@Column(name = "ZCDBJL_TIME", nullable = true, length = 60)
	public String getZcdbjlTime() {
		return zcdbjlTime;
	}

	public void setZcdbjlZrr(String zcdbjlZrr) {
		this.zcdbjlZrr = zcdbjlZrr;
	}

	@Column(name = "ZCDBJL_ZRR", nullable = true, length = 20)
	public String getZcdbjlZrr() {
		return zcdbjlZrr;
	}
	@Column(name = "REGISTRY",nullable = true, length = 1)
	public String getRegistry() {
		return registry;
	}
	
	public void setRegistry(String registry) {
		this.registry = registry;
	}
	
}
