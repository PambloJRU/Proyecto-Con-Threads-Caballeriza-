package logic;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

import controllerThreads.BurgundyThread;
import controllerThreads.DonkeyFThread;
import controllerThreads.DonkeyMThread;
import controllerThreads.HorseThread;
import controllerThreads.MareThread;
import controllerThreads.MuleThread;
import controllerThreads.PonyFThread;
import controllerThreads.PonyMThread;
import data.FileXML;
import domain.Burgundy;
import domain.ClosureOfStable;
import domain.DonkeyF;
import domain.DonkeyM;
import domain.Horse;
import domain.Mare;
import domain.Mule;
import domain.PonyF;
import domain.PonyM;
import view.StableGUI;

public class ListLogicThreads {

	ArrayList<Horse> arrayHorses;
	ArrayList<HorseThread> horseThArray;
	ArrayList<Mare> arrayMares;
	ArrayList<MareThread> mareThArray;
	ArrayList<DonkeyM> arrayDkm;
	ArrayList<DonkeyMThread> dkMThArray;
	ArrayList<DonkeyF> arrayDkf;
	ArrayList<DonkeyFThread> dkFThArray;
	ArrayList<PonyM> arrayPm;
	ArrayList<PonyMThread> PmThArray;
	ArrayList<PonyF> arrayPf;
	ArrayList<PonyFThread> PfThArray;
	ArrayList<Burgundy> arrayBur;
	ArrayList<BurgundyThread> burThArray;
	ArrayList<Mule> arrayMules;
	ArrayList<MuleThread> muleThArray;
	
	StableGUI gui;
	private String address;
	private FileXML xml;
	
	private int quantity=0;
	private int total;
	
	//Inicializador de los arrayList equinos(objeto e hilo) por default al inicio de la ejecución
	
	public ListLogicThreads(StableGUI gui) {
		arrayHorses= new ArrayList<>(10);
		horseThArray= new ArrayList<>(10);
		
		arrayMares = new ArrayList<>(7);
		mareThArray = new ArrayList<>(7);
		
		arrayDkm = new ArrayList<>(8);
		dkMThArray = new ArrayList<>(8);
		
		arrayDkf = new ArrayList<>(5);
		dkFThArray = new ArrayList<>(5);
		
		arrayPm = new ArrayList<>(6);
		PmThArray = new ArrayList<>(6);
		
		arrayPf = new ArrayList<>(3);
		PfThArray = new ArrayList<>(3);
		
		arrayBur = new ArrayList<>();
		burThArray = new ArrayList<>();
		
		arrayMules = new ArrayList<>();
		muleThArray = new ArrayList<>();
		
		this.gui=gui;//Comparticion de guis
		address = "C:\\Users\\Usuario\\eclipse-workspace\\ProyectoCaballeriza\\";
		//EL ADDRESS TIENE QUE TERMINAR CON DOS BARRAS INCLINADAS A LA IZQUIERDA
		xml = new FileXML();
	}
	
	//Metodo que llena los arrayList de objetos con los primeros equinos por default
	public void initializersArray() {
		for (int i=0;i<10;i++) {
			arrayHorses.add(new Horse(1000000,2000,"CA"));
		}
		for(int i=0;i<7;i++) {
			arrayMares.add(new Mare(750000,3000,"YE"));
		}
		for(int i=0;i<8;i++) {
			arrayDkm.add(new DonkeyM(500000,2000,"BUM"));
		}
		for(int i=0;i<5;i++) {
			arrayDkf.add(new DonkeyF(375000,3000,"BUH"));
		}
		for(int i=0;i<6;i++) {
			arrayPm.add(new PonyM(300000,2000,"PM"));
		}
		for(int i=0;i<3;i++) {
			arrayPf.add(new PonyF(225000,3000,"PF"));
		}
	}
	//Metodo que crea Hilos en los ArrayList de Hilos
	public void threadsObjectsInitializer() {
			for (int i=0;i<10;i++) {
				horseThArray.add(new HorseThread(arrayHorses.get(i),gui,this));
			}
			for(int i=0;i<7;i++) {
				mareThArray.add(new MareThread(arrayMares.get(i),gui,this));
			}
			for(int i=0;i<8;i++) {
				dkMThArray.add(new DonkeyMThread(arrayDkm.get(i),gui,this));
			}
			for(int i=0;i<5;i++) {
				dkFThArray.add(new DonkeyFThread(arrayDkf.get(i),gui,this));
			}
			for(int i=0;i<6;i++) {
				PmThArray.add(new PonyMThread(arrayPm.get(i),gui,this));
			}
			for(int i=0;i<3;i++) {
				PfThArray.add(new PonyFThread(arrayPf.get(i),gui,this));
			}
	}

