package logic;

import java.util.ArrayList;

import view.StableGUI;

public class TableWriter extends Thread {

	ListLogicThreads lgth;
	StableGUI gui;
	
	
	public TableWriter(ListLogicThreads lgth, StableGUI gui) {
		this.gui=gui;
		this.lgth=lgth;
	}
	
	//public static ArrayList<Integer> HorseCount = new ArrayList<>(6);
	public static int HorseData[] = new int[7];
	public static int MareData[] = new int[7];
	public static int DkfData[] = new int[7];
	public static int DkMData[] = new int[7];
	public static int PmData[] = new int[7];
	public static int PfData[] = new int[7];
	public static int BurMData[] = new int[7];
	public static int BurFData[] = new int[7];
	public static int MuleMData[] = new int[7];
	public static int MuleFData[] = new int[7];
	
	
	
	public static void addHorseMove() {
		HorseData[2]++;
	}
	public static void addHorseFights(){
		HorseData[3]++;
	}
	public static void addHorseReproduct() {
		HorseData[4]++;
	}
	public static void addHorseDeaths() {
		HorseData[5]++;
	}
	public static void addHorseSup() {
		HorseData[6]++;
	}

	public static void addMareMove() {
	    MareData[2]++;
	}
	public static void addMareFights() {
	    MareData[3]++;
	}
	public static void addMareReproduct() {
	    MareData[4]++;
	}
	public static void addMareDeaths() {
	    MareData[5]++;
	}
	public static void addMareSup() {
	    MareData[6]++;
	}
	
	public static void addDkfMove() {
	    DkfData[2]++;
	}
	public static void addDkfFights() {
	    DkfData[3]++;
	}
	public static void addDkfReproduct() {
	    DkfData[4]++;
	}
	public static void addDkfDeaths() {
	    DkfData[5]++;
	}
	public static void addDkfSup() {
	    DkfData[6]++;
	}
	
	public static void addDkMMove() {
	    DkMData[2]++;
	}
	public static void addDkMFights() {
	    DkMData[3]++;
	}
	public static void addDkMReproduct() {
	    DkMData[4]++;
	}
	public static void addDkMDeaths() {
	    DkMData[5]++;
	}
	public static void addDkMSup() {
	    DkMData[6]++;
	}
	
	public static void addPmMove() {
	    PmData[2]++;
	}
	public static void addPmFights() {
	    PmData[3]++;
	}
	public static void addPmReproduct() {
	    PmData[4]++;
	}
	public static void addPmDeaths() {
	    PmData[5]++;
	}
	public static void addPmSup() {
	    PmData[6]++;
	}
	
	public static void addPfMove() {
	    PfData[2]++;
	}
	public static void addPfFights() {
	    PfData[3]++;
	}
	public static void addPfReproduct() {
	    PfData[4]++;
	}
	public static void addPfDeaths() {
	    PfData[5]++;
	}
	public static void addPfSup() {
	    PfData[6]++;
	}
	
	public static void addBurMMove() {
	    BurMData[2]++;
	}
	public static void addBurMFights() {
	    BurMData[3]++;
	}
	public static void addBurMReproduct() {
	    BurMData[4]++;
	}
	public static void addBurMDeaths() {
	    BurMData[5]++;
	}
	public static void addBurMSup() {
	    BurMData[6]++;
	}
	
	public static void addBurFMove() {
	    BurFData[2]++;
	}
	public static void addBurFFights() {
	    BurFData[3]++;
	}
	public static void addBurFReproduct() {
	    BurFData[4]++;
	}
	public static void addBurFDeaths() {
	    BurFData[5]++;
	}
	public static void addBurFSup() {
	    BurFData[6]++;
	}
	
	public static void addMuleMMove() {
	    MuleMData[2]++;
	}
	public static void addMuleMFights() {
	    MuleMData[3]++;
	}
	public static void addMuleMReproduct() {
	    MuleMData[4]++;
	}
	public static void addMuleMDeaths() {
	    MuleMData[5]++;
	}
	public static void addMuleMSup() {
	    MuleMData[6]++;
	}
	
	public static void addMuleFMove() {
	    MuleFData[2]++;
	}
	public static void addMuleFFights() {
	    MuleFData[3]++;
	}
	public static void addMuleFReproduct() {
	    MuleFData[4]++;
	}
	public static void addMuleFDeaths() {
	    MuleFData[5]++;
	}
	public static void addMuleFSup() {
	    MuleFData[6]++;
	}
	
	
	
	@Override
	public synchronized void run() {
		while(lgth.activitychecker()) {
		
			try {
				sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for (int i = 2; i < HorseData.length; i++) {
				gui.getDtm().setValueAt(lgth.getArrayHorses().size(), 0, 1);
				gui.getDtm().setValueAt(HorseData[i], 0, i);
			}
			for (int i = 2; i < MareData.length; i++) {
				gui.getDtm().setValueAt(lgth.getArrayMares().size(), 1, 1);
				gui.getDtm().setValueAt(MareData[i], 1, i);
			}
			for (int i = 2; i < DkMData.length; i++) {
				gui.getDtm().setValueAt(lgth.getDkMArray().size(), 2, 1);
			    gui.getDtm().setValueAt(DkMData[i], 2, i);
			}
			for (int i = 2; i < DkfData.length; i++) {
				gui.getDtm().setValueAt(lgth.getDkFArray().size(), 3, 1);
			    gui.getDtm().setValueAt(DkfData[i], 3, i);
			}
			for (int i = 2; i < PmData.length; i++) {
				gui.getDtm().setValueAt(lgth.getPmArray().size(), 4, 1);
			    gui.getDtm().setValueAt(PmData[i], 4, i);
			}
			for (int i = 2; i < PfData.length; i++) {
				gui.getDtm().setValueAt(lgth.getPfArray().size(), 5, 1);
			    gui.getDtm().setValueAt(PfData[i], 5, i);
			}
			for (int i = 2; i < MuleFData.length; i++) {
				gui.getDtm().setValueAt(lgth.getMuleArray().size(), 6, 1);
			    gui.getDtm().setValueAt(MuleFData[i], 6, i);
			}
			for (int i = 2; i < MuleMData.length; i++) {
				gui.getDtm().setValueAt(lgth.getMuleArray().size(), 7, 1);
			    gui.getDtm().setValueAt(MuleMData[i], 7, i);
			}
			for (int i = 2; i < BurFData.length; i++) {
				gui.getDtm().setValueAt(lgth.getBurArray().size(), 8, 1);
			    gui.getDtm().setValueAt(BurFData[i], 8, i);
			}
			for (int i = 2; i < BurMData.length; i++) {
				gui.getDtm().setValueAt(lgth.getBurArray().size(), 9, 1);
			    gui.getDtm().setValueAt(BurMData[i], 9, i);
			}
			
			
			
		}
	}
	
	
	
}
