package logic;

import java.util.Random;

public class Suppliers {

	public static int hay=1000;
	public static int food=500;
	public static int vitamins=200;
	public static int water=1000;
	
	public static int randomOptionSupply() {
		Random r = new Random();
		return r.nextInt(4);
	}
	
	public static void consumeHay() {
		hay--;
	}
	
	public static void consumeFood() {
		food--;
	}
	
	public static void consumeVitamins() {
		vitamins--;
	}
	
	public static void consumeWater() {
		water--;
	}
	
	
	public static int getHay() {
		return hay;
	}

	public static int getFood() {
		return food;
	}

	public static int getVitamins() {
		return vitamins;
	}

	public static int getWater() {
		return water;
	}
	
	
	
	
	
}
