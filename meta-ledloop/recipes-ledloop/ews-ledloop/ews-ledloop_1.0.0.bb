SUMMARY = "EWS for Ledloop project"
DESCRIPTION = "Embedded web server for Ledloop project control"
AUTHOR = "Gonzalo Matarrubia Gonzalez"
HOMEPAGE = "https://github.com/Gmatarrubia/ledloop-ews"
SECTION = "web"
LICENSE = "CLOSED"

inherit flutter-web

SRC_URI = "git://github.com/Gmatarrubia/ledloop-ews.git;protocol=https;branch=main"
SRCREV = "08ad112bb9c5d8da5809c7047d73ba73a3d86ecc"

S = "${WORKDIR}/git"

PUBSPEC_APPNAME = "ews_ledloop"
FLUTTER_APP_RUNTIME_MODES = "release"
FLUTTER_INSTALL_DIR := "/home/root/ews-ledloop"
