package com.wonders.stpt.hiddenDangersCorrect.entity.bo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2015/3/23
 * Time: 14:36
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "HDC_CHANGE_LOG")
public class HdcChangeLog implements Serializable{
    private String id;
    private String hdcId;
    private Date orgLastDate;
    private Date nowLastDate;
    private String operateTime;
    private String operatePerson;

    public HdcChangeLog(){

    }

    public HdcChangeLog(String hdcId,Date orgLastDate,Date nowLastDate,String operatePerson){
        this.hdcId = hdcId;
        this.orgLastDate = orgLastDate;
        this.nowLastDate = nowLastDate;
        this.operateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.operatePerson = operatePerson;
    }

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid.hex")
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "HDC_ID", nullable = true, length = 50)
    public String getHdcId() {
        return hdcId;
    }

    public void setHdcId(String hdcId) {
        this.hdcId = hdcId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ORG_LAST_DATE")
    public Date getOrgLastDate() {
        return orgLastDate;
    }

    public void setOrgLastDate(Date orgLastDate) {
        this.orgLastDate = orgLastDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "NOW_LAST_DATE")
    public Date getNowLastDate() {
        return nowLastDate;
    }

    public void setNowLastDate(Date nowLastDate) {
        this.nowLastDate = nowLastDate;
    }

    @Column(name = "OPERATE_TIME", nullable = true, length = 50)
    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    @Column(name = "OPERATE_PERSON", nullable = true, length = 50)
    public String getOperatePerson() {
        return operatePerson;
    }

    public void setOperatePerson(String operatePerson) {
        this.operatePerson = operatePerson;
    }
}
