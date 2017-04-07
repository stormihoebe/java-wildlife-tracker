import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Ranger {
  public String name;
  public int id;

public Ranger(String name) {
  this.name = name;
}

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO rangers (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .executeUpdate()
      .getKey();
    }
  }

  public static Ranger find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM rangers WHERE id=:id;";
      Ranger ranger = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Ranger.class);
      return ranger;
    } catch (IndexOutOfBoundsException exception) {
      return null;
    }
  }

  public void updateName(String name){
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE rangers SET name = :name WHERE id=:id";
      con.createQuery(sql)
      .addParameter("name", name)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  @Override
  public boolean equals(Object otherRanger){
    if(!(otherRanger instanceof Ranger)){
      return false;
    } else {
      Ranger newRanger = (Ranger) otherRanger;
      return this.getName().equals(newRanger.getName()) && this.getId() == newRanger.getId();
    }
  }

  public static List<Ranger> all() {
    String sql = "SELECT * FROM rangers";
    try(Connection con = DB.sql2o.open()) {
    return con.createQuery(sql).executeAndFetch(Ranger.class);
    }
  }
}
