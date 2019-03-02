package edu.ncsu.csc216.wolf_results.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.time.LocalDate;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;

import edu.ncsu.csc216.wolf_results.manager.WolfResultsManager;
import edu.ncsu.csc216.wolf_results.race_results.IndividualResult;
import edu.ncsu.csc216.wolf_results.race_results.Race;
import edu.ncsu.csc216.wolf_results.util.RaceTime;

/**
 * Wolf Results GUI.
 * 
 * @author Abigail Ormond
 * @author Varsha Gopal
 */
public class WolfResultsGUI extends JFrame implements ActionListener, Observer {

	/** ID number used for object serialization. */
	private static final long serialVersionUID = 1L;
	/** Title for top of GUI. */
	private static final String APP_TITLE = "Wolf Results";
	/** Text for the File Menu. */
	private static final String FILE_MENU_TITLE = "File";
	/** Text for the New Issue XML menu item. */
	private static final String NEW_FILE_TITLE = "New";
	/** Text for the Load Issue XML menu item. */
	private static final String LOAD_FILE_TITLE = "Load";
	/** Text for the Save menu item. */
	private static final String SAVE_FILE_TITLE = "Save";
	/** Text for the Quit menu item. */
	private static final String QUIT_TITLE = "Quit";
	/** Menu bar for the GUI that contains Menus. */
	private JMenuBar menuBar;
	/** Menu for the GUI. */
	private JMenu menu;
	/** Menu item for creating a new list of Races. */
	private JMenuItem itemNewFile;
	/** Menu item for loading a file. */
	private JMenuItem itemLoadFile;
	/** Menu item for saving the list to a file. */
	private JMenuItem itemSaveFile;
	/** Menu item for quitting the program. */
	private JMenuItem itemQuit;

	// filtering panel
	/** field for age min text box */
	private JLabel ageMin;
	/** Text field for numerator 1 */
	private JTextField ageMinText;
	/** Label for denominator 1 */
	private JLabel ageMax;
	/** Text field for denominator 1 */
	private JTextField ageMaxText;
	/** Label for numerator 2 */
	private JLabel paceMin;
	/** Text field for numerator 2 */
	private JTextField paceMinText;
	/** Label for denominator 2 */
	private JLabel paceMax;
	/** Text field for denominator 2 */
	private JTextField paceMaxText;
	/** Button for Subtraction */
	private JButton filterButton;

	// race results panel
	/** field for age min text box */
	private JLabel runnerName;
	/** Text field for numerator 1 */
	private JTextField runnerNameText;
	/** Label for denominator 1 */
	private JLabel runnerAge;
	/** Text field for denominator 1 */
	private JTextField runnerAgeText;
	/** Label for numerator 2 */
	private JLabel runnerTime;
	/** Text field for numerator 2 */
	private JTextField runnerTimeText;
	/** Button for Subtraction */
	private JButton addRunnerButton;

	// race panel
	/** field for age min text box */
	private JLabel raceName;
	/** Text field for numerator 1 */
	private JTextField raceNameText;
	/** Label for denominator 1 */
	private JLabel raceDistance;
	/** Text field for denominator 1 */
	private JTextField raceDistanceText;
	/** Label for numerator 2 */
	private JLabel raceDate;
	/** Text field for numerator 2 */
	private JTextField raceDateText;
	/** Label for denominator 2 */
	private JLabel raceLocation;
	/** Text field for denominator 2 */
	private JTextField raceLocationText;

	private JTable tableResults;

	private JButton addRaceButton;
	private JButton removeRaceButton;
	private JButton editRaceButton;
	private JButton unselectRaceButton;

	private Race selectedRace;

	private DefaultListModel raceListModel;
	private JList raceListList;
	private int selectedRaceIndex;

	ListSelectionModel listSelectionModel;
	private ResultsTableModel tableModel;
	private JSplitPane splitPane;

