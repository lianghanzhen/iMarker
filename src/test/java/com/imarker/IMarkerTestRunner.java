package com.imarker;

import android.app.Application;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricConfig;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import junit.framework.Assert;
import org.junit.runners.model.InitializationError;

import java.io.File;
import java.lang.reflect.Method;

public class IMarkerTestRunner extends RobolectricTestRunner {

    private static final String ANDROID_MANIFEST = "AndroidManifest.xml";
    private static final String RESOURCES_DIR = "res";

    public IMarkerTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass, new RobolectricConfig(new File(ANDROID_MANIFEST), new File(RESOURCES_DIR)));
    }

    @Override
    protected Application createApplication() {
        IMarkerApplication application = (IMarkerApplication) super.createApplication();
        application.onCreate();
        return application;
    }

    @Override
    public void beforeTest(Method method) {
        super.beforeTest(method);

        Assert.assertNotNull(Robolectric.application);
    }
}
