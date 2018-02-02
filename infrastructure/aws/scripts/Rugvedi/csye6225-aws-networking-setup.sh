#!/bin/bash



#Variables
AWS_REGION="us-east-1"
VPC_NAME="stack1-csye6225-vpc"
VPC_CIDR="10.0.0.0/28"
IG_NAME="stack1-csye6225-InternetGateway"
RT_NAME="stack1-csye6225-public-route-table"



#Creating a vpc resource called stack1-csye6225-vpc
#aws ec2 create-vpc --cidr-block 10.0.0.0/28
#take a note of the vpc id

echo "Creating VPC"
VPC_ID=$(aws ec2 create-vpc --cidr-block $VPC_CIDR --query 'Vpc.{VpcId:VpcId}' --output text --region $AWS_REGION)
echo "VPC ID is '$VPC_ID' "

#Tag

aws ec2 create-tags --resources $VPC_ID --tags "Key=Name, Value=$VPC_NAME" --region $AWS_REGION
echo "VPC ID: '$VPC_ID', VPC NAME: '$VPC_NAME' "

#Creating an Internet Gateway

echo "Creating an Internet Gateway"
IG_ID=$(aws ec2 create-internet-gateway --query 'InternetGateway.{InternetGatewayId:InternetGatewayId}' --output text --region $AWS_REGION)
aws ec2 create-tags --resources $IG_ID --tags "Key=Name, Value=$IG_NAME"
echo "InternetGateway ID: '$IG_ID', InternetGateway NAME: '$IG_NAME' "

#Attaching Internet Gateway to the VPC

echo "Attaching InternetGateway to the VPC"
aws ec2 attach-internet-gateway --vpc-id $VPC_ID --internet-gateway-id $IG_ID --region $AWS_REGION
echo "InternetGateway has been attached to the VPC"

#Creating Route Table
RT_ID=$(aws ec2 create-route-table --vpc-id $VPC_ID --query 'RouteTable.{RouteTableId:RouteTableId}' --output text --region $AWS_REGION)
aws ec2 create-tags --resources $RT_ID --tags "Key=Name, Value=$RT_NAME" 
echo "Route Table ID: '$RT_ID'"


#Creating route to Internet Gateway

R1=$(aws ec2 create-route --route-table-id $RT_ID --destination-cidr-block 0.0.0.0/0 --gateway-id $IG_ID --region $AWS_REGION)
