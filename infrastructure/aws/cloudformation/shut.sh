#!/bin/bash
echo "DELETING STACK"
stackName=$1

keyTag='zillow-keys'
stackId=$(aws cloudformation update-stack --stack-name $stackName --template-body file://update-stack.json --parameters ParameterKey=keyTag,ParameterValue=$keyTag --query [StackId] --output text)
echo "#############################"
echo $stackId
echo "#############################"

if [ -z $stackId ]; then
    echo 'Error occurred.Dont proceed. TERMINATED'
else
    aws cloudformation wait stack-update-complete --stack-name $stackId
    echo "STACK UPDATE COMPLETE."
fi



echo 'DELETING STACK NOW!'
aws cloudformation delete-stack --stack-name $stackName

aws cloudformation wait stack-delete-complete --stack-name $stackName


echo "STACK TERMINATED SUCCESSFULLY"