package org.example;

import org.example.controllers.HomeSystemController;
import org.example.controllers.ThingController;
import org.example.core.Conf;
import org.example.core.Template;
import org.example.middlewares.LoggerMiddleware;
import org.example.models.Light;
import spark.Spark;

import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        initialize();

        HomeSystem homeSystem = HomeSystem.getInstance();

        Light light = new Light();
        light.setLightChangedListener(homeSystem);
        light.setLightOn(true);

        homeSystem.addThing(light);

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
