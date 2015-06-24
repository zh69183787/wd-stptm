
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

package com.wonders.stpt.myNotice.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import com.wonders.stpt.csUser.entity.bo.CsUser;
import com.wonders.stpt.dbFollow.entity.bo.TMsgUserMassage;
import com.wonders.stpt.myNotice.dao.TMsgUsermessageDao;
import com.wonders.stpt.myNotice.entity.bo.MsgUserMassage;
import com.wonders.stpt.myNotice.service.TMsgUsermessageService;
import com.wonders.stpt.shortMseeage.model.bo.ShortMessage;
import com.wonders.stpt.shortMseeage.model.bo.ShortMessageTask;
import com.wonders.stpt.util.AuthorUtil;
import com.wonders.stpt.util.CommonDao;
import com.wonders.stpt.util.GlobalFunc;
import com.wonders.stpt.util.PageUtils;
import com.wonders.stpt.util.RandomGUID;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;
import com.wondersgroup.framework.security.bo.SecurityUser;
import org.apache.commons.lang.StringUtils;

/**
 * @author ycl
 * @author modify by $Author$
 * @version $Revision$
 * @date 2014-6-23
 * @since 1.0
 */

public class TMsgUsermessageServiceImpl implements TMsgUsermessageService {

    private TMsgUsermessageDao msgUsermessageDao;


    public TMsgUsermessageDao getMsgUsermessageDao() {
        return msgUsermessageDao;
    }

    public void setMsgUsermessageDao(TMsgUsermessageDao msgUsermessageDao) {
        this.msgUsermessageDao = msgUsermessageDao;
    }

    public void addTMsgUsermessage(MsgUserMassage tMsgUsermessage) {
        msgUsermessageDao.save(tMsgUsermessage);
    }

    public void deleteTMsgUsermessage(MsgUserMassage tMsgUsermessage) {
        msgUsermessageDao.delete(tMsgUsermessage);
    }

    public MsgUserMassage findTMsgUsermessageById(Long id) {

        return msgUsermessageDao.findMessageById(id);
    }

    public void updateTMsgUsermessage(MsgUserMassage tMsgUsermessage) {
        msgUsermessageDao.update(tMsgUsermessage);
    }

    public Page findTMsgUsermessageByPage(int pageNo, int pageSize) {
        Page page = msgUsermessageDao.findAllWithPage(pageNo, pageSize);
        return page;
    }

    public List findTMsgUsermessageByPage(String userId, MsgUserMassage bean, int pageNo,
                                          int pageSize) {
        return msgUsermessageDao.findTMsgUsermessageByPage(userId, bean, pageNo, pageSize);
    }

    @Override
    public int recordCount(String userId, MsgUserMassage bean) {
        return msgUsermessageDao.recordCount(userId, bean);
    }

    @Override
    public int deleteItem(String msgType, Long id) {
        return msgUsermessageDao.deleteItem(msgType, id);
    }

    @Override
    public void updateMsgState(MsgUserMassage bean, String state) {
        msgUsermessageDao.updateMsgState(bean, state);

    }

