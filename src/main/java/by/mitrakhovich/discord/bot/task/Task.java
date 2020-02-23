package by.mitrakhovich.discord.bot.task;

import net.dv8tion.jda.api.events.GenericEvent;

public interface Task<T extends GenericEvent> {

	void execute(T event);
}
