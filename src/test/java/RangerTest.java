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
  public void Ranger_instantiatesWithName_true() {
    Ranger testRanger = new Ranger("Rick");
    assertEquals("Rick", testRanger.getName());
  }
}
