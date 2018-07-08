package webServices;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import couponClient.adminFacade;
import couponClient.clientType;
import couponClient.couponClientFacade;

@Path("/loginservice")
public class loginService {
	
	@GET
	@Path("/login")
	@Produces("application/json")
	public String login(@QueryParam("username") String username , @QueryParam("password") String password , @QueryParam("clientType") clientType clienttype) {
		System.out.println("Password is"+password);
		adminFacade userLogin=new adminFacade();
		couponClientFacade user = userLogin.login(username, password, clienttype);
		System.out.println(password);
		if ((username==null)||(password==null))
			return ("{'username':'null','password':'null',clientType:'null'}");	
		else
			return ("{'clientType':'"+clienttype+"'username':'"+username+"'password':'"+password);
		
	}

}
