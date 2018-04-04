package gas;

public class Car {
	
	public enum FuelType{GAS, DIESEL, PETROL};
	
	private FuelType fuel;
	private int amount;
	private int kolonka;
	
	public int getKolonka() {
		return kolonka;
	}
	public void setKolonka(int kolonka) {
		this.kolonka = kolonka;
	}
	public FuelType getFuel() {
		return fuel;
	}
	public void setFuel(FuelType fuel) {
		this.fuel = fuel;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	

}
