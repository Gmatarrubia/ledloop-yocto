SUMMARY = "CircuitPython bus device classes to manage bus sharing."
HOMEPAGE = "https://github.com/adafruit/Adafruit_CircuitPython_BusDevice"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6ec69d6e9e6c85adfb7799d7f8cf044e"
SRC_URI[sha256sum] = "ed06f5552e5567b0c89589c5bc6ef3adcac67d59eb505ce9127af99f33c2bc90"

PYPI_PACKAGE = "adafruit-circuitpython-busdevice"
inherit pypi setuptools3


DEPENDS += "python3-setuptools-scm-native"

RDEPENDS:${PN} += " \
    python3-core \
"
do_install:append() {
    install -d ${D}/usr/lib/python3.10/site-packages/
    cp -r ${S}/adafruit_bus_device ${D}/usr/lib/python3.10/site-packages/
    chmod -R 755 ${D}/usr/lib/python3.10/site-packages/
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
