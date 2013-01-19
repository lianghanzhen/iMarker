package com.imarker;

import android.app.Application;
import com.xtremelabs.robolectric.RobolectricConfig;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.runners.model.InitializationError;

import java.io.File;

public class IMarkerTestRunner extends RobolectricTestRunner {

    public IMarkerTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass, new RobolectricConfig(new File("AndroidManifest.xml"), new File("res")));
    }

}
