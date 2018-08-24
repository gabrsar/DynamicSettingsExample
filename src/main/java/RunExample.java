import br.com.gabrielsaraiva.dynamicsettings.dynamicsettings.DynamicSettings;
import br.com.gabrielsaraiva.dynamicsettings.dynamicsettings.SettingsReader;
import br.com.gabrielsaraiva.dynamicsettings.dynamicsettings.providers.dynamodb.DynamoDBProvider;
import java.util.Date;

public class RunExample {

    public static void main(String[] args) throws InterruptedException {

        // First of all check this class: Settings.java in same folder.

        // Create your Amazon credentials file with your credentials. (~/.aws/credentials)
        // https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html

        // Then create a new table on your DynamoDB, called "MySettings", with the key as "module".
        // You can change the table name, but not the key name, it should be "module"

        // Then add an Item on this table, with module value equal "Home".
        // Add another String (S) field with the name "address", choose a value for it.


        // Then simply run this project. Settings will be updated every 5 seconds

        DynamoDBProvider dynamodbProvider = new DynamoDBProvider("MySettings");
        DynamicSettings ds = new DynamicSettings(dynamodbProvider, 5, Settings.class);

        ds.start();

        // Instead of instantiating this every time, receive it through injection or as constructor parameter.
        // This will make your application testable.
        SettingsReader settingsReader = new SettingsReader();

        // This will keep it running for two minutes, so you have time to change things in table and
        // see how it reflects here after a while
        for (int i = 0; i < 120; i++) {

            System.out.println(new Date());
            System.out.println(settingsReader.get(Settings.Home.address));
            System.out.println(settingsReader.get(Settings.Home.totalArea));
            System.out.println(settingsReader.get(Settings.Home.family));

            Thread.sleep(1000);
        }

        System.exit(0);

    }

}
