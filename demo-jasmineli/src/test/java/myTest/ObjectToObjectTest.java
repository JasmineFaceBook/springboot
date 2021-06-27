package myTest;

import com.cn.jasmine.demo.entity.Student;
import com.cn.jasmine.demo.entity.Teacher;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ObjectToObjectTest {

    @Test
    public void test(){
        //相同字段对象互相赋值
        Student source=new Student();
        source.setId("123");
        source.setName("libignyao");
        Teacher target=new Teacher();
        BeanUtils.copyProperties(source, target);
        System.out.println("taechar:"+target.getName());
    }







}

