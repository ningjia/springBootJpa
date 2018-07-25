package com.example.springBootJpa.springBootJpa;

import com.example.springBootJpa.domain.User;
import com.example.springBootJpa.service.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJpaApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void contextLoads() {
		// 创建10条记录
		userRepository.save(new User("AAA", "10"));
		userRepository.save(new User("BBB", "20"));

		// 测试findByName, 查询姓名为AAA的User
		Assert.assertEquals("10", userRepository.findByName("AAA").getPassword());

		// 测试findUser, 查询姓名为BBB的User
		Assert.assertEquals("20", userRepository.findUser("BBB").getPassword());

		// 测试删除姓名为AAA和BBB的User
		userRepository.delete(userRepository.findByName("AAA"));
		userRepository.delete(userRepository.findUser("BBB"));

	}

}
