# Base AVR toolchain to build avr-libc

HOST_SYS = "avr"

inherit allarch

DEPENDS:append = " \
    avr-binutils-native \
    avr-gcc-native \
"

export AR = "avr-ar"
export AS = "avr-as"
export CC = "avr-gcc --sysroot=${STAGING_DIR_NATIVE}"
export CXX = "avr-g++ --sysroot=${STAGING_DIR_NATIVE}"
export CFLAGS = ""
export CXXFLAGS = ""
export CPPFLAGS = ""
export LD = "avr-ld --sysroot=${STAGING_DIR_NATIVE}"
export NM = "avr-nm"
export OBJCOPY = "avr-objcopy"
export OBJDUMP = "avr-objdump"
export RANLIB = "avr-ranlib"
export READELF = "avr-readelf"
export STRINGS = "avr-strings"
export STRIP = "avr-strip"

