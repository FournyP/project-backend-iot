package org.example;

import org.example.controllers.HomeSystemController;
import org.example.controllers.ThingController;
import org.example.core.Conf;
import org.example.core.Template;
import org.example.middlewares.LoggerMiddleware;
import org.example.models.Light;
import org.example.models.Thermostat;
import spark.Spark;

public class App {
    public static void main(String[] args) {
        initialize();

        SystemLogger logger = new SystemLogger();
        HomeSystem homeSystem = new HomeSystem(logger);

        Light light = new Light();
        light.setName("Bedroom");
        light.setLightChangedListener(homeSystem);
        light.setLightOn(true);

        Thermostat thermostat = new Thermostat(10, 30);
        thermostat.setName("Bedroom");

        homeSystem.addThing(light);
        homeSystem.addThing(thermostat);

        HomeSystemController homeSystemController = new HomeSystemController(homeSystem);
        ThingController thingController = new ThingController(homeSystem);

        Spark.get("/", (req, res) -> homeSystemController.list(req, res));
        Spark.get("/things/:id", (req, res) -> thingController.info(req, res));
    }

    static void initialize() {
        Template.initialize();

        // Display exceptions in logs
        Spark.exception(Exception.class, (e, req, res) -> e.printStackTrace());

        // Serve static files (img/css/js)
        Spark.staticFiles.externalLocation(Conf.STATIC_DIR.getPath());

        // Configure server port
        Spark.port(Conf.HTTP_PORT);
        final LoggerMiddleware log = new LoggerMiddleware();
        Spark.before(log::process);
    }
}
