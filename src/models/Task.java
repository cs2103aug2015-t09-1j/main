/**
 *
 */
package models;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Dalton
 *
 */
public abstract class Task {
	private static AtomicInteger nextId = new AtomicInteger();
	protected final int taskID;
	protected String taskDesc;
	protected boolean isDone;

	public Task(String taskDesc, boolean isDone) {
		this.taskID = nextId.incrementAndGet();
		this.taskDesc = taskDesc;
		this.isDone = isDone;
	}

	public int getTaskID() {
		return taskID;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
}