# AVR toolchain to build avr projects

inherit avr-toolchain-base

DEPENDS:append = " avr-libc"

