package couponClient;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import cop.Company;
import cop.Coupon;
import cop.couponType;
import dao.companyDAO;
import dao.companyDBDAO;


public class Tester {
	
	public static void main(String[] args) {
		
		adminFacade admin = adminFacade.getInstance();

		// create company
		//admin.createCompany(new Company(11,"Test2","sadasd",null,"nicola23@gmail.com"));
//		Company tmp = admin.getCompany(123);
//		if(tmp != null) {
//			System.out.println("Company Name : "+tmp.getCompName());
//			System.out.println("Company Password : "+tmp.getPassword());
//			System.out.println("Company Email : "+tmp.getEmail());
//		}
//		
//		companyDBDAO comp = new companyDBDAO();
//		
//		Company tmp = comp.getCompany(123);
//		System.out.println("Company Name: " + tmp.getCompName());
//		
//		 Date date= new Date ("12/12/12");
//		 couponType type = null ;
//		 Coupon coup = new Coupon();
//		 companyFacade compfacade= new companyFacade();
//		 coup.setId(123);
//		 coup.setTitle("asdasd");
//		 coup.setStartDate(date);
//		 coup.setEndDate(date);
//		 coup.setType(type.CAMPING);
//		 coup.setMessage("qwqw");
//		 coup.setAmount(2);
//		 coup.setPrice(2);
//		 coup.setImage("qwqw");
//		 
//		 compfacade.CreateCoupon(coup);	
		
		companyDAO comp=new companyDBDAO();
		Collection<Company> companyList=new HashSet<Company>();
		companyList=comp.getAllCompanies();
		java.util.Iterator<Company> iterator =companyList.iterator();
		while (iterator.hasNext()){
	        Object val = iterator.next();
	        System.out.println(val);
	}
		
		
	}

}