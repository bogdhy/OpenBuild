CREATE USER openbuild_user;

CREATE DATABASE openbuild;
GRANT ALL PRIVILEGES ON DATABASE openbuild TO openbuild_user;

CREATE DATABASE ob_keycloak;
GRANT ALL PRIVILEGES ON DATABASE ob_keycloak TO openbuild_user;
