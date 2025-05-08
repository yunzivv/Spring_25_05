package util;

import java.lang.reflect.Array;
import java.util.Map;

public class Ut {
	
	public static boolean isIncorrectParams(String params) {
		
		return params == null || params.trim().length() == 0;
	}
	
	public static boolean isEmpty(Object obj) {
		
		if(obj == null) return true;
		if(obj instanceof String) return ((String)obj).trim().length() == 0;
		if(obj instanceof Map) return ((Map<?, ?>)obj).isEmpty();
		if(obj.getClass().isArray()) return Array.getLength(obj) == 0;
		
		return false;
	}
	
	public static Object f(String str, Object...args){ // 가변인자 활용
		
		return String.format(str, args);
	}

}
