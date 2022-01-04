package org.example.models;

public abstract class Thing {

    public enum State {
        REACHABLE,
        UNREACHABLE,
    }

    protected String name = "";
    protected State state = State.REACHABLE;

    public abstract String getTypeName();
    public abstract String getDescription();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
