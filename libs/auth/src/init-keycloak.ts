import {KeycloakEventType, KeycloakService} from 'keycloak-angular'

export const initializeKeycloak = (keycloak: KeycloakService) => () => {
  const result = keycloak.init({
    enableBearerInterceptor: true,
    loadUserProfileAtStartUp: true,
    config: {
      url: 'http://localhost:8180/',
      realm: 'default',
      clientId: 'webapp',
    },
    initOptions: {
      onLoad: "login-required",
      pkceMethod: "S256",
      checkLoginIframe: false
    }
  })

  // Auto-refresh token
  keycloak.keycloakEvents$.subscribe({
    next: async (event) => {
      if (event.type === KeycloakEventType.OnTokenExpired) {
        await keycloak.updateToken(20);
        console.log(`Refresing finished. New token: ${await keycloak.getToken()}`);
      }
    },
  })

  return result
}
