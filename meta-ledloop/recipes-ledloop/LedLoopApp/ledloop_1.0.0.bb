LICENSE = "GNUv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

SRC_URI = "git://github.com/Gmatarrubia/rpi-zw-led-loop.git;protocol=https;branch=main \
           file://ledloop.service \
           file://ledloop-shutdown.service \
           file://example_2.py \
           file://example_3.py \
           file://off.py \
           "
SRCREV = "7051e837dbadc4263a44af775787e21dad7f75e4"
S = "${WORKDIR}/git"

inherit systemd

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN}:append = " ledloop.service ledloop-shutdown.service "

FILES:${PN} += " home/root/*"
FILES:${PN} += "${systemd_unitdir}/system/ledloop.service"
FILES:${PN} += "${systemd_unitdir}/system/ledloop-shutdown.service"

RDEPENDS:${PN} = "\
    rpi-gpio \
    rpio \
    python3-core \
    python3-rpi-ws281x \
    python3-adafruit-circuitpython-neopixel \
    python3-adafruit-platformdetect \
    python3-adafruit-busdevice \
    python3-adafruit-blinka \
    python3-adafruit-pixelbuf \
    python3-adafruit-requests \
    python3-adafruit-pureio \
    python3-pyserial \
    python3-pyzmq \
"

do_install() {
      APP_ROOT="/home/root/ledloop/"
      install -d ${D}${APP_ROOT}
      install -m755 "${S}"/*.py "${D}${APP_ROOT}"
      install -m755 "${S}"/*.json "${D}${APP_ROOT}"
      install -D -m755 "${WORKDIR}/example_2.py" "${D}${APP_ROOT}example_2.py"
      install -m755 "${WORKDIR}/example_3.py" "${D}${APP_ROOT}example_3.py"
      install -m755 "${WORKDIR}/off.py" "${D}${APP_ROOT}off.py"

      install -d ${D}/${systemd_unitdir}/system
      install -m 0644 ${WORKDIR}/ledloop.service ${D}/${systemd_unitdir}/system/ledloop.service
      install -m 0644 ${WORKDIR}/ledloop-shutdown.service ${D}/${systemd_unitdir}/system/ledloop-shutdown.service
      install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants/
      install -d ${D}${sysconfdir}/systemd/system/shutdown.target.wants/
      ln -s ${systemd_unitdir}/ledloop-shutdown.service ${D}${sysconfdir}/systemd/system/shutdown.target.wants/ledloop-shutdown.service
}
