package couponClient;

import cop.ConnectionPool;
import dao.genericExceptions;
import expiration.dailyCouponExpirationTask;

public class couponSystem {
	
	private static couponSystem _instance = null ;
	private dailyCouponExpirationTask dailycouponExpirationTask;

	private couponSystem() {
		dailycouponExpirationTask = new dailyCouponExpirationTask();
		Thread t = new Thread(dailycouponExpirationTask);
		t.start();
	}


	public synchronized static couponSystem getInstance() {
		if(_instance == null ) {
			_instance = new couponSystem() ; 
		}
		return _instance ; 
	}


	public couponClientFacade login(String name , String password , clientType clientType) throws genericExceptions{
		switch (clientType) {
		case adminLogin:
			return new  adminFacade().login(name, password, couponClient.clientType.adminLogin);
			
		case companyLogin:
			return new companyFacade().login(name, password, couponClient.clientType.companyLogin) ;
			
		case customerLogin:
			return new customerFacade().login(name, password, couponClient.clientType.customerLogin);
		}
		return null ;
	}

	@SuppressWarnings("deprecation")
	public synchronized void shutdown() {
		System.out.println("CouponSystem->shutdown | closing all threads ");
		dailycouponExpirationTask.stopTask();
		ConnectionPool.getInstance().closeAllConnections();
		Thread.currentThread().stop();

	}
}
