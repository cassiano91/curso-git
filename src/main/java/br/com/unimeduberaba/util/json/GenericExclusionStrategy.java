package br.com.unimeduberaba.util.json;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class GenericExclusionStrategy implements ExclusionStrategy {
	public boolean shouldSkipField(FieldAttributes f) {
		return (f.getName().equals("excluido") || f.getName().equals("__hashCodeCalc"));
	}

	public boolean shouldSkipClass(Class<?> clazz) {
		return false;
	}
}