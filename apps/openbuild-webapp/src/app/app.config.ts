import {APP_INITIALIZER, ApplicationConfig, importProvidersFrom} from '@angular/core';
import {KeycloakAngularModule, KeycloakService} from 'keycloak-angular';
import {provideHttpClient} from '@angular/common/http';
import {provideRouter, withEnabledBlockingInitialNavigation,} from '@angular/router';
import {appRoutes} from './app.routes';

export const appConfig: ApplicationConfig = {
  providers: [
    importProvidersFrom(KeycloakAngularModule),
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService],
    },
    provideHttpClient(),
    provideRouter(appRoutes, withEnabledBlockingInitialNavigation()),
  ],
};
