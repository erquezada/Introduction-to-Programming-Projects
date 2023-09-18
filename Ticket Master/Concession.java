public class Concession {
	private double popcornPrice = 6;
	private double sodaPrice = 1.50;
	private double hotDogPrice = 4.0;
	//default constructor
	public Concession() {}

	
	public Concession(double popcornPrice, double sodaPrice, double hotDogPrice) {
		this.popcornPrice = popcornPrice;
		this.sodaPrice = sodaPrice;
		this.hotDogPrice = hotDogPrice;
	}
	public void setPopcornPrice(double popcornPrice) {
		this.popcornPrice = popcornPrice;
	}
	public void setSodaPrice(double sodaPrice) {
		this.sodaPrice = sodaPrice;
	}
	public void setHotDogPrice(double hotDogPrice) {
		this.hotDogPrice = hotDogPrice;
	}
	public double getPopcornPrice() {
		return this.popcornPrice;
	}
	public double getSodaPrice() {
		return this.sodaPrice;
	}
	public double getHotDogPrice() {
		return this.hotDogPrice;
	}
	public void applyPriceFactor(double priceFactor) {
		this.popcornPrice *= priceFactor;
		this.sodaPrice *= priceFactor;
		this.hotDogPrice *= priceFactor;
	}
	public void printConcessionDetails() {
		System.out.println("Menu info:");
		System.out.println("\tPopcorn price: $" + this.popcornPrice);
		System.out.println("\tSoda price: $" + this.sodaPrice);
		System.out.println("\tHot Dog price: $" + this.hotDogPrice);
	}
}