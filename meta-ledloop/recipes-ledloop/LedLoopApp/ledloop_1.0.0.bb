LICENSE = "closed"

RDEPENDS:${PN} = "\
    rpi-gpio \
    rpio \
    python3-rpi-ws281x \
    python3-adafruit-circuitpython-neopixel \
    python3-adafruit-platformdetect \
    python3-adafruit-busdevice \
    python3-adafruit-blinka \
    python3-adafruit-pixelbuf \
    python3-adafruit-requests \
    python3-adafruit-pureio \
    python3-pyserial \
"


SRC_URI = "\
            file://test.py \
            file://test2.py \
            file://test3.py \
"

FILES:${PN} = " \
            file://test.py \
            file://test2.py \
            file://test3.py \
"

do_install() {
      install -d ${D}/root
      install -m755 "${WORKDIR}/test.py" "${D}/root/"
      install -m755 "${WORKDIR}/test2.py" "${D}/root/"
      install -m755 "${WORKDIR}/test3.py" "${D}/root/"
}
