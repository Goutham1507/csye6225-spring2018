#!/bin/bash
echo "DELETING STACK"
stack_name=$1

idRsa='id_rsa'

aws cloudformation update-stack --stack-name $stack_name --template-body file://csye6225-cf-application.jsonupdate.json --parameters ParameterKey=keyTag,ParameterValue=$idRsa
aws cloudformation wait stack-update-complete --stack-name $stack_name
aws cloudformation delete-stack --stack-name $stack_name
aws cloudformation wait stack-delete-complete --stack-name $stack_name


echo "STACK TERMINATED SUCCESSFULLY"