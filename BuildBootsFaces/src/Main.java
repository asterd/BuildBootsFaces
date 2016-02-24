import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

import java.io.File;

public class Main {
	public static String[] enabledThemeList = new String[] {
		//"default",
		//"cerulean",
		//"cosmo",
		//"cyborg",
		//"darkly",
		//"flatly",
		//"journal",
		//"lumen",
		//"paper",
		//"readable",
		//"sandstone",
		//"simplex",
		//"slate",
		//"spacelab",
		"superhero",
		"united",
		//"yeti",
		//"other"
	};
	
    public static void main(String[] args) {
        // Configure the connector and create the connection
        GradleConnector connector = GradleConnector.newConnector();

        if (args.length > 0) {
            connector.useInstallation(new File(args[0]));
            if (args.length > 1) {
                connector.useGradleUserHomeDir(new File(args[1]));
            }
        }

        for(String theme : enabledThemeList) {
	        connector.forProjectDirectory(new File("/Users/durzod/Desktop/Development/java/bootsfaces/asterd/BootsFaces-OSP"));
	
	        ProjectConnection connection = connector.connect();
	        try {
	            // Configure the build
	            BuildLauncher launcher = connection.newBuild();
	            launcher.forTasks("gradleResources:buildResources", "jar");
	            launcher.withArguments("-Pcustom-theme=" + theme);
	            launcher.setStandardOutput(System.out);
	            launcher.setStandardError(System.err);
	
	            // Run the build
	            launcher.run();
	        } finally {
	            // Clean up
	            connection.close();
	        }
    	}
    }
}
