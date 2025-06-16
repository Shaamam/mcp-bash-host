#!/bin/bash

# Check if Project ID was passed as an argument
if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <ProjectID>"
    exit 1
fi

PROJECT_ID=$1
SERVICE_ACCOUNT_EMAIL="sa-developers@${PROJECT_ID}.iam.gserviceaccount.com"

gcloud config unset auth/impersonate_service_account

gcloud auth login

gcloud config set project $PROJECT_ID

gcloud config set auth/impersonate_service_account $SERVICE_ACCOUNT_EMAIL

gcloud auth application-default login

echo "Impersonation set for project $PROJECT_ID with service account $SERVICE_ACCOUNT_EMAIL"