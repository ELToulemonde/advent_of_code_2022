package advent_kotlin

import advent_kotlin.domain.ElfGroup
import advent_kotlin.domain.Strategy
import advent_kotlin.domain.getLetterPriority
import advent_kotlin.domain.getMarkerIndex
import advent_kotlin.infrastructure.DataLoader


fun main() {
    val dataLoader1 = DataLoader("data/day1.txt")
    println("Day 1 part 2 : " + dataLoader1.readFileAndGetCalories())

    // Day 2
    val dataLoader2 = DataLoader("data/day2.txt")
    val strategies = dataLoader2.readStrategies()
    var totalScore = 0
    strategies.forEach { strategy: Strategy ->
        totalScore += strategy.getTotalScore()

    }
    println("Day 2 part 2 : " + totalScore)

    // Day 3
    val dataLoader3 = DataLoader("data/day3.txt")
    val ruckStacks = dataLoader3.readRuckStacks()
    var totalPriorities = 0
    ruckStacks.forEach { ruckStack ->
        val intersections: Set<String> = ruckStack.getSharedElementsInCompartments()
        if (intersections.size == 0) {
            println(ruckStack)
        }
        intersections.forEach { letter ->
            totalPriorities += getLetterPriority(letter)
        }
    }
    println("Day 3 part 1 : " + totalPriorities)

    // Day 3 part 2
    val numberOfRuckStacks = ruckStacks.size
    val numberOfGroups = numberOfRuckStacks / 3
    var badgesPriorites = 0
    for (groupId in 0..numberOfGroups - 1) {
        val elfGroup = ElfGroup(ruckStacks[groupId * 3], ruckStacks[groupId * 3 + 1], ruckStacks[groupId * 3 + 2])
        badgesPriorites += getLetterPriority(elfGroup.getBadge())
    }
    println("Day 3 part 2 : " + badgesPriorites.toString())


    // Day 4
    val dataLoader4 = DataLoader("data/day4.txt")
    val sectionPairs = dataLoader4.readSectionPairs()
    var totalIncludedSections = 0
    var totalSectionsWithOverlap = 0
    sectionPairs.forEach { sectionPair ->
        if (sectionPair.isOneSectionIncludedInOther()) {
            totalIncludedSections += 1
        }
        if (sectionPair.asOverlap()) {
            totalSectionsWithOverlap += 1
        }
    }
    println("Day 4, part 1 : " + totalIncludedSections.toString())
    println("Day 4, part 2 : " + totalSectionsWithOverlap.toString())


    // Day 5
    val dataLoader5 = DataLoader("data/day5.txt")
    val cratesMoves = dataLoader5.readMoves()
    val cratesStacks = dataLoader5.readCratesStack()
    cratesMoves.forEach { move ->
        cratesStacks.applyMove(move)
    }
    println("Day 5 part 1 : " + cratesStacks.getTopCrate())

    // Part 2
    val cratesStacks_part2 = dataLoader5.readCratesStack()
    cratesMoves.forEach { move ->
        cratesStacks_part2.applyMove9001(move)
    }
    println("Day 5 part 2 : " + cratesStacks_part2.getTopCrate())

    // Day 6
    val message: String =
        "mqllsjlslffbqbsbpbcbdbfbfvbfblfltffpddsmmhjmjvmvsmvvjfvfjjfccblbddhrdhrrlcrllrrswwlpwlwwgrglrgllrrlsshffrwfwcffmrrdfdrrncrnnlvllbsbcbwcchsshsrhhbnhbbqbmmmfdfvvqpqlpqppfgpfgpfpfttwrwwwfmfpfpmmpgglwglgfgsfgsggdllfhhmchmhttlhlchhrzrszsqqqzdzrrbbpgpccmfcmcbcdcrdrzzvjjgjfgfqfcqffvbbjbjbjsbjjbhjjzjlzjznzbbjvvpssdfdvffssrffpzfppsddgwgzgccrmrmdrrzpzbbjlbjlbjbgjbjmmtmllqffpjfppzzztfzfzfmfnndmdtmmhgmmsdshdhppbnpnbpnpdndgdbdwdtwwzqqjwjpphnnjddlglzlczzdpdjdjtjtgtqtptrrbqqqspsfswsdwdjdsdcdllpzpqplqqjnjrnnvrrnrccnwnnsttqzqcqppvmvdmdjjpnppljppptpzzwbwrrqgqhghshlslblflhflfbbwhwlhwhbwbdwbdwwzlwzwhzwznwwmmrddlttdrtrsttqbqtqtdqttqwwglwlbbdmbmcbbwbnbtbmbggdqqnhqqptpdtttvcvmvmdmtdttcwclccjcrctcllrprlrqqzqccttgnntstrtqqtfthtghhvcczhhctclljcljlcjcdjcdchhmjmjjrsjszjszsbsqqpfflqffqvfqvqjjnjtthvvvcjcjrcjclcwcrcrjcrchrcrggcjjpjjbwjbwjbwjwsjjrssfggzbgzgzrzmrmwmrrczcppnmnmzzgtglljlhjlhhgzgtgddrrhvvvlhlwhlhzzgzfgzzbvbvrvllplnlqnnfvnvgvdgvddsdqsdscscjjjvrvpptgpgdgtdtdzdtztfzfszfftjftfmftmtftddjzdzssfllfsllpwpwswrwdrwrcclczlclpccdrrftrftrtltftnftfnfrnfrfgfjfttrsrqsshlhdhnnztntwwnwppwbbmrbbtntznzggthghfggttpnpnpbplpvvpmmpwmbgcpwgsfndbrclcwbdcfhlcqblplglnqpnrpjqbddfqlqvbzrtwbwzvwqntcgmzrzztlffzmfmcmfzrmcvfctmlrlbtbpsgddbqrlblsslsbcmcglzdzjzlpgzprbrmfmlzrssqddzfjzfgbpvdgrrnldmtqgtjppqqwtzbltpfgpqtdqpwhbbwblnvvpmnljdghwrbnphswhgcvhpcplbbmwprznzzwnfntfplscpflhwdmlvfwtgrjhchnmnqbfgvsglllnnzwchqtcrvqzzhttcmblcthqrjdbvpwptcqtsnwrnfbbsqlshhtqdvcfcgdlbgzqjvzvglbcdwzpzttjnsvwrdldcqqstnnfnjthncgfvggphgfgstnmvnbmtvhpmsgmrccmmslqmjfzdjnbcjbjnpmsnvmzrphhjrdrrssnclvwbnzvpccqglnpljdtwrlnvpqzlshpcmfnmrjchqvlmthqbdrlnnpwcmfnwfzpbpnrsdmrqgqsjgwttwhgqlwghjntrvdndfhdwfzbwnmbjlzbhhdqfrdtwcjjvfnjbqdmdwncfhmslflvhqdmrcdrcdrldnqdmhzsvlglgflmlhwjqvfjdmqbmgffvdmmsbrrnrlcsbncvsjffttmhnbpwmqrnvdmzhztbbsrtwgfshjnlvhqvzwpvrmqfbsszswvrglnmwlmcdpjvmqsgnjshspzwrwctwwghmgjvbthcqcrlflsnrnpwvbnghrhvzpzchjlcljplflzqdvglgtvczhnbnlqltblddslqmdpvfbstvszqdsjvgfqlmdgbsnlzlrnbbqqfqjfqhljzlpbbgbnchwljjcpzbhdmwfzmqstcwtvgtvwcpgvmhpsngrshjvzzngbhjqmcfgjjzgdzcsbsvfwmznmwnnvlbntvcmgphqmdfjvhrlldcpwgnmbpjlqflvsrwqphvlpzlsdthfzdzvlphzdbqldvggsgrcmmfmfnjsfszqqbhnmntfgrbfwtlpqgwnrcqdsmqpqbtfdsnhbdcbwcdrhrfgsctrnlchrrnlptbcnqhndcpcdrgtznqrbgjlwzsjhblptncwtqcqcbzccrnjcmfvfnzwlrgdtgcvvcprwvnrrbdjzfnlvlqfpgbpwsvcnmnmmhnshtjgrcnscljwncdjqtwhlhvcggnwbzlzvfqmcdhmzddrdhvnnjbzbtnrgqcbmzhzzfldhlwwsgztfhncgctvjvszdzhrqmzvffmhvsqssjjvrrmtwqswhwjqgbfghbgfmgqssfhbcrglnbstfnqzvwqcznzgtnvjdvhtrlmgthcrqcwbjnzddsqhzwmdwndqcplhvpbpsdthngqwmlfqfndfqbpbwwrvsrnsjbsrwjdjbcqcvdfcsscgblggwggtmbntnbmmswfhvzhltwvprdgvzwltchhzsqlpwdndwftmsgbfwbpmhsdjhwbvvpzlpspsrsnpbwtdspfvdqdjfjbzmmtbnpzrqngccrbfndnjbcjfvwjvfjdvmsqdvgctzvpzmjmjvggpqfmmrsvqbrrlwrmzhmhpcmpltwdbtmwgzrrvsdhvhlwzggjwqzpbzvzrdbptzhzcrrjwjmdwdpsfwfspjgtmfcvddgspldbldtbtwrzdsjrbhvvcjgnrsbzvbrnqjwhrzgfsbdjlfqlszvlnrbfcrgfwrsmqmmnrwbtvfdpjzpfbhplfdsrwwgqqtgnzvddbgjjllmmcjjlglwmsbwrdrnnznwzplnbhlrlnmnllwgwgdpqdqqlmvsbgcshsmntrrlrvdhjgctzsfhmvfqtthvvchftflhlqqhhhbhqvgwtcmgcfwldhgptfddpsqrfzqmtpszswfrztzfsspltcvjwwsljsnjpnnqggscwwmcwfrljlrtqwqvplthsctvbndjfpnvcbdngzqtgjvwlsdhthdwmjvtnzrplwzwsfmgszpqjcjttslsmtbbvhjgpqmqfjbcccsnrlwmjhbsqgzqldmlhnbjnjfwmgzpvdcwndbwcncmtzccngcghhpwmjnncfgqtdtzwmhbdrpwsfbnjzfnwzwqncnlfjqjrjhgnqgvbcdhgdnbwpqjcfgprmfhzlrqtwlqpshfrgdszrwdtqfcntrzbgzlvrhtlsbjjwtnlqllnsvbzwjlmqvdgvtslmbwwcfstmqntwwwsjmrflrqnttfzjchpgwczzdtqbhdrtrpvhhbscvjtdtrhbstpqrnrzszwvcqzhbrzhlblvzrgwtqzbslbmgdqhpfqrdqrzcsbglcsshcwvlcpgjtjmcgpmsnldjzlwnrqlzzznpvmgssvzshjvtsmmzvstpqrhfvttnsrddfcqcbwhgpfdtlhcvcgjgdrvvntvdjqpvwvfmphhpzjgmshddqfsbpjbzrfdjnwrhmgcfbccmzqgvrbmcjdpvwfrtdpbwvjtjcrmnpzrrqbbvbsgcplwmlbsdwptbprlczjcqhdzprpttvnthbmtscdtjvrnwqhnvqbzvwnphnzwlgvvjhddjvjrvwlmhqcsffcnhgjzdjppqqwbglbhgzsmvzwjdvbqpztphshtrbrrhzmdlfdtssbhrcltwlqpzvpgbsgngpfjsjbrnnlzctqcqzwswhfnjjngwsztdgmmcffqfhbsgwstnflqjqttzbtgjvcfrrdwzcvhwjnhmtphszrsptjsqqwcwfnmtlzvzsqsmghtztrpvdslrmjqqvwfmzlwwjbwtpmhtqcfctdztsnfrhfqwqcjdzmjhvwwgrslmdqqwgwfvwlzzsznmdrzgcvbmrtcvjsqlftnpdhwmrzjwsnjjdrczbjcwhwlrtljwjsfmcfcrsjflsldbjrzpdgltmhtszzznjjlfqmgpbjfjncvtvlcfsmltbsvsrgdhwwhcpbdbntqhgjztvlwtwdsgqfwtlcdzffcszjmjvj"

    println("Day 6 part 1 : " + getMarkerIndex(message, 4).toString())
    println("Day 6 part 2 : " + getMarkerIndex(message, 14).toString())

    // Day 7
    val dataLoader7 = DataLoader("data/day7.txt")
    val fileSystem = dataLoader7.readFileSystem()
    println("Day 7 part 1 : " + fileSystem.sumOfSizeOfAllFoldersWithLowerSizeThan(100000))
    val spaceToFree = fileSystem.getSpaceToFree(30000000, 70000000)
    println("Day 7 part 2 : " + fileSystem.getMinFolderSizeToDelete(spaceToFree))

    // Day 8
    val dataLoader8 = DataLoader("data/day8.txt")
    val forest = dataLoader8.readForest()
    println("Day 8 part 1 : " + forest.numberOfVisibleTrees())
    println("Day 8 part 2 : " + forest.maxScenicScore())

    // Day 10
    val dataLoader10 = DataLoader("data/day10.txt")
    val communicationSystem = dataLoader10.readCommunicationSystem()
    val result = 20 * communicationSystem.getSignalAtCycle(20) +
            60 * communicationSystem.getSignalAtCycle(60) +
            100 * communicationSystem.getSignalAtCycle(100) +
            140 * communicationSystem.getSignalAtCycle(140) +
            180 * communicationSystem.getSignalAtCycle(180) +
            220 * communicationSystem.getSignalAtCycle(220)
    println("Day 10 part 1 : " + result)
    val screen = communicationSystem.draw()
    println("Day 10 part 2 : ")
    screen.forEach{
        println(it)
    }
}