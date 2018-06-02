package dao;

import java.util.Collection;

import cop.Coupon;
import cop.couponType;

public interface couponDAO {
	
	public void createCoupon (Coupon coup);
	public void removeCoupon(Coupon coup);
	public void updateCoupon(Coupon coup);
	public Coupon getCoupon(int id);
	public Collection<Coupon> getAllCoupon();
	public Collection<Coupon> getCouponbyType (couponType couponType); 

}
