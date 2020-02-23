package by.mitrakhovich.discord.bot.event;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import by.mitrakhovich.discord.bot.service.EmojiService;
import by.mitrakhovich.discord.bot.task.Task;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

@Component
@PropertySource("classpath:time_eaters.properties")
public class TimeEatersAddClassByChooseEmoji extends AbstractEvent implements Task<MessageReactionAddEvent> {

	@Value("${time.eat.emoji.class}")
	private String emojiClassPath;

	@Value("${time.eat.emoji.spec}")
	private String emojiSpecPath;

	@Value("${time.eat.message.emoji.class.id}")
	private String messageToken;

	@Value("${time.eat.class.count.max:1}")
	private int maxClassCount;

	@Value("${time.eat.spec.count.max:3}")
	private int maxSpecCount;

	@Autowired
	private EmojiService emojiService;

	private static final String EVENT_TYPE = "MessageReactionAddEvent";

	@Override
	public void execute(MessageReactionAddEvent event) {

		Map<String, String> mapClass = getJsonHelper().readMapFromFile(emojiClassPath);

		Map<String, String> mapSpec = getJsonHelper().readMapFromFile(emojiSpecPath);

		emojiService.addRoleByChooseEmojiInMessage(mapClass, messageToken, event, maxClassCount);

		emojiService.addRoleByChooseEmojiInMessage(mapSpec, messageToken, event, maxSpecCount);

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		getTaskPool().addTask(EVENT_TYPE, this);
	}

}
