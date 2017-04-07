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
}
