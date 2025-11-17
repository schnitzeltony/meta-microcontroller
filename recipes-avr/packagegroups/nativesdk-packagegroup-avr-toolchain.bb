SUMMARY = "Packages for the avr-gcc toolchain in a stanalone SDK"
LICENSE = "MIT"

inherit packagegroup

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

RDEPENDS:${PN} += " \
    nativesdk-avr-gcc \
    nativesdk-avr-binutils \
    nativesdk-avr-libc \
    "
