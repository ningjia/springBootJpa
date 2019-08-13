package com.example.springBootJpa;

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
		testSingleTable();
	}

	/**
	 * 测试单表的增删查改操作
	 */
	private void testSingleTable() {
		/* 测试单表插入操作 */
		//创建记录
		User userA = userRepository.save(new User("AAA", "10"));
		User userB = userRepository.save(new User("BBB", "20"));
		// 测试findByName, 查询姓名为AAA的User
		Assert.assertEquals(userA, userRepository.findByName("AAA"));
		// 测试findUser, 查询姓名为BBB的User
		Assert.assertEquals("User(id=2, name=BBB, password=20)", userRepository.findUser("BBB").toString());
		//测试插入的记录数
		long count = userRepository.count();
		Assert.assertEquals(2, count);

		/* 测试单表更新操作 */
		//更新记录
		userA.setPassword("1010");
		userRepository.save(userA);
		//测试更新结果
		Assert.assertEquals("1010", userRepository.findByName("AAA").getPassword());

		/* 测试单表删除操作 */
		//删除记录
		userRepository.delete(userRepository.findByName("AAA"));
		userRepository.delete(userRepository.findUser("BBB"));
		//测试删除后的记录数
		count = userRepository.count();
		Assert.assertEquals(0, count);
	}


}
