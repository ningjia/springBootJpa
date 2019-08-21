package com.example.springBootJpa;

import com.example.springBootJpa.domain.*;
import com.example.springBootJpa.service.UserAddressRepository;
import com.example.springBootJpa.service.UserCardRepository;
import com.example.springBootJpa.service.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJpaApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserCardRepository userCardRepository;

	@Autowired
	private UserAddressRepository userAddressRepository;


	/**
	 * 测试单表的增删查改操作
	 */
	@Test
	public void testSingleTable() {
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


	/**
	 * 联合主键表的增删查改操作
	 * 采用@Embeddable来注解复合主键
	 */
	@Test
	public void testCompoundKeyByEmbeddable() {
		//创建记录
		UserCard userCardA = userCardRepository.save(new UserCard( new UserCardPK(10001l, "Card-1"), "A123456", "备注A"));
		UserCard userCardB = userCardRepository.save(new UserCard( new UserCardPK(10002l, "Card-2"), "B654321", "备注B"));
		//测试findById，查询(10001,Card-1)主键的数据
		UserCard userCardTmp = userCardRepository.findById(new UserCardPK(10001l, "Card-1")).get();
		Assert.assertEquals(userCardTmp.getCardNo(),"A123456");
		//更新数据
		userCardTmp.setRemark("a123321");
		userCardRepository.save(userCardTmp);
		//测试更新结果
		UserCard userCardTmp2 = userCardRepository.findById(new UserCardPK(10001l, "Card-1")).get();
		Assert.assertEquals(userCardTmp2.getRemark(),"a123321");
		//删除记录
		userCardRepository.deleteById(new UserCardPK(10001l, "Card-1"));
		UserCard userCardTmp3 = userCardRepository.findById(new UserCardPK(10002l, "Card-2")).get();
		userCardRepository.delete(userCardTmp3);
		//测试删除后的记录数
		long count = userCardRepository.count();
		Assert.assertEquals(0, count);
	}


	/**
	 * 联合主键表的增删查改操作
	 * 采用@IdClass来注解复合主键
	 */
	@Test
	public void testCompoundKeyByIdClass() {
		//创建记录
		UserAddress userAddressA = userAddressRepository.save(new UserAddress(201l, 10001l, "测试地址-1", 12345));
		UserAddress userAddressB = userAddressRepository.save(new UserAddress(202l, 10002l, "测试地址-2", 54321));
		//测试findOne，查询(201l,10001l)主键的数据
		UserAddress userAddressExample = new UserAddress(201l, 10001l, null, null);
		Example<UserAddress> queryExample = Example.of(userAddressExample);
		UserAddress userAddress = userAddressRepository.findOne(queryExample).get();
		Assert.assertEquals(userAddress.getAddress(),"测试地址-1");
		//更新数据
		userAddress.setZip(12321);
		userAddressRepository.save(userAddress);
		//测试更新结果
		UserAddress userAddress1 = userAddressRepository.findById(new UserAddressPK(201l, 10001l)).get();
		Assert.assertEquals(userAddress1.getZip(),Integer.valueOf(12321));
		//删除记录
		userAddressRepository.delete(userAddress1);
		userAddressRepository.deleteById(new UserAddressPK(202l, 10002l));
		//测试删除后的记录数
		long count = userAddressRepository.count();
		Assert.assertEquals(0, count);
	}


}
