SUMMARY = "Small Device C Compiler"
HOMEPAGE = "http://sdcc.sourceforge.net/"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=5574c6965ae5f583e55880e397fbb018"

PV = "4.0.0"
SRC_URI = " \
    ${SOURCEFORGE_MIRROR}/project/${BPN}/${BPN}/${PV}/${BPN}-src-${PV}.tar.bz2 \
    file://0001-Do-not-check-matching-autoconf-version.patch \
    file://0002-multiple-configure.ac-Fix-many-subdir-warnings.patch \
"
# for debugging add
#   file://0000-Supported-device-check-verbose-output.patch 
SRC_URI_append_class-target = " \
    file://0003-Use-native-sdcc-tools.patch \
"

SRC_URI[sha256sum] = "489180806fc20a3911ba4cf5ccaf1875b68910d7aed3f401bbd0695b0bef4e10"

DEPENDS = " \
    bison-native \
    gputils-native \
    boost \
    zlib \
"
DEPENDS_append_class-target = "${BPN}-native"

inherit autotools

# --enable-doc requires lyx
EXTRA_OECONF = " \
    --enable-pdk16-port \
    --disable-bootstrap \
"

# check avr-binutils for a verbose explanation on this
lcl_maybe_fortify = ""

do_configure () {
    export STRIP=echo
    (cd ${S} && gnu-configize)
    oe_runconf
}

RDEPENDS_${PN} = "gputils"

BBCLASSEXTEND = "native"
