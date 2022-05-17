require micropython.inc

inherit cmake native

S = "${WORKDIR}/git/lib/pico-sdk/tools/elf2uf2"

do_install() {
    install -d ${D}/${bindir}
    install -m0755 ${B}/elf2uf2 ${D}/${bindir}
}
