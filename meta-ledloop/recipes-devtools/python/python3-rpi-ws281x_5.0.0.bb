DESCRIPTION = "A module to control "
HOMEPAGE = "https://github.com/rpi-ws281x/rpi-ws281x-python/"
SECTION = "devel/python"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://LICENSE;md5=06d9cc0de5275c6ec26cc02bd7b82b5a"

PYPI_PACKAGE = "rpi-ws281x"

S = "${WORKDIR}/git"
inherit setuptools3
SETUPTOOLS_SETUP_PATH = "${S}/library"

SRC_URI = "gitsm://github.com/rpi-ws281x/rpi-ws281x-python.git;protocol=https;branch=master"
SRCREV = "95a43d61421ffe43f9ed77034df7d5f9c00f27ed"

COMPATIBLE_MACHINE = "^rpi$"
CFLAGS += "-fcommon"
