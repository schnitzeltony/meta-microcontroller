SUMMARY = "GPUTILS is a set of tools for the Microchip (TM) PIC controllers"
HOMEPAGE = "https://gputils.sourceforge.io/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=5574c6965ae5f583e55880e397fbb018"

PV = "1.5.0"
SRC_URI = " \
    ${SOURCEFORGE_MIRROR}/project/${BPN}/${BPN}/${PV}/${BPN}-${PV}-1.tar.bz2 \
    file://0001-Do-not-reset-out-CFLAGS-LDFLAGS.patch \
"
SRC_URI[sha256sum] = "6f88a018e85717b57a22f27a0ca41b2157633a82351f7755be92e2d7dc40bb14"

DEPENDS = " \
    bison-native \
"

inherit autotools

do_install:append:class-native() {
    create_wrapper ${D}/${bindir}/gpasm \
        GPUTILS_HEADER_PATH=${STAGING_DATADIR_NATIVE}/gputils/header \
        GPUTILS_LIB_PATH=${STAGING_DATADIR_NATIVE}/gputils/lib \
        GPUTILS_LKR_PATH=${STAGING_DATADIR_NATIVE}/gputils/lkr
    create_wrapper ${D}/${bindir}/gplink \
        GPUTILS_HEADER_PATH=${STAGING_DATADIR_NATIVE}/gputils/header \
        GPUTILS_LIB_PATH=${STAGING_DATADIR_NATIVE}/gputils/lib \
        GPUTILS_LKR_PATH=${STAGING_DATADIR_NATIVE}/gputils/lkr
}

BBCLASSEXTEND = "native"
