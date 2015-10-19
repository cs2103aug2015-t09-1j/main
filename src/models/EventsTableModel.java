/**
 *
 */
package models;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

import models.EnumTypes.TASK_TYPE;
import storage.Storage;

/**
 * @author Dalton
 *
 */
public class EventsTableModel extends AbstractTableModel {
	private static EventsTableModel etm = EventsTableModel.getInstance();
	private final String[] columnNames = { "ID", "Start Date", "End Date", "Task Description", "Done" };
	private final Class<?>[] columnTypes = { Integer.class, Date.class, Date.class, String.class, Boolean.class };
	private ArrayList<Event> events;

	private EventsTableModel() {
		super();
		this.events = (ArrayList)Storage.getInstance().getAllTask(EnumTypes.TASK_TYPE.EVENT);
	}

	public static EventsTableModel getInstance() {
		if (etm == null) {
			etm = new EventsTableModel();
		}
		return etm;
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
		switch (col) {
			case 1:
				evt.setFromDate((Date)value);
				break;
			case 2:
				evt.setToDate((Date)value);
				break;
			case 3:
				evt.setTaskDesc((String)value);
				break;
			case 4:
				evt.setDone((Boolean)value);
				break;
		}
		Storage.getInstance().saveTaskType(TASK_TYPE.EVENT);
    }

	public Object getValueAt(int row, int col) {
		Event evt = (Event)events.get(row);
		switch (col) {
			case 0:
				return evt.getTaskID();
			case 1:
				return evt.getFromDate();
			case 2:
				//return (evt.getFromDate() == evt.getToDate()) ? null : evt.getToDate();
				return evt.getToDate();
			case 3:
				return evt.getTaskDesc();
			case 4:
				return evt.isDone();
		}
		return new String();
	}
}