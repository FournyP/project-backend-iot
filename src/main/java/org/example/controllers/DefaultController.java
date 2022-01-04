package org.example.controllers;

import org.example.HomeSystem;

public abstract class DefaultController {
    protected HomeSystem homeSystem;

    public DefaultController(HomeSystem homeSystem) {
        this.homeSystem = homeSystem;
    }
}
