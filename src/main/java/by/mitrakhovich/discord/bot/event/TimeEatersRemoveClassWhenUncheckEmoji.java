package by.mitrakhovich.discord.bot.event;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import by.mitrakhovich.discord.bot.service.EmojiService;
import by.mitrakhovich.discord.bot.task.Task;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;

@Component
@PropertySource("classpath:time_eaters.properties")
public class TimeEatersRemoveClassWhenUncheckEmoji extends AbstractEvent implements Task<MessageReactionRemoveEvent> {

	@Value("${time.eat.emoji.class}")
	private String emojiClassPath;

	@Value("${time.eat.emoji.spec}")
	private String emojiSpecPath;

	@Value("${time.eat.message.emoji.class.id}")
	private String messageToken;

	@Autowired
	private EmojiService emojiServicel;

	private static final String EVENT_TYPE = "MessageReactionRemoveEvent";

	@Override
	public void afterPropertiesSet() throws Exception {
		getTaskPool().addTask(EVENT_TYPE, this);

	}

	@Override
	public void execute(MessageReactionRemoveEvent event) {

		Map<String, String> mapClass = getJsonHelper().readMapFromFile(emojiClassPath);

		Map<String, String> mapSpec = getJsonHelper().readMapFromFile(emojiSpecPath);

		Map<String, String> combMap = new HashMap<String, String>(mapClass);
		combMap.putAll(mapSpec);

		emojiServicel.removeRoleByUnckeckEmojiInMessage(combMap, messageToken, event);

	}

}
