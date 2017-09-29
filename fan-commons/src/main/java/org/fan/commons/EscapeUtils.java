package org.fan.commons;

public class EscapeUtils {

	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	public static void main(String[] args) {
		String tmp = " ~!@#$%^&*()_+|\\=-,./?><;'][{}\"";
		System.out.println("testing escape : " + tmp);
		tmp = escape(tmp);
		System.out.println(tmp);
		System.out.println("testing unescape :" + tmp);
		System.out.println(unescape(tmp));

		tmp = "<>guide/matchnum/index.html?bt_code_old=&bt_code=&tab=tab1%22/%3e%3cscript%3ealert%28/xsstest/%29%3c%2fscript%3e&querynumberhelp.areaid=571&querynumberhelp.cityid=&querynumberhelp.number=&querynumberhelp.numberhead=180&querynumberhelp.productname=phy-man-0022&querynumberhelp.amount=50&systemname=zjwt&searchbutton=number&match_type=&iscontain_areacode=n%20%3e%3e%20127.0.0.1:4444/isa127.0.0.1:4444/isale/guide/matchnum/index.html?bt_code_old=&bt_code=&tab=tab1%22/%3e%3cscript%3ealert%28/xsstest/%29%3c/script%3e&querynumberhelp.areaid=571&querynumberhelp.cityid=&querynumberhelp.number=&querynumberhelp.numberhead=180&querynumberhelp.productname=phy-man-0022&querynumberhelp.amount=50&systemname=zjwt&searchbutton=number&match_type=&iscontain_areacode=n";
		System.out.println(unescape(tmp));
	}

}
