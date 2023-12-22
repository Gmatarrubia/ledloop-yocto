# The ledloop project

This a DIY alternative Nanoleaf led lights project. Following these instructions, you will be able to build your own fully customizable LED lights system, which is powered by a Raspberry Pi.
It will be controlled with an embedded web app hosted in the Raspberry Pi itself.

The LED strips configuration is fully customizable to your needs as well as the operating system configuration. For example, it is quite easy to customize your Wi-Fi settings or the hostname.

## Building software

This repo is powered by the yocto project and docker.

```bash
git clone https://github.com/Gmatarrubia/ledloop-yocto.git
cd ledloop-yocto
./init-docker-env.sh
./build.sh -m rspwz -wi
# use ./build.sh --help for more information
# wait a long time for getting the image generated
```

- Plug a uSD in your PC for writting the generated image you can use bmap tool to write it.

- Disconnect the uSD from the PC, insert it in the raspberry Pi and power on the Raspi.


### Software Architecture

The project OS image starts a few services at the beginning:

- Wi-Fi service to connect the Raspberry Pi to your network.
- Ledloop service. It is a Python application that reads a json configuration and runs it in the LEDs.
- Fcgiwrap service. A fast CGI service that services the Python scripts as a backend and as an intermediate between the Flutter App and the Python LEDs services.
- Nginx service. It hosts the EWS (application written in Flutter).

## Hardware and schematics

Coming soon.

## Playing time

Coming soon.

### Configuration

Coming soon.

### How to use the product

Coming soon.
