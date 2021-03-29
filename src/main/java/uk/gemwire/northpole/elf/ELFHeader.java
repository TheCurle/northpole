package uk.gemwire.northpole.elf;

public class ELFHeader {
    // Should be 0x7F, followed by "ELF" (0x45, 0x4C, 0x46)
    public static char[] Ident_Magic;

    // 1 for 32 bit, 2 for 64 bit.
    public static char Ident_Class;

    // 1 for little endian, 2 for big endian.
    public static char Ident_Data;

    // 1.
    public static char Ident_Version;

    // See ABI enum.
    public static char Ident_OSABI;

    // 0.
    public static char Ident_ABIVersion;

    // 0.
    public static char[] Ident_Pad;

    // See TypeValues enum.
    public static char[] Type;

    // See MachineValues enum.
    public static char[] Machine;

    // 1.
    public static char[] Version;

    // Entry point address. 4 chars if Ident_Class = 1, otherwise 8 chars.
    public static char[] Entry;

    // Offset to the Program Headers. 4 chars if Ident_Class = 1, otherwise 8 chars.
    public static char[] PHOffset;

    // Offset to the Section Headers. 4 chars if Ident_Class = 1, otherwise 8 chars.
    public static char[] SHOffset;

    // Platform dependant.
    public static char[] Flags;

    // 64 if Ident_Class = 1, otherwise 52.
    public static char[] ElfHeaderSize;

    // The size of a single entry in the Program Headers.
    public static char[] ProgramHeaderEntrySize;

    // The amount of entries in the Program Headers.
    public static char[] ProgramHeaderNumber;

    // The size of a single entry in the Section Headers.
    public static char[] SectionHeaderEntrySize;

    // The amount of entries in the Section Headers.
    public static char[] SectionHeaderNumber;

    // The index to the Section Header entry that contains the section names.
    public static char[] SectionHeaderStringIndex;

    // The end of the header. == ElfHeaderSize.
    public static char HeaderEnd;

    static class Offsets {
        public static final int IdentMagicOffset = 0;

        public static final int IdentClassOffset = 0x4;

        public static final int IdentDataOffset = 0x5;

        public static final int IdentVersionOffset = 0x6;

        public static final int IdentOSABIOffset = 0x7;

        public static final int IdentABIVersionOffset = 0x8;

        public static final int IdentPadOffset = 0x9;

        public static final int TypeOffset = 0x10;

        public static final int MachineOffset = 0x12;

        public static final int VersionOffset = 0x13;

        public static final int EntryOffset = 0x18;

        public static final int PHOffset32 = 0x1C;
        public static final int PHOffset64 = 0x20;

        public static final int SHOffset32 = 0x20;
        public static final int SHOffset64 = 0x28;

        public static final int FlagsOffset32 = 0x24;
        public static final int FlagsOffset64 = 0x30;

        public static final int EHSizeOffset32 = 0x28;
        public static final int EHSizeOffset64 = 0x34;

        public static final int PHEntSizeOffset32 = 0x2A;
        public static final int PHEntSizeOffset64 = 0x36;

        public static final int PHNumOffset32 = 0x2C;
        public static final int PHNumOffset64 = 0x38;

        public static final int SHEntSizeOffset32 = 0x2E;
        public static final int SHEntSizeOffset64 = 0x3A;

        public static final int SHNumOffset32 = 0x30;
        public static final int SHNumOffset64 = 0x3C;

        public static final int SHStrNdxOffset32 = 0x32;
        public static final int SHStrNdxOffset64 = 0x3E;

        public static final int EndOffset32 = 0x34;
        public static final int EndOffset64 = 0x40;
    }

    static class Defaults {
        // Some common values.
        public static final char[] BIT32ZERO = new char[] {0,0,0,0};
        public static final char[] BIT64ZERO = new char[] {0,0,0,0,0,0,0,0};
        public static final char[] CHAR7ZERO = new char[] {0,0,0,0,0,0,0};
        public static final char[] CHAR2ZERO = new char[] {0,0};

        public static final int ZERO = 0;
        public static final int ONE = 1;
        public static final int TWO = 2;

        // 0x7F followed by ASCII for "ELF". The magic number of the ELF header.
        public static final char[] ELF_IDENT_MAGIC = new char[] {0x7F, 0x45, 0x4C, 0x46};

        public static final int ELF_IDENT_CLASS_32 = ONE;
        public static final int ELF_IDENT_CLASS_64 = TWO;

