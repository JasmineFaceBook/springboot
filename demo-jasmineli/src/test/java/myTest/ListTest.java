package myTest;

import com.cn.jasmine.demo.entity.Student;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class ListTest {


    List getList(){
        List<Student> list=new ArrayList<>();
        Student source=new Student();
        source.setId("");
        source.setName("libignyao");
        source.setAge(1);
        Student source1=new Student();
        source1.setId("");
        source1.setName("libignyao1");
        source1.setAge(1);
        Student source11=new Student();
        source11.setId("");
        source11.setName("libignyao1");
        source11.setAge(1);
        Student source12=new Student();
        source12.setId("1234");
        source12.setName("libignyao2");
        source12.setAge(0);
        Student source13=new Student();
        source13.setId("1234");
        source13.setName("libignyao3");
        source13.setAge(0);
        Student source14=new Student();
        source14.setId("1234");
        source14.setName("libignyao4");
        source14.setAge(0);
        Student source15=new Student();
        source15.setId("1234");
        source15.setName("libignyao2");
        source15.setAge(0);
        list.add(source);
        list.add(source1);
        list.add(source12);
        list.add(source13);
        list.add(source14);
        list.add(source15);
        list.add(source11);
        return list;
    }

    @Test
    public void testLam(){
        //list对象中获取某个字段满足条件的List对象筛选
        List<Student> list=getList();
        Optional<Student> cartOptional = list.stream().filter(item -> item.getId().equals("123")&&
                item.getName().equals("libignyao2")
        ).findFirst();
        if (cartOptional.isPresent()) {
            // 存在
            Student cart =  cartOptional.get();
            System.out.println("name:"+cart.getName()+",size:");
        } else {
            // 不存在
        }
    }

    @Test
    public void test3(){
        //将List对象中某个值取出来生成新的List
        List<Student> list=getList();
        List<String> students=new ArrayList<>();
        list .forEach(n -> students.add(n.getName()));
        System.out.println("studentName:"+students.toString());
    }

    @Test
    public void test4(){
        //将list以一个字段做去重处理获取出来
        List<Student> list=getList();
        List<Student> list1 = removeDuplicateOrder(list);

        List<List<Student>> lists=new ArrayList<>();
        List<Student> nullList=new ArrayList<>();
        for(Student student:list1){
                //查询同组的数据
                List<Student> cartOptional = list.stream().filter(item -> item.getId().equals(student.getId())).collect(Collectors.toList());
                if (cartOptional.size() > 0) {
                    System.out.println("存在,size:" + cartOptional.toString());
                    if(null!=student.getId()&&!"".equals(student.getId())) {
                    lists.add(cartOptional);
                    }else{
                        nullList=cartOptional;
                    }
                } else {
                    System.out.println("不存在");
                }
        }
        System.out.println("list:"+lists.toString());
    }

    /**
     * 去重
     *
     * @param orderList
     * @return
     * @author jqlin
     */
    private static List<Student> removeDuplicateOrder(List<Student> orderList) {
        Set<Student> set = new TreeSet<Student>(new Comparator<Student>() {
            @Override
            public int compare(Student a, Student b) {
                // 字符串则按照asicc码升序排列
                return a.getId().compareTo(b.getId());
            }
        });
        set.addAll(orderList);
        return new ArrayList<Student>(set);
    }


    @Test
    public void test5(){
        List<Student> list=getList();
        List<Student> lst = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(o -> o.getId() + "#" + o.getAge()+"#"+o.getName()))),
                ArrayList::new));
        System.out.println("list:"+ lst.toString());
    }

}
