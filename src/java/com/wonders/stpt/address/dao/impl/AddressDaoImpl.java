package com.wonders.stpt.address.dao.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wonders.stpt.address.dao.AddressDao;
import com.wonders.stpt.address.entity.*;
import com.wonders.stpt.util.AuthorUtil;
import com.wonders.stpt.util.ConvToCPData;
import com.wonders.stpt.util.PageUtils;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

public class AddressDaoImpl extends AbstractHibernateDaoImpl<Object> implements
		AddressDao {

	private String generateSql(Address bean) {
		String id = AuthorUtil.GetLoginUserID(PageUtils.GetRequest());
		String sql = "select * from t_user_register where 1=1";
		if (sql == null || sql.length() <= 0) {
			sql = "select * from t_user_register where 2=1";
		}
		/********* 组合查询条件 **********/
		String name = bean.getName();
		String email = bean.getEmail();
		String moblie = bean.getMobile();
		String officePhone = bean.getOffice_phone();
		String fax = bean.getFax();
		String deptName = bean.getDeptName();

		if (name != null && !"".equals(name)) {
			sql += " and NAME  like '%" + name + "%'";
		}
		if (email != null && !"".equals(email)) {
			sql += " and Email1 like '%" + email + "%'";
		}
		if (moblie != null && !"".equals(moblie)) {
			sql += " and Mobile1 like '%" + moblie + "%'";
		}
		if (officePhone != null && !"".equals(officePhone)) {
			sql += " and OFFICE_PHONE like '%" + officePhone + "%'";
		}
		if (fax != null && !"".equals(fax)) {
			sql += " and FAX like '%" + fax + "%'";
		}
		if (deptName != null && !"".equals(deptName)) {
			sql += " and DEPT_NAME like '%" + deptName + "%'";
		}
		/********* 组合查询条件结束 *********/
		return sql;
	}

	/***
	 * 列表视图的分页查询方法
	 */
	@Override
	public List GroupAddressByPage(Address bean, int startRow, int pageSize) {
		List<Object[]> list = new ArrayList<Object[]>();
		String sql = this.generateSql(bean);
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		return list;
	}

	/***
	 * 列表视图的记录条数
	 */
	@Override
	public int recordCount(Address bean) {
		String sql = this.generateSql(bean);
		sql = "select count(*) as count_num from ( " + sql + " ) a ";
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		query.addScalar("count_num", Hibernate.INTEGER);
		return (Integer) query.uniqueResult();

	}

	// 详细信息
	public List PersonAddress(String login) {
		List<Object[]> list = new ArrayList<Object[]>();

		String sql = "select * from t_user_register where LOGIN_NAME ='"
				+ login + "'";
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		list = query.list();

		return list;
	}

	// 分类视图查询方法
	public List SortViewByCode1() {
		List<Object[]> list1 = new ArrayList<Object[]>();
		String sql = "select name,email1,mobile1,login_name,office_phone from t_user_register where dept_code ='2120' and rownum<=5";
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		list1 = query.list();
		return list1;
	}

	public List SortViewByCode2() {
		List<Object[]> list2 = new ArrayList<Object[]>();
		String sql = "select name,email1,mobile1,login_name,office_phone from t_user_register where dept_code ='2134' and rownum<=5";
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		list2 = query.list();
		return list2;
	}

	public List SortViewByCode3() {
		List<Object[]> list3 = new ArrayList<Object[]>();
		String sql = "select name,email1,mobile1,login_name,office_phone from t_user_register where dept_code ='2132' and rownum<=5";
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		list3 = query.list();
		return list3;
	}
	
	//分类更多 
	@Override
	public List SortView(String sql,int startRow, int pageSize) {
		List<Object[]> list = new ArrayList<Object[]>();
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		return list;		
	}
	@Override
	public int recordCount(String sql) {
		sql = "select count(*) as count_num from ( " + sql + " ) a ";
		SQLQuery query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.addScalar("count_num", Hibernate.INTEGER);
		return (Integer) query.uniqueResult();
	}

	/**
	 * 个人通讯录
	 */
	public String findAllPersonAddress(PersonAddress bean) {
		String findAllSql = "select * from t_personal_address where 1=1";
		if (findAllSql == null || findAllSql.length() <= 0) {
			findAllSql = "select * from t_personal_address where 2=1";
		}

		// ********* 组合查询条件 **********//
		String name = bean.getName();
		String email = bean.getEmail();
		String mobil = bean.getMobil();
		String unitTel = bean.getUnitTel();
		String unitFax = bean.getUnitFax();
		String unitName = bean.getUnitName();

		if (name != null && !"".equals(name)) {
			findAllSql += " and NAME like '%" + name + "%'";
		}
		if (email != null && !"".equals(email)) {
			findAllSql += " and EMAIL ='" + email + "'";
		}
		if (mobil != null && !"".equals(mobil)) {
			findAllSql += " and MOBIL ='" + mobil + "'";
		}
		if (unitTel != null && !"".equals(unitTel)) {
			findAllSql += " and UNITTEL='" + unitTel + "'";
		}
		if (unitFax != null && !"".equals(unitFax)) {
			findAllSql += " and UNITFAX ='" + unitFax + "'";
		}
		if (unitName != null && !"".equals(unitName)) {
			findAllSql += " and UNITNAME ='" + unitName + "'";
		}

		return findAllSql;
	}

	/***
	 * 查询的分页查询方法
	 */

	@Override
	public List GroupAddressByPagePerson(PersonAddress bean, int startRow,
			int pageSize) {
		List<Object[]> list = new ArrayList<Object[]>();
		String sql = this.findAllPersonAddress(bean);
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		return list;
	}

	/***
	 * 查询的记录条数
	 */
	@Override
	public int recordCountPerson(PersonAddress bean) {
		String sql = this.findAllPersonAddress(bean);
		sql = "select count(*) as count_num from ( " + sql + " ) a ";
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		query.addScalar("count_num", Hibernate.INTEGER);
		return (Integer) query.uniqueResult();
	}

	// 添加
	public void addPersonAddress(PersonAddress bean) {
		String addSql = " insert into  t_personal_address (id,name,email,mobil,unittel,unitfax,unitname,lastdate) values('"
				+ UUID.randomUUID()
				+ "','"
				+ bean.getName()
				+ "','"
				+ bean.getEmail()
				+ "','"
				+ bean.getMobil()
				+ "','"
				+ bean.getUnitTel()
				+ "','"
				+ bean.getUnitFax()
				+ "','"
				+ bean.getUnitName() + "',sysdate)";
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(addSql);
		query.executeUpdate();

	}

	// 删除
	public void delPersonAddress(String  id) {
		String delSql = "delete from t_personal_address where ID = '"
				+ id + "'";
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(delSql);
		query.executeUpdate();

	}

	// 通过id 查询
	public List findPersonAddress(String id) {
		List<Object[]> list = new ArrayList<Object[]>();
		String sql = "select * from t_personal_address where ID ='" + id + "'";
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		list = query.list();
		return list;
	}

	// 修改
	public void updatePersonAddress(PersonAddress bean) {
		String updateSql = "update t_personal_address set NAME = '"
				+ bean.getName() + "',EMAIL = '" + bean.getEmail()
				+ "',MOBIL= '" + bean.getMobil() + "',UNITTEL = '"
				+ bean.getUnitTel() + "',UNITFAX = '" + bean.getUnitFax()
				+ "',UNITNAME = '" + bean.getUnitName() + "',LASTDATE=sysdate where Id='"
				+ bean.getId() + "'";
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(updateSql);
		query.executeUpdate();

	}

	
}