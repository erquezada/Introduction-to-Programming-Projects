public class Event {
	private String eventName;
	private String venueName;
	private String eventAddress;
	private double ticketPrice;
	private int numTicketsAvailable;
	Concession concessionStand;

	// Default constructor.
	public Event() {}

	public Event(String eventName, String venueName, String eventAddress, double ticketPrice, int numTicketsAvailable, Concession concessionStand) {
		this.eventName = eventName;
		this.venueName = venueName;
		this.eventAddress = eventAddress;
		this.ticketPrice = ticketPrice;
		this.numTicketsAvailable = numTicketsAvailable;
		this.concessionStand = concessionStand;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}	
	public void setAddress(String eventAddress) {
		this.eventAddress = eventAddress;
	}
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public void setNumTicketsAvailable(int numTicketsAvailable) {
		this.numTicketsAvailable = numTicketsAvailable;
	}
	public void setConcesionStand(Concession concessionStand) {
		this.concessionStand = concessionStand;
	} 
	public String getEventName() {
		return this.eventName;
	}
	public String getAddress() {
		return this.eventAddress;
	}
	public String getVenueName() {
		return this.venueName;
	}
	public double getTicketPrice () {
		return this.ticketPrice;
	}
	public int getNumTicketsAvailable() {
		return this.numTicketsAvailable;
	}
	public Concession getConcessionStand() {
		return this.concessionStand;
	}
	public boolean ticketsAreAvailable(double numTickets) {
		if (numTickets <= numTicketsAvailable) {
			return true;
		} else {
			return false;
			
		}
	}
	public double purchaseTicket(double numTickets) {
		
		if (!ticketsAreAvailable(numTickets)) {
			return 0;
		}
		numTicketsAvailable -= numTickets;
		return ticketPrice * numTickets;
	}
	public void printEventDetails() {
		System.out.println("Event info:");
		System.out.println("\tEvent name: " + this.eventName);
		System.out.println("\tVenue name: " + this.venueName);
		System.out.println("\tAddress: " + this.eventAddress);
		System.out.println("\t Ticket price: " + this.ticketPrice);
		System.out.println("\tNumber of tickets available: " + this.numTicketsAvailable);
	}
}