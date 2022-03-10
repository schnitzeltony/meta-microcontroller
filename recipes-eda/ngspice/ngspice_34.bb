SUMMARY = "Open source spice simulator for electric and electronic circuits"
LICENSE = "BSD-3-Clause & GPL-2.0-only & LGPL-2.0-only & LGPL-2.1-only & CC-BY-SA-4.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=86d6c7dc24550918e3c0a3253ba110e2"

SRC_URI = " \
    ${SOURCEFORGE_MIRROR}/project/${BPN}/ng-spice-rework/${PV}/${BPN}-${PV}.tar.gz \
    file://0001-Don-t-kill-build-system-s-CFLAGS-LDFLAGS.patch \
"
SRC_URI[sha256sum] = "2263fffc6694754972af7072ef01cfe62ac790800dad651bc290bfcae79bd7b5"

MIRRORS += "${SOURCEFORGE_MIRROR}/project/${BPN}/ng-spice-rework/${PV} ${SOURCEFORGE_MIRROR}/project/${BPN}/ng-spice-rework/old-releases/${PV}"

DEPENDS = " \
    bison \
    fftw \
    ncurses \
"
# TODO editline

inherit autotools features_check

REQUIRED_DISTRO_FEATURES = "x11"

EXTRA_OECONF = " \
    --disable-rpath \
    --disable-gprof \
    \
    --enable-xspice \
    --enable-cider \
    --enable-openmp \
    --with-ngshared \
    --enable-predictor \
"


