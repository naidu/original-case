#!/bin/bash -e

export alias ll='ls -l'
export HOME=/source_dir/

wget https://services.gradle.org/distributions/gradle-7.3-bin.zip -P /tmp
unzip -d /opt/gradle /tmp/gradle-*.zip

cat <<EOT > /etc/profile.d/gradle.sh
export GRADLE_HOME=/opt/gradle/gradle-7.3
export PATH=/opt/gradle/gradle-7.3/bin:${PATH}
EOT

chmod +x /etc/profile.d/gradle.sh
source /etc/profile.d/gradle.sh

wget https://download.java.net/java/GA/jdk17.0.1/2a2082e5a09d4267845be086888add4f/12/GPL/openjdk-17.0.1_linux-x64_bin.tar.gz -P /tmp
tar xf /tmp/openjdk-17.0.1_linux-x64_bin.tar.gz -C /opt 
tee /etc/profile.d/jdk.sh <<EOF
export JAVA_HOME=/opt/jdk-17.0.1
export PATH=\$PATH:\$JAVA_HOME/bin
EOF

chmod +x /etc/profile.d/jdk.sh
source /etc/profile.d/jdk.sh

pushd ${HOME}/original-case/trip-planner
# Replace API ip
IPADDR=$(ip r | tail -1 | awk -F' ' '{print $NF}')
sed -i -r 's/(REST_API_SERVER =) .*/\1 "http:\/\/'$IPADDR':8080"/' src/app/data.service.ts
# Build the angular app
npm install --development
npm install -g npm@8.2.0
npm install --save-dev @angular-devkit/build-angular
gradle build
popd 

cp ${HOME}/Docker/screenrc ${HOME}/.screenrc
screen

rm -rf .bash_history .config .screenrc