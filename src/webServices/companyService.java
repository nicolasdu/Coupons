package webServices;

import java.sql.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import cop.Coupon;
import cop.couponType;
import couponClient.companyFacade;

@Path("/companyService")
public class companyService {

	
	//need to fix
	 @POST
	 @Path("/createcoupon")	
	 @Consumes(MediaType.TEXT_PLAIN)
	 public void createCoupon(@QueryParam("id")int id,@QueryParam("title")String title,@QueryParam("start")Date start,@QueryParam("end")Date end,
			 @QueryParam("type")couponType type,@QueryParam("message")String message,@QueryParam("amount")int amount,@QueryParam("price")double price,@QueryParam("image")String image) {
		
		 System.out.println("server side");
		 Coupon coup = new Coupon();
		 companyFacade compfacade= new companyFacade();
		 coup.setId(id);
		 coup.setTitle(title);
		 coup.setStartDate(start);
		 coup.setEndDate(end);
		 coup.setType(type);
		 coup.setMessage(message);
		 coup.setAmount(amount);
		 coup.setPrice(price);
		 coup.setImage(image);
		 compfacade.CreateCoupon(coup);		 
	    }
	 
	 @DELETE
	 @Path("/deletecoupon")
		public void deleteCoupon(@QueryParam("couponid")int id){
				
		 	Coupon coup = new Coupon();
		 	companyFacade compfacade= new companyFacade();
		 	coup=compfacade.getCoupon(id);
		 	compfacade.removeCoupon(coup);
		}
	 
	 @GET 
	 @Path("/getcoupon")
	 @Produces("application/json")
		public String getCoupon(@QueryParam("couponId")int id)
		{
		 	Coupon coup = new Coupon();
		 	companyFacade compfacade= new companyFacade();
		 	coup=compfacade.getCoupon(id);
			return ("{'start':'"+coup.getStartDate()+"','end':'"+coup.getEndDate()+"','type':'"+coup.getType()+"','message':'"+coup.getMessage()+
					"','couptitle':'"+coup.getTitle()+"','amount':'"+coup.getAmount()+"','image':'"+coup.getImage()+
					"','price':"+coup.getPrice()+"}");
		}

}
