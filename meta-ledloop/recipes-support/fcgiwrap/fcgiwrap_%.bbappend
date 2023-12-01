FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "\
    file://fcgiwrap.service \
"

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN}:append = " fcgiwrap.service "

FILES:${PN} += "${LEDLOOP_APP_PATH}*"
FILES:${PN} += "${systemd_unitdir}/system/fcgiwrap.service"

do_install:append() {
      install -d ${D}/${systemd_unitdir}/system
      install -m 0644 ${WORKDIR}/fcgiwrap.service ${D}/${systemd_unitdir}/system/fcgiwrap.service
      install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants/
}
