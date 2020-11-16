SUMMARY = "GNU compiler for Microchip (formerly Atmel) AVR microcontrollers"
HOMEPAGE = "http://www.gnu.org/software/gcc/"
SECTION = "devel"
LICENSE = "GPLv2 & GPLv3 & LGPLv2 & LGPLv3"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552 \
	file://COPYING3;md5=d32239bcb673463ab874e80d47fae504 \
	file://COPYING3.LIB;md5=6a6a8e020838b23406c81b19c1d46df6 \
	file://COPYING.LIB;md5=9f604d8a4f8e74f4f5140845a21b6674 \
"


SUMMARY = "GNU debugger"
HOMEPAGE = "http://www.gnu.org/software/gdb/"
SECTION = "devel"

DEPENDS = " \
    expat \
    zlib \
    ncurses \
    virtual/libiconv \
    bison-native \
"


inherit autotools texinfo gettext

BBCLASSEXTEND = "native"

SRC_URI = "https://ftp.gnu.org/gnu/gdb/gdb-${PV}.tar.xz"
SRC_URI[md5sum] = "f7e9f6236c425097d9e5f18a6ac40655"
SRC_URI[sha256sum] = "699e0ec832fdd2f21c8266171ea5bf44024bd05164fdf064e4d10cc4cf0d1737"

S = "${WORKDIR}/gdb-${PV}"

EXTRA_OECONF = " \
    --target=avr \
    --program-prefix=avr- \
    --disable-werror \
    --with-system-zlib \
"

PACKAGECONFIG ??= "readline"
# Use --without-system-readline to compile with readline 5.
PACKAGECONFIG[readline] = "--with-system-readline,--without-system-readline,readline"
PACKAGECONFIG[python] = "--with-python=${WORKDIR}/python,--without-python,python3,python3 python3-codecs"
PACKAGECONFIG[babeltrace] = "--with-babeltrace,--without-babeltrace,babeltrace"
# ncurses is already a hard DEPENDS, but would be added here if it weren't
PACKAGECONFIG[tui] = "--enable-tui,--disable-tui"

# see avr-binutils for further details
lcl_maybe_fortify = ""

do_configure () {
	# override this function to avoid the autoconf/automake/aclocal/autoheader
	# calls for now
	(cd ${S} && gnu-configize) || die "failure in running gnu-configize"
	oe_runconf
}

do_install_append() {
    # remove unncesssary files - they conflict with target gdb
    rm -rf ${D}/${datadir}/gdb/syscalls
    rm -rf ${D}/${datadir}/gdb/system-gdbinit
    # using rmdir is intended - it is going to fail if dir is no more empty
    rmdir ${D}/${datadir}/gdb

    # remove some files conflicting with target utils
    rm -rf ${D}/${datadir}/locale
    rm -rf ${D}/${datadir}/info
}
