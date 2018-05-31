package cop;


import java.util.Date;

public class Coupon {
	
	private int id;
	private String title; 
	private Date startDate;
	private Date endDate;
	private int amount;
	private String message;
	private double price; 
	private String image;
	private couponType type;
	
	public Coupon(int id, String title, Date startDate, Date endDate, int amount, String message, double price,
			String image) {
		super();
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.message = message;
		this.price = price;
		this.image = image;
	}
	
	//Empty constructor
	public Coupon() {
	}
	
	public String toString() {
		return (this.id+"ID"+this.title+"Title"+this.startDate+"Start Date"+this.endDate+"End Date"+this.amount+"Amount"
				+this.message+"Message"+this.price+"Price"+this.image+"Image");
	}
	
	
	
	public couponType getType() {
		return type;
	}

	public void setType(couponType type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
