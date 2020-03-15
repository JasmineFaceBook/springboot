package myTest;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestAll {

	@Test
	public void test() throws ParseException {

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

		Map map=new HashMap();



    }




































}
