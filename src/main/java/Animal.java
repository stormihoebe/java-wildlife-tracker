import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Animal {
  public String name;
  public int id;
  public String type;


  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  @Override
  public boolean equals(Object otherAnimal) {
    if(!(otherAnimal instanceof Animal)) {
      return false;
    } else {
      Animal newAnimal = (Animal) otherAnimal;
      return this.getName().equals(newAnimal.getName());
    }
  }




  public static List<Object> getAllAnimals() {
    List<Object> allAnimals = new ArrayList<Object>();

    try(Connection con = DB.sql2o.open()) {
      String sqlEndangered = "SELECT * FROM animals WHERE type = 'endangered';";
      List<EndangeredAnimal> endangeredAnimals = con.createQuery(sqlEndangered)
      .throwOnMappingFailure(false)
      .executeAndFetch(EndangeredAnimal.class);
      allAnimals.addAll(endangeredAnimals);

      String sqlNonEndangeredAnimal = "SELECT * FROM animals WHERE type = 'nonEndangered';";
      List<NonEndangeredAnimal> nonEndangeredAnimals = con.createQuery(sqlNonEndangeredAnimal)
      .throwOnMappingFailure(false)
      .executeAndFetch(NonEndangeredAnimal.class);
      allAnimals.addAll(nonEndangeredAnimals);
    }
    return allAnimals;
  }


  public void updateName(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE animals SET name=:name WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("name", name)
        .throwOnMappingFailure(false)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM animals WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeUpdate();
    }
  }

  public List<Sighting> getSightings() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE animal_id=:id;";
        List<Sighting> sightings = con.createQuery(sql)
          .addParameter("id", id)
          .throwOnMappingFailure(false)
          .executeAndFetch(Sighting.class);
      return sightings;
    }
  }

}
