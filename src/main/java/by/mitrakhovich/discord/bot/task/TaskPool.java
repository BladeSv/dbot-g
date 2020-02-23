package by.mitrakhovich.discord.bot.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.events.GenericEvent;

@Component
public class TaskPool {

	private Map<String, List<Task>> taskPool = new HashMap();

	public void addTask(String eventType, Task task) {
		if (eventType != null && task != null) {
			if (taskPool.containsKey(eventType)) {
				taskPool.get(eventType).add(task);

			}

			else {

				taskPool.put(eventType, Stream.of(task).collect(Collectors.toList()));

			}
		}
	}

	public void doTasks(GenericEvent event) {

		if (event != null) {
			System.out.println("название таки " + event.getClass().getSimpleName());
			if (taskPool.containsKey(event.getClass().getSimpleName())) {

				taskPool.get(event.getClass().getSimpleName()).forEach(e -> e.execute(event));
			}
		}
	}
}
