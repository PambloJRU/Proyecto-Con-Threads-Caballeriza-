package controllerThreads;

import java.util.Random;

import domain.DonkeyF;
import domain.DonkeyFBehaviour;
import domain.Mule;
import logic.ListLogicThreads;
import logic.Suppliers;
import logic.TableWriter;
import view.StableGUI;

public class DonkeyFThread extends Thread {

	private DonkeyF dkf;
	private StableGUI gui;
	private DonkeyFBehaviour dkFBehav;
	private int randomSet=0;
	private boolean pause;
	private ListLogicThreads lgTh;
	
	private int actualTime;
	private int initTime = 0; 
    private int passedTime = 0;
    
    private int tempPauseTime = 0;
    private int tempResumeTime = 0;
    private int tempPauseLength =0;
	
	public DonkeyFThread(DonkeyF dkf, StableGUI gui, ListLogicThreads lgTh) {
		
			this.dkf=dkf;
			this.gui=gui;
			this.lgTh=lgTh;
			dkFBehav = new DonkeyFBehaviour(this.dkf,this.gui);
	}

	@Override
	public void run() {
					
		 initTime = (int) System.currentTimeMillis(); 
	     passedTime = 0;
			 while (passedTime < gui.getTime()) {
				 
				 	dkFBehav.moveRandom(); 
				 	TableWriter.addDkfMove();
				 	
				 	if(randomSet==0) {
				 		randomFirstRun();
				 	}
				 	
				 	
				 	
				 
			        gui.getBtnMatrix()[dkFBehav.getX()][dkFBehav.getY()].setText(dkf.getSpecies());
			        try {
			            sleep(2000); // Espera 2 segundos
			        } catch (InterruptedException e) {
			            e.printStackTrace();
			        }
			        
			        if(lgTh.randomUseSuppliers()==20) {
			        	randomSupplier();
			        }
			        
			        gui.getBtnMatrix()[dkFBehav.getX()][dkFBehav.getY()].setText("");
			           
			        actualTime = (int) System.currentTimeMillis();
			        passedTime = (int) (actualTime - initTime);

			        synchronized(this) {
						if(pause) {
							try {
								tempPauseTime = (int) System.currentTimeMillis();
								gui.getBtnMatrix()[dkFBehav.getX()][dkFBehav.getY()].setText(dkf.getSpecies());
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
		gui.getBtnMatrix()[dkFBehav.getX()][dkFBehav.getY()].setText("");
	}
	
	public void randomFirstRun() {
		int tempx = dkFBehav.randomMatrixPoint();
 		int tempy = dkFBehav.randomMatrixPoint();
 		gui.getBtnMatrix()[tempx][tempy].setText(dkf.getSpecies());
 		dkFBehav.setX(tempx);
 		dkFBehav.setY(tempy);
 		randomSet++;
 		gui.getBtnMatrix()[tempx][tempy].setText("");
	}
	
	public synchronized void reproduceMule() {
		Random r = new Random();
		int ran = r.nextInt(2);
		if(ran==0) {
			System.out.println("Mulo nuevo xd");
			TableWriter.addMuleMReproduct();
			lgTh.getMuleArray().add(new Mule(250000,2000,"MM"));
			lgTh.getMuleArray().get(lgTh.getMuleArray().size()-1).setGender("MM");
			lgTh.getMuleThArray().add(new MuleThread(lgTh.getMuleArray().get(lgTh.getMuleThArray().size()),gui,lgTh));
			lgTh.getMuleThArray().get(lgTh.getMuleThArray().size()-1).start();
		}else {
			TableWriter.addMuleFReproduct();
			System.out.println("MULA nueva xd");
			lgTh.getMuleArray().add(new Mule(250000,2000,"MF"));
			lgTh.getMuleArray().get(lgTh.getMuleArray().size()-1).setGender("MF");
			lgTh.getMuleThArray().add(new MuleThread(lgTh.getMuleArray().get(lgTh.getMuleThArray().size()),gui,lgTh));
			lgTh.getMuleThArray().get(lgTh.getMuleThArray().size()-1).start();
		}
	}
	
public synchronized void randomSupplier() {
		TableWriter.addDkfSup();
		switch(Suppliers.randomOptionSupply()) {
		case 0:if(gui.getBtnHay().getText().equals("...")) {
			pauseThread();
			gui.getBtnMatrix()[dkFBehav.getX()][dkFBehav.getY()].setText("");
			gui.getBtnHay().setText("BUH");
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
				gui.getBtnMatrix()[dkFBehav.getX()][dkFBehav.getY()].setText("");
				gui.getBtnFood().setText("BUH");
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
				gui.getBtnMatrix()[dkFBehav.getX()][dkFBehav.getY()].setText("");
				gui.getBtnVitamins().setText("BUH");
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
				gui.getBtnMatrix()[dkFBehav.getX()][dkFBehav.getY()].setText("");
				gui.getBtnWater().setText("BUH");
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
