package uk.gemwire.northpole.elf;

import uk.gemwire.northpole.elf.sections.ProgramSection;
import uk.gemwire.northpole.elf.sections.Section;

public class ELF {
    public ELFHeader Header;

    public ProgramSection[] ProgramSections;

    public Section[] Sections;
}
