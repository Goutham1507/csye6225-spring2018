#!/bin/bash
echo "Updating Stack"
stack_name=$1
idRsa=$2
echo $stack_name
subnetExportName1="csye6225-devesh-Networking-db-subnet1Id"
subnetExportName2="csye6225-devesh-Networking-db-subnet2Id"
stackId=$(aws cloudformation create-stack --stack-name $stack_name --template-body file://csye6225-cf-application.json --parameters ParameterKey=subnetExportName1,ParameterValue=$subnetExportName1 ParameterKey=subnetExportName2,ParameterValue=$subnetExportName2 ParameterKey=keyTag,ParameterValue=$idRsa --query [StackId] --output text)
echo "#############################"
echo $stackId
echo "#############################"

if [ -z $stackId ]; then
    echo 'Error occurred.Dont proceed. TERMINATED'
else
    aws cloudformation wait stack-create-complete --stack-name $stack_name
    echo "STACK CREATION COMPLETE."
fi







