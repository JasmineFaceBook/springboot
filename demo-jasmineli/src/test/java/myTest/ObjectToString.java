package myTest;

import com.cn.jasmine.demo.entity.Student;
import com.cn.jasmine.demo.utils.JsonUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ObjectToString {
    @Test
    public void test(){
        //list转字符串
        //list对象中获取某个字段满足条件的List对象筛选
        List<Student> list=new ArrayList<>();
        Student source=new Student();
        source.setId("123");
        source.setName("libignyao");
        Student source1=new Student();
        source1.setId("123");
        source1.setName("libignyao2");
        Student source12=new Student();
        source12.setId("1234");
        source12.setName("libignyao2");
        list.add(source);
        list.add(source1);
        list.add(source12);
        String ss=JsonUtil.listToJson(list);
       List<Student> sss= (List<Student>) JsonUtil.jsonToList(ss,Student.class);

        System.out.println("ssss:"+JsonUtil.listToJson(sss));
    }
}
