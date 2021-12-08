#!/bin/bash

#docker rmi node-docker

USERID="$(id -u)"
GROUPID="$(id -g)"

#${DOCKER} image build --build-arg USERID=$USERID --build-arg GROUPID=$GROUPID --tag secuserv .
docker image build --tag klm-docker --build-arg USERID=$USERID --build-arg GROUPID=$GROUPID . || exit 1

docker run --rm -v $(pwd)/..:/source_dir -w /source_dir --name trip_planner -it klm-docker /source_dir/Docker/launch_shell.sh

#