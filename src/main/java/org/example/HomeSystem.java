package org.example;

import org.example.models.Light;
import org.example.models.Thing;

import java.util.ArrayList;
import java.util.List;

public class HomeSystem implements Light.OnLightChangedListener {

    public enum State {
        ON,
        OFF,
    }

    private State state;

    private final List<Thing> things;

    private final SystemLogger logger;

    public HomeSystem(SystemLogger logger) {
        state = State.ON;
        this.logger = logger;
        this.things = new ArrayList<>();
    }

    public boolean addThing(Thing thing) {
        return things.add(thing);
    }

    public List<Thing> getThings() {
        return things;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public void onLightChanged(Light light) {
        String message = "HomeSystem - Light " + light.getName() + " updated. lightOn="+light.isLightOn();
        logger.addLog(message);
    }

    public void toggleAllLights(boolean isLightOn) {
        if (state == State.OFF)
            return;

        for (Light light : this.getAllLights()) {
            light.setLightOn(isLightOn);
        }
    }

    public List<Light> getAllLights() {
        List<Light> lights = new ArrayList<>();

        for (Thing thing : this.getThings()) {
            if (thing instanceof Light)
                lights.add((Light) thing);
        }

        return lights;
    }
}
