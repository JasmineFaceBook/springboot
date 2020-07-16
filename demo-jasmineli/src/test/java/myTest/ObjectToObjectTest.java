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
        Student source=new Student();
        source.setId("123");
        source.setName("libignyao");
        Teacher target=new Teacher();
        BeanUtils.copyProperties(source, target);
        System.out.println("taechar:"+target.getName());
    }

    @Test
    public void testLam(){

        List<Student> list=new ArrayList<>();
        Student source=new Student();
        source.setId("123");
        source.setName("libignyao");
        Student source1=new Student();
        source1.setId("1234");
        source1.setName("libignyao2");
        list.add(source);
        list.add(source1);
        Optional<Student> cartOptional = list.stream().filter(item -> item.getId().equals("123")).findFirst();
        if (cartOptional.isPresent()) {
            // 存在
            Student cart =  cartOptional.get();
            System.out.println("name:"+cart.getName());
        } else {
            // 不存在
        }
    }





}

