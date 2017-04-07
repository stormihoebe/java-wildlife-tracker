import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class NonEndangeredAnimalTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void nonEndangeredAnimal_instantiatesCorrectly_false() {
    NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Bear");
    assertEquals(true, testNonEndangeredAnimal instanceof NonEndangeredAnimal);
  }

  @Test
  public void getName_nonEndangeredAnimalInstantiatesWithName_Bear() {
    NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Bear");
    assertEquals("Bear", testNonEndangeredAnimal.getName());
  }

  @Test
  public void getType_nonEndangeredAnimalInstantiatesWithType_nonEndangered() {
    NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Bear");
    assertEquals("nonEndangered", testNonEndangeredAnimal.getType());
  }

  @Test
  public void equals_returnsTrueIfNameIsTheSame_true() {
    NonEndangeredAnimal firstNonEndangeredAnimal = new NonEndangeredAnimal("Bear");
    NonEndangeredAnimal anotherNonEndangeredAnimal = new NonEndangeredAnimal("Bear");
    assertTrue(firstNonEndangeredAnimal.equals(anotherNonEndangeredAnimal));
  }

  @Test
  public void save_assignsIdToObjectAndSavesObjectToDatabase() {
    NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Bear");
    testNonEndangeredAnimal.save();
    NonEndangeredAnimal savedNonEndangeredAnimal = NonEndangeredAnimal.all().get(0);
    assertEquals(testNonEndangeredAnimal.getId(), savedNonEndangeredAnimal.getId());
  }

  @Test
  public void all_returnsAllInstancesOfNonEndangeredAnimal_true() {
    NonEndangeredAnimal firstNonEndangeredAnimal = new NonEndangeredAnimal("Bear");
    firstNonEndangeredAnimal.save();
    NonEndangeredAnimal secondNonEndangeredAnimal = new NonEndangeredAnimal("Black Bear");
    secondNonEndangeredAnimal.save();
    assertEquals(true, NonEndangeredAnimal.all().get(0).equals(firstNonEndangeredAnimal));
    assertEquals(true, NonEndangeredAnimal.all().get(1).equals(secondNonEndangeredAnimal));
  }

  @Test
  public void find_returnsNonEndangeredAnimalWithSameId_secondNonEndangeredAnimal() {
    NonEndangeredAnimal firstNonEndangeredAnimal = new NonEndangeredAnimal("Bear");
    firstNonEndangeredAnimal.save();
    NonEndangeredAnimal secondNonEndangeredAnimal = new NonEndangeredAnimal("Black Bear");
    secondNonEndangeredAnimal.save();
    assertEquals(NonEndangeredAnimal.find(secondNonEndangeredAnimal.getId()), secondNonEndangeredAnimal);
  }

  @Test
  public void delete_deletesNonEndangeredAnimalFromDatabase_true() {
    NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Bear");
    testNonEndangeredAnimal.save();
    testNonEndangeredAnimal.delete();
    assertEquals(0, NonEndangeredAnimal.all().size());
  }
  public void updateName_updatesNonEndangeredAnimalNameInDatabase_UpdatedName() {
    NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Bear");
    testNonEndangeredAnimal.save();
    testNonEndangeredAnimal.updateName("Little Bear");
    assertEquals("Little Bear", testNonEndangeredAnimal.getName());
  }

  @Test
  public void find_returnsNullWhenNoNonEndangeredAnimalFound_null() {
    assertTrue(NonEndangeredAnimal.find(999) == null);
  }
}
