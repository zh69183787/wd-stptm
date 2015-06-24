<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>资产详细信息</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<!--[if IE 6.0]>
    <script src="js/iepng.js" type="text/javascript"></script>
    <script type="text/javascript">
         EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
    </script>
<![endif]-->
<script src="../js/html5.js"></script>
<script src="../js/jquery-1.7.1.js"></script>
<script src="../js/jquery.formalize.js"></script>
<%String basePath = request.getContextPath(); %>
<script type="text/javascript">
         $(function(){
			$(".odd tr:odd").css("background","#fafafa");	
			
		});
</script>



</head>
<body>

<div class="main"><!--Ctrl-->
<div class="ctrl clearfix">
<div class="fl"><img src="../images/sideBar_arrow_left.jpg"
	width="46" height="30" alt="收起" ></div>
<div class="posi fl">
<ul>
	<li><a href="#">资产管理</a></li>
	<li><a href="#">资产动态管理</a></li>
	<li class="fin">资产详细信息</li>
</ul>
</div>
<div class="fr lit_nav">
<ul>
	<li><a class="print" href="#">打印</a></li>
	<li><a class="storage" href="#">网络硬盘</a></li>
	<li><a class="rss" href="#">订阅</a></li>
	<li><a class="chat" href="#">聊天</a></li>
	<li><a class="query" href="#">查询</a></li>
