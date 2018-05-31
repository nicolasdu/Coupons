package couponClient;

public interface couponClientFacade {
	
	static final String USER_NAME = "admin";
	static final String PASSWORD = "1234";	
	public couponClientFacade login(String name, String password, clientType type);
}
