package com.anz.platform.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PartitionUtilsTest {
  @Test
  public void testCallPrivateConstructor() {
    Assertions.assertThrows(InvocationTargetException.class, () -> {
      Constructor<PartitionUtils> c = PartitionUtils.class.getDeclaredConstructor();
      c.setAccessible(true);
      c.newInstance();
    });
  }

  @Test
  public void givenList_whenParitioningIntoNSublists_thenCorrect1() {
    List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
    List<List<Integer>> subSets = PartitionUtils.partition(intList, 3);

    assertEquals(3, subSets.size());
    assertEquals(3, subSets.get(0).size());
    assertEquals(3, subSets.get(1).size());
    assertEquals(2, subSets.get(2).size());
  }

  @Test
  public void givenList_whenParitioningIntoNSublists_thenCorrect2() {
    List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
    List<List<Integer>> subSets = PartitionUtils.partition(intList, 50);

    assertEquals(1, subSets.size());
    assertEquals(8, subSets.get(0).size());
  }

  @Test
  public void givenList_partitiontest2() {
    List<String> list = new ArrayList<String>();
    list.add("one");
    list.add("two");
    list.add("three");
    list.add("four");
    list.add("five");
    list.add("six");
    list.add("seven");
    list.add("eight");
    list.add("nine");
    list.add("ten");
    list.add("eleven");
    List<List<String>> partition = PartitionUtils.partition(list, 1);
    System.out.println(partition.get(2).size());
    assertTrue(partition.size() == 11);
    assertTrue(partition.get(0).size() == 1);
    partition = PartitionUtils.partition(list, 7);
    assertTrue(partition.size() == 2);
    assertTrue(partition.get(0).size() == 7);
    assertTrue(partition.get(1).size() == 4);

    // now let assume you want to have x number of buckets
    // How many elements must placed in a list?
    // Take x as 3

    int buckets = 3;
    int divide = list.size() / buckets;
    if (list.size() % buckets != 0) {
      divide++;
    }
    System.out.println("Max. number of element in each bucket " + divide);
    partition = PartitionUtils.partition(list, divide);
    assertTrue(partition.size() == buckets);
  }
}
