package domain;

public class DonkeyM {

	private int price=0;
	private int time=2000;
	private String species="BUM";
	private int x=0;
	private int y=0;
	
	public DonkeyM(int price, int time, String species) {
		super();
		this.price = price;
		this.species = species;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
