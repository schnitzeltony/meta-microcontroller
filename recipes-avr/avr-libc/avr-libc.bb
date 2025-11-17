require ${BPN}.inc

inherit allarch

BBCLASSEXTEND = "nativesdk"

# Although we ship libraries this is allarch
INSANE_SKIP:${PN} = "arch"
