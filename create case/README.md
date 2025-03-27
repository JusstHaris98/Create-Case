## Setup Secrets

1. Copy the template files and rename them:
   ```bash
   cp src/main/resources/serviceAccount.example.json src/main/resources/serviceAccount.json
   cp src/main/resources/application.example.properties src/main/resources/application-prod.properties
   ```

2. Update `serviceAccount.json` with your actual Google Cloud service account credentials

3. Update `application-prod.properties` with your production configuration 