# Will send this to meta-oe master but don't expect to get it into dunfell so:
PACKAGECONFIG_append = " ${@bb.utils.filter('DISTRO_FEATURES', 'opengl', d)}"

PACKAGECONFIG[opengl] = ",,libglu"
