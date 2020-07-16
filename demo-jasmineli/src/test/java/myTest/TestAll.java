package myTest;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestAll {

	@Test
	public void test() throws ParseException {

		ArrayList<String> a = new ArrayList<>(10);
		for (int i = 0; i < 10; i ++) {
			a.add(i + "");
		}

		ArrayList<String> b = (ArrayList<String>) a.subList(0, 5);

		System.out.println(b.get(0));



		/*  JSON parentsJson=new JSONObject();
	    JSONObject transitCityOneJson = new JSONObject();
        transitCityOneJson.put("parentName", "zhongguo人");
        System.out.println("是不是工作日："+getWorkDayFlag ("2018-05-07"));*/

		//设置转换的日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		//开始时间
		Date startDate = sdf.parse("2020-01-01");
		//结束时间
		Date endDate = sdf.parse("2020-02-26");

		//得到相差的天数 betweenDate
		long betweenDate = (endDate.getTime() - startDate.getTime())/(60*60*24*1000);

		//打印控制台相差的天数
		System.out.println(betweenDate);
		double s=betweenDate/365;
		System.out.println("ss:"+(s*5)+",s:"+s);

		String  endDates="20200204";

		String month=endDates.substring(4,6);
		if(month.substring(0,1).equals("0")){
			month=month.substring(1,2);
			System.out.println("month:"+month);
		}
		String day=endDates.substring(6,endDates.length());
		if(day.substring(0,1).equals("0")){
			day=day.substring(1,2);
		}
		System.out.println(month+"."+day);





    }




































}