        public static final int ELF_IDENT_DATA_LITTLE = ONE;
        public static final int ELF_IDENT_DATA_BIG = TWO;

        public static final int ELF_IDENT_VERSION = ONE;

        enum ABI {
            SYSTEM_V,
            HP_UX,
            NetBSD,
            Linux,
            GNU_Hurd,
            Solaris,
            AIX,
            IRIX,
            FreeBSD,
            Tru64,
            Novell_Modesto,
            OpenBSD,
            OpenVMS,
            NonStop_Kernel,
            AROS,
            Fenix_OS,
            CloudABI,
            Stratus_OpenVOS
        }

        public static final int ELF_IDENT_ABI = ZERO;

        public static final int ELF_IDENT_OSABI_VERSION = ZERO;

        public static final char[] ELF_IDENT_PAD = CHAR7ZERO;

        enum TypeValues {
            NONE  (0x0000),
            REL   (0x0001),
            EXEC  (0x0002),
            DYN   (0x0003),
            CORE  (0x0004),
            LOOS  (0xFE00),
            HIOS  (0xFEFF),
            LOPROC(0xFF00),
            HIPROC(0xFFFF);

            public int Value;

            TypeValues(int Val) { Value = Val; }
        }

        public static final int ELF_TYPE = TypeValues.EXEC.Value;

        enum MachineValues {
            NONE                (0x00),
            AT_T_WE_32100       (0x01),
            SPARC               (0x02),
            x86                 (0x03),
            M68k                (0x04),
            M88k                (0x05),
            Intel_MCU           (0x06),
            Intel_80860         (0x07),
            MIPS                (0x08),
            IBM_System370       (0x09),
            MIPS_LE             (0x0A),
            HP_PA_RISC          (0x0E),
            Intel_80960         (0x13),
            PowerPC32           (0x14),
            PowerPC64           (0x15),
            S390                (0x16),
            IBM_SPU_SPC         (0x17),
            NEC_V800            (0x24),
            Fujitsu_FR20        (0x25),
            TRW_RH32            (0x26),
            Motorola_RCE        (0x27),
            ARM                 (0x28),
            Digital_Alpha       (0x29),
            SuperH              (0x2A),
            SPARCV9             (0x2B),
            Siemens_TriCore     (0x2C),
            Argonaut_RISC       (0x2D),
            Hitachi_H8300       (0x2E),
            Hitachi_H8300H      (0x2F),
            Hitachi_H8S         (0x30),
            Hitachi_H8500       (0x31),
            IA_64               (0x32),
            Stanford_MIPS_X     (0x33),
            Motorola_ColdFire   (0x34),
            Motorola_M68HC12    (0x35),
            Fujitsu_MMA         (0x36),
            Siemens_PCP         (0x37),
            Sony_nCPU           (0x38),
            Denso_NDR1          (0x39),
            Motorola_StarCore   (0x3A),
            Toyota_ME16         (0x3B),
            STM_ST100           (0x3C),
            ALC_TinyJ           (0x3D),
            AMD64               (0x3E),
            TMS320C6000         (0x8C),
            ARM64               (0xB7),
            RISC_V              (0xF3),
            Berkely_PF          (0xF7),
            WDC_65C816          (0x101);

            public int Value;

            MachineValues(int Val) { Value = Val; }
        }

        public static final char[] ELF_VERSION = BIT32ZERO;

        public static final char[] ELF_ENTRY_32 = BIT32ZERO;
        public static final char[] ELF_ENTRY_64 = BIT64ZERO;

        public static final char[] ELF_PHOFF_32 = BIT32ZERO;
        public static final char[] ELF_PHOFF_64 = BIT64ZERO;

        public static final char[] ELF_SHOFF_32 = BIT32ZERO;
        public static final char[] ELF_SHOFF_64 = BIT64ZERO;

        public static final char[] ELF_FLAGS = BIT32ZERO;

        public static final char[] ELF_EHSIZE = CHAR2ZERO;

        public static final char[] ELF_PHENTSIZE = CHAR2ZERO;

        public static final char[] ELF_PHNUM = CHAR2ZERO;

        public static final char[] ELF_SHENTSIZE = CHAR2ZERO;

        public static final char[] ELF_SHNUM = CHAR2ZERO;

        public static final char[] ELF_SHSTRNDX = CHAR2ZERO;
    }
}
