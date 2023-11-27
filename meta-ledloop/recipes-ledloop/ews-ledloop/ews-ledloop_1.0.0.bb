SUMMARY = "EWS for Ledloop project"
DESCRIPTION = "Embedded web server for Ledloop project control"
AUTHOR = "Gonzalo Matarrubia Gonzalez"
HOMEPAGE = "https://github.com/Gmatarrubia/ledloop-ews"
SECTION = "web"
LICENSE = "CLOSED"

inherit flutter-web

require conf/include/ledloop-user-common.inc
inherit useradd

SRC_URI = "git://github.com/Gmatarrubia/ledloop-ews.git;protocol=https;branch=main"
SRCREV = "08ad112bb9c5d8da5809c7047d73ba73a3d86ecc"

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
    ln -s ${LEDLOOP_USER_PATH}app-ledloop/led-map.json "${D}${FLUTTER_INSTALL_DIR}/led-map.json"
    ln -s ${LEDLOOP_USER_PATH}app-ledloop/work-mode.json "${D}${FLUTTER_INSTALL_DIR}/work-mode.json"

    # Asigning appropriate user and group
    chown ${LEDLOOP_USER_NAME}:users -R ${D}${FLUTTER_INSTALL_DIR}
}
