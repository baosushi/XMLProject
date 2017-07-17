package com.utilities;

import BLO.BlockBLO;
import BLO.BlockOfMajorBLO;
import BLO.MajorBLO;
import BLO.UniversityBLO;
import com.entities.Block;
import com.entities.BlockOfMajor;
import com.entities.Major;
import com.entities.University;
import java.util.List;

public class JAXBUtils {

    public static void saveCrawlBlockToDb(List<Block> blocks) {
        BlockBLO blockBLO = new BlockBLO();
        for (Block block : blocks) {
            blockBLO.add(block);
        }
    }

    public static void saveCrawlUniversityToDb(List<University> universities) {
        UniversityBLO universityBLO = new UniversityBLO();
        for (University university : universities) {
            universityBLO.add(university);
        }
    }

    public static void saveCrawlBlockAndMajorToDb(Major major, List<BlockOfMajor> listBlocksOfMajor) {
        Integer indexUniversityDb = null;
        Integer indexMajorDb = null;
        Integer indexBlockDb = null;

        UniversityBLO universityBLO = new UniversityBLO();
        MajorBLO majorBLO = new MajorBLO();
        BlockBLO blockBLO = new BlockBLO();
        BlockOfMajorBLO blockOfMajorBLO = new BlockOfMajorBLO();

        indexUniversityDb = universityBLO.getIdBySchoolCode(major.getUniversityId().getCode());
        if (indexUniversityDb != null) {
            major.getUniversityId().setId(indexUniversityDb);
            Major tmpMajor = major;
            tmpMajor.setBlockOfMajorList(null);
            // Add major to DB
            if ((indexMajorDb = majorBLO.add(tmpMajor)) != null) {
//                indexMajorDb = majorBLO.getIdByCode(major.getMajorCode(), indexUniversityDb);

                for (BlockOfMajor blocksOfMajor : listBlocksOfMajor) {
                    blocksOfMajor.getMajorId().setId(indexMajorDb);

                    if (blocksOfMajor.getBlockId() != null) {
                        indexBlockDb = blockBLO.getIdByBlockName(blocksOfMajor.getBlockId().getBlockName());

                        if (indexBlockDb != null) {
                            blocksOfMajor.getBlockId().setId(indexBlockDb);
                            blockOfMajorBLO.add(blocksOfMajor);
                            indexBlockDb = null;
                        }
                    }
                } // end for blocksOfMajor
            } // if major of school not found
        } // if university not found
    }

    public static void clear() {
        try {
            MajorBLO majorBLO = new MajorBLO();
            BlockBLO blockBLO = new BlockBLO();
            BlockOfMajorBLO blockOfMajorBLO = new BlockOfMajorBLO();
            UniversityBLO universityBLO = new UniversityBLO();

            blockOfMajorBLO.deleteAllRecord();
            majorBLO.deleteAllRecord();
            universityBLO.deleteAllRecord();
            blockBLO.deleteAllRecord();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
