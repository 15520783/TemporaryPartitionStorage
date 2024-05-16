package TemporaryPartitionStorage;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Student {
  private String id;
  private String name;
  private int age;
}


@Slf4j
public class Application {

  public static void main(String[] args) {
    try (TemporaryPartition<String, Student> tempPartition = new TemporaryPartition<String, Student>() {}) {
      for (int i = 0; i < 100; i++) {
        tempPartition.add(UUID.randomUUID().toString(), new Student(("0000" + i), "Member " + i, 18));
      }
      tempPartition.save();
      tempPartition.load();
      tempPartition.getDataMap();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

}
