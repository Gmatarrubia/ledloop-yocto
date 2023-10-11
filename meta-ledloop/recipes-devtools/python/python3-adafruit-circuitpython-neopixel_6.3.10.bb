SUMMARY = "Python library for controlling NeoPixels"
DESCRIPTION = "A Python library for controlling NeoPixels (WS2812) LEDs."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=04598686f1598e808b1e56f5b2fcb94f"
HOMEPAGE = "https://github.com/adafruit/Adafruit_CircuitPython_NeoPixel"

SRC_URI[sha256sum] = "184db9f6ccb05484726d788cc4934cd6f095f94cf60be01d768f35a8f11f2a3c"

PYPI_PACKAGE = "adafruit-circuitpython-neopixel"

inherit pypi setuptools3

RDEPENDS_${PN} = "python3-core"

do_install:append() {
    install -d ${D}/usr/lib/python3.10/site-packages/
    install -m 664 ${WORKDIR}/adafruit-circuitpython-neopixel-6.3.10/neopixel.py ${D}/usr/lib/python3.10/site-packages/neopixel.py
    #TODO: Install examples install
}
do_configure:prepend() {
cat > ${S}/setup.py <<-EOF
from setuptools import setup, find_packages

setup(
    name="${PN}",
    version="${PV}",
)
EOF
}
