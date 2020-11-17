inherit cmake allarch

# To avoid rebuild on different arches set compilers to native ones
CC = "${BUILD_CC}"
CXX = "${BUILD_CXX}"
