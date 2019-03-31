package com.school;

import com.school.repository.UserRepository;
import com.school.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

    @Autowired
    UserRepository userRepository;


    @Test
    public void contextLoads() {

    }

    @Test
    public void test() {
        List<UserVO> list = userRepository.findByUidIn(Arrays.asList(1, 2, 3));
        System.out.println(list.size());
//        service.set("test1","hhh");
//        System.out.println(service.get("test"));
//        service.setList("array2", Arrays.asList("1","2"));



    }

}
