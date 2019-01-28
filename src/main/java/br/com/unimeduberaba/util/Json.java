package br.com.unimeduberaba.util;

import java.lang.reflect.Modifier;
import java.time.LocalDateTime;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.unimeduberaba.util.json.GenericExclusionStrategy;
import br.com.unimeduberaba.util.json.LocalDateTimeSerializer;

public class Json {

	public static String toJson(Object objeto) {
		Gson gson = new GsonBuilder().setExclusionStrategies(new GenericExclusionStrategy())
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer()).create();
		return gson.toJson(objeto);
	}

	public static String toJsonTransient(Object objeto) {
		Gson gson = new GsonBuilder().setExclusionStrategies(new GenericExclusionStrategy())
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
				.excludeFieldsWithModifiers(Modifier.STATIC).create();
		return gson.toJson(objeto);
	}

	@SuppressWarnings({ "rawtypes" })
	public static String toJsonSerialization(Object objeto, Class serializada, Object adapter) {
		Gson gson = new GsonBuilder().setExclusionStrategies(new GenericExclusionStrategy())
				.registerTypeAdapter(serializada, adapter)
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer()).create();
		return gson.toJson(objeto);
	}

	@SuppressWarnings({ "rawtypes" })
	public static String toJsonSerializationWithoutDate(Object objeto, Class serializada, Object adapter) {
		Gson gson = new GsonBuilder().setExclusionStrategies(new GenericExclusionStrategy())
				.registerTypeAdapter(serializada, adapter).create();
		return gson.toJson(objeto);
	}

	public static String toJsonExclusionStrategy(Object objeto, ExclusionStrategy excludeStrings) {
		Gson gson = new GsonBuilder().setExclusionStrategies(excludeStrings).create();
		return gson.toJson(objeto);
	}

	@SuppressWarnings("rawtypes")
	public static String toJsonSerializationExclusionStrategy(Object objeto, Class serializada, Object adapter,
			ExclusionStrategy excludeStrings) {
		Gson gson = new GsonBuilder().registerTypeAdapter(serializada, adapter).setExclusionStrategies(excludeStrings)
				.create();
		return gson.toJson(objeto);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object fromJson(String json, Class classe) {
		Gson gson = new Gson();

		return gson.fromJson(json, classe);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object fromJsonDeserialization(String json, Class classe, Class serializada, Object adapter) {
		Gson gson = new GsonBuilder().registerTypeAdapter(serializada, adapter).create();

		return gson.fromJson(json, classe);
	}

}
