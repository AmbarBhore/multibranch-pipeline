#!/bin/bash
set -e

node_ips=["192.168.0.106","192.168.0.109"]
node_port="30672"

for ip in "${node_ips[@]}"; do
	result="http://${ip}:${node_port}/actuator/health"
	echo "Testing ${result}..."
	if curl -sf "$result"; then
		echo "Smoke tests are passed on $ip"
		exit 0
	fi
done

echo "smoke tests are passed"
exit 1
