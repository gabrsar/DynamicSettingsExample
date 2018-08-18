import static br.com.gabrielsaraiva.dynamicsettings.dynamicsettings.Setting.setting;

import br.com.gabrielsaraiva.dynamicsettings.dynamicsettings.DynamicSettings;
import br.com.gabrielsaraiva.dynamicsettings.dynamicsettings.Setting;
import br.com.gabrielsaraiva.dynamicsettings.dynamicsettings.providers.dynamodb.DynamodbProvider;
import java.util.Date;

public class RunExample {

    // This is how you define your settings from now. I suggesting using a new file for this Settings class.
    // this one will be here for the sake of simplicity of this example.
    public static class Settings {

        // It's will be called a Module, with a lot of settings inside. Modules cannot be nested for now.
        public static class Home {

            // This is how you define a setting.
            public static final Setting<String> address = setting("Home", "address", "This is my house");
            public static final Setting<Float> totalArea = setting("Home", "totalArea", 42.3F);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        // Create your Amazon credentials file with your credentials. (~/.aws/credentials)
        // https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html

        // Then create a new table on your DynamoDB, called "MySettings", with the key as "module".
        // You can change the table name, but not the key name, it should be "module"

        // Then add an Item on this table, with module value equal "Home".
        // Add another String (S) field with the name "address", choose a value for it.

        // Notice that the setting called totalArea, is not in your table, but it will use the default value :)

        // Then simply run this project. Settings will be updated every 5 seconds

        DynamodbProvider dynamodbProvider = new DynamodbProvider("MySettings");
        DynamicSettings ds = new DynamicSettings(dynamodbProvider, Settings.class);

        ds.start();

        // This will keep it running for two minutes, so you have time to change things in table and
        // see how it reflects here after a while
        for (int i = 0; i < 120; i++) {

            System.out.println(new Date());
            System.out.println(Settings.Home.address.getValue());
            System.out.println(Settings.Home.totalArea.getValue());

            Thread.sleep(1000);
        }

        System.exit(0);

    }

}
