SUMMARY = "An implementation of the Debug Adapter Protocol for Python 3"
LICENSE = "MIT & EPL-1.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=18496e0e5a38c5caaade8503120ef9ea \
                    file://src/debugpy/_vendored/pydevd/LICENSE;md5=30b3836521b3d65bef598bbc358a3afa \
                    file://src/debugpy/_vendored/pydevd/_pydevd_frame_eval/vendored/bytecode-0.13.0.dev0.dist-info/COPYING;md5=0b470642172a83f6cf504fba28bac72f"
SRCREV = "2ac9538dd5d59d0bb582d445e4a6b87663cfbab4"

SRC_URI = "git://git@github.com/microsoft/debugpy.git;protocol=ssh;branch=main \
           file://0001-arch-use-generic-yocto-architecture-suffix.patch \
           "

S = "${WORKDIR}/git"

inherit setuptools3

EXTRA_CXXFLAGS += "-fPIC -nostartfiles"

do_compile:append() {
    ${CC} -o ${S}/src/debugpy/_vendored/pydevd/pydevd_attach_to_process/attach_linux_yocto.so ${S}/src/debugpy/_vendored/pydevd/pydevd_attach_to_process/linux_and_mac/attach.cpp ${CXXFLAGS} ${EXTRA_CXXFLAGS} ${LDFLAGS} -shared
}


do_install:append() {
    install -m 0755 ${S}/src/debugpy/_vendored/pydevd/pydevd_attach_to_process/attach_linux_yocto.so ${D}${libdir}/${PYTHON_DIR}/site-packages/debugpy/_vendored/pydevd/pydevd_attach_to_process/
    rm -f ${D}${libdir}/${PYTHON_DIR}/site-packages/debugpy/_vendored/pydevd/pydevd_attach_to_process/attach_linux_amd64.so
}

RDEPENDS:${PN}:append = " \
    gdb \
    python3-core \
    python3-ctypes \
    python3-distutils \
    python3-io \
    python3-json \
    python3-math \
    python3-misc \
    python3-multiprocessing \
    python3-numbers \
    python3-pickle \
    python3-setuptools \
    python3-unixadmin \
    python3-xmlrpc \
"