	//Getters y setters de los arrayList para eliminar y añadir elementos
	public ArrayList<Horse> getArrayHorses() {
		return arrayHorses;
	}
	public void addInArrayHorses(Horse horse) {
		this.arrayHorses.add(horse);
	}
	public ArrayList<HorseThread> getHorseThArray() {
		return horseThArray;
	}
	public void setHorseThArray(HorseThread horseThread) {
		this.horseThArray.add(horseThread);
	}
	
	public ArrayList<Mare> getArrayMares() {
		return arrayMares;
	}
	public void addInArrayMare(Mare mare) {
		this.arrayMares.add(mare);
	}
	public ArrayList<MareThread> getMareThArray() {
		return mareThArray;
	}
	public void setMareThArray(MareThread mareThread) {
		this.mareThArray.add(mareThread);
	}
	

	public ArrayList<DonkeyM> getDkMArray() {
		return arrayDkm;
	}
	public void addInArrayDonkeyM(DonkeyM dkM) {
		this.arrayDkm.add(dkM);
	}
	public ArrayList<DonkeyMThread> getDkMThArray(){
		return dkMThArray;
	}
	public void setDonkeyMThArray(DonkeyMThread dkMThread) {
		this.dkMThArray.add(dkMThread);
	}
	
	public ArrayList<DonkeyF> getDkFArray() {
		return arrayDkf;
	}
	public void addInArrayDonkeyF(DonkeyF dkf) {
		this.arrayDkf.add(dkf);
	}
	public ArrayList<DonkeyFThread> getDkFThArray(){
		return dkFThArray;
	}
	public void setDonkeyFThArray(DonkeyFThread dkFThread) {
		this.dkFThArray.add(dkFThread);
	}
	
	
	public ArrayList<PonyM> getPmArray() {
		return arrayPm;
	}
	public void addInArrayPonyM(PonyM pm) {
		this.arrayPm.add(pm);
	}
	public ArrayList<PonyMThread> getPonyMThArray(){
		return PmThArray;
	}
	public void setPonyMThArray(PonyMThread pmTh) {
		this.PmThArray.add(pmTh);
	}
	
	public ArrayList<PonyF> getPfArray() {
		return arrayPf;
	}
	public void addInArrayPonyF(PonyF pf) {
		this.arrayPf.add(pf);
	}
	public ArrayList<PonyFThread> getPonyFThArray(){
		return PfThArray;
	}
	public void setPonyFThArray(PonyFThread pfTh) {
		this.PfThArray.add(pfTh);
	}
	
	public ArrayList<Burgundy> getBurArray() {
		return arrayBur;
	}
	public void addInArrayBur(Burgundy bur) {
		this.arrayBur.add(bur);
	}
	public ArrayList<BurgundyThread> getBurgundyThArray(){
		return burThArray;
	}
	public void setBurThArray(BurgundyThread bur) {
		this.burThArray.add(bur);
	}
	
	public ArrayList<Mule> getMuleArray() {
		return arrayMules;
	}
	public void addInArrayMule(Mule mule) {
		this.arrayMules.add(mule);
	}
	public ArrayList<MuleThread> getMuleThArray(){
		return muleThArray;
	}
	public void setMuleThArray(MuleThread mule) {
		this.muleThArray.add(mule);
	}
	
