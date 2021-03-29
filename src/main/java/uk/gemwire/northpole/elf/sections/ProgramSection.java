package uk.gemwire.northpole.elf.sections;

public class ProgramSection {

    // The Program Section Header
    public ProgramHeader Header;

    // The length of the data.
    public long Size;

    // The data inside the header.
    public char[] Data;


    static class ProgramHeader {
        // See TypeValues enum.
        public static char[] Type;

        // Depends on the segment.
        public static char[] Flags;

        // Offset from the start of the file to the start of the segment.
        public static char[] Offset;

        // The virtual address this segment is to be placed at.
        public static char[] VAddr;

        // The physical address this segment is expected to be at (where this is relevant)
        public static char[] PAddr;

        // Size in bytes of the segment on disk
        public static char[] FileSz;

        // Size in bytes of the segment in memory
        public static char[] MemSz;

        // 0 or 1 for no alignment. Must otherwise be a power of 2. Virtual Address = Offset % Alignment.
        public static char[] Alignment;

        static class Offsets {
            public static final int TypeOffset = 0;

            // 64 bit is different from 32 bit. 64 first..
            public static final int FlagsOffset64   = 0x4;
            public static final int Offset64        = 0x8;
            public static final int VAddrOffset64   = 0x10;
            public static final int PAddrOffset64   = 0x18;
            public static final int FileSzOffset64  = 0x20;
            public static final int MemSzOffset64   = 0x28;
            public static final int AlignOffset64   = 0x30;

            // 32 bit..
            public static final int Offset32        = 0x4;
            public static final int VAddrOffset32   = 0x8;
            public static final int PAddrOffset32   = 0x0C;
            public static final int FileSzOffset32  = 0x10;
            public static final int MemSzOffset32   = 0x14;
            public static final int FlagsOffset32   = 0x18;
            public static final int AlignOffset32   = 0x1C;

            public static final int EndOffset32     = 0x20;
            public static final int EndOffset64     = 0x38;
        }

        static class Defaults {
            private static final char[] ZERO32 = { 0,0,0,0 };
            private static final char[] ZERO64 = { 0,0,0,0,0,0,0,0 };

            enum TypeValues {
                NULL    (new char[] {0x00, 0x00, 0x00, 0x00}),
                LOAD    (new char[] {0x00, 0x00, 0x00, 0x01}),
                DYN     (new char[] {0x00, 0x00, 0x00, 0x02}),
                INTERP  (new char[] {0x00, 0x00, 0x00, 0x03}),
                NOTE    (new char[] {0x00, 0x00, 0x00, 0x04}),
                SHLIB   (new char[] {0x00, 0x00, 0x00, 0x05}),
                PHDR    (new char[] {0x00, 0x00, 0x00, 0x06}),
                TLS     (new char[] {0x00, 0x00, 0x00, 0x07}),
                LOOS    (new char[] {0x60, 0x00, 0x00, 0x00}),
                HIOS    (new char[] {0x6F, 0xFF, 0xFF, 0xFF}),
                LOPROC  (new char[] {0x70, 0x00, 0x00, 0x00}),
                HIPROC  (new char[] {0x7F, 0xFF, 0xFF, 0xFF});

                public char[] Value;

                TypeValues(char[] Val) { Value = Val; }
            }

            public static final char[] ELF_PH_TYPE = TypeValues.NULL.Value;
            public static final char[] ELF_PH_FLAGS = ZERO32;

            public static final char[] ELF_PH_OFFSET32 = ZERO32;
            public static final char[] ELF_PH_OFFSET64 = ZERO64;

            public static final char[] ELF_PH_VADDR32 = ZERO32;
            public static final char[] ELF_PH_VADDR64 = ZERO64;

            public static final char[] ELF_PH_PADDR32 = ZERO32;
            public static final char[] ELF_PH_PADDR64 = ZERO64;

            public static final char[] ELF_PH_FILESZ32 = ZERO32;
            public static final char[] ELF_PH_FILESZ64 = ZERO64;

            public static final char[] ELF_PH_MEMSZ32 = ZERO32;
            public static final char[] ELF_PH_MEMSZ64 = ZERO64;

            public static final char[] ELF_PH_ALIGN32 = ZERO32;
            public static final char[] ELF_PH_ALIGN64 = ZERO64;
        }
    }
}
