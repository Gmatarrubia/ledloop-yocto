SUMMARY = "Adafruit-circuitpython-pixelbuf"
HOMEPAGE = "https://github.com/adafruit/Adafruit_CircuitPython_Pixelbuf"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=78a287ed9ac9062fc1aa26a863e5c39d"
SRC_URI[sha256sum] = "6d4516ac64b4e8d270abe183d436c6d983b9022e2c2c1587a494651494ac122b"

PYPI_PACKAGE = "adafruit-circuitpython-pixelbuf"
inherit pypi setuptools3

DEPENDS += "python3-setuptools-scm-native"

RDEPENDS:${PN} += " \
    python3-core \
"
do_install:append() {
    install -d ${D}/usr/lib/python3.10/site-packages/
    install -m 755 ${S}/adafruit_pixelbuf.py ${D}/usr/lib/python3.10/site-packages/adafruit_pixelbuf.py
    #TODO: Install examples install
}
do_configure:prepend() {
cat > ${S}/setup.py <<-EOF
from setuptools import setup

setup()
EOF
}