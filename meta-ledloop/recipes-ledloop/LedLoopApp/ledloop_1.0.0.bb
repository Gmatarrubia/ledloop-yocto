LICENSE = "GNUv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

SRC_URI = "\
    git://github.com/Gmatarrubia/rpi-zw-led-loop.git;protocol=https;branch=main \
    file://ledloop.service \
    file://example_2.py \
    file://example_3.py \
    file://off.py \
    file://send_off.py \
"
SRCREV = "25cd7a2228db14cb9b89ecef67aaba6179775be3"
S = "${WORKDIR}/git"

inherit systemd

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN}:append = " ledloop.service "

FILES:${PN} += " home/root/*"
FILES:${PN} += "${systemd_unitdir}/system/ledloop.service"

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
      install -d ${D}/home/root/
      install -m755 "${S}/ledloop.py" "${D}/home/root/ledloop.py"
      install -m755 "${WORKDIR}/example_2.py" "${D}/home/root/example_2.py"
      install -m755 "${WORKDIR}/example_3.py" "${D}/home/root/example_3.py"
      install -m755 "${WORKDIR}/off.py" "${D}/home/root/off.py"
      install -m755 "${WORKDIR}/send_off.py" "${D}/home/root/send_off.py"

      install -d ${D}/${systemd_unitdir}/system
      install -m 0644 ${WORKDIR}/ledloop.service ${D}/${systemd_unitdir}/system/ledloop.service
      install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants/
}
