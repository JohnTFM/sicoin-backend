## Configurar keycloak HTTP:
- docker compose exec -it keycloak /bin/bash
- ./kcadm.sh config credentials --server http://localhost:8080 --realm master --user admin
- ./kcadm.sh update realms/master -s sslRequired=NONE