# The ledloop project

This a DIY and open-software project alternative to the commercial Nanoleaf. You will be able to build your own fully customizable LED lights system powered by a Raspberry Pi.
Create your own figures, your own scripts, your own modes, now it is easier than ever. Everything is controlled by an embedded web app hosted in the Raspberry Pi itself.

The LED strips configuration is fully customizable to your needs as well as the operating system configuration. For example, it is quite easy to customize your Wi-Fi settings or the hostname.
The OS is robust and secure becuase it is based in yocto with a huge community with take care about the support and also, it has been design with security in mind, in contrast with anothers 
general-purposed embedded OS.

<div align="center">
  <img src="docs/rainbow.jpg" width="470" hspace="10">
  <img src="docs/app1.jpeg" width="180" hspace="10">
</div>

## Building software

This repo is powered by the yocto project and docker.

```bash
git clone https://github.com/Gmatarrubia/ledloop-yocto.git

#(optional) For your saffety
git update-index --assume-unchange meta-ledloop/recipes-connectivity/wpa-supplicant/files/*

cd ledloop-yocto
./init-docker-env.sh
./build.sh -m rpizw -wi
# use ./build.sh --help for more information
# wait a long time for getting the image generated
```

- Plug a uSD in your PC for writting the generated image you can use bmap tool to write it.

- Disconnect the uSD from the PC, insert it in the raspberry Pi and power on the Raspberry Pi.

### Software Architecture

The project OS image starts a few services at the beginning:

- Wi-Fi service to connect the Raspberry Pi to your network.
- Ledloop service. It is a Python application that reads a json configuration and runs it in the LEDs.
- Fcgiwrap service. A fast CGI service that services the Python scripts as a backend and as an intermediate between the Flutter App and the Python LEDs services.
- Nginx service. It hosts the EWS (application written in Flutter).

## Hardware and schematics

I use a Raspberry Pi Zero W Rev1.3. But any of then can be used. Just make sure you read the [rpi_ws281x library](https://github.com/jgarff/rpi_ws281x) documentation for knowing which GPIOs can be use for the connection.

As a summary you can use any GPIOs with access to some of this devices:

- PCM (GPIO 21 corresponding to physical pin #40 on a Raspberry Pi zero w)
- PWM0 (GPIO 18 corresponding to physical pin #12 on a Raspberry Pi zero w)
- PWM1
- SPI (GPIO 12 corresponding to physical pin # on a Raspberry Pi zerow)

Connect Raspberry Pi's 5V pin with positive of a 5V power source and the Vdd pin of the ws2812b strip.
Connect your choosen GPIO with the Data pin in the strip.
Finally, connect de all the grounds.

<div align="center">
  <img src="docs/wiring.png" width="400" hspace="10">
</div>

## How to use the product: play time


<div align="center">
  <img src="docs/rainbow.jpg" width="400" hspace="10">
</div>

### Configuration

It is based in two json files:

- figures-mode.json
- led-map.json

### Take off sequence

1. Build the system image.
2. Copy the image to an uSD with bmap tool.
1. Power on the Raspberry Pi.
2. Wait until the script makes some flashings.
3. Connect your device (phone/tablet/laptop) to the same Wi-Fi than you previously configured the Raspberry Pi.
4. Open the browser and go to the EWS. By default the host name will be *ledloop.local* so you can try:
[http://ledloop.local](http://ledloop.local)

If everything was successful you might see a similar screen to the following one.

<div align="center">
  <img src="docs/app1.jpeg" width="400" hspace="10">
</div>

## License

This project is licensed under the GNU GPLv3.0 license. See [LICENSE](LICENSE) file for more details.
