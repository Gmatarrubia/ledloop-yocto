SUMMARY = "EWS for Ledloop project"
DESCRIPTION = "Embedded web server for Ledloop project control"
AUTHOR = "Gonzalo Matarrubia Gonzalez"
HOMEPAGE = "https://github.com/Gmatarrubia/ledloop-ews"
SECTION = "web"
LICENSE = "CLOSED"

inherit flutter-web
require conf/include/ledloop-user-common.inc

RDEPENDS:${PN} += "nginx python3-core"

SRCREV = "ecd3cab85275862514393ca256ae630cd723b985"
SRC_URI = "git://github.com/Gmatarrubia/ledloop-ews.git;protocol=https;branch=main"

S = "${WORKDIR}/git"

PUBSPEC_APPNAME = "ews_ledloop"
FLUTTER_APP_RUNTIME_MODES = "release"
FLUTTER_INSTALL_DIR := "${LEDLOOP_EWS_PATH}"

RDEPENDS:${PN} = "comm-ledloop"

do_install:append() {
    # Create soft links for accessing backend json files
    ln -s ${LEDLOOP_COMM_PATH} "${D}${FLUTTER_INSTALL_DIR}scripts"

    # Asigning appropriate user and group
    chown ${LEDLOOP_USER_NAME}:users -R ${D}${FLUTTER_INSTALL_DIR}
}
