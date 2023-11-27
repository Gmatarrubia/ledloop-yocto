SUMMARY = "EWS for Ledloop project"
DESCRIPTION = "Embedded web server for Ledloop project control"
AUTHOR = "Gonzalo Matarrubia Gonzalez"
HOMEPAGE = "https://github.com/Gmatarrubia/ledloop-ews"
SECTION = "web"
LICENSE = "CLOSED"

inherit flutter-web

SRC_URI = "git://github.com/Gmatarrubia/ledloop-ews.git;protocol=https;branch=main"
SRCREV = "5c1d3fa4470385aab413a143c25704f944081f62"

S = "${WORKDIR}/git"

PUBSPEC_APPNAME = "ews_ledloop"
FLUTTER_APP_RUNTIME_MODES = "release"
FLUTTER_INSTALL_DIR := "/home/root/ews-ledloop"

do_install:append() {
    ln -s /home/root/led-map.json ${D}${FLUTTER_INSTALL_DIR}/led-map.json
    ln -s /home/root/work-mode.json ${D}${FLUTTER_INSTALL_DIR}/work-mode.json
}