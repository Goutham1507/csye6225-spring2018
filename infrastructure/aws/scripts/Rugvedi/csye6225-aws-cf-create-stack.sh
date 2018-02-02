#!/bin/bash

#Creating a stack

aws cloudformation create-stack --stack-name stack1 --template-body file:///home/rugvedi/csye6225/dev/csye6225-spring2018/infrastructure/aws/scripts/Rugvedi/csye6225-cf-networking.json
# aws cloudformation validate-template --template-body file:///home/rugvedi/csye6225/dev/csye6225-spring2018/infrastructure/aws/scripts/Rugvedi/csye6225-cf-networking.json
