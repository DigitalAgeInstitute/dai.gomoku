package dai.gomoku.client.swing;

import java.util.*;

public class Utilities {
	public static void containsIgnoreCase(String s, String a, List<String> l) {
    	int aHash = 0, sHash = 0;
		StringBuffer sb = new StringBuffer();
		for(String x : a.split("")) {
			aHash = x.hashCode();
			for(String y : s.split("")) {
				sHash = y.hashCode();
				if(sHash - aHash == 32) {
					x = x.toLowerCase();
				}
				else if(aHash - sHash == 32) {
					x = x.toUpperCase();
				}
				
			}
			sb.append(x);
		}
		if(s.contains(sb.toString())) {
			l.add(s);
		}
	}
}
