LICENSE = "GNUv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

SRC_URI = "git://github.com/Gmatarrubia/app-ledloop.git;protocol=https;branch=main \
           file://ledloop.service \
           file://ledloop-shutdown.service \
           file://example_2.py \
           file://example_3.py \
           file://off.py \
           "
SRCREV = "e13afc48dae5b8996cb4c083e44c7dca5d7fe87f"
S = "${WORKDIR}/git"

require conf/include/ledloop-user-common.inc
inherit systemd

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN}:append = " ledloop.service ledloop-shutdown.service "

FILES:${PN} += "${LEDLOOP_APP_PATH}*"
FILES:${PN} += "${systemd_unitdir}/system/ledloop.service"
FILES:${PN} += "${systemd_unitdir}/system/ledloop-shutdown.service"

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
    install -m755 "${WORKDIR}/off.py" "${D}${LEDLOOP_APP_PATH}off.py"

    # Install examples
    install -d ${D}${LEDLOOP_APP_PATH}examples/
    install -D -m755 "${WORKDIR}/example_2.py" "${D}${LEDLOOP_APP_PATH}examples/colorful.py"
    install -m755 "${WORKDIR}/example_3.py" "${D}${LEDLOOP_APP_PATH}examples/snake.py"

    # Install test
    install -d ${D}${LEDLOOP_APP_PATH}tests/
    install -D -m755 "${S}"/tests/*.py "${D}${LEDLOOP_APP_PATH}/tests/"

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
