#!/bin/bash
echo "CREATING STACK"

keyTag='zillow-keys'
echo $keyTag
stackName=$1
stackId=$(aws cloudformation create-stack --stack-name $stackName --template-body file://start.json --parameters ParameterKey=keyTag,ParameterValue=$keyTag --query [StackId] --output text)
echo "#############################"
echo $stackId
echo "#############################"

if [ -z $stackId ]; then
    echo 'Error occurred.Dont proceed. TERMINATED'
else
    aws cloudformation wait stack-create-complete --stack-name $stackId
    echo "STACK CREATION COMPLETE."
fi








