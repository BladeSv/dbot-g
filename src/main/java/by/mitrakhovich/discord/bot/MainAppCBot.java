package by.mitrakhovich.discord.bot;

import javax.security.auth.login.LoginException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAppCBot {

	public static void main(String[] args) throws LoginException {

		new AnnotationConfigApplicationContext("by.mitrakhovich.discord.bot.config");

	}
}
