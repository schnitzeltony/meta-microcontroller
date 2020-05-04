SUMMARY = "C library for use with GCC on Microchip (formerly Atmel) AVR microcontrollers"
HOMEPAGE = "http://www.nongnu.org/avr-libc/"
SECTION = "devel"
LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://LICENSE;md5=8d91a8f153d3de715f67a5df0d841c43"

HOST_SYS = "avr"

inherit avr-toolchain-base autotools gettext texinfo

DEPENDS = " \
 	zlib-native \
    gawk-native \
    gmp-native \
    mpfr-native \
    libmpc-native \
    flex-native \
"

SRC_URI = "http://download.savannah.gnu.org/releases/avr-libc/avr-libc-${PV}.tar.bz2"
SRC_URI[md5sum] = "2360981cd5d94e1d7a70dfc6983bdf15"
SRC_URI[sha256sum] = "b2dd7fd2eefd8d8646ef6a325f6f0665537e2f604ed02828ced748d49dc85b97"

do_configure() {
	(cd ${S} && gnu-configize)

	oe_runconf
}

FILES_${PN} += "${prefix}/avr"
FILES_${PN}-staticdev += " \
    ${prefix}/avr/lib/*.a \
    ${prefix}/avr/lib/*/*.a \
    ${prefix}/avr/lib/*/*/*.a \
"

RDEPENDS_${PN} += "${PN}-staticdev"

SYSROOT_DIRS_append = " ${prefix}/avr"

# Although we ship libraries this is allarch
INSANE_SKIP_${PN} = "arch"

