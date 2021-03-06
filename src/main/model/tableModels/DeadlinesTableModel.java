/*
 *
 */
package main.model.tableModels;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import main.model.taskModels.Deadline;
import main.model.taskModels.Task;
import main.ui.MainGUI;

/**
 * The Class DeadlinesTableModel.
 * The table model for the Deadlines Table.
 * Handles the user UI interactions of the JTable.
 *
 * @@author Dalton
 */
@SuppressWarnings("serial")
public class DeadlinesTableModel extends AbstractTableModel {
	private static DeadlinesTableModel dtm = null;
	private static MainGUI mainGUI = null;
	private List<Task> deadlines = null;
	private final String[] columnNames = { "ID", "Deadline (2)", "Task Description (3)", "Done" };
	private final Class<?>[] columnTypes = { Integer.class, Date.class, String.class, Boolean.class };


	/**
	 * Instantiates a new deadlines table model.
	 */
	private DeadlinesTableModel() {
		super();
		deadlines = new ArrayList<Task>();
	}

	/**
	 * Gets the single instance of DeadlinesTableModel.
	 *
	 * @return single instance of DeadlinesTableModel
	 */
	public static DeadlinesTableModel getInstance() {
		if (dtm == null) {
			dtm = new DeadlinesTableModel();
		}
		return dtm;
	}

	/**
	 * Sets the UI instance.
	 *
	 * @param ui	the new UI instance
	 */
	public void setUIInstance(MainGUI ui) {
		assert ui != null;
		mainGUI = ui;
	}

	/**
	 * Sets the tasks.
	 *
	 * @param tasks	the new tasks
	 */
	public void setTasks(List<Task> tasks) {
		assert tasks != null;
		deadlines = tasks;
	}

	public int getColumnCount() {
		return columnNames.length;
    }

	public String getColumnName(int col) {
		return columnNames[col];
	}

    public Class<?> getColumnClass(int col) {
		return columnTypes[col];
	}

	public int getRowCount() {
	    return deadlines.size();
    }

	public boolean isCellEditable(int row, int col) {
        switch (col) {
        	case 1:
        		return true;
        	case 2:
        		return true;
        	case 3:
        		return true;
            default:
                return false;
        }
    }

	public void setValueAt(Object value, int row, int col) {
		Deadline deadline = (Deadline)deadlines.get(row);
		String simulatedCommand = "update " + deadline.getTaskID() + " " + (col + 1) + " ";
		String updatedValue = "";
		String originalValue = "";
		switch (col) {
			case 1:
				originalValue += deadline.getDate();
				updatedValue += value;
				break;
			case 2:
				originalValue += deadline.getTaskDesc();
				updatedValue += value;
				break;
			case 3:
				originalValue += value;
				simulatedCommand = ((Boolean)value ?  "done" : "undone") + " " + deadline.getTaskID();
				break;
			default:
				// impossible case
				assert false : col;
		}

		// Sends a simulated command if the user updates from the table directly
		if (!updatedValue.trim().equals(originalValue)) {
			mainGUI.sendUserInput(simulatedCommand + updatedValue);
		}
    }

	public Object getValueAt(int row, int col) {
		Deadline deadline = (Deadline)deadlines.get(row);
		switch (col) {
			case 0:
				return deadline.getTaskID();
			case 1:
				return deadline.getDate();
			case 2:
				return deadline.getTaskDesc();
			case 3:
				return deadline.isDone();
			default:
				// impossible case
				assert false : col;
		}

		return new String();
	}


}