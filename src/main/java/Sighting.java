import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;

public class Sighting {
  private int animal_id;
  private String location;
  private int ranger_id;
  private int id;
  private Timestamp time;

  public Sighting(int animal_id, String location, int ranger_id) {
    this.animal_id = animal_id;
    this.location = location;
    this.ranger_id = ranger_id;
  }

  public int getId() {
    return id;
  }

  public int getAnimalId() {
    return animal_id;
  }

  public String getLocation() {
    return location;
  }

  public Timestamp getSightingTime(){
    return time;
  }

  public int getRangerId() {
    return ranger_id;
  }

  @Override
  public boolean equals(Object otherSighting) {
    if(!(otherSighting instanceof Sighting)) {
      return false;
    } else {
      Sighting newSighting = (Sighting) otherSighting;
      return this.getAnimalId() == (newSighting.getAnimalId()) && this.getLocation().equals(newSighting.getLocation()) && this.getRangerId() == newSighting.getRangerId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO sightings (animal_id, location, ranger_id, time) VALUES (:animal_id, :location, :ranger_id, now());";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("animal_id", this.animal_id)
        .addParameter("location", this.location)
        .addParameter("ranger_id", this.ranger_id)
        .throwOnMappingFailure(false)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Sighting> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings;";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .executeAndFetch(Sighting.class);
    }
  }

  public static Sighting find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE id=:id;";
      Sighting sighting = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Sighting.class);
      return sighting;
    } catch (IndexOutOfBoundsException exception) {
      return null;
    }
  }

}
