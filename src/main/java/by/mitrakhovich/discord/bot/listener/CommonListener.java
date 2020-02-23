package by.mitrakhovich.discord.bot.listener;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.mitrakhovich.discord.bot.task.TaskPool;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Component
public class CommonListener extends ListenerAdapter implements InitializingBean {
	@Autowired
	private TaskPool taskPool;

	@Autowired
	private JDA jda;

	@Override
	public void afterPropertiesSet() throws Exception {
		jda.addEventListener(this);
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {

		taskPool.doTasks(event);

	}

	@Override
	public void onMessageReactionAdd(MessageReactionAddEvent event) {

		taskPool.doTasks(event);
	}

	@Override
	public void onMessageReactionRemove(MessageReactionRemoveEvent event) {

		taskPool.doTasks(event);
	}

}
