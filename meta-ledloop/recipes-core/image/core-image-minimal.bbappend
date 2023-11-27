require conf/include/ledloop-user-common.inc

inherit extrausers

EXTRA_USERS_PARAMS = " \
	groupadd -g 1011 users;\
	useradd \
	-u 1010 \
	--home ${LEDLOOP_USER_PATH} \
	--groups users \
	--user-group ${LEDLOOP_USER_NAME};\
"
