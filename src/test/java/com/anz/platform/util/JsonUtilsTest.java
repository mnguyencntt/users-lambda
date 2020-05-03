package com.anz.platform.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class JsonUtilsTest {
  @Test
  public void testConstructor() {
    Assertions.assertThrows(UnsupportedOperationException.class, () -> {
      new JsonUtils();
    });
  }

  @Test
  public void testToJson() {
    Student student = new Student("Test1", "Test2");
    String jsonStudent = JsonUtils.toJson(student);
    assertNotNull(jsonStudent);
    assertNotNull(JsonUtils.toObject(jsonStudent, Student.class));
    String jsonStudents = JsonUtils.toJson(Arrays.asList(student));
    assertNotNull(jsonStudents);
    assertNotNull(JsonUtils.toObject(jsonStudents, new TypeReference<List<Student>>() {}));
  }
}


@Data
@NoArgsConstructor
@AllArgsConstructor
class Student {
  private String name;
  private String age;
}
