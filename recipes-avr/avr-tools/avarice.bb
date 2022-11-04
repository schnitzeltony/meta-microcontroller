SUMMARY = "AVaRICE interfaces GDB with AVR debugging tools"
HOMEPAGE = "http://avarice.sourceforge.net/"
SECTION = "devel"
LICENSE = "GPL-2.0-or-later"

LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

inherit autotools gettext

# hidapi?
DEPENDS = " \
    libusb-compat \
"

SRC_URI = "git://github.com/avrdudes/avarice.git;protocol=https;branch=main"
SRCREV = "5a125cdd403cf8b7d758a363f75e4e492d15e5ee"
S = "${WORKDIR}/git"
PV = "2.14+git${SRCPV}"

CXXFLAGS += "-std=c++11"

do_install:append() {
    # fix shebang in ice-gdb script
    sed -i 's:#!.*perl:#!/usr/bin/perl:' ${D}${bindir}/ice-gdb
}

RDEPENDS:${PN} += "perl"
RRECOMMENDS:${PN} += "avr-udev-rules"
