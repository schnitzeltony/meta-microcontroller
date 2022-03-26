# AVR toolchain to build avr projects

inherit avr-toolchain-base

PACKAGE_ARCH = "all"

DEPENDS:append = " avr-libc-native"

CFLAGS = "-I${STAGING_DIR_NATIVE}${prefix}/avr/include"
LDFLAGS = "-L${STAGING_DIR_NATIVE}${prefix}/avr/lib"

