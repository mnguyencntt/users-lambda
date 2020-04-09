package com.anz.platform.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import com.anz.platform.model.Users;

public class UsersTest {
  @Test
  public void testUser() {
    int expectedLength = 13;

    Users user = new Users();
    user.setId("12345");

    String fieldJoining = user.findFieldsJoining();
    System.out.println(fieldJoining);
    assertTrue(fieldJoining.startsWith("id,receiverUserId,subject,contentBody"));
    assertEquals(expectedLength, fieldJoining.split(",").length);

    String marks = user.findMarksJoining();
    System.out.println(marks);
    assertEquals(expectedLength, marks.chars().filter(ch -> ch == '?').count());
    assertEquals(expectedLength, marks.split(",").length);

    try {
      assertEquals("12345", user.findValues()[0]);
      assertEquals("", user.findValues()[1]);
      assertEquals("", user.findValues()[2]);
    } catch (IllegalArgumentException | IllegalAccessException e) {
      fail();
    }
  }
}
