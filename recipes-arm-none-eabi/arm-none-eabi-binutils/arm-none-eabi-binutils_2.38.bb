SUMMARY = "GNU binutils for ARM embedded controllers (arm-none-eabi)"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM="\
    file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552\
    file://COPYING.LIB;md5=9f604d8a4f8e74f4f5140845a21b6674\
    file://COPYING3;md5=d32239bcb673463ab874e80d47fae504\
    file://COPYING3.LIB;md5=6a6a8e020838b23406c81b19c1d46df6\
    file://gas/COPYING;md5=d32239bcb673463ab874e80d47fae504\
    file://include/COPYING;md5=59530bdf33659b29e73d4adb9f9f6552\
    file://include/COPYING3;md5=d32239bcb673463ab874e80d47fae504\
    file://libiberty/COPYING.LIB;md5=a916467b91076e631dd8edb7424769c7\
    file://bfd/COPYING;md5=d32239bcb673463ab874e80d47fae504\
"

inherit autotools gettext texinfo

SRC_URI = "ftp://ftp.gnu.org/pub/gnu/binutils/binutils-${PV}.tar.xz"
SRC_URI[sha256sum] = "e316477a914f567eccc34d5d29785b8b0f5a10208d36bbacedcc39048ecfe024"

S = "${WORKDIR}/binutils-${PV}"

BBCLASSEXTEND = "native"

DEPENDS += "zlib"

EXTRA_OECONF = " \
    --target=arm-none-eabi \
    --disable-werror \
    --with-system-zlib \
"

do_configure () {
    (cd ${S} && gnu-configize)
    oe_runconf
}

do_install:append() {
    # remove some files conflicting with target utils
    rm -rf ${D}/${datadir}/locale
    rm -rf ${D}/${datadir}/info
    rm -rf ${D}/${libdir}/bfd-plugins
    rmdir ${D}/${libdir}
}

FILES:${PN} += "${prefix}/avr"
SYSROOT_DIRS:append:class-native = " ${prefix}/arm-none-eabi"
