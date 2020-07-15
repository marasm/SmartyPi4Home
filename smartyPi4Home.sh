#!/bin/bash

echo Making sure the current working directory is correct
cd "$(dirname "$0")" 

export WIRINGPI_GPIOMEM=1

echo Getting latest version from GitHub...
git pull
git submodule update --recursive --checkout --force --init

echo Launching...
gradle execute
#nohup gradle execute > /dev/null 2>&1 &
