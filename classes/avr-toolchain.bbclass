# AVR toolchain to build avr projects

inherit avr-toolchain-base

DEPENDS:append = " avr-libc"

CFLAGS += "-I${STAGING_DIR_HOST}${prefix}/avr/include"

