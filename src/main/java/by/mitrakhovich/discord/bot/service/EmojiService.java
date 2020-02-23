package by.mitrakhovich.discord.bot.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;

@Component
public class EmojiService extends AbstractService {

	public void addRoleByChooseEmojiInMessage(Map<String, String> emojiBase, String messageToken, MessageReactionAddEvent event, int maxClassCount) {
		if (emojiBase != null && messageToken != null && event != null && maxClassCount > 0) {

			String CheckUserEmoji = event.getReaction().getReactionEmote().getName();

			if (event.getMessageId().equals(messageToken) && emojiBase.containsKey(CheckUserEmoji)) {

				Member member = event.getMember();

				if ((!member.getRoles().stream().anyMatch(e -> emojiBase.containsValue(e.getId()))) || (countEmojiEqualRole(event, emojiBase) < maxClassCount)) {

					member.getGuild().addRoleToMember(member, member.getGuild().getRoleById(emojiBase.get(CheckUserEmoji))).queue();
					log.info("User " + member.getNickname() + " SUCSSES add role " + CheckUserEmoji);

				} else {

					event.getReaction().removeReaction(event.getUser()).queue();

				}

			}
		}

	}

	public void removeRoleByUnckeckEmojiInMessage(Map<String, String> emojiBase, String messageToken, MessageReactionRemoveEvent event) {
		if (emojiBase != null && messageToken != null && event != null) {

			String uncheckUserEmoji = event.getReaction().getReactionEmote().getName();
			if (event.getMessageId().equals(messageToken) && emojiBase.containsKey(uncheckUserEmoji)) {

				Member member = event.getMember();

				member.getGuild().removeRoleFromMember(member, member.getGuild().getRoleById(emojiBase.get(uncheckUserEmoji))).queue();
				log.info("User " + member.getNickname() + " SUCSSES remove role " + uncheckUserEmoji);
			}
		}
	}

	private int countEmojiEqualRole(MessageReactionAddEvent event, Map<String, String> map) {
		List<Role> memberRoles = event.getMember().getRoles();
		int count = 0;
		for (String er : map.values()) {
			for (Role r : memberRoles) {
				if (r.getId().equals(er)) {
					count++;
				}
			}

		}
		return count;
	}
}
