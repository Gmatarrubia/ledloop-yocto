SUMMARY = "Adafruit-Blinka"
HOMEPAGE = "https://github.com/adafruit/Adafruit_Blinka"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fccd531dce4b989c05173925f0bbb76c"

SRC_URI[sha256sum] = "2cc5e301439a0381b7cf8149fba8ab9c9b329eebf20322642f53411009d2c1bb"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://no-pulseio.patch \
"

S = "${WORKDIR}/git"

PYPI_PACKAGE = "Adafruit-Blinka"
inherit pypi setuptools3

DEPENDS += "python3-setuptools-scm-native"

RDEPENDS:${PN} += " python3-core"
COMPATIBLE_MACHINE = "^rpi$"
CFLAGS += "-fcommon"

