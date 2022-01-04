package org.example.models;

public class Light extends Thing {

    public interface OnLightChangedListener {
        void onLightChanged(Light light);
    }

    private boolean isLightOn = false;

    private OnLightChangedListener lightChangedListener;

    @Override
    public String getTypeName() {
        return "Light";
    }

    @Override
    public String getDescription() {
        return "Light is " + isLightOn;
    }

    public boolean isLightOn() {
        return isLightOn;
    }

    public void setLightOn(boolean lightOn) {
        if (state == State.UNREACHABLE)
            return;

        isLightOn = lightOn;
        if (lightChangedListener != null)
            lightChangedListener.onLightChanged(this);
    }

    public void setLightChangedListener(OnLightChangedListener lightChangedListener) {
        this.lightChangedListener = lightChangedListener;
    }
}
