package com.anz.platform.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.anz.platform.util.Constants;

public class UsersTest {
  private static final int expectedLength = 18;

  @Test
  public void testUserBuilder() {
    Users user = Users.builder().baseAddress("baseAddress").billingAddress("billingAddress").createdAt("createdAt").createdBy("createdBy").dateOfBirth("dateOfBirth").deliveryAddress("deliveryAddress")
        .gender("gender").imageAvatarUrl("imageAvatarUrl").id("id").isActivated("true").lastLogin("lastLogin").name("name").password("password").request("request").updatedAt("updatedAt")
        .updatedBy("updatedBy").username("username").userType("userType").build();
    assertNotNull(user);
    String userStr = Users.builder().baseAddress("baseAddress").billingAddress("billingAddress").createdAt("createdAt").createdBy("createdBy").dateOfBirth("dateOfBirth")
        .deliveryAddress("deliveryAddress").gender("gender").imageAvatarUrl("imageAvatarUrl").id("id").isActivated("true").lastLogin("lastLogin").name("name").password("password").request("request")
        .updatedAt("updatedAt").updatedBy("updatedBy").username("username").userType("userType").toString();
    assertNotNull(userStr);
  }

  @Test
  public void testUser() {
    Users user = new Users();
    user.setId("12345");
    user.setUsername("testuser");
    user.setPassword("testpassword");

    user.persist("");
    user.update("");
    user.persist(Constants.ANONYMOUS);
    user.update(Constants.ANONYMOUS);
    assertEquals(user.hashCode(), user.hashCode());
    assertTrue(user.equals(user));

    //
    String fieldJoining = user.findFieldsJoining();
    System.out.println(fieldJoining);
    assertTrue(fieldJoining.startsWith("id,username,password,"));
    assertEquals(expectedLength, fieldJoining.split(",").length);

    //
    String marks = user.findMarksJoining();
    System.out.println(marks);
    assertEquals(expectedLength, marks.chars().filter(ch -> ch == '?').count());
    assertEquals(expectedLength, marks.split(",").length);

    //
    String[] findValues = user.findValues();
    assertEquals("12345", findValues[0]);
    assertEquals("testuser", findValues[1]);
    assertEquals("testpassword", findValues[2]);

    //
    List<String> findFieldValues1 = user.findFieldValues();
    assertEquals("id='12345'", findFieldValues1.get(0));
    assertEquals("username='testuser'", findFieldValues1.get(1));
    assertEquals("password='testpassword'", findFieldValues1.get(2));
    List<String> findFieldValues2 = user.findFieldValues("id");
    assertEquals("username='testuser'", findFieldValues2.get(0));
    assertEquals("password='testpassword'", findFieldValues2.get(1));

    //
    String findFieldValuesJoining1 = user.findFieldValuesJoining();
    System.out.println(findFieldValuesJoining1);
    assertTrue(findFieldValuesJoining1.startsWith("id='12345',username='testuser',password='testpassword',"));
    String findFieldValuesJoining2 = user.findFieldValuesJoining("id");
    System.out.println(findFieldValuesJoining2);
    assertTrue(findFieldValuesJoining2.startsWith("username='testuser',password='testpassword',"));
  }
}
