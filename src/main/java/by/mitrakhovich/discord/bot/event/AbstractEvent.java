package by.mitrakhovich.discord.bot.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.mitrakhovich.discord.bot.task.TaskPool;
import by.mitrakhovich.discord.bot.utils.JsonHelper;

@Component
public abstract class AbstractEvent implements InitializingBean {

	@Autowired
	private JsonHelper jsonHelper;

	@Autowired
	private TaskPool taskPool;

	protected static Logger log;

	static {
		log = LoggerFactory.getLogger("CBot.class");
	}

	public JsonHelper getJsonHelper() {
		return jsonHelper;
	}

	public void setJsonHelper(JsonHelper jsonHelper) {
		this.jsonHelper = jsonHelper;
	}

	public TaskPool getTaskPool() {
		return taskPool;
	}

	public void setTaskPool(TaskPool taskPool) {
		this.taskPool = taskPool;
	}

}
