package com.anz.platform.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;

public class UsersTest {
  @Test
  public void testUser() {
    int expectedLength = 18;

    Users user = new Users();
    user.setId("12345");
    user.setUsername("testuser");
    user.setPassword("testpassword");

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
    assertEquals("id=12345", findFieldValues1.get(0));
    assertEquals("username=testuser", findFieldValues1.get(1));
    assertEquals("password=testpassword", findFieldValues1.get(2));
    List<String> findFieldValues2 = user.findFieldValues("id");
    assertEquals("username=testuser", findFieldValues2.get(0));
    assertEquals("password=testpassword", findFieldValues2.get(1));

    //
    String findFieldValuesJoining1 = user.findFieldValuesJoining();
    System.out.println(findFieldValuesJoining1);
    assertEquals("id=12345,username=testuser,password=testpassword", findFieldValuesJoining1);
    String findFieldValuesJoining2 = user.findFieldValuesJoining("id");
    System.out.println(findFieldValuesJoining2);
    assertEquals("username=testuser,password=testpassword", findFieldValuesJoining2);
  }
}
