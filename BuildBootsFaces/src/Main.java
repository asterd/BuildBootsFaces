import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

import java.io.File;

public class Main {
	private static final String PROJECT_DIRECTORY_PATH = "/Users/durzod/Desktop/Development/java/bootsfaces/asterd/BootsFaces-OSP";
	public static String[] enabledThemeList = new String[] {
		"default",
		"cerulean",
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
		//"superhero",
		//"united",
		//"yeti",
		"other"
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

        // configure project
        connector.forProjectDirectory(new File(PROJECT_DIRECTORY_PATH));
        ProjectConnection connection = connector.connect();
        try {
        	for(String theme : enabledThemeList) {
	            // Configure the build for single theme compilation
	            BuildLauncher launcher = connection.newBuild();
	            //if your build needs crazy amounts of memory:
	            launcher.setJvmArguments("-Xmx1024m", "-Xms256m", "-XX:MaxPermSize=1024m");
	            launcher.forTasks("gradleResources:buildResources", "jar");
	            launcher.withArguments("-Pcustom-theme=" + theme); 
	            launcher.setStandardOutput(System.out);
	            launcher.setStandardError(System.err);
	
	            // Run the build
	            launcher.run();
        	}
        } finally {
            // Clean up
            connection.close();
        }
    }
}
