package test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import main.model.EnumTypes.TASK_TYPE;
import main.model.taskModels.Event;
import main.model.taskModels.Task;
import main.storage.Storage;

//@@author Xiang Jie
public class ModelTest {

	@Test
	public void testCloneTask() {
		List<Task> tasks = Storage.getInstance().getAllTask(TASK_TYPE.EVENT);
		Task event = tasks.get(0);
		Task eventClone = event.clone();
		Date d1 = ((Event)event).getFromDate(),
			 d2 = ((Event)eventClone).getFromDate();
		if (d1 == d2) {
			System.out.println("equal");
		} else {
			System.out.println("not equal");
		}
	}

	@Test
	public void testModels() {
		List<Task> tasks = Storage.getInstance().getAllTask(TASK_TYPE.EVENT);
		Task event = tasks.get(0);
		System.out.println(event.getType());
	}
}
