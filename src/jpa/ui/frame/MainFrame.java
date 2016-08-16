package jpa.ui.frame;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import jpa.model.entity.Event;
import jpa.model.service.EventService;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//UI variables
	private JPanel contentPane;
	JScrollPane scrollPane = new JScrollPane();
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtSearch;
	private JTextField txtCost;
	private JTextField txtPaying;
	private JTextField txtPaid;
	private JComboBox<String> cbxYear = new JComboBox<String>();
	private JComboBox<String> cbxMonth = new JComboBox<String>();
	
	//Variables
	private EventService eventService = new EventService();
	
	/**
	 * Table
	 */
	private Object[][] getTableData() {
		String key = txtSearch.getText();
		List<Event> listAllEvent = eventService.getAllEvent();
		List<Event> subList = new ArrayList<>();
		
		for (Event event : listAllEvent) {
			if (event.getNote().contains(key)) {
				subList.add(event);
			}
		}
		
		Object[][] objects = new Object[subList.size()][5];
		
		for (int i = 0; i < subList.size(); i++) {
			objects[i][0] = subList.get(i).getId();
			objects[i][1] = subList.get(i).getDay() + "/" + subList.get(i).getMonth() + "/" + subList.get(i).getYear(); 
			objects[i][2] = subList.get(i).getName();
			objects[i][3] = subList.get(i).getNote();
			objects[i][4] = subList.get(i).getCost();
		}
		
		return objects;
	}
	
	private String[] tableHeader = { "ID", "Ngày", "Sự kiện", "Ghi chú", "Chi phí" };
	
	/**
	 * 
	 */
	
	private void refreshTable() {
		table.setModel(new DefaultTableModel(getTableData(), tableHeader));
		table.setColumnSelectionAllowed(true);
		scrollPane.setViewportView(table);
	}
	/**
	 * 
	 */
	private class DeleteActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int id = (int)table.getValueAt(table.getSelectedRow(), 0);
			eventService.deleteEvent(eventService.getEvent(id));
			refreshTable();			
		}
		
	}
	
	/**
	 * 
	 */
	
	
	/**
	 * 
	 * 
	 */
	private List<String> getCbxListYear() {
		List<String> reList = new ArrayList<>();
		List<Integer> allYear = eventService.getAllYear();
		for (Integer integer : allYear) {
			reList.add(Integer.toString(integer));
		}
		return reList;
	}
	/**
	 * 
	 */

	DefaultComboBoxModel<String> cbxYearModel = new DefaultComboBoxModel(getCbxListYear().toArray());
	DefaultComboBoxModel<String> cbxMonthModel = new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rdbtnAll = new JRadioButton("Tất cả");
		buttonGroup.add(rdbtnAll);
		rdbtnAll.setBounds(6, 7, 109, 23);
		rdbtnAll.setSelected(true);
		contentPane.add(rdbtnAll);
		
		JRadioButton rdbtnOption = new JRadioButton("Tùy chọn");
		buttonGroup.add(rdbtnOption);
		rdbtnOption.setBounds(131, 7, 109, 23);
		rdbtnOption.setSelected(false);
		contentPane.add(rdbtnOption);
		
		JLabel lblYear = new JLabel("Năm");
		lblYear.setBounds(246, 11, 46, 14);
		contentPane.add(lblYear);
		
		
		cbxYear.setModel(cbxYearModel);
		cbxYear.setBounds(305, 8, 56, 20);
		contentPane.add(cbxYear);
		
		JLabel lblMonth = new JLabel("Tháng");
		lblMonth.setBounds(444, 11, 46, 14);
		contentPane.add(lblMonth);
		
		
		cbxMonth.setModel(cbxMonthModel);
		cbxMonth.setBounds(500, 8, 69, 20);
		contentPane.add(cbxMonth);
		
		scrollPane.setBounds(6, 37, 483, 272);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(getTableData(), tableHeader));
		scrollPane.setViewportView(table);
		
		JButton btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new DeleteActionListener());
		btnDelete.setBounds(528, 55, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnEdit = new JButton("Sửa");
		btnEdit.setBounds(528, 89, 89, 23);
		contentPane.add(btnEdit);
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAdd.setBounds(528, 123, 89, 23);
		contentPane.add(btnAdd);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(6, 334, 249, 20);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(265, 333, 89, 23);
		contentPane.add(btnSearch);
		
		JLabel lblCost = new JLabel("Tổng cộng");
		lblCost.setBounds(500, 157, 69, 14);
		contentPane.add(lblCost);
		
		txtCost = new JTextField();
		txtCost.setEditable(false);
		txtCost.setBounds(598, 157, 86, 20);
		contentPane.add(txtCost);
		txtCost.setColumns(10);
		
		JLabel lblPaying = new JLabel("Chi");
		lblPaying.setBounds(500, 196, 69, 14);
		contentPane.add(lblPaying);
		
		txtPaying = new JTextField();
		txtPaying.setEditable(false);
		txtPaying.setBounds(598, 193, 86, 20);
		contentPane.add(txtPaying);
		txtPaying.setColumns(10);
		
		JLabel lblPaid = new JLabel("Thu");
		lblPaid.setBounds(500, 234, 69, 14);
		contentPane.add(lblPaid);
		
		txtPaid = new JTextField();
		txtPaid.setEditable(false);
		txtPaid.setBounds(598, 231, 86, 20);
		contentPane.add(txtPaid);
		txtPaid.setColumns(10);
	}
}
