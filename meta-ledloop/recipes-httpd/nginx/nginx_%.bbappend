FILESEXTRAPATHS:prepend:debug := "${THISDIR}/files:"

do_install:append() {
    ln -s /home/root/led-map.json ${D}${sysconfdir}/nginx/sites-available/led-map.json
    ln -s /home/root/work-mode.json ${D}${sysconfdir}/nginx/sites-available/work-mode.json
}