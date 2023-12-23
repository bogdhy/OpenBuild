import {Component, inject} from "@angular/core";
import {RouterModule} from "@angular/router";
import {KeycloakService} from "keycloak-angular";

@Component({
  standalone: true,
  imports: [RouterModule],
  selector: "open-build-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
})
export class AppComponent {
  title = "openbuild-webapp";

  keycloakService = inject(KeycloakService);
}
