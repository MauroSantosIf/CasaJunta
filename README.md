# CasaJunta

App Android da rotina de uma casa compartilhada. O módulo `:app` é Kotlin com Jetpack Compose. O Postgres de desenvolvimento sobe em Docker e recebe o schema pelo Flyway.

O app ainda não lê nem grava nesse banco. A tela atual é o onboarding. As URLs em `.env` ficam prontas para quando a conexão JDBC entrar no projeto.

## Pré-requisitos

- Linux, macOS ou Windows com Docker Engine e Compose v2 (`docker compose version`)
- JDK 21 ou mais novo. O daemon do Gradle está configurado para JDK 25 em `gradle/gradle-daemon-jvm.properties`; se esse JDK não existir, o wrapper pode baixá-lo pelo resolver Foojay
- Android Studio com Android SDK. O projeto usa compileSdk e targetSdk 37, minSdk 24
- Um emulador ou aparelho com API 24 ou superior

## Dependências do app

Versões fixadas em `gradle/libs.versions.toml`. Gradle 9.6.0 vem do wrapper.

| Peça | Versão |
| --- | --- |
| Android Gradle Plugin | 9.4.1, com o Kotlin embutido do AGP |
| Plugin Compose | `org.jetbrains.kotlin.plugin.compose` 2.2.10 |
| Compose BOM | 2026.02.01 |
| `androidx.core:core-ktx` | 1.10.1 |
| `androidx.lifecycle:lifecycle-runtime-ktx` | 2.6.1 |
| `androidx.activity:activity-compose` | 1.8.0 |
| Material 3, UI e tooling | alinhados pelo BOM |

Java do módulo: source e target 11 (`app/build.gradle.kts`).

O banco local não entra no APK. São imagens fixas:

| Imagem | Tag |
| --- | --- |
| `postgres` | `17.11-alpine3.24` |
| `flyway/flyway` | `13.9.0` |

## Banco local

A porta do host é **55432**, para não disputar o 5432 com outros Postgres. Se o `up` falhar com `address already in use`, alguma conexão já está usando 55432 (pode ser a porta de origem de um processo, não só um servidor). Libere a porta e rode de novo.

```bash
cp .env.example .env
```

No `.env`, troque `DB_OWNER_PASSWORD` e `APP_DB_PASSWORD`. Não commite esse arquivo.

```bash
chmod +x gradlew scripts/db.sh
./scripts/db.sh up
./scripts/db.sh test
```

`up` sobe o Postgres, espera o healthcheck e aplica migrations, o callback do login do app e o seed. `test` precisa terminar com `OK — todos os testes do banco passaram`.

| Comando | Efeito |
| --- | --- |
| `./scripts/db.sh up` | Sobe o banco e migra |
| `./scripts/db.sh migrate` | Roda o Flyway de novo. A segunda vez não deve duplicar dados |
| `./scripts/db.sh test` | Testes SQL dentro de uma transação desfeita no fim |
| `./scripts/db.sh psql` | Console como `casajunta_owner` |
| `./scripts/db.sh reset` | Apaga o volume e recria o banco do zero |
| `./scripts/db.sh down` | Para os containers e mantém o volume |

Contas do seed. Senha de todas: `casajunta123`.

| E-mail | Papel na casa |
| --- | --- |
| rafael@casajunta.dev | responsável |
| camila@casajunta.dev | morador |
| juliana@casajunta.dev | morador |
| pedro@casajunta.dev | morador |

A casa é República Mandacaru, código `K7Q2XM`.

O usuário do app (`APP_DB_USER`, padrão `casajunta_android`) lê e grava o conteúdo, não apaga linhas e não acessa a tabela `users`. A senha do dono (`DB_OWNER_PASSWORD`) fica só no Docker e no Flyway.

`APP_DB_URL` usa `10.0.2.2`, que é o host visto de dentro do emulador. `HOST_DB_URL` usa `127.0.0.1` para testes que rodam na máquina. Num celular físico, troque o host de `APP_DB_URL` pelo IP da máquina na rede local; a porta 55432 fica publicada enquanto o container está no ar.

## App Android

Abra a pasta do repositório no Android Studio, espere o sync do Gradle e rode a configuração `app` num emulador.

Pela linha de comando, com o SDK apontado em `local.properties` (`sdk.dir`):

```bash
./gradlew :app:assembleDebug
./gradlew :app:testDebugUnitTest
```

O APK de debug sai em `app/build/outputs/apk/debug/`.

## Schema em outro Postgres

Para aplicar só o schema, sem o seed de desenvolvimento, use o serviço `migrate` com outras variáveis e sem a pasta `db/seed`:

```bash
docker compose run --rm --no-deps \
  -e FLYWAY_URL='jdbc:postgresql://<host>:<porta>/<db>?sslmode=require' \
  -e FLYWAY_USER='<dono>' \
  -e FLYWAY_PASSWORD='<senha>' \
  -e FLYWAY_LOCATIONS='filesystem:/flyway/project/migrations,filesystem:/flyway/project/callbacks' \
  migrate
```

O callback cria o login do app com `APP_DB_USER` e `APP_DB_PASSWORD` do `.env`. Na nuvem, aponte `APP_DB_URL` para esse host e inclua `?sslmode=require` quando o provedor exigir TLS. O formato de usuário de um pooler (por exemplo no Supabase, modo sessão) depende do provedor e fica a verificar antes de usar um papel customizado.
