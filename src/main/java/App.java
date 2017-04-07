import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      // model.put("nonEndangeredAnimals", NonEndangeredAnimal.all());
      // model.put("endangeredAnimals", EndangeredAnimal.all());
      // model.put("sightings", Sighting.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animals/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/animal-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animals/new/nonEndangered", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      NonEndangeredAnimal animal = new NonEndangeredAnimal(name);
      animal.save();
      model.put("nonEndangeredAnimals", NonEndangeredAnimal.all());
      model.put("endangeredAnimals", EndangeredAnimal.all());
      String url = String.format("/animals");
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animals/new/endangered", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String health = request.queryParams("health");
      String age = request.queryParams("age");
      EndangeredAnimal animal = new EndangeredAnimal(name, health, age);
      animal.save();
      model.put("nonEndangeredAnimals", NonEndangeredAnimal.all());
      model.put("endangeredAnimals", EndangeredAnimal.all());
      String url = String.format("/animals");
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animals", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("nonEndangeredAnimals", NonEndangeredAnimal.all());
      model.put("endangeredAnimals", EndangeredAnimal.all());
      model.put("template", "templates/animals.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animals/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int animalId = Integer.parseInt(request.params(":id"));
      if(NonEndangeredAnimal.find(animalId) == null) {
        EndangeredAnimal animal = EndangeredAnimal.find(animalId);
        model.put("animal", animal);
      } else {
        NonEndangeredAnimal animal = NonEndangeredAnimal.find(animalId);
        model.put("animal", animal);
      }
      model.put("template", "templates/animal.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/sightings/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("animals", Animal.getAllAnimals());
      Ranger newRanger = new Ranger("dan");
      newRanger.save();
      model.put("nonEndangeredAnimals", NonEndangeredAnimal.all());
      model.put("endangeredAnimals", EndangeredAnimal.all());
      model.put("rangers", Ranger.all());
      model.put("template", "templates/sighting-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/sightings/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int animal = Integer.parseInt(request.queryParams("animal"));
      int ranger = Integer.parseInt(request.queryParams("ranger"));
      String location = request.queryParams("location");
      Sighting newSighting = new Sighting(animal, location, ranger);
      newSighting.save();
      String url = String.format("/sightings");
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/sightings", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("sightings", Sighting.all());
      model.put("template", "templates/sightings.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // post("/endangered_sighting", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   String rangerName = request.queryParams("rangerName");
    //   int animalIdSelected = Integer.parseInt(request.queryParams("endangeredAnimalSelected"));
    //   String latLong = request.queryParams("latLong");
    //   Sighting sighting = new Sighting(animalIdSelected, latLong, rangerName);
    //   sighting.save();
    //   model.put("sighting", sighting);
    //   model.put("animals", EndangeredAnimal.all());
    //   String animal = EndangeredAnimal.find(animalIdSelected).getName();
    //   model.put("animal", animal);
    //   model.put("template", "templates/success.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // post("/sighting", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   String rangerName = request.queryParams("rangerName");
    //   int animalIdSelected = Integer.parseInt(request.queryParams("animalSelected"));
    //   String latLong = request.queryParams("latLong");
    //   Sighting sighting = new Sighting(animalIdSelected, latLong, rangerName);
    //   sighting.save();
    //   model.put("sighting", sighting);
    //   model.put("animals", Animal.all());
    //   String animal = Animal.find(animalIdSelected).getName();
    //   model.put("animal", animal);
    //   model.put("template", "templates/success.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //

    // get("/error", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   model.put("template", "templates/error.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
  }
}
