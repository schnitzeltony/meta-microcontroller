require micropython.inc

SUMMARY = "A lean and efficient Python implementation for microcontrollers"

DEPENDS += " \
    micropython-mpy-cross-native \
    readline \
    openssl \
    libffi \
    libusb1 \
"

EXTRA_OEMAKE += " \
    CROSS_COMPILE="${TARGET_PREFIX}" \
    CC="${CC}" \
    LD="${LD}" \
    MICROPY_MPYCROSS=${STAGING_BINDIR_NATIVE}/mpy-cross \
"

do_compile() {
    oe_runmake -C ports/unix
}

do_install() {
    oe_runmake -C ports/unix 'DESTDIR=${D}' install
}