    @Override
    public void sendMsg() {
        try {
            String yewuid = "";
            String strContent = PageUtils.GetParameter("txt_Content");
            String strTitle = PageUtils.GetParameter("txt_Title");
            String type = PageUtils.GetParameter("type");
            String strSIDList = PageUtils.GetParameter("SID_LIST");
            String types = "%&$";
            String sendFuns = PageUtils.GetParameter("sendFuns");
            String sjhm = PageUtils.GetParameter("sjhm");
            types = PageUtils.GetParameter("types");
            if ("".equals(types)) {
                types = "%&$";
            }
            String[] sid = null;

            // 获得附件信息
            //ArrayList fileInfo = msgForm.getAlFile();
            // 附件在收文表中的值

            String attachMent = ",";

            // 标志是否存在附件
            int has_atta = 0;


            if (!("%&$".equals(types))) {
                String[] sd = strSIDList.split(",");
                String st = "";
                for (int i = 0; i < sd.length; i++) {
                    st += "'" + sd[i].substring((sd[i].indexOf("/") + 1)) + "',";
                }
                strSIDList = GlobalFunc.getIdByLoginName(st.substring(0, st.lastIndexOf(",")));

            }


            if (strSIDList != null && !"".equals(strSIDList)) {
                sid = strSIDList.split(",");
                if (type.equals("send")) {
                    for (int i = 0; i < sid.length; i++) {
                        String receiver = "";
                        String id = "";
                        String strTitle1 = "";
                        try {
                            RandomGUID guid = new RandomGUID();
                            id = guid.toString();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        SecurityUser user = GlobalFunc.loadUserById(sid[i]);
                        receiver = user.getName() + "|" + user.getLoginName() + "|" + user.getMobile1();
                        //mobile = GlobalFunc.getByIdMobile(sid[i]);

                        strTitle1 = "新通知提醒。标题：" + strTitle;
                        GlobalFunc.insertMsg(receiver, strTitle1
                                , (AbstractHibernateDaoImpl) CommonDao.GetNewDatabaseDao());
                    }


                }
            }
            if (sendFuns.equals("1")) {// 需要发送手机短信

                String[] mobile = sjhm.split(",");
                for (int i = 0; i < mobile.length; i++) {
                    String id = "";
                    try {
                        RandomGUID guid = new RandomGUID();
                        id = guid.toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String receiver = "无|无|" + mobile[i];
                    strContent = "手机短信通知。发送号码为:" + sjhm;
                    GlobalFunc.insertMsg(receiver, strTitle
                            , (AbstractHibernateDaoImpl) CommonDao.GetNewDatabaseDao());
                }
            }


            // ��ò�����Ա����Ϣ

            // �ڲ����

            String useId = AuthorUtil.GetLoginUserID();


            String sidList = ",";
            if (strSIDList != null && !"".equals(strSIDList)) {

                String[] strSID = strSIDList.split(",");

                String strMode = "186";

                // ��Զ��ķ���

                if (strSID.length > 1)
                    strMode = "187";

                for (int i = 0; i < strSID.length; i++) {

                    // 插入的站内短信的SID

                    attachMent = ",";

					/*if (fileInfo != null) {// 有附件信息，保存附件
                        has_atta = 1
						int j=0;
						while (j < fileInfo.size()) {
							// 附件在收文表中的值

							attachMent += fileInfo.get(j + 3).toString() + ",";
							j = j + 5;
						}
					}*/


                    MsgUserMassage msg = new MsgUserMassage();
                    msg.setEmpidrec(Long.parseLong(strSID[i]));
                    msg.setEmpidsend(Long.parseLong(useId));
                    msg.setTitle(strTitle);
                    msg.setContent(strContent);
                    msg.setSendmode(Long.parseLong(strMode));
                    msg.setState(0);
                    msg.setHasAtta(has_atta);
                    msg.setAttachment(attachMent);


                    if (sid != null && sendFuns.equals("1")) {
                        String mobile = GlobalFunc.getByIdMobile(sid[i]);
                        if ("send".equals(type) && (!"".equals(mobile)))
                            msg.setMsgcount(1);
                    }
                    msgUsermessageDao.saveOrUpdate(msg);
                    long id = msg.getSid();
                    yewuid += (id + ",");
                    sidList += id + ","; //保存插入的站内短信SID，准备记录到附件表中
                    //dm.insert(dbnew);
                    // 善后处理相关
                }
            }


            // 插入手机短信通知类型的数据

            if (sendFuns.equals("1")) {
                TMsgUserMassage msg2 = new TMsgUserMassage();
                msg2.setEmpidrec(0);
                msg2.setEmpidsend(Long.parseLong(useId));
                msg2.setTitle(strTitle);
                msg2.setContent(strContent);
                msg2.setSendmode(187);
                msg2.setState(0);
                msg2.setMsgcount(sjhm.split(",").length);
                msgUsermessageDao.saveOrUpdate(msg2);

                String id = msg2.getSid() + "";

                yewuid += (id + ",");


            }
            PageUtils.GetRequest().getSession().setAttribute("yewuId", yewuid);
            PageUtils.GetRequest().getSession().setAttribute("displayName", strTitle);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void sendShortMsgNotice(CsUser sender, CsUser receive, String content) {
        String receiver = receive.getName() + "|" + receive.getLoginName() + "|" + receive.getMobile1();
        try {
            ShortMessageTask bo = new ShortMessageTask();
            bo.setReceiversMsg(receiver);
            bo.setContent(content);
            bo.setSendUserLoginname(sender.getLoginName());
            bo.setSendUserName(sender.getName());
            bo.setPlanSendTime(new Date());
            bo.setSendType("1");
            bo.setRemoved("0");
            bo.setOperateTime(new Date());
            bo.setTaskStatus("1");
            //bo.setPeriod((long)1);
            CommonDao.GetNewDatabaseDao().save(bo);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private CsUser getOldUser(String newLoginName) {
        if(newLoginName.startsWith("G") && newLoginName.length()==16){
            newLoginName = newLoginName.substring(0, newLoginName.length() - 4);
        }
        List objects = CommonDao.GetOldDatabaseDao().fetchAll("select id,mobile1,name,login_name from cs_user where login_name ='" +newLoginName + "'");
        Long oldUserId = 0l;
        CsUser user = new CsUser();
        if (objects != null && objects.size() > 0) {
            for (Object object : objects) {
                Object o[] = (Object[]) object;
                user.setId(((BigDecimal) o[0]).longValue());
                user.setMobile1((String) o[1]);
                user.setName((String) o[2]);
                user.setLoginName((String) o[3]);
                break;
            }
        }
        return user;
    }

    @Override
    public void sendApplyHolidayNoticeMsg(CsUser sender, List<CsUser> noticeList, String applyDate, String applyId, boolean isNeedSendShortMessage) {
        if (noticeList != null && noticeList.size() > 0) {
            CsUser user = getOldUser(sender.getLoginName());
            for (CsUser receiver : noticeList) {
                MsgUserMassage msg = new MsgUserMassage();
                CsUser oldReceiver = getOldUser(receiver.getLoginName());
                msg.setEmpidrec(getOldUser(receiver.getLoginName()).getId());
                msg.setEmpidsend(user.getId());
                msg.setTitle(sender.getName() + "的公休申请");
                String content = sender.getName() + "的公休申请,公休日期为(" + applyDate + ")!审批通过。";
                msg.setContent(content + "<a href=\"../holHolidaysApply/showApplyView.action?id=" + applyId + "\" target=\"_blank\">点击此处查看详细表单。</a>");
                msg.setSendmode(noticeList.size() > 1 ? 187 : 186);
                msg.setState(0);
                msg.setHasAtta(0);
                msg.setMsgcount(1);
                msgUsermessageDao.save(msg);

                if (isNeedSendShortMessage)
                    if (StringUtils.isBlank(receiver.getMobile1())) {
                        receiver.setMobile1(oldReceiver.getMobile1());
                    }
                sendShortMsgNotice(sender, receiver, content);
            }

        }
    }
}
	