</ul>
</div>
</div>
<div class="mb10">
	<table width="100%" class="table_1">
		<thead>
			<th colspan="6" class="t_r">
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
			</th>
		</thead>
		<tbody>
			<tr><td style="background-color: #F2F2F2;text-align: right;font-weight: bold;"><b>资产基本信息</b></td><td colspan="5"></td></tr>
			<tr>
				<td class="t_r lableTd">资产编号</td>
				<td><s:property value="#request.assetInfoHistory.assetId"/></td>
				<td class="t_r lableTd">资产名称</td>
				<td><s:property value="#request.assetInfoHistory.assetName"/></td>
				<td class="t_r lableTd">生产厂商</td>
				<td>
					<s:property value="#request.manufacturer.name"/>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">规格</td>
				<td><s:property value="#request.assetInfoHistory.specification"/></td>
				<td class="t_r lableTd">型号</td>
				<td><s:property value="#request.assetInfoHistory.model"/></td>
				<td class="t_r lableTd">供应商</td>
				<td>
					<s:property value="#request.supplier.name"/>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">大类</td>
				<td>
					<s:property value="#request.type1.name"/>
				</td>
				<td class="t_r lableTd">中类</td>
				<td>
					<s:property value="#request.type2.name"/>
				</td>
				<td class="t_r lableTd">小类</td>
				<td>
					<s:property value="#request.type3.name"/>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">供应日期</td>
				<td><s:property value="#request.assetInfoHistory.provideTime"/></td>
				<td class="t_r lableTd">出厂日期</td>
				<td><s:property value="#request.assetInfoHistory.makeTime"/></td>
				<td class="t_r lableTd">预期使用寿命(月)</td>
				<td><s:property value="#request.assetInfoHistory.useLife"/></td>
			</tr>
			
			<!-- 
			<tr>
				<td class="t_r lableTd" colspan="">总类号 </td>
				<td colspan="5">
					<s:property value="#request.assetInfoHistory."/><input type="text" id="" name="zl" class="input_large" />
				</td>
			</tr>
			-->
			
			<tr>
				<td class="t_r lableTd"> 安装地点 </td>
				<td colspan="5"><s:property value="#request.assetInfoHistory.buildAddr"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd">备注 </td>
				<td colspan="3">
					<textarea rows="3" cols="4" name="memo" readonly="readonly" style="border: none;background-color: transparent;"><s:property value="#request.assetInfoHistory.memo"/></textarea>					
				</td>
				<td colspan="2"></td>
			</tr>
			<tr>
				<td class="t_r lableTd">是否入册</td>
				<td colspan="5">
					已入册
				</td>
			</tr>
			<tr><td colspan="6" style="background-color: #FFFFFF;height: 40px;"></td></tr>
			<tr><td style="background-color: #F2F2F2;text-align: right;font-weight: bold;"><b>资产所属信息</b></td><td colspan="5"></td></tr>
			<tr>
				<td class="t_r lableTd">所属线路</td>
				<td><s:property value="#request.line.name"/></td>
				<td class="t_r lableTd">所属车站</td>
				<td><s:property value="#request.station.name"/></td>
				<td class="t_r lableTd">权属单位</td>
				<td><s:property value="#request.unit.name"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd">使用责任人</td>
				<td><s:property value="#request.usePerson.userName"/></td>
				<td class="t_r lableTd">权属责任人</td>
				<td><s:property value="#request.owner.userName"/></td>
				<td class="t_r lableTd">使用单位</td>
				<td><s:property value="#request.userDuty.name"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd">开始使用日期</td>
				<td><s:property value="#request.assetInfoHistory.useTime"/></td>
				<td class="t_r lableTd">停止使用日期</td>
				<td><s:property value="#request.assetInfoHistory.stopuseTime"/></td>
				<td class="t_r lableTd">维护部门</td>
				<td><s:property value="#request.maintainDept.name"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd">保修期至</td>
				<td><s:property value="#request.assetInfoHistory.shelfLife"/></td>
				<td class="t_r lableTd">移交时间</td>
				<td><s:property value="#request.assetInfoHistory.handoverTime"/></td>
				<td class="t_r lableTd">购买时间</td>
				<td><s:property value="#request.assetInfoHistory.purchaseTime"/></td>
			</tr>
			<tr><td colspan="6" style="background-color: #FFFFFF;height: 40px;"></td></tr>
			<tr><td style="background-color: #F2F2F2;text-align: right;font-weight: bold;"><b>资产价值信息</b></td><td colspan="5"></td></tr>
			<tr>
				<td class="t_r lableTd">报废时间</td>
				<td><s:property value="#request.assetInfoHistory.discardTime"/></td>
				<td class="t_r lableTd">出厂价</td>
				<td><s:property value="#request.assetInfoHistory.leaveFactoryPrice"/></td>
				<td class="t_r lableTd">合同价</td>
				<td><s:property value="#request.assetInfoHistory.compactPrice"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd">原值(元)</td>
				<td><s:property value="#request.assetInfoHistory.yuanzhi"/></td>
				<td class="t_r lableTd">决算价(元)</td>
				<td><s:property value="#request.assetInfoHistory.finalAccountPrice"/></td>
				<td class="t_r lableTd">本期折旧(元)</td>
				<td><s:property value="#request.assetInfoHistory.benqizhijiu"/></td>
			</tr>
			
			<tr>
				<td class="t_r lableTd">累计折旧(元)</td>
				<td><s:property value="#request.assetInfoHistory.leijizhejiu"/></td>
				<td class="t_r lableTd">折旧方法</td>
				<td><s:property value="#request.depreciationWay.name"/></td>
				<td class="t_r lableTd">净值(元)</td>
				<td><s:property value="#request.assetInfoHistory.jingzhi"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd">当前使用状态</td>
				<td><s:property value="#request.useState.name"/></td>
				<td class="t_r lableTd">当前设备状态</td>
				<td><s:property value="#request.equipmentState.name"/></td>
				<td class="t_r lableTd">铭牌张贴部位 </td>
				<td><s:property value="#request.mpztbw.name"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd">残值率</td>
				<td><s:property value="#request.assetInfoHistory.czl"/>%</td>
			</tr>
		</tbody>
		<tr class="tfoot">
			<td colspan="6" class="t_r">
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
			</td>
		</tr>
	</table>
	
	<div class="mb10">
		<table width="100%" class="table_1">
			<thead>
				<th colspan="14">设备列表</th>
			</thead>
			<tbody>
				<s:if test="#request.equipmentList!=null && #request.equipmentList.size()>0">
					<tr class="tit">
						<td class="t_c" style="white-space: nowrap;">序号</td>
						<td class="t_c" style="white-space: nowrap;">设备编号</td>
						<td class="t_c" style="white-space: nowrap;">设备名称</td>
						<td class="t_c" colspan="2" style="white-space: nowrap;">生产厂家</td>
						<td class="t_c" colspan="2" style="white-space: nowrap;">产地</td>
						<td class="t_c" style="white-space: nowrap;">规格型号</td>
						<td colspan="3" style="white-space: nowrap;">操作</td>
					</tr>
					<s:iterator value="#request.equipmentList" id="equipment" status="st">
						<tr class="t_c">
							<td class="t_c"><s:property value="#st.index+1"/></td>
							<td class="t_c"><s:property value="#equipment.equipmentId"/></td>
							<td class="t_c"><s:property value="#equipment.equipmentName"/></td>
							<td class="t_c" colspan="2"><s:property value="#request.supplierNameList[#st.index]"/></td>
							<td class="t_c" colspan="2"><s:property value="#equipment.provenance"/></td>
							<td class="t_c"><s:property value="#equipment.productModel"/></td>
							<td class="t_c" colspan="3">
								<a href="<%=basePath%>/equipment/showView.action?id=<s:property value='#equipment.id'/>" target="_blank">查看详细</a>
							</td>
						</tr>
					</s:iterator>
				</s:if>
				<s:else>
					<tr><td colspan="14">无相关设备数据</td></tr>
				</s:else>
			</tbody>
			
			
		    <thead>
				<th colspan="14">资产历史快照</th>
			</thead>
		     <tbody>
		     	<s:if test="#request.assetInfoHistoryList!=null && #request.assetInfoHistoryList.size()>0">
		     		<tr class="tit">
						<td width="5%;" class="t_c" style="white-space: nowrap;">序号</td>
						<td width="10%;" class="t_c" style="white-space: nowrap;">资产编号</td>
						<td width="10%;" class="t_c" style="white-space: nowrap;">资产名称</td>
						<td width="10%;" class="t_c" style="white-space: nowrap;">大类</td>
						<td width="10%;" class="t_c" style="white-space: nowrap;">中类</td>
						<td width="10%;" class="t_c" style="white-space: nowrap;">小类</td>
						<td width="10%;" class="t_c" style="white-space: nowrap;">线路</td>
						<td width="10%;" class="t_c" style="white-space: nowrap;">车站</td>
						<td width="10%;" class="t_c" style="white-space: nowrap;">操作时间</td>
						<td width="5%;" class="t_c" style="white-space: nowrap;">是否入册</td>
						<td style="white-space: nowrap;">操作</td>
					</tr>
					<s:iterator value="#request.assetInfoHistoryList" id="assetInfoHistory" status="st">
						<tr class="t_c">
							<td class="t_c" style="white-space: nowrap;"><s:property value="#st.index+1"/></td>
							<td class="t_c" style="white-space: nowrap;"><s:property value="#assetInfoHistory.assetId"/></td>
							<td class="t_c" style="white-space: nowrap;"><s:property value="#assetInfoHistory.assetName"/></td>
							<td class="t_c" style="white-space: nowrap;"><s:property value="#request.type1HistoryList[#st.index]"/></td>
							<td class="t_c" style="white-space: nowrap;"><s:property value="#request.type2HistoryList[#st.index]"/></td>
							<td class="t_c" style="white-space: nowrap;"><s:property value="#request.type3HistoryList[#st.index]"/></td>
							<td class="t_c" style="white-space: nowrap;"><s:property value="#request.lineHistoryList[#st.index]"/></td>
							<td class="t_c" style="white-space: nowrap;"><s:property value="#request.stationHistoryList[#st.index]"/></td>
							<td class="t_c" style="white-space: nowrap;">
								<s:property value="#assetInfoHistory.operateTime.substring(0,10)"/>
							</td>
							<td class="t_c" style="white-space: nowrap;">
								<s:if test="#assetInfoHistory.registry==1">已入册</s:if>
								<s:else>待审核</s:else>
							</td>
							<td class="t_c" style="white-space: nowrap;">
								<s:if test="#assetInfoHistory.id==null">
									<a href="showAssetInfoHistory.action?paramId=<s:property value='#assetInfoHistory.assetId'/>&inventory=true" target="_blank">查看详细</a>
								</s:if>
								<s:else>
									<a href="showAssetInfoHistory.action?paramId=<s:property value='#assetInfoHistory.assetId'/>&paramHistoryId=<s:property value='#assetInfoHistory.id'/>" target="_blank">查看详细</a>
								</s:else>
								
							</td>
						</tr>
					</s:iterator>
		     	</s:if>
		     	<s:else>
		     		<tr><td colspan="14">无相关历史数据</td></tr>
		     	</s:else>
			</tbody>
			
			
			<thead>
				<th colspan="14">资产盘点记录</th>
			</thead>
		     <tbody>
		     	<s:if test="#request.taskCheckList!=null && #request.taskCheckList.size()>0">
		     		<tr class="tit">
						<td style="white-space: nowrap;width: 5%;">序号</td>
						<td style="white-space: nowrap;width: 15%;">资产编号</td>
						<td style="white-space: nowrap;width: 15%;">盘点时间 </td>
						<td style="white-space: nowrap;width: 10%;">盘点结果</td>
						<td style="white-space:nowrap;">操作</td>
						<td colspan="6"></td>
					</tr>
					<s:iterator value="#request.taskCheckList" id="taskCheckList" status="st">
						<tr class="t_c">
							<td class="t_c"><s:property value="#st.index+1"/></td>
							<td class="t_c"><s:property value="#taskCheckList.assetInfoId"/></td>
							<td class="t_c"><s:property value="#taskCheckList.operateDate"/></td>
							<td class="t_c"><s:property value="#request.resultList.get(#st.index)"/></td>
							<td class="t_c">
								<a href="<%=basePath%>/task/showCheckResult.action?id=<s:property value='#taskCheckList.taskId'/>" target="_blank">查看详细</a>
							</td>
							<td colspan="6"></td>
						</tr>
					</s:iterator>
		     	</s:if>
		     	<s:else>
		     		<tr><td colspan="14">无相关历史数据</td></tr>
		     	</s:else>
			</tbody>
			
			<tfoot> 
				<tr class="tfoot">
				      <td colspan="14">
				      	<div class="clearfix">
				      		<!--<s:if test="#request.page.totalSize==0"><span>无相关数据</span></s:if>-->
			       		</div>
			       	</td>
			     </tr>
		    </tfoot>
		</table>
	
	
<!--Table End-->
	</div>
</div>
</div>
</body>
</html>

