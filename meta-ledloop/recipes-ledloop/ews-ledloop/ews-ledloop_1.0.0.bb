SUMMARY = "EWS for Ledloop project"
DESCRIPTION = "Embedded web server for Ledloop project control"
AUTHOR = "Gonzalo Matarrubia Gonzalez"
HOMEPAGE = "https://github.com/Gmatarrubia/ledloop-ews"
SECTION = "web"
LICENSE = "CLOSED"

inherit flutter-web

require conf/include/ledloop-user-common.inc
inherit useradd

RDEPENDS:${PN} += "nginx python3-core"

FILESEXTRAPATHS:prepend := "${THISDIR}/scripts:"
SRCREV = "cedbf7899caab604986de15a0e8d70d23007ec02"
SRC_URI = " \
    git://github.com/Gmatarrubia/ledloop-ews.git;protocol=https;branch=main \
    file://test.py \
    file://test-simple.py \
"


S = "${WORKDIR}/git"

PUBSPEC_APPNAME = "ews_ledloop"
FLUTTER_APP_RUNTIME_MODES = "release"
FLUTTER_INSTALL_DIR := "${LEDLOOP_EWS_PATH}"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = " \
	--home ${LEDLOOP_USER_PATH} \
	--groups users \
	--user-group ${LEDLOOP_USER_NAME}\
"

do_install:append() {
    ln -s ${LEDLOOP_USER_PATH}app-ledloop/led-map.json "${D}${FLUTTER_INSTALL_DIR}assets/led-map.json"
    ln -s ${LEDLOOP_USER_PATH}app-ledloop/work-mode.json "${D}${FLUTTER_INSTALL_DIR}assets/work-mode.json"

    install -d "${D}/${FLUTTER_INSTALL_DIR}scripts"
    install -D -m755 "${WORKDIR}/test.py" "${D}${FLUTTER_INSTALL_DIR}scripts/test.py"
    install -D -m755 "${WORKDIR}/test-simple.py" "${D}${FLUTTER_INSTALL_DIR}scripts/test-simple.py"
    # Asigning appropriate user and group
    chown ${LEDLOOP_USER_NAME}:users -R ${D}${FLUTTER_INSTALL_DIR}
}
