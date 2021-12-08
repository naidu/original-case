FROM node:16.13.1

ARG USERID
ARG GROUPID

# Run as "root"
USER 0

WORKDIR /source_dir/Docker
ENV HOME /source_dir/Docker

RUN apt update && apt -y upgrade && apt install -y screen telnet wget vim sudo

