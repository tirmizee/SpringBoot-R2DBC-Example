package com.tirmizee.utils;

import java.util.WeakHashMap;

public final class CommonUtils {
	
	public static WeakHashMap<String, String> splitToMap(final String text, String reg1, String reg2) {
		WeakHashMap<String, String> map = new WeakHashMap<>();
		String[] texts = text.split(reg1);
		for (String txt : texts) {
			String[] couple = txt.split(reg2);
			map.put(couple[0], couple[1]);
		}
		return map;
	}
	
	public static Integer[] asArray(Integer...objects) {
		return objects;
	}

}
