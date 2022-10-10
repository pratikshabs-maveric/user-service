package com.maveric.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.userservice.enumeration.Gender;
import com.maveric.userservice.model.User;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public
class UserServiceApplicationTests {

	public static final String APIV1 ="/api/v1/users";

	@Test
	void contextLoads() {
		assertTrue(true);
	}
	public static Object getUserDto()
	{
		return  UserDto.builder()
				._id("123")
				.firstName("karthick")
				.lastName("prabhu")
				.middleName("mh")
				.phoneNumber("9788146455")
				.email("test@gmail.com")
				.address("madurai")
				.dateOfBirth(LocalDate.parse("2022-08-30"))
				.gender(Gender.MALE)
				.build();
	}
	public static User getUser()
	{
		return  User.builder()
				._id("123")
				.firstName("karthick")
				.lastName("prabhu")
				.middleName("mh")
				.phoneNumber("9788146455")
				.email("test@gmail.com")
				.address("madurai")
				.dateOfBirth(LocalDate.parse("2022-08-30"))
				.gender(Gender.MALE)
				.build();
	}
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