	//Metodo que pausa todos los hilos al mismo tiempo con el boton pausa
	public synchronized void pauseThreads() {
		
		for (int i = 0; i < getHorseThArray().size(); i++) {
			getHorseThArray().get(i).pauseThread();
		}
		for (int i = 0; i < getMareThArray().size(); i++) {
			getMareThArray().get(i).pauseThread();
		}
		for (int i = 0; i < getDkMThArray().size(); i++) {
			getDkMThArray().get(i).pauseThread();
		}
		for (int i = 0; i < getDkFThArray().size(); i++) {
			getDkFThArray().get(i).pauseThread();
		}
		for (int i = 0; i < getPonyMThArray().size(); i++) {
			getPonyMThArray().get(i).pauseThread();
		}
		for (int i = 0; i < getPonyFThArray().size(); i++) {
			getPonyFThArray().get(i).pauseThread();
		}
		for (int i = 0; i < getBurgundyThArray().size(); i++) {
			getBurgundyThArray().get(i).pauseThread();
		}
		for (int i = 0; i < getMuleThArray().size(); i++) {
			getMuleThArray().get(i).pauseThread();
		}
		
		
	}
	//Metodo que reanuda todos los hilos al mismo tiempo luego de pausarlos
	public synchronized void resumeThreads() {
		for (int i = 0; i < getHorseThArray().size(); i++) {
			getHorseThArray().get(i).resumeThread();
		}
		for (int i = 0; i < getMareThArray().size(); i++) {
			getMareThArray().get(i).resumeThread();
		}
		for (int i = 0; i < getDkMThArray().size(); i++) {
			getDkMThArray().get(i).resumeThread();
		}
		for (int i = 0; i < getDkFThArray().size(); i++) {
			getDkFThArray().get(i).resumeThread();
		}
		for (int i = 0; i < getPonyMThArray().size(); i++) {
			getPonyMThArray().get(i).resumeThread();
		}
		for (int i = 0; i < getPonyFThArray().size(); i++) {
			getPonyFThArray().get(i).resumeThread();
		}
		for (int i = 0; i < this.getBurgundyThArray().size(); i++) {
			getBurgundyThArray().get(i).resumeThread();
		}
		for (int i = 0; i < this.getMuleThArray().size(); i++) {
			getMuleThArray().get(i).resumeThread();
		}

	}
	//Metodo que arranca todos los hilos
	public void executeThreads() {
		
	    for (int i = 0; i < getHorseThArray().size(); i++) {
	    	getHorseThArray().get(i).start();
	    }
	    for (int i = 0; i < getMareThArray().size(); i++) {
	    	getMareThArray().get(i).start();
	    }
	    for (int i = 0; i < getDkMThArray().size(); i++) {
	    	getDkMThArray().get(i).start();
	    }
	    for (int i = 0; i < getDkFThArray().size(); i++) {
	    	getDkFThArray().get(i).start();
	    }
	    for (int i = 0; i < getPonyMThArray().size(); i++) {
	    	getPonyMThArray().get(i).start();
	    }
	    for (int i = 0; i < getPonyFThArray().size(); i++) {
	    	getPonyFThArray().get(i).start();
	    }
	    
	}
	
	//Metodo para detectar actividad en los hilos cuando ya no se cumple se genera el cierre de caballeriza
	public boolean activitychecker() {
		boolean check=false;
		Thread th;
		for (int i = 0; i < getMareThArray().size(); i++) {
			th = getMareThArray().get(0);
			if(th.isAlive()) {
				check = true;
				return check;
			}
		}
		
		xml.createXML("Specie", address, "Close Of Stable");
		CloseStableXML();
		return check;
		
	}
	
