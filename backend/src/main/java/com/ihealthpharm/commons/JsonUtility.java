package com.ihealthpharm.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JsonUtility {

	private static Gson gson = null;
	static {
		gson = new GsonBuilder().serializeNulls().create();
	}

	public static String objectToJson(Object obj) {
		return gson.toJson(obj);
	}

	@SuppressWarnings("rawtypes")
	public static Map jsonToMap(String json) {
		java.lang.reflect.Type mapType = new TypeToken<Map<String,Object>>() {}.getType();
		return gson.fromJson(json, mapType);
	}

	@SuppressWarnings("rawtypes")
	public static List jsonToList(String json, Class clazz) {
		return gson.fromJson(json, TypeToken.getParameterized(ArrayList.class, clazz).getType());

	}

	public static Object jsonToObject(String json,Class clazz) { 
		return gson.fromJson(json, clazz);

	}
}
