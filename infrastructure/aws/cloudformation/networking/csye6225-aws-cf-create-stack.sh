#!/bin/bash
echo "CREATING STACK"
stackName=$1
csye_const=csye6225
vpc_const=vpc
ig_const=InternetGateway
route_table_const=route-table

vpcTag=$stackName$csye_const$vpc_const
echo $vpcTag
stackId=$(aws cloudformation create-stack --stack-name $stackName --template-body file://csye6225-cf-networking.json --parameters ParameterKey=vpcTag,ParameterValue=$vpcTag ParameterKey=igTag,ParameterValue=stackName$csye_const$ig_const ParameterKey=routeTableTag,ParameterValue=$stackName$csye_const$route_table_const --query [StackId] --output text)
echo "#############################"
echo $stackId
echo "#############################"

if [ -z $stackId ]; then
    echo 'Error occurred.Dont proceed. TERMINATED'
else
    aws cloudformation wait stack-create-complete --stack-name $stackId
    echo "STACK CREATION COMPLETE."
fi








