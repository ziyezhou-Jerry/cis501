package cis501.submission;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class MyProperties {

    private static boolean initialized = false;
    private static String theTraceFile = null;
    private static int runLimit = 0;

    private static void init() {
        assert !initialized;
        Properties prop = new Properties();
        String propFileName = "cis501.properties";

        try {
            // read the properties file to trigger any access control exceptions
            Path path = FileSystems.getDefault().getPath(propFileName);
            byte[] b = Files.readAllBytes(path);
            if (0 == b.length) {
                throw new IllegalAccessError("Can't read from property file " + propFileName);
            }

            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propFileName);

            if (inputStream == null) {
                throw new FileNotFoundException("unable to read property file '" + propFileName + "'");
            }
            prop.load(inputStream);

            theTraceFile = prop.getProperty("TraceFile");
            if (null == theTraceFile) {
                throw new NullPointerException();
            }
            runLimit = Integer.parseInt(prop.getProperty("UopRunLimit"));
            initialized = true;

        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalAccessError(e.toString());
        }
    }

    public static String traceFile() {
        if (!initialized) { init(); }

        assert null != theTraceFile;
        return theTraceFile;
    }

    public static int uopLimit() {
        return 100000; // TODO: don't hard-code this
        //if (!initialized) { init(); }

        //return runLimit;
    }

}
