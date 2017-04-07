import org.junit.*;
import org.sql2o.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Timestamp;

import java.text.DateFormat;
import java.util.Date;

public class SightingTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void sighting_instantiatesCorrectly_true() {
    NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Deer");
    testNonEndangeredAnimal.save();
    Sighting testSighting = new Sighting(testNonEndangeredAnimal.getId(), "45.472428, -121.946466", 2);
    assertEquals(true, testSighting instanceof Sighting);
  }

  @Test
  public void equals_returnsTrueIfLocationAndDescriptionAreSame_true() {
    NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Deer");
    testNonEndangeredAnimal.save();
    Sighting testSighting = new Sighting(testNonEndangeredAnimal.getId(), "45.472428, -121.946466", 2);
    Sighting anotherSighting = new Sighting(testNonEndangeredAnimal.getId(), "45.472428, -121.946466", 2);
    assertTrue(testSighting.equals(anotherSighting));
  }

  @Test
  public void save_insertsObjectIntoDatabase_Sighting() {
    NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Deer");
    testNonEndangeredAnimal.save();
    Sighting testSighting = new Sighting (testNonEndangeredAnimal.getId(), "45.472428, -121.946466", 2);
    testSighting.save();
    assertEquals(true, Sighting.all().get(0).equals(testSighting));
  }

  @Test
  public void all_returnsAllInstancesOfSighting_true() {
    NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Deer");
    testNonEndangeredAnimal.save();
    Sighting testSighting = new Sighting (testNonEndangeredAnimal.getId(), "45.472428, -121.946466", 2);
    testSighting.save();
    NonEndangeredAnimal secondTestNonEndangeredAnimal = new NonEndangeredAnimal("Badger");
    secondTestNonEndangeredAnimal.save();
    Sighting secondTestSighting = new Sighting (testNonEndangeredAnimal.getId(), "45.472428, -121.946466", 3);
    secondTestSighting.save();
    assertEquals(true, Sighting.all().get(0).equals(testSighting));
    assertEquals(true, Sighting.all().get(1).equals(secondTestSighting));
  }

  @Test
  public void find_returnsSightingWithSameId_secondSighting() {
    NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Deer");
    testNonEndangeredAnimal.save();
    Sighting testSighting = new Sighting (testNonEndangeredAnimal.getId(), "45.472428, -121.946466", 2);
    testSighting.save();
    NonEndangeredAnimal secondTestNonEndangeredAnimal = new NonEndangeredAnimal("Badger");
    secondTestNonEndangeredAnimal.save();
    Sighting secondTestSighting = new Sighting (testNonEndangeredAnimal.getId(), "45.472428, -121.946466", 3);
    secondTestSighting.save();
    assertEquals(Sighting.find(secondTestSighting.getId()), secondTestSighting);
  }

  @Test
  public void find_returnsNullWhenNoNonEndangeredAnimalFound_null() {
    assertTrue(NonEndangeredAnimal.find(999) == null);
  }

}
