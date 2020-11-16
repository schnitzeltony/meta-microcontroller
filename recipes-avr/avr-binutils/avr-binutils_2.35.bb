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
SRC_URI[md5sum] = "fc8d55e2f6096de8ff8171173b6f5087"
SRC_URI[sha256sum] = "1b11659fb49e20e18db460d44485f09442c8c56d5df165de9461eb09c8302f85"

S = "${WORKDIR}/binutils-${PV}"

BBCLASSEXTEND = "native"

# during compile libiberty is configured but fails finding limits.h:
#
# | configure:5290: checking for limits.h
# | ...
# | <...>/recipe-sysroot/usr/include/features.h:397:4: warning: #warning _FORTIFY_SOURCE requires compiling with optimization (-O) [-Wcpp]
# |  397 | #  warning _FORTIFY_SOURCE requires compiling with optimization (-O)
#        |    ^~~~~~~
# ...
# configure:5290: result: no
#
# That fails later with
# | ../../binutils-2.34/libiberty/fibheap.c: In function 'fibheap_replace_key_data':
# | ../../binutils-2.34/libiberty/fibheap.c:38:24: error: 'LONG_MIN' undeclared (first use in this function)
# |    38 | #define FIBHEAPKEY_MIN LONG_MIN
#
# So as long as we don't know whwer optimization get lost disable '-D_FORTIFY_SOURCE=2' set in
# conf/distro/include/security_flags.inc:
lcl_maybe_fortify = ""

EXTRA_OECONF = " \
    --target=avr \
    --disable-werror \
"

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


