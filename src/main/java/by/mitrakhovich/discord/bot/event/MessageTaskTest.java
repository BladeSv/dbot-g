package by.mitrakhovich.discord.bot.event;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageTaskTest extends ListenerAdapter {

	private Object msgs;

	public MessageTaskTest(Object msgs) {
		super();
		this.msgs = msgs;
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		String name = event.getMember().getEffectiveName();
		System.out.println(name);

		if (!(name.equalsIgnoreCase("cbot"))) {
			String msg = event.getMessage().getContentRaw();

			if (msg.equals("Hi")) {
				event.getChannel().sendMessage("Hello " + name + " message is: " + msgs).queue();

			}

		}

	}

	public Object getMsg() {
		return msgs;
	}

	public void setMsg(Object msgs) {
		this.msgs = msgs;
	}

}
