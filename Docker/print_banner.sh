#!/bin/bash 
i=0
while :
do
  for s in / - \\ \| 
  do 
    printf "\rWaiting for \033[0;31mspring bootRun\033[0m to finish $s"
    sleep 1
    ((i++))
    if [[ $i -gt 60 ]]
    then 
      break 2
    fi
  done
done

IPADDR=$(ip r | tail -1 | awk -F' ' '{print $NF}')

echo 
echo
echo "********************************************"
echo "Server listening on http://$IPADDR:8080"
echo "Web server listening on http://$IPADDR:9000"
echo "********************************************"
echo 
echo

/bin/bash 
