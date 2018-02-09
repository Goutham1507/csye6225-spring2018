#!/bin/bash
echo "DELETING STACK"
stack_name=$1

idRsa=$2

aws cloudformation update-stack --stack-name $stack_name --template-body file://csye6225-cf-application-update.json --parameters ParameterKey=keyTag,ParameterValue=$idRsa
aws cloudformation wait stack-update-complete --stack-name $stack_name
aws cloudformation delete-stack --stack-name $stack_name
aws cloudformation wait stack-delete-complete --stack-name $stack_name


echo "STACK TERMINATED SUCCESSFULLY"