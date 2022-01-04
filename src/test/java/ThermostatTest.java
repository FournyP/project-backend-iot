import org.example.models.Thermostat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ThermostatTest {

    Thermostat thermostat;

    @Before
    public void setUp() {
        thermostat = new Thermostat(10, 50);
    }

    @Test
    public void setTemperatureWithinValidRangeSuccess() {
        Assert.assertEquals(thermostat.getMinTemperature(), thermostat.getTemperature());

        boolean changed = thermostat.setTemperature(thermostat.getMaxTemperature());

        Assert.assertTrue(changed);
        Assert.assertEquals(thermostat.getMaxTemperature(), thermostat.getTemperature());
    }

    @Test
    public void setTemperatureBelowMinFail() {
        Assert.assertEquals(thermostat.getMinTemperature(), thermostat.getTemperature());

        boolean changed = thermostat.setTemperature(thermostat.getMinTemperature() - 1);

        Assert.assertFalse(changed);
        Assert.assertEquals(thermostat.getMinTemperature(), thermostat.getTemperature());
    }

    @Test
    public void setTemperatureAboveMaxFail() {
        Assert.assertEquals(thermostat.getMinTemperature(), thermostat.getTemperature());

        boolean changed = thermostat.setTemperature(thermostat.getMaxTemperature() + 1);

        Assert.assertFalse(changed);
        Assert.assertEquals(thermostat.getMinTemperature(), thermostat.getTemperature());
    }
}
