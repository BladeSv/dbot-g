package by.mitrakhovich.discord.bot.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {

	public HashMap<String, String> readMapFromFile(String sourse) {
		HashMap<String, String> map = null;
		File file = new File(this.getClass().getClassLoader().getResource(sourse).getFile());
		ObjectMapper mapper = new ObjectMapper();
		try {
			map = mapper.readValue(file, HashMap.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return map;

	}

}
