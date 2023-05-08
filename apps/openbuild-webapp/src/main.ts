import {bootstrapApplication} from "@angular/platform-browser";
import {provideRouter, withEnabledBlockingInitialNavigation,} from "@angular/router";
import {appRoutes} from "./app/app.routes";
import {AppComponent} from "./app/app.component";
import {APP_INITIALIZER, importProvidersFrom} from "@angular/core";
import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";
import {provideHttpClient} from "@angular/common/http";
import {initializeKeycloak} from "@openbuild/auth";

bootstrapApplication(AppComponent, {
  providers: [
    importProvidersFrom(KeycloakAngularModule),
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService],
    },
    provideHttpClient(),
    provideRouter(appRoutes, withEnabledBlockingInitialNavigation())
  ],
}).catch((err) => console.error(err));
