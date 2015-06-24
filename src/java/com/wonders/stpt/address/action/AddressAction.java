package com.wonders.stpt.address.action;

import java.util.List;

import com.wonders.stpt.address.entity.Address;
import com.wonders.stpt.address.entity.PersonAddress;
import com.wonders.stpt.address.service.AddressService;
import com.wonders.stpt.dbBusiness.util.PageInfo;
import com.wonders.stpt.dbBusiness.util.PageResultSet;
import com.wonders.stpt.util.AuthorUtil;
import com.wonders.stpt.util.PageUtils;
import com.wonders.stpt.util.PropertiesReader;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

public class AddressAction extends BaseAjaxAction {

	private static final long serialVersionUID = 1L;

private Address address = new Address();;
private PersonAddress person = new PersonAddress();
	private AddressService addressService;
	private String login_name;
	private String id;
private String dept_code;
public String getDept_code() {
	return dept_code;
}
public void setDept_code(String deptCode) {
	dept_code = deptCode;
}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PersonAddress getPerson() {
		return person;
	}

	public void setPerson(PersonAddress person) {
		this.person = person;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String loginName) {
		login_name = loginName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public AddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	/**
	 * 列表视图查询 
	 * 
	 * @return
	 */
	public String showGroupList() {
		int pageNo = 0;
		PageResultSet<Object> result = new PageResultSet<Object>();
		String strPageNo = PageUtils.GetParameter("number");
		if (strPageNo != null && !"".equals(strPageNo)) {
			pageNo = Integer.parseInt(strPageNo);
		}
		int pageSize = PageUtils.GetDefaultPageSize();
		int recordCount = addressService.recordCount(this.address);

		PageInfo pageinfo = new PageInfo(recordCount, pageSize, pageNo);
		List list = addressService.GroupAddressByPage(this.address,
				pageinfo.getBeginIndex(), pageSize);
		result.setPageInfo(pageinfo);
		result.setList(list);
		// servletRequest.setAttribute("list", list);
		this.servletRequest.setAttribute("result", result);
		this.servletRequest.setAttribute("loginName",
				AuthorUtil.GetLoginUserName(PageUtils.GetRequest()));
		this.servletRequest.setAttribute("ultimusip",
				PropertiesReader.GetProptery("ultimusip"));
		return "showGroupList";
	}

	public String personAddressList() {
		List list = addressService.PersonAddress(login_name);
		Object[] obj = (Object[]) list.get(0);
		String name = (String) obj[1];
		String dept_name = (String) obj[20];
		String email = (String) obj[9];
		String moblie = (String) obj[6];
		String home_phone = (String) obj[5];
		String address = (String) obj[11];
		if (address == null) {
			address = "";
		}
		String office_phone = (String) obj[4];
		String fax = (String) obj[8];
		this.servletRequest.setAttribute("name", name);
		this.servletRequest.setAttribute("dept_name", dept_name);
		this.servletRequest.setAttribute("email", email);
		this.servletRequest.setAttribute("moblie", moblie);
		this.servletRequest.setAttribute("home_phone", home_phone);
		this.servletRequest.setAttribute("address", address);
		this.servletRequest.setAttribute("office_phone", office_phone);
		this.servletRequest.setAttribute("fax", fax);
		return "personAddressList";
	}

	// 分类视图
	public String sortViewByCode() {
		List list1 = addressService.SortViewByCode1();
		List list2 = addressService.SortViewByCode2();
		List list3 = addressService.SortViewByCode3();
		this.servletRequest.setAttribute("list1", list1);
		this.servletRequest.setAttribute("list2", list2);
		this.servletRequest.setAttribute("list3", list3);
		return "sortViewByCode";
	}

	/**
	 * 个人通讯录
	 */

	public String showPersonAddressList() {
		int pageNo = 0;
		PageResultSet<Object> result = new PageResultSet<Object>();
		String strPageNo = PageUtils.GetParameter("number");
		if (strPageNo != null && !"".equals(strPageNo)) {
			pageNo = Integer.parseInt(strPageNo);
		}
		int pageSize = PageUtils.GetDefaultPageSize();
		int recordCount = addressService.recordCountPerson(this.person);

		PageInfo pageinfo = new PageInfo(recordCount, pageSize, pageNo);
		List list = addressService.GroupAddressByPagePerson(this.person,
				pageNo, pageSize);
		result.setPageInfo(pageinfo);
		result.setList(list);
		// servletRequest.setAttribute("list", list);
		this.servletRequest.setAttribute("result", result);
		this.servletRequest.setAttribute("loginName",
				AuthorUtil.GetLoginUserName(PageUtils.GetRequest()));
		this.servletRequest.setAttribute("ultimusip",
				PropertiesReader.GetProptery("ultimusip"));
		return "showPersonAddressList";
	}
	//跳到添加页面
	public String showToAddPersonAddressList(){
		return "showToAddPersonAddressList";
	}
	// 添加
	public String showAddPersonAddressList() {
		addressService.addPersonAddress(person);
		return "showAddPersonAddressList";
	}

	// 删除
	public String delPersonAddress() {
		addressService.delPersonAddress(id);
		return "delPersonAddress";
	}

	// 修改
	public String updatePersonAddress() {
		addressService.updatePersonAddress(person);
		return "updatePersonAddress";
	}

	// 通过id查询
	public String findByIdPersonAddress() {
		List list = addressService.findPersonAddress(id);
		Object[] obj = (Object[]) list.get(0);
		String name = (String) obj[2];
		String email = (String) obj[3];
		String mobil = (String) obj[4];
		String unitTel = (String) obj[10];
		String unitFax = (String) obj[11];
		String unitName = (String) obj[8];
		this.servletRequest.setAttribute("name", name);
		this.servletRequest.setAttribute("email", email);
		this.servletRequest.setAttribute("mobil", mobil);
		this.servletRequest.setAttribute("unitTel", unitTel);
		this.servletRequest.setAttribute("unitFax", unitFax);
		this.servletRequest.setAttribute("unitName", unitName);
		return "findByIdPersonAddress";
	}

//分类更多 
	String deptcode1="2120";
	String deptcode2="2134";
	String deptcode3="2132";
	private String generateSql(Address bean,String deptcode) {
		String id = AuthorUtil.GetLoginUserID(PageUtils.GetRequest());
		String sql="select * from t_user_register where dept_code='"+deptcode+"'";
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
	
public String sortView(){
	String sql=generateSql(address,deptcode1);
	int pageNo = 0;
	PageResultSet<Object> result = new PageResultSet<Object>();
	String strPageNo = PageUtils.GetParameter("number");
	if (strPageNo != null && !"".equals(strPageNo)) {
		pageNo = Integer.parseInt(strPageNo);
	}
	int pageSize = PageUtils.GetDefaultPageSize();
	int recordCount = addressService.recordCount(sql);
	PageInfo pageinfo = new PageInfo(recordCount, pageSize, pageNo);
	List list = addressService.SortView(sql, pageinfo.getBeginIndex(), pageSize);
	result.setPageInfo(pageinfo);
	result.setList(list);
	this.servletRequest.setAttribute("result", result);
	this.servletRequest.setAttribute("loginName",
			AuthorUtil.GetLoginUserName(PageUtils.GetRequest()));
	return "sortView";	
}

public String sortView1(){
	String sql=generateSql(address,deptcode2);
	int pageNo = 0;
	PageResultSet<Object> result = new PageResultSet<Object>();
	String strPageNo = PageUtils.GetParameter("number");
	if (strPageNo != null && !"".equals(strPageNo)) {
		pageNo = Integer.parseInt(strPageNo);
	}
	int pageSize = PageUtils.GetDefaultPageSize();
	int recordCount = addressService.recordCount(sql);
	PageInfo pageinfo = new PageInfo(recordCount, pageSize, pageNo);
	List list = addressService.SortView(sql, pageinfo.getBeginIndex(), pageSize);
	result.setPageInfo(pageinfo);
	result.setList(list);
	this.servletRequest.setAttribute("result", result);
	this.servletRequest.setAttribute("loginName",
			AuthorUtil.GetLoginUserName(PageUtils.GetRequest()));
	return "sortView1";	
}

public String sortView2(){
	String sql=generateSql(address,deptcode3);
	int pageNo = 0;
	PageResultSet<Object> result = new PageResultSet<Object>();
	String strPageNo = PageUtils.GetParameter("number");
	if (strPageNo != null && !"".equals(strPageNo)) {
		pageNo = Integer.parseInt(strPageNo);
	}
	int pageSize = PageUtils.GetDefaultPageSize();
	int recordCount = addressService.recordCount(sql);
	PageInfo pageinfo = new PageInfo(recordCount, pageSize, pageNo);
	List list = addressService.SortView(sql, pageinfo.getBeginIndex(), pageSize);
	result.setPageInfo(pageinfo);
	result.setList(list);
	this.servletRequest.setAttribute("result", result);
	this.servletRequest.setAttribute("loginName",
			AuthorUtil.GetLoginUserName(PageUtils.GetRequest()));
	return "sortView2";	
}
} 
