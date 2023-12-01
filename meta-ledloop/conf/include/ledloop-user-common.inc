
# Common variables
LEDLOOP_USER_NAME = "ledloop"
LEDLOOP_USER_PATH = "/home/${LEDLOOP_USER_NAME}/"
LEDLOOP_APP_PATH="${LEDLOOP_USER_PATH}app-ledloop/"
LEDLOOP_EWS_PATH="${LEDLOOP_USER_PATH}ews-ledloop/"
LEDLOOP_COMM_PATH="${LEDLOOP_USER_PATH}comm-ledloop/"

inherit useradd

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = " \
	--home ${LEDLOOP_USER_PATH} \
	--groups users \
	--user-group ${LEDLOOP_USER_NAME}\
"
