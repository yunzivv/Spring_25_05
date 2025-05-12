package util;

import java.lang.reflect.Array;
import java.util.Map;

import com.example.demo.vo.Member;

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
	
	public static String f(String str, Object...args){ // 가변인자 활용
		
		return String.format(str, args);
	}

	public static String isReplace(String resultCode, String msg, String replaceUri) {
		
		if(resultCode == null) resultCode = "";
		if(msg == null) msg = "";
		if(replaceUri == null) msg = "/";
		
		String resultMsg = resultCode + " : " + msg;
		
		return Ut.f("""
				<script>
					let resultMsg = '%s'.trim();

					if(resultMsg.length > 0){
						alert(resultMsg);
					}

					location.replace('%s');
				</script>
				""", resultMsg, replaceUri);
	}

	public static String isHistoryBack(String resultCode, String msg) {
		
		String resultMsg = resultCode + " : " + msg;
		
		return Ut.f("""
				<script>
					let resultMsg = '%s'.trim();

					if(resultMsg.length > 0){
						alert(resultMsg);
					}

					history.back();
				</script>
				""", resultMsg);
	}

}
