package main.storage;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import main.model.EnumTypes.TASK_TYPE;
import main.model.taskModels.Deadline;
import main.model.taskModels.Event;
import main.model.taskModels.Task;
import main.model.taskModels.Todo;

//@@author Hiep
public class DataParser {
	
	private static volatile Gson gson = null;
	
	public static Gson getGson() {
        if (gson == null) {
            synchronized (DataParser.class) {
                if (gson == null) {
                	gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
                }
            }
        }
        return gson;
	}
	
	/**
	 *
	 * @return Type of the task type "type"
	 */
	public static Type getListType(TASK_TYPE type) {
		switch (type) {
			case DEADLINE: return new TypeToken<List<Deadline>>() {}.getType();
			case EVENT: return new TypeToken<List<Event>>() {}.getType();
			case TODO: return new TypeToken<List<Todo>>() {}.getType();
			default: return null;
		}
	}
	/**
	 * 
	 * @param tasks
	 * @param type
	 * @return convert list tasks of type "type" into String of Json format 
	 */
	public static String serialize(List<Task> tasks, TASK_TYPE type) {
		Gson gson = getGson();
		Type listType = getListType(type);
		if (type == null) {
			return "[]";
		}
		return gson.toJson(tasks, listType);
	}
	
	/**
	 * 
	 * @param data
	 * @param type
	 * @return list of tasks from String "data" of Json format 
	 */
	public static List<Task> deserialize(String data, TASK_TYPE type) {
		Gson gson = getGson();
		Type listType = getListType(type);
		if (data.isEmpty()) data = "[]";
		return gson.fromJson(data, listType);
	}
}
