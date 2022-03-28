# AVR toolchain to build avr projects during YOCTO build

inherit allarch avr-toolchain-base

DEPENDS:append = " avr-libc-native"

doc_compile:prepend() {
    export CFLAGS = "-I${STAGING_DIR_NATIVE}${prefix}/avr/include"
    export LDFLAGS = "-L${STAGING_DIR_NATIVE}${prefix}/avr/lib"
}

INSANE_SKIP:${PN} = "arch"
