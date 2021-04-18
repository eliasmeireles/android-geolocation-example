package com.challenge.us.geolocation.core.permission.location;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class AccessPermissionModule {

    @Provides
    public AccessPermission permission() {
        return new AccessPermissionImpl();
    }
}
