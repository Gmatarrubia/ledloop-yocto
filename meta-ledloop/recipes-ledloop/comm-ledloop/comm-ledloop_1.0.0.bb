LICENSE = "CLOSED"

SRC_URI = "file://comm-ledloop.py \
           file://set-config.py \
           file://get-config.py \
           file://get-info.py \
           file:/get-map.py \
           file://test.py \
           "

require conf/include/ledloop-user-common.inc

FILES:${PN} += "${LEDLOOP_COMM_PATH}*"

RDEPENDS:${PN} = "\
    python3-core \
    ews-ledloop \
    ledloop \
"

do_install() {
    install -d "${D}${LEDLOOP_COMM_PATH}"
    install -m755 "${WORKDIR}"/*.py "${D}${LEDLOOP_COMM_PATH}"

    # Create soft links for accessing backend json files
    ln -s ${LEDLOOP_APP_PATH}led-map.json "${D}${LEDLOOP_COMM_PATH}led-map.json"
    ln -s ${LEDLOOP_APP_PATH}work-mode.json "${D}${LEDLOOP_COMM_PATH}work-mode.json"
    ln -s ${LEDLOOP_APP_PATH}work-mode.json "${D}${LEDLOOP_COMM_PATH}figures-mode.json"

    # Manage ownership
    chown ${LEDLOOP_USER_NAME}:${LEDLOOP_USER_NAME} -R ${D}${LEDLOOP_COMM_PATH}
}
