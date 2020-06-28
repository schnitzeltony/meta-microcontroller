SUMMARY = "GNU binutils for Microchip (formerly Atmel) AVR microcontrollers"
LICENSE = "GPLv2+"
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

SRC_URI = " \
    ftp://ftp.gnu.org/pub/gnu/binutils/binutils-${PV}.tar.xz \
    file://avr-size.patch \
"
SRC_URI[md5sum] = "664ec3a2df7805ed3464639aaae332d6"
SRC_URI[sha256sum] = "f00b0e8803dc9bab1e2165bd568528135be734df3fabf8d0161828cd56028952"

S = "${WORKDIR}/binutils-${PV}"

BBCLASSEXTEND = "native"

EXTRA_OECONF = "--target=avr"

do_configure () {
	(cd ${S} && gnu-configize)

	oe_runconf
    #
    # must prime config.cache to ensure the build of libiberty
    #
	mkdir -p ${B}/build-${BUILD_SYS}
	for i in ${CONFIG_SITE}; do
		cat $i >> ${B}/build-${BUILD_SYS}/config.cache || true
	done
}

do_install_append() {
    # remove some files conflicting with target utils
    rm -rf ${D}/${datadir}/locale
    rm -rf ${D}/${datadir}/info
}

FILES_${PN} += "${prefix}/avr"
SYSROOT_DIRS_append_class-native = " ${prefix}/avr"


