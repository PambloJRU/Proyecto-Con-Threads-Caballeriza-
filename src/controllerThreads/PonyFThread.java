package controllerThreads;

import domain.PonyF;
import domain.PonyFBehaviour;
import logic.ListLogicThreads;
import logic.Suppliers;
import logic.TableWriter;
import view.StableGUI;

public class PonyFThread extends Thread {

	private PonyF pF;
	private StableGUI gui;
	private PonyFBehaviour pfBehav;
	private int randomSet=0;
	private boolean pause;
	private String comparator="";
	private ListLogicThreads lgTh;

	private int actualTime;
	private int initTime = 0; 
    private int passedTime = 0;
    
    private int tempPauseTime = 0;
    private int tempResumeTime = 0;
    private int tempPauseLength =0;
	
	public PonyFThread(PonyF pf, StableGUI gui, ListLogicThreads lgTh) {
		
			this.pF=pf;
			this.gui=gui;
			this.lgTh=lgTh;
			pfBehav = new PonyFBehaviour(this.pF,this.gui);
	}

	@Override
	public void run() {
					
		 initTime = (int) System.currentTimeMillis(); 
	     passedTime = 0;
			 while (passedTime < gui.getTime()) {
				 
				 	pfBehav.moveRandom(); 
				 	TableWriter.addPfMove();
				 	
				 	
				 	if(randomSet==0) {
				 		randomFirstRun();
				 	}
				 	 
				 	
			        gui.getBtnMatrix()[pfBehav.getX()][pfBehav.getY()].setText(pF.getSpecies());
			        try {
			            sleep(2000); // Espera 3 segundos
			        } catch (InterruptedException e) {
			            e.printStackTrace();
			        }
			           
			        actualTime = (int) System.currentTimeMillis();
			        passedTime = actualTime - initTime;

			        comparator = gui.getBtnMatrix()[pfBehav.getX()][pfBehav.getY()].getText();
			        killPony(comparator);
			        
			        if(lgTh.randomUseSuppliers()==20) {
			        	randomSupplier();
			        }
			        
			        gui.getBtnMatrix()[pfBehav.getX()][pfBehav.getY()].setText("");
			        
			        
			        synchronized(this){
						if(pause) {
							try {
								tempPauseTime = (int) System.currentTimeMillis();
								gui.getBtnMatrix()[pfBehav.getX()][pfBehav.getY()].setText(pF.getSpecies());
								wait();
								tempResumeTime = (int) System.currentTimeMillis(); 
				                tempPauseLength = tempResumeTime - tempPauseTime; 
				                initTime += tempPauseLength; 
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
			    }		 	
	}
	
	public synchronized void pauseThread() {
		this.pause=true;
	}
	
	public synchronized void resumeThread() {
		this.pause=false;
		notify();
		gui.getBtnMatrix()[pfBehav.getX()][pfBehav.getY()].setText("");
	}
	
	public void randomFirstRun() {
		int tempx = pfBehav.randomMatrixPoint();
 		int tempy = pfBehav.randomMatrixPoint();
 		gui.getBtnMatrix()[tempx][tempy].setText(pF.getSpecies());
 		pfBehav.setX(tempx);
 		pfBehav.setY(tempy);
 		randomSet++;
 		gui.getBtnMatrix()[tempx][tempy].setText("");
	}
	
	public void killPony(String comparator) {
		if (comparator.equals("CA") || comparator.equals("YE") || comparator.equals("BUM") || comparator.equals("BUH")
                || comparator.equals("MM") || comparator.equals("MH") || comparator.equals("BGM") || comparator.equals("BGH")) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		passedTime=1000000;
		lgTh.getPfArray().remove(pF);
		lgTh.getPonyFThArray().remove(this);
		System.out.println("Se murio LA pony");
		TableWriter.addPfDeaths();
		}
	}
	
	public synchronized void randomSupplier() {
		TableWriter.addPfSup();
		switch(Suppliers.randomOptionSupply()) {
		case 0:if(gui.getBtnHay().getText().equals("...")) {
			pauseThread();
			gui.getBtnMatrix()[pfBehav.getX()][pfBehav.getY()].setText("");
			gui.getBtnHay().setText("PF");
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
				gui.getBtnMatrix()[pfBehav.getX()][pfBehav.getY()].setText("");
				gui.getBtnFood().setText("PF");
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
				gui.getBtnMatrix()[pfBehav.getX()][pfBehav.getY()].setText("");
				gui.getBtnVitamins().setText("PF");
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
				gui.getBtnMatrix()[pfBehav.getX()][pfBehav.getY()].setText("");
				gui.getBtnWater().setText("PF");
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
