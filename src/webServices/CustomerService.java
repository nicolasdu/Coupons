package webServices;

import java.io.IOException;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import cop.Coupon;
import cop.couponType;
import couponClient.companyFacade;

@Path("/customerService")
public class CustomerService {

		 @GET
		 @Path("/getpurchasedcoupons")	
		 @Consumes(MediaType.TEXT_PLAIN)
		 public String getPurchasedCoup() throws JsonGenerationException, JsonMappingException, IOException
			{
				System.out.println("server side");
				companyFacade compfacade= new companyFacade();
				Collection<Coupon> couponList=new HashSet<Coupon>();
				couponList=compfacade.getAllCoupon();
				java.util.Iterator<Coupon> iterator =couponList.iterator();
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String json=null;
				while (iterator.hasNext()){
			        Object val = iterator.next();
			        json = json+ow.writeValueAsString(val);
				}
				return json;
			}
		 

}
