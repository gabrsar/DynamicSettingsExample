import br.com.gabrielsaraiva.dynamicsettings.dynamicsettings.Setting;
import br.com.gabrielsaraiva.dynamicsettings.dynamicsettings.SettingsReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RunExampleTest {

    @Test
    void testExampleWithSettingsReader() {

        // This way is not so good, once you cannot test for different values
        // to see how your application will behave.

        SettingsReader originalReader = new SettingsReader();
        String v = originalReader.get(Settings.Home.address);

        Assertions.assertEquals("This is my house", v);
    }

    @Test
    void testExampleWithMockSettingsReader() {

        // This way you can test for different values to see how your appliction will
        // behave with different values in a simple way.

        SettingsReader mockReader = Mockito.mock(SettingsReader.class);
        Mockito.when(mockReader.get(Settings.Home.address)).thenReturn("Some address");

        String x = mockReader.get(Settings.Home.address);

        Assertions.assertEquals("Some address", x);

    }

    @Test
    void testExampleWithNewImplementation() {

        // This is really weird approach,
        // but if you don't want to use Mockito or something else you can do this.

        SettingsReader newReader = new SettingsReader() {
            @Override
            public <T> T get(Setting<T> setting) {

                if (setting == Settings.Home.address) {
                    return (T) "Another value";
                }

                return null;

            }
        };

        String x = newReader.get(Settings.Home.address);

        Assertions.assertEquals("Another value", x);

    }

}
