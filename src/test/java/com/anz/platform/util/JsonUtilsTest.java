package com.anz.platform.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;

public class JsonUtilsTest {
  @Test
  public void testCallPrivateConstructor() throws Exception {
    Assertions.assertThrows(InvocationTargetException.class, () -> {
      Constructor<JsonUtils> c = JsonUtils.class.getDeclaredConstructor();
      c.setAccessible(true);
      c.newInstance();
    });
  }

  @Test
  public void testJsonObject() throws Exception {
    final Student student = new Student();
    student.setName("MinhNguyen");
    student.setAge(27);
    final String studentJson = JsonUtils.toJson(student);
    assertEquals("{\"name\":\"MinhNguyen\",\"age\":27}", studentJson);

    final Student studentObject1 = JsonUtils.toObject(studentJson, Student.class);
    assertEquals("MinhNguyen", studentObject1.getName());

    Map<Object, Object> linkedHashMap = new LinkedHashMap<>();
    linkedHashMap.put("name", "MinhNguyen");
    linkedHashMap.put("age", "27");
    final Student studentObject2 = JsonUtils.convertValue(linkedHashMap, Student.class);
    assertEquals("MinhNguyen", studentObject2.getName());
  }

  @Test
  public void testJsonListObject() throws Exception {
    final Student student = new Student();
    student.setName("MinhNguyen");
    student.setAge(27);
    List<Student> students = new ArrayList<>();
    students.add(student);
    final String studentsJson = JsonUtils.toJson(students);
    assertEquals("[{\"name\":\"MinhNguyen\",\"age\":27}]", studentsJson);

    final List<Student> studentsObject1 = JsonUtils.toObject(studentsJson, new TypeReference<List<Student>>() {});
    assertEquals("MinhNguyen", studentsObject1.get(0).getName());
  }

  @Test
  public void testToJsonExeption() throws Exception {
    Assertions.assertThrows(RuntimeException.class, () -> {
      final Teacher teacher = new Teacher("MinhNguyen", 27);
      JsonUtils.toJson(teacher);
    });
  }

  @Test
  public void testToObjectExeption() throws Exception {
    Assertions.assertThrows(RuntimeException.class, () -> {
      final String studentJson = "{,UnknowException,}";
      JsonUtils.toObject(studentJson, Student.class);
    });
  }

  @Test
  public void testToListObjectExeption() throws Exception {
    Assertions.assertThrows(RuntimeException.class, () -> {
      final String studentsJson = "{,UnknowException,}";
      JsonUtils.toObject(studentsJson, new TypeReference<List<Student>>() {});
    });
  }

  @Test
  public void testMapConvertValueExeption() throws Exception {
    Assertions.assertThrows(RuntimeException.class, () -> {
      Map<Object, Object> linkedHashMap = new LinkedHashMap<>();
      linkedHashMap.put("name1", "MinhNguyen");
      linkedHashMap.put("age2", "27");
      JsonUtils.convertValue(linkedHashMap, Student.class);
    });
  }

  @Test
  public void testLinkedMapConvertValueExeption() throws Exception {
    Assertions.assertThrows(RuntimeException.class, () -> {
      Map<Object, Object> linkedHashMap = new LinkedHashMap<>();
      linkedHashMap.put("name1", "MinhNguyen");
      linkedHashMap.put("age2", "27");
      JsonUtils.convertValue(linkedHashMap, new TypeReference<Student>() {});
    });
  }
}


@Data
class Student {
  private String name;
  private int age;
}


class Teacher {

  public Teacher(String name, int age) {
    this.name = name;
    this.age = age;
  }

  @SuppressWarnings("unused")
  private String name;
  @SuppressWarnings("unused")
  private int age;
}
