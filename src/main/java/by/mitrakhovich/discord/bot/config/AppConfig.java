package by.mitrakhovich.discord.bot.config;

import javax.security.auth.login.LoginException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import by.mitrakhovich.discord.bot.utils.JsonHelper;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

@Configuration
@ComponentScan("by.mitrakhovich.discord.bot")
public class AppConfig {

	private String botToken = "NjY5MjgxNzcxMjcwOTYzMjEw.XlLaQQ.F0sq9gVb_7UKKhSfc5VYCkUMLrs";

	@Bean
	public JDA getJda() throws LoginException {
		JDA jda = new JDABuilder(botToken).build();
		return jda;

	}

	@Bean
	public JsonHelper getJsonHelper() {
		return new JsonHelper();

	}

}
