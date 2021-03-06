import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class RangerTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Ranger_instantiatesCorrectly_true() {
    Ranger testRanger = new Ranger("Rick");
    assertEquals(true, testRanger instanceof Ranger);
  }

  @Test
  public void getName_instantiatesWithName_true() {
    Ranger testRanger = new Ranger("Rick");
    assertEquals("Rick", testRanger.getName());
  }

  @Test
  public void getId_IdAssignedOnSave_true() {
    Ranger testRanger = new Ranger("Rick");
    testRanger.save();
    assertTrue(0 < testRanger.getId());
  }

  @Test
  public void all_returnsAllInstancesOfRanger_true() {
    Ranger firstRanger = new Ranger("Rick");
    firstRanger.save();
    Ranger secondRanger = new Ranger("Jane");
    secondRanger.save();
    assertEquals(true, Ranger.all().get(0).equals(firstRanger));
    assertEquals(true, Ranger.all().get(1).equals(secondRanger));
  }

  @Test
  public void updateName_updatesNameOfRanger_true() {
    Ranger testRanger = new Ranger("Jane");
    testRanger.save();
    testRanger.updateName("Jan");
    assertEquals("Jan", Ranger.find(testRanger.getId()).getName());
  }

}
