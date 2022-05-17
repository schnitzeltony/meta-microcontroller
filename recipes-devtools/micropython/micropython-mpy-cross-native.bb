require micropython.inc

inherit native

do_compile() {
    oe_runmake -C mpy-cross
}

do_install() {
    install -d ${D}/${bindir}
    install -m0755 ${B}/mpy-cross/mpy-cross ${D}/${bindir}
}
