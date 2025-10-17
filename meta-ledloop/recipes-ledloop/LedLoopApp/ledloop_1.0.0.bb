LICENSE = "GNUv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"
FILESEXTRAPATHS:prepend := "${THISDIR}/confs:"

SRC_URI = "git://github.com/Gmatarrubia/app-ledloop.git;protocol=https;branch=main \
           file://ledloop.service \
           file://ledloop-shutdown.service \
           file://figures-mode.json \
           file://led-map.json \
"

SRC_URI:append:debug = "\
    file://example_2.py \
    file://example_3.py \
"

SRCREV = "075d9cd38354c8fb9bb5c6c0be1e40ff53370460"
S = "${WORKDIR}/git"

require conf/include/ledloop-user-common.inc
inherit systemd

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN}:append = " ledloop.service ledloop-shutdown.service "

FILES:${PN} += "${LEDLOOP_APP_PATH}*"
FILES:${PN} += "${systemd_unitdir}/system/ledloop.service"
FILES:${PN} += "${systemd_unitdir}/system/ledloop-shutdown.service"
FILES:${PN} += "/settings/*"

RDEPENDS:${PN} = "\
    rpi-gpio \
    rpio \
    python3-core \
    python3-rpi-ws281x \
    python3-adafruit-circuitpython-neopixel \
    python3-adafruit-platformdetect \
    python3-adafruit-busdevice \
    python3-adafruit-blinka \
    python3-adafruit-pixelbuf \
    python3-adafruit-requests \
    python3-adafruit-pureio \
    python3-pyserial \
    python3-pyzmq \
"

do_install() {
    install -d ${D}${LEDLOOP_APP_PATH}
    install -m755 "${S}"/*.py "${D}${LEDLOOP_APP_PATH}"
    install -m755 "${S}"/*.json "${D}${LEDLOOP_APP_PATH}"

    if [ -f "${WORKDIR}"/led-map.json ] && [ -f "${WORKDIR}"/figures-mode.json ]; then
        bbnote "---Installing custom configuration---"
        install -d "${D}/settings/"
        install -D -m755 "${WORKDIR}"/led-map.json "${D}"/settings/
        install -D -m755 "${WORKDIR}"/figures-mode.json "${D}"/settings/
        ln -s /settings/led-map.json "${D}${LEDLOOP_APP_PATH}"/led-map.json
        ln -s /settings/figures-mode.json "${D}${LEDLOOP_APP_PATH}"/figures-mode.json

        install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants/
        ln -s ${systemd_unitdir}/system/wpa_supplicant@.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/wpa_supplicant-nl80211@wlan0.service
    fi

    # Manage ownership
    chown ${LEDLOOP_USER_NAME}:ledloop -R ${D}${LEDLOOP_APP_PATH}

    # Install systemd stuff
    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/ledloop.service ${D}/${systemd_unitdir}/system/ledloop.service
    install -m 0644 ${WORKDIR}/ledloop-shutdown.service ${D}/${systemd_unitdir}/system/ledloop-shutdown.service
    install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants/
    install -d ${D}${sysconfdir}/systemd/system/shutdown.target.wants/
    ln -s ${systemd_unitdir}/ledloop-shutdown.service ${D}${sysconfdir}/systemd/system/shutdown.target.wants/ledloop-shutdown.service
}

do_install:append:debug() {
    # Install examples
    install -d ${D}${LEDLOOP_APP_PATH}examples/
    install -D -m755 "${WORKDIR}/example_2.py" "${D}${LEDLOOP_APP_PATH}examples/colorful.py"
    install -m755 "${WORKDIR}/example_3.py" "${D}${LEDLOOP_APP_PATH}examples/snake.py"

    # Install test
    install -d ${D}${LEDLOOP_APP_PATH}tests/
    install -D -m755 "${S}"/tests/*.py "${D}${LEDLOOP_APP_PATH}/tests/"
}
