import org.example.HomeSystem;
import org.example.SystemLogger;
import org.example.models.Light;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class HomeSystemTest {

    private SystemLogger logger;
    private HomeSystem homeSystem;

    @Before
    public void setUp() {
        logger = Mockito.mock(SystemLogger.class);
        homeSystem = new HomeSystem(logger);
    }

    @Test
    public void thingsEmptyAtInit() {
        Assert.assertTrue(homeSystem.getThings().isEmpty());
    }

    @Test
    public void addLightSuccess() {
        homeSystem.addThing(new Light());
        Assert.assertEquals(1, homeSystem.getThings().size());
    }

    @Test
    public void toggleLightsWhenHomeSystemIsOnSuccess() {
        homeSystem.addThing(new Light());
        homeSystem.addThing(new Light());

        homeSystem.toggleAllLights(true);

        for (Light light : homeSystem.getAllLights()) {
            Assert.assertTrue(light.isLightOn());
        }
    }

    @Test
    public void toggleLightsWhenHomeSystemIsOffFail() {
        homeSystem.addThing(new Light());
        homeSystem.addThing(new Light());
        homeSystem.setState(HomeSystem.State.OFF);

        homeSystem.toggleAllLights(true);

        for (Light light : homeSystem.getAllLights()) {
            Assert.assertFalse(light.isLightOn());
        }
    }

    @Test
    public void toggleLightTriggersHomeSystemAddLogSuccess() {
        Light light = new Light();
        light.setLightChangedListener(homeSystem);
        light.setLightOn(true);

        Mockito.verify(logger).addLog(Mockito.anyString());
    }
}
