# AVR toolchain to build avr projects during YOCTO build

inherit allarch avr-toolchain-base

DEPENDS:append = " avr-libc-native"

INSANE_SKIP:${PN} = "arch"
