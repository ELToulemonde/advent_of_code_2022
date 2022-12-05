package advent_kotlin.domain

class Section(val start: Int, val end: Int) {
    fun isSectionIncludedIn(section: Section): Boolean = this.start >= section.start && this.end <= section.end

}

class SectionPairs(val section_1: Section, val section_2: Section) {
    fun isOneSectionIncludedInOther(): Boolean =
        section_1.isSectionIncludedIn(section_2) || section_2.isSectionIncludedIn(section_1)

    fun asOverlap(): Boolean = !(section_1.end < section_2.start || section_1.start > section_2.end)
}