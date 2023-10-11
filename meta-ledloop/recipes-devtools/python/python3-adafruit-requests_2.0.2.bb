SUMMARY = "Pure python (i.e. no native extensions) access to Linux IO    including I2C and SPI. Drop in replacement for smbus and spidev modules."
HOMEPAGE = "https://github.com/adafruit/Adafruit_CircuitPython_Requests"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=66e022c238fbc8444a4a5166aed6ccb2"
SRC_URI[sha256sum] = "fec34d21be9d721a44bd1471e3651630eb7d394ce806de2fb39536dac73408aa"

PYPI_PACKAGE = "adafruit-circuitpython-requests"
inherit pypi setuptools3

DEPENDS += "python3-setuptools-scm-native"

RDEPENDS:${PN} += " \
    python3-core \
"
FILES:${PN} += "/usr/lib/python3.10/site-packages/adafruit_requests.py"

do_install:append() {
    install -d ${D}/usr/lib/python3.10/site-packages/
    install -m 755 ${S}/adafruit_requests.py ${D}/usr/lib/python3.10/site-packages/adafruit_requests.py
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
