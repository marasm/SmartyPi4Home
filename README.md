# Software pre-requitites
Need git, java 8+, gradle 7+ and wiringpi

# OS Setup
Using `raspi-config` disable all interface options except I2C since that is needed for the LCD. The rest need to be disabled to prevent them from interfering with the PI4J GPIO provisioning

# App configuration
since the app needs to run as root to have access to GPIO all the configuration files need to be added to the root user home folder: `/root/.smartyPi4Home`. Here are the files that are needed:
- aws.properties
- mqtt.properties
- aws certificate
- aws private key
- aws public key

# Application install
clone the repo into `/home/pi/SmartyPi4Home`

# Automatic start on boot
add the following entries to the `/etc/rc.local` before the `exit 0` command
```
#reset GPIO pins
sudo bash /home/pi/SmartyPi4Home/reset_pins.sh > /home/pi/SmartyPi4Home/logs/console.log

#start the smartyPi
#sudo bash /home/pi/SmartyPi4Home/smartyPi4Home.sh >> /home/pi/SmartyPi4Home/logs/console.log
```

