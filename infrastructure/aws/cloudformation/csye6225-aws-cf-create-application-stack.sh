#!/bin/bash
echo "CREATING STACK"
stackName=$1

idRsa='id_rsa'

stackId=$(aws cloudformation create-stack --stack-name $stackName --template-body file://csye6225-cf-application.json --parameters ParameterKey=keyTag,ParameterValue=$idRsa --query [StackId] --output text)
echo "#############################"
echo $stackId
echo "#############################"

if [ -z $stackId ]; then
    echo 'Error occurred.Dont proceed. TERMINATED'
else
    aws cloudformation wait stack-create-complete --stack-name $stackId
    echo "STACK CREATION COMPLETE."
fi








