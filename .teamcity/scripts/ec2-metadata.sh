#!/bin/bash
#
# Copyright 2024 the original author or authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

# This scripts detects builds running on EC2 by accessing the special ip 169.254.169.254 exposed by AWS instances.
# https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-instance-metadata.html

# In case of running on EC2
# - we add a TeamCity tag to the build with an instance type of the EC2 instance.

curl -m 1 -s "http://169.254.169.254/latest/meta-data/instance-id"
IS_EC2_INSTANCE=$?
if [ $IS_EC2_INSTANCE -ne 0 ]; then
  echo "Not running on an EC2 instance, skipping the configuration"
  exit 0
fi

EC2_INSTANCE_TYPE=$(curl -s "http://169.254.169.254/latest/meta-data/instance-type")
echo "##teamcity[addBuildTag '$EC2_INSTANCE_TYPE']"
