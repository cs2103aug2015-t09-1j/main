package main.model.tableModels;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import main.model.EnumTypes.TASK_TYPE;
import main.model.taskModels.Event;
import main.model.taskModels.Task;
import main.storage.Storage;
import main.ui.MainGUI;

/**
 * @@author Dalton
 *
 */
@SuppressWarnings("serial")
public class EventsTableModel extends AbstractTableModel {
	private static EventsTableModel etm = null;
	private static MainGUI mainGUI = null;
	private List<Task> events = null;
	private final String[] columnNames = {"ID", "Start Date (2)", "End Date (3)", "Task Description (4)", "Done"};
	private final Class<?>[] columnTypes = {Integer.class, Date.class, Date.class, String.class, Boolean.class};

	private EventsTableModel() {
		super();
		events = new ArrayList<Task>();
	}

	public static EventsTableModel getInstance() {
		if (etm == null) {
			etm = new EventsTableModel();
		}
		return etm;
	}

	public void setUIInstance(MainGUI ui) {
		assert ui != null;
		mainGUI = ui;
	}

	public void setTasks(List<Task> tasks) {
		assert tasks != null;
		events = tasks;
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
	    return events.size();
    }

	public boolean isCellEditable(int row, int col) {
        switch (col) {
	    	case 1:
	    		return true;
	    	case 2:
	    		return true;
        	case 3:
        		return true;
        	case 4:
        		return true;
        	default:
        		return false;
        }
    }

	public void setValueAt(Object value, int row, int col) {
		Event evt = (Event)events.get(row);
		String simulatedCommand = "update " + evt.getTaskID() + " " + (col + 1) + " ";
		String updatedValue = "";
		String originalValue = "";
		switch (col) {
			case 1:
				originalValue += evt.getFromDate();
				updatedValue += value;
				break;
			case 2:
				originalValue += evt.getToDate();
				updatedValue += value;
				break;
			case 3:
				originalValue += evt.getTaskDesc();
				updatedValue += value;
				break;
			case 4:
				originalValue += value;
				simulatedCommand = ((Boolean)value ?  "done" : "undone") + " " + evt.getTaskID();
				break;
			default:
				// impossible case
		}

		if (!updatedValue.trim().equals(originalValue)) {
			mainGUI.sendUserInput(simulatedCommand + updatedValue);
		}
    }

	public Object getValueAt(int row, int col) {
		Event evt = (Event)events.get(row);
		switch (col) {
			case 0:
				return evt.getTaskID();
			case 1:
				return evt.getFromDate();
			case 2:
				return evt.getToDate();
			case 3:
				return evt.getTaskDesc();
			case 4:
				return evt.isDone();
			default:
				// impossible case
		}

		return new String();
	}
}