#!/bin/bash



vpcName="csye6225-vpc"
internetGateway="csye6225-InternetGateway"
vpcCidrBlock="10.0.0.0/16"
destinationCDRBlock="0.0.0.0/0"

stackName=$1



vpc_id=$(aws ec2 create-vpc --cidr-block "$vpcCidrBlock" --query Vpc.VpcId --output text)

echo "VPC_ID:${vpc_id}"

aws ec2 create-tags --resources "${vpc_id}" --tags Key=Name,Value="${stackName}-${vpcName}"


#Create an Internet Gateway
gate_id=$(aws ec2 create-internet-gateway --query InternetGateway.InternetGatewayId --output text)

echo "Internet_Gateway:${gate_id}"

aws ec2 create-tags --resources "${gate_id}" --tags Key=Name,Value="${stackName}-${gate_id}"



#Using the ID from the previous step, attach the Internet gateway to your VPC.
aws ec2 attach-internet-gateway --vpc-id ${vpc_id} --internet-gateway-id ${gate_id}


#Create a custom route table for your VPC.
route_table_id=$(aws ec2 create-route-table --vpc-id ${vpc_id} --query RouteTable.RouteTableId --output text)

echo "Route_Table:${route_table_id}"

aws ec2 create-tags --resources "${route_table_id}" --tags Key=Name,Value="${stackName}-${route_table_id}"



#Create a route in the route table that points all traffic (0.0.0.0/0) to the Internet gateway.
aws ec2 create-route --route-table-id "${route_table_id}" --destination-cidr-block "${destinationCDRBlock}" --gateway-id "${gate_id}"



#To confirm that your route has been created and is active, you can describe the route table and view the results.
echo $(aws ec2 describe-route-tables --route-table-id "${route_table_id}")

