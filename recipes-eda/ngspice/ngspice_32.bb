SUMMARY = "Open source spice simulator for electric and electronic circuits"
LICENSE = "BSD-3-Clause & GPLv2 & LGPLv2 & LGPLv2.1 & CC-BY-SA-4.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=0710b2d77312959f15046743b7378bc1"

SRC_URI = " \
    ${SOURCEFORGE_MIRROR}/project/${BPN}/ng-spice-rework/${PV}/${BPN}-${PV}.tar.gz \
    file://0001-Don-t-kill-build-system-s-CFLAGS-LDFLAGS.patch \
"
SRC_URI[sha256sum] = "3cd90c4e94516d87c5b4d02a3a6405b1136b25d05c871d4fee1fd7c4c0d03ef2"

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


