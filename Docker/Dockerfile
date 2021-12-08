FROM node:16.13.1
#ENV NODE_ENV=development

ARG USERID
ARG GROUPID

# Run as "root"
USER 0

#RUN useradd -m developer
#USER developer

WORKDIR /source_dir/Docker
ENV HOME /source_dir/Docker

#RUN pwd; ls -l

#COPY ["screenrc", ".screenrc", "./"]
#COPY screenrc, $HOME/.screenrc

RUN apt update && apt -y upgrade && apt install -y screen telnet wget vim sudo

# RUN npm install --development

#COPY .. .

#CMD [ "node", "server.js" ]

# Modify group "developer" to use group ID $GROUPID.
#RUN groupmod -g $GROUPID developer

# Modify user "developer" to group "developer".
#RUN usermod -u $USERID -g $GROUPID developer

# Set the password "default" for user "default". 
#RUN echo "developer:developer" | chpasswd

#RUN echo "%developer ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers

#USER $USERID
