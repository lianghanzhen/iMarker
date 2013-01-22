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

    public IMarkerTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass, new RobolectricConfig(new File("AndroidManifest.xml"), new File("res")));
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
