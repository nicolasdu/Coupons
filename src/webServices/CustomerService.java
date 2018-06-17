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
import couponClient.customerFacade;

@Path("/customerService")
public class CustomerService {

		 @GET
		 @Path("/getpurchasedcoupons")	
		 @Consumes(MediaType.TEXT_PLAIN)
		 public String getPurchasedCoup() throws JsonGenerationException, JsonMappingException, IOException
			{
				System.out.println("server side");
				customerFacade custfacade= new customerFacade();
				Collection<Coupon> couponList=new HashSet<Coupon>();
				couponList=custfacade.getAllPurchasedCoupons();
				java.util.Iterator<Coupon> iterator =couponList.iterator();
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String json=null;
				while (iterator.hasNext()){
			        Object val = iterator.next();
			        json = json+ow.writeValueAsString(val);
				}
				System.out.println(json);
				return json;
			}
		 
		 
		 @GET
		 @Path("/getpurchasedcouponstype")	
		 @Consumes(MediaType.TEXT_PLAIN)
		 public String getPurchasedCoupType(@QueryParam("type")couponType type) throws JsonGenerationException, JsonMappingException, IOException
			{
				System.out.println("server side");
				customerFacade custfacade= new customerFacade();
				Collection<Coupon> couponList=new HashSet<Coupon>();
				couponList=custfacade.getAllPurchasedCouponsByType(type);
				java.util.Iterator<Coupon> iterator =couponList.iterator();
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String json=null;
				while (iterator.hasNext()){
			        Object val = iterator.next();
			        json = json+ow.writeValueAsString(val);
				}
				if (json==null)
					return null;
				else 
					return json;
			}
		 
		 @GET
		 @Path("/getpurchasedcouponsbyprice")	
		 @Consumes(MediaType.TEXT_PLAIN)
		 public String getPurchasedCoupByType(@QueryParam("price")double price) throws JsonGenerationException, JsonMappingException, IOException
			{
				System.out.println("server side");
				customerFacade custfacade= new customerFacade();
				Collection<Coupon> couponList=new HashSet<Coupon>();
				couponList=custfacade.getAllPurchasedCouponsByPrice(price);
				java.util.Iterator<Coupon> iterator =couponList.iterator();
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String json=null;
				while (iterator.hasNext()){
			        Object val = iterator.next();
			        json = json+ow.writeValueAsString(val);
				}
				
				System.out.println(json);
				if (json==null)
					return null;
				else 
					return json;
			}
		 
		 
}
