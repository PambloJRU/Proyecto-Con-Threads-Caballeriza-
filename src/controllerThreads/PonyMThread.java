package controllerThreads;

import java.util.Random;

import domain.PonyF;
import domain.PonyM;
import domain.PonyMBehaviour;
import logic.ListLogicThreads;
import logic.Suppliers;
import logic.TableWriter;
import view.StableGUI;

public class PonyMThread extends Thread {

	private PonyM pM;
	private StableGUI gui;
	private PonyMBehaviour pmBehav;
	private int randomSet = 0;
	private boolean pause;
	private String comparator = "";
	private ListLogicThreads lgTh;

	private int actualTime;
	private int initTime = 0;
	private int passedTime = 0;

	private int tempPauseTime = 0;
	private int tempResumeTime = 0;
	private int tempPauseLength = 0;

	public PonyMThread(PonyM pM, StableGUI gui, ListLogicThreads lgTh) {

		this.pM = pM;
		this.gui = gui;
		this.lgTh = lgTh;
		pmBehav = new PonyMBehaviour(this.pM, this.gui);
	}

	@Override
	public void run() {

		initTime = (int) System.currentTimeMillis();
		passedTime = 0;
		while (passedTime < gui.getTime()) {

			pmBehav.moveRandom();
			TableWriter.addPmMove();
			
			if (randomSet == 0) {
				randomFirstRun();
			}

			gui.getBtnMatrix()[pmBehav.getX()][pmBehav.getY()].setText(pM.getSpecies());
			try {
				sleep(2000); // Espera 2 segundos
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(lgTh.randomUseSuppliers()==20) {
	        	randomSupplier();
	        }

			actualTime = (int) System.currentTimeMillis();
			passedTime = actualTime - initTime;

			comparator = gui.getBtnMatrix()[pmBehav.getX()][pmBehav.getY()].getText();
			killPony(comparator);

			gui.getBtnMatrix()[pmBehav.getX()][pmBehav.getY()].setText("");

			synchronized (this) {
				if (pause) {
					try {
						tempPauseTime = (int) System.currentTimeMillis();
						gui.getBtnMatrix()[pmBehav.getX()][pmBehav.getY()].setText(pM.getSpecies());
						wait();
						tempResumeTime = (int) System.currentTimeMillis(); // mantiene el tiempo al reanudar
						tempPauseLength = tempResumeTime - tempPauseTime; // analiza la duración de la pausa
						initTime += tempPauseLength;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	public synchronized void pauseThread() {
		this.pause = true;
	}

	public synchronized void resumeThread() {
		this.pause = false;
		notify();
		gui.getBtnMatrix()[pmBehav.getX()][pmBehav.getY()].setText("");
	}

	public void randomFirstRun() {
		int tempx = pmBehav.randomMatrixPoint();
		int tempy = pmBehav.randomMatrixPoint();
		gui.getBtnMatrix()[tempx][tempy].setText(pM.getSpecies());
		pmBehav.setX(tempx);
		pmBehav.setY(tempy);
		randomSet++;
		gui.getBtnMatrix()[tempx][tempy].setText("");
	}
	
	

	public synchronized void killPony(String comparator) {
		if (comparator.equals("CA") || comparator.equals("YE") || comparator.equals("BUM") || comparator.equals("BUH")
				|| comparator.equals("MM") || comparator.equals("MH") || comparator.equals("BGM")
				|| comparator.equals("BGH")) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			passedTime = 1000000;
			lgTh.getPmArray().remove(pM);
			lgTh.getPonyMThArray().remove(this);
			System.out.println("Se murió el pony");
			TableWriter.addPmDeaths();
		}

	}
	
	public synchronized void reproducePony() {
		Random r = new Random();
		int ran = r.nextInt(2);
		if(ran==0) {
			TableWriter.addPmReproduct();
			System.out.println("pony macho nuevo :D");
			lgTh.getPmArray().add(new PonyM(150000,2000,"PM"));
			lgTh.getPonyMThArray().add(new PonyMThread(lgTh.getPmArray().get(lgTh.getPonyMThArray().size()),gui,lgTh));
			lgTh.getPonyMThArray().get(lgTh.getPonyMThArray().size()-1).start();
		}else {
			TableWriter.addPfReproduct();
			lgTh.getPfArray().add(new PonyF(150000,3000,"PF"));
			lgTh.getPonyFThArray().add(new PonyFThread(lgTh.getPfArray().get(lgTh.getPonyFThArray().size()),gui,lgTh));
			lgTh.getPonyFThArray().get(lgTh.getPonyFThArray().size()-1).start();
			System.out.println("pony hembra nueva :D");
		}
		
	}
	
	public synchronized void randomSupplier() {
		TableWriter.addPmSup();
		switch(Suppliers.randomOptionSupply()) {
		case 0:if(gui.getBtnHay().getText().equals("...")) {
			pauseThread();
			gui.getBtnMatrix()[pmBehav.getX()][pmBehav.getY()].setText("");
			gui.getBtnHay().setText("PM");
			Suppliers.consumeHay();
			gui.getHayQuantity().setText("Cantidad: " + Suppliers.getHay());
			
			try {
				sleep(2000);	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			resumeThread();
			gui.getBtnHay().setText("...");
		}
		break;
		case 1:
			if(gui.getBtnFood().getText().equals("...")) {
				pauseThread();
				gui.getBtnMatrix()[pmBehav.getX()][pmBehav.getY()].setText("");
				gui.getBtnFood().setText("PM");
				Suppliers.consumeFood();
				gui.getFoodQuantity().setText("Cantidad: " + Suppliers.getFood());
				
				try {
					sleep(3000);	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				resumeThread();
				gui.getBtnFood().setText("...");
			}
			break;
		case 2:
			if(gui.getBtnVitamins().getText().equals("...")) {
				pauseThread();
				gui.getBtnMatrix()[pmBehav.getX()][pmBehav.getY()].setText("");
				gui.getBtnVitamins().setText("PM");
				Suppliers.consumeVitamins();
				gui.getVitaminsQuantity().setText("Cantidad: " + Suppliers.getVitamins());
				
				try {
					sleep(2000);	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				resumeThread();
				gui.getBtnVitamins().setText("...");
			}
			break;
		case 3:	
			if(gui.getBtnWater().getText().equals("...")) {
				pauseThread();
				gui.getBtnMatrix()[pmBehav.getX()][pmBehav.getY()].setText("");
				gui.getBtnWater().setText("PM");
				Suppliers.consumeWater();
				gui.getWaterQuantity().setText("Cantidad: " + Suppliers.getWater());
				
				try {
					sleep(3000);	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				resumeThread();
				gui.getBtnWater().setText("...");
			}
			break;
			
		}
		
		
	}
}
