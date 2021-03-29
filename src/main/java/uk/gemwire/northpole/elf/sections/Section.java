package uk.gemwire.northpole.elf.sections;

public class Section {

    // The Section Header
    public SectionHeader Header;

    // The length of the data.
    public long Size;

    // The data inside the header.
    public char[] Data;


    static class SectionHeader {
        // Offset to the name of the header, in .shstrtab
        public static char[] Name;

        // See TypeValues enum.
        public static char[] Type;

        // Depends on the segment.
        public static char[] Flags;

        // Offset from the start of the file to the start of the segment.
        public static char[] Offset;

        // The size of the section on disk.
        public static char[] Size;

        // Section index of associated sections.
        public static char[] Link;

        // Extra data about the section.
        public static char[] Info;

        // 0 or 1 for no alignment. Must otherwise be a power of 2. Virtual Address = Offset % Alignment.
        public static char[] Alignment;

        // Size in bytes of each entry.
        public static char[] EntrySize;

        static class Offsets {
            public static final int NameOffset = 0;
            public static final int TypeOffset = 0x4;
            public static final int FlagsOffset = 0x8;

            // 64 bit is different from 32 bit. 32 first..
            public static final int AddrOffset32      = 0x0C;
            public static final int Offset32          = 0x10;
            public static final int SizeOffset32      = 0x14;
            public static final int LinkOffset32      = 0x18;
            public static final int InfoOffset32      = 0x1C;
            public static final int AlignmentOffset32 = 0x20;
            public static final int EntSizeOffset32   = 0x24;

            // 64 bit..
            public static final int AddrOffset64      = 0x10;
            public static final int Offset64          = 0x18;
            public static final int SizeOffset64      = 0x20;
            public static final int LinkOffset64      = 0x28;
            public static final int InfoOffset64      = 0x2C;
            public static final int AlignmentOffset64 = 0x30;
            public static final int EntSizeOffset64   = 0x38;

            public static final int EndOffset32     = 0x28;
            public static final int EndOffset64     = 0x40;
        }

        static class Defaults {
            private static final char[] ZERO32 = { 0,0,0,0 };
            private static final char[] ZERO64 = { 0,0,0,0,0,0,0,0 };

            enum TypeValues {
                NULL            (new char[] {0x00, 0x00, 0x00, 0x00}),
                PROGBITS        (new char[] {0x00, 0x00, 0x00, 0x01}),
                SYMTAB          (new char[] {0x00, 0x00, 0x00, 0x02}),
                STRTAB          (new char[] {0x00, 0x00, 0x00, 0x03}),
                RELA            (new char[] {0x00, 0x00, 0x00, 0x04}),
                HASH            (new char[] {0x00, 0x00, 0x00, 0x05}),
                DYN             (new char[] {0x00, 0x00, 0x00, 0x06}),
                NOTE            (new char[] {0x00, 0x00, 0x00, 0x07}),
                NOBITS          (new char[] {0x00, 0x00, 0x00, 0x08}),
                REL             (new char[] {0x00, 0x00, 0x00, 0x09}),
                SHLIB           (new char[] {0x00, 0x00, 0x00, 0x0A}),
                DYNSYM          (new char[] {0x00, 0x00, 0x00, 0x0B}),
                INIT            (new char[] {0x00, 0x00, 0x00, 0x0E}),
                FINI            (new char[] {0x00, 0x00, 0x00, 0x0F}),
                PREINIT         (new char[] {0x00, 0x00, 0x00, 0x10}),
                GROUP           (new char[] {0x00, 0x00, 0x00, 0x11}),
                SYMTAB_SHDNX    (new char[] {0x00, 0x00, 0x00, 0x12}),
                NUM             (new char[] {0x00, 0x00, 0x00, 0x13}),
                LOOS            (new char[] {0x60, 0x00, 0x00, 0x00}),
                HIOS            (new char[] {0x6F, 0xFF, 0xFF, 0xFF}),
                LOPROC          (new char[] {0x70, 0x00, 0x00, 0x00}),
                HIPROC          (new char[] {0x7F, 0xFF, 0xFF, 0xFF});

                public char[] Value;

                TypeValues(char[] Val) { Value = Val; }
            }

            public static final char[] ELF_SH_TYPE = TypeValues.NULL.Value;

            enum FlagValues {
                NULL        (new char[] {0x00, 0x00, 0x00, 0x00}),
                WRITE       (new char[] {0x00, 0x00, 0x00, 0x01}),
                ALLOC       (new char[] {0x00, 0x00, 0x00, 0x02}),
                EXECINSTR   (new char[] {0x00, 0x00, 0x00, 0x04}),
                MERGE       (new char[] {0x00, 0x00, 0x00, 0x10}),
                STRINGS     (new char[] {0x00, 0x00, 0x00, 0x20}),
                INFO_LINK   (new char[] {0x00, 0x00, 0x00, 0x40}),
                LINK_ORDER  (new char[] {0x00, 0x00, 0x00, 0x80}),
                OS_NONCONF  (new char[] {0x00, 0x00, 0x01, 0x00}),
                GROUP       (new char[] {0x00, 0x00, 0x02, 0x00}),
                TLS         (new char[] {0x00, 0x00, 0x04, 0x00}),
                MASKOS      (new char[] {0x0F, 0xF0, 0x00, 0x00}),
                MASKPROC    (new char[] {0xF0, 0x00, 0x00, 0x00}),
                ORDERED     (new char[] {0x04, 0x00, 0x00, 0x00}),
                EXCLUDE     (new char[] {0x08, 0x00, 0x00, 0x00});

                public char[] Value;

                FlagValues(char[] Val) { Value = Val; }
            }
            public static final char[] ELF_SH_FLAGS = FlagValues.EXECINSTR.Value;

            public static final char[] ELF_SH_ADDR32 = ZERO32;
            public static final char[] ELF_SH_ADDR64 = ZERO64;

            public static final char[] ELF_SH_OFFSET32 = ZERO32;
            public static final char[] ELF_SH_OFFSET64 = ZERO64;

            public static final char[] ELF_SH_SIZE32 = ZERO32;
            public static final char[] ELF_SH_SIZE64 = ZERO64;

            public static final char[] ELF_SH_LINK32 = ZERO32;
            public static final char[] ELF_SH_LINK64 = ZERO64;

            public static final char[] ELF_SH_INFO32 = ZERO32;
            public static final char[] ELF_SH_INFO64 = ZERO64;

            public static final char[] ELF_SH_ALIGN32 = ZERO32;
            public static final char[] ELF_SH_ALIGN64 = ZERO64;

            public static final char[] ELF_SH_ENTSIZE32 = ZERO32;
            public static final char[] ELF_SH_ENTSIZE64 = ZERO64;
        }
    }
}
