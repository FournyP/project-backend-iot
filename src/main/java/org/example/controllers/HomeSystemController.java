package org.example.controllers;

import org.example.HomeSystem;
import org.example.core.Template;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class HomeSystemController extends DefaultController {

    public HomeSystemController(HomeSystem homeSystem) {
        super(homeSystem);
    }

    public String list(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        model.put("things", homeSystem.getThings());
        return Template.render("home.html", model);
    }
}
