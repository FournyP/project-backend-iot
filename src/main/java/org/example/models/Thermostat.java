package org.example.models;

public class Thermostat extends Thing {

    private final int minTemperature;
    private final int maxTemperature;

    private int temperature;

    public Thermostat(int minTemperature, int maxTemperature) {
        this.temperature = minTemperature;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    @Override
    public String getTypeName() {
        return "Thermostat";
    }

    @Override
    public String getDescription() {
        return "temperature=" + temperature;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public int getTemperature() {
        return temperature;
    }

    public boolean setTemperature(int temperature) {
        if (temperature < minTemperature || temperature > maxTemperature)
            return false;

        this.temperature = temperature;
        return true;
    }
}
