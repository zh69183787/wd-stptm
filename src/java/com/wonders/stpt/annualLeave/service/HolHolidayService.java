package com.wonders.stpt.annualLeave.service;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.annualLeave.model.HolHoliday;
import com.wonders.stpt.annualLeave.model.VUserdep;
import com.wonders.stpt.csUser.entity.bo.CsUser;
import com.wondersgroup.framework.core.bo.Page;


public interface HolHolidayService {
	/**
	 * ɾ��ʵ�����
	 * 
	 * @param holHoliday
	 */
	public void deleteHolHoliday(HolHoliday holHoliday);

	/**
	 * 
	 * ͨ��IDװ����Ӧ�Ķ���ʵ������Ӧ��ʵ�岻���ڣ�����null
	 * 
	 * @param id
	 *            ����
	 * @return
	 */
	public HolHoliday findHolHolidayById(String id);

	/**
	 * �־û�һ��ʵ�����
	 * 
	 * @param holHoliday
	 */
	public void addHolHoliday(HolHoliday holHoliday);

	/**
	 * ������ݵ���ݿ�
	 * 
	 * @param holHoliday
	 *            ʵ��
	 */
	public void updateHolHoliday(HolHoliday holHoliday);

	/**
	 * ��ݷ�ҳ������з�ҳ��ѯ.
	 * 
	 * @param pageNo
	 *            ��ǰҳ��
	 * @param pageSize
	 *            ÿҳ��ʾ��¼��.
	 * @return
	 */
	public Page findHolHolidayByPage(int pageNo, int pageSize);

	/**
	 * ���Map�й��������ͷ�ҳ������з�ҳ��ѯ.
	 * 
	 * @param filter
	 *            ��������<propertyName,properyValue>
	 * @param pageNo
	 *            ��ǰҳ��
	 * @param pageSize
	 *            ÿҳ��ʾ��¼��.
	 * @return
	 */
	public Page findHolHolidayByPage(Map<String, Object> filter, int pageNo,
			int pageSize);
	
	
	public List<HolHoliday> findByYearAndHolPersonId(String year,String loginName);
	
	public List<HolHoliday> findByStartYearAndHolPersonId(String year,String loginName);
	
	public boolean isUserExist(String loginName);
	
	public List<CsUser> findUsersByLoginName(String loginName);
	
	public HolHoliday findLastHolidaysSetByholPersonId(String loginName);
	
	public void saveAll(List<HolHoliday> list);
	
	public com.wonders.stpt.csUser.entity.bo.CsUser findUserById(long id);
	
	public Object[] findHolHolidaySettime();
	
	public void updateHolHolidaySettime(Long overyear,Long month);
	
	public VUserdep findVUserdepById(Long id);
	
	public List<HolHoliday> findOtherAccountByHolId(String holId,String holYear);

    public Page getHoliday(Map filter, int pageNo, int pageSize);
}
