inherit cmake allarch

# To avoid rebuild on different arches on oe-core pre gatesgarth set compilers
# to dummy arch-constant values (empty values fail parsing)
CC = "murbeschligge"
CXX = "murbeschligge"
