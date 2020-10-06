package com.ljb.util;

import java.util.Date;
import java.util.List;

/**
 * ʵ�ù�����
 * @author llq
 *
 */
public class StringUtil {
	
	/**
	 * ��������list����ָ���ķָ����ָ���ַ�������
	 * @param list
	 * @param split
	 * @return
	 */
	public static String joinString(List<Long> list,String split){
		String ret = "";
		for(Long l:list){
			ret += l + split;
		}
		if(!"".equals(ret)){
			ret = ret.substring(0,ret.length() - split.length());
		}
		return ret;
	}
	
	
	public static String generateSn(String prefix,String suffix){
		return prefix + new Date().getTime() + suffix;
	}
}
