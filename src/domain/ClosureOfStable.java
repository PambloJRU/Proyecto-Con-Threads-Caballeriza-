package domain;

public class ClosureOfStable {

	private String SpecieName;
	private int quantity;
	private int individualPrice;
	private int total;
	
	public ClosureOfStable(String specieName, int quantity, int individualPrice, int total) {
		super();
		SpecieName = specieName;
		this.quantity = quantity;
		this.individualPrice = individualPrice;
		this.total = total;
	}

	public String getSpecieName() {
		return SpecieName;
	}

	public void setSpecieName(String specieName) {
		SpecieName = specieName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getIndidivualPrice() {
		return individualPrice;
	}

	public void setIndidivualPrice(int indidivualPrice) {
		this.individualPrice = indidivualPrice;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public String[] getDataName() { //Nombre de los datos
		String[] dataName = {"SpecieName","quantity","individualPrice","total"};
		return dataName;
	}
	
	public String[] getData() { //valores como tal
		String[] data = {SpecieName,String.valueOf(quantity),String.valueOf(individualPrice),String.valueOf(total)};
		return data;
	}
}
