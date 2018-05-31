package webServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.json.JSONObject;

import cop.Company;
import cop.Customer;
import couponClient.adminFacade;
import couponClient.clientType;
import couponClient.couponClientFacade;
import dao.companyDAO;
import dao.companyDBDAO;


@Path("/adminServices")
public class AdminService {
	

	@GET 
	@Path("/get")
	@Produces("application/json")
	public String getComp(@QueryParam("compId")int id)
	{
		Company comp = new Company();
		adminFacade admin= new adminFacade();
		comp=admin.getCompany(id);
		return ("{'name':'"+comp.getCompName()+"','compemail':'"+comp.getEmail()+"'}");
	}
	
	
	@DELETE
	@Path("/{compid}")
	public void deleteCompanyById(@PathParam("compid")int id){
		
		Company comp = new Company();
		adminFacade admin= new adminFacade();
		comp=admin.getCompany(id);
		admin.removeCompany(comp);
	}
	
	 @POST
	 @Path("/createcompany")	
	 @Consumes(MediaType.TEXT_PLAIN)
	 public void createCompany(@QueryParam("id")int id,@QueryParam("name")String name,@QueryParam("email")String email,@QueryParam("pass")String password) {
		 System.out.println(id);
		 System.out.println("server side");
		 Company comp = new Company();
		 adminFacade admin= new adminFacade();
		 comp.setCompName(name);
		 comp.setId(id);
		 comp.setEmail(email);
		 comp.setPassword(password);
		 admin.createCompany(comp);		 
	    }
	
	@PUT
	@Path("/updatecompany")
	@Consumes(MediaType.TEXT_PLAIN)
	public void updateCompanyById(@QueryParam("id")int id,@QueryParam("name")String name,@QueryParam("email")String email,@QueryParam("pass")String password){
		
		Company comp = new Company();
		adminFacade admin= new adminFacade();
		comp=admin.getCompany(id);
		comp.setCompName(name);
		comp.setEmail(email);
		comp.setPassword(password);
		admin.updateCompany(comp);
	}
	
	@GET 
	@Path("/getallcompanies")
	@Produces("application/text")
	public String getAllComp() throws JsonGenerationException, JsonMappingException, IOException
	{
		System.out.println("server side");
		adminFacade admin= new adminFacade();
		Collection<Company> companyList=new HashSet<Company>();
		companyList=admin.getAllCompanies();
		java.util.Iterator<Company> iterator =companyList.iterator();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json=null;
		while (iterator.hasNext()){
	        Object val = iterator.next();
	        json = json+ow.writeValueAsString(val);
		}
		return json;
	}

	 @POST
	 @Path("/createcustomer")	
	 @Consumes(MediaType.TEXT_PLAIN)
	 public void createCustomer(@QueryParam("id")int id,@QueryParam("name")String name,@QueryParam("pass")String password) {
		 Customer cust = new Customer();
		 adminFacade admin= new adminFacade();
		 cust.setId(id);
		 cust.setCustName(name);
		 cust.setPassword(password);
		 admin.createCustomer(cust);	 
	    }
	 
	@DELETE
	@Path("/deletecustomer")
	public void deleteCustomerById(@QueryParam("custid")int id){
			
		 Customer cust = new Customer();
		 adminFacade admin= new adminFacade();
		 cust=admin.getCustomer(id);
		 admin.removeCustomer(cust);
	}
	
 	@PUT
	@Path("/updatecustomer")
	@Consumes(MediaType.TEXT_PLAIN)
	public void updateCustomerById(@QueryParam("id")int id,@QueryParam("name")String name,@QueryParam("pass")String password){
		
	 	Customer cust = new Customer();
		adminFacade admin= new adminFacade();
		cust=admin.getCustomer(id);
		cust.setCustName(name);
		cust.setPassword(password);
		admin.updateCustomer(cust);
	}
	
 	@GET 
	@Path("/getcustomer")
	@Produces("application/json")
	public String getCust(@QueryParam("custId")int id)
	{
 		Customer cust = new Customer();
		adminFacade admin= new adminFacade();
		cust=admin.getCustomer(id);
		return ("{'name':'"+cust.getCustName()+"','custpass':'"+cust.getPassword()+"'}");
	}
 	
 	@GET 
	@Path("/getallcustomers")
	@Produces("application/json")
	public String getAllCust() throws JsonGenerationException, JsonMappingException, IOException
	{
		System.out.println("server side");
		adminFacade admin= new adminFacade();
		Collection<Customer> custList=new HashSet<Customer>();
		custList=admin.getAllCustomer();
		java.util.Iterator<Customer> iterator =custList.iterator();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json=null;
		while (iterator.hasNext()){
	        Object val = iterator.next();
	        json = json+ow.writeValueAsString(val);
		}
		return json;
	}
	

	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	public String login(@QueryParam("username") String username , @QueryParam("password") String password , @QueryParam("clientType") String clienttype) {
		
		System.out.println("server side");
		System.out.println("username is : "+username+"password is :"+password+"client type is : "+clienttype);
//		clientType type = clientType.valueOf(clienttype);
//		couponClientFacade couponClientFacade = new adminFacade().login(username, password, type);
//		System.out.println("username is : "+username+"password is :"+password+"client type is : "+clienttype);
		
		
		return null;
		
	}
	
}