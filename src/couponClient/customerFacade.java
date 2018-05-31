package couponClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import cop.Coupon;
import cop.Customer;
import cop.couponType;
import dao.couponDBDAO;
import dao.customerDBDAO;

public class customerFacade implements couponClientFacade {
	
	private customerDBDAO customerDBDAO;
	couponDBDAO coupon;
	

	public customerFacade() {
		
		coupon=new couponDBDAO();
		customerDBDAO=new customerDBDAO();
		
	}
	
	//purchase coupon
	public void purchaseCoupon(Coupon coup,Customer customerinput)
	{
		int tmpCoupon = coup.getId();
		Date date = new Date();
		boolean flag= false;
		Collection<Coupon> couponList = new HashSet<Coupon>();
		couponList = customerDBDAO.getCoupons();
		for(Coupon coupon : couponList) {
			if(tmpCoupon == coupon.getId() || coupon.getEndDate().compareTo(date) > 0)
			{
				flag=true;
			}
		}
		
		if(!flag)
		{
			couponList.add(coup);
			customerinput.setCoupons(couponList);
		}
		else
		{
			System.out.println("This Coupon Is Not Available");
		}
		
	}
	
	//get all purchased coupons
	public Collection<Coupon> getAllPurchasedCoupons()
	{
		return customerDBDAO.getCoupons();
	}
	
	//get ALL purchased coupons by type
	public Collection<Coupon> getAllPurchasedCouponsByType(couponType type)
	{
		Collection<Coupon> couponList = new HashSet<Coupon>();
		Collection<Coupon> couponListType = new ArrayList<Coupon>();
		couponList = customerDBDAO.getCoupons();
		for(Coupon coupon : couponList) {
			if(type.equals(coupon.getType()))
			{
				couponListType.add(coupon);
			}
		}

		return couponListType;
	}
	
	//get ALL purchased coupons by price
	public Collection<Coupon> getAllPurchasedCouponsByPrice(double price)
	{
		Collection<Coupon> couponList = new HashSet<Coupon>();
		Collection<Coupon> couponListType = new ArrayList<Coupon>();
		couponList = customerDBDAO.getCoupons();
		for(Coupon coupon : couponList) {
			if(price == coupon.getPrice())
			{
				couponListType.add(coupon);
			}
		}

		return couponListType;
	}
	
	
	public couponClientFacade login(String name, String password,clientType type) {
		
		if(customerDBDAO.login(name, password))
		{
				switch (type) {
			    case adminLogin:
			        return adminFacade.getInstance();
			    case companyLogin:
			    	return new companyFacade();
			    case customerLogin:
			    	return new customerFacade();			
			    }
		}
		return null;
	}
}
