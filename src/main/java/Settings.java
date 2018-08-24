import br.com.gabrielsaraiva.dynamicsettings.dynamicsettings.Setting;
import java.util.Arrays;
import java.util.List;

// This is how you define your settings from now. I suggesting using a new file for this Settings class.
// this one will be here for the sake of simplicity of this example.

public class Settings {

    // It's will be called a Module, with a lot of settings inside. Modules cannot be nested for now.
    public static class Home {

        // This is how you define a setting.
        public static final Setting<String> address = Setting.define("address", "This is my house");

        // Notice that the setting called totalArea, is not in your table, but it will use the default value :)
        public static final Setting<Float> totalArea = Setting.define("totalArea", 42.3F);

        public static final Setting<List<String>> family = Setting
            .define("family", Arrays.asList("Me", "Dog", "Cat"), List.class, String.class);
    }
}