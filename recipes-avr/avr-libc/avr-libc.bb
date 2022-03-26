require ${BPN}.inc

inherit allarch

# Although we ship libraries this is allarch
INSANE_SKIP:${PN} = "arch"
