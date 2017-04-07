import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class NonEndangeredAnimal extends Animal{
  public static final String DATABASE_TYPE = "nonEndangered";


  public NonEndangeredAnimal(String name) {
    this.name = name;
    type = DATABASE_TYPE;
  }

  @Override
  public boolean equals(Object otherNonEndangeredAnimal) {
    if(!(otherNonEndangeredAnimal instanceof NonEndangeredAnimal)) {
      return false;
    } else {
      NonEndangeredAnimal newNonEndangeredAnimal = (NonEndangeredAnimal) otherNonEndangeredAnimal;
      return this.getName().equals(newNonEndangeredAnimal.getName()) && this.getId() == newNonEndangeredAnimal.getId();
    }
  }

  public static List<NonEndangeredAnimal> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals where type = 'nonEndangered';";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .executeAndFetch(NonEndangeredAnimal.class);
    }
  }

  public static NonEndangeredAnimal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE id=:id;";
      NonEndangeredAnimal nonEndangeredAnimal = con.createQuery(sql)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(NonEndangeredAnimal.class);
      return nonEndangeredAnimal;
    }
  }


}