	/**
	 * Constructs the GUI.
	 */
	public WolfResultsGUI() {
		super();

		// Observe Manager
		WolfResultsManager.getInstance().addObserver(this);

		// Set up general GUI info
		setSize(1260, 600);
		setLocation(50, 50);
		setTitle(APP_TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUpMenuBar();

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				doExit();
			}

		});

		initializeGUI();

		// Set the GUI visible
		setVisible(true);
	}

	/**
	 * Initializes GUI
	 */
	private void initializeGUI() {

		Container racesContainer = this.getContentPane();
		JPanel racesPanel = new JPanel();
		racesPanel.setLayout(new BoxLayout(racesPanel, BoxLayout.PAGE_AXIS));
		JPanel racesButtonsPanel = new JPanel();
		racesButtonsPanel.setLayout(new BoxLayout(racesButtonsPanel, BoxLayout.Y_AXIS));
		JPanel racesListArea = new JPanel();
		JPanel racesListPanel = new JPanel();
		racesListPanel.setBorder(new TitledBorder("Races"));
		racesListPanel.setPreferredSize(new Dimension(600, 150));
		racesListPanel.add(racesButtonsPanel);
		racesListPanel.add(racesListArea);

		JPanel raceDetailsPanel = new JPanel();
		raceDetailsPanel.setBorder(new TitledBorder("Race Details"));
		raceDetailsPanel.setPreferredSize(new Dimension(600, 150));

		JPanel raceDetail = new JPanel();
		raceDetail.setLayout(new BoxLayout(raceDetail, BoxLayout.Y_AXIS));

		JPanel raceText = new JPanel();
		raceText.setLayout(new BoxLayout(raceText, BoxLayout.Y_AXIS));

		raceDetailsPanel.add(raceDetail);
		raceDetailsPanel.add(raceText);

		racesPanel.add(racesListPanel);
		racesPanel.add(raceDetailsPanel);

		raceName = new JLabel("Race Name");
		raceDetail.add(raceName);
		raceNameText = new JTextField(35);
		raceText.add(raceNameText);

		raceDistance = new JLabel("Race Distance");
		raceDetail.add(raceDistance);
		raceDistanceText = new JTextField(35);
		raceText.add(raceDistanceText);

		raceDate = new JLabel("Race Date");
		raceDetail.add(raceDate);
		raceDateText = new JTextField(35);
		raceText.add(raceDateText);

		raceLocation = new JLabel("Race Location");
		raceDetail.add(raceLocation);
		raceLocationText = new JTextField(35);
		raceText.add(raceLocationText);

		addRaceButton = new JButton("Add Race");
		racesButtonsPanel.add(addRaceButton);
		addRaceButton.addActionListener(this);

		removeRaceButton = new JButton("Remove Race");
		racesButtonsPanel.add(removeRaceButton);
		removeRaceButton.addActionListener(this);

		editRaceButton = new JButton("Edit Race");
		racesButtonsPanel.add(editRaceButton);
		editRaceButton.addActionListener(this);

		unselectRaceButton = new JButton("Unselect Race");
		racesButtonsPanel.add(unselectRaceButton);
		unselectRaceButton.addActionListener(this);

		// set up list of races
		raceListModel = new DefaultListModel();
		raceListList = new JList(raceListModel);
		racesListArea.add(raceListList);
		raceListList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		raceListList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		raceListList.setVisibleRowCount(-1);
		raceListList.setPreferredSize(new Dimension(450, 100));
		raceListList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent le) {
				selectedRaceIndex = raceListList.getSelectedIndex();
				if (selectedRaceIndex != -1) {
					// System.out.println("should add something to list - being clicked");
					updateTableData(selectedRaceIndex);
					raceNameText.setText(
							"" + WolfResultsManager.getInstance().getRaceList().getRace(selectedRaceIndex).getName());
					raceDateText.setText(
							"" + WolfResultsManager.getInstance().getRaceList().getRace(selectedRaceIndex).getDate());
					raceLocationText.setText(
							WolfResultsManager.getInstance().getRaceList().getRace(selectedRaceIndex).getLocation());
					raceDistanceText.setText(""
							+ WolfResultsManager.getInstance().getRaceList().getRace(selectedRaceIndex).getDistance());

				}
			}
		});

		// filtering container
		JPanel filterPanel = new JPanel();
		filterPanel.setBorder(new TitledBorder("Filter Results"));
		filterPanel.setPreferredSize(new Dimension(600, 300));

		JPanel filterDetail = new JPanel();
		filterDetail.setLayout(new BoxLayout(filterDetail, BoxLayout.Y_AXIS));

		JPanel filterText = new JPanel();
		filterText.setLayout(new BoxLayout(filterText, BoxLayout.Y_AXIS));

		filterPanel.add(filterDetail);
		filterPanel.add(filterText);

		ageMin = new JLabel("Age Min");
		filterDetail.add(ageMin);
		ageMinText = new JTextField(15);
		filterText.add(ageMinText);

		ageMax = new JLabel("Age Max");
		filterDetail.add(ageMax);
		ageMaxText = new JTextField(15);
		filterText.add(ageMaxText);

		paceMin = new JLabel("Pace Min");
		filterDetail.add(paceMin);
		paceMinText = new JTextField(15);
		filterText.add(paceMinText);

		paceMax = new JLabel("Pace Max");
		filterDetail.add(paceMax);
		paceMaxText = new JTextField(15);
		filterText.add(paceMaxText);

		filterButton = new JButton("Filter");
		filterPanel.add(filterButton);

		filterButton.addActionListener(this);

		JPanel raceResultPanel = new JPanel();
		raceResultPanel.setBorder(new TitledBorder("Race Results"));
		raceResultPanel.setPreferredSize(new Dimension(600, 600));

		JPanel raceResultDetail = new JPanel();
		raceResultDetail.setLayout(new BoxLayout(raceResultDetail, BoxLayout.Y_AXIS));

		JPanel raceResultText = new JPanel();
		raceResultText.setLayout(new BoxLayout(raceResultText, BoxLayout.Y_AXIS));

		raceResultPanel.add(raceResultDetail);
		raceResultPanel.add(raceResultText);

		runnerName = new JLabel("Runner Name");
		raceResultDetail.add(runnerName);
		runnerNameText = new JTextField(20);
		raceResultText.add(runnerNameText);

		runnerAge = new JLabel("Runner Age");
		raceResultDetail.add(runnerAge);
		runnerAgeText = new JTextField(20);
		raceResultText.add(runnerAgeText);

		runnerTime = new JLabel("Runner Time");
		raceResultDetail.add(runnerTime);
		runnerTimeText = new JTextField(20);
		raceResultText.add(runnerTimeText);

		addRunnerButton = new JButton("Add Runner");
		raceResultPanel.add(addRunnerButton);
		addRunnerButton.addActionListener(this);

		tableModel = new ResultsTableModel();
		tableResults = new JTable(tableModel);
		tableResults.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableResults.setPreferredScrollableViewportSize(new Dimension(600, 250));
		tableResults.setFillsViewportHeight(true);
		tableResults.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableResults.getColumnModel().getColumn(0).setPreferredWidth(150);
		tableResults.getColumnModel().getColumn(1).setPreferredWidth(150);

		tableResults.getColumnModel().getColumn(2).setPreferredWidth(150);

		tableResults.getColumnModel().getColumn(3).setPreferredWidth(150);
		JScrollPane tableScroll = new JScrollPane(tableResults);
		raceResultPanel.add(tableScroll, BorderLayout.EAST);

		JPanel leftPanel = new JPanel();
		leftPanel.add(racesPanel, BorderLayout.NORTH);
		leftPanel.add(filterPanel, BorderLayout.SOUTH);

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, raceResultPanel);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(600);
		Dimension minimumSize = new Dimension(600, 300);
		leftPanel.setMinimumSize(minimumSize);
		raceResultPanel.setMinimumSize(minimumSize);
		racesContainer.add(splitPane);

	}

	/**
	 * Makes the GUI Menu bar that contains options for loading a file containing
	 * issues or for quitting the application.
	 */
	private void setUpMenuBar() {
		// Construct Menu items
		menuBar = new JMenuBar();
		menu = new JMenu(FILE_MENU_TITLE);
		itemNewFile = new JMenuItem(NEW_FILE_TITLE);
		itemLoadFile = new JMenuItem(LOAD_FILE_TITLE);
		itemSaveFile = new JMenuItem(SAVE_FILE_TITLE);
		itemQuit = new JMenuItem(QUIT_TITLE);
		itemNewFile.addActionListener(this);
		itemLoadFile.addActionListener(this);
		itemSaveFile.addActionListener(this);
		itemQuit.addActionListener(this);

		// Start with save button disabled
		itemSaveFile.setEnabled(false);

		// Build Menu and add to GUI
		menu.add(itemNewFile);
		menu.add(itemLoadFile);
		menu.add(itemSaveFile);
		menu.add(itemQuit);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
	}

	/**
	 * Exits the GUI
	 */
	private void doExit() {
		if (WolfResultsManager.getInstance().isChanged()) {
			doSaveFile();
		}

		if (!WolfResultsManager.getInstance().isChanged()) {
			System.exit(NORMAL);
		} else { // Did NOT save when prompted to save
			JOptionPane.showMessageDialog(this,
					"Race Results changes have not been saved. " + "Your changes will not be saved.", "Saving Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Saves GUI to file
	 */
	private void doSaveFile() {
		try {
			WolfResultsManager instance = WolfResultsManager.getInstance();
			JFileChooser chooser = new JFileChooser("./");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Race Results files (md)", "md");
			chooser.setFileFilter(filter);
			chooser.setMultiSelectionEnabled(false);
			if (instance.getFilename() != null) {
				chooser.setSelectedFile(new File(instance.getFilename()));
			}
			int returnVal = chooser.showSaveDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile().getAbsolutePath();
				if (chooser.getSelectedFile().getName().trim().equals("")
						|| !chooser.getSelectedFile().getName().endsWith(".md")) {
					throw new IllegalArgumentException();
				}
				instance.setFilename(filename);
				instance.saveFile(filename);
			}
			itemLoadFile.setEnabled(true);
			itemNewFile.setEnabled(true);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, "File not saved.", "Saving Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Clear and updates the list of races
	 */
	private void setRaceList() {
//		raceListModel.addElement("Happy Birthday");
//		raceListModel.addElement("To meee");
		raceListModel.removeAllElements();
		String[] races = new String[WolfResultsManager.getInstance().getRaceList().size()];
		for (int i = 0; i < races.length; i++) {
			races[i] = WolfResultsManager.getInstance().getRaceList().getRace(i).toString();
			raceListModel.addElement(races[i]);
		}

	}

	/**
	 * Updates the table of IndividualResults
	 * 
	 * @param index of race to update
	 */
	public void updateTableData(int index) {
		tableModel.updateData(index);
	}

	/**
	 * Loads race results from file
	 */
	private void doLoadFile() {
		try {
			WolfResultsManager instance = WolfResultsManager.getInstance();
			JFileChooser chooser = new JFileChooser("./");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Race Results files (md)", "md");
			chooser.setFileFilter(filter);
			chooser.setMultiSelectionEnabled(false);
			int returnVal = chooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				instance.loadFile(chooser.getSelectedFile().getAbsolutePath());
				setRaceList();
			}

			itemLoadFile.setEnabled(false);
			itemNewFile.setEnabled(false);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, "Error opening file.", "Opening Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Overrides update method to repaint and validate
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof WolfResultsManager) {
			itemSaveFile.setEnabled(true);

			repaint();
			validate();
		}
	}

	/**
	 * Overrides actionPerformed method and performs different actions based on user
	 * input
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		WolfResultsManager instance = WolfResultsManager.getInstance();

		if (e.getSource() == itemNewFile) {
			doSaveFile();
			instance.newList();
		} else if (e.getSource() == itemLoadFile) {
			doLoadFile();
		} else if (e.getSource() == itemSaveFile) {
			doSaveFile();
		} else if (e.getSource() == itemQuit) {
			doExit();
		}

		if (e.getSource() == addRunnerButton) {
			if (selectedRaceIndex == -1) {
				JOptionPane.showMessageDialog(null, "No race selected.");
			} else {
				try {
					String name = runnerNameText.getText();
					int age = Integer.parseInt(runnerAgeText.getText());
					String time = runnerTimeText.getText();
					RaceTime t = new RaceTime(time);
					runnerNameText.setText("");
					runnerAgeText.setText("");
					runnerTimeText.setText("");
					selectedRace = WolfResultsManager.getInstance().getRaceList().getRace(selectedRaceIndex);
					IndividualResult ir = new IndividualResult(selectedRace, name, age, t);
					WolfResultsManager.getInstance().getRaceList().getRace(selectedRaceIndex).addIndividualResult(ir);
					updateTableData(selectedRaceIndex);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Invalid Runner");
				}
				this.repaint();
				this.validate();
			}
		}
		if (e.getSource() == addRaceButton) {
			try {
				String raceName2 = raceNameText.getText();
				double distance = Double.parseDouble(raceDistanceText.getText());
				String date = raceDateText.getText();
				String location = raceLocationText.getText();
				LocalDate ld = LocalDate.parse(date);
				Race r = new Race(raceName2, distance, ld, location);
				WolfResultsManager.getInstance().getRaceList().addRace(r);
				selectedRace = null;
				selectedRaceIndex = -1;
				setRaceList();
				raceNameText.setText("");
				raceDistanceText.setText("");

				raceDateText.setText("");
				raceLocationText.setText("");

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Invalid Race");
			}
		}

		if (e.getSource() == removeRaceButton) {
			if (selectedRaceIndex == -1) {
				JOptionPane.showMessageDialog(null, "No race selected.");
			} else {
				try {
					WolfResultsManager.getInstance().getRaceList().removeRace(selectedRaceIndex);
					selectedRace = null;
					selectedRaceIndex = -1;
					setRaceList();
					tableModel.clearTableData();
					raceNameText.setText("");
					raceDistanceText.setText("");
					raceDateText.setText("");
					raceLocationText.setText("");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Cannot remove race");
				}
			}
		}
		if (e.getSource() == editRaceButton) {
			if (selectedRaceIndex == -1) {
				JOptionPane.showMessageDialog(null, "No race selected.");
			} else {
				try {
					double distance = Double.parseDouble(raceDistanceText.getText());
					WolfResultsManager.getInstance().getRaceList().getRace(selectedRaceIndex).setDistance(distance);
					updateTableData(selectedRaceIndex);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Invalid Distance");
				}
			}
		}
		if (e.getSource() == unselectRaceButton) {
			raceListList.clearSelection();
			raceNameText.setText("");
			raceDistanceText.setText("");
			raceDateText.setText("");
			raceLocationText.setText("");
			tableModel.clearTableData();
			selectedRaceIndex = -1;
			selectedRace = null;
		}
		if (e.getSource() == filterButton) {
			if (selectedRaceIndex == -1) {
				JOptionPane.showMessageDialog(null, "No race selected.");
			}
			try {
				int min = Integer.parseInt(ageMinText.getText());
				int max = Integer.parseInt(ageMaxText.getText());
				String pace1 = paceMinText.getText();
				String pace2 = paceMaxText.getText();
				ageMinText.setText("");
				ageMaxText.setText("");
				paceMinText.setText("");
				paceMaxText.setText("");

				// System.out.println("getting pressed");
				tableModel.updateUsingFilter(selectedRaceIndex, min, max, pace1, pace2);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Invalid age or pace");
			}
		}
	}

	/**
	 * {@link ResultsTableModel} is the object underlying the {@link JTable} object
	 * that displays the list of Courses to the user. (Taken from Packscheduler)
	 * 
	 * @author Sarah Heckman
	 * @author Abigail Ormond
	 * @author Varsha Gopal
	 */
	private class ResultsTableModel extends AbstractTableModel {

		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String[] columnNames = { "Runner", "Age", "Time", "Pace" };
		/** Data stored in the table */
		private Object[][] data;

		/**
		 * Constructs the {@link ResultsTableModel} by requesting the latest information
		 * from the {@link RequirementTrackerModel}.
		 */
		public ResultsTableModel() {
			data = null;
		}

		/**
		 * Returns the number of columns in the table.
		 * 
		 * @return the number of columns in the table.
		 */
		public int getColumnCount() {
			return columnNames.length;
		}

		/**
		 * Returns the number of rows in the table.
		 * 
		 * @return the number of rows in the table.
		 */
		public int getRowCount() {
			if (data == null)
				return 0;
			return data.length;
		}

		/**
		 * Returns the column name at the given index.
		 * 
		 * @return the column name at the given column.
		 */
		public String getColumnName(int col) {
			return columnNames[col];
		}

		/**
		 * Returns the data at the given {row, col} index.
		 * 
		 * @return the data at the given location.
		 */
		public Object getValueAt(int row, int col) {
			if (data == null)
				return null;
			return data[row][col];
		}

		/**
		 * Sets the given value to the given {row, col} location.
		 * 
		 * @param value  Object to modify in the data.
		 * @param row    location to modify the data.
		 * @param column location to modify the data.
		 */
		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}

		/**
		 * Updates the given model with {@link Course} information from the
		 * {@link CourseCatalog}.
		 */
		public void updateData(int index) {

			data = WolfResultsManager.getInstance().getRaceList().getRace(index).getResults().getResultsAsArray();
			fireTableDataChanged();
		}

		/**
		 * Clears data in table of IndividualResults
		 */
		public void clearTableData() {
			data = null;
			fireTableDataChanged();
		}

		/**
		 * Updates the table of IndividualResults based on a filter
		 * 
		 * @param index   Index of race
		 * @param minAge  int minimum age of runner
		 * @param maxAge  int max age of runner
		 * @param minPace string minimum pace of runner
		 * @param maxPace string maximum pace of runner
		 */
		private void updateUsingFilter(int index, int minAge, int maxAge, String minPace, String maxPace) {
			data = WolfResultsManager.getInstance().getRaceList().getRace(index).getResults()
					.filter(minAge, maxAge, minPace, maxPace).getResultsAsArray();
			fireTableDataChanged();

		}
	}

	/**
	 * Starts the application
	 * 
	 * @param args command line args
	 */
	public static void main(String[] args) {
		new WolfResultsGUI();
	}
}
//}  WolfResult Void 
//     (Insert textbox a1)
//     {say command alpha}
