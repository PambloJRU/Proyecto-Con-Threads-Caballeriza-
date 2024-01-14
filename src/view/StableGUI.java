package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.Color;

public class StableGUI extends JFrame {

	private JPanel contentPane;
	private JPanel panelMatrix;
	private JButton stableMatrix[][];
	private JComboBox cmbYears;
	private JButton btnRunSim;
	private JTable tblEquines;
	private DefaultTableModel dtm;
	private Object data[][];
	private JScrollPane SPTableEquines;
	private JButton btnPause;
	private JButton btnResume;
	private JLabel lblNewLabel;
	private JButton btnHay;
	private JButton btnFood;
	private JButton btnVitamins;
	private JButton btnWater;
	private JLabel lblHen;
	private JLabel lblFood;
	private JLabel lblVitamins;
	private JLabel lblWater;
	private JLabel hayQuantity;
	private JLabel foodQuantity;
	private JLabel vitaminsQuantity;
	private JLabel waterQuantity;

	public StableGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		setTitle("Proyecto caballeriza");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanelMatrix());
		contentPane.add(getCmbYears());
		contentPane.add(getBtnRunSim());
		contentPane.add(getBtnPauseSim());
		contentPane.add(getBtnResume());
		setCreateMatrix(20);
		
		setTBEquineModel(data, getColumnsNames());
		setTblEquine(dtm);
		setSPTblEquines(tblEquines);
		
		getContentPane().add(SPTableEquines);
		contentPane.add(getLblNewLabel());
		contentPane.add(getBtnHay());
		contentPane.add(getBtnFood());
		contentPane.add(getBtnVitamins());
		contentPane.add(getBtnWater());
		contentPane.add(getLblHen());
		contentPane.add(getLblFood());
		contentPane.add(getLblVitamins());
		contentPane.add(getLblWater());
		contentPane.add(getHayQuantity());
		contentPane.add(getFoodQuantity());
		contentPane.add(getVitaminsQuantity());
		contentPane.add(getWaterQuantity());
		preTableStructure();
		
	}
	
	public JPanel getPanelMatrix() {
		if (panelMatrix == null) {
			panelMatrix = new JPanel();
			panelMatrix.setBounds(0, 0, 1080, 601);
		}
		return panelMatrix;
	}
	
	public void setCreateMatrix(int size) {
		
		panelMatrix.setLayout(new GridLayout(size,size));
		stableMatrix = new JButton[size][size];
		for (int i = 0; i < stableMatrix.length; i++) {
			for(int j=0;j <stableMatrix[i].length;j++) {
				stableMatrix[i][j] = new JButton("");
				stableMatrix[i][j].setFont(new Font("Segoe UI Black",1,7));
				stableMatrix[i][j].setBackground(new Color(255,228,196));
				panelMatrix.add(stableMatrix[i][j]);
			}
		}
		getContentPane().add(panelMatrix);
		setVisible(true);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JComboBox getCmbYears() {
		if (cmbYears == null) {
			cmbYears = new JComboBox();
			cmbYears.setBackground(new Color(188, 143, 143));
			cmbYears.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
			cmbYears.setModel(new DefaultComboBoxModel(new String[] {"1 AÑO", "3 AÑOS", "5 AÑOS"}));
			cmbYears.setBounds(1129, 60, 109, 22);
		}
		return cmbYears;
	}
	
	public JButton getBtnRunSim() {
		if (btnRunSim == null) {
			btnRunSim = new JButton("CORRER");
			btnRunSim.setBackground(new Color(255, 228, 196));
			btnRunSim.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
			btnRunSim.setBounds(1129, 103, 109, 22);
		}
		return btnRunSim;
	}
	
	public JButton getBtnPauseSim() {
		if(btnPause==null) {
			btnPause = new JButton("PAUSAR");
			btnPause.setBackground(new Color(255, 228, 196));
			btnPause.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
			btnPause.setBounds(1129, 147, 109, 23);
			contentPane.add(btnPause);
			btnPause.setEnabled(false);
		}
			return btnPause;
	}
	
	public JButton getBtnResume() {
		if (btnResume == null) {
			btnResume = new JButton("REANUDAR");
			btnResume.setBackground(new Color(255, 228, 196));
			btnResume.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
			btnResume.setBounds(1129, 193, 109, 23);
			btnResume.setEnabled(false);
		}
		return btnResume;
	}
	
	public JButton[][] getBtnMatrix(){
		return this.stableMatrix;
	}
	
	public Object[][] getDataFill(){
		Object data[][]= {{"","","","","","",""}};
		return data;
	}
	
	public void preTableStructure() {
		String[] names = {"Caballos", "Yeguas", "Burros", "Burras", 
					       "Pony Macho", "Pony Hembra", "Mula", "Mulo", 
				            "Burdégano macho", "Burdégano hembra"};
		for (int i = 0; i < names.length; i++) {
		    Object[] row = new Object[]{names[i], 0, 0, 0, 0, 0, 0};
		    getDtm().addRow(row);
		}
	}
	
	public String[] getColumnsNames() {
		String[] columnsNames = {"Especie", "Cantidad","Avances","Enfrentamientos","Reproducciones","Muertes","Uso de suministros"};
		return columnsNames;
	}
	
	public DefaultTableModel getDtm() {
		return dtm;
	}

	public void setTBEquineModel(Object[][] data,String names[]){
		this.dtm = new DefaultTableModel(data,names);
	}
	
	public void setTblEquine(DefaultTableModel dtm) {
		tblEquines = new JTable(dtm);
		
		tblEquines.setEnabled(false);
	    tblEquines.getTableHeader().setReorderingAllowed(false);
	    tblEquines.getTableHeader().setResizingAllowed(false);
		
	}
	public void setSPTblEquines(JTable Equines) {
		this.SPTableEquines=new JScrollPane(Equines);
		SPTableEquines.setBounds(10, 612, 822, 103);
	}
	
	public int getTime() {
		if(getCmbYears().getSelectedItem().toString().equals("1 AÑO")) {
			 return 60000;
		}
		if(getCmbYears().getSelectedItem().toString().equals("3 AÑOS")) {
			 return 180000;
		}
		if(getCmbYears().getSelectedItem().toString().equals("5 AÑOS")) {
			 return 300000;
		}
		else {
			return 0;
		}
	}
	public JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Selecionar Duración:");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
			lblNewLabel.setBounds(1119, 26, 129, 14);
		}
		return lblNewLabel;
	}
	public JButton getBtnHay() {
		if (btnHay == null) {
			btnHay = new JButton("...");
			btnHay.setBounds(1090, 284, 71, 43);
			btnHay.setBackground(new Color(255, 228, 196));
		}
		return btnHay;
	}
	public JButton getBtnFood() {
		if (btnFood == null) {
			btnFood = new JButton("...");
			btnFood.setBounds(1090, 365, 71, 43);
			btnFood.setBackground(new Color(255, 228, 196));
		}
		return btnFood;
	}
	public JButton getBtnVitamins() {
		if (btnVitamins == null) {
			btnVitamins = new JButton("...");
			btnVitamins.setBounds(1090, 445, 71, 43);
			btnVitamins.setBackground(new Color(255, 228, 196));
		}
		return btnVitamins;
	}
	public JButton getBtnWater() {
		if (btnWater == null) {
			btnWater = new JButton("...");
			btnWater.setBounds(1090, 536, 71, 43);
			btnWater.setBackground(new Color(255, 228, 196));
		}
		return btnWater;
	}
	public JLabel getLblHen() {
		if (lblHen == null) {
			lblHen = new JLabel("HENO");
			lblHen.setForeground(Color.WHITE);
			lblHen.setBounds(1090, 271, 59, 14);
		}
		return lblHen;
	}
	public JLabel getLblFood() {
		if (lblFood == null) {
			lblFood = new JLabel("ALIMENTO");
			lblFood.setForeground(Color.WHITE);
			lblFood.setBounds(1090, 352, 71, 14);
		}
		return lblFood;
	}
	public JLabel getLblVitamins() {
		if (lblVitamins == null) {
			lblVitamins = new JLabel("VITAMINAS");
			lblVitamins.setForeground(Color.WHITE);
			lblVitamins.setBounds(1090, 431, 71, 14);
		}
		return lblVitamins;
	}
	public JLabel getLblWater() {
		if (lblWater == null) {
			lblWater = new JLabel("AGUA");
			lblWater.setForeground(Color.WHITE);
			lblWater.setBounds(1090, 516, 46, 22);
		}
		return lblWater;
	}
	public JLabel getHayQuantity() {
		if (hayQuantity == null) {
			hayQuantity = new JLabel("Cantidad: 1000");
			hayQuantity.setForeground(Color.WHITE);
			hayQuantity.setBounds(1090, 327, 89, 14);
		}
		return hayQuantity;
	}
	public JLabel getFoodQuantity() {
		if (foodQuantity == null) {
			foodQuantity = new JLabel("Cantidad: 500");
			foodQuantity.setForeground(Color.WHITE);
			foodQuantity.setBounds(1090, 406, 92, 14);
		}
		return foodQuantity;
	}
	public JLabel getVitaminsQuantity() {
		if (vitaminsQuantity == null) {
			vitaminsQuantity = new JLabel("Cantidad: 200");
			vitaminsQuantity.setForeground(Color.WHITE);
			vitaminsQuantity.setBounds(1090, 487, 92, 14);
		}
		return vitaminsQuantity;
	}
	public JLabel getWaterQuantity() {
		if (waterQuantity == null) {
			waterQuantity = new JLabel("Cantidad: 1000");
			waterQuantity.setForeground(Color.WHITE);
			waterQuantity.setBounds(1090, 587, 92, 14);
		}
		return waterQuantity;
	}
}

