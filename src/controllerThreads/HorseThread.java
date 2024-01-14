package controllerThreads;


import java.util.Random;

import domain.Burgundy;
import domain.Horse;
import domain.HorseBehaviour;
import domain.Mare;
import logic.ListLogicThreads;
import logic.Suppliers;
import logic.TableWriter;
import view.StableGUI;

public class HorseThread extends Thread implements Runnable {

	private Horse horse;
	private StableGUI gui;
	private HorseBehaviour hBehav;
	private int randomSet=0;
	private boolean pause;
	
	private int actualTime;
	private int initTime = 0; 
    private int passedTime = 0;
    
    private int tempPauseTime = 0;
    private int tempResumeTime = 0;
    private int tempPauseLength =0;
    
    ListLogicThreads lgTh;
	
	public HorseThread(Horse horse, StableGUI gui, ListLogicThreads lgTh) {
		
			this.horse=horse;
			this.gui=gui;
			hBehav = new HorseBehaviour(this.horse,this.gui);
			this.lgTh=lgTh;
	}
	
	@Override
	public void run() {
					
		 initTime = (int) System.currentTimeMillis(); // tiempo al arrancar
	     passedTime = 0;
			 while (passedTime < gui.getTime()) {
				 
				 	hBehav.moveRandom(); // Mueve el caballo
				 	TableWriter.addHorseMove();
				 	
				 	if(randomSet==0) {
				 		randomFirstRun();
				 	}
				 	
				 	if(gui.getBtnMatrix()[hBehav.getX()][hBehav.getY()].getText().equals("YE")) {
				 		System.out.println("antes de la reproduccion para un caballo o yegua");
				 		
				 		try {
							sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				 		reproduceHorse();
				 	}
				 	
				 	if(gui.getBtnMatrix()[hBehav.getX()][hBehav.getY()].getText().equals("BUH")) {
				 		try {
							sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				 		reproduceBurgundy();
				 	}
				 	
				 	actualTime = (int) System.currentTimeMillis();
			        passedTime = (int) (actualTime - initTime);
			        
			        if(gui.getBtnMatrix()[hBehav.getX()][hBehav.getY()].getText().equals("CA")) {
				 		try {
							sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				 		versusHorse();
				 	}
			        
				 // Actualiza la posición del caballo en la matriz
			        gui.getBtnMatrix()[hBehav.getX()][hBehav.getY()].setText(horse.getSpecies());
			        
			        if(lgTh.randomUseSuppliers()==20) {
			        	randomSupplier();
			        }
			        try {
			            sleep(2000); // Espera 2 segundos
			        } catch (InterruptedException e) {
			            e.printStackTrace();
			        }
			        gui.getBtnMatrix()[hBehav.getX()][hBehav.getY()].setText("");
			        
			        
			        synchronized(this) {
						if(pause) {
							try {
								tempPauseTime = (int) System.currentTimeMillis();
								gui.getBtnMatrix()[hBehav.getX()][hBehav.getY()].setText(horse.getSpecies());
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
	
	//suspender un  hilo
	public synchronized void pauseThread() {
		this.pause=true;
	}
	
	//reanudar hilo
	public synchronized void resumeThread() {
		this.pause=false;
		notify();
		gui.getBtnMatrix()[hBehav.getX()][hBehav.getY()].setText("");
	}
	
	public void randomFirstRun() {
		int tempx = hBehav.randomMatrixPoint();
 		int tempy = hBehav.randomMatrixPoint();
 		gui.getBtnMatrix()[tempx][tempy].setText(horse.getSpecies());
 		hBehav.setX(tempx);
 		hBehav.setY(tempy);
 		randomSet++;
 		gui.getBtnMatrix()[tempx][tempy].setText("");
	}
	
	public synchronized void reproduceHorse() {
		
		Random r = new Random();
		int ran = r.nextInt(2);
		if(ran==0) {
			System.out.println("caballo nuevo :D");
			TableWriter.addHorseReproduct();
			lgTh.getArrayHorses().add(new Horse(500000,2000,"CA"));
			lgTh.getHorseThArray().add(new HorseThread(lgTh.getArrayHorses().get(lgTh.getHorseThArray().size()),gui,lgTh));
			lgTh.getHorseThArray().get(lgTh.getHorseThArray().size()-1).start();
		}else {
			TableWriter.addMareReproduct(); 
			lgTh.getArrayMares().add(new Mare(500000,3000,"YE"));
			lgTh.getMareThArray().add(new MareThread(lgTh.getArrayMares().get(lgTh.getMareThArray().size()),gui,lgTh));
			lgTh.getMareThArray().get(lgTh.getMareThArray().size()-1).start();
			System.out.println("Yegua nueva :D");
		}
		
	}
	
	public synchronized void reproduceBurgundy() {
		Random r = new Random();
		int ran = r.nextInt(2);
		if(ran==0) {
			TableWriter.addBurMReproduct();
			lgTh.getBurArray().add(new Burgundy(500000,2000,"BGM"));
			lgTh.getBurArray().get(lgTh.getBurArray().size()-1).setGender("BGM");
			lgTh.getBurgundyThArray().add(new BurgundyThread(lgTh.getBurArray().get(lgTh.getBurgundyThArray().size()),gui,lgTh));
			lgTh.getBurgundyThArray().get(lgTh.getBurgundyThArray().size()-1).start();
			System.out.println("burdeganillo nuevo :v");
		}else {
			TableWriter.addBurFReproduct();
			lgTh.getBurArray().add(new Burgundy(500000,3000,"BGH"));
			lgTh.getBurArray().get(lgTh.getBurArray().size()-1).setGender("BGH");
			lgTh.getBurgundyThArray().add(new BurgundyThread(lgTh.getBurArray().get(lgTh.getBurgundyThArray().size()),gui,lgTh));
			lgTh.getBurgundyThArray().get(lgTh.getBurgundyThArray().size()-1).start();
			System.out.println("BURDEGANA nueva");
		}
	}
	
	public synchronized void versusHorse() {
		passedTime = 1000000;
		lgTh.getArrayHorses().remove(horse);
        lgTh.getHorseThArray().remove(this);
		System.out.println("Un caballo se peleo y murio");
		TableWriter.addHorseDeaths();
	}
	
public synchronized void randomSupplier() {
		
		switch(Suppliers.randomOptionSupply()) {
		
		case 0:if(gui.getBtnHay().getText().equals("...")) {
			pauseThread();
			gui.getBtnMatrix()[hBehav.getX()][hBehav.getY()].setText("");
			gui.getBtnHay().setText("CA");
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
				gui.getBtnMatrix()[hBehav.getX()][hBehav.getY()].setText("");
				gui.getBtnFood().setText("CA");
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
				gui.getBtnMatrix()[hBehav.getX()][hBehav.getY()].setText("");
				gui.getBtnVitamins().setText("CA");
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
				gui.getBtnMatrix()[hBehav.getX()][hBehav.getY()].setText("");
				gui.getBtnWater().setText("CA");
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
		TableWriter.addHorseSup();
		
		
	}

}

