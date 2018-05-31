package expiration;

import java.util.Collection;
import java.util.Date;

import cop.Coupon;
import dao.couponDBDAO;

public class dailyCouponExpirationTask implements Runnable {
	
	couponDBDAO coupon;
	boolean quit;
	
	
	
	public dailyCouponExpirationTask ()
	{
		this.quit=false;
		coupon = new couponDBDAO();
	}
	
	@Override
	public void run() {
		Collection<Coupon> copounList=null;
		while(!this.quit)
		{
			copounList=coupon.getAllCoupon();
			for(Coupon cou : copounList)
			{
				Date couponDate = cou.getEndDate();
				Date nowDate = new Date(System.currentTimeMillis());
				if(couponDate.getTime() - nowDate.getTime() <=0)
				{
					coupon.removeCoupon(cou);
				}
			}
			
		}
	}
	
	public synchronized void stopTask()
	{
		this.quit = true;
	}
	
	

}