	//Metodo que mata todos los hilos
	@SuppressWarnings("deprecation")
	public void finalizeAll() {
		for (int i = 0; i < getHorseThArray().size(); i++) {
			getHorseThArray().get(i).stop();
		}
		for (int i = 0; i < getMareThArray().size(); i++) {
			getMareThArray().get(i).stop();
		}
		for (int i = 0; i < this.getDkFThArray().size(); i++) {
			getDkFThArray().get(i).stop();
		}
		for (int i = 0; i < this.getDkMThArray().size(); i++) {
			getDkMThArray().get(i).stop();
		}
		for (int i = 0; i < this.getPonyMThArray().size(); i++) {
			getPonyMThArray().get(i).stop();
		}
		for (int i = 0; i < this.getPonyFThArray().size(); i++) {
			getPonyFThArray().get(i).stop();
		}
		for (int i = 0; i < this.getBurgundyThArray().size(); i++) {
			getBurgundyThArray().get(i).stop();
		}
		for (int i = 0; i < this.getMuleThArray().size(); i++) {
			getMuleThArray().get(i).stop();
		}
		
	}
	//Da un valor entre ese rango para el huracan aleatorio
	public int randomHurricaneNumber() {
		Random random = new Random();
		int low = 60000, hig = 500000;
		return random.nextInt(hig-low + 1) + low;
	}
	//Metodo mounstroso :( para escribir en el XML
	public void CloseStableXML() {

		
		for(int i = 0; i < arrayHorses.size(); i++) {
    		quantity++;
    		total+=arrayHorses.get(i).getPrice();
    	}
		ClosureOfStable close = new ClosureOfStable(getArrayHorses().get(0).getSpecies(),
				quantity,arrayHorses.get(0).getPrice(),total);
		xml.writeXML(address+"Close Of Stable.xml", "Specie", close.getDataName(), close.getData());
		quantity=0;
		total=0;
		
		for(int i = 0; i < arrayMares.size(); i++) {
    		quantity++;
    		total+=arrayMares.get(i).getPrice();
    	}
		close = new ClosureOfStable(getArrayMares().get(0).getSpecies(),
				quantity,arrayMares.get(0).getPrice(),total);
		xml.writeXML(address+"Close Of Stable.xml", "Specie", close.getDataName(), close.getData());
		quantity=0;
		total=0;
		
		for(int i = 0; i < arrayDkm.size(); i++) {
    		quantity++;
    		total+=arrayDkm.get(i).getPrice();
    	}
		close = new ClosureOfStable(arrayDkm.get(0).getSpecies(),
				quantity,arrayDkm.get(0).getPrice(),total);
		xml.writeXML(address+"Close Of Stable.xml", "Specie", close.getDataName(), close.getData());
		quantity=0;
		total=0;
		
		for(int i = 0; i < arrayDkf.size(); i++) {
    		quantity++;
    		total+=arrayDkf.get(i).getPrice();
    	}
		close = new ClosureOfStable(arrayDkf.get(0).getSpecies(),
				quantity,arrayDkf.get(0).getPrice(),total);
		xml.writeXML(address+"Close Of Stable.xml", "Specie", close.getDataName(), close.getData());
		quantity=0;
		total=0;
		
		if(!arrayPm.isEmpty()){
			for(int i = 0; i < arrayPm.size(); i++) {
	    		quantity++;
	    		total+=arrayPm.get(i).getPrice();
	    	}
			close = new ClosureOfStable(arrayPm.get(0).getSpecies(),
					quantity,arrayPm.get(0).getPrice(),total);
			xml.writeXML(address+"Close Of Stable.xml", "Specie", close.getDataName(), close.getData());
		}
		quantity=0;
		total=0;
		
		if(!arrayPf.isEmpty()) {
			for(int i = 0; i < arrayPf.size(); i++) {
	    		quantity++;
	    		total+=arrayPf.get(i).getPrice();
	    	}
			close = new ClosureOfStable(arrayPf.get(0).getSpecies(),
					quantity,arrayPf.get(0).getPrice(),total);
			xml.writeXML(address+"Close Of Stable.xml", "Specie", close.getDataName(), close.getData());
		}
		
	}
	//Metodo que define un numero aleatorio para que el hilo use los suministros
	public int randomUseSuppliers() {
		Random random = new Random();
		return random.nextInt(21);
	}
	
	
}
