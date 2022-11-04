SUMMARY = "C library for use with GCC on Microchip (formerly Atmel) AVR microcontrollers"
HOMEPAGE = "http://www.nongnu.org/avr-libc/"
SECTION = "devel"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8d91a8f153d3de715f67a5df0d841c43"

HOST_SYS = "avr"

inherit avr-toolchain-base autotools-brokensep gettext texinfo

DEPENDS = " \
 	zlib-native \
    gawk-native \
    gmp-native \
    mpfr-native \
    libmpc-native \
    flex-native \
"

PV = "2.1.0+git${SRCPV}"
SRC_URI = " \
    git://github.com/avrdudes/avr-libc.git;protocol=https;branch=main \
    file://0001-Change-python-shebangs-to-python3.patch \
"
SRCREV = "55e8cac69935657bcd3e4d938750960c757844c3"
S = "${WORKDIR}/git"

do_configure:prepend() {
    cd ${S}
    ./devtools/gen-avr-lib-tree.sh
    touch ChangeLog
}

FILES:${PN} += "${prefix}/avr"
FILES:${PN}-staticdev += " \
    ${prefix}/avr/lib/*.a \
    ${prefix}/avr/lib/*/*.a \
    ${prefix}/avr/lib/*/*/*.a \
"

RDEPENDS:${PN} += "${PN}-staticdev"

SYSROOT_DIRS:append = " ${prefix}/avr"
