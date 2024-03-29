SUMMARY = "GNU compiler for Microchip (formerly Atmel) AVR microcontrollers"
HOMEPAGE = "http://www.gnu.org/software/gcc/"
SECTION = "devel"
LICENSE = "GPL-2.0-or-later"

LIC_FILES_CHKSUM = "\
    file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552 \
    file://COPYING3;md5=d32239bcb673463ab874e80d47fae504 \
    file://COPYING3.LIB;md5=6a6a8e020838b23406c81b19c1d46df6 \
    file://COPYING.LIB;md5=2d5025d4aa3495befef8f17206a5b0a1 \
    file://COPYING.RUNTIME;md5=fe60d87048567d4fe8c8a0ed2448bcc8 \
"

inherit autotools gettext texinfo

BBCLASSEXTEND = "native"

DEPENDS = " \
    flex-native \
    avr-binutils \
    zlib \
    gawk \
    gmp \
    mpfr \
    libmpc \
"
DEPENDS:append:class-target = " ${BPN}-native"

PE = "1"

SRC_URI = " \
    https://ftp.gnu.org/gnu/gcc/gcc-${PV}/gcc-${PV}.tar.xz \
    file://0001-gcc-poison-system-directories.patch \
"
SRC_URI[sha256sum] = "e30a6e52d10e1f27ed55104ad233c30bd1e99cfb5ff98ab022dc941edd1b2dd4"

S = "${WORKDIR}/gcc-${PV}"

EXTRA_OECONF = " \
    --target=avr \
    --program-prefix=avr- \
    --enable-languages=c,c++ \
    --enable-c99 \
    --enable-long-long \
    --disable-bootstrap \
    --disable-libmudflap \
    --with-system-zlib \
    --with-build-sysroot=${STAGING_DIR_TARGET} \
"

# wow same as gcc in oe-core (checked dunfell)
SECURITY_STRINGFORMAT = ""

EXTRA_OECONF:append:class-target = " \
    --with-gnu-as \
    --with-gnu-ld \
    --with-as=${STAGING_BINDIR_NATIVE}/avr-as \
    --with-ld=${STAGING_BINDIR_NATIVE}/avr-ld \
"

do_configure() {
	(cd ${S} && gnu-configize)
	oe_runconf
}

CFLAGS += "-ftree-vectorize"

do_install:append() {
    # fix some host contamination - TBD: fix properly
    for file in `find ${D}/${libdir}/gcc/avr/${PV}/include`; do
        chown root:root $file
    done
    for file in `find ${D}/${libdir}/gcc/avr/${PV}/include-fixed`; do
        chown root:root $file
    done

    # remove some files conflicting with target utils
    rm -rf ${D}/${datadir}/locale
    rm -rf ${D}/${datadir}/info
    rm -rf ${D}/${datadir}/man/man7
}

FILES:${PN} += "${libdir}/gcc/avr"

FILES:${PN}-staticdev += " \
    ${libdir}/gcc/avr/${PV}/*.a \
    ${libdir}/gcc/avr/${PV}/*/*.a \
    ${libdir}/gcc/avr/${PV}/*/*/*.a \
"

# as long as there is no other libc we can pin avr-libc
RDEPENDS:${PN}:class-target += " \
    ${PN}-staticdev \
    avr-libc \
"

INSANE_SKIP:${PN} = "dev-so"

