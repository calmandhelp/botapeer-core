#!/bin/bash

# for debug
source .env
mvn test -Dmaven.surefire.debug -DB_URL=${DB_URL} \
-DB_SCHEMA=${DB_SCHEMA} \
-DSPRING_DATASOURCE_URL=jdbc:mysql://localhost:33061/${DB_SCHEMA} \
-DDB_USERNAME=${DB_USERNAME} \
-DDB_PASSWORD=${DB_PASSWORD} \
-DDB_DRIVER_CLASS_NAME=${DB_DRIVER_CLASS_NAME} \
-DAWS_REGION_STATIC=${AWS_REGION_STATIC} \
-DS3_ACCESS_KEY=${S3_ACCESS_KEY} \
-DS3_SECRET_KEY=${S3_SECRET_KEY} \
-DIMAGE_PATH=${IMAGE_PATH} \
-DJWT_SECRET=${JWT_SECRET} \
-DJWT_EXPIRATION_IN_MS=${JWT_EXPIRATION_IN_MS}