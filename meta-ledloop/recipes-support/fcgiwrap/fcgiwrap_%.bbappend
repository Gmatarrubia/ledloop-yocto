FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "\
    file://fcgiwrap.service \
    file://fcgiwrap-access.service \
"

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN}:append = " fcgiwrap.service fcgiwrap-access.service "

FILES:${PN} += "${LEDLOOP_APP_PATH}*"
FILES:${PN} += "${systemd_unitdir}/system/fcgiwrap.service"
FILES:${PN} += "${systemd_unitdir}/system/fcgiwrap-access.service"


do_install:append() {
      install -d ${D}/${systemd_unitdir}/system
      install -m 0644 ${WORKDIR}/fcgiwrap.service ${D}/${systemd_unitdir}/system/fcgiwrap.service
      install -m 0644 ${WORKDIR}/fcgiwrap-access.service ${D}/${systemd_unitdir}/system/fcgiwrap-access.service
      install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants/
      ln -s ${systemd_unitdir}/fcgiwrap-access.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/fcgiwrap-access.service
}
